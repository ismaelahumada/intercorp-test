package com.intercorp.challenge.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@Getter @Setter
public class Client extends DatabaseObject {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false)
    private LocalDate birthDate;
}
