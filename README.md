# TestNG

## We have two method to test, in both method we use TestNG DataProvider ( @DataProvider(name = "name-data-provider") ) to pass the parameters in the test function, because we want to run the same test with multiple values (DDT)
## Login
![login](https://github.com/ninehnineh/Unit-Test/blob/41949d8fe4f7192c0479d8ac41c2bd2df87e1632/screenshots/Login.png)
#### We need two parameters to test above method: user and pass
#### DB
![DB](https://github.com/ninehnineh/Unit-Test/blob/482e228873af3e53eea0d4455b8735efd5d3b696/screenshots/db.png)
#### Test case:
* Test loginUser with valid value
![valid](https://github.com/ninehnineh/Unit-Test/blob/7fc90afb5b674042828bfd17055efcaac25e8fc6/screenshots/LoginValid.png)
#### We use assertNotNull() to check method checkLogin()
#### Expected: checkLogin() return value that not null, that mean user is existed
#### Actual: as we can see in DB two user is existed
#### Result: We have green
![Green](https://github.com/ninehnineh/Unit-Test/blob/482e228873af3e53eea0d4455b8735efd5d3b696/screenshots/result-valid.png)
====================================================================================
* Test loginUser with invalid value
![invalid](https://github.com/ninehnineh/Unit-Test/blob/26699b0e3131a4c325da580eeb796a59a4bc4767/screenshots/Logininval.png)
#### We use assertNull() to check method checkLogin()
#### Expected: checkLogin() return value that null, that mean user is not existed
#### Actual: in DB we can see two user is not existed
#### Result:
![Green1](https://github.com/ninehnineh/Unit-Test/blob/482e228873af3e53eea0d4455b8735efd5d3b696/screenshots/result-invalid.png)
## In conclusion, the purpose of checkLogin() is to authenticate who the user is allowed to access to the system, after 2 test cases we have already tested 7 values that may be a user can enter, thus the behavior of  checkLogin() method is matched with the expected
================================================================
## Insert
![Insert](https://github.com/ninehnineh/Unit-Test/blob/67702edbb27dbe1829752a0efeaa2226dcf1f663/screenshots/Insert.png)
#### We need 5 parameters to test above method: userId, fullName, password, roleId, status
#### In this method, we use DataProviders with method as a Parameter
#### Import Required: import java.lang.reflect.Method
![switch](https://github.com/ninehnineh/Unit-Test/blob/83001eae4d8413a722da204e761625d325c41015/screenshots/ifel.png) 
#### Test case:
* Test insertUser with valid value:
![valid](https://github.com/ninehnineh/Unit-Test/blob/67702edbb27dbe1829752a0efeaa2226dcf1f663/screenshots/Insert-valid.png)
#### We use assertEquals() to check method Insert()
#### Expected: Insert() return true
#### Actual: true
#### Result:
![Rresult-val](https://github.com/ninehnineh/Unit-Test/blob/c4d2ade2a07e1b8f64dbb3ec3f89ea0e49bf98c1/screenshots/result-insert-valid.png)
* Test insertUser with invalid value:
#### In this test:
* Insert very long userId that break data type nvarchar(50) in DB
* Insert existed user into DB 
![Invalid](https://github.com/ninehnineh/Unit-Test/blob/67702edbb27dbe1829752a0efeaa2226dcf1f663/screenshots/insert-invalid.png)
#### We use assertEquals() to check method Insert()
#### Expected: Insert() return false, because userId is too long and userId is existed
#### Actual: false, cant insert into DB
#### Result:
![resutl-inval](https://github.com/ninehnineh/Unit-Test/blob/c4d2ade2a07e1b8f64dbb3ec3f89ea0e49bf98c1/screenshots/result-insert-inval.png)
## In conclusion, Insert () aims to insert a new user into the system. In this test, we already checked 4 values, 2 valid, 2 invalids, and see the behavior of Insert() method is matched with the expected

# In comparison after testing two methods:
* checkLogin() method we use two Dataprovider for two tests (1)
* Insert() method we use just one Dataprovider for all tests (2) and if any method is added, we just need to add two lines:
	- case "method" :
	- return statement.
# => to optimize we recommend use approach (2) instead of (1).