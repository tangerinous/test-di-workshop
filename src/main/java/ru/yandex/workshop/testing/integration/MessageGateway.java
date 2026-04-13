package ru.yandex.workshop.testing.integration;

public interface MessageGateway {

    void sendRewardIssuedMessage(String email, String text);
}
