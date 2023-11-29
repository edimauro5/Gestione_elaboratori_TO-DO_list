/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.diem.oop.developed.group12;

import java.util.Comparator;

/**
 *
 * @author Asdrubale
 */
public class DeviceReleaseDateComparator implements Comparator<Device> {

    @Override
    public int compare(Device o1, Device o2) {
        if (o1.getReleaseDate().isAfter(o2.getReleaseDate())) {
            return 1;
        }
        if (o1.getReleaseDate().isBefore(o2.getReleaseDate())) {
            return -1;
        }
        return o1.compareTo(o2);
    }

}
