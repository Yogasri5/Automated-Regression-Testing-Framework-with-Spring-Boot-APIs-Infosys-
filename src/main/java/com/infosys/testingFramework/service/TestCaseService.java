package com.infosys.testingFramework.service;

import com.infosys.testingFramework.models.TestCase;
import com.infosys.testingFramework.repositories.TestCaseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TestCaseService {

    private static final Logger logger = LoggerFactory.getLogger(TestCaseService.class);

    private final TestCaseRepository repo;

    public TestCaseService(TestCaseRepository repo) {
        this.repo = repo;
        logger.info("TestCaseService initialized");
    }

    public TestCase save(TestCase tc) {
        logger.info("Saving test case: {}", tc.getName());
        TestCase savedTc = repo.save(tc);
        logger.info("Test case saved with ID: {}", savedTc.getId());
        return savedTc;
    }

    public List<TestCase> findAllByIds(List<Long> ids) {
        logger.info("Finding test cases by IDs: {}", ids);
        List<TestCase> testCases = repo.findAllById(ids);
        logger.info("Found {} test cases", testCases.size());
        return testCases;
    }

    public List<TestCase> findAll() {
        logger.info("Retrieving all test cases");
        List<TestCase> testCases = repo.findAll();
        logger.info("Retrieved {} test cases", testCases.size());
        return testCases;
    }

    public Optional<TestCase> findById(Long id) {
        logger.info("Finding test case by ID: {}", id);
        Optional<TestCase> testCase = repo.findById(id);
        if (testCase.isPresent()) {
            logger.info("Test case found: {}", testCase.get().getName());
        } else {
            logger.warn("Test case not found with ID: {}", id);
        }
        return testCase;
    }

    public void deleteById(Long id) {
        logger.info("Deleting test case by ID: {}", id);
        repo.deleteById(id);
        logger.info("Test case deleted with ID: {}", id);
    }

    public TestCase update(TestCase tc) {
        logger.info("Updating test case: {}", tc.getName());
        TestCase updatedTc = repo.save(tc);
        logger.info("Test case updated with ID: {}", updatedTc.getId());
        return updatedTc;
    }
}
