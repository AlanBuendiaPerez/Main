Feature: Crear cuenta de ahorros

  @crearcuenta
  Scenario Outline: crear cuenta savings
    Given url 'https://parabank.parasoft.com/parabank/services/bank/createAccount'
    And param customerId = <customerId>
    And param newAccountType = <type>
    And param fromAccountId = <fromAccountId>
    And header Accept = 'application/xml'
    When method post
    Then status 200

    * def accountId = response.account.id
    * def respCustomerId = response.account.customerId
    * def respType = response.account.type
    * def respBalance = response.account.balance

    And match accountId == '#string'
    And match respCustomerId == '#string'
    And match respType == 'SAVINGS'
    And match respBalance == '#string'

    * assert parseInt(accountId) > 0
    * assert parseInt(respCustomerId) == <customerId>
    * assert parseFloat(respBalance) >= 0

    Examples:
      | customerId | type | fromAccountId |
      | 12545      | 1    | 13677         |
