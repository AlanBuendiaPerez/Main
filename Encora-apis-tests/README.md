
---

## `apis-tests/README.md`

# API Tests (Parabank) - Karate + JUnit5

## Requisitos
- Java 17+
- Maven 3.9+

## Ejecutar tests 


mvn test -Dkarate.options=classpath:features/crearcuenta.feature
mvn test -Dkarate.options=classpath:features/consultarcuenta.feature
mvn test -Dkarate.options=classpath:features/transferencia.feature
mvn test -Dkarate.options=classpath:features/validartransaccion.feature

