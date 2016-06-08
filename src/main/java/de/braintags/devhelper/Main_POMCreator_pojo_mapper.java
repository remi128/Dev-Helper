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
  public static final String BTBASE_VERSION = "15-SNAPSHOT";
  public static final String BT_VERTX_VERSION = "1.1.0";
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

  /**
   * @param args
   */
  public static void main(String[] args) {
    Map<String, String> replacer = new HashMap<>();
    replacer.put("${BT_BASE_VERSION}", BTBASE_VERSION);
    replacer.put("${BT_VERTX_VERSION}", BT_VERTX_VERSION);

    for (String project : projectList) {
      POMCreator.handleProjectPath(replacer, project);
    }
  }

}
