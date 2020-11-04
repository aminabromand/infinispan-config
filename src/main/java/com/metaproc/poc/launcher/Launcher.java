package com.metaproc.poc.launcher;

import com.metaproc.poc.infinispan.InfinispanService;
import com.metaproc.poc.infinispan.InfinispanServiceFactory;
import com.metaproc.poc.model.BookHibernate;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import org.infinispan.Cache;

public class Launcher {

  public static void main(String[] args)
      throws InterruptedException, IOException {

    InfinispanServiceFactory infinispanServiceFactory = new InfinispanServiceFactory();

    infinispanServiceFactory.transportConfigActive = true;
    infinispanServiceFactory.serializationConfigActive = true;
    infinispanServiceFactory.globalStateConfigActive = true;

    infinispanServiceFactory.clusteringCacheConfigActive = true;
    infinispanServiceFactory.indexingCacheConfigActive = false;
    infinispanServiceFactory.persistenceCacheConfigActive = true;
    infinispanServiceFactory.filePersistenceCacheConfigActive = false;
    infinispanServiceFactory.simpleTransactionCacheConfigActive = false;
    infinispanServiceFactory.transactionCacheConfigActive = false;
    infinispanServiceFactory.batchingCacheConfigActive = false;
    infinispanServiceFactory.isolationCacheConfigActive = false;

    Scanner sc = new Scanner(System.in);
    System.out.print("InfinispanServerId (int): ");
    int id = sc.nextInt();
    sc.nextLine();

    InfinispanService infinispanService = infinispanServiceFactory.build(id);
    Thread.sleep(3000);

    Cache<String, String> stringCache = infinispanService.getStringCache();
    queryStringCache(stringCache);

    Thread.sleep(3000);

    System.out.print("put cacheEntryId (String): ");
    String putCacheEntryId = sc.nextLine();
    stringCache.put(putCacheEntryId, "value-" + putCacheEntryId);

    System.out.print("read CacheEntryId (String): ");
    String readCacheEntryId = sc.nextLine();
    System.out.println("$$$ query cache for " + readCacheEntryId + ": " + stringCache.get(readCacheEntryId));

    System.out.print("deactivate cache now? (true/false): ");
    boolean deactivate1 = sc.nextBoolean();
    sc.nextLine();

    if(deactivate1) {
      infinispanService.deactivate();
      Thread.sleep(3000);
      System.out.print("#### press enter to activate");
      sc.nextLine();
      infinispanService.activateManager();
      Thread.sleep(3000);
      infinispanService.activateStringCache();
      Thread.sleep(3000);
      System.out.println("#### query after restart");
      stringCache = infinispanService.getStringCache();
      queryStringCache(stringCache);
    } else {
      Thread.sleep(3000);
    }

    System.out.print("put cacheEntryId (String): ");
    String putCacheEntryId2 = sc.nextLine();
    stringCache.put(putCacheEntryId2, "value-" + putCacheEntryId2);

    System.out.print("read CacheEntryId (String): ");
    String readCacheEntryId2 = sc.nextLine();
    System.out.println("$$$ query cache for " + readCacheEntryId2 + ": " + stringCache.get(readCacheEntryId2));


    System.out.print("deactivate cache now? (true/false): ");
    boolean deactivate2 = sc.nextBoolean();
    sc.nextLine();

    if(deactivate2) {
      Thread.sleep(3000);
      infinispanService.deactivate();
      System.out.print("#### press enter to activate");
      sc.nextLine();
      infinispanService.activateManager();
      Thread.sleep(3000);
      infinispanService.activateStringCache();
      Thread.sleep(3000);
      System.out.println("#### query after restart");
      stringCache = infinispanService.getStringCache();
      queryStringCache(stringCache);
    } else {
      Thread.sleep(3000);
    }


    System.out.print("put cacheEntryId (String): ");
    String putCacheEntryId3 = sc.nextLine();
    stringCache.put(putCacheEntryId3, "value-" + putCacheEntryId3);

    System.out.print("read CacheEntryId (String): ");
    String readCacheEntryId3 = sc.nextLine();
    System.out.println("$$$ query cache for " + readCacheEntryId3 + ": " + stringCache.get(readCacheEntryId3));

    infinispanService.deactivate();

    Thread.sleep(3000);

  }

  public static void queryStringCache(Cache<String, String> cache) {
    System.out.println("###");
    cache.entrySet().forEach(System.out::println);
    System.out.println("###");
  }

//  public static void testTransaction(Cache<String, String> cache)
//      throws SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException {
//    cache.getAdvancedCache().getTransactionManager().begin();
//    cache.remove("id2");
//    cache.put("id2", "removed and added value2");
//    cache.getAdvancedCache().getTransactionManager().commit();
//  }

  public static void testBatch(Cache<String, String> cache) {
    cache.startBatch();
    cache.remove("id3");
    cache.put("id3", "removed and added value3");
    cache.endBatch(true);
  }



  public static void testBookData(InfinispanService infinispanService) throws InterruptedException {
    Cache<Integer, BookHibernate> cache = infinispanService.getBookCache();

    Thread.sleep(3000);
    putTestData(cache);
    Thread.sleep(3000);
    putTestData(cache);
    Thread.sleep(3000);
    putTestData(cache);
    Thread.sleep(3000);
    queryTestDataStream(cache);
    Thread.sleep(3000);
  }

  public static void putTestData(Cache<Integer, BookHibernate> cache) {

    int max = 1000;
    int min = 0;
    Random rn = new Random();
    int randNumber = rn.nextInt(max - min + 1) + min;

    System.out.println("###: " + randNumber);
    BookHibernate book1 = new BookHibernate();
    book1.setId(1);
    book1.setTitle("Titel: " + randNumber);
    book1.setDescription("Description: " + randNumber);
    cache.put(randNumber, book1);
    System.out.println("###");
  }

  public static void queryTestDataStream(Cache<Integer, BookHibernate> cache) {
    System.out.println("###");
    cache.entrySet().forEach(System.out::println);
    System.out.println("###");
  }

}
