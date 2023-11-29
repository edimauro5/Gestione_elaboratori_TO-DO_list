/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.diem.oop.developed.group12;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author Asdrubale
 */
public abstract class Device implements Comparable<Device> {

    private final String serialNumber;
    private final LocalDate releaseDate;
    private final int RAMsize;
    private final int storageCapacity;

    public Device(String serialNumber, int year, int month, int dayOfMonth, int RAMsize, int storageCapacity) {
        this.serialNumber = serialNumber;
        this.releaseDate = LocalDate.of(year, month, dayOfMonth);
        this.RAMsize = RAMsize;
        this.storageCapacity = storageCapacity;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public int getRAMsize() {
        return RAMsize;
    }

    public int getStorageCapacity() {
        return storageCapacity;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.serialNumber.toUpperCase());
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Device other = (Device) obj;
        if (!Objects.equals(this.serialNumber.toUpperCase(), other.serialNumber.toUpperCase())) {
            return false;
        }
        return true;
    }

    @Override
    public final int compareTo(Device o) {
        return serialNumber.compareToIgnoreCase(o.getSerialNumber());
    }

    public abstract boolean hasTouchScreen();

    @Override
    public String toString() {
        return "serialNumber= " + serialNumber + "\nreleaseDate= " + releaseDate + "\nRAMsize= " + RAMsize + "GB\nstorageCapacity= " + storageCapacity + "GB";
    }

}
