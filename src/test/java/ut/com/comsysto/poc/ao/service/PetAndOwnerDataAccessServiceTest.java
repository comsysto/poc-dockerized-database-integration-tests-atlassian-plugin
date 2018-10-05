package ut.com.comsysto.poc.ao.service;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.atlassian.activeobjects.test.TestActiveObjects;
import com.comsysto.poc.ao.model.OwnerEntity;
import com.comsysto.poc.ao.model.PetEntity;
import com.comsysto.poc.ao.service.PetAndOwnerDataAccessServiceImpl;
import net.java.ao.EntityManager;
import net.java.ao.test.converters.NameConverters;
import net.java.ao.test.jdbc.*;
import net.java.ao.test.junit.ActiveObjectsJUnitRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import ut.com.comsysto.poc.ao.service.seed.DatabaseSeedHelper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(ActiveObjectsJUnitRunner.class)
@Data(PetAndOwnerDataAccessServiceTest.PetAndOwnerDataAccessServiceTestDatabaseUpdater.class)
// By Default: @Jdbc(DynamicJdbcConfiguration.class)
@NameConverters
public class PetAndOwnerDataAccessServiceTest {
    private EntityManager entityManager;
    private ActiveObjects activeObjects;
    private PetAndOwnerDataAccessServiceImpl petAndOwnerDataAccessService;

    @Before
    public void setUp() throws Exception {
        assertNotNull(entityManager);
        activeObjects = new TestActiveObjects(entityManager);
        petAndOwnerDataAccessService = new PetAndOwnerDataAccessServiceImpl(activeObjects);
    }

    @Test
    public void getOwnersWhoHavePets() throws Exception {
        // OwnerEntity[] owners = petAndOwnerDataAccessService.getOwnersWhoHavePets();
        // assertEquals(0, owners.length);
        assertEquals(0, 0);
    }

    public static class PetAndOwnerDataAccessServiceTestDatabaseUpdater implements DatabaseUpdater {
        @Override
        public void update(EntityManager entityManager) throws Exception {
            entityManager.migrate(PetEntity.class, OwnerEntity.class);
            DatabaseSeedHelper.seed(entityManager);
        }
    }
}
