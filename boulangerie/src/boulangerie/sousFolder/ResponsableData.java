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
public class ResponsableData {
    private SimpleIntegerProperty numero;
    private SimpleStringProperty nom, pseudo, pass, contact, droit;

    public ResponsableData(int numero, String nom, String pseudo, String pass, String contact, String droit) {
        this.numero = new SimpleIntegerProperty(numero);
        this.nom = new SimpleStringProperty(nom);
        this.pseudo = new SimpleStringProperty(pseudo);
        this.pass = new SimpleStringProperty(pass);
        this.contact = new SimpleStringProperty(contact);
        this.droit = new SimpleStringProperty(droit);
    }

    public SimpleIntegerProperty numeroProperty() {
        return numero;
    }

    public SimpleStringProperty nomProperty() {
        return nom;
    }

    public SimpleStringProperty pseudoProperty() {
        return pseudo;
    }

    public SimpleStringProperty passProperty() {
        return pass;
    }

    public SimpleStringProperty contactProperty() {
        return contact;
    }

    public SimpleStringProperty droitProperty() {
        return droit;
    }
    
    public void setNumero(int newVal) {
        this.numero.set(newVal);
    }
    public void setNom(String newVal) {
        this.nom.set(newVal);
    }
    public void setPass(String newVal) {
        this.pass.set(newVal);
    }
    public void setContact(String newVal) {
        this.contact.set(newVal);
    }
    public void setPseudo(String newVal) {
        this.pseudo.set(newVal);
    }
    public void setDroit(String newVal) {
        this.droit.set(newVal);
    }
    
    public int getNumero() {
        return numero.get();
    }
    public String getNom() {
        return nom.get();
    }
    public String getContact() {
        return contact.get();
    }
    public String getPseudo() {
        return pseudo.get();
    }
    public String getPass() {
        return pass.get();
    }
    public String getDroit() {
        return droit.get();
    }
    
}
