package org.sample.assignment.utils;

import org.sample.assignment.constants.EntityType;
import org.sample.assignment.errorcodes.ApplicationErrorCode;
import org.sample.assignment.exception.ApplicationException;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

/**
 * @author RahulPure
 */
public class UUIDGenerator
{
    private UUIDGenerator() {}

    private static final String JOINT = "-";

    public static String generateUniversalId() {
        return UUID5
                .fromUTF8(generateSalt().concat(String.valueOf(LocalDateTime.now().getNano())))
                .toString();
    }
    public static String generateUniversalId(EntityType entityType) {
        return new StringBuilder()
                .append(entityType.identifierPrefix)
                .append(JOINT)
                .append(UUID5.fromUTF8(generateSalt().concat(String.valueOf(System.nanoTime()))))
                .toString();
    }
    private static String generateSalt() {
        byte[] array = new byte[7]; // length is bounded by 7
        try {
            new Random().nextBytes(array);
        } catch (Exception e) {
            throw new ApplicationException(e.getMessage(), ApplicationErrorCode.UNEXPECTED_ERROR);
        }
        return new String(array, StandardCharsets.UTF_8);
    }

    public static String getUUID() {
        return UUID.randomUUID().toString();
    }

    public static String getUUIDNoDash() {
        return getUUID().replace("-", "");
    }

    public static String getUUID16Digit() {
        return getUUIDNoDash().substring(0, 16);
    }

}
