package entity;


import javax.persistence.*;

@Entity
@Table(name = "Jobs")
public class Job {

  @Id @GeneratedValue
  @Column(name = "idJobs")
  private int id;

  @Column(name = "name")
  private String name;

  @Column(name = "baseSalary")
  private int salary;

  public int getId() { return id; }
  public void setId(int id) { this.id = id; }

  public int getSalary() { return salary; }
  public void setSalary(int salary) { this.salary = salary; }

  public String getName() { return name; }
  public void setName(String name) { this.name = name; }

  @Override
  public String toString() {
    return "Job{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", salary=" + salary +
            '}';
  }
}
