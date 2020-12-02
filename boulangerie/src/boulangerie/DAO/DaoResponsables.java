package boulangerie.DAO;

import boulangerie.Connect;
import boulangerie.GetSet.Responsables;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
public class DaoResponsables
{
  Connect con = new Connect();
  String sql = "";
  
  public void add(int NumResponsable, String NomResponsable, String PseudoResponsable, String PasswordResponsable,   String TelResponsable, String DroitResponsable)
    throws SQLException, ClassNotFoundException
  {
    Responsables resp = new Responsables();
    resp.setNumResponsable(NumResponsable);
    resp.setNomResponsable(NomResponsable);
    resp.setPseudoResponsable(PseudoResponsable);
    resp.setPasswordResponsable(PasswordResponsable);
    resp.setTelResponsable(TelResponsable);
    resp.setDroitResponsable(DroitResponsable);
    
    Connection connection = this.con.connect();
    this.sql = ("insert into RESPONSABLES(NomResponsable, PseudoResponsable, PasswordResponsable, TelResponsable, DroitResponsable) values ('" + resp.getNomResponsable() + "','" + resp.getPseudoResponsable() + "','" + resp.getPasswordResponsable() + "','" + resp.getTelResponsable() + "','" + resp.getDroitResponsable() + "')");
    System.out.println(this.sql);
    Statement statement = connection.createStatement();
    statement.execute(this.sql);
  }
  
  public void remove(int id)
    throws SQLException, ClassNotFoundException
  {
    Connection connection = this.con.connect();
    this.sql = ("delete from RESPONSABLES where NumResponsable =" + id);
    Statement statement = connection.createStatement();
    System.out.println(this.sql);
    statement.execute(this.sql);
  }
  
  public void mod(int NumResponsable, String NomResponsable, String PseudoResponsable, String PasswordResponsable, String TelResponsable, String DroitResponsable)
    throws SQLException, ClassNotFoundException
  {
    Responsables resp = new Responsables();
    resp.setNumResponsable(NumResponsable);
    resp.setNomResponsable(NomResponsable);
    resp.setPseudoResponsable(PseudoResponsable);
    resp.setPasswordResponsable(PasswordResponsable);
    resp.setTelResponsable(TelResponsable);
    resp.setDroitResponsable(DroitResponsable);
    Connection connection = this.con.connect();
    this.sql = ("update RESPONSABLES set NomResponsable='" + resp.getNomResponsable() + "', PseudoResponsable='" + resp.getPseudoResponsable() + "', PasswordResponsable='" + resp.getPasswordResponsable() + "', TelResponsable='" + resp.getTelResponsable() + "', DroitResponsable='" + resp.getDroitResponsable() + "' where NumResponsable='" + resp.getNumResponsable() + "'");
    System.out.println(this.sql);
    Statement statement = connection.createStatement();
    statement.execute(this.sql);
  }
  
  public int getLastNumero() {
      int num = -1;
      this.sql = (" select NumResponsable from RESPONSABLES order by NumResponsable desc limit 0,1");
      System.out.println(this.sql);
      Connection connection;
        try {
            connection = this.con.connect();
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery(this.sql);
            while( res.next() )
                num = (int) res.getInt("NumResponsable");
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
      
        return num;
      
  }
  
  public Responsables find(int i)
    throws ClassNotFoundException, SQLException
  {
    Connection connection = this.con.connect();
    Responsables resp = null;
    this.sql = ("select * from RESPONSABLES where NumResponsable =" + i);
    Statement statement = connection.createStatement();
    ResultSet resultset = statement.executeQuery(this.sql);
    System.out.println(this.sql);
    while (resultset.next()) {
      resp = new Responsables(resultset.getInt("NumResponsable"), resultset.getString("NomResponsable"), resultset.getString("PseudoResponsable"), resultset.getString("PasswordResponsable"), resultset.getString("TelResponsable"), resultset.getString("DroitResponsable"));
    }
    return resp;
  }
  
  public ArrayList findAll()
    throws SQLException, ClassNotFoundException
  {
    ArrayList<Responsables> resp = new ArrayList();
    Connection connection = this.con.connect();
    this.sql = "select * from RESPONSABLES";
    Statement statement = connection.createStatement();
    ResultSet resultset = statement.executeQuery(this.sql);
    while (resultset.next()) {
      resp.add(new Responsables(resultset.getInt("NumResponsable"), resultset.getString("NomResponsable"), resultset.getString("PseudoResponsable"), resultset.getString("PasswordResponsable"), resultset.getString("TelResponsable"), resultset.getString("DroitResponsable")));
    }
    return resp;
  }
  
  public ArrayList searchAll(String id)
    throws SQLException, ClassNotFoundException
  {
    ArrayList<Responsables> resp = new ArrayList();
    Connection connection = this.con.connect();
    String other = null;
    if (id.matches("[0-9]*")) {
      other = id;
    }
    this.sql = ("select * from RESPONSABLES where NumResponsable= " + other + " || NomResponsable='" + id + "' || PseudoResponsable='" + id + "' || PasswordResponsable='" + id  + "' || TelResponsable='" + id + "' || DroitResponsable='" + id + "'");
    System.out.println(this.sql);
    Statement statement = connection.createStatement();
    ResultSet resultset = statement.executeQuery(this.sql);
    while (resultset.next()) {
      resp.add(new Responsables(resultset.getInt("NumResponsable"), resultset.getString("NomResponsable"), resultset.getString("PseudoResponsable"), resultset.getString("PasswordResponsable"), resultset.getString("TelResponsable"), resultset.getString("DroitResponsable")));
    }
    return resp;
  }
}
