package de.braintags.devhelper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.Status;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.errors.NoWorkTreeException;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.PushResult;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

/**
 * 
 * 
 * @author Michael Remme
 * 
 */
public class GitUtil {
  private static final Log LOGGER = LogFactory.getLog(GitUtil.class);

  /**
   * 
   */
  private GitUtil() {
  }

  /**
   * Open an existing repository
   * 
   * @param path
   *          path to the .git file
   * @return
   * @throws IOException
   */
  public static Repository openRepository(String path) throws IOException {
    File file = new File(path);
    return new FileRepositoryBuilder().setGitDir(file).build();
  }

  /**
   * Deletes the tag from the local repository
   * 
   * @param repo
   * @param tagName
   * @throws NoWorkTreeException
   * @throws GitAPIException
   */
  public static void deleteTag(Repository repo, String tagName) throws NoWorkTreeException, GitAPIException {
    if (!repoExists(repo)) {
      throw new IllegalArgumentException("Repository does not exist: " + repo.getDirectory().getAbsolutePath());
    }
    Objects.requireNonNull(tagName, "The tagname must not be null");
    checkClean(repo);
    Git git = new Git(repo);
    List<String> res = git.tagDelete().setTags(tagName).call();
    LOGGER.info("Deleted tag result: " + res);
  }

  /**
   * Returns true if the repository exists
   * 
   * @param repo
   * @return
   */
  public static final boolean repoExists(Repository repo) {
    return repo.getDirectory().exists();
  }

  /**
   * Creates a new tag and pushs it
   * 
   * @param repo
   * @param cp
   * @param tagName
   * @throws NoWorkTreeException
   * @throws GitAPIException
   */
  public static void createTag(Repository repo, CredentialsProvider cp, String tagName)
      throws NoWorkTreeException, GitAPIException {
    if (!repoExists(repo)) {
      throw new IllegalArgumentException("Repository does not exist: " + repo.getDirectory().getAbsolutePath());
    }
    Objects.requireNonNull(tagName, "The tagname must not be null");
    checkClean(repo);
    Git git = new Git(repo);
    LOGGER.info("repo is clean: " + repo.getWorkTree().getName());
    List<Ref> tags = git.tagList().call();
    for (Ref ref : tags) {
      LOGGER.info("Existing tag: " + ref.getName());
      if (ref.getName().endsWith("/" + tagName)) {
        throw new IllegalArgumentException("tag with name '" + tagName + "' already exists in repository "
            + repo.getWorkTree().getName() + ": " + ref.getName());
      }
    }
    Ref tagRef = git.tag().setName(tagName).call();
    LOGGER.info("Created tag: " + tagRef.getName());
    Iterable<PushResult> results = git.push().setCredentialsProvider(cp).add(tagRef).call();
    for (PushResult res : results) {
      LOGGER.info("result: " + res.toString());
    }
    LOGGER.info("Tag successfully created and pushed for " + repo.getWorkTree().getName());
  }

  /**
   * Checks wether the given repo is clean, throws an IllegalStateException if not
   * 
   * @param repo
   * @throws NoWorkTreeException
   * @throws GitAPIException
   */
  public static void checkClean(Repository repo) throws NoWorkTreeException, GitAPIException {
    if (!repoExists(repo)) {
      throw new IllegalArgumentException("Repository does not exist: " + repo.getDirectory().getAbsolutePath());
    }
    Git git = new Git(repo);
    Status status = git.status().call();
    if (!status.isClean()) {
      throw new IllegalStateException(
          "The repository must be clean to create a new tag: " + repo.getWorkTree().getName());
    }
  }

  public static void main(String[] args) throws Exception {
    CredentialsProvider cp = new UsernamePasswordCredentialsProvider("remi128", "qdmaha3t");
    String tagName = "testtag";
    Repository repo = openRepository("../TestFileUpload/.git");
    LOGGER.info(repo.getRepositoryState());
    deleteTag(repo, tagName);
    createTag(repo, cp, tagName);

  }
}
