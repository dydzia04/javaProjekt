package main;

import entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Iterator;
import java.util.List;

public class DatabaseOperations {

  private SessionFactory factory;
  private Session session;
  Transaction transaction;

  public DatabaseOperations(){
    try{
      factory = new Configuration().
          configure().buildSessionFactory();
//          addPackage("entity").
//          addAnnotatedClass(Employee.class).
//          addAnnotatedClass(Departament.class).
//          addAnnotatedClass(Job.class).
//          buildSessionFactory();
    }
    catch (Throwable ex){
      System.out.println("Nie udało się stworzyć SessionFactory. "+ex);
      throw new ExceptionInInitializerError(ex);
    }
  }

  public List getAllEmployees(){
    session = factory.openSession();
    transaction = null;
    try {
      transaction = session.beginTransaction();
      List employees = session.createQuery("FROM Employee").list();
      transaction.commit();
      return employees;
    }
    catch (Exception e){
      if (transaction!=null) transaction.rollback();
      e.printStackTrace();
    }
    finally {
      session.close();
    }
    return null;
  }

  public List getAllDepartaments(){
    session = factory.openSession();
    transaction = null;
    try {
      transaction = session.beginTransaction();
      List departaments = session.createQuery("FROM Departament ").list();
      transaction.commit();
      return departaments;
    }
    catch (Exception e){
      if (transaction!=null) transaction.rollback();
      e.printStackTrace();
    }
    finally {
      session.close();
    }
    return null;
  }

  public List getAllJobs(){
    session = factory.openSession();
    transaction = null;
    try {
      transaction = session.beginTransaction();
      List jobs = session.createQuery("FROM Job ").list();
      transaction.commit();
      return jobs;
    }
    catch (Exception e){
      if (transaction!=null) transaction.rollback();
      e.printStackTrace();
    }
    finally {
      session.close();
    }
    return null;
  }

  public List findEmployeesByName( String name ){
    List temp = null;
    try {
      List empl = this.getAllEmployees();
      Iterator iterator = empl.listIterator();
      Employee employee = null;

      while ( iterator.hasNext() ){
        if ( (employee = (Employee) iterator.next()).getFirstName().equals(name) ){
          System.out.println(employee);
          empl.add(employee);
        }
      }

      return temp;
    }
    catch (Exception e){
      System.err.println("Ups");
      //e.printStackTrace();
    }
    return null;
  }

  public List findEmployeesBySurName( String surname ){
    List temp = null;
    try {
      List empl = this.getAllEmployees();
      Iterator iterator = empl.listIterator();
      Employee employee = null;

      while ( iterator.hasNext() ){
        if ( (employee = (Employee) iterator.next()).getSurName().equals(surname) ){
          System.out.println(employee);
          empl.add(employee);
        }
      }

      return temp;
    }
    catch (Exception e){
      System.err.println("Ups");
      //e.printStackTrace();
    }
    return null;
  }

  public List findEmployeesByID( int ID ){
    List temp = null;
    try {
      List empl = this.getAllEmployees();
      Iterator iterator = empl.listIterator();
      Employee employee = null;

      while ( iterator.hasNext() ){
        if ( (employee = (Employee) iterator.next()).getId() == ID ){
          System.out.println(employee);
          empl.add(employee);
        }
      }

      return temp;
    }
    catch (Exception e){
      System.err.println("Ups");
      //e.printStackTrace();
    }
    return null;
  }

  //TODO: update
  public void updateEmployeeFirstName( String firstName ){}
  public void updateEmployeeSurName( String surName ){}
  public void updateEmployeeIdDept( int idDept ){}
  public void updateEmployeeIdJob( int idJob ){}

  //TODO: delete
  public void deleteEmployee( int ID ){ // need to find employee matching data in GUI, then give this method his ID

  }

  //TODO: create
  

}