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
public class Reglements {
  private int NumReglement;
  private int MontantReglement;
  private String EtatReglement;
  private int NumCommander;

    public Reglements(int NumReglement, int MontantReglement, String EtatReglement, int NumCommander) {
        this.NumReglement = NumReglement;
        this.MontantReglement = MontantReglement;
        this.EtatReglement = EtatReglement;
        this.NumCommander = NumCommander;
    }

    public int getNumReglement() {
        return NumReglement;
    }

    public void setNumReglement(int NumReglement) {
        this.NumReglement = NumReglement;
    }

    public int getMontantReglement() {
        return MontantReglement;
    }

    public void setMontantReglement(int MontantReglement) {
        this.MontantReglement = MontantReglement;
    }

    public String getEtatReglement() {
        return EtatReglement;
    }

    public void setEtatReglement(String EtatReglement) {
        this.EtatReglement = EtatReglement;
    }

    public int getNumCommander() {
        return NumCommander;
    }

    public void setNumCommander(int NumCommander) {
        this.NumCommander = NumCommander;
    }

    

    public Reglements() {
        //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
