package pub.qiuf.litemc.common.util;

import com.google.common.io.BaseEncoding;

public abstract class ByteUtils {
    public static String bytestoHexString(byte[] b) {
        return BaseEncoding.base16().upperCase().encode(b);
    }

    public static byte[] hexStringtoBytes(String hexString) {
        return BaseEncoding.base16().upperCase().decode(hexString);
    }

    public static String getUUIDString(byte[] b) {
        String s = BaseEncoding.base16().upperCase().encode(b);
        return String.format("%s-%s-%s-%s-%s", s.substring(0, 8), s.substring(8, 12), s.substring(12, 16), s.substring(16, 20), s.substring(20, 32));
    }

    public static byte[] getBytes(short data) {
        byte[] bytes = new byte[2];
        bytes[0] = (byte) (data & 0xff);
        bytes[1] = (byte) ((data & 0xff00) >> 8);
        return bytes;
    }

    public static byte[] getBytes(char data) {
        byte[] bytes = new byte[2];
        bytes[0] = (byte) (data);
        bytes[1] = (byte) (data >> 8);
        return bytes;
    }

    public static byte[] getBytes(int data) {
        byte[] bytes = new byte[4];
        bytes[0] = (byte) (data & 0xff);
        bytes[1] = (byte) ((data & 0xff00) >> 8);
        bytes[2] = (byte) ((data & 0xff0000) >> 16);
        bytes[3] = (byte) ((data & 0xff000000) >> 24);
        return bytes;
    }

    public static byte[] getBytes(long data) {
        byte[] bytes = new byte[8];
        bytes[7] = (byte) (data & 0xff);
        bytes[6] = (byte) ((data >> 8) & 0xff);
        bytes[5] = (byte) ((data >> 16) & 0xff);
        bytes[4] = (byte) ((data >> 24) & 0xff);
        bytes[3] = (byte) ((data >> 32) & 0xff);
        bytes[2] = (byte) ((data >> 40) & 0xff);
        bytes[1] = (byte) ((data >> 48) & 0xff);
        bytes[0] = (byte) ((data >> 56) & 0xff);
        return bytes;
    }

    public static byte[] getBytes(float data) {
        int intBits = Float.floatToIntBits(data);
        return getBytes(intBits);
    }

    public static byte[] getBytes(double data) {
        long longBits = Double.doubleToLongBits(data);
        return getBytes(longBits);
    }

    public static short getShort(byte[] bytes) {
        return (short) ((0xff & bytes[0]) | (0xff00 & (bytes[1] << 8)));
    }

    public static char getChar(byte[] bytes) {
        return (char) ((0xff & bytes[0]) | (0xff00 & (bytes[1] << 8)));
    }

    public static int getInt(byte[] bytes) {
        return (0xff & bytes[0]) | (0xff00 & (bytes[1] << 8)) | (0xff0000 & (bytes[2] << 16)) | (0xff000000 & (bytes[3] << 24));
    }

    public static long getLong(byte[] bytes) {
        return (0xffL & (long) bytes[0]) | (0xff00L & ((long) bytes[1] << 8)) | (0xff0000L & ((long) bytes[2] << 16))
                | (0xff000000L & ((long) bytes[3] << 24)) | (0xff00000000L & ((long) bytes[4] << 32)) | (0xff0000000000L & ((long) bytes[5] << 40))
                | (0xff000000000000L & ((long) bytes[6] << 48)) | (0xff00000000000000L & ((long) bytes[7] << 56));
    }

    public static long getUnsignedLong(byte[] positionBytes) {
        return (((long) positionBytes[0] << 56) + ((long) (positionBytes[1] & 255) << 48) + ((long) (positionBytes[2] & 255) << 40)
                + ((long) (positionBytes[3] & 255) << 32) + ((long) (positionBytes[4] & 255) << 24) + ((positionBytes[5] & 255) << 16)
                + ((positionBytes[6] & 255) << 8) + ((positionBytes[7] & 255) << 0));
    }

    public static float getFloat(byte[] bytes) {
        return Float.intBitsToFloat(getInt(bytes));
    }

    public static double getDouble(byte[] bytes) {
        long l = (((long) bytes[0] << 56) + ((long) (bytes[1] & 255) << 48) + ((long) (bytes[2] & 255) << 40) + ((long) (bytes[3] & 255) << 32)
                + ((long) (bytes[4] & 255) << 24) + ((bytes[5] & 255) << 16) + ((bytes[6] & 255) << 8) + ((bytes[7] & 255) << 0));
        return Double.longBitsToDouble(l);
    }

}
