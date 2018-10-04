package ut.com.comsysto.poc.ao.service.seed;

import com.comsysto.poc.ao.model.OwnerEntity;
import net.java.ao.EntityManager;

public class DatabaseSeedHelper {
    public static void seed(EntityManager entityManager) throws Exception {
        OwnerEntity owner = entityManager.create(OwnerEntity.class);
        owner.setName("bob");
        owner.save();
    }
}
