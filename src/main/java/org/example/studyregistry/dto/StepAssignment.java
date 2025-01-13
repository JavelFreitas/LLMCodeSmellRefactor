package org.example.studyregistry.dto;
import java.time.LocalDateTime;
import java.util.List;
public record StepAssignment(
        List<String> steps,
        Integer numberOfSteps,
        boolean isImportant,
        LocalDateTime startDate,
        LocalDateTime endDate
) {}