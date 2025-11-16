package org.example;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class Person extends PanacheEntity {

    public String name;
    public int age;

}
