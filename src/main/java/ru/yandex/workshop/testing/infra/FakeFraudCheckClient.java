package ru.yandex.workshop.testing.infra;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import ru.yandex.workshop.testing.integration.FraudCheckClient;
import ru.yandex.workshop.testing.integration.model.FraudVerdict;

@Component
public class FakeFraudCheckClient implements FraudCheckClient {

    @Override
    public FraudVerdict check(Long customerId, BigDecimal purchaseAmount) {
        boolean blocked = purchaseAmount.compareTo(new BigDecimal("20000")) > 0;
        String reason = blocked ? "Amount is above workshop threshold" : "OK";
        return new FraudVerdict(blocked, reason);
    }
}
