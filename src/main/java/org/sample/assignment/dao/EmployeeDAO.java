package org.sample.assignment.dao;

import jakarta.persistence.NoResultException;
import org.sample.assignment.entity.Address;
import org.sample.assignment.entity.Employee;
import org.sample.assignment.representations.Pageable;
import org.sample.assignment.representations.SearchRequestRepresentation;

import java.util.List;

/**
 * @author RahulPure
 */
public interface EmployeeDAO
{
  Employee save(Employee employee);
  boolean isEmailAlreadyExistBoolean(String email);

  Employee findById(String id);
  boolean isEmailEmployeeAlreadyExistBoolean(String email,String id);

  Pageable getSearchCount(
          Integer pageNo,
          Integer pageSize,
          SearchRequestRepresentation searchRequestRepresentation)
          throws NoResultException;

  List<Employee> getSearchResult(
          Integer pageNo,
          Integer pageSize,
          String sortBy,
          String sortOrder,
          SearchRequestRepresentation searchRequestRepresentation)
          throws NoResultException;
  Address saveEmployeeAddress(Address address);

  Address findAddressById(String id);

  Pageable getAddressSearchCount(
          Integer pageNo,
          Integer pageSize,
          SearchRequestRepresentation searchRequestRepresentation)
          throws NoResultException;

  List<Address> getAddressSearchResult(
          Integer pageNo,
          Integer pageSize,
          String sortBy,
          String sortOrder,
          SearchRequestRepresentation searchRequestRepresentation)
          throws NoResultException;
   void deleteAddressByEmployeeId(String empId);
   void deleteEmployeeById(String empId);

  void deleteAddressByAddressId(String addressId);
}
