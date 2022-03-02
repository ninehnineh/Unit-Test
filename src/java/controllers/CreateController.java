/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import user.DAO;
import user.DTO;
import user.UserError;

/**
 *
 * @author admin
 */
@WebServlet(name = "CreateController", urlPatterns = {"/CreateController"})
public class CreateController extends HttpServlet {

    private static final String ERROR = "create.jsp";
    private static final String SUCCESS = "login.html";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        UserError userError = new UserError();

        try {
            String userID = request.getParameter("userID");
            String fullName = request.getParameter("fullName");
            String roleID = request.getParameter("roleID");
            String password = request.getParameter("password");
            String confirm = request.getParameter("confirm");

            DAO dao = new DAO();
            boolean checkValidation = true;
            if (userID.length() < 3 || userID.length() > 10) {
                checkValidation = false;
                userError.setUserIDError("UserID must be in[3,10]!");
            }
           boolean checkDuplicate = dao.checkDuplicate(userID);
//            if (checkDuplicate) {
//                checkValidation = false;
//                userError.setUserIDError("Duplicate userID!");
//            }
            if (fullName.length() < 5 || fullName.length() > 20) {
                checkValidation = false;
                userError.setFullNameError("fullName must be in[5,20]!");
            }
            if (!password.equals(confirm)) {
                checkValidation = false;
                userError.setConfirmPasswordError("Confirm password is not match!");
            }
            if (checkValidation) {
                DTO user = new DTO(userID, fullName, roleID, password, true);
//                boolean checkInsert = dao.Insert(user);
                boolean checkInsert = dao.Insert(user);
                if (checkInsert) {
                    url = SUCCESS;
                }
            } else {
                request.setAttribute("USER_ERROR", userError);
            }
        } catch (Exception e) {
            log("error at createcontroller" + e.toString());
            if (e.toString().contains("duplicate")) {
                userError.setUserIDError("Duplicate!!");
                request.setAttribute("USER_ERROR", userError);
            }
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
