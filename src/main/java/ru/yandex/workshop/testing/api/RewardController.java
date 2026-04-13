package ru.yandex.workshop.testing.api;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.yandex.workshop.testing.api.dto.GenerateCertificateRequest;
import ru.yandex.workshop.testing.api.dto.IssueRewardRequest;
import ru.yandex.workshop.testing.api.dto.RewardResponse;
import ru.yandex.workshop.testing.reward.CertificateResult;
import ru.yandex.workshop.testing.reward.LegacyCertificateService;
import ru.yandex.workshop.testing.reward.RewardDecision;
import ru.yandex.workshop.testing.reward.RewardIssuingService;

@RestController
@RequestMapping("/api")
public class RewardController {

    private final RewardIssuingService rewardIssuingService;
    private final LegacyCertificateService legacyCertificateService;

    public RewardController(
        RewardIssuingService rewardIssuingService,
        LegacyCertificateService legacyCertificateService
    ) {
        this.rewardIssuingService = rewardIssuingService;
        this.legacyCertificateService = legacyCertificateService;
    }

    @PostMapping("/rewards/issue")
    public RewardResponse issueReward(@Valid @RequestBody IssueRewardRequest request) {
        RewardDecision decision = rewardIssuingService.issueReward(request.customerId(), request.purchaseAmount());
        return new RewardResponse(
            decision.status().name(),
            decision.rewardAmount(),
            decision.campaignName(),
            decision.message()
        );
    }

    @PostMapping("/certificates")
    public CertificateResult generateCertificate(@Valid @RequestBody GenerateCertificateRequest request) {
        return legacyCertificateService.generateCertificate(request.studentEmail(), request.solvedTasks());
    }
}
