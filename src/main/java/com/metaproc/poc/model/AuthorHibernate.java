package com.metaproc.poc.model;

//import org.hibernate.search.annotations.Analyze;
//import org.hibernate.search.annotations.Field;
//import org.hibernate.search.annotations.Indexed;

//@Indexed
public class AuthorHibernate {

//  @Field
  int id;

//  @Field(analyze = Analyze.NO)
  String name;

//  @Field
  String surname;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public AuthorHibernate(){}

  public AuthorHibernate(int id, String name, String surname) {
    this.id = id;
    this.name = name;
    this.surname = surname;
  }
}
