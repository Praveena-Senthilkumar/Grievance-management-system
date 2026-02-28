package com.organization.controller;

import com.organization.util.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/viewComplaints")
public class ViewComplaintsServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<String[]> complaintsList = new ArrayList<>();

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM complaints");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String[] complaint = new String[4];
                complaint[0] = rs.getString("id");
                complaint[1] = rs.getString("employee_id");
                complaint[2] = rs.getString("description");
                complaint[3] = rs.getString("status");

                complaintsList.add(complaint);
            }

            request.setAttribute("complaints", complaintsList);
            request.getRequestDispatcher("viewComplaints.jsp")
                   .forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error loading complaints.");
        }
    }
}