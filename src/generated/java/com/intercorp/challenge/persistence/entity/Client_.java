package com.intercorp.challenge.persistence.entity;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Client.class)
public abstract class Client_ extends com.intercorp.challenge.persistence.entity.DatabaseObject_ {

	public static volatile SingularAttribute<Client, String> lastName;
	public static volatile SingularAttribute<Client, String> name;
	public static volatile SingularAttribute<Client, LocalDate> birthDate;
	public static volatile SingularAttribute<Client, Integer> age;

	public static final String LAST_NAME = "lastName";
	public static final String NAME = "name";
	public static final String BIRTH_DATE = "birthDate";
	public static final String AGE = "age";

}

