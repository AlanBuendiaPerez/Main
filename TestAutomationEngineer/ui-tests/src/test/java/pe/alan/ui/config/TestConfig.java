package pe.alan.ui.config;

import java.time.Duration;

public final class TestConfig {
    private TestConfig(){}

    public static final Duration TIMEOUT = Duration.ofSeconds(
            Integer.parseInt(System.getProperty("timeout", "10"))
    );

    public static String baseUrl() {
        return System.getProperty("baseUrl", "https://parabank.parasoft.com/parabank");
    }

    public static boolean isHeadless() {
        return Boolean.parseBoolean(System.getProperty("headless", "false"));
    }
}
