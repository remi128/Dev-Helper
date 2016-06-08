package de.braintags.devhelper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;

/**
 * Used to create and modify pom files from templates inside a project. Can be used to prepare releases and snapshot
 * versions
 * 
 * @author mremme
 * 
 */
public class POMCreator {
  private static final Logger LOGGER = Logger.getLogger(POMCreator.class.getName());

  private static final String BAK_DIR = "pombak";
  private static final String TEMPLATE_SUFFIX = ".tpl.xml";
  private static final String POM_SUFFIX = ".xml";
  private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

  /**
   * 
   */
  private POMCreator() {
  }

  /**
   * This method expects, that the application is started from Dev-Helper and searches the project with the given name
   * inside a parallel directory
   * 
   * @param replacer
   *          key / value pairs to be replaced
   * @param projectName
   *          the name of the project directory to be handled
   */
  public static void handleProjectPath(Map<String, String> replacer, String projectName) {
    String path = new File("").getAbsolutePath();
    File parent = new File(path).getParentFile();
    String dirPath = new File(parent, projectName).getAbsolutePath();
    handleProjectPath(dirPath, replacer);
  }

  /**
   * Use a directory, search for existing template files ( {@value #TEMPLATE_SUFFIX} ) and create new poms from it, by
   * replacing placeholders
   * 
   * @param dirPath
   *          the directory to search for templates
   * @param replacer
   *          key / value pairs to be replaced
   */
  public static void handleProjectPath(String dirPath, Map<String, String> replacer) {
    File file = new File(dirPath);
    LOGGER.info("handling project dir " + dirPath);
    if (file.exists() && file.isDirectory()) {
      handleTemplates(file, replacer);
    } else {
      throw new IllegalArgumentException("path does not exist or is no directory: " + file.getAbsolutePath());
    }

  }

  private static void handleTemplates(File dir, Map<String, String> replacer) {
    List<File> fl = Arrays.asList(dir.listFiles());
    Iterator<File> it = fl.stream().filter(file -> file.getName().endsWith(TEMPLATE_SUFFIX)).iterator();
    if (!it.hasNext()) {
      LOGGER.info("No templates found in " + dir.getAbsolutePath());
    }
    while (it.hasNext()) {
      handleOneTemplate(it.next(), replacer);
    }
  }

  private static void handleOneTemplate(File tpl, Map<String, String> replacer) {
    try {
      LOGGER.info("handling template " + tpl.getPath());
      String fileContent = readFile(tpl.getAbsolutePath());
      fileContent = replaceContent(fileContent, replacer);
      File dir = tpl.getParentFile();
      String newFileName = getDestinationFileName(tpl);
      moveOldPom(dir, newFileName);
      createNewFile(dir, newFileName, fileContent);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private static void createNewFile(File dir, String newFileName, String content) throws IOException {
    File dest = new File(dir, newFileName);
    if (dest.exists()) {
      throw new IOException("File should not exist and moved into bak before: " + dest.getAbsolutePath());
    }
    Path path = Paths.get(dest.getAbsolutePath());
    LOGGER.info("creating new file " + path.toString());
    Files.createFile(path);
    Files.write(path, content.getBytes());
  }

  private static void moveOldPom(File dir, String newFileName) throws IOException {
    File dest = new File(dir, newFileName);
    if (dest.exists()) {
      File bakDir = new File(dir, BAK_DIR);
      if (!bakDir.exists()) {
        if (!bakDir.mkdirs()) {
          throw new IOException("Could not create directory at " + bakDir.getAbsolutePath());
        }
      }
      String dateStamp = FORMAT.format(new Timestamp(System.currentTimeMillis()));
      File bakFile = new File(bakDir, dateStamp + "_" + newFileName);
      LOGGER.info("moving old file into " + bakFile.getPath());
      Files.move(Paths.get(dest.getAbsolutePath()), Paths.get(bakFile.getAbsolutePath()));
    }
  }

  private static String getDestinationFileName(File tpl) {
    String fileName = tpl.getName();
    int index = fileName.lastIndexOf(TEMPLATE_SUFFIX);
    String newFileName = fileName.substring(0, index) + POM_SUFFIX;
    return newFileName;
  }

  private static String replaceContent(String fileContent, Map<String, String> replacer) {
    Iterator<Entry<String, String>> entries = replacer.entrySet().iterator();
    while (entries.hasNext()) {
      Entry<String, String> entry = entries.next();
      fileContent = fileContent.replace(entry.getKey(), entry.getValue());
    }
    return fileContent;
  }

  private static String readFile(String path) throws IOException {
    byte[] encoded = Files.readAllBytes(Paths.get(path));
    return new String(encoded);
  }
}
