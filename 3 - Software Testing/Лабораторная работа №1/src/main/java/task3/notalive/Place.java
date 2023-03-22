package main.java.task3.notalive;

import main.java.task3.exception.DescException;


public abstract class Place implements Craft {

    private String name;
    private String description;

    public Place(String name) {
        this.name = name;
    }

    public Place(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public static class Time {

        static String time = "";

        public Time(String time) {
            this.time = time;
        }


        public static void setTime(String clock) {
            time = clock;
        }

        public static String getTime() {
            return time;
        }
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public String getInfo() throws DescException {
        if (description == null) {
            throw new DescException("Нет информации по " + name);
        }
        return description;
    }

    @Override
    public String getDescription() {
        try {
            return getInfo() + " " + name;
        } catch (DescException ex) {
            System.err.print(ex.getMessage());
            return name;
        }
    }

    public String getReversDescription() {
        try {
            return name + " " + getInfo();
        } catch (DescException ex) {
            System.err.print(ex.getMessage());
            return name;
        }
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
