package com.project.pt.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AddressDTO {

    @NotNull(message = "Country cannot be null")
    @Size(min = 3, max = 20, message = "Country must be between 3 and 20 characters")
    private String country;

    @NotNull(message = "City cannot be null")
    @Size(min = 3, max = 20, message = "City must be between 3 and 20 characters")
    private String city;

    @NotNull(message = "Neighbourhood cannot be null")
    @Size(min = 3, max = 20, message = "Neighbourhood must be between 3 and 20 characters")
    private String neighbourhood;

    @NotNull(message = "Street cannot be null")
    @Size(min = 3, max = 20, message = "Street must be between 3 and 20 characters")
    private String street;

    @NotNull(message = "No cannot be null")
    @Size(min = 3, max = 7, message = "No must be between 3 and 7 characters")
    private String no;

    @NotNull(message = "Phone number cannot be null")
    @Size(min = 3, max = 20, message = "Phone number must be between 3 and 20 characters")
    private String phoneNumber;

}
