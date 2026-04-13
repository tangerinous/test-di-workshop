package ru.yandex.workshop.testing.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "workshop")
public record WorkshopStorageProperties(String certificatesDirectory) {
}
