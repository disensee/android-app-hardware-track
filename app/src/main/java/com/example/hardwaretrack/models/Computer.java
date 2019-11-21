package com.example.hardwaretrack.models;

public class Computer {
    private long id;
    private String type;
    private String manufacturer;
    private String model;
    private boolean customBuild;
    private CPU processor;
    private GPU graphicsProcessor;
    private Drive drive1;
    private Drive drive2;
    private Drive drive3;
    private RAM ram;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public boolean isCustomBuild() {
        return customBuild;
    }

    public void setCustomBuild(boolean customBuild) {
        this.customBuild = customBuild;
    }

    public CPU getProcessor() {
        return processor;
    }

    public void setProcessor(CPU processor) {
        this.processor = processor;
    }

    public GPU getGraphicsProcessor() {
        return graphicsProcessor;
    }

    public void setGraphicsProcessor(GPU graphicsProcessor) {
        this.graphicsProcessor = graphicsProcessor;
    }

    public Drive getDrive1() {
        return drive1;
    }

    public void setDrive1(Drive drive1) {
        this.drive1 = drive1;
    }

    public Drive getDrive2() {
        return drive2;
    }

    public void setDrive2(Drive drive2) {
        this.drive2 = drive2;
    }

    public Drive getDrive3() {
        return drive3;
    }

    public void setDrive3(Drive drive3) {
        this.drive3 = drive3;
    }

    public RAM getRam() {
        return ram;
    }

    public void setRam(RAM ram) {
        this.ram = ram;
    }
}
