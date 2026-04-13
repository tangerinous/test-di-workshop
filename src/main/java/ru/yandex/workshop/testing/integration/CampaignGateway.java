package ru.yandex.workshop.testing.integration;

import java.util.Optional;

import ru.yandex.workshop.testing.integration.model.Campaign;

public interface CampaignGateway {

    Optional<Campaign> findCampaignForSegment(String segment);
}
