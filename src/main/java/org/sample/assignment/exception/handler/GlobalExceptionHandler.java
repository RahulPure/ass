package org.sample.assignment.exception.handler;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.IteratorUtils;
import org.sample.assignment.constants.ValidationMessages;
import org.sample.assignment.exception.*;
import org.sample.assignment.model.ErrorResponseBody;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.MimeType;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.text.MessageFormat;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.sample.assignment.errorcodes.ApplicationErrorCode.*;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final String LOG_MESSAGE =
            " Exception: {} | HttpStatus: {} | ApplicationErrorCode: {} | Message: {} | Path:{}";

    @ExceptionHandler(value = MissingRequestHeaderException.class)
    public ResponseEntity<Object> missingRequestHeaderExceptionHandler(
            MissingRequestHeaderException ex, WebRequest request) {
        log.debug(
                LOG_MESSAGE,
                ex.getClass(),
                HttpStatus.BAD_REQUEST,
                REQUEST_HEADER_NOT_FOUND.getCode(),
                ex.getMessage(),
                request.getContextPath());
        return new ResponseEntity<>(
                new ErrorResponseBody<>(
                        REQUEST_HEADER_NOT_FOUND.getCode(),
                        MessageFormat.format(ValidationMessages.HEADER_MISSING, ex.getHeaderName())),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = NoHandlerFoundException.class)
    public ResponseEntity<Object> noHandlerFoundExceptionHandler(
            NoHandlerFoundException ex, WebRequest request) {
        log.debug(
                LOG_MESSAGE,
                ex.getClass(),
                HttpStatus.METHOD_NOT_ALLOWED,
                METHOD_NOT_ALLOWED.getCode(),
                ex.getMessage(),
                request.getContextPath());
        return new ResponseEntity<>(
                new ErrorResponseBody<>(METHOD_NOT_ALLOWED.getCode(), ex.getMessage()),
                HttpStatus.METHOD_NOT_ALLOWED);
    }

    /*Request body  missing*/
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity<Object> httpMessageNotReadableExceptionHandler(
            HttpMessageNotReadableException ex, WebRequest request) {
        log.debug(
                LOG_MESSAGE,
                ex.getClass(),
                HttpStatus.BAD_REQUEST,
                REQUEST_BODY_INVALID.getCode(),
                ex.getMessage(),
                request.getContextPath());
        if (Objects.isNull(ex.getCause())) {
            return new ResponseEntity<>(
                    new ErrorResponseBody<>(
                            REQUEST_BODY_NOT_FOUND.getCode(), ValidationMessages.REQUEST_BODY_NOT_PRESENT),
                    HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(
                new ErrorResponseBody<>(
                        REQUEST_BODY_INVALID.getCode(), ValidationMessages.INVALID_REQUEST_BODY),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = AuthenticationException.class)
    public ResponseEntity<Object> unauthorizedHandler(
            AuthenticationException ex, WebRequest request) {
        log.debug("Unauthorized resource requested :: {}", ex.getMessage());
        return new ResponseEntity<>(
                new ErrorResponseBody<>(ex.getCode().getCode(), ex.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = AuthorizationException.class)
    public ResponseEntity<Object> forbiddenHandler(AuthorizationException ex, WebRequest request) {
        log.debug("Forbidden resource requested :: {}", ex.getMessage());
        return new ResponseEntity<>(
                new ErrorResponseBody<>(ex.getCode().getCode(), ex.getMessage()), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Object> httpRequestMethodNotSupportedExceptionHandler(
            HttpRequestMethodNotSupportedException ex, WebRequest request) {
        log.debug("Method Not Supported Exception :: {}", ex.getMessage());
        return new ResponseEntity<>(
                new ErrorResponseBody<>(HTTP_METHOD_INVALID.getCode(), ex.getMessage() + "."),
                HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> constraintViolationExceptionHandler(
            ConstraintViolationException ex, WebRequest request) {
        List<FieldValidation> violations =
                ex.getConstraintViolations().stream()
                        .map(e -> new FieldValidation(e.getPropertyPath().toString(), e.getMessage()))
                        .toList();
        log.debug(
                "Constraint Violation Exception :: HttpStatus: {} | ApplicationErrorCode: {} | Message: {} | Path:{} ",
                HttpStatus.BAD_REQUEST,
                CONSTRAINT_VIOLATION.getCode(),
                violations,
                request.getContextPath());
        return new ResponseEntity<>(
                new ErrorResponseBody<>(CONSTRAINT_VIOLATION.getCode(), null, violations),
                HttpStatus.BAD_REQUEST);
    }
    /*field validation exceptions*/
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Object> methodArgumentNotValidExceptionHandler(
            MethodArgumentNotValidException ex, WebRequest request) {
        List<FieldValidation> violations =
                ex.getBindingResult().getAllErrors().stream()
                        .map(e -> new FieldValidation(((FieldError) e).getField(), e.getDefaultMessage()))
                        .toList();
        log.debug(
                LOG_MESSAGE,
                ex.getClass(),
                HttpStatus.BAD_REQUEST,
                FIELD_VALIDATION_ERROR.getCode(),
                violations,
                request.getContextPath());

        return new ResponseEntity<>(
                new ErrorResponseBody<>(CONSTRAINT_VIOLATION.getCode(), null, violations),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = ResourceConflictException.class)
    public ResponseEntity<Object> resourceConflictExceptionHandler(
            ResourceConflictException ex, WebRequest request) {
        log.debug(
                "Resource Conflict Exception :: ApplicationErrorCode: {} | Message: {}",
                ex.getCode(),
                ex.getMessage());
        return new ResponseEntity<>(
                new ErrorResponseBody<>(ex.getCode().getCode(), ex.getMessage()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<Object> resourceNotFoundExceptionHandler(
            ResourceNotFoundException ex, WebRequest request) {
        log.debug(
                "Resource Not Found Exception :: ApplicationErrorCode: {} | Message: {}",
                ex.getCode(),
                ex.getMessage());
        return new ResponseEntity<>(
                new ErrorResponseBody<>(ex.getCode().getCode(), ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = BadFilterRequestException.class)
    public ResponseEntity<Object> badFilterRequestExceptionHandler(
            BadFilterRequestException ex, WebRequest request) {
        log.debug(
                "Bad Filter Request Exception :: ApplicationErrorCode: {} | Message: {}",
                ex.getCode(),
                ex.getMessage());
        return new ResponseEntity<>(
                new ErrorResponseBody<>(ex.getCode().getCode(), ex.getMessage()), HttpStatus.BAD_REQUEST);
    }



    @ExceptionHandler(value = ApplicationException.class)
    public ResponseEntity<Object> applicationExceptionHandler(
            ApplicationException e, WebRequest request) {
        log.debug("Application Exception :: {}", e.getMessage());
        return new ResponseEntity<>(
                new ErrorResponseBody<>(e.getCode().getCode(), e.getMessage()), e.getStatus());
    }

    @ExceptionHandler(value = RepresentationException.class)
    public ResponseEntity<Object> representationExceptionExceptionHandler(
            RepresentationException e, WebRequest request) {
        log.debug("Representation Exception :: {}", e.getMessage());
        return new ResponseEntity<>(
                new ErrorResponseBody<>(e.getCode().getCode(), e.getMessage()), e.getStatus());
    }

    @ExceptionHandler(value = HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<Object> unsupportedMediaTypeExceptionHandler(
            HttpMediaTypeNotSupportedException ex, WebRequest request) {

        log.debug(
                LOG_MESSAGE,
                ex.getClass(),
                HttpStatus.UNSUPPORTED_MEDIA_TYPE,
                REQUEST_HEADER_INVALID.getCode(),
                ex.getMessage(),
                request.getContextPath());

        /*Validates request Content-Type header*/
        if (IteratorUtils.toList(request.getHeaderNames()).stream()
                .noneMatch(h -> h.equalsIgnoreCase(HttpHeaders.CONTENT_TYPE))) {
            return new ResponseEntity<>(
                    new ErrorResponseBody<>(
                            REQUEST_HEADER_NOT_FOUND.getCode(),
                            MessageFormat.format(ValidationMessages.HEADER_MISSING, HttpHeaders.CONTENT_TYPE)),
                    HttpStatus.UNSUPPORTED_MEDIA_TYPE);
        } else {
            return new ResponseEntity<>(
                    new ErrorResponseBody<>(
                            REQUEST_HEADER_INVALID.getCode(),
                            MessageFormat.format(
                                    ValidationMessages.ALLOWED_SINGLE_VALUE_VALIDATION,
                                    HttpHeaders.CONTENT_TYPE,
                                    request.getHeader(HttpHeaders.CONTENT_TYPE),
                                    ex.getSupportedMediaTypes().stream()
                                            .map(MimeType::toString)
                                            .collect(Collectors.joining(",")))),
                    HttpStatus.UNSUPPORTED_MEDIA_TYPE);
        }
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<Object> illegalArgumentExceptionHandler(
            IllegalArgumentException e, WebRequest request) {
        log.debug("Illegal Argument Exception :: {}", e.getMessage());
        return new ResponseEntity<>(
                new ErrorResponseBody<>(REQUEST_BODY_INVALID.getCode(), e.getMessage()),
                HttpStatus.BAD_REQUEST);
    }
}
