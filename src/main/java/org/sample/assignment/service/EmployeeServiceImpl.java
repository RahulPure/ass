package org.sample.assignment.service;

import lombok.extern.slf4j.Slf4j;
import org.sample.assignment.constants.EntityType;
import org.sample.assignment.constants.ValidationMessages;
import org.sample.assignment.converter.AddressRepresentationConverter;
import org.sample.assignment.converter.EmployeeRepresentationConverter;
import org.sample.assignment.dao.EmployeeDAO;
import org.sample.assignment.entity.Address;
import org.sample.assignment.entity.Employee;
import org.sample.assignment.errorcodes.ApplicationErrorCode;
import org.sample.assignment.exception.ResourceConflictException;
import org.sample.assignment.exception.ResourceNotFoundException;
import org.sample.assignment.model.ResponseBody;
import org.sample.assignment.representations.*;
import org.sample.assignment.utils.DateUtils;
import org.sample.assignment.utils.QueryUtils;
import org.sample.assignment.utils.UUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author RahulPure
 */
@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDAO employeeDAO;

    @Override
    @Transactional
    public ResponseBody<EmployeeRepresentation> save(Authentication authentication, EmployeeRepresentation employeeRepresentation) {
        if (employeeDAO.isEmailAlreadyExistBoolean(employeeRepresentation.getEmailId())) {
            throw new ResourceConflictException(
                    MessageFormat.format(ValidationMessages.EMAIL_ALREADY_EXIST, employeeRepresentation.getEmailId()),
                    ApplicationErrorCode.RESOURCE_CONFLICT);
        }
        Employee entity = EmployeeRepresentationConverter.INSTANCE.toEntity(employeeRepresentation);
        entity.setId(UUIDGenerator.generateUniversalId(EntityType.EMPLOYEE));
        entity.setCreatedTimestamp(DateUtils.currentDateTime());
        entity.setUpdatedTimestamp(DateUtils.currentDateTime());
        return new ResponseBody<>(
                HttpStatus.OK, EmployeeRepresentationConverter.INSTANCE.toRepresentation(employeeDAO.save(entity)));
    }

    @Override
    @Transactional
    public ResponseBody<EmployeeRepresentation> update(Authentication authentication, EmployeeRepresentation employeeRepresentation) {
        Employee byId = employeeDAO.findById(employeeRepresentation.getEmployeeId());
        if (!byId.getEmailId().equalsIgnoreCase(employeeRepresentation.getEmailId()) && employeeDAO.isEmailEmployeeAlreadyExistBoolean(employeeRepresentation.getEmailId(), employeeRepresentation.getEmployeeId())) {
            throw new ResourceConflictException(
                    MessageFormat.format(ValidationMessages.EMAIL_ALREADY_EXIST, employeeRepresentation.getEmailId()),
                    ApplicationErrorCode.RESOURCE_CONFLICT);
        }
        byId.setFirstName(employeeRepresentation.getFirstName());
        byId.setLastName(employeeRepresentation.getLastName());
        byId.setEmailId(employeeRepresentation.getEmailId());
        byId.setSalary(employeeRepresentation.getSalary());
        byId.setUpdatedTimestamp(DateUtils.currentDateTime());
        byId.setPhoneNumber(employeeRepresentation.getPhoneNumber());
        return new ResponseBody<>(
                HttpStatus.OK, EmployeeRepresentationConverter.INSTANCE.toRepresentation(employeeDAO.save(byId)));
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseBody<EmployeeRepresentation> get(Authentication authentication, String empId) {
         return new ResponseBody<>(
                HttpStatus.OK, EmployeeRepresentationConverter.INSTANCE.toRepresentation(employeeDAO.findById(empId)));
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseBody<SearchResponseRepresentation<EmployeeRepresentation>> searchEmployee(Authentication authentication, Integer pageNo, Integer pageSize, String sort, SearchRequestRepresentation searchRequestRepresentation) {
        Pageable pageable;
        try {
            pageable =
                    employeeDAO.getSearchCount(pageNo, pageSize, searchRequestRepresentation);
            if (pageable.getSize() == 0)
                throw new ResourceNotFoundException(
                        MessageFormat.format(ValidationMessages.SEARCH_RESULT_NOT_FOUND, EntityType.EMPLOYEE.title),
                        ApplicationErrorCode.RESOURCE_NOT_FOUND);
        } catch (IllegalArgumentException e) {
            log.info(
                    " Not able to search with requested criteria :{}",
                    searchRequestRepresentation);
            throw new ResourceNotFoundException(
                    MessageFormat.format(ValidationMessages.SEARCH_RESULT_NOT_FOUND, EntityType.EMPLOYEE.title), ApplicationErrorCode.RESULTS_NOT_FOUND);
        }
        return new ResponseBody<>(
                HttpStatus.OK,
                new SearchResponseRepresentation<>(
                        pageable,
                        employeeDAO.getSearchResult(
                                pageNo,
                                pageSize,
                                QueryUtils.extractSortBy(sort),
                                QueryUtils.extractSortOrder(sort),
                                searchRequestRepresentation).stream().map(EmployeeRepresentationConverter.INSTANCE::toRepresentation).collect(Collectors.toList())));
    }

    @Override
    @Transactional
    public ResponseBody<AddressRepresentation> saveEmployeeAddress(Authentication authentication, AddressRepresentation addressRepresentation, String empId) {
        Employee byId = employeeDAO.findById(empId);
        Address address = AddressRepresentationConverter.INSTANCE.toEntity(addressRepresentation);
        address.setId(UUIDGenerator.generateUniversalId(EntityType.ADDRESS));
        address.setCreatedTimestamp(DateUtils.currentDateTime());
        address.setUpdatedTimestamp(DateUtils.currentDateTime());
        return new ResponseBody<>(
                HttpStatus.OK, AddressRepresentationConverter.INSTANCE.toRepresentation(employeeDAO.saveEmployeeAddress(address)));
    }

    @Override
    @Transactional
    public ResponseBody<AddressRepresentation> updateEmployeeAddress(Authentication authentication, AddressRepresentation addressRepresentation) {
        Employee byId = employeeDAO.findById(addressRepresentation.getEmployeeId());
        Address addressById = employeeDAO.findAddressById(addressRepresentation.getAddressId());
        if (Objects.nonNull(addressRepresentation.getCity()))
            addressById.setCity(addressRepresentation.getCity());

        if (Objects.nonNull(addressRepresentation.getState()))
            addressById.setState(addressRepresentation.getState());

        if (Objects.nonNull(addressRepresentation.getStreet()))
            addressById.setStreet(addressRepresentation.getStreet());

        if (Objects.nonNull(addressRepresentation.getZipCode()))
            addressById.setZipCode(addressRepresentation.getZipCode());
        addressById.setUpdatedTimestamp(DateUtils.currentDateTime());
        return new ResponseBody<>(
                HttpStatus.OK, AddressRepresentationConverter.INSTANCE.toRepresentation(employeeDAO.saveEmployeeAddress(addressById)));
    }

    @Override
    public ResponseBody<SearchResponseRepresentation<AddressRepresentation>> searchEmployeeAddress(Authentication authentication, Integer pageNo, Integer pageSize, String sort, SearchRequestRepresentation searchRequestRepresentation) {
        Pageable pageable;
        try {
            pageable =
                    employeeDAO.getAddressSearchCount(pageNo, pageSize, searchRequestRepresentation);
            if (pageable.getSize() == 0)
                throw new ResourceNotFoundException(
                        MessageFormat.format(ValidationMessages.SEARCH_RESULT_NOT_FOUND, EntityType.ADDRESS.title),
                        ApplicationErrorCode.RESOURCE_NOT_FOUND);
        } catch (IllegalArgumentException e) {
            log.info(
                    " Not able to search with requested criteria :{}",
                    searchRequestRepresentation);
            throw new ResourceNotFoundException(
                    MessageFormat.format(ValidationMessages.SEARCH_RESULT_NOT_FOUND, EntityType.ADDRESS.title), ApplicationErrorCode.RESULTS_NOT_FOUND);
        }
        return new ResponseBody<>(
                HttpStatus.OK,
                new SearchResponseRepresentation<>(
                        pageable,
                        employeeDAO.getAddressSearchResult(
                                pageNo,
                                pageSize,
                                QueryUtils.extractSortBy(sort),
                                QueryUtils.extractSortOrder(sort),
                                searchRequestRepresentation).stream().map(AddressRepresentationConverter.INSTANCE::toRepresentation).collect(Collectors.toList())));
    }

    @Override
    @Transactional
    public ResponseBody<DeleteRepresentation> delete(Authentication authentication, String empId) {
        employeeDAO.findById(empId);
        employeeDAO.deleteAddressByEmployeeId(empId);
        employeeDAO.deleteEmployeeById(empId);
        return new ResponseBody<>(
                HttpStatus.OK, new DeleteRepresentation(empId,"Employee deleted successfully"));
    }

    @Override
    @Transactional
    public ResponseBody<DeleteRepresentation> deleteAddress(Authentication authentication, String addressId) {
        employeeDAO.findAddressById(addressId);
        employeeDAO.deleteAddressByAddressId(addressId);
        return new ResponseBody<>(
                HttpStatus.OK, new DeleteRepresentation(addressId,"Address deleted successfully"));
    }
}
