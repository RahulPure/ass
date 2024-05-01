package org.sample.assignment.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.sample.assignment.constants.QueryParams;
import org.sample.assignment.constants.ValidationMessages;
import org.sample.assignment.model.ErrorResponseBody;
import org.sample.assignment.model.ResponseBody;
import org.sample.assignment.model.Save;
import org.sample.assignment.representations.*;
import org.sample.assignment.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author RahulPure
 */
@Slf4j
@RestController
@RequestMapping(path = "/api/v1/",consumes = MediaType.APPLICATION_JSON_VALUE)
@Validated
@ApiResponses(
        value = {
                @ApiResponse(
                        responseCode = ValidationMessages.HTTP_STATUS_CODE_401,
                        description = ValidationMessages.HTTP_STATUS_CODE_401_DESCRIPTION,
                        content = {
                                @Content(
                                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                                        schema = @Schema(implementation = ErrorResponseBody.class))
                        }),
                @ApiResponse(
                        responseCode = ValidationMessages.HTTP_STATUS_CODE_403_DESCRIPTION,
                        description = ValidationMessages.HTTP_STATUS_CODE_403_DESCRIPTION,
                        content = {
                                @Content(
                                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                                        schema = @Schema(implementation = ErrorResponseBody.class))
                        })
        })
