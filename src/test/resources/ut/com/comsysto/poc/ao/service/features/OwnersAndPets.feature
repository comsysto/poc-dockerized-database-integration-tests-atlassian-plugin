Feature: Owners and Pets are stored in he Database
  We can query the database for owners and pets

  Scenario: find owners with pets
    Given PetAndOwnerDataAccessService to query for pets and owners
    When we query the database via method getOwnersWhoHavePets
    Then we should only retrieve owners who actually have pets
