package com.example.hardwaretrack.models;

public class GPU {

    private String manufacturer;
    private String model;
    private long coreCount;
    private long baseClock;
    private long boostClock;
    private String vRam;

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

    public long getCoreCount() {
        return coreCount;
    }

    public void setCoreCount(long coreCount) {
        this.coreCount = coreCount;
    }

    public long getBaseClock() {
        return baseClock;
    }

    public void setBaseClock(long baseClock) {
        this.baseClock = baseClock;
    }

    public long getBoostClock() {
        return boostClock;
    }

    public void setBoostClock(long boostClock) {
        this.boostClock = boostClock;
    }

    public String getvRam() {
        return vRam;
    }

    public void setvRam(String vRam) {
        this.vRam = vRam;
    }
}
