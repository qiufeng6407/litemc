package pub.qiuf.litemc.common.util;

public abstract class ArrayUtils {

    public static byte[] merge(byte[] a1, byte[] a2) {
        byte[] a = new byte[a1.length + a2.length];
        System.arraycopy(a1, 0, a, 0, a1.length);
        System.arraycopy(a2, 0, a, a1.length, a2.length);
        return a;
    }

    public static byte[] merge(byte[]... as) {
        if (as == null || as.length == 0) {
            return new byte[0];
        }
        byte[] result = new byte[0];
        for (byte[] a : as) {
            result = merge(result, a);
        }
        return result;
    }

}
