package ru.yandex.workshop.testing.reward;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Clock;
import java.time.Instant;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.yandex.workshop.testing.customer.Customer;
import ru.yandex.workshop.testing.customer.CustomerRepository;
import ru.yandex.workshop.testing.integration.CampaignGateway;
import ru.yandex.workshop.testing.integration.FraudCheckClient;
import ru.yandex.workshop.testing.integration.MessageGateway;
import ru.yandex.workshop.testing.integration.model.Campaign;
import ru.yandex.workshop.testing.integration.model.FraudVerdict;

@Service
public class RewardIssuingService {

    private static final BigDecimal MIN_PURCHASE = new BigDecimal("3000");
    private static final BigDecimal BONUS_MESSAGE_THRESHOLD = new BigDecimal("500");

    private final CustomerRepository customerRepository;
    private final RewardIssueRepository rewardIssueRepository;
    private final CampaignGateway campaignGateway;
    private final FraudCheckClient fraudCheckClient;
    private final MessageGateway messageGateway;
    private final Clock clock;

    public RewardIssuingService(
        CustomerRepository customerRepository,
        RewardIssueRepository rewardIssueRepository,
        CampaignGateway campaignGateway,
        FraudCheckClient fraudCheckClient,
        MessageGateway messageGateway,
        Clock clock
    ) {
        this.customerRepository = customerRepository;
        this.rewardIssueRepository = rewardIssueRepository;
        this.campaignGateway = campaignGateway;
        this.fraudCheckClient = fraudCheckClient;
        this.messageGateway = messageGateway;
        this.clock = clock;
    }

    @Transactional
    public RewardDecision issueReward(Long customerId, BigDecimal purchaseAmount) {
        Customer customer = customerRepository.findById(customerId)
            .orElseThrow(() -> new IllegalArgumentException("Customer not found: " + customerId));

        if (!customer.isActive()) {
            return RewardDecision.rejected(RewardStatus.REJECTED_INACTIVE_CUSTOMER, "Customer is inactive");
        }

        if (purchaseAmount.compareTo(MIN_PURCHASE) < 0) {
            return RewardDecision.rejected(RewardStatus.REJECTED_SMALL_PURCHASE, "Purchase is too small");
        }

        FraudVerdict fraudVerdict = fraudCheckClient.check(customerId, purchaseAmount);
        if (fraudVerdict.blocked()) {
            return RewardDecision.rejected(RewardStatus.REJECTED_FRAUD, fraudVerdict.reason());
        }

        Campaign campaign = campaignGateway.findCampaignForSegment(customer.getSegment())
            .orElse(null);
        if (campaign == null) {
            return RewardDecision.rejected(RewardStatus.REJECTED_NO_CAMPAIGN, "No campaign for segment");
        }

        BigDecimal rewardAmount = calculateReward(purchaseAmount, campaign, customer);
        rewardIssueRepository.save(new RewardIssue(
            customer.getId(),
            purchaseAmount,
            rewardAmount,
            campaign.name(),
            RewardStatus.APPROVED,
            Instant.now(clock)
        ));

        if (rewardAmount.compareTo(BONUS_MESSAGE_THRESHOLD) >= 0) {
            messageGateway.sendRewardIssuedMessage(
                customer.getEmail(),
                "You have received " + rewardAmount + " bonus points in campaign " + campaign.name()
            );
        }

        return RewardDecision.approved(rewardAmount, campaign.name());
    }

    private BigDecimal calculateReward(BigDecimal purchaseAmount, Campaign campaign, Customer customer) {
        BigDecimal multiplier = BigDecimal.ONE.add(BigDecimal.valueOf(customer.getLoyaltyLevel() - 1L).movePointLeft(1));
        return purchaseAmount
            .multiply(campaign.percent())
            .multiply(multiplier)
            .setScale(2, RoundingMode.HALF_UP);
    }
}
