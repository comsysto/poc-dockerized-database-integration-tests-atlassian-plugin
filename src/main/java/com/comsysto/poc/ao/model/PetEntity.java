package com.comsysto.poc.ao.model;

import net.java.ao.Entity;
import net.java.ao.schema.Index;
import net.java.ao.schema.Indexes;
import net.java.ao.schema.Table;

@Indexes({
    @Index(name = "search", methodNames = { "getName", "getType" }),
    @Index(name = "getName", methodNames = "getName"),
    @Index(name = "getType", methodNames = "getType")
})
@Table("pets") // TABLE NAME NO longer than 30 characters including AO_000000
public interface PetEntity extends Entity {

    String NAME = "NAME";
    String TYPE = "TYPE";

    String getName();
    void setName(String value);

    String getType();
    void setType(String value);

    // This could be done with @OneToOne Annotations too,
    // But that produce a real foreign key constraint on the database
    // We want to show how to "loosely" perform an inner join, that's
    // why we do it this way together with an @Index() on the column
    Long getOwnerId();
    void setOwnerId(Long value);

}
