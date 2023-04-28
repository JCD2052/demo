package org.jcd2052.api.models;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public final class Address {
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private Geolocation geo;
}
