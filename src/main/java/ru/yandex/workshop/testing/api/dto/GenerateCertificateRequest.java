package ru.yandex.workshop.testing.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record GenerateCertificateRequest(
    @NotBlank @Email String studentEmail,
    @Min(0) int solvedTasks
) {
}
