package com.comsysto.poc.ao.model;

import net.java.ao.Entity;
import net.java.ao.schema.Index;
import net.java.ao.schema.Indexes;
import net.java.ao.schema.Table;

@Indexes({
    @Index(name = "getName", methodNames = "getName")
})
@Table("owners") // TABLE NAME NO longer than 30 characters including AO_000000
public interface OwnerEntity extends Entity {

    String NAME = "NAME";

    String getName();
    void setName(String value);

}
