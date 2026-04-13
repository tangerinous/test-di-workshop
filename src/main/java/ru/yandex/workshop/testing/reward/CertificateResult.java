package ru.yandex.workshop.testing.reward;

public record CertificateResult(boolean generated, String filePath, String code, String message) {

    public static CertificateResult skipped(String message) {
        return new CertificateResult(false, null, null, message);
    }

    public static CertificateResult generated(String filePath, String code) {
        return new CertificateResult(true, filePath, code, "Certificate created");
    }
}
