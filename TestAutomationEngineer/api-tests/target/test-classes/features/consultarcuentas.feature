Feature: Obtener cuenta del cliente

  @consultarcuenta
  Scenario Outline: Validar cuenta por accountId
    Given url 'https://parabank.parasoft.com/parabank/services/bank/accounts/' + <accountId>
    And header Accept = 'application/xml'
    When method get
    Then status 200

    * def respAccountId = response.account.id
    * def respType = response.account.type
    * def respBalance = response.account.balance

    * match respAccountId == '#string'
    * match parseInt(respAccountId) == <accountId>

    * match respType == '<expectedType>'

    * match respBalance == '#string'
    * assert parseFloat(respBalance) >= 0

    Examples:
      | accountId | expectedType |
      | 13899     | CHECKING     |
