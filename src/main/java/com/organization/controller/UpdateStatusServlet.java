package com.organization.controller;

import com.organization.util.DBUtil;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/updateStatus")
public class UpdateStatusServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
                          throws ServletException, IOException {

        int complaintId = Integer.parseInt(request.getParameter("id"));
        String status = request.getParameter("status");

        try (Connection conn = DBUtil.getConnection()) {

            String sql = "UPDATE complaints SET status=? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, status);
            ps.setInt(2, complaintId);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("viewComplaintsAdmin");
    }
}