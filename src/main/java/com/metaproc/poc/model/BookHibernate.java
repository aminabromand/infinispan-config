package com.metaproc.poc.model;

import java.util.HashSet;
import java.util.Set;
//import org.hibernate.search.annotations.Field;
//import org.hibernate.search.annotations.Indexed;
//import org.hibernate.search.annotations.IndexedEmbedded;

//@Indexed
public class BookHibernate {

//  @Field
  int id;

//  @Field
  String title;

//  @Field
  String description;

//  @IndexedEmbedded
  Set<AuthorHibernate> authors = new HashSet<>();

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Set<AuthorHibernate> getAuthors() {
    return authors;
  }

  public void setAuthors(Set<AuthorHibernate> authors) {
    this.authors = authors;
  }

  public BookHibernate() {}

  public BookHibernate(int id, String title, String description, Set<AuthorHibernate> authors) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.authors = authors;
  }

  @Override
  public String toString() {
    return "Book{" +
        "id=" + id +
        ", title='" + title + '\'' +
        ", description=" + description +
        ", authors=" + authors.size() +
        '}';
  }
}
