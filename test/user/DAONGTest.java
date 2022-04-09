/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import java.lang.reflect.Method;
import java.sql.SQLException;
import static org.testng.Assert.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 *
 * @author admin
 */
public class DAONGTest {

    @DataProvider(name = "data-provider")
    public Object[][] dataProviderMethod(Method method) {
        Object[][] data = null;
        switch (method.getName()) {
            case "testInsertUserWithValidValue":
                data = new Object[][]{
                    {"n13", "trinhcongtruong1", "1", "AD", true},
                    {"n23", "trinhcongtruong12", "1", "AD", true},};
                break;
            case "testInsertUserWithInvalidValue":
                data = new Object[][]{
                    {"Lorem Ipsum is simply dummy text of the printing and typesetting"
                        + " industry. Lorem Ipsum has been the industry's"
                        + " standard dummy text ever since the 1500s, when"
                        + " an unknown printer took a galley of type and scrambled"
                        + " it to make a type specimen book "
                        + "It has survived not only five centuries, but also the leap"
                        + " into electronic typesetting, remaining essentially unchanged",
                        "trinhcongtruong123", "1", "AD", true},
                    {"admin", "trinhcongtruong123", "1", "AD", true}};
                break;
        }
        return data;
    }

    @Test(dataProvider = "data-provider")
    public void testInsertUserWithValidValue(String userId, String fullName, String password, String roleId, boolean status) throws SQLException, Exception {
        DAO dao = new DAO();
        DTO user = new DTO(userId, fullName, password, roleId, status);
        boolean expected = true;
        boolean actual = dao.Insert(user);
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "data-provider", enabled = false)
    public void testInsertUserWithInvalidValue(String userId, String fullName, String password, String roleId, boolean status) throws SQLException, Exception {
        DAO dao = new DAO();
        DTO user = new DTO(userId, fullName, password, roleId, status);
        boolean expected = false;
        boolean actual = dao.Insert(user);
        assertEquals(actual, expected);
    }
    
    @DataProvider(name = "data-provider-valid")
    public Object[][] dataProviderMethod() {
        return new Object[][]{
            {"admin", "1"},
            {"staff", "1"}
        };
    }

    @Test(dataProvider = "data-provider-valid", enabled = false)
    public void testLoginWithValidArgumentTest(String userid, String pass) throws SQLException, Exception {
        DAO dao = new DAO();
        DTO user = dao.checkLogin(userid, pass);
        assertNotNull(user);
    }

    @DataProvider(name = "data-provider-invalid")
    public Object[][] dataProviderMethod1() {
        return new Object[][]{
            {"", ""},
            {"sv111", ""},
            {"", "1"},
            {"blabla", "1"},
            {"taolaotaolao", "1"}
        };
    }

    @Test(dataProvider = "data-provider-invalid", enabled = false )
    public void testLoginWithInValidArgumentTest(String userid, String pass) throws SQLException, Exception {
        DAO dao = new DAO();
        DTO user = dao.checkLogin(userid, pass);
        assertNull(user);
    }
}
