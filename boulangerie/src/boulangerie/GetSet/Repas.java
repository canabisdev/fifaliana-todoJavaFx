/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package boulangerie.GetSet;

/**
 *
 * @author kana
 */
public class Repas {
    private int NumRepas;
  private String NomRepas;
  private int PrixRepas;
  private int QtRepas;

    public Repas(int NumRepas, String NomRepas, int PrixRepas, int QtRepas) {
        this.NumRepas = NumRepas;
        this.NomRepas = NomRepas;
        this.PrixRepas = PrixRepas;
        this.QtRepas = QtRepas;
    }

    public Repas() {
        
    }

    public int getNumRepas() {
        return NumRepas;
    }

    public void setNumRepas(int NumRepas) {
        this.NumRepas = NumRepas;
    }

    public String getNomRepas() {
        return NomRepas;
    }

    public void setNomRepas(String NomRepas) {
        this.NomRepas = NomRepas;
    }

    public int getPrixRepas() {
        return PrixRepas;
    }

    public void setPrixRepas(int PrixRepas) {
        this.PrixRepas = PrixRepas;
    }

    public int getQtRepas() {
        return QtRepas;
    }

    public void setQtRepas(int QtRepas) {
        this.QtRepas = QtRepas;
    }

   
 
  
  
    
}
