package pe.alan.ui.config;

public final class TestConfig {
    private TestConfig() {}

    public static final String BASE_URL = "https://parabank.parasoft.com/parabank";
    public static final String HOME_URL = BASE_URL + "/index.htm";
    public static final String REGISTER_URL = BASE_URL + "/register.htm";

    public static final int TIMEOUT_SEC = 10;
}
