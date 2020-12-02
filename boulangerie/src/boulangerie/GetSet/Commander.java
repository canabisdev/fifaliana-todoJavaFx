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
public class Commander {
  private int NumCommander;
  private int TarifCommander;
  private String DateCommander;
  private String NomClientCommander;
  private int NumRepas;
  private int NumResponsable;

    public Commander(int NumCommander, int TarifCommander, String DateCommander, String NomClientCommander, int NumRepas, int NumResponsable) {
        this.NumCommander = NumCommander;
        this.TarifCommander = TarifCommander;
        this.DateCommander = DateCommander;
        this.NomClientCommander = NomClientCommander;
        this.NumRepas = NumRepas;
        this.NumResponsable = NumResponsable;
    }

    public Commander() {
         //To change body of generated methods, choose Tools | Templates.
    }

    public int getNumCommander() {
        return NumCommander;
    }

    public void setNumCommander(int NumCommander) {
        this.NumCommander = NumCommander;
    }

    public int getTarifCommander() {
        return TarifCommander;
    }

    public void setTarifCommander(int TarifCommander) {
        this.TarifCommander = TarifCommander;
    }

    public String getDateCommander() {
        return DateCommander;
    }

    public void setDateCommander(String DateCommander) {
        this.DateCommander = DateCommander;
    }

    public String getNomClientCommander() {
        return NomClientCommander;
    }

    public void setNomClientCommander(String NomClientCommander) {
        this.NomClientCommander = NomClientCommander;
    }

    public int getNumRepas() {
        return NumRepas;
    }

    public void setNumRepas(int NumRepas) {
        this.NumRepas = NumRepas;
    }

    public int getNumResponsable() {
        return NumResponsable;
    }

    public void setNumResponsable(int NumResponsable) {
        this.NumResponsable = NumResponsable;
    }
  
    
}
