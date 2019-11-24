package com.example.hardwaretrack.models;

public class CPU {

    private long id;
    private String manufacturer;
    private String model;
    private int coreCount;
    private int threadCount;
    private float baseClock;
    private float boostClock;

    public String getManufacturer() {
        return manufacturer;
    }

    public CPU(long id, String manufacturer, String model, int coreCount, int threadCount, float baseClock, float boostClock) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.model = model;
        this.coreCount = coreCount;
        this.threadCount = threadCount;
        this.baseClock = baseClock;
        this.boostClock = boostClock;
    }

    public CPU(){};

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public int getCoreCount() {
        return coreCount;
    }

    public void setCoreCount(int coreCount) {
        this.coreCount = coreCount;
    }

    public int getThreadCount() {
        return threadCount;
    }

    public void setThreadCount(int threadCount) {
        this.threadCount = threadCount;
    }

    public float getBaseClock() {
        return baseClock;
    }

    public void setBaseClock(float baseClock) {
        this.baseClock = baseClock;
    }

    public float getBoostClock() {
        return boostClock;
    }

    public void setBoostClock(float boostClock) {
        this.boostClock = boostClock;
    }
}
