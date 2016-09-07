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
import java.util.List;
import java.util.Map;

/**
 * Creates pom from templates in NetRelay and extending projects
 * 
 * @author Michael Remme
 * 
 */
public class Main_POMCreator_NetRelay {

  public static final String NETRELAY_VERSION = Main_POMCreator_pojo_mapper.createVersion("10.2.0");
  public static final String NETRELAY_CONTROLLER_VERSION = Main_POMCreator_pojo_mapper.createVersion("1.2.0");
  public static final String AUTOBOB_VERSION = Main_POMCreator_pojo_mapper.createVersion("1.2.0");
  public static final String THYMELEAF_VERSION = "3.0.1.RELEASE";
  public static final String NETRELAY_CMS_VERSION = "1.0.0";
  public static final String DATASTORE_AUTH = "1.0.0";

  private static List<String> projectList = new ArrayList<>();

  static {
    Main_POMCreator_pojo_mapper.SNAPSHOT = true;

    projectList.add("NetRelay");
    projectList.add("NetRelay-Controller");
    projectList.add("NetRelay-DemoProject");
    projectList.add("NetRelay-PdfController");
    projectList.add("autobob");
    projectList.add("CreditReform");
    projectList.add("CreditReformVerticle");
    projectList.add("NetRelay-CMS");
  }

  public static Map<String, String> createReplacer() {
    Map<String, String> replacer = Main_POMCreator_pojo_mapper.createReplacer();
    replacer.put("${NETRELAY_VERSION}", NETRELAY_VERSION);
    replacer.put("${NETRELAY_CONTROLLER_VERSION}", NETRELAY_CONTROLLER_VERSION);
    replacer.put("${NETRELAY_PDF_CONTROLLER_VERSION}", NETRELAY_CONTROLLER_VERSION);
    replacer.put("${NETRELAY_DEMO_VERSION}", NETRELAY_CONTROLLER_VERSION);
    replacer.put("${CREDITREFORM_VERSION}", NETRELAY_CONTROLLER_VERSION);
    replacer.put("${CREDITREFORM_VERTICLE_VERSION}", NETRELAY_CONTROLLER_VERSION);
    replacer.put("${AUTOBOB_VERSION}", AUTOBOB_VERSION);
    replacer.put("${THYMELEAF_VERSION}", THYMELEAF_VERSION);
    replacer.put("${NETRELAY_CMS_VERSION}", NETRELAY_CMS_VERSION);
    replacer.put("${DATASTORE_AUTH}", DATASTORE_AUTH);
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
