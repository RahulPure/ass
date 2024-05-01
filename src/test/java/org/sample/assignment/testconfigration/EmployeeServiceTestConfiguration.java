package org.sample.assignment.testconfigration;

import org.sample.assignment.model.ResponseBody;
import org.sample.assignment.representations.AddressRepresentation;
import org.sample.assignment.representations.EmployeeRepresentation;
import org.sample.assignment.representations.SearchRequestRepresentation;
import org.sample.assignment.representations.SearchResponseRepresentation;
import org.sample.assignment.service.EmployeeService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;

/**
 * @author RahulPure
 */
@TestConfiguration
public class EmployeeServiceTestConfiguration {

    @Bean
    public EmployeeService getMockEmployeeService()
    {
        return  new MockEmployeeService();
    }
    private class MockEmployeeService implements EmployeeService {

        @Override
        public ResponseBody<EmployeeRepresentation> save(Authentication authentication, EmployeeRepresentation employeeRepresentation) {
            return new ResponseBody<>(HttpStatus.OK, employeeRepresentation);
        }

        @Override
        public ResponseBody<EmployeeRepresentation> update(Authentication authentication, EmployeeRepresentation employeeRepresentation) {
            return new ResponseBody<>(HttpStatus.OK, employeeRepresentation);
        }

        @Override
        public ResponseBody<EmployeeRepresentation> get(Authentication authentication, String empId) {
            EmployeeRepresentation employeeRepresentation =new EmployeeRepresentation();
            return  new ResponseBody<>(HttpStatus.OK, employeeRepresentation);
        }

        @Override
        public ResponseBody<SearchResponseRepresentation<EmployeeRepresentation>> searchEmployee(Authentication authentication, Integer pageNo, Integer pageSize, String sort, SearchRequestRepresentation searchRequestRepresentation) {
            return null;
        }

        @Override
        public ResponseBody<AddressRepresentation> saveEmployeeAddress(Authentication authentication, AddressRepresentation addressRepresentation, String empId) {
            return null;
        }

        @Override
        public ResponseBody<AddressRepresentation> updateEmployeeAddress(Authentication authentication, AddressRepresentation addressRepresentation) {
            return null;
        }

        @Override
        public ResponseBody<SearchResponseRepresentation<AddressRepresentation>> searchEmployeeAddress(Authentication authentication, Integer pageNo, Integer pageSize, String sort, SearchRequestRepresentation searchRequestRepresentation) {
            return null;
        }
    }

}
