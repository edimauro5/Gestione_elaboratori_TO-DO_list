/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gruppo12;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

/**
 *
 * @author Asdrubale
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Button btnAdd;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField tfdDescrizione;
    @FXML
    private TableView<Evento> mainTab;
    @FXML
    private ContextMenu contextMenu;
    @FXML
    private MenuItem rimuoviItem;
    @FXML
    private MenuItem importaItem;
    @FXML
    private MenuItem esportaItem;
    @FXML
    private TableColumn<Evento, LocalDate> dataClm;
    @FXML
    private TableColumn<Evento, String> eventoClm;
    @FXML
    private AnchorPane rootPane;

    private ObservableList<Evento> eventi;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        eventi = FXCollections.observableArrayList();
        eventi.clear();
        eventi.addAll(metodo("file_save"));
        eventi.sort(null);
        Thread t = new Thread(new TimedSaving(eventi, "file_save"));
        t.setDaemon(true);
        t.start();
        dataClm.setCellValueFactory(new PropertyValueFactory("data"));
        eventoClm.setCellValueFactory(new PropertyValueFactory("descrizione"));
        eventoClm.setCellFactory(TextFieldTableCell.forTableColumn());
        mainTab.setItems(eventi);
        datePicker.setValue(LocalDate.now());
        btnAdd.disableProperty().bind(tfdDescrizione.textProperty().isEqualTo(""));
        rimuoviItem.disableProperty().bind(new SimpleListProperty(eventi).emptyProperty());
        esportaItem.disableProperty().bind(new SimpleListProperty(eventi).emptyProperty());
    }

    @FXML
    private void add(ActionEvent event) {
        synchronized (eventi) {
            eventi.add(new Evento(datePicker.getValue(), tfdDescrizione.getText()));
            eventi.sort(null);
        }
    }

    @FXML
    private void rimuovi(ActionEvent event) {
        synchronized (eventi) {
            eventi.remove(mainTab.getSelectionModel().getSelectedItem());
        }
    }

    @FXML
    private void importa(ActionEvent event) {
        FileChooser fc = new FileChooser();
        File file = fc.showOpenDialog(rootPane.getScene().getWindow());
        if (file != null) {
            try (Scanner i = new Scanner(new BufferedReader(new FileReader(file)))) {
                synchronized (eventi) {
                    eventi.clear();
                    i.useDelimiter(";|\\n");
                    i.useLocale(Locale.US);
                    Evento e;
                    while (i.hasNext()) {
                        e = new Evento();
                        e.setData(LocalDate.parse(i.next()));
                        e.setDescrizione(i.next().replace('|', ';'));
                        eventi.add(e);
                        eventi.sort(null);
                    }
                }
            } catch (FileNotFoundException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }

    @FXML
    private void esporta(ActionEvent event) {
        FileChooser fc = new FileChooser();
        File file = fc.showSaveDialog(rootPane.getScene().getWindow());
        if (file != null) {
            try (PrintWriter o = new PrintWriter(new BufferedWriter(new FileWriter(file)))) {
                synchronized (eventi) {
                    for (Evento x : eventi) {
                        o.write(String.valueOf(x.getData()) + ';' + x.getDescrizione().replace(';', '|') + "\n");
                    }
                }
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }

    @FXML
    private void editEvento(TableColumn.CellEditEvent<Evento, String> event) {
        synchronized (eventi) {
            Evento evento = mainTab.getSelectionModel().getSelectedItem();
            evento.setDescrizione(event.getNewValue());
        }
    }

    private ArrayList<Evento> metodo (String fileName){
        ArrayList<Evento> array=null;
        try (ObjectInputStream i = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fileName)))) {
            array=(ArrayList<Evento>)i.readObject();
        } catch (FileNotFoundException ex) {
            System.err.println(ex.getMessage());
        } catch (IOException | ClassNotFoundException ex) {
            System.err.println(ex.getMessage());
        }
        return array;
    }
}
