package com.infosys.testingFramework.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Data
@Getter
@Setter
public class TestResult {

    @Id
    @GeneratedValue
    private Long id;

    private Long testCaseId;

    private String status; // PASS / FAIL

    private long executionTime;

    private LocalDateTime executedAt;

    @ManyToOne
    private ExecutionRun executionRun;
}
