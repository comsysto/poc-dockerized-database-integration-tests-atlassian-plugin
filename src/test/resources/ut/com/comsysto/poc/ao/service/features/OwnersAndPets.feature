Feature: Owners and Pets are stored in the Database
  We can query the database for owners and pets.
  We can query for relations between pets and owners.

  Scenario: find owners with pets
    Given owners and some pets with at least one pet having an owner
    When we query the database via method getOwnersWhoHavePets
    Then we should only retrieve owners who actually have pets

  Scenario: find pets without owners
    Given owners and some pets with at least one pet having no owner
    When we query the database via method getPetsWithoutOwners
    Then we should only retrieve pets who actually have no owners
