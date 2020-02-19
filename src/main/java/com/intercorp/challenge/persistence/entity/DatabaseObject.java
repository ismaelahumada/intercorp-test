package com.intercorp.challenge.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter @Setter
public abstract class DatabaseObject {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    protected Long id;

    public DatabaseObject() {
    }

}
