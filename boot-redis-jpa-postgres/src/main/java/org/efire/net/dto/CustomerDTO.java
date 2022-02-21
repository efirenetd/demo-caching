package org.efire.net.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class CustomerDTO {

    private String name;
    private String contactName;
    private String address;
    private String city;
    private String postalCode;
    private String country;
}
