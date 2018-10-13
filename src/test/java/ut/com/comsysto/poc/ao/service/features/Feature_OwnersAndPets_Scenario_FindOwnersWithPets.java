package ut.com.comsysto.poc.ao.service.features;

import com.comsysto.poc.ao.model.OwnerEntity;
import com.comsysto.poc.ao.service.PetAndOwnerDataAccessServiceImpl;
import cucumber.api.java8.En;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class Feature_OwnersAndPets_Scenario_FindOwnersWithPets implements En {

    // Dependency
    private PetAndOwnerDataAccessServiceImpl petAndOwnerDataAccessService;
    // Subject
    private OwnerEntity[] ownerEntities;

    public Feature_OwnersAndPets_Scenario_FindOwnersWithPets() {
        Given("PetAndOwnerDataAccessService to query for pets and owners", () -> {
            petAndOwnerDataAccessService = Feature_OwnersAndPets_Test.petAndOwnerDataAccessService;
            assertThat(this.petAndOwnerDataAccessService, is(notNullValue()));
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
