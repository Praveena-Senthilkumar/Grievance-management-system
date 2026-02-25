package com.organization.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class TestServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.getWriter().println("Backend is working");
    }
}
