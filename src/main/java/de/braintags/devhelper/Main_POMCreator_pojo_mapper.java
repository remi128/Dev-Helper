/*
 * #%L
 * Dev-Helper
 * %%
 * Copyright (C) 2015 Braintags GmbH
 * %%
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * #L%
 */
package de.braintags.devhelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Creates pom from templates in pojo-mapper and all projects under
 * 
 * @author Michael Remme
 * 
 */
public class Main_POMCreator_pojo_mapper {
  public static boolean SNAPSHOT = true;

  public static final String BTBASE_VERSION = createVersion("19");
  public static final String BT_VERTX_VERSION = createVersion("1.3.2");

  public static final String VERTX_STACK_VERSION = "3.2.1";

  public static List<String> projectList = new ArrayList();

  static {
    projectList.add("BtBase");
    projectList.add("BtVertxBase");
    projectList.add("vertx-util");
    projectList.add("vertx-key-generator");
    projectList.add("vertx-pojo-mapper");
    projectList.add("vertx-pojo-mapper/vertx-pojo-mapper-common");
    projectList.add("vertx-pojo-mapper/vertx-pojo-mapper-common-test");
    projectList.add("vertx-pojo-mapper/vertx-pojo-mapper-json");
    projectList.add("vertx-pojo-mapper/vertx-pojo-mapper-mysql");
    projectList.add("vertx-pojo-mapper/vertx-pojongo");
  }

  public static String createVersion(String version) {
    return version + (SNAPSHOT ? "-SNAPSHOT" : "");
  }

  public static Map<String, String> createReplacer() {
    Map<String, String> replacer = new HashMap<>();
    replacer.put("${BT_BASE_VERSION}", BTBASE_VERSION);
    replacer.put("${BT_VERTX_VERSION}", BT_VERTX_VERSION);
    replacer.put("${VERTX_STACK_VERSION}", VERTX_STACK_VERSION);
    return replacer;
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    Map<String, String> replacer = createReplacer();
    for (String project : projectList) {
      POMCreator.handleProjectPath(replacer, project);
    }
  }

}
