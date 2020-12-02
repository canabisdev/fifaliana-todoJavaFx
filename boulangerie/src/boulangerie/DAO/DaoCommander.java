/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package boulangerie.DAO;

import boulangerie.Connect;
import boulangerie.GetSet.Commander;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
public class DaoCommander {
  Connect con = new Connect();
  String sql = "";
  
  public void add(int NumCommander, int nombre, String DateCommander, String NomClientCommander,   int NumRepas, int NumResponsable)
    throws SQLException, ClassNotFoundException
  {
    Commander com = new Commander();
    com.setNumCommander(NumCommander);
    com.setTarifCommander(nombre);
    com.setDateCommander(DateCommander);
    com.setNomClientCommander(NomClientCommander);
    com.setNumRepas(NumRepas);
    com.setNumResponsable(NumResponsable);
    
    Connection connection = this.con.connect();
    this.sql = ("insert into COMMANDER( TarifCommander, DateCommander, NomClientCommander,NumRepas,NumResponsable ) values (" + com.getTarifCommander() + ",'" + com.getDateCommander() + "','" + com.getNomClientCommander() + "'," + com.getNumRepas() + "," + com.getNumResponsable() + ")");
    System.out.println(this.sql);
    Statement statement = connection.createStatement();
    statement.execute(this.sql);

    updateStock(NumRepas, nombre);
  }
  
  public void remove(int id)
    throws SQLException, ClassNotFoundException
  {
    Connection connection = this.con.connect();
    this.sql = ("delete from COMMANDER where NumCommander =" + id);
    Statement statement = connection.createStatement();
    System.out.println(this.sql);
    statement.execute(this.sql);
  }
  
  public void mod(int NumCommander, int nombre, String DateCommander, String NomClientCommander,   int NumRepas, int NumResponsable)
    throws SQLException, ClassNotFoundException
  {
    Commander com = new Commander();
    com.setNumCommander(NumCommander);
    com.setTarifCommander(nombre);
    com.setDateCommander(DateCommander);
    com.setNomClientCommander(NomClientCommander);
    com.setNumRepas(NumRepas);
    com.setNumResponsable(NumResponsable);
    Connection connection = this.con.connect();
    this.sql = ("update COMMANDER set TarifCommander=" + com.getTarifCommander() + ", DateCommander='" + com.getDateCommander() + "', NomClientCommander='" + com.getNomClientCommander() + "',NumRepas=" + com.getNumRepas() + ", NumResponsable=" + com.getNumResponsable() + " where NumCommander=" + com.getNumCommander() + "");
    System.out.println(this.sql);
    Statement statement = connection.createStatement();
    statement.execute(this.sql);
    
      updateStock(NumRepas, nombre);
   
  }
  
  public Commander find(int i)
    throws ClassNotFoundException, SQLException
  {
    Connection connection = this.con.connect();
    Commander com = null;
    this.sql = ("select * from COMMANDER where NumCommander =" + i);
    Statement statement = connection.createStatement();
    ResultSet resultset = statement.executeQuery(this.sql);
    System.out.println(this.sql);
    while (resultset.next()) {
      com = new Commander(resultset.getInt("NumCommander"), resultset.getInt("TarifCommander"), resultset.getString("DateCommander"), resultset.getString("NomClientCommander"), resultset.getInt("NumRepas"), resultset.getInt("NumResponsable"));
    }
    return com;
  }
  
  public ArrayList findAll()
  {
      ArrayList<Commander> com = new ArrayList();
      
      try {
          Connection connection = this.con.connect();
          this.sql = "select * from COMMANDER";
          Statement statement = connection.createStatement();
          ResultSet resultset = statement.executeQuery(this.sql);
          while (resultset.next()) {
              com.add(new Commander(resultset.getInt("NumCommander"), resultset.getInt("TarifCommander"), resultset.getString("DateCommander"), resultset.getString("NomClientCommander"), resultset.getInt("NumRepas"), resultset.getInt("NumResponsable")));
          }
      
      } catch (SQLException ex) {
          Logger.getLogger(DaoCommander.class.getName()).log(Level.SEVERE, null, ex);
      } catch (ClassNotFoundException ex) {
          Logger.getLogger(DaoCommander.class.getName()).log(Level.SEVERE, null, ex);
      }
      
      return com;
  }
  
