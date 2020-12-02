/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package boulangerie.DAO;

import boulangerie.Connect;
import boulangerie.GetSet.Repas;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoRepas {
    Connect con = new Connect();
  String sql = "";
  
  public void add(int NumRepas, String NomRepas, int PrixRepas, int QtRepas)
    throws SQLException, ClassNotFoundException
  {
    Repas rep = new Repas();
    rep.setNumRepas(NumRepas);
    rep.setNomRepas(NomRepas);
    rep.setPrixRepas(PrixRepas);
    rep.setQtRepas(QtRepas);
    
    
    Connection connection = this.con.connect();
    this.sql = ("insert into REPAS(NomRepas, PrixRepas, QtRepas) values ('" + rep.getNomRepas() + "','" + rep.getPrixRepas() + "','" + rep.getQtRepas() + "')");
    System.out.println(this.sql);
    Statement statement = connection.createStatement();
    statement.execute(this.sql);
  }
  
  public void remove(int id) {
        try {
            Connection connection = this.con.connect();
            this.sql = ("delete from REPAS where NumRepas =" + id);
            Statement statement = connection.createStatement();
            System.out.println(this.sql);
            statement.execute(this.sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
  }
  
  public void mod(int NumRepas, String NomRepas, int PrixRepas, int QtRepas)
    throws SQLException, ClassNotFoundException
  {
    Repas rep = new Repas();
    rep.setNumRepas(NumRepas);
    rep.setNomRepas(NomRepas);
    rep.setPrixRepas(PrixRepas);
    rep.setQtRepas(QtRepas);
    
    Connection connection = this.con.connect();
    this.sql = ("update REPAS set NomRepas='" + rep.getNomRepas() + "', PrixRepas='" + rep.getPrixRepas() + "', QtRepas='" + rep.getQtRepas() + "'  where NumRepas='" + rep.getNumRepas() + "'");
    System.out.println(this.sql);
    Statement statement = connection.createStatement();
    statement.execute(this.sql);
  }
  
  public Repas find(int i)
    throws ClassNotFoundException, SQLException
  {
    Connection connection = this.con.connect();
    Repas rep = null;
    this.sql = ("select * from REPAS where NumRepas =" + i);
    Statement statement = connection.createStatement();
    ResultSet resultset = statement.executeQuery(this.sql);
    System.out.println(this.sql);
    while (resultset.next()) {
      rep = new Repas(resultset.getInt("NumRepas"), resultset.getString("NomRepas"), resultset.getInt("PrixRepas"), resultset.getInt("QtRepas"));
    }
    return rep;
  }
  
  public ArrayList findAll()
    throws SQLException, ClassNotFoundException
  {
    ArrayList<Repas> rep = new ArrayList();
    Connection connection = this.con.connect();
    this.sql = "select * from REPAS";
    Statement statement = connection.createStatement();
    ResultSet resultset = statement.executeQuery(this.sql);
    while (resultset.next()) {
      rep.add(new Repas(resultset.getInt("NumRepas"), resultset.getString("NomRepas"), resultset.getInt("PrixRepas"), resultset.getInt("QtRepas")));
    }
    return rep;
  }
  
  public ArrayList searchAll(String id)
    throws SQLException, ClassNotFoundException
  {
    ArrayList<Repas> rep = new ArrayList();
    Connection connection = this.con.connect();
    String other = null;
    if (id.matches("[0-9]*")) {
      other = id;
    }
    this.sql = ("select * from REPAS where NumRepas= " + other + " || NomRepas='" + id + "' || PrixRepas='" + id + "' || QtRepas='"+ id + "'");
    System.out.println(this.sql);
    Statement statement = connection.createStatement();
    ResultSet resultset = statement.executeQuery(this.sql);
    while (resultset.next()) {
      rep.add(new Repas(resultset.getInt("NumRepas"), resultset.getString("NomRepas"), resultset.getInt("PrixRepas"), resultset.getInt("QtRepas")));
    }
    return rep;
  }
  
  public int getLastNumero() {
      int num = -1;
      this.sql = (" select NumRepas from REPAS order by NumRepas desc limit 0,1");
      System.out.println(this.sql);
      Connection connection;
        try {
            connection = this.con.connect();
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery(this.sql);
            while( res.next() )
                num = (int) res.getInt("NumRepas");
            
        } catch (SQLException ex) {
            Logger.getLogger(DaoRepas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoRepas.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        return num;
      
  }
    
}
