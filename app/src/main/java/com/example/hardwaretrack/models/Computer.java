package com.example.hardwaretrack.models;

public class Computer {
    private long id;
    private String type;
    private String manufacturer;
    private String model;
    private boolean customBuild;
    private CPU processor;
    private GPU graphicsProcessor;
    private Drive drive;
    private RAM ram;

    public Computer(long id, String type, String manufacturer, String model, boolean customBuild, CPU processor, GPU graphicsProcessor, Drive drive, RAM ram) {
        this.id = id;
        this.type = type;
        this.manufacturer = manufacturer;
        this.model = model;
        this.customBuild = customBuild;
        this.processor = processor;
        this.graphicsProcessor = graphicsProcessor;
        this.drive = drive;
        this.ram = ram;
    }

    public Computer(){}

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

    public Drive getDrive() {
        return drive;
    }

    public void setDrive(Drive drive) {
        this.drive = drive;
    }

    public RAM getRam() {
        return ram;
    }

    public void setRam(RAM ram) {
        this.ram = ram;
    }


    @Override
    public String toString() {
        return this.manufacturer + " " + this.model;
    }

}
