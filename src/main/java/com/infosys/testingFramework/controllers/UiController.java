package com.infosys.testingFramework.controllers;

import com.infosys.testingFramework.models.TestCase;
import com.infosys.testingFramework.repositories.TestResultRepository;
import com.infosys.testingFramework.service.AnalyticsService;
import com.infosys.testingFramework.service.ExecutionService;
import com.infosys.testingFramework.service.TestCaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/ui")
public class UiController {

    private static final Logger logger = LoggerFactory.getLogger(UiController.class);

    private final TestCaseService testCaseService;
    private final ExecutionService executionService;
    private final AnalyticsService analyticsService;
    private final TestResultRepository resultRepo;

    public UiController(
            TestCaseService testCaseService,
            ExecutionService executionService,
            AnalyticsService analyticsService,
            TestResultRepository resultRepo
    ) {
        this.testCaseService = testCaseService;
        this.executionService = executionService;
        this.analyticsService = analyticsService;
        this.resultRepo = resultRepo;
        logger.info("UiController initialized");
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        logger.info("Accessing dashboard");
        model.addAttribute("testCases", testCaseService.findAll());
        model.addAttribute("testCase", new TestCase());
        logger.info("Dashboard loaded with test cases");
        return "dashboard";
    }

    @PostMapping("/testcase")
    public String createTestCase(@ModelAttribute TestCase testCase) {
        logger.info("Creating test case via UI: {}", testCase.getName());
        testCaseService.save(testCase);   // ✅ SAME SERVICE AS REST
        logger.info("Test case created via UI");
        return "redirect:/ui/dashboard";
    }

    @PostMapping("/run")
    public String runTests(@RequestParam List<Long> testCaseIds) {
        logger.info("Running tests via UI for IDs: {}", testCaseIds);
        executionService.schedule(testCaseIds); // ✅ SAME API LOGIC
        logger.info("Tests scheduled via UI");
        return "redirect:/ui/execution";
    }

    @GetMapping("/execution")
    public String execution(Model model) {
        logger.info("Accessing execution page");
        model.addAttribute("results", resultRepo.findAll());
        logger.info("Execution page loaded with results");
        return "execution";
    }

    @GetMapping("/analytics")
}
