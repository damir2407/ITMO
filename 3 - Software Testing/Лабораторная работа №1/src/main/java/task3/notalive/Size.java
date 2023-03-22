package main.java.task3.notalive;

public enum Size {
    LITTLE("маленькая "),
    AVERAGE("средняя "),
    BIG("большая ");

    private String description;

    Size(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
