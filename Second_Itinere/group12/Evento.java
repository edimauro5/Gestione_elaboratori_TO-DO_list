/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gruppo12;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author Asdrubale
 */
public class Evento implements Serializable, Comparable<Evento> {

    private LocalDate data;
    private String descrizione;
    private int id;
    private static int cont = 1;

    public Evento() {
    }

    public Evento(LocalDate data, String descrizione) {
        this.data = data;
        this.descrizione = descrizione;
        id = cont;
        cont++;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public int getId() {
        return id;
    }

    @Override
    public int compareTo(Evento t) {
        if (data.isEqual(t.getData())) {
            return Integer.compare(id, t.getId());
        } else {
            return data.compareTo(t.getData());
        }
    }

}
