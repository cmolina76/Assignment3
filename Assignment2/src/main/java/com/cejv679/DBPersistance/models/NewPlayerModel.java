package com.cejv679.DBPersistance.models;

import javax.persistence.*;

/**
 * Created by Carlos Molina.
 */
@Entity
public class NewPlayerModel {
    public NewPlayerModel() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "age")
    private int age;

    @Column(name = "currency")
    private String currency;

    @Column(name = "salary")
    private double salary;

    @Column(name = "country")
    private String country;

    @Column(name = "position")
    private String position;

    @Column(name = "goals")
    private int goals;

    @Column(name = "bookings")
    private int bookings;

    public NewPlayerModel(Long id, String firstname, String lastname, int age, String currency, double salary, String country, String position, int goals, int bookings) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.currency = currency;
        this.salary = salary;
        this.country = country;
        this.position = position;
        this.goals = goals;
        this.bookings = bookings;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getGoals() {
        return goals;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public int getBookings() {
        return bookings;
    }

    public void setBookings(int bookings) {
        this.bookings = bookings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NewPlayerModel newPlayerModel = (NewPlayerModel) o;

        if (age != newPlayerModel.age) return false;
        if (Double.compare(newPlayerModel.salary, salary) != 0) return false;
        if (goals != newPlayerModel.goals) return false;
        if (bookings != newPlayerModel.bookings) return false;
        if (id != null ? !id.equals(newPlayerModel.id) : newPlayerModel.id != null) return false;
        if (firstname != null ? !firstname.equals(newPlayerModel.firstname) : newPlayerModel.firstname != null) return false;
        if (lastname != null ? !lastname.equals(newPlayerModel.lastname) : newPlayerModel.lastname != null) return false;
        if (currency != null ? !currency.equals(newPlayerModel.currency) : newPlayerModel.currency != null) return false;
        if (country != null ? !country.equals(newPlayerModel.country) : newPlayerModel.country != null) return false;
        return !(position != null ? !position.equals(newPlayerModel.position) : newPlayerModel.position != null);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id != null ? id.hashCode() : 0;
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + age;
        result = 31 * result + (currency != null ? currency.hashCode() : 0);
        temp = Double.doubleToLongBits(salary);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (position != null ? position.hashCode() : 0);
        result = 31 * result + goals;
        result = 31 * result + bookings;
        return result;
    }

    @Override
    public String toString() {
        return "NewPlayerModel{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", age=" + age +
                ", currency='" + currency + '\'' +
                ", salary=" + salary +
                ", country='" + country + '\'' +
                ", position='" + position + '\'' +
                ", goals=" + goals +
                ", bookings=" + bookings +
                '}';
    }
}
