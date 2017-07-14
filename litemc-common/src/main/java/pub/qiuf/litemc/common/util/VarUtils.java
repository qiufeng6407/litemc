package pub.qiuf.litemc.common.util;

import java.nio.ByteBuffer;
import java.util.Arrays;

public abstract class VarUtils {

    public static void main(String[] args) throws Exception {
        System.out.println(decodeVarInt(ByteUtils.hexStringtoBytes("10")));
        System.out.println(new String(ByteUtils.hexStringtoBytes("6C6F63616C686F7374")));
        System.out.println(ByteUtils.bytestoHexString(encodeVarInt(344403158)));
    }

    public static int nextVarIntLength(byte[] b) {
        for (int i = 0; i < b.length; i++) {
            if (i > 4) {
                throw new RuntimeException("VarInt is too long!");
            }
            if ((b[i] & 0b10000000) == 0) {
                return (i + 1);
            }
        }
        return -1;
    }

    public static boolean canDecodeVarInt(byte[] b) {
        for (int i = 0; i < b.length; i++) {
            if (i > 4) {
                throw new RuntimeException("VarInt is too long!");
            }
            if ((b[i] & 0b10000000) == 0) {
                return true;
            }
        }
        return false;
    }

    public static int decodeVarInt(byte[] b) {
        int result = 0;
        for (int i = 0; i < b.length; i++) {
            if (i > 4) {
                throw new RuntimeException("VarInt is too big");
            }
            int value = (b[i] & 0b01111111);
            result |= (value << (7 * i));
            if ((b[i] & 0b10000000) == 0) {
                return result;
            }
        }
        throw new RuntimeException("VarInt is too short!");
    }

    public static byte[] encodeVarInt(int value) throws Exception {
        ByteBuffer buf = ByteBuffer.allocate(5);
        do {
            byte temp = (byte) (value & 0b01111111);
            value >>>= 7;
            if (value != 0) {
                temp |= 0b10000000;
            }
            buf.put(temp);
        } while (value != 0);
        buf.flip();
        return Arrays.copyOfRange(buf.array(), 0, buf.limit());
    }

}
