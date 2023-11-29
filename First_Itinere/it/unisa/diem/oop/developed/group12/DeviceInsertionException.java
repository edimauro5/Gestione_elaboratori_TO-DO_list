/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.diem.oop.developed.group12;

/**
 *
 * @author Asdrubale
 */
public class DeviceInsertionException extends RuntimeException {

    /**
     * Creates a new instance of <code>DeviceInsertionException</code> without
     * detail message.
     */
    public DeviceInsertionException() {
    }

    /**
     * Constructs an instance of <code>DeviceInsertionException</code> with the
     * specified detail message.
     *
     * @param errorMessage
     */
    public DeviceInsertionException(String errorMessage) {
        super(errorMessage);
    }
}
