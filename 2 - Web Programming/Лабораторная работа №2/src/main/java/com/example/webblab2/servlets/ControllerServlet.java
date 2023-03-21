package com.example.webblab2.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ControllerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if ((req.getParameter("id_x") == null) || (req.getParameter("id_y") == null) || (req.getParameter("id_r") == null)) {
            getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
        }
        getServletContext().getRequestDispatcher("/areaCheck").forward(req, resp);

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
