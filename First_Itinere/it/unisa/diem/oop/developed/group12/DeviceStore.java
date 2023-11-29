/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.diem.oop.developed.group12;

import it.unisa.diem.oop.provided.DeviceFilter;
import it.unisa.diem.oop.provided.Filterable;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author Asdrubale
 */
public class DeviceStore implements Filterable {

    private final String name;
    private final Set<Device> store;

    public DeviceStore(String name) {
        this.name = name;
        store = new TreeSet<>();
    }

    public DeviceStore(String name, Comparator<Device> c) {
        this.name = name;
        store = new TreeSet<>(c);
    }

    public void addDevice(Device d) throws DeviceInsertionException {
        if (!store.add(d)) {
            throw new DeviceInsertionException();
        }
    }

    @Override
    public DeviceStore filter(DeviceFilter d, Comparator<Device> c) {
        DeviceStore store2 = new DeviceStore(name + " custom view", c);
        for (Device x : store) {
            if (d.checkDevice(x)) {
                store2.addDevice(x);
            }
        }
        return store2;
    }

    @Override
    public String toString() {
        String s = name + " contains " + store.size() + " items.\nPrinting:";
        for (Device x : store) {
            s = s.concat("\n*****\n" + x.toString());
        }
        return s;
    }

}
