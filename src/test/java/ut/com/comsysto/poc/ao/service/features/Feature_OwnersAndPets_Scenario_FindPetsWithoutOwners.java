package ut.com.comsysto.poc.ao.service.features;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.comsysto.poc.ao.model.OwnerEntity;
import com.comsysto.poc.ao.model.PetEntity;
import com.comsysto.poc.ao.service.PetAndOwnerDataAccessServiceImpl;
import cucumber.api.java8.En;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class Feature_OwnersAndPets_Scenario_FindPetsWithoutOwners implements En {

    // Dependencies
    private PetAndOwnerDataAccessServiceImpl petAndOwnerDataAccessService;
    private ActiveObjects activeObjects;
    // Subject
    private PetEntity[] petEntities;

    public Feature_OwnersAndPets_Scenario_FindPetsWithoutOwners() {
        Given("owners and some pets with at least one pet having no owner", () -> {
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
            PetEntity pet2 = activeObjects.create(PetEntity.class);
            pet2.setName("lone-smitty");
            pet2.setType("DOG");
            pet2.save();
        });
        When("we query the database via method getPetsWithoutOwners", () -> {
            petEntities = petAndOwnerDataAccessService.getPetsWithoutOwners();
        });
        Then("we should only retrieve pets who actually have no owners", () -> {
            assertThat(petEntities, is(notNullValue()));
            assertThat(petEntities.length, is(1));
            assertThat(petEntities[0].getName(), is(equalTo("lone-smitty")));
        });
    }

}
