package com.organization.controller;

import com.organization.util.DBUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet("/raiseComplaint")
public class RaiseComplaintServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        HttpSession session = request.getSession(false);
        String username = (String) session.getAttribute("username");

        String subject = request.getParameter("subject");
        String description = request.getParameter("description");

        try {
            Connection con = DBUtil.getConnection();
            
            if (con == null) {
                response.sendRedirect("raiseComplaint.jsp?error=Database connection failed");
                return;
            }
            
            String sql = "INSERT INTO complaints (employee_id, title, description, status) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, username);
            ps.setString(2, subject);
            ps.setString(3, description);

            ps.executeUpdate();
            ps.close();
            con.close();

            response.sendRedirect("raiseComplaint.jsp?success=Complaint submitted successfully");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("raiseComplaint.jsp?error=" + e.getMessage());
        }
    }
}
