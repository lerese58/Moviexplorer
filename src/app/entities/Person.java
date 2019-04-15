package app.entities;

import app.enums.Genre;
import app.enums.Role;

import java.time.LocalDate;
import java.util.Set;

public class Person {

    private long personID;
    private Set<Role> roles;
    private LocalDate birthDay;
    private String countryFrom;
    private Set<Genre> genres;
    private int filmCount;
}
