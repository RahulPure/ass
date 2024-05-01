package org.sample.assignment.constants;

public class ValidationMessages
{
    public static final String RESOURCE_NOT_FOUND = "{0} with id ''{1}'' is not found in the system.";
    public static final String REQUEST_PARAM_PAGE_NO_INVALID = "Request param 'pageNo' must be a positive integer value.";
    public static final String REQUEST_PARAM_PAGE_SIZE_INVALID = "Request param 'pageSize' must be a positive integer value.";
    public static final String REQUIRED_VALIDATION_ERR_MSG =
            "{0} is required. It must not be empty or null.";
    public static final String ENTITY_NAME_REGEX_ALLOW_SPACE_AT_SIGN = "^[a-zA-Z0-9-_@., ]+$|\\s*";
    public static final String EMAIL_REGEX =
            "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" +
                    "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    public static final String MOBILE_NUMBER_REGEX = "^[0-9]{10}$";

    public static final String EMPLOYEE_FIRST_NAME_VALIDATION_ERR_MSG =
            "Employee first name '${validatedValue}' is invalid. Only alphanumeric, hyphen, period, at sign, space and underscore characters are allowed.";

    public static final String EMPLOYEE_LAST_NAME_VALIDATION_ERR_MSG =
            "Employee last name '${validatedValue}' is invalid. Only alphanumeric, hyphen, period, at sign, space and underscore characters are allowed.";

    public static final String EMAIL_VALIDATION_ERR_MSG =
            "Employee email '${validatedValue}' is invalid.";
    public static final String MOBILE_VALIDATION_ERR_MSG =
            "Employee phone '${validatedValue}' is invalid.";
    public static final String HEADER_MISSING = "Request header ''{0}'' is required. It must not be empty or null.";
    public static final String REQUEST_BODY_NOT_PRESENT = "Request body is required.";
    public static final String UNEXPECTED_ERROR = "Something went wrong. Please try again later. If the issue persists contact, our support team.";
    public static final String INVALID_REQUEST_BODY = "Request body is invalid.";
    public static final String ALLOWED_SINGLE_VALUE_VALIDATION = "{0} ''{1}'' is invalid. Valid value is {2}.";

    public static final String HTTP_STATUS_CODE_200 = "200";
    public static final String HTTP_STATUS_CODE_200_DESCRIPTION = "Success";
    public static final String HTTP_STATUS_CODE_400 = "400";
    public static final String HTTP_STATUS_CODE_400_DESCRIPTION =
            "Bad Request: Details provided to create/update resource are not valid";
    public static final String EMAIL_ALREADY_EXIST = "Email id ''{0}'' already exists.";
    public static final String APPLICATION_JSON_VALUE = "application/json";
    public static final String SEARCH_RESULT_NOT_FOUND = "{0} not found for the search request.";
    public static final String HTTP_STATUS_CODE_401 = "401";

    public static final String HTTP_STATUS_CODE_403 = "403";
    public static final String HTTP_STATUS_CODE_403_DESCRIPTION =
            "Forbidden: User not permitted to access this resource. Please contact administrator to get the access";

    public static final String HTTP_STATUS_CODE_401_DESCRIPTION =
            "Unauthorized: User not authorized to perform this operation. Please contact administrator to get the access";
}
