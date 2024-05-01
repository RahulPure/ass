package org.sample.assignment.representations;

import lombok.*;
import org.sample.assignment.model.Save;
import org.sample.assignment.validators.ValidateStringValue;

import java.io.Serializable;

/**
 * @author RahulPure
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddressRepresentation implements Serializable {
    private String employeeId;

    private String addressId;
    @ValidateStringValue(
            nullCheck = true,
            field = "street",
            groups = {Save.class})
    private String street;

    @ValidateStringValue(
            nullCheck = true,
            field = "city",
            groups = {Save.class})
    private String city;

    @ValidateStringValue(
            nullCheck = true,
            field = "state",
            groups = {Save.class})
    private String state;

    @ValidateStringValue(
            nullCheck = true,
            field = "zipCode",
            groups = {Save.class})
    private String zipCode;

    private String createdTimestamp;

    private String updatedTimestamp;
}
