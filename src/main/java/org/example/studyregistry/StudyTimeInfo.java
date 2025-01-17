package org.example.studyregistry;

import java.time.LocalDateTime;

public record StudyTimeInfo(
        LocalDateTime startDate,
        LocalDateTime endDate
) {}
