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
public class RepasData {
    private SimpleIntegerProperty numero, prix, quantite;
    private SimpleStringProperty nom;

    public RepasData(int numero, int prix, int quantite, String nom) {
        this.numero = new SimpleIntegerProperty(numero);
        this.prix = new SimpleIntegerProperty(prix);
        this.quantite = new SimpleIntegerProperty(quantite);
        this.nom = new SimpleStringProperty(nom);
    }

    public SimpleIntegerProperty numeroProperty() {
        return numero;
    }

    public SimpleIntegerProperty prixProperty() {
        return prix;
    }

    public SimpleIntegerProperty quantiteProperty() {
        return quantite;
    }

    public SimpleStringProperty nomProperty() {
        return nom;
    }
    
    public String getNom() {
        return nom.get();
    }
    public int getNumero() {
        return numero.get();
    }
    public int getPrix() {
        return prix.get();
    }
    public int getQuantite() {
        return quantite.get();
    }
    
    public void setPrix(int prix) {
        this.prix.set(prix);
    }
    public void setQuantite(int quantite) {
        this.quantite.set(quantite);
    }
    public void setNumero(int num) {
        this.numero.set(num);
    }
    public void setNom(String nom) {
        this.nom.set(nom);
    }
    
}
