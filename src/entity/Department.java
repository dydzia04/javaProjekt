package entity;

import javax.persistence.*;

@Entity
@Table(name = "Departaments")
public class Department {

  @Id
  @GeneratedValue
  @Column(name = "idDepartaments")
  private int id;

  @Column(name = "address")
  private String address;

  @Column(name = "phoneNumber")
  private String phoneNumber;

  @Column(name = "eMail")
  private String eMail;

  public Department() {
  }

  public Department(String address, String phoneNumber, String eMail) {
    this.address = address;
    this.phoneNumber = phoneNumber;
    this.eMail = eMail;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String geteMail() {
    return eMail;
  }

  public void seteMail(String eMail) {
    this.eMail = eMail;
  }

  @Override
  public String toString() {
    return "Departament{" +
        "id=" + id +
        ", address='" + address + '\'' +
        ", phoneNumber=" + phoneNumber +
        ", eMail='" + eMail + '\'' +
        '}';
  }
}
