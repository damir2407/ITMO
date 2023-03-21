package com.example.webblab2.servlets;

import com.example.webblab2.savers.Result;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class AreaCheckServlet extends HttpServlet {
    private double x;
    private double y;
    private double r;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long executeTimeStart = System.nanoTime();
        if (validateVars(req)) {
            Result result = setResultObject(executeTimeStart);
            List<Result> resultList = (ArrayList<Result>) getServletContext().getAttribute("resultList");
            if (resultList == null) resultList = new ArrayList<Result>();
            resultList.add(result);
            req.getServletContext().setAttribute("resultList", resultList);
            if (req.getParameter("canvasReady") == null) {
                req.getRequestDispatcher("/area_index.jsp").forward(req, resp);
            } else {
                resp.setContentType("application/json; charset=UTF-8");
                Gson gson = new Gson();
                String jsonObject = gson.toJson(result);
                resp.getWriter().write(jsonObject);
            }
        } else req.getRequestDispatcher("/area_index.jsp").forward(req, resp);


    }


    private boolean validateVars(HttpServletRequest req) {
        try {
            Double[] xValues = {-3.0, -2.0, -1.0, 0.0, 1.0, 2.0, 3.0, 4.0, 5.0};
            Double[] rValues = {1.0, 2.0, 3.0, 4.0, 5.0};
            double x = Double.parseDouble(req.getParameter("id_x"));
            double y = Double.parseDouble(req.getParameter("id_y"));
            double r = Double.parseDouble(req.getParameter("id_r"));
            if ((Arrays.asList(xValues).contains(x)) && (y > -5 && y < 3) && (Arrays.asList(rValues).contains(r))) {
                setX(x);
                setY(y);
                setR(r);
                return true;
            }
            return false;
        } catch (NumberFormatException exception) {
            return false;
        }
    }

    private Result setResultObject(long executeTimeStart) {
        long executeTimeFinish = System.nanoTime();
        String executeTime = String.format("%.6f", (executeTimeFinish - executeTimeStart) / Math.pow(10, 9));

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = dateFormat.format(new Date());

        boolean hitValue = (checkCircle() || checkTriangle() || checkSquare());
        return new Result(this.x, this.y, this.r, currentTime, executeTime, hitValue);
    }

    private boolean checkSquare() {
        return x >= 0 && x <= (r / 2) && y <= 0 && y >= -r;
    }

    private boolean checkTriangle() {
        return (((-x * -r) >= 0 && (-r * -y) >= 0 && ((r - x) * r - (-r) * (-y)) >= 0) || ((-x * -r) <= 0 && (-r * -y) <= 0 && ((r - x) * r - (-r) * (-y)) <= 0));
    }

    private boolean checkCircle() {
        return x <= 0 && y >= 0 && Math.sqrt(x * x + y * y) <= r / 2;
    }


    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setR(double r) {
        this.r = r;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getR() {
        return this.r;
    }


}
