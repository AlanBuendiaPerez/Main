package pe.alan.ui.utils;

import pe.alan.ui.models.TestUser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;

public final class UserFactory {
    private UserFactory() {}

    public static TestUser newUser() {
        TestUser u = new TestUser();

        u.firstName = "Alan";
        u.lastName  = "Buendia";
        u.address   = "Av. Test 123";
        u.city      = "Lima";
        u.state     = "PE";
        u.zipCode   = "15001";
        u.phone     = "999" + rand(100000, 999999);
        u.ssn       = "" + rand(1000, 9999);
        u.username  = "abuendia" + rand(1000000, 9999999);
        u.password  = "abuendia" + rand(1000000, 9999999);
        return u;
    }

    private static int rand(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}
