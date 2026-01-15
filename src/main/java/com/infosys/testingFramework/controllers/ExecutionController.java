package com.infosys.testingFramework.controllers;

import com.infosys.testingFramework.dtos.ExecutionStatusResponse;
import com.infosys.testingFramework.dtos.ScheduleRequest;
import com.infosys.testingFramework.models.ExecutionRun;
import com.infosys.testingFramework.service.ExecutionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/execution")
public class ExecutionController {

    private static final Logger logger = LoggerFactory.getLogger(ExecutionController.class);

    private final ExecutionService service;

    public ExecutionController(ExecutionService service) {
        this.service = service;
        logger.info("ExecutionController initialized");
    }

    @PostMapping("/schedule/run")
    public ExecutionStatusResponse run(@Valid @RequestBody ScheduleRequest request) {
        logger.info("Scheduling execution run for test case IDs: {}", request.getTestCaseIds());
        ExecutionRun run = service.schedule(request.getTestCaseIds());
        logger.info("Execution run scheduled with ID: {}", run.getId());
        return new ExecutionStatusResponse(run.getId(), run.getStatus());
    }
}
