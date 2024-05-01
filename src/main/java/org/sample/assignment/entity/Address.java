package org.sample.assignment.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.sample.assignment.model.Save;
import org.sample.assignment.validators.ValidateStringValue;

import java.time.ZonedDateTime;

/**
 * @author RahulPure
 */
@Entity
@Getter
@Setter
@Table(name = "address")
@AttributeOverride(name = "id", column = @Column(name = "address_id"))
public class Address {

    @Column(name = "employee_id")
    private String employeeId;

    @Id
    @Column(name = "address_id")
    private String id;

    @Column(name = "street")
    private String street;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "zipcode")
    private String zipCode;

    @Column(name = "create_dttm")
    protected ZonedDateTime createdTimestamp;

    @Column(name = "update_dttm")
    protected ZonedDateTime updatedTimestamp;
}
