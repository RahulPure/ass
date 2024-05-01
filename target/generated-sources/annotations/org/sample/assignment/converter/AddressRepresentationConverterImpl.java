package org.sample.assignment.converter;

import javax.annotation.processing.Generated;
import org.sample.assignment.entity.Address;
import org.sample.assignment.representations.AddressRepresentation;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-01T14:56:43+0530",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.7 (Azul Systems, Inc.)"
)
public class AddressRepresentationConverterImpl implements AddressRepresentationConverter {

    @Override
    public Address toEntity(AddressRepresentation addressRepresentation) {
        if ( addressRepresentation == null ) {
            return null;
        }

        Address address = new Address();

        address.setEmployeeId( addressRepresentation.getEmployeeId() );
        address.setStreet( addressRepresentation.getStreet() );
        address.setCity( addressRepresentation.getCity() );
        address.setState( addressRepresentation.getState() );
        address.setZipCode( addressRepresentation.getZipCode() );

        return address;
    }

    @Override
    public AddressRepresentation toRepresentation(Address address) {
        if ( address == null ) {
            return null;
        }

        AddressRepresentation addressRepresentation = new AddressRepresentation();

        addressRepresentation.setAddressId( address.getId() );
        addressRepresentation.setEmployeeId( address.getEmployeeId() );
        addressRepresentation.setStreet( address.getStreet() );
        addressRepresentation.setCity( address.getCity() );
        addressRepresentation.setState( address.getState() );
        addressRepresentation.setZipCode( address.getZipCode() );

        addressRepresentation.setCreatedTimestamp( org.sample.assignment.utils.DateUtils.convertToString(address.getCreatedTimestamp()) );
        addressRepresentation.setUpdatedTimestamp( org.sample.assignment.utils.DateUtils.convertToString(address.getUpdatedTimestamp()) );

        return addressRepresentation;
    }
}
