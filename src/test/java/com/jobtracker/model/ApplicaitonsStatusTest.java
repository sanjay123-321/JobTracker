package com.jobtracker.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ApplicationStatusTest {

    @Test
    void appliedStatusNameIsCorrectString() {
        ApplicationStatus status = ApplicationStatus.APPLIED;
        assertEquals("APPLIED", status.name());
    }
}