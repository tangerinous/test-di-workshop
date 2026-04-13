package ru.yandex.workshop.testing.integration;

import java.math.BigDecimal;

import ru.yandex.workshop.testing.integration.model.FraudVerdict;

public interface FraudCheckClient {

    FraudVerdict check(Long customerId, BigDecimal purchaseAmount);
}
