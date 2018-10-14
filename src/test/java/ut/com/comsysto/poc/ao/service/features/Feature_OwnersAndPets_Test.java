package ut.com.comsysto.poc.ao.service.features;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.atlassian.activeobjects.test.TestActiveObjects;
import com.comsysto.poc.ao.model.OwnerEntity;
import com.comsysto.poc.ao.model.PetEntity;
import com.comsysto.poc.ao.service.PetAndOwnerDataAccessServiceImpl;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import net.java.ao.EntityManager;
import net.java.ao.test.converters.NameConverters;
import net.java.ao.test.jdbc.Data;
import net.java.ao.test.jdbc.DatabaseUpdater;
import net.java.ao.test.junit.ActiveObjectsJUnitRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertNotNull;

@RunWith(ActiveObjectsJUnitRunner.class)
@Data(Feature_OwnersAndPets_Test.CucumberRunnerTestDatabaseUpdater.class)
@NameConverters
public class Feature_OwnersAndPets_Test {

    // ------ FROM HERE DOWN ACTIVE OBJECTS -----------

    private EntityManager entityManager;

    // Make available to tests
    public static ActiveObjects activeObjects;
    public static PetAndOwnerDataAccessServiceImpl petAndOwnerDataAccessService;

    @Before
    public void setUp() throws Exception {
        assertNotNull(entityManager);
        activeObjects = new TestActiveObjects(entityManager);
        petAndOwnerDataAccessService = new PetAndOwnerDataAccessServiceImpl(activeObjects);
    }

    public static class CucumberRunnerTestDatabaseUpdater implements DatabaseUpdater {
        @Override
        public void update(EntityManager entityManager) throws Exception {
            entityManager.migrate(PetEntity.class, OwnerEntity.class);
        }
    }

    // ------ FROM HERE DOWN CUCUMBER -----------

    @Test
    public void subRunner() throws Exception {
        Result res = JUnitCore.runClasses(SubRunner.class);
        System.err.println(res.getFailures());
        assert(res.wasSuccessful());
    }

    @RunWith(Cucumber.class)
    @CucumberOptions(
            features = "src/test/resources/ut/com/comsysto/poc/ao/service/features",
            plugin = {"pretty"})
    public static class SubRunner { }
}
