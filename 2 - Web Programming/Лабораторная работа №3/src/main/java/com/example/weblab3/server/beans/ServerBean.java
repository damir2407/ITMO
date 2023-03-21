package com.example.weblab3.server.beans;


import com.example.weblab3.server.CheckCoordinates;
import com.example.weblab3.server.Resulted;
import com.example.weblab3.server.db.ResultService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@ManagedBean(name = "serverBean")
@SessionScoped
public class ServerBean implements Serializable {
    public String strY;

    public ServerBean() {
    }

    private Resulted result = new Resulted();
    private CheckCoordinates checkCoordinates = new CheckCoordinates();
    private ResultService resultService = new ResultService();

    public void changeX(Double x) {
        if (checkCoordinates.checkXCoordinate(x)) getResult().setX(x);
        else return;
    }

    public void changeR(Double r) {
        if (checkCoordinates.checkRCoordinate(r)) getResult().setR(r);
        else return;
    }

    public String getStrY() {
        return strY;
    }

    public void setStrY(String strY) {
        this.strY = strY;
    }

    public void submitFunction() {
        long executeTimeStart = System.nanoTime();
        System.out.println(strY);
        if (checkCoordinates.checkYCoordinate(strY) &&
                checkCoordinates.checkXCoordinate(getResult().getX()) &&
                checkCoordinates.checkRCoordinate(getResult().getR())) {
            Double yValue = Double.parseDouble(strY);
            getResult().setY(yValue);

            setResultValues(executeTimeStart);
            resultService.saveResult(result);
        } else return;
    }


    public void submitCanvasFunction() {
        long executeTimeStart = System.nanoTime();
        setResultValues(executeTimeStart);
        resultService.saveResult(result);
    }


    public void resetFunction() {
        this.result = new Resulted();
        resultService.deleteAllResult();
    }

    public List<Resulted> getAllResults() {
        return resultService.getAllResult();
    }

    public void setResultValues(long executeTimeStart) {
        long executeTimeFinish = System.nanoTime();
        String executeTime = String.format("%.7f", (executeTimeFinish - executeTimeStart) / Math.pow(10, 9));

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = dateFormat.format(new Date());

        boolean hitValue = (checkCoordinates.checkHit(getResult()));

        getResult().setCurrentTime(currentTime);
        getResult().setExecuteTime(executeTime);
        getResult().setHitValue(hitValue);

    }

    public Resulted getResult() {
        return result;
    }

}
