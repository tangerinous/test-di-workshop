package ru.yandex.workshop.testing.reward;

import java.math.BigDecimal;
import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "reward_issues")
public class RewardIssue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long customerId;

    @Column(nullable = false)
    private BigDecimal purchaseAmount;

    @Column(nullable = false)
    private BigDecimal rewardAmount;

    @Column(nullable = false)
    private String campaignName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RewardStatus status;

    @Column(nullable = false)
    private Instant createdAt;

    protected RewardIssue() {
    }

    public RewardIssue(
        Long customerId,
        BigDecimal purchaseAmount,
        BigDecimal rewardAmount,
        String campaignName,
        RewardStatus status,
        Instant createdAt
    ) {
        this.customerId = customerId;
        this.purchaseAmount = purchaseAmount;
        this.rewardAmount = rewardAmount;
        this.campaignName = campaignName;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public BigDecimal getPurchaseAmount() {
        return purchaseAmount;
    }

    public BigDecimal getRewardAmount() {
        return rewardAmount;
    }

    public String getCampaignName() {
        return campaignName;
    }

    public RewardStatus getStatus() {
        return status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