@Tag(name = "Employee", description = "Create | Update | Details | Search | Delete ")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Operation(
            summary = "Create Employee",
            description = "This API will create a new employee",
            responses = {
                    @ApiResponse(
                            responseCode = ValidationMessages.HTTP_STATUS_CODE_200,
                            description = ValidationMessages.HTTP_STATUS_CODE_200_DESCRIPTION,
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = EmployeeRepresentation.class))
                            }),
                    @ApiResponse(
                            responseCode = ValidationMessages.HTTP_STATUS_CODE_400,
                            description = ValidationMessages.HTTP_STATUS_CODE_400_DESCRIPTION,
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = ErrorResponseBody.class))
                            })
            })
    @PostMapping(value = "employee")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<EmployeeRepresentation> create(
             @RequestBody @Validated(Save.class) EmployeeRepresentation employeeRepresentation,
            Authentication authentication) {
        log.debug(" employee object to create ");
        ResponseBody<EmployeeRepresentation> responseBody = employeeService.save(authentication, employeeRepresentation);
        log.debug(" employee object to created employee id : {} ",responseBody.getPayload().getEmployeeId());
        return new ResponseEntity<>(responseBody.getPayload(), responseBody.getStatus());
    }

    @Operation(
            summary = "Update Employee",
            description = "This API will update employee",
            responses = {
                    @ApiResponse(
                            responseCode = ValidationMessages.HTTP_STATUS_CODE_200,
                            description = ValidationMessages.HTTP_STATUS_CODE_200_DESCRIPTION,
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = EmployeeRepresentation.class))
                            }),
                    @ApiResponse(
                            responseCode = ValidationMessages.HTTP_STATUS_CODE_400,
                            description = ValidationMessages.HTTP_STATUS_CODE_400_DESCRIPTION,
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = ErrorResponseBody.class))
                            })
            })
    @PutMapping(value = "employee/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<EmployeeRepresentation> update(
            @PathVariable(value = "id") String id,
            @Valid @RequestBody EmployeeRepresentation employeeRepresentation,
            Authentication authentication) {
        log.debug(" employee object to update : {} ", id);
        employeeRepresentation.setEmployeeId(id);
        ResponseBody<EmployeeRepresentation> responseBody = employeeService.update(authentication, employeeRepresentation);
        log.debug(" employee object to updated for employee id : {} ", id);
        return new ResponseEntity<>(responseBody.getPayload(), responseBody.getStatus());
    }


    @Operation(
            summary = "Employee Details",
            description = "This API will fetch employee details for the input id",
            responses = {
                    @ApiResponse(
                            responseCode = ValidationMessages.HTTP_STATUS_CODE_200,
                            description = ValidationMessages.HTTP_STATUS_CODE_200_DESCRIPTION,
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = EmployeeRepresentation.class))
                            }),
                    @ApiResponse(
                            responseCode = ValidationMessages.HTTP_STATUS_CODE_400,
                            description = ValidationMessages.HTTP_STATUS_CODE_400_DESCRIPTION,
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = ErrorResponseBody.class))
                            })
            })
    @GetMapping(value = "employee/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<EmployeeRepresentation> get(
            @PathVariable(value = "id") String id,
            Authentication authentication) {
        log.debug(" get employee object : {} ", id);
        ResponseBody<EmployeeRepresentation> responseBody = employeeService.get(authentication, id);
        log.debug(" Fetched employee object for employee id : {} ", id);
        return new ResponseEntity<>(responseBody.getPayload(), responseBody.getStatus());
    }

    @Operation(
            summary = " Search Employee",
            description = "This API provide the search ability for employee",
            responses = {
                    @ApiResponse(
                            responseCode = ValidationMessages.HTTP_STATUS_CODE_200,
                            description = ValidationMessages.HTTP_STATUS_CODE_200_DESCRIPTION,
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = EmployeeRepresentation.class))
                            }),
                    @ApiResponse(
                            responseCode = ValidationMessages.HTTP_STATUS_CODE_400,
                            description = ValidationMessages.HTTP_STATUS_CODE_400_DESCRIPTION,
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = ErrorResponseBody.class))
                            })
            })
    @PostMapping(value = "employee/search")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<SearchResponseRepresentation<EmployeeRepresentation>> search(
            @RequestParam(name = QueryParams.PAGE_NO, defaultValue = QueryParams.PAGE_NO_DEFAULT_VALUE, required = false)
            @Pattern(regexp = "^[1-9]\\d*$", message = ValidationMessages.REQUEST_PARAM_PAGE_NO_INVALID)
            String pageNo,
            @RequestParam(name = QueryParams.PAGE_SIZE, defaultValue = QueryParams.PAGE_SIZE_DEFAULT_VALUE, required = false)
            @Pattern(regexp = "^[1-9]\\d*$", message = ValidationMessages.REQUEST_PARAM_PAGE_SIZE_INVALID)
            String pageSize,
            @RequestParam(name = QueryParams.SORT, defaultValue = QueryParams.SORT_DEFAULT_VALUE_UPDATED_DESC, required = false)
            String sort,
            @RequestBody(required = false)
            SearchRequestRepresentation searchRequestRepresentation,
            Authentication authentication) {
        log.info(
                " Retrieving employee(s) | filters:: pageNo: {} | pageSize:  {} | sort:  {} ",
                pageNo,
                pageSize,
                sort);
        ResponseBody<SearchResponseRepresentation<EmployeeRepresentation>> responseBody = employeeService
                .searchEmployee(authentication, Integer.parseInt(pageNo), Integer.parseInt(pageSize), sort, searchRequestRepresentation);

        return new ResponseEntity<>(responseBody.getPayload(), responseBody.getStatus());
    }


    @Operation(
            summary = "Create Address",
            description = "This API will create a new address for employee",
            responses = {
                    @ApiResponse(
                            responseCode = ValidationMessages.HTTP_STATUS_CODE_200,
                            description = ValidationMessages.HTTP_STATUS_CODE_200_DESCRIPTION,
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = EmployeeRepresentation.class))
                            }),
                    @ApiResponse(
                            responseCode = ValidationMessages.HTTP_STATUS_CODE_400,
                            description = ValidationMessages.HTTP_STATUS_CODE_400_DESCRIPTION,
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = ErrorResponseBody.class))
                            })
            })
    @PostMapping(value = "employee/{id}/address")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<AddressRepresentation> createEmployeeAddress(
            @PathVariable(value = "id") String id,
            @Valid @RequestBody AddressRepresentation addressRepresentation,
            Authentication authentication) {
        log.debug(" Creating address for  employee id {} ",id);
        addressRepresentation.setEmployeeId(id);
        ResponseBody<AddressRepresentation> responseBody = employeeService.saveEmployeeAddress(authentication, addressRepresentation,id);
        log.debug(" Created address for  employee id {} ",id);
        return new ResponseEntity<>(responseBody.getPayload(), responseBody.getStatus());
    }

    @Operation(
            summary = "Update Address",
            description = "This API will update address for employee",
            responses = {
                    @ApiResponse(
                            responseCode = ValidationMessages.HTTP_STATUS_CODE_200,
                            description = ValidationMessages.HTTP_STATUS_CODE_200_DESCRIPTION,
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = EmployeeRepresentation.class))
                            }),
                    @ApiResponse(
                            responseCode = ValidationMessages.HTTP_STATUS_CODE_400,
                            description = ValidationMessages.HTTP_STATUS_CODE_400_DESCRIPTION,
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = ErrorResponseBody.class))
                            })
            })

    @PutMapping(value = "employee/{id}/address/{addId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<AddressRepresentation> updateEmployeeAddress(
            @PathVariable(value = "id") String id,
            @PathVariable(value = "addId") String addId,
            @Valid @RequestBody AddressRepresentation addressRepresentation,
            Authentication authentication) {
        log.debug(" Updating address for  employee id {} | address Id {} ",id,addId);
        addressRepresentation.setEmployeeId(id);
        addressRepresentation.setAddressId(addId);
        ResponseBody<AddressRepresentation> responseBody = employeeService.updateEmployeeAddress(authentication, addressRepresentation);
        log.debug(" updated address for  employee id {} | address id {} ",id,addId);
        return new ResponseEntity<>(responseBody.getPayload(), responseBody.getStatus());
    }


    @Operation(
            summary = " Search Address",
            description = "This API provide the search ability for Address",
            responses = {
                    @ApiResponse(
                            responseCode = ValidationMessages.HTTP_STATUS_CODE_200,
                            description = ValidationMessages.HTTP_STATUS_CODE_200_DESCRIPTION,
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = EmployeeRepresentation.class))
                            }),
                    @ApiResponse(
                            responseCode = ValidationMessages.HTTP_STATUS_CODE_400,
                            description = ValidationMessages.HTTP_STATUS_CODE_400_DESCRIPTION,
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = ErrorResponseBody.class))
                            })
            })
    @PostMapping(value = "address/search")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<SearchResponseRepresentation<AddressRepresentation>> searchAddress(
            @RequestParam(name = QueryParams.PAGE_NO, defaultValue = QueryParams.PAGE_NO_DEFAULT_VALUE, required = false)
            @Pattern(regexp = "^[1-9]\\d*$", message = ValidationMessages.REQUEST_PARAM_PAGE_NO_INVALID)
            String pageNo,
            @RequestParam(name = QueryParams.PAGE_SIZE, defaultValue = QueryParams.PAGE_SIZE_DEFAULT_VALUE, required = false)
            @Pattern(regexp = "^[1-9]\\d*$", message = ValidationMessages.REQUEST_PARAM_PAGE_SIZE_INVALID)
            String pageSize,
            @RequestParam(name = QueryParams.SORT, defaultValue = QueryParams.SORT_DEFAULT_VALUE_UPDATED_DESC, required = false)
            String sort,
            @RequestBody(required = false)
            SearchRequestRepresentation searchRequestRepresentation,
            Authentication authentication) {
        log.info(
                " Retrieving employee(s) addresses | filters:: pageNo: {} | pageSize:  {} | sort:  {} ",
                pageNo,
                pageSize,
                sort);
        ResponseBody<SearchResponseRepresentation<AddressRepresentation>> responseBody = employeeService
                .searchEmployeeAddress(authentication, Integer.parseInt(pageNo), Integer.parseInt(pageSize), sort, searchRequestRepresentation);

        return new ResponseEntity<>(responseBody.getPayload(), responseBody.getStatus());
    }


    @Operation(
            summary = " Delete Employee",
            description = "This API delete the employee",
            responses = {
                    @ApiResponse(
                            responseCode = ValidationMessages.HTTP_STATUS_CODE_200,
                            description = ValidationMessages.HTTP_STATUS_CODE_200_DESCRIPTION,
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = EmployeeRepresentation.class))
                            }),
                    @ApiResponse(
                            responseCode = ValidationMessages.HTTP_STATUS_CODE_400,
                            description = ValidationMessages.HTTP_STATUS_CODE_400_DESCRIPTION,
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = ErrorResponseBody.class))
                            })
            })
    @DeleteMapping(value = "employee/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<DeleteRepresentation> delete(
            @PathVariable(value = "id") String id,
            Authentication authentication) {
        log.debug(" employee object to delete : {} ", id);
        ResponseBody<DeleteRepresentation> responseBody = employeeService.delete(authentication, id);
        log.debug(" employee object deleted: {} ", id);
        return new ResponseEntity<>(responseBody.getPayload(), responseBody.getStatus());
    }


    @Operation(
            summary = " Delete Address",
            description = "This API delete the Address",
            responses = {
                    @ApiResponse(
                            responseCode = ValidationMessages.HTTP_STATUS_CODE_200,
                            description = ValidationMessages.HTTP_STATUS_CODE_200_DESCRIPTION,
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = EmployeeRepresentation.class))
                            }),
                    @ApiResponse(
                            responseCode = ValidationMessages.HTTP_STATUS_CODE_400,
                            description = ValidationMessages.HTTP_STATUS_CODE_400_DESCRIPTION,
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = ErrorResponseBody.class))
                            })
            })
    @DeleteMapping(value = "address/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<DeleteRepresentation> deleteAddress(
            @PathVariable(value = "id") String id,
            Authentication authentication) {
        log.debug(" Address object to delete : {} ", id);
        ResponseBody<DeleteRepresentation> responseBody = employeeService.deleteAddress(authentication, id);
        log.debug(" Address object deleted: {} ", id);
        return new ResponseEntity<>(responseBody.getPayload(), responseBody.getStatus());
    }

}
