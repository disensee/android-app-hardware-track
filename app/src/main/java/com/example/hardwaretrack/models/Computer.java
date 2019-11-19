package com.example.hardwaretrack.models;

public class Computer {
    private long id;
    private CPU processor;
    private GPU graphicsProcessor;
    private Drive drive;
    private RAM ram;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

}
