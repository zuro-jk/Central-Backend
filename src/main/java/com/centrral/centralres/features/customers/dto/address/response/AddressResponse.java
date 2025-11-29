package com.centrral.centralres.features.customers.dto.address.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressResponse {
    private Long id;
    private Long customerId;
    private String street;
    private String reference;
    private String city;
    private String province;
    private String zipCode;
    private String instructions;
    private String createdAt;
    private String updatedAt;
    private Long createdBy;
    private Long updatedBy;
}