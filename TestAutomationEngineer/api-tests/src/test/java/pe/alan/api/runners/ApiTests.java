package pe.alan.api.runners;

import com.intuit.karate.junit5.Karate;

class ApiTests {

    @Karate.Test
    Karate testAll() {
        return Karate.run("classpath:features");
    }
}
