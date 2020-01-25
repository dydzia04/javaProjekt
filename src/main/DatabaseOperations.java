package main;

import entity.Departament;
import entity.Employee;
import entity.Job;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
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
    }
    catch (Throwable ex){
      System.out.println("Nie udało się stworzyć SessionFactory. "+ex);
      throw new ExceptionInInitializerError(ex);
    }
  }

  // --- GET ---
  public List getAllEmployees(){
    session = factory.openSession();
    transaction = null;
    try {
      transaction = session.beginTransaction();
      List employees = session.createQuery("FROM Employee").list();
      transaction.commit();
      return employees;
    } catch (Exception e) {
      if (transaction != null) transaction.rollback();
      e.printStackTrace();
    } finally {
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
    } catch (Exception e) {
      if (transaction != null) transaction.rollback();
      e.printStackTrace();
    } finally {
      session.close();
    }
    return null;
  }

  // --- FIND ---
  public List findEmployeesByName(String name) {
    List temp = new ArrayList();
    try {
      List empl = this.getAllEmployees();
      Iterator iterator = empl.listIterator();
      Employee employee = null;

      while (iterator.hasNext()) {
        if ((employee = (Employee) iterator.next()).getFirstName().equals(name)) {
          System.out.println(employee);
          temp.add(employee);
        }
      }

      return temp;
    } catch (Exception e) {
      System.err.println("Name search error");
      e.printStackTrace();
      e.getCause();
    }
    return null;
  }
  public List findEmployeesBySurName( String surname ){
    List temp = new ArrayList();
    try {
      List empl = this.getAllEmployees();
      Iterator iterator = empl.listIterator();
      Employee employee = null;

      while (iterator.hasNext()) {
        if ((employee = (Employee) iterator.next()).getSurName().equals(surname)) {
          System.out.println(employee);
          temp.add(employee);
        }
      }

      return temp;
    } catch (Exception e) {
      System.err.println("Surname search error");
      e.printStackTrace();
      e.getCause();
    }
    return null;
  }
  public List findEmployeesByID( int ID ){
    List temp = new ArrayList();
    try {
      List empl = this.getAllEmployees();
      Iterator iterator = empl.listIterator();
      Employee employee = null;

      while (iterator.hasNext()) {
        employee = (Employee) iterator.next();
        if (employee.getId() == ID) {
          System.out.println(employee);
          temp.add(employee);
        }
      }
      return temp;
    } catch (Exception e) {
      System.err.println("ID search error");
      e.printStackTrace();
      e.getCause();
    }
    return null;
  }

  //TODO: check
  // need to get ID for updating
  public void updateEmployeeFirstName( Integer ID, String firstName ){
    session = factory.openSession();
    transaction = null;

    try{
      transaction = session.beginTransaction();
      Employee employee = (Employee) session.get(Employee.class, ID);
      employee.setFirstName(firstName);
      session.update(employee);
      transaction.commit();
    }
    catch ( HibernateException h ){
      if( transaction!=null ) transaction.rollback();
      h.printStackTrace();
    }
    finally {
      session.close();
    }
  }
  public void updateEmployeeSurName( Integer ID, String surName ){
    session = factory.openSession();
    transaction = null;

    try{
      transaction = session.beginTransaction();
      Employee employee = (Employee) session.get(Employee.class, ID);
      employee.setSurName(surName);
      session.update(employee);
      transaction.commit();
    }
    catch ( HibernateException h ){
      if( transaction!=null ) transaction.rollback();
      h.printStackTrace();
    }
    finally {
      session.close();
    }
  }
  public void updateEmployeeIdDept( Integer ID, int idDept ){
    session = factory.openSession();
    transaction = null;

    try{
      transaction = session.beginTransaction();
      Employee employee = (Employee) session.get(Employee.class, ID);
      employee.setIdDept(idDept);
      session.update(employee);
      transaction.commit();
    }
    catch ( HibernateException h ){
      if( transaction!=null ) transaction.rollback();
      h.printStackTrace();
    }
    finally {
      session.close();
    }
  }
  public void updateEmployeeIdJob( Integer ID, int idJob ){
    session = factory.openSession();
    transaction = null;

    try{
      transaction = session.beginTransaction();
      Employee employee = (Employee) session.get(Employee.class, ID);
      employee.setIdJob(idJob);
      session.update(employee);
      transaction.commit();
    }
    catch ( HibernateException h ){
      if( transaction!=null ) transaction.rollback();
      h.printStackTrace();
    }
    finally {
      session.close();
    }
  }

  public void updateDepartamentAddress( Integer ID, String address ){
    session = factory.openSession();
    transaction = null;

    try{
      transaction = session.beginTransaction();
      Departament departament = (Departament) session.get(Departament.class, ID);
      departament.setAddress(address);
      session.update(departament);
      transaction.commit();
    }
    catch ( HibernateException h ){
      if( transaction!=null ) transaction.rollback();
      h.printStackTrace();
    }
    finally {
      session.close();
    }
  }
  public void updateDepartamentPhoneNumber( Integer ID, String phoneNumber ){
    session = factory.openSession();
    transaction = null;

    try{
      transaction = session.beginTransaction();
      Departament departament = (Departament) session.get(Departament.class, ID);
      departament.setPhoneNumber(phoneNumber);
      session.update(departament);
      transaction.commit();
    }
    catch ( HibernateException h ){
      if( transaction!=null ) transaction.rollback();
      h.printStackTrace();
    }
    finally {
      session.close();
    }
  }
  public void updateDepartamentEMail( Integer ID, String eMail ){
    session = factory.openSession();
    transaction = null;

    try{
      transaction = session.beginTransaction();
      Departament departament = (Departament) session.get(Departament.class, ID);
      departament.seteMail(eMail);
      session.update(departament);
      transaction.commit();
    }
    catch ( HibernateException h ){
      if( transaction!=null ) transaction.rollback();
      h.printStackTrace();
    }
    finally {
      session.close();
    }
  }

  public void updateJobName( Integer ID, String name ){
    session = factory.openSession();
    transaction = null;

    try{
      transaction = session.beginTransaction();
      Job job = (Job) session.get(Job.class, ID);
      job.setName(name);
      session.update(job);
      transaction.commit();
    }
    catch ( HibernateException h ){
      if( transaction!=null ) transaction.rollback();
      h.printStackTrace();
    }
    finally {
      session.close();
    }
  }
  public void updateJobSalary( Integer ID, int salary ){
    session = factory.openSession();
    transaction = null;

    try{
      transaction = session.beginTransaction();
      Job job = (Job) session.get(Job.class, ID);
      job.setSalary(salary);
      session.update(job);
      transaction.commit();
    }
    catch ( HibernateException h ){
      if( transaction!=null ) transaction.rollback();
      h.printStackTrace();
    }
    finally {
      session.close();
    }
  }

  //TODO: check
  // need to find ID for deletion matching data in GUI, then give this method his ID
  public void deleteEmployee( Integer ID ){
    session = factory.openSession();
    transaction = null;
    try{
      transaction = session.beginTransaction();
      Employee employee = (Employee) session.get(Employee.class, ID);
    }
    catch ( HibernateException h ){
      if (transaction!=null) transaction.rollback();
      h.printStackTrace();
    }
    finally {
      session.close();
    }
  }
  public void deleteDepartament( Integer ID ){
    session = factory.openSession();
    transaction = null;
    try{
      transaction = session.beginTransaction();
      Departament departament = (Departament) session.get(Departament.class, ID);
    }
    catch ( HibernateException h ){
      if (transaction!=null) transaction.rollback();
      h.printStackTrace();
    }
    finally {
      session.close();
    }
  }
  public void deleteJob( Integer ID ){
    session = factory.openSession();
    transaction = null;
    try{
      transaction = session.beginTransaction();
      Job job = (Job) session.get(Job.class, ID);
    }
    catch ( HibernateException h ){
      if (transaction!=null) transaction.rollback();
      h.printStackTrace();
    }
    finally {
      session.close();
    }
  }

  //TODO: check
  public Integer createEmployee( String firstName, String SurName, int idDept, int idJob ){
    session = factory.openSession();
    transaction = null;
    Integer EmployeeID = null;

    try{
      Employee employee = new Employee();
      employee.setFirstName(firstName);
      employee.setSurName(SurName);
      employee.setIdDept(idDept);
      employee.setIdJob(idJob);
      EmployeeID = (Integer) session.save(employee);
      transaction.commit();
    }
    catch ( HibernateException h ){
      if(transaction!=null) transaction.rollback();
      h.printStackTrace();
    }
    finally {
      session.close();
    }

    return EmployeeID;
  }
  public Integer createDepartament( String address, String phoneNumber, String eMail ){
    session = factory.openSession();
    transaction = null;
    Integer DepartamentID = null;

    try{
      Departament departament = new Departament();
      departament.setAddress(address);
      departament.setPhoneNumber(phoneNumber);
      departament.seteMail(eMail);
      DepartamentID = (Integer) session.save(departament);
      transaction.commit();
    }
    catch ( HibernateException h ){
      if(transaction!=null) transaction.rollback();
      h.printStackTrace();
    }
    finally {
      session.close();
    }
    return DepartamentID;
  }
  public Integer createJob( String name, int baseSalary ){
    session = factory.openSession();
    transaction = null;
    Integer JobID = null;

    try{
      Job job = new Job();
      job.setName(name);
      job.setSalary(baseSalary);
      JobID = (Integer) session.save(job);
      transaction.commit();
    }
    catch ( HibernateException h ){
      if(transaction!=null) transaction.rollback();
      h.printStackTrace();
    }
    finally {
      session.close();
    }
    return JobID;
  }
}
