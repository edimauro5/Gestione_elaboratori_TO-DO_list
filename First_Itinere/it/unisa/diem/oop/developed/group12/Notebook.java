/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.diem.oop.developed.group12;

import it.unisa.diem.oop.provided.CPUType;
import it.unisa.diem.oop.provided.NBScreenType;

/**
 *
 * @author Asdrubale
 */
public class Notebook extends Device {

    private final CPUType type;
    private final NBScreenType screenType;
    private boolean touchScreen;

    public Notebook(CPUType type, NBScreenType screenType, String serialNumber, int year, int month, int dayOfMonth, int RAMsize, int storageCapacity) {
        super(serialNumber, year, month, dayOfMonth, RAMsize, storageCapacity);
        this.type = type;
        this.screenType = screenType;
    }

    public Notebook(CPUType type, NBScreenType screenType, boolean touchScreen, String serialNumber, int year, int month, int dayOfMonth, int RAMsize, int storageCapacity) {
        super(serialNumber, year, month, dayOfMonth, RAMsize, storageCapacity);
        this.type = type;
        this.screenType = screenType;
        this.touchScreen = touchScreen;
    }

    public CPUType getType() {
        return type;
    }

    public NBScreenType getScreenType() {
        return screenType;
    }

    @Override
    public boolean hasTouchScreen() {
        return touchScreen;
    }

    @Override
    public String toString() {
        return "Notebook\n" + super.toString() + "\nCPU= " + type + "\nscreenType= " + screenType + (touchScreen ? "\ntouchScreen available" : "");
    }

}
