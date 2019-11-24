package com.example.hardwaretrack.models;

public class Drive {

    private long id;
    private String manufacturer;
    private String model;
    private String type;
    private String formFactor;
    private String transferProtocol;
    private long capacity;
    private long maxTransferRate;

    public Drive(long id, String manufacturer, String model, String type, String formFactor, String transferProtocol, long capacity, long maxTransferRate) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.model = model;
        this.type = type;
        this.formFactor = formFactor;
        this.transferProtocol = transferProtocol;
        this.capacity = capacity;
        this.maxTransferRate = maxTransferRate;
    }

    public Drive(){};

    public String getManufacturer() {
        return manufacturer;
    }

    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

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

    public long getCapacity() {
        return capacity;
    }

    public void setCapacity(long capacity) {
        this.capacity = capacity;
    }

    public long getMaxTransferRate() {
        return maxTransferRate;
    }

    public void setMaxTransferRate(long maxTransferRate) {
        this.maxTransferRate = maxTransferRate;
    }
}
