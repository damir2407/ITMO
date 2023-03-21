package com.example.weblab3.server;

public class CheckCoordinates {


    public boolean checkXCoordinate(Double x) {
        return x != null;
    }

    public boolean checkYCoordinate(String yStringValue) {
        try {
            Double yValue = Double.parseDouble(yStringValue);
            return yValue > -3 && yValue < 3;
        } catch (NumberFormatException exception) {
            return false;
        }
    }

    public boolean checkRCoordinate(Double r) {
        return r != null;
    }



    public boolean checkCircle(Resulted result) {
        return result.getX() <= 0 && result.getY() >= 0 && Math.sqrt(result.getX()
                * result.getX() + result.getY() * result.getY()) <= result.getR() / 2;
    }

    public boolean checkSquare(Resulted result) {
        return result.getX() >= 0 && result.getX() <= (result.getR())
                && result.getY() <= 0 && result.getY() >= -result.getR();
    }

    public boolean checkTriangle(Resulted result) {
        return ((((-result.getX()) * (-result.getR())) >= 0 && ((-result.getX()) * (result.getR()) - (-result.getR())
                * (-result.getR() - result.getY())) >= 0 && (-(result.getR()) * (-result.getY())) >= 0)
                || (((-result.getX()) * (-result.getR())) <= 0 && ((-result.getX()) * (result.getR()) - (-result.getR())
                * (-result.getR() - result.getY())) <= 0 && (-(result.getR()) * (-result.getY())) <= 0));
    }

    public boolean checkHit(Resulted result) {
        return checkCircle(result) || checkSquare(result) || checkTriangle(result);
    }

}
