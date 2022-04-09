# TestNG

## We have two method to test, in both method we use TestNG DataProvider ( @DataProvider(name = "name-data-provider") ) to pass the parameters in the test function, because we want to run the same test with multiple values (DDT)
### Login
![login](https://github.com/ninehnineh/Unit-Test/blob/41949d8fe4f7192c0479d8ac41c2bd2df87e1632/screenshots/Login.png)
#### We need two parameters to test above method: user and pass
#### DB
![DB](https://github.com/ninehnineh/Unit-Test/blob/482e228873af3e53eea0d4455b8735efd5d3b696/screenshots/db.png)
#### Test case:
* Test loginUser with valid value
![valid](https://github.com/ninehnineh/Unit-Test/blob/7fc90afb5b674042828bfd17055efcaac25e8fc6/screenshots/LoginValid.png)
#### We use assertNotNull() to check method checkLogin()
#### Excepted: checkLogin() return value that not null, that mean user is existed
#### Actual: as we can see in DB two user is existed
#### Result: We have green
![Green](https://github.com/ninehnineh/Unit-Test/blob/482e228873af3e53eea0d4455b8735efd5d3b696/screenshots/result-valid.png)
====================================================================================
* Test loginUser with invalid value
![invalid](https://github.com/ninehnineh/Unit-Test/blob/26699b0e3131a4c325da580eeb796a59a4bc4767/screenshots/Logininval.png)
#### We use assertNull() to check method checkLogin()
#### Excepted: checkLogin() return value that null, that mean user is not existed
#### Actual: in DB we can see two user is not existed
#### Result:
![Green1](https://github.com/ninehnineh/Unit-Test/blob/482e228873af3e53eea0d4455b8735efd5d3b696/screenshots/result-invalid.png)
## In conclusion, the purpose of checkLogin() is to authenticate who the user is allowed to access to the system, after 2 test cases we have already tested 7 values that may be a user can enter, thus the behavior of  checkLogin() method is matched with the expected
================================================================
### Insert
![Insert](https://github.com/ninehnineh/Unit-Test/blob/41949d8fe4f7192c0479d8ac41c2bd2df87e1632/screenshots/Insert.png)
####Test case:
* Test insertUser with valid value
![valid]()
* Test insertUser with invalid value
![Invalid]()
