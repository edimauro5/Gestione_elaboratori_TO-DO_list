/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gruppo12;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javafx.collections.ObservableList;

/**
 * Thread che si occupa di salvare automaticamente una lista di eventi ogni 30
 * secondi
 *
 * @author Asdrubale
 */
public class TimedSaving implements Runnable {

    private ObservableList<Evento> l;
    private String fileName;

    /**
     * Costruttore della classe che si occupa di assegnare i valori passati dal
     * FXMLDocumentController alle variabili di istanza
     *
     * @param l è la lista osservabile contentente gli eventi inseriti
     * @param fileName è il nome del file su cui vogliamo far salvare gli eventi
     */
    public TimedSaving(ObservableList<Evento> l, String fileName) {
        this.l = l;
        this.fileName = fileName;
    }

    /**
     * Consente l'avvio del Thread che si fermerà solamente in caso di
     * interruzione
     * <p>
     * Il Thread attende per 30 secondi in stato di sleep nel quale lancerà
     * un'eccezione in caso di interruzione
     * <p>
     * Dopo l'attesa il Thread cercherà di acquisire il mutex della lista per
     * potersi sincronizzare con altre operazioni su di essa e poter
     * salvare/scrivere in maniera serializzata la lista osservabile
     * opportunamente inserita in un array
     */
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            synchronized (l) {
                try (ObjectOutputStream o = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)))) {
                    o.writeObject(new ArrayList<>(l));
                } catch (FileNotFoundException ex) {
                    System.err.println(ex.getMessage());
                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }
            }
            try {
                Thread.sleep(30 * 1000);
            } catch (InterruptedException ex) {
                System.err.println(ex.getMessage());
                return;
            }
        }
    }

}
