Feature: Validar transacción por ID

  @transaccion
  Scenario Outline: validar transacción (existencia, monto, tipo, timestamp)
    Given url 'https://parabank.parasoft.com/parabank/services/bank/transactions/<transactionId>'
    And header Accept = 'application/xml'
    When method get
    Then status 200

    * def trxId = response.transaction.id
    * def type = response.transaction.type
    * def date = response.transaction.date
    * def amountStr = response.transaction.amount
    * def amount = parseFloat(amountStr)

    And match trxId == '#string'
    And match parseInt(trxId) == <transactionId>

    And match type == '<type>'

    And match amountStr == '#string'
    And match amount == <amount>
    * assert amount > 0

    And match date == '#string'
    And match date contains 'T'

    Examples:
      | transactionId | type   | amount  |
      | 14587         | Credit | 100.00  |