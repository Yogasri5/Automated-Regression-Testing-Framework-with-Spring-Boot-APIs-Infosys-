package com.infosys.testingFramework.execution;

import com.infosys.testingFramework.models.ExecutionRun;
import com.infosys.testingFramework.models.TestCase;
import com.infosys.testingFramework.models.TestResult;
import com.infosys.testingFramework.repositories.TestResultRepository;
import java.time.LocalDateTime;

public class ExecutionTask implements Runnable {

    private final TestCase testCase;
    private final TestResultRepository resultRepo;
    private final ExecutionRun run;

    public ExecutionTask(
            TestCase testCase,
            TestResultRepository resultRepo,
            ExecutionRun run
    ) {
        this.testCase = testCase;
        this.resultRepo = resultRepo;
        this.run = run;
    }

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        boolean success;

        if ("UI".equalsIgnoreCase(testCase.getType())) {
            success = new SeleniumTestRunner().run(testCase.getTarget());
        } else {
            success = new ApiTestRunner().run(
                    testCase.getTarget(),
                    testCase.getMethod()
            );
        }

        long end = System.currentTimeMillis();

        TestResult result = new TestResult();
        result.setTestCaseId(testCase.getId());
        result.setStatus(success ? "PASS" : "FAIL");
        result.setExecutionTime(end - start);
        result.setExecutedAt(LocalDateTime.now());
        result.setExecutionRun(run);

        resultRepo.save(result);
    }
}
