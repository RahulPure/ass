package org.sample.assignment.converter;

import javax.annotation.processing.Generated;
import org.sample.assignment.entity.Employee;
import org.sample.assignment.representations.EmployeeRepresentation;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-01T14:56:43+0530",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.7 (Azul Systems, Inc.)"
)
public class EmployeeRepresentationConverterImpl implements EmployeeRepresentationConverter {

    @Override
    public Employee toEntity(EmployeeRepresentation employeeRepresentation) {
        if ( employeeRepresentation == null ) {
            return null;
        }

        Employee employee = new Employee();

        employee.setFirstName( employeeRepresentation.getFirstName() );
        employee.setLastName( employeeRepresentation.getLastName() );
        employee.setEmailId( employeeRepresentation.getEmailId() );
        employee.setPhoneNumber( employeeRepresentation.getPhoneNumber() );
        employee.setSalary( employeeRepresentation.getSalary() );

        return employee;
    }

    @Override
    public EmployeeRepresentation toRepresentation(Employee employee) {
        if ( employee == null ) {
            return null;
        }

        EmployeeRepresentation employeeRepresentation = new EmployeeRepresentation();

        employeeRepresentation.setEmployeeId( employee.getId() );
        employeeRepresentation.setFirstName( employee.getFirstName() );
        employeeRepresentation.setLastName( employee.getLastName() );
        employeeRepresentation.setEmailId( employee.getEmailId() );
        employeeRepresentation.setPhoneNumber( employee.getPhoneNumber() );
        employeeRepresentation.setSalary( employee.getSalary() );

        employeeRepresentation.setCreatedTimestamp( org.sample.assignment.utils.DateUtils.convertToString(employee.getCreatedTimestamp()) );
        employeeRepresentation.setUpdatedTimestamp( org.sample.assignment.utils.DateUtils.convertToString(employee.getUpdatedTimestamp()) );

        return employeeRepresentation;
    }
}
