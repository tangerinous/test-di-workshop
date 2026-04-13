package ru.yandex.workshop.testing.api.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public record IssueRewardRequest(
    @NotNull Long customerId,
    @NotNull @DecimalMin("0.01") BigDecimal purchaseAmount
) {
}
