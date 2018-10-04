package ut.com.comsysto.poc.ao.service;

import com.comsysto.poc.ao.model.OwnerEntity;
import com.comsysto.poc.ao.service.PetAndOwnerDataAccessService;
import net.java.ao.EntityManager;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import ut.com.comsysto.poc.ao.service.helper.CmdLineExecutionResult;
import ut.com.comsysto.poc.ao.service.helper.CmdLineTestHelper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@Ignore
public class PetAndOwnerDataAccessServiceTest {


    private EntityManager entityManager;
    private PetAndOwnerDataAccessService petAndOwnerDataAccessService;

    @Before
    public void setUp() throws Exception {
        assertNotNull(entityManager);
        petAndOwnerDataAccessService = new PetAndOwnerDataAccessService(entityManager);
    }

    @Test
    public void getOwnersWhoHavePets() throws Exception {
        OwnerEntity[] owners = petAndOwnerDataAccessService.getOwnersWhoHavePets();
        assertEquals(0, owners.length);
    }

    //
    // SHARED SETUP AND TEAR DOWN
    //

    protected static void setUpDockerContainer(String dockerRunCommand) {
        CmdLineExecutionResult result = CmdLineTestHelper
                .executeCommand(dockerRunCommand);
        assertEquals(new Integer(0), result.exitCode);
        assertEquals(null, result.exception);
    }

    protected static void tearDownDockerContainer(String dockerGetIdCommand) {
        CmdLineExecutionResult result = CmdLineTestHelper
                .executeCommand(dockerGetIdCommand);
        assertEquals(new Integer(0), result.exitCode);
        assertEquals(null, result.exception);
        String dockerId = result.stdout.trim();
        CmdLineTestHelper.executeCommand("docker kill " + dockerId);
    }
}
