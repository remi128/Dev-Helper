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
 * Creates pom from templates in NetRelay and extending projects
 * 
 * @author Michael Remme
 * 
 */
public class Main_POMCreator_NetRelay {
  public static List<String> projectList = new ArrayList();

  static {
    projectList.add("NetRelay");
    projectList.add("NetRelay-Controller");
    projectList.add("NetRelay-DemoProject");
    projectList.add("NetRelay-PdfController");
    projectList.add("autobob-webapp");
    projectList.add("NetRelay");
    projectList.add("CreditReformVerticle");
  }

  /**
   * 
   */
  public Main_POMCreator_NetRelay() {
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    Map<String, String> replacer = new HashMap<>();
    replacer.put("${BT_BASE_VERSION}", Main_POMCreator_pojo_mapper.BTBASE_VERSION);
    replacer.put("${BT_VERTX_VERSION}", Main_POMCreator_pojo_mapper.BT_VERTX_VERSION);
    replacer.put("${NETRELAY_VERSION}", "10.0.0-SNAPSHOT");
    replacer.put("${NETRELAY_CONTROLLER_VERSION}", "1.0.0-SNAPSHOT");
    replacer.put("${NETRELAY_PDF_CONTROLLER_VERSION}", "1.0.0-SNAPSHOT");

    for (String project : projectList) {
      POMCreator.handleProjectPath(replacer, project);
    }
  }

}
