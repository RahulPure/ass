package org.sample.assignment.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.sample.assignment.representations.EmployeeRepresentation;
import org.sample.assignment.testconfigration.EmployeeServiceTestConfiguration;
import org.sample.assignment.utils.TestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author RahulPure
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {
        EmployeeController.class,
        EmployeeServiceTestConfiguration.class
})
public class EmployeeControllerTest {
    @Autowired
    private EmployeeController employeeController;

    @Test
    public void testCreateEmployee()
    {
        String id = TestUtil.generateId(org.sample.assignment.constants.EntityType.EMPLOYEE.identifierPrefix);
        EmployeeRepresentation employeeRepresentation =new EmployeeRepresentation();
        employeeRepresentation.setEmployeeId(id);
        employeeRepresentation.setFirstName("rahul");
        employeeRepresentation.setLastName("pure");
        employeeRepresentation.setSalary(1000.0);
        employeeRepresentation.setEmailId("rahul.p@toranainc.com");
        employeeRepresentation.setPhoneNumber("7620192181");

        ResponseEntity<EmployeeRepresentation> employeeRepresentationResponseEntity = employeeController.create(employeeRepresentation, TestUtil.getAuthentication());
        TestUtil.successResponseEntity(employeeRepresentationResponseEntity);
        assertNotNull(Objects.requireNonNull(employeeRepresentationResponseEntity.getBody()).getEmployeeId());
        assertEquals(employeeRepresentationResponseEntity.getBody().getFirstName(), employeeRepresentationResponseEntity.getBody().getFirstName());
        assertEquals(employeeRepresentationResponseEntity.getBody().getLastName(), "pure");
    }

    @Test
    public void testUpdateEmployee()
    {
        String id = "emp-806855ee-0b6e-5b5c-9337-0f1dfa384d76";
        EmployeeRepresentation employeeRepresentation =new EmployeeRepresentation();
        employeeRepresentation.setEmployeeId(id);
        employeeRepresentation.setFirstName("rahul");
        employeeRepresentation.setLastName("pure");
        employeeRepresentation.setSalary(1000.0);
        employeeRepresentation.setEmailId("rahul.p@toranainc.com");
        employeeRepresentation.setPhoneNumber("7620192181");

        ResponseEntity<EmployeeRepresentation> employeeRepresentationResponseEntity = employeeController.update(id,employeeRepresentation, TestUtil.getAuthentication());
        TestUtil.successResponseEntity(employeeRepresentationResponseEntity);
        assertNotNull(Objects.requireNonNull(employeeRepresentationResponseEntity.getBody()).getEmployeeId());
        assertEquals(employeeRepresentationResponseEntity.getBody().getFirstName(), employeeRepresentationResponseEntity.getBody().getFirstName());
        assertEquals(employeeRepresentationResponseEntity.getBody().getLastName(), "pure");
    }

}