  public ArrayList searchAll(String id)
    throws SQLException, ClassNotFoundException
  {
    ArrayList<Commander> com = new ArrayList();
    Connection connection = this.con.connect();
    String other = null;
    if (id.matches("[0-9]*")) {
      other = id;
    }
    this.sql = ("select * from COMMNADER where NumCommander= " + other + " || TarifCommander='" + id + "' || DateCommander='" + id + "' || NomClientCommander='" + id  + "' || NumRepas='" + id + "' || NumResponsable='" + id + "'");
    System.out.println(this.sql);
    Statement statement = connection.createStatement();
    ResultSet resultset = statement.executeQuery(this.sql);
    while (resultset.next()) {
      com.add(new Commander(resultset.getInt("NumCommander"), resultset.getInt("TarifCommander"), resultset.getString("DateCommander"), resultset.getString("NomClientCommander"), resultset.getInt("NumRepas"), resultset.getInt("NumResponsable")));
    }
    return com;
  } 
  
  public int getLastNumero() {
      int num = -1;
      this.sql = (" select NumCommander from COMMANDER order by NumCommander desc limit 0,1");
      System.out.println(this.sql);
      Connection connection;
        try {
            connection = this.con.connect();
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery(this.sql);
            while( res.next() )
                num = (int) res.getInt("NumCommander");
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
      
        return num;
      
  }

    public ObservableList getNumResp() {
      ObservableList<Integer> com = FXCollections.observableArrayList();
      
      try {
          Connection connection = this.con.connect();
          this.sql = ("select NumResponsable from RESPONSABLES");
          System.out.println(this.sql);
          Statement statement = connection.createStatement();
          ResultSet resultset = statement.executeQuery(this.sql);
          while (resultset.next()) {
              com.add(resultset.getInt("NumResponsable"));
          }
      } catch (SQLException ex) {
          ex.printStackTrace();
      } catch (ClassNotFoundException ex) {
          ex.printStackTrace();
      }
      
      return com;
    }

    public ObservableList getNumRepas() {
        ObservableList<Integer> com = FXCollections.observableArrayList();
      
      try {
          Connection connection = this.con.connect();
          this.sql = ("select NumRepas from REPAS");
          System.out.println(this.sql);
          Statement statement = connection.createStatement();
          ResultSet resultset = statement.executeQuery(this.sql);
          while (resultset.next()) {
              com.add(resultset.getInt("NumRepas"));
          }
      } catch (SQLException ex) {
          ex.printStackTrace();
      } catch (ClassNotFoundException ex) {
          ex.printStackTrace();
      }
      
      return com;
    }

    private void updateStock(int NumRepas, int nombre) {

      try {
          // maj stock
          int qtRepas = 0;
          this.sql = "select QtRepas from REPAS Where NumRepas="+NumRepas;
          Connection con = this.con.connect();
          Statement statement = con.createStatement();
          ResultSet res = statement.executeQuery(this.sql);
          while (res.next()) {
              qtRepas = res.getInt("QtRepas");
          }
          
          System.out.println(qtRepas);
          
          this.sql = "update REPAS set QtRepas="+(qtRepas - nombre)+ " where NumRepas="+NumRepas;
          statement.execute(this.sql);
          System.out.println(this.sql);
      } catch (SQLException ex) {
          Logger.getLogger(DaoCommander.class.getName()).log(Level.SEVERE, null, ex);
      } catch (ClassNotFoundException ex) {
          Logger.getLogger(DaoCommander.class.getName()).log(Level.SEVERE, null, ex);
      }
    
    }
}
