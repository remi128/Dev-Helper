package de.braintags.devhelper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

/**
 * Creates a new tag for the defined git projects in here
 * 
 * @author Michael Remme
 * 
 */
public class TaggingAutobob {
  private static final Log LOGGER = LogFactory.getLog(TaggingAutobob.class);
  private static final String USERNAME_PROPERTY = "userName";
  private static final String PASSWORD_PROPERTY = "password";

  private static final String TAGNAME = Main_POMCreator_NetRelay.AUTOBOB_VERSION;

  public static List<String> projectList = new ArrayList();

  static {
    projectList.add("CreditReform");
    projectList.add("CreditReformVerticle");
    projectList.add("autobob");
  }

  /**
   * @param args
   */
  public static void main(String[] args) throws Exception {
    CredentialsProvider cp = createProvider();
    String path = new File("").getAbsolutePath();
    File parentDirectory = new File(path).getParentFile();
    List<Repository> repoList = createRepositories(parentDirectory, projectList);
    for (Repository repo : repoList) {
      GitUtil.checkClean(repo);
    }
    for (Repository repo : repoList) {
      GitUtil.createTag(repo, cp, TAGNAME);
    }
  }

  public static CredentialsProvider createProvider() {
    String userName = System.getProperty(USERNAME_PROPERTY, null);
    Objects.requireNonNull(userName, "You must set the system property " + USERNAME_PROPERTY);
    String password = System.getProperty(PASSWORD_PROPERTY, null);
    Objects.requireNonNull(password, "You must set the system property " + PASSWORD_PROPERTY);
    return new UsernamePasswordCredentialsProvider(userName, password);
  }

  private static List<Repository> createRepositories(File parentDirectory, List<String> projectList)
      throws IOException {
    LOGGER.info("handling projects in directory " + parentDirectory.getAbsolutePath());
    List<Repository> returnList = new ArrayList<>();
    for (String project : projectList) {
      String dirPath = new File(parentDirectory, project + "/.git").getAbsolutePath();
      returnList.add(GitUtil.openRepository(dirPath));
    }
    return returnList;
  }

}
