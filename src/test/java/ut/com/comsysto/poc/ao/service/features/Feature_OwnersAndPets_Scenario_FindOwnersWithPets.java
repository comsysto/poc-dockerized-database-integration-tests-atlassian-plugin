package ut.com.comsysto.poc.ao.service.features;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.comsysto.poc.ao.model.OwnerEntity;
import com.comsysto.poc.ao.model.PetEntity;
import com.comsysto.poc.ao.service.PetAndOwnerDataAccessServiceImpl;
import cucumber.api.java8.En;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class Feature_OwnersAndPets_Scenario_FindOwnersWithPets implements En {

    // Dependencies
    private PetAndOwnerDataAccessServiceImpl petAndOwnerDataAccessService;
    private ActiveObjects activeObjects;
    // Subject
    private OwnerEntity[] ownerEntities;

    public Feature_OwnersAndPets_Scenario_FindOwnersWithPets() {
        Given("owners and some pets with at least one pet having an owner", () -> {
            // INIT DEPENDENCIES
            petAndOwnerDataAccessService = Feature_OwnersAndPets_Test.petAndOwnerDataAccessService;
            activeObjects = Feature_OwnersAndPets_Test.activeObjects;
            assertThat(petAndOwnerDataAccessService, is(notNullValue()));
            assertThat(activeObjects, is(notNullValue()));
            // DATABASE SHOULD BE EMPTY
            activeObjects.deleteWithSQL(PetEntity.class, " 1=1 ", new Object[0]);
            activeObjects.deleteWithSQL(OwnerEntity.class, " 1=1 ", new Object[0]);
            assertThat(activeObjects.count(PetEntity.class), is(equalTo(0)));
            assertThat(activeObjects.count(OwnerEntity.class), is(equalTo(0)));
            // SEED DATA FOR THIS SCENARIO ONLY
            OwnerEntity owner = activeObjects.create(OwnerEntity.class);
            owner.setName("bob");
            owner.save();
            OwnerEntity owner2 = activeObjects.create(OwnerEntity.class);
            owner2.setName("jim");
            owner2.save();
            PetEntity pet = activeObjects.create(PetEntity.class);
            pet.setName("snorre");
            pet.setType("CAT");
            pet.setOwnerId((long) owner2.getID());
            pet.save();
        });
        When("we query the database via method getOwnersWhoHavePets", () -> {
            ownerEntities = petAndOwnerDataAccessService.getOwnersWhoHavePets();
        });
        Then("we should only retrieve owners who actually have pets", () -> {
            assertThat(ownerEntities, is(notNullValue()));
            assertThat(ownerEntities.length, is(1));
            assertThat(ownerEntities[0].getName(), is(equalTo("jim")));
        });
    }

}
