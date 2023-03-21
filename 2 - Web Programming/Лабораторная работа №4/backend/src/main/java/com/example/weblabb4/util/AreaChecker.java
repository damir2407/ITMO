package com.example.weblabb4.util;

import org.springframework.stereotype.Component;

@Component
public class AreaChecker {


    public Boolean checkCircle(Double x, Double y, Double r) {
        return x >= 0 && y >= 0 && Math.sqrt(x
                * x + y * y) <= r;
    }

    public Boolean checkSquare(Double x, Double y, Double r) {
        return x <= 0 && x >= -r
                && y <= 0 && y >= -r;
    }

    public Boolean checkTriangle(Double x, Double y, Double r) {
        return (((-(r / 2) * (-y)) >= 0 &&
                ((r / 2 - x) * (-r) - (-r / 2) * (-y)) >= 0 &&
                ((-x) * (r)) >= 0) ||
                ((-(r / 2) * (-y)) <= 0 &&
                        ((r / 2 - x) * (-r) - (-r / 2) * (-y)) <= 0 &&
                        ((-x) * (r)) <= 0));
    }

    public Boolean checkHit(Double x, Double y, Double r) {
        return checkCircle(x, y, r) || checkSquare(x, y, r) || checkTriangle(x, y, r);
    }
}
