package org.sample.assignment.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

/**
 * @author RahulPure
 */
@Entity
@Getter
@Setter
@Table(name = "employees")
@AttributeOverride(name = "id", column = @Column(name = "employee_id"))
public class Employee
{
    @Id
    @Column(name = "employee_id")
    private String id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String emailId;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "salary")
    private Double salary;
    /** The Created date. */
    @Column(name = "create_dttm")
    protected ZonedDateTime createdTimestamp;

    /** The Update date. */
    @Column(name = "update_dttm")
    protected ZonedDateTime updatedTimestamp;
}
