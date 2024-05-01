package org.sample.assignment.dao;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;
import org.sample.assignment.constants.EntityType;
import org.sample.assignment.constants.SortOrder;
import org.sample.assignment.constants.ValidationMessages;
import org.sample.assignment.entity.Address;
import org.sample.assignment.entity.Employee;
import org.sample.assignment.errorcodes.ApplicationErrorCode;
import org.sample.assignment.exception.ApplicationException;
import org.sample.assignment.exception.ResourceNotFoundException;
import org.sample.assignment.representations.Pageable;
import org.sample.assignment.representations.SearchRequestRepresentation;
import org.sample.assignment.utils.FilterCriteriaBuilder;
import org.sample.assignment.utils.QueryUtils;
import org.sample.assignment.utils.SearchCriteriaBuilder;
import org.springframework.stereotype.Repository;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author RahulPure
 */
@Slf4j
@Repository
public class EmployeeDAOImpl implements EmployeeDAO
{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Employee save(Employee employee) {
        employee = entityManager.merge(employee);
        log.info("object saved | emp_id: {}",employee.getId());
        return employee;
    }

    @Override
    public boolean isEmailAlreadyExistBoolean(String emailId) {
      log.debug(" fetch email : {}",emailId);
        TypedQuery<Long> query=entityManager.createQuery("select count(*) from Employee where emailId = :emailId",Long.class);
        query.setParameter("emailId",emailId);
        Long count =query.getSingleResult();
        log.info("Number of email present with email id : {} ",emailId);
        return count != null && count > 0;
    }

    @Override
    public Employee findById(String id) {
        try {
            TypedQuery<Employee> query =
                    entityManager.createQuery(
                            "from  Employee i where i.id =: id ",
                            Employee.class);
            query.setParameter("id", id);
            return query.getSingleResult();
        } catch (NoResultException e) {
            log.warn("Employee Not found | Employee id:{} ", id);
            throw new ResourceNotFoundException(
                    MessageFormat.format(ValidationMessages.RESOURCE_NOT_FOUND, EntityType.EMPLOYEE.title, id),
                    ApplicationErrorCode.RESOURCE_NOT_FOUND);
        }
    }

    @Override
    public boolean isEmailEmployeeAlreadyExistBoolean(String emailId,String id) {
        log.debug(" fetch email : {} with employee id : {} ",emailId,id);
        TypedQuery<Long> query=entityManager.createQuery("select count(*) from Employee where emailId = :emailId and id = :id ",Long.class);
        query.setParameter("emailId",emailId);
        query.setParameter("id",id);
        Long count =query.getSingleResult();
        log.info("Number of email present with email id : {} ",emailId);
        return count != null && count > 0;
    }

    @Override
    public Pageable getSearchCount(Integer pageNo, Integer pageSize, SearchRequestRepresentation searchRequestRepresentation) throws NoResultException {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<Employee> root = criteriaQuery.from(Employee.class);
        criteriaQuery.select(criteriaBuilder.count(root));
        /*where clause*/
        try {
            criteriaQuery.where(
                    getFinalPredicate(
                            searchRequestRepresentation, criteriaBuilder, root)
                            .toArray(new Predicate[] {}));
        } catch (IllegalArgumentException e) {
            throw new ApplicationException(e.getMessage(), ApplicationErrorCode.UNEXPECTED_ERROR);
        }
        int size;
        try {
            TypedQuery<Long> query = entityManager.createQuery(criteriaQuery);
            size = query.getSingleResult().intValue();
        } catch (Exception e) {
            throw new ApplicationException(e.getMessage(), ApplicationErrorCode.UNEXPECTED_ERROR);
        }
        return new Pageable(pageNo, pageSize, QueryUtils.calculatesPages(pageSize, size), size);

    }

    @Override
    public List<Employee> getSearchResult(Integer pageNo, Integer pageSize, String sortBy, String sortOrder, SearchRequestRepresentation searchRequestRepresentation) throws NoResultException {
        /*Criteria Builder*/
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> root = criteriaQuery.from(Employee.class);

        /*where clause*/
        criteriaQuery.where(
                getFinalPredicate(searchRequestRepresentation, criteriaBuilder, root)
                        .toArray(new Predicate[] {}));
        TypedQuery<Employee> typedQuery;
        try {
            if (SortOrder.ASC.name().equalsIgnoreCase(sortOrder))
                criteriaQuery.orderBy(criteriaBuilder.asc(root.get(sortBy)));
            else criteriaQuery.orderBy(criteriaBuilder.desc(root.get(sortBy)));
            typedQuery = entityManager.createQuery(criteriaQuery);
        } catch (Exception e) {
            throw new ApplicationException(e.getMessage(), ApplicationErrorCode.UNEXPECTED_ERROR);
        }
        /*Pagination*/
        typedQuery.setFirstResult((pageNo - 1) * pageSize);
        typedQuery.setMaxResults(pageSize);
        return typedQuery.getResultList();

    }

    @Override
    public Address saveEmployeeAddress(Address address) {
        address = entityManager.merge(address);
        log.info("object saved | emp_id: {} | address_id: {}",address.getEmployeeId(),address.getId());
        return address;
    }

    @Override
    public Address findAddressById(String id) {
        try {
            TypedQuery<Address> query =
                    entityManager.createQuery(
                            "from  Address i where i.id =: id ",
                            Address.class);
            query.setParameter("id", id);
            return query.getSingleResult();
        } catch (NoResultException e) {
            log.warn("Address Not found | address id:{} ", id);
            throw new ResourceNotFoundException(
                    MessageFormat.format(ValidationMessages.RESOURCE_NOT_FOUND, EntityType.ADDRESS.title, id),
                    ApplicationErrorCode.RESOURCE_NOT_FOUND);
        }
    }

