package ut.com.comsysto.poc.ao.service.seed;

import com.comsysto.poc.ao.model.OwnerEntity;
import com.comsysto.poc.ao.model.PetEntity;
import net.java.ao.EntityManager;

public class DatabaseSeedHelper {
    public static void seed(EntityManager entityManager) throws Exception {
        OwnerEntity owner = entityManager.create(OwnerEntity.class);
        owner.setName("bob");
        owner.save();

        OwnerEntity owner2 = entityManager.create(OwnerEntity.class);
        owner2.setName("jim");
        owner2.save();

        PetEntity pet = entityManager.create(PetEntity.class);
        pet.setName("snorre");
        pet.setType("CAT");
        pet.setOwnerId((long) owner2.getID());
        pet.save();
    }
}
