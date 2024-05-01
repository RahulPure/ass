package org.sample.assignment.converter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.sample.assignment.entity.Address;
import org.sample.assignment.entity.Employee;
import org.sample.assignment.representations.AddressRepresentation;
import org.sample.assignment.representations.EmployeeRepresentation;
import org.springframework.stereotype.Component;

/**
 * @author RahulPure
 */
@Mapper
@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public interface AddressRepresentationConverter {

    AddressRepresentationConverter INSTANCE= Mappers.getMapper(AddressRepresentationConverter.class);
    @Mapping(target = "createdTimestamp", ignore = true)
    @Mapping(target = "updatedTimestamp", ignore = true)
    Address toEntity(AddressRepresentation addressRepresentation);

    @Mapping(source = "id", target = "addressId")
    @Mapping(
            target = "createdTimestamp",
            expression =
                    "java(org.sample.assignment.utils.DateUtils.convertToString(address.getCreatedTimestamp()))")
    @Mapping(
            target = "updatedTimestamp",
            expression =
                    "java(org.sample.assignment.utils.DateUtils.convertToString(address.getUpdatedTimestamp()))")
    AddressRepresentation toRepresentation(Address address) ;
}
