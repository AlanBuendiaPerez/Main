function fn() {
  var config = {};
  config.baseUrl = 'https://parabank.parasoft.com/parabank/services/bank';


  config.customerId = karate.properties['customerId'] || 13766;
  config.fromAccountId = karate.properties['fromAccountId'] || 16008;

  //0=CHECKING
  //1=SAVINGS
  //2=LOAN
  config.newAccountType = karate.properties['newAccountType'] || 1;

  karate.configure('connectTimeout', 10000);
  karate.configure('readTimeout', 10000);

  return config;
}
