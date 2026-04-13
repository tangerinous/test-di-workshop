package ru.yandex.workshop.testing.reward;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.UUID;

import org.springframework.stereotype.Service;

import ru.yandex.workshop.testing.config.WorkshopStorageProperties;

@Service
public class LegacyCertificateService {

    private final WorkshopStorageProperties properties;

    public LegacyCertificateService(WorkshopStorageProperties properties) {
        this.properties = properties;
    }

    public CertificateResult generateCertificate(String studentEmail, int solvedTasks) {
        if (solvedTasks < 3) {
            return CertificateResult.skipped("Not enough solved tasks");
        }

        String code = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        LocalDate issuedAt = LocalDate.now();
        Path baseDirectory = Path.of(properties.certificatesDirectory());

        try {
            Files.createDirectories(baseDirectory);
            String sanitizedEmail = studentEmail.replace("@", "_at_");
            Path certificatePath = baseDirectory.resolve(sanitizedEmail + "-" + code + ".txt");

            String content = """
                Workshop certificate
                Student: %s
                Solved tasks: %d
                Issue date: %s
                Certificate code: %s
                """.formatted(studentEmail, solvedTasks, issuedAt, code);

            Files.writeString(
                certificatePath,
                content,
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING
            );
            return CertificateResult.generated(certificatePath.toString(), code);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to create certificate", e);
        }
    }
}
