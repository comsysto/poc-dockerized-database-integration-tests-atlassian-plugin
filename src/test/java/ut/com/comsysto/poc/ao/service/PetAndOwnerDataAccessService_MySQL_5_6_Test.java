package ut.com.comsysto.poc.ao.service;

import com.comsysto.poc.ao.model.OwnerEntity;
import com.comsysto.poc.ao.model.PetEntity;
import net.java.ao.EntityManager;
import net.java.ao.test.converters.NameConverters;
import net.java.ao.test.jdbc.Data;
import net.java.ao.test.jdbc.DatabaseUpdater;
import net.java.ao.test.jdbc.Jdbc;
import net.java.ao.test.junit.ActiveObjectsJUnitRunner;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import ut.com.comsysto.poc.ao.service.jdbc.Dockerized_MySQL_5_6_JdbcConfig;
import ut.com.comsysto.poc.ao.service.seed.DatabaseSeedHelper;

@RunWith(ActiveObjectsJUnitRunner.class)
@Data(PetAndOwnerDataAccessService_MySQL_5_6_Test.DataAccessServiceDatabaseUpdater.class)
@Jdbc(Dockerized_MySQL_5_6_JdbcConfig.class)
@NameConverters
public class PetAndOwnerDataAccessService_MySQL_5_6_Test extends PetAndOwnerDataAccessServiceTest {
    @BeforeClass
    public static void setUpEnv() throws Exception {
        setUpDockerContainer(Dockerized_MySQL_5_6_JdbcConfig.DOCKER_RUN_COMMAND);
        Thread.sleep(8000);
    }
    @AfterClass
    public static void tearDownEnv() throws Exception {
        tearDownDockerContainer(Dockerized_MySQL_5_6_JdbcConfig.DOCKER_GET_ID_COMMAND);
        Thread.sleep(1000);
    }
    public static final class DataAccessServiceDatabaseUpdater implements DatabaseUpdater {
        @Override
        public void update(EntityManager entityManager) throws Exception {
            entityManager.migrate(OwnerEntity.class, PetEntity.class);
            DatabaseSeedHelper.seed(entityManager);
        }
    }
}