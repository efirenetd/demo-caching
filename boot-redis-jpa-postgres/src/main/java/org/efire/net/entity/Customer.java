package org.efire.net.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
public class Customer implements Serializable {

    @Id
    private String id = UUID.randomUUID().toString();

    private String name;
    private String contactName;
    private String address;
    private String city;
    private String postalCode;
    private String country;

}
