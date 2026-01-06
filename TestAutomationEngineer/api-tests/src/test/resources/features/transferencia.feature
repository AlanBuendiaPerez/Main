Feature: Transferir fondos

  @transferencia
  Scenario Outline: Transferir y validar mensaje
    Given url baseUrl + '/transfer'
    And param fromAccountId = <fromAccountId>
    And param toAccountId = <toAccountId>
    And param amount = <amount>
    When method post
    Then status 200

    * def msg = response
    And match msg contains 'Successfully transferred'
    And match msg contains <fromAccountIdStr>
    And match msg contains <toAccountIdStr>

    Examples:
      | fromAccountId | toAccountId | amount | fromAccountIdStr | toAccountIdStr |
      | 13788         | 13899       | 30.00  | '#13788'         | '#13899'       |
