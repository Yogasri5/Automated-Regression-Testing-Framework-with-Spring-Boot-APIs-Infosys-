package com.infosys.testingFramework.service;

import com.infosys.testingFramework.repositories.TestResultRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class AnalyticsService {

    private static final Logger logger = LoggerFactory.getLogger(AnalyticsService.class);

    private final TestResultRepository repo;

    public AnalyticsService(TestResultRepository repo) {
        this.repo = repo;
        logger.info("AnalyticsService initialized");
    }

    public List<Map<String, Object>> failureTrends() {
        logger.info("Retrieving failure trends");
        List<Map<String, Object>> trends = repo.failureTrends().stream()
                .map(r -> Map.of(
                        "date", r[0],
                        "failures", r[1]
                ))
                .toList();
        logger.info("Retrieved {} failure trend records", trends.size());
        return trends;
    }

    public Map<String, Object> getSummary() {
        logger.info("Retrieving analytics summary");
        long totalTests = repo.count();
        Map<String, Object> summary = Map.of(
                "totalTests", totalTests,
                "failureTrends", failureTrends(),
                "successRate", getSuccessRate(),
                "testCaseStats", getTestCaseStats()
        );
        logger.info("Analytics summary: {}", summary);
        return summary;
    }

    public double getSuccessRate() {
        logger.info("Calculating success rate");
        long total = repo.count();
        if (total == 0) {
            logger.warn("No test results found for success rate calculation");
            return 0.0;
        }
        long passed = repo.findAll().stream()
                .mapToLong(r -> r.getStatus().equals("PASSED") ? 1 : 0)
                .sum();
        double rate = (double) passed / total * 100;
        logger.info("Success rate calculated: {}%", rate);
        return rate;
    }

    public Map<String, Object> getTestCaseStats() {
        logger.info("Retrieving test case statistics");
        // Assuming TestResult has testCaseId
        // This is a placeholder; in real implementation, group by testCaseId
        Map<String, Object> stats = Map.of(
                "totalTestCases", repo.findAll().stream()
                        .map(r -> r.getTestCase().getId())
                        .distinct()
                        .count(),
                "averageExecutionTime", 0.0 // Placeholder
        );
        logger.info("Test case stats: {}", stats);
        return stats;
    }
}
