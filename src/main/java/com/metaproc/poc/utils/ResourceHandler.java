package com.metaproc.poc.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import org.apache.commons.io.FileUtils;

public class ResourceHandler {

  String JGROUPS_CONFIG = "global-jgroups-config.xml";
  String DATAGRID_LOCAL_STORAGE = "datagrid";

  public String getPathToJGroupsConfig(String id) throws IOException {

    String pathToJGroupsConfig = Paths.get(System.getProperty("user.dir"), "target", "nodes", id, JGROUPS_CONFIG).toString();
    File targetFile = new File(pathToJGroupsConfig);

    if(!targetFile.exists()) {
      FileUtils.copyInputStreamToFile(
          this.getClass().getResourceAsStream("/" + JGROUPS_CONFIG),
          new File(pathToJGroupsConfig));
    }

    return pathToJGroupsConfig;
  }

  public String getPersistenceLocation(String id) {
    return Paths.get(System.getProperty("user.dir"), "target", "nodes", id, DATAGRID_LOCAL_STORAGE).toString();
//    return Paths.get(".", "target", "nodes", id, DATAGRID_LOCAL_STORAGE).toString();
  }

  public String getCachePersistenceLocation(String id, String cacheName) {
    return Paths.get(getPersistenceLocation(id), cacheName).toString();
  }

}
