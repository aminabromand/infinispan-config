package com.metaproc.poc.infinispan;

import com.metaproc.poc.model.AuthorHibernate;
import com.metaproc.poc.model.BookHibernate;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import org.infinispan.Cache;
//import org.infinispan.query.Search;
import org.junit.Ignore;
import org.junit.Test;

public class InfinispanServiceTest {

  @Ignore
  @Test
  public void testBookCache() throws InterruptedException, IOException {

    InfinispanService infinispanService = new InfinispanService(1);

    infinispanService.activateManager();
    Cache<Integer, BookHibernate> cache = infinispanService.getBookCache();

    putTestData(cache);
//    queryTestData(cache);
//    queryTestDataIterator(cache);
    queryTestDataStream(cache);

    infinispanService.deactivate();

  }

//  public void queryTestData(Cache<Integer, BookHibernate> cache) {
//    System.out.println("###");
//    Search.getQueryFactory(cache).create("from com.metaproc.poc.model.BookHibernate b where b.id = 1").list().forEach(System.out::println);
//    System.out.println("###");
//    Search.getQueryFactory(cache).create("from com.metaproc.poc.model.BookHibernate b where b.authors.name = 'Stephen'").list().forEach(System.out::println);
//    System.out.println("###");
//    Search.getQueryFactory(cache).create("from com.metaproc.poc.model.BookHibernate b where b.authors.name = 'Stephen' and b.description : (-'tower')").list().forEach(System.out::println);
//    System.out.println("###");
//  }
//
//  public void queryTestDataIterator(Cache<Integer, BookHibernate> cache) {
//    System.out.println("###");
//    for (Object o : Search.getQueryFactory(cache)
//        .create("from com.metaproc.poc.model.BookHibernate b where b.authors.name = 'Stephen'")) {
//      BookHibernate book = (BookHibernate) o;
//      System.out.println(book);
//    }
//    System.out.println("###");
//  }

  public void queryTestDataStream(Cache<Integer, BookHibernate> cache) {
    System.out.println("###");
    cache.entrySet().stream().filter(e -> e.getValue().getDescription().contains("Dark")).forEach(System.out::println);
    System.out.println("###");
  }

  public void putTestData(Cache<Integer, BookHibernate> cache) {

    AuthorHibernate author1 = new AuthorHibernate();
    author1.setName("Stephen");
    author1.setSurname("King");

    AuthorHibernate author2 = new AuthorHibernate();
    author2.setName("Rui");
    author2.setSurname("Oliveira");

    Set<AuthorHibernate> authors1 = new HashSet<>();
    authors1.add(author1);

    Set<AuthorHibernate> authors2 = new HashSet<>();
    authors2.add(author2);

    Date now = new Date();

    BookHibernate book1 = new BookHibernate();
    book1.setAuthors(authors1);
    book1.setId(1);
    book1.setTitle("The Dark Half");
    book1.setDescription("The Dark Half");

    BookHibernate book2 = new BookHibernate();
    book2.setId(2);
    book2.setAuthors(authors2);
    book2.setTitle("SQL Engine");
    book2.setDescription("SQL Engine a book on highly scalable query engines test");

    BookHibernate book3 = new BookHibernate();
    book3.setId(3);
    book3.setAuthors(authors1);
    book3.setTitle("The Dark Tower");
    book3.setDescription("The Dark Tower");
    book1.setAuthors(authors1);

    cache.put(book1.getId(), book1);
    cache.put(book2.getId(), book2);
    cache.put(book3.getId(), book3);

  }
}