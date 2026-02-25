package com.organization.controller;

import com.organization.util.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            Connection con = DBUtil.getConnection();
            
            if (con == null) {
                request.setAttribute("error", "Database connection failed. Please contact administrator.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
                return;
            }

            String sql = "SELECT role FROM users WHERE username=? AND password=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String role = rs.getString("role");

                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                session.setAttribute("role", role);

                if ("ADMIN".equalsIgnoreCase(role)) {
                    response.sendRedirect("adminDashboard.jsp");
                } else {
                    response.sendRedirect("raiseComplaint.jsp");
                }

            } else {
                request.setAttribute("error", "Invalid username or password");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
            
            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error during login: " + e.getMessage());
            try {
                request.getRequestDispatcher("login.jsp").forward(request, response);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
