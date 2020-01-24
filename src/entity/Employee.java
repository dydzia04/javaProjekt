package entity;

import javax.persistence.*;

@Entity
@Table(name = "Employees")
public class Employee {

  @Id @GeneratedValue
  @Column(name = "idEmployees")
  private int id;

  @Column(name = "firstName")
  private String firstName;

  @Column(name = "surName")
  private String surName;

  @Column(name = "idDept")
  private int idDept;

  @Column(name = "idJob")
  private int idJob;

  public Employee(){}

  public Employee( String firstName, String surName, int idDept, int idJob) {
    this.firstName = firstName;
    this.surName = surName;
    this.idDept = idDept;
    this.idJob = idJob;
  }

  public int getId() { return id; }
  public void setId(int id) { this.id = id; }

  public String getFirstName(){
    return this.firstName;
  }
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getSurName() {
    return surName;
  }
  public void setSurName(String surName) {
    this.surName = surName;
  }

  public int getIdDept() { return idDept; }
  public void setIdDept(int idDept) { this.idDept = idDept; }

  public int getIdJob() { return idJob; }
  public void setIdJob(int idJob) { this.idJob = idJob; }

  @Override
  public String toString() {
    return "Employee{" +
        "id=" + id +
        ", firstName='" + firstName + '\'' +
        ", surName='" + surName + '\'' +
        '}';
  }
}
