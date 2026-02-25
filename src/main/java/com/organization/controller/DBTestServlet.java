package com.organization.controller;

import com.organization.util.DBUtil;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;

public class DBTestServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Connection con = DBUtil.getConnection();
        if (con != null) {
            resp.getWriter().println("Database connected successfully!");
        } else {
            resp.getWriter().println("Database connection FAILED");
        }
    }
}
