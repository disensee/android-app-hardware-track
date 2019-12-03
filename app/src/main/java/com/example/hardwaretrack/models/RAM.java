package com.example.hardwaretrack.models;

public class RAM {

    private long id;
    private String manufacturer;
    private String model;
    private String type;
    private long capacity;
    private long speed;
    private String formFactor;

    public RAM(long id, String manufacturer, String model, String type, long capacity, long speed, String formFactor) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.model = model;
        this.type = type;
        this.capacity = capacity;
        this.speed = speed;
        this.formFactor = formFactor;
    }

    public RAM(){}

    public long getId() { return id; }

    public void setId(long id) {
        this.id = id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getCapacity() {
        return capacity;
    }

    public void setCapacity(long capacity) {
        this.capacity = capacity;
    }

    public long getSpeed() {
        return speed;
    }

    public void setSpeed(long speed) {
        this.speed = speed;
    }

    public String getFormFactor() {
        return formFactor;
    }

    public void setFormFactor(String formFactor) {
        this.formFactor = formFactor;
    }

    @Override
    public String toString() {
        return "RAM{" +
                "id=" + id +
                ", manufacturer='" + manufacturer + '\'' +
                ", model='" + model + '\'' +
                ", type='" + type + '\'' +
                ", capacity=" + capacity +
                ", speed=" + speed +
                ", formFactor='" + formFactor + '\'' +
                '}';
    }
}
