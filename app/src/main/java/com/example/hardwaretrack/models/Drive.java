package com.example.hardwaretrack.models;

public class Drive {

    private String manufacturer;
    private String model;
    private String type;
    private String formFactor;
    private String transferProtocol;
    private String capacity;
    private long maxTransferRate;

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

    public String getFormFactor() {
        return formFactor;
    }

    public void setFormFactor(String formFactor) {
        this.formFactor = formFactor;
    }

    public String getTransferProtocol() {
        return transferProtocol;
    }

    public void setTransferProtocol(String transferProtocol) {
        this.transferProtocol = transferProtocol;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public long getMaxTransferRate() {
        return maxTransferRate;
    }

    public void setMaxTransferRate(long maxTransferRate) {
        this.maxTransferRate = maxTransferRate;
    }
}
