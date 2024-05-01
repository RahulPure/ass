package org.sample.assignment.representations;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.sample.assignment.model.Save;
import org.sample.assignment.validators.ValidateStringValue;
import org.springframework.validation.annotation.Validated;

import static org.sample.assignment.constants.ValidationMessages.*;

/**
 * @author RahulPure
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeRepresentation
{
    private String employeeId;
    @ValidateStringValue(
            nullCheck = true,
            field = "first name",
            groups = {Save.class})
    @Pattern(
            regexp = ENTITY_NAME_REGEX_ALLOW_SPACE_AT_SIGN,
            message = EMPLOYEE_FIRST_NAME_VALIDATION_ERR_MSG,
            groups = {Save.class})
    private String firstName;

    @ValidateStringValue(
            nullCheck = true,
            field = "last name",
            groups = {Save.class})
    @Pattern(
            regexp = ENTITY_NAME_REGEX_ALLOW_SPACE_AT_SIGN,
            message = EMPLOYEE_LAST_NAME_VALIDATION_ERR_MSG,
            groups = {Save.class})
    private String lastName;

    @ValidateStringValue(
            nullCheck = true,
            field = "emailId",
            groups = {Save.class})
    @Pattern(
            regexp = EMAIL_REGEX,
            message = EMAIL_VALIDATION_ERR_MSG,
            groups = {Save.class})
    private String emailId;

    @ValidateStringValue(
            nullCheck = true,
            field = "phone number",
            groups = {Save.class})
    @Pattern(
            regexp = MOBILE_NUMBER_REGEX,
            message = MOBILE_VALIDATION_ERR_MSG,
            groups = {Save.class})
    private String phoneNumber;

    private Double salary;

    private String createdTimestamp;

    private String updatedTimestamp;
}
