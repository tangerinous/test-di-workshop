package ru.yandex.workshop.testing.infra;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

import ru.yandex.workshop.testing.integration.CampaignGateway;
import ru.yandex.workshop.testing.integration.model.Campaign;

@Component
public class FakeCampaignGateway implements CampaignGateway {

    private static final Map<String, Campaign> CAMPAIGNS = Map.of(
        "NEW", new Campaign("Welcome bonus", new BigDecimal("0.05")),
        "REGULAR", new Campaign("Spring cashback", new BigDecimal("0.10")),
        "VIP", new Campaign("VIP booster", new BigDecimal("0.15"))
    );

    @Override
    public Optional<Campaign> findCampaignForSegment(String segment) {
        return Optional.ofNullable(CAMPAIGNS.get(segment));
    }
}
