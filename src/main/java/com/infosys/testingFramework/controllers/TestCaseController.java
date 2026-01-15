package com.infosys.testingFramework.controllers;

import com.infosys.testingFramework.models.TestCase;
import com.infosys.testingFramework.service.TestCaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/testcases")
public class TestCaseController {

    private static final Logger logger = LoggerFactory.getLogger(TestCaseController.class);

    private final TestCaseService service;

    public TestCaseController(TestCaseService service) {
        this.service = service;
        logger.info("TestCaseController initialized");
    }

    @PostMapping
    public TestCase create(@Valid @RequestBody TestCase tc) {
        logger.info("Creating new test case: {}", tc.getName());
        TestCase savedTc = service.save(tc);
        logger.info("Test case created with ID: {}", savedTc.getId());
        return savedTc;
    }

    @GetMapping
    public List<TestCase> getAll() {
        logger.info("Retrieving all test cases");
        List<TestCase> testCases = service.findAll();
        logger.info("Retrieved {} test cases", testCases.size());
        return testCases;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TestCase> getById(@PathVariable Long id) {
        logger.info("Retrieving test case by ID: {}", id);
        Optional<TestCase> testCase = service.findById(id);
        if (testCase.isPresent()) {
            logger.info("Test case found: {}", testCase.get().getName());
            return ResponseEntity.ok(testCase.get());
        } else {
            logger.warn("Test case not found with ID: {}", id);
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TestCase> update(@PathVariable Long id, @Valid @RequestBody TestCase tc) {
        logger.info("Updating test case with ID: {}", id);
        Optional<TestCase> existing = service.findById(id);
        if (existing.isPresent()) {
            tc.setId(id);
            TestCase updatedTc = service.update(tc);
            logger.info("Test case updated: {}", updatedTc.getName());
            return ResponseEntity.ok(updatedTc);
        } else {
            logger.warn("Test case not found for update with ID: {}", id);
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        logger.info("Deleting test case with ID: {}", id);
        Optional<TestCase> existing = service.findById(id);
        if (existing.isPresent()) {
            service.deleteById(id);
            logger.info("Test case deleted with ID: {}", id);
            return ResponseEntity.noContent().build();
        } else {
            logger.warn("Test case not found for deletion with ID: {}", id);
            return ResponseEntity.notFound().build();
        }
    }
}
