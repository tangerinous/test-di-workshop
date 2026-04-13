package ru.yandex.workshop.testing.api.dto;

import java.math.BigDecimal;

public record RewardResponse(
    String status,
    BigDecimal rewardAmount,
    String campaignName,
    String message
) {
}
