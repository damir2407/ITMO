package com.example.weblabb4.util;

import com.example.weblabb4.entity.PointEntity;
import com.example.weblabb4.requests.PointRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class PointCreator {

    @Autowired
    private AreaChecker areaChecker;

    public PointEntity createPoint(PointRequest pointRequest, Long executeTimeStart) throws NumberFormatException{

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = dateFormat.format(new Date());

        Double x = Double.parseDouble(pointRequest.getX());
        Double y = Double.parseDouble(pointRequest.getY());
        Double r = Double.parseDouble(pointRequest.getR());
        Boolean hitValue = (areaChecker.checkHit(x, y, r));

        long executeTimeFinish = System.nanoTime();
        String executeTime = String.format("%.7f", (executeTimeFinish - executeTimeStart) / Math.pow(10, 9));

        return new PointEntity(x, y, r, currentTime, executeTime, hitValue);

    }
}
