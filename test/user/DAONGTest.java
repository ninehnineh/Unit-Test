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
            case "testLoginWithValidArgument"://1
                data = new String[][]{
                    {"admin", "1"},
                    {"staff", "1"}};
                break; 
            case "testLoginWithInvalidArgument"://2
                data = new String[][]{
                    {"", ""},
                    {"sv111", ""},
                    {"", "1"},
                    {"blabla", "1"},
                    {"taolaotaolao", "1"}};
                break;
            case "testInsertUserWithValidValue"://3
                data = new Object[][]{
                    {"c12", "trinhcongtruong1", "1", "AD", true},
                    {"c22", "trinhcongtruong12", "1", "AD", true}};
                break;
            case "testInsertUserWithInvalidValue"://4
                data = new Object[][]{
                    {"Lorem Ipsum is simply dummy text of the printing and typesetting"
                        + " industry. Lorem Ipsum has been the industry's"
                        + " standard dummy text ever since the 1500s, when"
                        + " an unknown printer took a galley of type and scrambled"
                        + " it to make a type specimen book "
                        + "It has survived not only five centuries, but also the leap"
                        + " into electronic typesetting, remaining essentially unchanged",
                        "trinhcongtruong123", "1","AD", true},
                    {"admin", "trinhcongtruong123", "1", "AD", true}};
                break;
        }
        return data;
    }
    
    //1
    @Test(dataProvider = "data-provider")
    public void testLoginWithValidArgument(String userID, String password) throws SQLException, Exception {
        DAO dao = new DAO();
        DTO user = dao.checkLogin(userID, password);
        assertNotNull(user);
    }

    //2
    @Test(dataProvider = "data-provider")
    public void testLoginWithInvalidArgument(String userID, String password) throws SQLException, Exception {
        DAO dao = new DAO();
        DTO user = dao.checkLogin(userID, password);
        assertNull(user);
    }

    //3
    @Test(dataProvider = "data-provider")
    public void testInsertUserWithValidValue(String data, String data1, String data2, String data3, boolean boo) throws SQLException, Exception {
        DTO user = new DTO(data, data1, data2, data3, boo);
        DAO dao = new DAO();
        dao.Insert(user);
    }

    //4
    @Test(dataProvider = "data-provider")
    public void testInsertUserWithInvalidValue(String data, String data1, String data2, String data3, boolean boo) throws SQLException, Exception {
        DTO user = new DTO(data, data1, data2, data3, boo);
        DAO dao = new DAO();
        boolean expected = false;
        boolean check = dao.Insert(user);
        assertEquals(check, expected);
    }
    
    //5
//    @Test(dataProvider = "data-provider")
//    public void testGetUserInfoByUserIdWithValidValue(){
//        
//    }

//    @DataProvider(name = "data-provider")
//    public Object[][] dataProviderMethod() {
//        return new Object[][]{
//            {" ", "trinhcongtruong", "1", "AD", true},
//            {"Lorem Ipsum is simply dummy text of the printing and typesetting industry."
//                + " Lorem Ipsum has been the industry's standard dummy text ever since the 1500s"
//                + ",when an unknown printer took a galley of type and scrambled it to make a type",
//                "trinhcongtruong1", "1", "AD", true},
//            {"c2313", "trinhcongtruong1", "1", "AD", true}
//        };
//    }
//    
    // insert valid value
//   /
    //  insert null value
//    @Test(expectedExceptions = Exception.class)
//    public void testInsertUserWithNullUserId() throws SQLException, Exception {
//        DTO user = new DTO();
//        user.setUserID("");
//        user.setFullName("truongđwcoưqengchinh");
//        user.setPassword("1");
//        user.setRoleID("AD");
//        user.setStatus(true);
//        DAO dao = new DAO();
//        dao.Insert(user);
//    }
//    
//    //insert valid value
//    @Test(enabled = true)
//    public void testInsertUserWithValidValue() throws SQLException, Exception {
//        DTO user = new DTO();
//        user.setUserID("sv1212322221");
//        user.setFullName("truongđwcoưqengchinh");
//        user.setPassword("1");
//        user.setRoleID("AD");
//        user.setStatus(true);
//        DAO dao = new DAO();
//        dao.Insert(user);
//    }
//    
//    //insert duplicate value
//    @Test(expectedExceptions = Exception.class)
//    public void testInsertUserWithDuplicateValue() throws SQLException, Exception {
//        DTO user = new DTO();
//        user.setUserID("sv121");
//        user.setFullName("truongđwcoưqengchinh");
//        user.setPassword("1");
//        user.setRoleID("AD");
//        user.setStatus(true);
//        DAO dao = new DAO();
//        dao.Insert(user);
//    }
    //get user by user id
//    @Test(expectedExceptions = Exception.class)
//    public void testGetUserInfoByUserIdWithValidValue() throws SQLException, Exception {
//        DAO dao = new DAO();
//        dao.getUserInfor("sdwd");
//    }
    //get user not exist
    @Test(expectedExceptions = Exception.class)
    public void testGetUserInfoByUserIdWithInvalidValue() throws SQLException, Exception {
        DAO dao = new DAO();
        dao.getUserInfor("sdwd");
    }
    //Delete user by user id
//    @Test
//    public void testDeleteUserByUserIdWithInvalidValue() throws SQLException, Exception {
//        DAO dao = new DAO();
//        boolean check = dao.deleteUser("dwdqwd");
//        assertEquals(check, false);
//    }
}