    @Override
    public Pageable getAddressSearchCount(Integer pageNo, Integer pageSize, SearchRequestRepresentation searchRequestRepresentation) throws NoResultException {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<Address> root = criteriaQuery.from(Address.class);
        criteriaQuery.select(criteriaBuilder.count(root));
        /*where clause*/
        try {
            criteriaQuery.where(
                    getFinalPredicate(
                            searchRequestRepresentation, criteriaBuilder, root)
                            .toArray(new Predicate[] {}));
        } catch (IllegalArgumentException e) {
            throw new ApplicationException(e.getMessage(), ApplicationErrorCode.UNEXPECTED_ERROR);
        }
        int size;
        try {
            TypedQuery<Long> query = entityManager.createQuery(criteriaQuery);
            size = query.getSingleResult().intValue();
        } catch (Exception e) {
            throw new ApplicationException(e.getMessage(), ApplicationErrorCode.UNEXPECTED_ERROR);
        }
        return new Pageable(pageNo, pageSize, QueryUtils.calculatesPages(pageSize, size), size);
    }

    @Override
    public List<Address> getAddressSearchResult(Integer pageNo, Integer pageSize, String sortBy, String sortOrder, SearchRequestRepresentation searchRequestRepresentation) throws NoResultException {
        /*Criteria Builder*/
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Address> criteriaQuery = criteriaBuilder.createQuery(Address.class);
        Root<Address> root = criteriaQuery.from(Address.class);

        /*where clause*/
        criteriaQuery.where(
                getFinalPredicate(searchRequestRepresentation, criteriaBuilder, root)
                        .toArray(new Predicate[] {}));
        TypedQuery<Address> typedQuery;
        try {
            if (SortOrder.ASC.name().equalsIgnoreCase(sortOrder))
                criteriaQuery.orderBy(criteriaBuilder.asc(root.get(sortBy)));
            else criteriaQuery.orderBy(criteriaBuilder.desc(root.get(sortBy)));
            typedQuery = entityManager.createQuery(criteriaQuery);
        } catch (Exception e) {
            throw new ApplicationException(e.getMessage(), ApplicationErrorCode.UNEXPECTED_ERROR);
        }
        /*Pagination*/
        typedQuery.setFirstResult((pageNo - 1) * pageSize);
        typedQuery.setMaxResults(pageSize);
        return typedQuery.getResultList();
    }

    @Override
    public void deleteAddressByEmployeeId(String employeeId) {
        try {
            Query query =
                    entityManager.createQuery(
                            "DELETE from  Address i where i.employeeId =: employeeId ");
            query.setParameter("employeeId", employeeId);
             query.executeUpdate();
        } catch (NoResultException e) {
            log.warn("Address Not found | Employee id:{} ", employeeId);
            throw new ResourceNotFoundException(
                    MessageFormat.format(ValidationMessages.RESOURCE_NOT_FOUND, EntityType.ADDRESS.title, employeeId),
                    ApplicationErrorCode.RESOURCE_NOT_FOUND);
        }
    }

    @Override
    public void deleteEmployeeById(String id) {
        try {
            Query query =
                    entityManager.createQuery(
                            "DELETE from  Employee i where i.id =: id ");
            query.setParameter("id", id);
            query.executeUpdate();
        } catch (NoResultException e) {
            log.warn("Employee Not found | Employee id:{} ", id);
            throw new ResourceNotFoundException(
                    MessageFormat.format(ValidationMessages.RESOURCE_NOT_FOUND, EntityType.EMPLOYEE.title, id),
                    ApplicationErrorCode.RESOURCE_NOT_FOUND);
        }
    }

    @Override
    public void deleteAddressByAddressId(String id) {
        try {
            Query query =
                    entityManager.createQuery(
                            "DELETE from  Address i where i.id =: id ");
            query.setParameter("id", id);
            query.executeUpdate();
        } catch (NoResultException e) {
            log.warn("Address Not found | Address id:{} ", id);
            throw new ResourceNotFoundException(
                    MessageFormat.format(ValidationMessages.RESOURCE_NOT_FOUND, EntityType.ADDRESS.title, id),
                    ApplicationErrorCode.RESOURCE_NOT_FOUND);
        }
    }

    private List<Predicate> getFinalPredicate(
            SearchRequestRepresentation searchRequestRepresentation,
            CriteriaBuilder criteriaBuilder,
            Root<?> root) {
        List<Predicate> predicates = new ArrayList<>();

        if (Objects.nonNull(searchRequestRepresentation)) {
            /*Search Criteria*/
            if (Objects.nonNull(searchRequestRepresentation.getSearch())) {
                SearchCriteriaBuilder searchCriteriaBuilder =
                        new SearchCriteriaBuilder(criteriaBuilder.disjunction(), criteriaBuilder, root);
                searchRequestRepresentation.getSearch().forEach(searchCriteriaBuilder);
                predicates.add(searchCriteriaBuilder.getPredicate());
            }
            /*Filter Criteria*/
            if (Objects.nonNull(searchRequestRepresentation.getFilter())) {
                FilterCriteriaBuilder filterCriteriaBuilder =
                        new FilterCriteriaBuilder(criteriaBuilder.conjunction(), criteriaBuilder, root);
                searchRequestRepresentation.getFilter().forEach(filterCriteriaBuilder);
                predicates.add(filterCriteriaBuilder.getPredicate());
            }
        }
        return predicates;
    }
}
