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
  private static List<String> projectList = new ArrayList<>();

  static {
    projectList.add("NetRelay");
    projectList.add("NetRelay-Controller");
    projectList.add("NetRelay-DemoProject");
    projectList.add("NetRelay-PdfController");
    projectList.add("autobob");
    projectList.add("CreditReformVerticle");
  }

  public static Map<String, String> createReplacer() {
    Map<String, String> replacer = Main_POMCreator_pojo_mapper.createReplacer();
    replacer.put("${NETRELAY_VERSION}", "10.0.0-SNAPSHOT");
    replacer.put("${NETRELAY_CONTROLLER_VERSION}", "1.0.0-SNAPSHOT");
    replacer.put("${NETRELAY_PDF_CONTROLLER_VERSION}", "1.0.0-SNAPSHOT");
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
