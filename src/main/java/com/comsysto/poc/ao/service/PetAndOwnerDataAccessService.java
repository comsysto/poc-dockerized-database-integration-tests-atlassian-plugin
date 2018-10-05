package com.comsysto.poc.ao.service;

import com.atlassian.activeobjects.tx.Transactional;
import com.comsysto.poc.ao.model.OwnerEntity;
import com.comsysto.poc.ao.model.PetEntity;

@Transactional
public interface PetAndOwnerDataAccessService {

    OwnerEntity[] getOwnersWhoHavePets();

    void createOrUpdateOwner(String ownerName);

    OwnerEntity findOwnerByName(String ownerName);

    void createOrUpdatePet(String petName, String petType, Long ownerId);

    PetEntity findPetByNameAndType(String petName, String petType);
}
