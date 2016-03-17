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
 * Creates templates in project vertx-test
 * 
 * @author Michael Remme
 * 
 */
public class Main_POMCreator_Test {

  /**
   * 
   */
  public Main_POMCreator_Test() {
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    Map<String, String> replacer = new HashMap<>();
    replacer.put("${BT_BASE_VERSION}", "13");
    replacer.put("${BT_VERTX_VERSION}", "1.1.0");

    POMCreator.handleProjectPath(replacer, "vertx-test");
  }

}
