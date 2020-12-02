/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boulangerie.sousFolder;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author jok3r
 */
public class CommandeData {
    private SimpleIntegerProperty nombre, tarif, numRepas,numResp;
    private SimpleStringProperty date, nomCli;
    
    public CommandeData(int numero, int tarif, String date, String nomCli, int numRepas, int numResp) {
        this.nombre = new SimpleIntegerProperty(numero);
        this.tarif = new SimpleIntegerProperty(tarif);
        this.date = new SimpleStringProperty(date);
        this.nomCli = new SimpleStringProperty(nomCli);
        this.numRepas = new SimpleIntegerProperty(numRepas);
        this.numResp = new SimpleIntegerProperty(numResp);
    }
    
    public SimpleIntegerProperty numeroProperty() {
        return this.nombre;
    }
    public SimpleIntegerProperty tarifProperty() {
        return this.tarif;
    }
    public SimpleIntegerProperty numRespProperty(){
        return this.numResp;
    }
    public SimpleIntegerProperty numRepasProperty(){
        return this.numRepas;
    }
    public SimpleStringProperty nomCliProperty(){
        return this.nomCli;
    }
    public SimpleStringProperty dateProperty (){
        return this.date;
    }
    
    public int getNumero() {
        return this.nombre.get();
    }
    public int getTarif() {
        return this.tarif.get();
    }
    public int getNumResp(){
        return this.numResp.get();
    }
    public int getNumRepas(){
        return this.numRepas.get();
    }
    public String getNomCli(){
        return this.nomCli.get();
    }
    public String getDate() {
        return this.date.get();
    }
    
    public void setNumero(int nv) {
        this.nombre.set(nv);
    }
    public void setTarif(int nv) {
        this.tarif.set(nv);
    }
    public void setNumResp(int nv){
        this.numResp.set(nv);
    }
    public void setNumRepas(int nv) {
        this.numRepas.set(nv);
    }
    public void setNomClient(String newVal) {
        this.nomCli.set(newVal);
    }
    public void setDate(String newVal) {
        this.date.set(newVal);
    }
}
