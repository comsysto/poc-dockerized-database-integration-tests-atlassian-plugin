package com.comsysto.poc.ao.service;

import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.comsysto.poc.ao.model.OwnerEntity;
import com.comsysto.poc.ao.model.PetEntity;
import net.java.ao.EntityManager;
import net.java.ao.Query;

import javax.inject.Inject;
import javax.inject.Named;
import java.sql.SQLException;

@Named("petAndOwnerDataAccessService")
public class PetAndOwnerDataAccessService {

    private EntityManager entityManager;

    @Inject
    public PetAndOwnerDataAccessService(@ComponentImport EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public OwnerEntity[] getOwnersWhoHavePets() throws SQLException {
        Query searchQuery = Query
                .select()
                .distinct()
                .limit(10) // You can add pagination here
                .offset(0)
                .alias(OwnerEntity.class, "o")
                .alias(PetEntity.class, "p")
                .join(PetEntity.class, "o.ID = p.OWNER_ID");
        return entityManager.find(OwnerEntity.class, searchQuery);
    }

    public void createOrUpdateOwner(String ownerName) throws SQLException {
        OwnerEntity owner = findOwnerByName(ownerName);
        if (owner == null) {
            owner = entityManager.create(OwnerEntity.class);
        }
        owner.setName(ownerName);
        owner.save();
    }

    public OwnerEntity findOwnerByName(String ownerName) throws SQLException {
        Query searchQuery = Query.select().where(OwnerEntity.NAME + " = ?", ownerName);
        OwnerEntity[] results = entityManager.find(OwnerEntity.class, searchQuery);
        if (results != null && results.length > 0) {
            return results[0];
        }
        return null;
    }

    public void createOrUpdatePet(String petName, String petType, Long ownerId) throws SQLException {
        PetEntity owner = findPetByNameAndType(petName, petType);
        if (owner == null) {
            owner = entityManager.create(PetEntity.class);
        }
        owner.setName(petName);
        owner.setType(petType);
        // Note: You can insert pets with ownerId == null. That means, the pet has no owner
        owner.setOwnerId(ownerId);
        owner.save();
    }

    public PetEntity findPetByNameAndType(String petName, String petType) throws SQLException {
        Query searchQuery = Query.select().where(PetEntity.NAME + " = ? AND " + PetEntity.TYPE + " = ?", petName, petType);
        PetEntity[] results = entityManager.find(PetEntity.class, searchQuery);
        if (results != null && results.length > 0) {
            return results[0];
        }
        return null;
    }
}
