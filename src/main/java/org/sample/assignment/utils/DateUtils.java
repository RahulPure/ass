package org.sample.assignment.utils;

import lombok.extern.slf4j.Slf4j;
import org.sample.assignment.constants.Delimiter;
import org.sample.assignment.constants.ValidationConstants;
import org.sample.assignment.errorcodes.ApplicationErrorCode;
import org.sample.assignment.exception.DataFormatException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

/**
 * @author RahulPure
 */
@Slf4j
public class DateUtils {
    private DateUtils() {}

    public static String convertToString(Long longDate) {
        Date date = Objects.isNull(longDate) ? null : new Date(longDate);
        if (Objects.isNull(date)) return Delimiter.EMPTY_STRING.title();
        return new SimpleDateFormat(ValidationConstants.DATE_FORMAT_MM_DD_YYYY_HH_MM_SS_ZZZ).format(date);
    }

    public static String convertToString(ZonedDateTime dateTime) {
        if (Objects.isNull(dateTime)) return Delimiter.EMPTY_STRING.title();
        dateTime = dateTime.withZoneSameInstant(ZoneId.of(ValidationConstants.DATE_FORMAT_DEFAULT_TIMEZONE));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ValidationConstants.DATE_FORMAT_MM_DD_YYYY_HH_MM_SS_ZZZ);
        return dateTime.format(formatter);
    }

    public static ZonedDateTime currentDateTime() {
        return ZonedDateTime.now(ZoneId.of(ValidationConstants.DATE_FORMAT_DEFAULT_TIMEZONE));
    }

    public static ZonedDateTime toDateTime(String dateString) {

        if (Objects.isNull(dateString) || StringUtils.isEmpty(dateString)) return null;
        DateFormat sdf = new SimpleDateFormat(ValidationConstants.DATE_FORMAT_MM_DD_YYYY_HH_MM_SS_ZZZ);
        sdf.setLenient(false);
        try {
            return ZonedDateTime.ofInstant(
                    sdf.parse(dateString).toInstant(), ZoneId.of(ValidationConstants.DATE_FORMAT_DEFAULT_TIMEZONE));
        } catch (ParseException e) {
            log.error(
                    "Date parsing exception :: expected format: {}", ValidationConstants.DATE_FORMAT_MM_DD_YYYY_HH_MM_SS_ZZZ);
            throw new DataFormatException(e.getMessage(), ApplicationErrorCode.CONSTRAINT_VIOLATION);
        }
    }

    public static ZonedDateTime toDate(String dateString) {

        if (Objects.isNull(dateString) || StringUtils.isEmpty(dateString)) return null;
        DateFormat sdf = new SimpleDateFormat(ValidationConstants.DATE_FORMAT_MM_DD_YYYY);
        sdf.setLenient(false);
        try {
            return ZonedDateTime.ofInstant(
                    sdf.parse(dateString).toInstant(), ZoneId.of(ValidationConstants.DATE_FORMAT_DEFAULT_TIMEZONE));
        } catch (ParseException e) {
            log.error("Date parsing exception :: expected format: {}", ValidationConstants.DATE_FORMAT_MM_DD_YYYY);
            throw new DataFormatException(e.getMessage(), ApplicationErrorCode.CONSTRAINT_VIOLATION);
        }
    }

    public static boolean checkForValidDate(String dateString) {
        return checkForValidDate(dateString, ValidationConstants.DATE_FORMAT_MM_DD_YYYY_HH_MM_SS_ZZZ);
    }

    public static boolean checkForValidDate(String dateString, String format) {
        DateFormat sdf = new SimpleDateFormat(format);
        sdf.setLenient(false);
        try {
            sdf.parse(dateString);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    public static Long calculateElapsedTime(ZonedDateTime startDateTime, ZonedDateTime endDateTime) {
        if (Objects.isNull(startDateTime) || Objects.isNull(endDateTime)) {
            return null;
        }
        return java.time.Duration.between(startDateTime, endDateTime).getSeconds();
    }
}
