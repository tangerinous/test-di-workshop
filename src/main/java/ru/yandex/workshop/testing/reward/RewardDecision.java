package ru.yandex.workshop.testing.reward;

import java.math.BigDecimal;

public record RewardDecision(
    RewardStatus status,
    BigDecimal rewardAmount,
    String campaignName,
    String message
) {

    public static RewardDecision approved(BigDecimal rewardAmount, String campaignName) {
        return new RewardDecision(
            RewardStatus.APPROVED,
            rewardAmount,
            campaignName,
            "Reward approved"
        );
    }

    public static RewardDecision rejected(RewardStatus status, String message) {
        return new RewardDecision(status, BigDecimal.ZERO, null, message);
    }
}
