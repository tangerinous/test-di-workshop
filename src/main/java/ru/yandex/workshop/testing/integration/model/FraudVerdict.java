package ru.yandex.workshop.testing.integration.model;

public record FraudVerdict(boolean blocked, String reason) {
}
