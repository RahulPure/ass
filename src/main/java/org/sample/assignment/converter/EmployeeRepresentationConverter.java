package org.sample.assignment.converter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.sample.assignment.entity.Employee;
import org.sample.assignment.representations.EmployeeRepresentation;
import org.springframework.stereotype.Component;

/**
 * @author RahulPure
 */
@Mapper
@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public interface EmployeeRepresentationConverter
{
    EmployeeRepresentationConverter INSTANCE =   Mappers.getMapper(EmployeeRepresentationConverter.class);

    @Mapping(target = "createdTimestamp", ignore = true)
    @Mapping(target = "updatedTimestamp", ignore = true)
  Employee toEntity(EmployeeRepresentation employeeRepresentation);

    @Mapping(source = "id", target = "employeeId")
    @Mapping(
            target = "createdTimestamp",
            expression =
                    "java(org.sample.assignment.utils.DateUtils.convertToString(employee.getCreatedTimestamp()))")
    @Mapping(
            target = "updatedTimestamp",
            expression =
                    "java(org.sample.assignment.utils.DateUtils.convertToString(employee.getUpdatedTimestamp()))")
    EmployeeRepresentation toRepresentation(Employee employee) ;
}
