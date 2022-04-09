/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import utils.DBUtils;

/**
 *
 * @author admin
 */
public class DAO {

    private static final String LOGIN = " SELECT fullName, roleID, status FROM tblUsers WHERE userID=? AND password=? AND status=?";
    private static final String SEARCH = " SELECT userID, fullName, roleID, status FROM tblUsers WHERE fullName like ?";
    private static final String DELETE = " UPDATE tblUsers SET status=? WHERE userID=? ";
    private static final String UPDATE = " UPDATE tblUsers SET fullName=?, roleID=? WHERE userID=? ";
    private static final String USER_INFO = " SELECT fullName, roleID, status FROM tblUsers WHERE userID = ? ";
    private static final String CHECK_DUPLICATE = " SELECT userID FROM tblUsers WHERE userID=? ";
    private static final String INSERT = " INSERT INTO tblUsers( userID, fullName, roleID, password, status) VALUES (?,?,?,?,?) ";

    public DTO checkLogin(String userID, String pass) throws SQLException, Exception {
        DTO user = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(LOGIN);
                stm.setString(1, userID);
                stm.setString(2, pass);
                stm.setBoolean(3, true);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String fullName = rs.getString("fullName");
                    String roleID = rs.getString("roleID");
                    boolean status = rs.getBoolean("status");
                    user = new DTO(userID, fullName, roleID, "", status);
                } 
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return user;
    }

    public List<DTO> getListUser(String search) throws SQLException, Exception {
        List<DTO> List = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(SEARCH);
                stm.setString(1, "%" + search + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    String userID = rs.getString("userID");
                    String fullName = rs.getString("fullName");
                    String roleID = rs.getString("roleID");
                    String password = "***";
                    boolean status = rs.getBoolean("status");
                    List.add(new DTO(userID, fullName, roleID, password, status));
                }
            }
        } catch (Exception e) {
            throw new Exception();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return List;
    }

    public boolean deleteUser(String userID) throws SQLException, Exception {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(DELETE);
                stm.setBoolean(1, false);
                stm.setString(2, userID);
                check = stm.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
            throw new Exception();
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean update(DTO user) throws SQLException, Exception {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(UPDATE);
                stm.setString(1, user.getFullName());
                stm.setString(2, user.getRoleID());
                stm.setString(3, user.getUserID());
                check = stm.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
            throw new Exception();
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public DTO getUserInfor(String userID) throws SQLException, Exception {
        DTO user = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(USER_INFO);
                stm.setString(1, userID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String fullName = rs.getString("fullName");
                    String roleID = rs.getString("roleID");
                    boolean status = rs.getBoolean("status");
                    user = new DTO(userID, fullName, roleID, "", status);
                } 
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return user;
    }

    public boolean checkDuplicate(String userID) throws SQLException, Exception {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(CHECK_DUPLICATE);
                stm.setString(1, userID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    check = true;
                }
            }
        } catch (Exception e) {
            throw new Exception();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean Insert(DTO user) throws SQLException, Exception {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(INSERT);
                stm.setString(1, user.getUserID().trim());
                stm.setString(2, user.getFullName());
                stm.setString(3, user.getRoleID());
                stm.setString(4, user.getPassword());
                stm.setBoolean(5, user.isStatus());
                check = stm.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    //ko co catch exception
//    public boolean Insert2(DTO user) throws SQLException, ClassNotFoundException, NamingException {
//        boolean check = false;
//        Connection conn = null;
//        PreparedStatement stm = null;
//        try {
//            conn = DBUtils.getConnection();
//            if (conn != null) {
//                stm = conn.prepareStatement(INSERT);
//                stm.setString(1, user.getUserID());
//                stm.setString(2, user.getFullName());
//                stm.setString(3, user.getRoleID());
//                stm.setString(4, user.getPassword());
//                stm.setBoolean(5, user.isStatus());
//                check = stm.executeUpdate()>0 ? true:false;
//            }
//        }  finally {
//            if (stm != null) {
//                stm.close();
//            }
//            if (conn != null) {
//                conn.close();
//            }
//        }
//        return check;
//    }
}
