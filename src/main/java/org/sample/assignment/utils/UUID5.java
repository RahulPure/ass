package org.sample.assignment.utils;

import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.util.UUID;

/**
 * @author RahulPure
 */
public class UUID5
{
    public static UUID fromUTF8(String input) {
        return fromBytes(input.getBytes(Charset.forName("UTF-8")));
    }

    private static UUID fromBytes(byte[] input) {
        if (Objects.isNull(input)) {
            throw new NullPointerException("input == null");
        }
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            return makeUUID(md.digest(input), 5);
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError(e);
        }
    }

    private static UUID makeUUID(byte[] hash, int version) {
        long msb = peekLong(hash, 0, ByteOrder.BIG_ENDIAN);
        long lsb = peekLong(hash, 8, ByteOrder.BIG_ENDIAN);
        // Set the version field
        msb &= ~(0xfL << 12);
        msb |= ((long) version) << 12;
        // Set the variant field to 2
        lsb &= ~(0x3L << 62);
        lsb |= 2L << 62;
        return new UUID(msb, lsb);
    }

    private static long peekLong(final byte[] src, final int offset, final ByteOrder order) {
        long ans = 0;
        if (order == ByteOrder.BIG_ENDIAN) {
            for (int i = offset; i < offset + 8; i += 1) {
                ans <<= 8;
                ans |= src[i] & 0xffL;
            }
        } else {
            for (int i = offset + 7; i >= offset; i -= 1) {
                ans <<= 8;
                ans |= src[i] & 0xffL;
            }
        }
        return ans;
    }
}
