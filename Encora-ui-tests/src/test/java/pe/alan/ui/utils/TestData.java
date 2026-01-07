package pe.alan.ui.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class TestData {
    private TestData() {}

    public static String uniqueUsername() {
        // ejemplo: qa_20260107_103512_123
        String ts = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SSS"));
        return "qa_" + ts;
    }

    public static String password() {
        return "P@ssw0rd123!";
    }
}
