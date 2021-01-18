package com.metaproc.poc.infinispan;

import java.io.IOException;
import org.osgi.service.component.annotations.Component;

@Component(immediate = true, service = InfinispanServiceFactory.class)
public class InfinispanServiceFactory {

  public boolean transportConfigActive = true;
  public boolean serializationConfigActive = true;
  public boolean globalStateConfigActive = true;

  public boolean clusteringCacheConfigActive = true;
  public boolean indexingCacheConfigActive = false;
  public boolean persistenceCacheConfigActive = false;
  public boolean filePersistenceCacheConfigActive = false;
  public boolean softIndexPersistenceCacheConfigActive = false;
  public boolean simpleTransactionCacheConfigActive = false;
  public boolean transactionCacheConfigActive = false;
  public boolean batchingCacheConfigActive = false;
  public boolean isolationCacheConfigActive = false;

  public InfinispanService build(int id) throws IOException {
    InfinispanService infinispanService = new InfinispanService(id);
    return setup(infinispanService);
  }

  public InfinispanService build(int id, int persistenceLocation) throws IOException {
    InfinispanService infinispanService = new InfinispanService(id, persistenceLocation);
    return setup(infinispanService);
  }

  private InfinispanService setup(InfinispanService infinispanService) throws IOException {
    infinispanService.setSerializationConfigActive(serializationConfigActive);
    infinispanService.setTransportConfigActive(transportConfigActive);
    infinispanService.setGlobalStateConfigActive(globalStateConfigActive);

    infinispanService.setClusteringCacheConfigActive(clusteringCacheConfigActive);
    infinispanService.setIndexingCacheConfigActive(indexingCacheConfigActive);
    infinispanService.setPersistenceCacheConfigActive(persistenceCacheConfigActive);
    infinispanService.setFilePersistenceCacheConfigActive(filePersistenceCacheConfigActive);
    infinispanService.setSoftIndexPersistenceCacheConfigActive(softIndexPersistenceCacheConfigActive);
    infinispanService.setSimpleTransactionCacheConfigActive(simpleTransactionCacheConfigActive);
    infinispanService.setTransactionCacheConfigActive(transactionCacheConfigActive);
    infinispanService.setBatchingCacheConfigActive(batchingCacheConfigActive);
    infinispanService.setIsolationCacheConfigActive(isolationCacheConfigActive);

    infinispanService.activateManager();
    infinispanService.activateStringCache();

    return infinispanService;
  }

}
