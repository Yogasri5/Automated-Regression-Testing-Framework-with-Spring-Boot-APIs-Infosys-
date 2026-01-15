package com.infosys.testingFramework.service;

import com.infosys.testingFramework.execution.ExecutionTask;
import com.infosys.testingFramework.models.ExecutionRun;
import com.infosys.testingFramework.models.TestCase;
import com.infosys.testingFramework.repositories.ExecutionRunRepository;
import com.infosys.testingFramework.repositories.TestResultRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Service
public class ExecutionService {

    private static final Logger logger = LoggerFactory.getLogger(ExecutionService.class);

    private final TestCaseService testCaseService;
    private final TestResultRepository resultRepo;
    private final ExecutionRunRepository runRepo;

    private final ExecutorService executor =
            Executors.newFixedThreadPool(5);

    public ExecutionService(
            TestCaseService testCaseService,
            TestResultRepository resultRepo,
            ExecutionRunRepository runRepo
    ) {
        this.testCaseService = testCaseService;
        this.resultRepo = resultRepo;
        this.runRepo = runRepo;
        logger.info("ExecutionService initialized");
    }

    public ExecutionRun schedule(List<Long> testCaseIds) {
        logger.info("Scheduling execution for test case IDs: {}", testCaseIds);
        // Validate the input list
        if (testCaseIds == null || testCaseIds.isEmpty()) {
            logger.error("Test case IDs must not be null or empty");
            throw new IllegalArgumentException("Test case IDs must not be null or empty");
        }

        ExecutionRun run = new ExecutionRun();
        run.setStatus("RUNNING");
        run.setStartedAt(LocalDateTime.now());
        runRepo.save(run);
        logger.info("Execution run created with ID: {}", run.getId());

        List<TestCase> testCases =
                testCaseService.findAllByIds(testCaseIds);
        logger.info("Found {} test cases to execute", testCases.size());

        for (TestCase tc : testCases) {
            logger.debug("Submitting execution task for test case: {}", tc.getName());
            executor.submit(new ExecutionTask(tc, resultRepo, run));
        }

        logger.info("Execution scheduled successfully");
        return run;
    }

    public Optional<ExecutionRun> findById(Long id) {
        logger.info("Finding execution run by ID: {}", id);
        Optional<ExecutionRun> run = runRepo.findById(id);
        if (run.isPresent()) {
            logger.info("Execution run found: {}", run.get().getId());
        } else {
            logger.warn("Execution run not found with ID: {}", id);
        }
        return run;
    }

    public List<ExecutionRun> findAll() {
        logger.info("Retrieving all execution runs");
        List<ExecutionRun> runs = runRepo.findAll();
        logger.info("Retrieved {} execution runs", runs.size());
        return runs;
    }

    public void deleteById(Long id) {
        logger.info("Deleting execution run by ID: {}", id);
        runRepo.deleteById(id);
        logger.info("Execution run deleted with ID: {}", id);
    }
}
