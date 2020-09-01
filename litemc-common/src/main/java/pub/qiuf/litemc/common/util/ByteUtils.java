package pub.qiuf.litemc.common.util;

import com.flowpowered.nbt.Tag;
import com.google.common.base.Strings;
import com.google.common.io.BaseEncoding;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import pub.qiuf.litemc.common.bean.datatype.*;

import java.util.Arrays;
import java.util.UUID;

public abstract class ByteUtils {
    public static String bytestoHexString(byte[] b) {
        return BaseEncoding.base16().upperCase().encode(b);
    }

    public static byte[] hexStringtoBytes(String hexString) {
        return BaseEncoding.base16().upperCase().decode(hexString);
    }

    public static byte encodeByte(byte val) {
        return val;
    }

    public static byte encodeAngle(byte yaw) {
        return yaw;
    }

    public static byte[] encodeInt(int data) {
        byte[] bytes = new byte[4];
        bytes[0] = (byte) (data & 0xff);
        bytes[1] = (byte) ((data & 0xff00) >> 8);
        bytes[2] = (byte) ((data & 0xff0000) >> 16);
        bytes[3] = (byte) ((data & 0xff000000) >> 24);
        return bytes;
    }

    public static byte[] encodeVarInt(int value) throws Exception {
        byte[] bytes = new byte[5];
        int index = 0;
        do {
            byte temp = (byte) (value & 0b01111111);
            value >>>= 7;
            if (value != 0) {
                temp |= 0b10000000;
            }
            bytes[index++] = temp;
        } while (value != 0);
        return Arrays.copyOfRange(bytes, 0, index);
    }

    public static byte[] encodeVarLong(long value) {
        byte[] bytes = new byte[10];
        int index = 0;
        do {
            byte temp = (byte)(value & 0b01111111);
            value >>>= 7;
            if (value != 0) {
                temp |= 0b10000000;
            }
            bytes[index++] = temp;
        } while (value != 0);
        return Arrays.copyOfRange(bytes, 0, index + 1);
    }

    public static byte[] encodeUnsignedShort(int data) {
        byte[] bytes = new byte[2];
        // TODO
        return bytes;
    }

    public static byte[] encodeUnsignedByte(int data) {
        byte[] bytes = new byte[1];
        // TODO
        return bytes;
    }

    public static byte[] encodeLong(long data) {
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

    public static byte[] encodeShort(short data) {
        byte[] bytes = new byte[2];
        bytes[0] = (byte) (data & 0xff);
        bytes[1] = (byte) ((data & 0xff00) >> 8);
        return bytes;
    }

    public static byte[] encodeFloat(float data) {
        int intBits = Float.floatToIntBits(data);
        return encodeInt(intBits);
    }

    public static byte[] encodeDouble(double data) {
        long longBits = Double.doubleToLongBits(data);
        return encodeLong(longBits);
    }

    public static byte[] encodeString(String str) throws Exception {
        if (Strings.isNullOrEmpty(str)) {
            str = "";
        }
        byte[] strBytes = str.getBytes(CommUtils.ENCODING);
        ByteArrayDataOutput packet = ByteStreams.newDataOutput();
        packet.write(VarUtils.encodeVarInt(strBytes.length));
        packet.write(strBytes);
        return packet.toByteArray();
    }

    public static byte[] encodeChat(String str) throws Exception {
        return encodeString(str);
    }

    public static byte[] encodeBoolean(boolean onGround) throws Exception {
        byte[] bytes = new byte[1];
        bytes[0] = (byte) (onGround ? 0x01 : 0x00);
        return bytes;
    }

    public static byte[] encodePosition(long[] location) throws Exception {
        long result = ((location[0] & 0x3FFFFFF) << 38) | ((location[1] & 0xFFF) << 26) | (location[2] & 0x3FFFFFF);
        return encodeLong(result);
    }

    public static byte[] encodeSlot(Slot slotData) throws Exception {
        return slotData.encode();
    }

    public static byte[] encodeIdentifier(String identifiers) throws Exception {
        return encodeString(identifiers);
    }

    public static long decodeUnsignedLong(byte[] bytes) {
        return (((long) bytes[0] << 56) + ((long) (bytes[1] & 255) << 48) + ((long) (bytes[2] & 255) << 40)
                + ((long) (bytes[3] & 255) << 32) + ((long) (bytes[4] & 255) << 24) + ((bytes[5] & 255) << 16)
                + ((bytes[6] & 255) << 8) + ((bytes[7] & 255) << 0));
    }

    public static byte[] encodeTag(Tag val) {
        byte[] bytes = new byte[1];
        // TODO
        return bytes;
    }

    public static byte[] encodeVaries(Varies data) {
        byte[] bytes = new byte[1];
        // TODO
        return bytes;
    }

    public static byte[] encodeEntityMetadata(EntityMetadata metadata) {
        byte[] bytes = new byte[1];
        // TODO
        return bytes;
    }

    public static byte[] encodeModifierData(ModifierData temp) {
        byte[] bytes = new byte[1];
        // TODO
        return bytes;
    }

    public static byte[] encodeAdvancement(Advancement value) {
        byte[] bytes = new byte[1];
        // TODO
        return bytes;
    }

    public static byte[] encodeAdvancementProgress(AdvancementProgress value) {
        byte[] bytes = new byte[1];
        // TODO
        return bytes;
    }

    public static byte[] encodeNode(Node temp) {
        byte[] bytes = new byte[1];
        // TODO
        return bytes;
    }

    public static byte[] encodeTags(Tags blockTags) {
        byte[] bytes = new byte[1];
        // TODO
        return bytes;
    }

    public static byte[] encodeUUID(UUID uuid) {
        byte[] bytes = new byte[16];
        System.arraycopy(encodeLong(uuid.getMostSignificantBits()), 0, bytes, 0, 8);
        System.arraycopy(encodeLong(uuid.getLeastSignificantBits()), 0, bytes, 8, 8);
        return bytes;
    }

    public static void main(String[] args) {
        System.out.println(VarUtils.decodeVarInt(hexStringtoBytes("19C4")));
        System.out.println(new String(hexStringtoBytes("1B147B2274657874223A2254696D6564206F7574")));
    }
}
