package boulangerie.GetSet;

public class Responsables
{
  private int NumResponsable;
  private String NomResponsable;
  private String PseudoResponsable;
  private String PasswordResponsable;
  private String TelResponsable;
  private String DroitResponsable;
  
  public Responsables(int NumResponsable, String NomResponsable, String PseudoResponsable, String PasswordResponsable, String TelResponsable, String DroitResponsable)
  {
    this.NumResponsable = NumResponsable;
    this.NomResponsable = NomResponsable;
    this.PseudoResponsable = PseudoResponsable;
    this.PasswordResponsable = PasswordResponsable;
    this.TelResponsable = TelResponsable;
    this.DroitResponsable = DroitResponsable;
  }
  
  public Responsables() {}
  
  public int getNumResponsable()
  {
    return this.NumResponsable;
  }
  
  public void setNumResponsable(int NumResponsable)
  {
    this.NumResponsable = NumResponsable;
  }
  
  public String getNomResponsable()
  {
    return this.NomResponsable;
  }
  
  public void setNomResponsable(String NomResponsable)
  {
    this.NomResponsable = NomResponsable;
  }
  
  public String getPseudoResponsable()
  {
    return this.PseudoResponsable;
  }
  
  public void setPseudoResponsable(String PseudoResponsable)
  {
    this.PseudoResponsable = PseudoResponsable;
  }
  
  public String getPasswordResponsable()
  {
    return this.PasswordResponsable;
  }
  
  public void setPasswordResponsable(String PasswordResponsable)
  {
    this.PasswordResponsable = PasswordResponsable;
  }
  
  public String getTelResponsable()
  {
    return this.TelResponsable;
  }
  
  public void setTelResponsable(String TelResponsable)
  {
    this.TelResponsable = TelResponsable;
  }
  
  public String getDroitResponsable()
  {
    return this.DroitResponsable;
  }
  
  public void setDroitResponsable(String DroitResponsable)
  {
    this.DroitResponsable = DroitResponsable;
  }
}
