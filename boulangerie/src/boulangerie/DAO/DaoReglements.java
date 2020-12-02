/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package boulangerie.DAO;

import boulangerie.Connect;
import boulangerie.GetSet.Reglements;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
public class DaoReglements {
    Connect con = new Connect();
  String sql = "";
  
  public void add(int NumReglement, int MontantReglement, String EtatReglement, int NumCommander)
    throws SQLException, ClassNotFoundException
  {
    Reglements reg = new Reglements();
    reg.setNumReglement(NumReglement);
    reg.setMontantReglement(MontantReglement);
    reg.setEtatReglement(EtatReglement);
    reg.setNumCommander(NumCommander);
    
    
    
    Connection connection = this.con.connect();
    this.sql = ("insert into REGLEMENTS(MontantReglement, EtatReglement, NumCommander) values ('" + reg.getMontantReglement() + "','" + reg.getEtatReglement() + "','" + reg.getNumCommander() + "')");
    System.out.println(this.sql);
    Statement statement = connection.createStatement();
    statement.execute(this.sql);
  }
  
  public void remove(int id)
    throws SQLException, ClassNotFoundException
  {
    Connection connection = this.con.connect();
    this.sql = ("delete from REGLEMENTS where NumReglement =" + id);
    Statement statement = connection.createStatement();
    System.out.println(this.sql);
    statement.execute(this.sql);
  }
  
  public void mod(int NumReglement, int MontantReglement, String EtatReglement, int NumCommander)
    throws SQLException, ClassNotFoundException
  {
    Reglements reg = new Reglements();
    reg.setNumReglement(NumReglement);
    reg.setMontantReglement(MontantReglement);
    reg.setEtatReglement(EtatReglement);
    reg.setNumCommander(NumCommander);
    
    Connection connection = this.con.connect();
    this.sql = ("update REGLEMENTS set MontantReglement='" + reg.getMontantReglement() + "',EtatReglement='" + reg.getEtatReglement() + "', NumCommander='" + reg.getNumCommander() + "'  where NumReglement='" + reg.getNumReglement() + "'");
    System.out.println(this.sql);
    Statement statement = connection.createStatement();
    statement.execute(this.sql);
  }
  
  public Reglements find(int i)
    throws ClassNotFoundException, SQLException
  {
    Connection connection = this.con.connect();
    Reglements reg = null;
    this.sql = ("select * from REGLEMENTS where NumReglement =" + i);
    Statement statement = connection.createStatement();
    ResultSet resultset = statement.executeQuery(this.sql);
    System.out.println(this.sql);
    while (resultset.next()) {
      reg = new Reglements(resultset.getInt("NumReglenent"), resultset.getInt("MontantReglement"), resultset.getString("EtatReglement"), resultset.getInt("NumCommander"));
    }
    return reg;
  }
  
  public ArrayList findAll()
    throws SQLException, ClassNotFoundException
  {
    ArrayList<Reglements> reg = new ArrayList();
    Connection connection = this.con.connect();
    this.sql = "select * from REGLEMENTS";
    Statement statement = connection.createStatement();
    ResultSet resultset = statement.executeQuery(this.sql);
    while (resultset.next()) {
      reg.add(new Reglements(resultset.getInt("NumReglement"), resultset.getInt("MontantReglement"), resultset.getString("EtatReglement"), resultset.getInt("NumCommander")));
    }
    return reg;
  }
  
  public ArrayList searchAll(String id)
    throws SQLException, ClassNotFoundException
  {
    ArrayList<Reglements> reg = new ArrayList();
    Connection connection = this.con.connect();
    String other = null;
    if (id.matches("[0-9]*")) {
      other = id;
    }
    this.sql = ("select * from REGLEMENTS where NumReglement= " + other + " || MontantReglement='" + id + "' || EtatReglement='" + id + "' || NumCommander='"+ id + "'");
    System.out.println(this.sql);
    Statement statement = connection.createStatement();
    ResultSet resultset = statement.executeQuery(this.sql);
    while (resultset.next()) {
      reg.add(new Reglements(resultset.getInt("NumReglement"), resultset.getInt("MontantReglement"), resultset.getString("EtatReglement"), resultset.getInt("NumCommander")));
    }
    return reg;
  }
    
}
