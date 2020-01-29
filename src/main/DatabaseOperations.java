package main;

import entity.Department;
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
      List departaments = session.createQuery("FROM Department ").list();
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
      List employees = this.getAllEmployees();
      Iterator iterator = employees.listIterator();
      Employee employee;

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
      List employees = this.getAllEmployees();
      Iterator iterator = employees.listIterator();
      Employee employee;

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
      List employees = this.getAllEmployees();
      Iterator iterator = employees.listIterator();
      Employee employee;

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

  public void updateEmployeeFirstName( Integer ID, String firstName ){
    session = factory.openSession();
    transaction = null;

    try{
      transaction = session.beginTransaction();
      Employee employee = session.get(Employee.class, ID);
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
      Employee employee = session.get(Employee.class, ID);
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
      Employee employee = session.get(Employee.class, ID);
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
      Employee employee = session.get(Employee.class, ID);
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

  public void updateDepartmentAddress(Integer ID, String address) {
    session = factory.openSession();
    transaction = null;

    try {
      transaction = session.beginTransaction();
      Department department = session.get(Department.class, ID);
      department.setAddress(address);
      session.update(department);
      transaction.commit();
    } catch (HibernateException h) {
      if (transaction != null) transaction.rollback();
      h.printStackTrace();
    } finally {
      session.close();
    }
  }
  public void updateDepartmentPhoneNumber(Integer ID, String phoneNumber) {
    session = factory.openSession();
    transaction = null;

    try {
      transaction = session.beginTransaction();
      Department department = session.get(Department.class, ID);
      department.setPhoneNumber(phoneNumber);
      session.update(department);
      transaction.commit();
    } catch (HibernateException h) {
      if (transaction != null) transaction.rollback();
      h.printStackTrace();
    } finally {
      session.close();
    }
  }
  public void updateDepartmentEMail(Integer ID, String eMail) {
    session = factory.openSession();
    transaction = null;

    try {
      transaction = session.beginTransaction();
      Department department = session.get(Department.class, ID);
      department.seteMail(eMail);
      session.update(department);
      transaction.commit();
    } catch (HibernateException h) {
      if (transaction != null) transaction.rollback();
      h.printStackTrace();
    } finally {
      session.close();
    }
  }

  public void updateJobName( Integer ID, String name ){
    session = factory.openSession();
    transaction = null;

    try{
      transaction = session.beginTransaction();
      Job job = session.get(Job.class, ID);
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
      Job job = session.get(Job.class, ID);
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

  public void deleteEmployee( Integer ID ){
    session = factory.openSession();
    transaction = null;
    try {
      transaction = session.beginTransaction();
      Employee employee = session.get(Employee.class, ID);
      session.delete(employee);
      transaction.commit();
    } catch (HibernateException h) {
      if (transaction != null) transaction.rollback();
      h.printStackTrace();
    } finally {
      session.close();
    }
  }
  public void deleteDepartment(Integer ID) {
    session = factory.openSession();
    transaction = null;
    try {
      transaction = session.beginTransaction();
      Department department = session.get(Department.class, ID);
      session.delete(department);
      transaction.commit();
    } catch (HibernateException h) {
      if (transaction != null) transaction.rollback();
      h.printStackTrace();
    } finally {
      session.close();
    }
  }
  public void deleteJob( Integer ID ){
    session = factory.openSession();
    transaction = null;
    try {
      transaction = session.beginTransaction();
      Job job = session.get(Job.class, ID);
      session.delete(job);
      transaction.commit();
    } catch (HibernateException h) {
      if (transaction != null) transaction.rollback();
      h.printStackTrace();
    } finally {
      session.close();
    }
  }

  public Integer createEmployee( String firstName, String SurName, int idDept, int idJob ){
    session = factory.openSession();
    transaction = null;
    Integer EmployeeID = null;

    try {
      Employee employee = new Employee();
      employee.setFirstName(firstName);
      employee.setSurName(SurName);
      employee.setIdDept(idDept);
      employee.setIdJob(idJob);
      EmployeeID = (Integer) session.save(employee);
      transaction.commit();
    } catch (HibernateException h) {
      if (transaction != null) transaction.rollback();
      h.printStackTrace();
      h.getCause();
    } finally {
      session.close();
    }

    return EmployeeID;
  }
  public Integer createDepartment(String address, String phoneNumber, String eMail) {
    session = factory.openSession();
    transaction = null;
    Integer DepartmentID = null;

    try {
      Department department = new Department();
      department.setAddress(address);
      department.setPhoneNumber(phoneNumber);
      department.seteMail(eMail);
      DepartmentID = (Integer) session.save(department);
      transaction.commit();
    } catch (HibernateException h) {
      if (transaction != null) transaction.rollback();
      h.printStackTrace();
    } finally {
      session.close();
    }
    return DepartmentID;
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
