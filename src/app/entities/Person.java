package app.entities;

import app.enums.Role;

import java.time.LocalDate;
import java.util.Set;

public class Person {

    private long personID;
    private String name;
    private Set<Role> roles;
    private LocalDate birthDay;
    private String countryFrom;

    public Person(String name, Set<Role> roles, LocalDate birthDay, String countryFrom) {
        this.name = name;
        this.roles = roles;
        this.birthDay = birthDay;
        this.countryFrom = countryFrom;
        this.personID = this.hashCode();
    }

    public Person(long id, String name, Set<Role> roles, LocalDate birthDay, String countryFrom) {
        this.personID = id;
        this.name = name;
        this.roles = roles;
        this.birthDay = birthDay;
        this.countryFrom = countryFrom;
    }

    public long getPersonID() {
        return personID;
    }

    public void setPersonID(long personID) {
        this.personID = personID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public String getCountryFrom() {
        return countryFrom;
    }

    public void setCountryFrom(String countryFrom) {
        this.countryFrom = countryFrom;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Person{");
        sb.append("ID=").append(personID);
        sb.append(", name='").append(name).append('\'');
        sb.append(", roles=").append(roles);
        sb.append(", birth=").append(birthDay);
        sb.append(", from='").append(countryFrom).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
