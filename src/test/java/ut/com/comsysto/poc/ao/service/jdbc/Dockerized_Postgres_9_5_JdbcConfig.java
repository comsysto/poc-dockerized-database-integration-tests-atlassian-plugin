package ut.com.comsysto.poc.ao.service.jdbc;

import net.java.ao.test.jdbc.AbstractJdbcConfiguration;

public class Dockerized_Postgres_9_5_JdbcConfig extends AbstractJdbcConfiguration {
  // DOCKER STUFF
  public static final String DOCKER_RUN_COMMAND = "docker run " +
    "--name postgres95 " +
    "-d --rm " +
    "-p 5441:5432 " +
    "-e POSTGRES_PASSWORD=jira " +
    "-e POSTGRES_USER=jira " +
    "-e POSTGRES_DB=jira " +
    "postgres:9.5";

  public static final String DOCKER_GET_ID_COMMAND = "docker ps -q --filter=\"NAME=postgres95\"";

  // JIRA INTERNAL
  public static final String DEFAULT_SCHEMA = "public";
  public static final String DEFAULT_URL = "jdbc:postgresql://localhost:5441/jira";

  public Dockerized_Postgres_9_5_JdbcConfig() {
    super("jdbc:postgresql://localhost:5441/jira", "jira", "jira", "public");
  }

  public Dockerized_Postgres_9_5_JdbcConfig(String url, String username, String password, String schema) {
    super(url, username, password, schema);
  }

  protected String getDefaultSchema() {
    return "public";
  }

  protected String getDefaultUrl() {
    return "jdbc:postgresql://localhost:5441/jira";
  }
}
