package org.sample.assignment.service;

import org.sample.assignment.model.ResponseBody;
import org.sample.assignment.representations.*;
import org.springframework.security.core.Authentication;

import java.util.List;

/**
 * @author RahulPure
 */
public interface EmployeeService
{
    ResponseBody<EmployeeRepresentation> save(Authentication authentication ,EmployeeRepresentation employeeRepresentation);
    ResponseBody<EmployeeRepresentation> update(Authentication authentication ,EmployeeRepresentation employeeRepresentation);
    ResponseBody<EmployeeRepresentation> get(Authentication authentication ,String empId);
    ResponseBody<SearchResponseRepresentation<EmployeeRepresentation>> searchEmployee(
            Authentication authentication,
            Integer pageNo,
            Integer pageSize,
            String sort,
            SearchRequestRepresentation searchRequestRepresentation);

    ResponseBody<AddressRepresentation> saveEmployeeAddress(Authentication authentication , AddressRepresentation addressRepresentation,String empId);
    ResponseBody<AddressRepresentation> updateEmployeeAddress(Authentication authentication , AddressRepresentation addressRepresentation);

    ResponseBody<SearchResponseRepresentation<AddressRepresentation>> searchEmployeeAddress(
            Authentication authentication,
            Integer pageNo,
            Integer pageSize,
            String sort,
            SearchRequestRepresentation searchRequestRepresentation);

    ResponseBody<DeleteRepresentation> delete(Authentication authentication , String empId);
    ResponseBody<DeleteRepresentation> deleteAddress(Authentication authentication , String addressId);
}
