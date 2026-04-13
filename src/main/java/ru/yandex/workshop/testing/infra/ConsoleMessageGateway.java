package ru.yandex.workshop.testing.infra;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import ru.yandex.workshop.testing.integration.MessageGateway;

@Component
public class ConsoleMessageGateway implements MessageGateway {

    private static final Logger log = LoggerFactory.getLogger(ConsoleMessageGateway.class);

    @Override
    public void sendRewardIssuedMessage(String email, String text) {
        log.info("Sending message to {}: {}", email, text);
    }
}
