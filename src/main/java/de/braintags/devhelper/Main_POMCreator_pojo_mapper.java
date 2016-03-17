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

import java.util.HashMap;
import java.util.Map;

/**
 * Creates pom from templates in pojo-mapper and all projects under
 * 
 * @author Michael Remme
 * 
 */
public class Main_POMCreator_pojo_mapper {

  /**
   * 
   */
  public Main_POMCreator_pojo_mapper() {
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    Map<String, String> replacer = new HashMap<>();
    replacer.put("${BT_BASE_VERSION}", "13_SNAPSHOT");
    replacer.put("${BT_VERTX_VERSION}", "1.1.0_SNAPSHOT");

    POMCreator.handleProjectPath(replacer, "BtBase");
    POMCreator.handleProjectPath(replacer, "BtVertxBase");
    POMCreator.handleProjectPath(replacer, "vertx-util");
    POMCreator.handleProjectPath(replacer, "vertx-key-generator");
    POMCreator.handleProjectPath(replacer, "vertx-pojo-mapper");
    POMCreator.handleProjectPath(replacer, "vertx-pojo-mapper/vertx-pojo-mapper-common");
    POMCreator.handleProjectPath(replacer, "vertx-pojo-mapper/vertx-pojo-mapper-common-test");
    POMCreator.handleProjectPath(replacer, "vertx-pojo-mapper/vertx-pojo-mapper-json");
    POMCreator.handleProjectPath(replacer, "vertx-pojo-mapper/vertx-pojo-mapper-mysql");
    POMCreator.handleProjectPath(replacer, "vertx-pojo-mapper/vertx-pojongo");
  }

}
