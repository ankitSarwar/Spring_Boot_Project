package com.geekster.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.engine.internal.Cascade;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "addressId")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer addressId;

    @NotBlank
    private String addressName;

    private String addressLandmark;

    @Pattern(regexp = "\\d{10,12}", message = "Phone number must contain only digits and have 10-12 characters")
    private String addressPhoneNumber;

    @NotNull
    @Size(min = 5, max = 6)
    @Pattern(regexp = "\\d{6}", message = "Zip code must be a number")
    private String zipCode;

    @Size(min = 2, max = 50, message = "State must be between 2 and 50 characters")
    private String addressState;

    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

}
