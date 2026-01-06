# Test Automation Engineer

Proyecto de automatización de pruebas **UI (Selenium + TestNG)** y **API (Karate)** usando Maven multi-módulo.

> Todos los comandos deben ejecutarse desde la **raíz del proyecto**.

---

## ▶ Ejecutar pruebas UI (Selenium + TestNG)

Ejecuta el módulo `ui-tests`:


mvn -pl ui-tests -am test


Este flujo:
- Registra un usuario nuevo en Parabank
- Cierra sesión
- Inicia sesión con el usuario generado
- Valida que el login sea exitoso

---

## ▶ Ejecutar pruebas API (Karate)

Las pruebas API se ejecutan desde el módulo `api-tests`.
No es necesario modificar el runner, solo indicar el feature a ejecutar.

### Crear cuenta

mvn -pl api-tests -am test -Dkarate.options="classpath:features/crearcuenta.feature"


### Consultar cuentas

mvn -pl api-tests -am test -Dkarate.options="classpath:features/consultarcuentas.feature"


### Transferencia de fondos


mvn -pl api-tests -am test -Dkarate.options="classpath:features/transferencia.feature"



### Validar transacción


mvn -pl api-tests -am test -Dkarate.options="classpath:features/validartransaccion.feature"


