package ut.com.comsysto.poc.ao.service.jdbc;

import net.java.ao.test.jdbc.AbstractJdbcConfiguration;

public class Dockerized_MySQL_5_6_JdbcConfig extends AbstractJdbcConfiguration {
  // DOCKER STUFF
  public static final String DOCKER_RUN_COMMAND = "docker run " +
    "--name mysql56 " +
    "-d --rm " +
    "-p 3316:3306 " +
    "-e MYSQL_RANDOM_ROOT_PASSWORD=true " +
    "-e MYSQL_USER=jira " +
    "-e MYSQL_PASSWORD=jira " +
    "-e MYSQL_DATABASE=jira " +
    "mysql:5.6";

  public static final String DOCKER_GET_ID_COMMAND = "docker ps -q --filter=\"NAME=mysql56\"";

  // JIRA INTERNAL
  public static final String DEFAULT_URL = "jdbc:mysql://localhost:3316/jira?autoReconnect=true";

  public Dockerized_MySQL_5_6_JdbcConfig() {
    super("jdbc:mysql://localhost:3316/jira?autoReconnect=true", "jira", "jira", (String)null);
  }

  public Dockerized_MySQL_5_6_JdbcConfig(String url, String username, String password, String schema) {
    super(url, username, password, schema);
  }

  protected String getDefaultSchema() {
    return null;
  }

  protected String getDefaultUrl() {
    return "jdbc:mysql://localhost:3316/jira?autoReconnect=true";
  }
}
