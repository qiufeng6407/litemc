package pub.qiuf.litemc.common.util;

import com.flowpowered.nbt.*;
import com.google.common.base.Strings;
import com.google.common.io.ByteArrayDataInput;
import com.google.gson.JsonObject;
import pub.qiuf.litemc.common.bean.datatype.*;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class StreamUtils {

    public static boolean readBoolean(ByteArrayDataInput in) throws IOException {
        int ch = in.readByte();
        if (ch < 0) {
            throw new EOFException();
        }
        if (ch != 0 && ch != 1) {
            throw new RuntimeException("read boolean failed, unexcepted " + ch);
        }
        return ch == 0x01;
    }

    
    public static byte readByte(ByteArrayDataInput in) {
        return in.readByte();
    }

    
    public static int readUnsignedByte(ByteArrayDataInput in) {
        return in.readUnsignedByte();
    }

    
    public static short readShort(ByteArrayDataInput in) throws IOException {
        int ch1 = in.readByte();
        int ch2 = in.readByte();
        if ((ch1 | ch2) < 0)
            throw new EOFException();
        return (short) ((ch1 << 8) + (ch2 << 0));
    }

    
    public static int readUnsignedShort(ByteArrayDataInput in) throws IOException {
        int ch1 = in.readByte();
        int ch2 = in.readByte();
        if ((ch1 | ch2) < 0)
            throw new EOFException();
        return (ch1 << 8) + (ch2 << 0);
    }

    
    public static int readInt(ByteArrayDataInput in) throws IOException {
        int ch1 = in.readByte();
        int ch2 = in.readByte();
        int ch3 = in.readByte();
        int ch4 = in.readByte();
        if ((ch1 | ch2 | ch3 | ch4) < 0)
            throw new EOFException();
        return ((ch1 << 24) + (ch2 << 16) + (ch3 << 8) + (ch4 << 0));
    }

    public static long readLong(ByteArrayDataInput in) throws IOException {
        byte[] readBuffer_8 = new byte[8];
        readFully(in, readBuffer_8, 0, 8);
        return ByteUtils.decodeUnsignedLong(readBuffer_8);
    }

    
    public static long readUnsignedLong(ByteArrayDataInput in) throws IOException {
        byte[] readBuffer_8 = new byte[8];
        readFully(in, readBuffer_8, 0, 8);
        return ByteUtils.decodeUnsignedLong(readBuffer_8);
    }

    
    public static float readFloat(ByteArrayDataInput in) throws IOException {
        return Float.intBitsToFloat(readInt(in));
    }

    
    public static double readDouble(ByteArrayDataInput in) throws IOException {
        return Double.longBitsToDouble(readLong(in));
    }

    
    public static String readString(ByteArrayDataInput in) throws IOException {
        return readString(in, CommUtils.ENCODING);
    }

    
    public static String readString(ByteArrayDataInput in, String encoding) throws IOException {
        int len = readVarInt(in);
        byte[] b = new byte[len];
        readFully(in, b);
        return new String(b, encoding);
    }

    
    public static String readChat(ByteArrayDataInput in) throws IOException {
        String chatMessage = readString(in);
        if (Strings.isNullOrEmpty(chatMessage)) {
            return chatMessage;
        }
        JsonObject json = GsonUtils.GSON.fromJson(chatMessage, JsonObject.class);
        String result = CommUtils.decodeChatMessage(json);
        if (Strings.isNullOrEmpty(result)) {
            return chatMessage;
        }
        return result;
    }

    
    public static int readVarInt(ByteArrayDataInput in) throws IOException {
        int numRead = 0;
        int result = 0;
        byte read;
        do {
            read = (byte) in.readByte();
            int value = (read & 0b01111111);
            result |= (value << (7 * numRead));

            numRead++;
            if (numRead > 5) {
                throw new RuntimeException("VarInt is too big");
            }
        } while ((read & 0b10000000) != 0);

        return result;
    }

    
    public static long readVarLong(ByteArrayDataInput in) throws IOException {
        int numRead = 0;
        long result = 0;
        byte read;
        do {
            read = (byte) in.readByte();
            int value = (read & 0b01111111);
            result |= (value << (7 * numRead));

            numRead++;
            if (numRead > 10) {
                throw new RuntimeException("VarLong is too big");
            }
        } while ((read & 0b10000000) != 0);

        return result;
    }

    
    public static Object readChunkSection(ByteArrayDataInput in) throws IOException {
        // TODO Auto-generated method stub
        return null;
    }

    
    public static EntityMetadata readEntityMetadata(ByteArrayDataInput in) throws IOException {
        // TODO Auto-generated method stub
        return null;
    }

    
    public static Slot readSlot(ByteArrayDataInput in) throws IOException {
        Slot slot = new Slot();
        slot.setBlockId(readShort(in));
        if (slot.getBlockId() == -1) {
            return slot;
        }
        slot.setItemCount(readByte(in));
        slot.setItemDamage(readShort(in));
        if (slot.getBlockId() == 0) {
            return slot;
        }
        slot.setTag(readTag(in));
        return slot;
    }

    
    public static Tag<?> readTag(ByteArrayDataInput in) throws IOException {
        return readTag(in, 0);
    }

    private static Tag<?> readTag(ByteArrayDataInput in, int depth) throws IOException {
        int typeId = readByte(in) & 0xFF;
        TagType type = TagType.getById(typeId);

        String name;
        if (type != TagType.TAG_END) {
            int nameLength = readShort(in) & 0xFFFF;
            byte[] nameBytes = new byte[nameLength];
            readFully(in, nameBytes);
            name = new String(nameBytes, NBTConstants.CHARSET.name());
        } else {
            name = "";
            return new EndTag();
        }
        return readTagPayload(in, type, name, depth);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private static Tag<?> readTagPayload(ByteArrayDataInput in, TagType type, String name, int depth) throws IOException {
        switch (type) {
            case TAG_END:
                if (depth == 0) {
                    throw new IOException("TAG_End found without a TAG_Compound/TAG_List tag preceding it.");
                } else {
                    return new EndTag();
                }
            case TAG_BYTE:
                return new ByteTag(name, readByte(in));
            case TAG_SHORT:
                return new ShortTag(name, readShort(in));

            case TAG_INT:
                return new IntTag(name, readInt(in));

            case TAG_LONG:
                return new LongTag(name, readLong(in));

            case TAG_FLOAT:
                return new FloatTag(name, readFloat(in));

            case TAG_DOUBLE:
                return new DoubleTag(name, readDouble(in));

            case TAG_BYTE_ARRAY:
                int length = readInt(in);
                byte[] bytes = new byte[length];
                readFully(in, bytes);
                return new ByteArrayTag(name, bytes);

            case TAG_STRING:
                length = readShort(in);
                bytes = new byte[length];
                readFully(in, bytes);
                return new StringTag(name, new String(bytes, NBTConstants.CHARSET.name()));

            case TAG_LIST:
                TagType childType = TagType.getById(readByte(in));
                length = readInt(in);

                Class<? extends Tag<?>> clazz = childType.getTagClass();
                List<Tag<?>> tagList = new ArrayList<>(length);
                for (int i = 0; i < length; i++) {
                    Tag<?> tag = readTagPayload(in, childType, "", depth + 1);
                    if (tag instanceof EndTag) {
                        throw new IOException("TAG_End not permitted in a list.");
                    } else if (!clazz.isInstance(tag)) {
                        throw new IOException("Mixed tag types within a list.");
                    }
                    tagList.add(tag);
                }
                return new ListTag(name, clazz, tagList);
            case TAG_COMPOUND:
                CompoundMap compoundTagList = new CompoundMap();
                while (true) {
                    Tag<?> tag = readTag(in, depth + 1);
                    if (tag instanceof EndTag) {
                        break;
                    } else {
                        compoundTagList.put(tag);
                    }
                }
                return new CompoundTag(name, compoundTagList);

            case TAG_INT_ARRAY:
                length = readInt(in);
                int[] ints = new int[length];
                for (int i = 0; i < length; i++) {
                    ints[i] = readInt(in);
                }
                return new IntArrayTag(name, ints);

            case TAG_SHORT_ARRAY:
                length = readInt(in);
                short[] shorts = new short[length];
                for (int i = 0; i < length; i++) {
                    shorts[i] = readShort(in);
                }
                return new ShortArrayTag(name, shorts);

            default:
                throw new IOException("Invalid tag type: " + type + ".");
        }
    }

    
    public static long[] readPosition(ByteArrayDataInput in) throws IOException {
        long result = readLong(in);

        long x = result >> 38;
        if (x >= Math.pow(2, 25)) {
            x -= Math.pow(2, 26);
        }

        long y = (result >> 26) & 0xFFF;
        if (y >= Math.pow(2, 11)) {
            y -= Math.pow(2, 12);
        }

        long z = result << 38 >> 38;
        if (z >= Math.pow(2, 25)) {
            z -= Math.pow(2, 26);
        }
        return new long[] { x, y, z };
    }

    
    public static byte readAngle(ByteArrayDataInput in) throws IOException {
        return readByte(in);
    }

    
    public static UUID readUUID(ByteArrayDataInput in) throws IOException {
        return new UUID(readUnsignedLong(in), readUnsignedLong(in));
    }

    
    public static byte[] readByteArray(ByteArrayDataInput in, int length) throws IOException {
        byte[] b = new byte[length];
        readFully(in, b);
        return b;
    }

    public static void readFully(ByteArrayDataInput in, byte b[]) throws IOException {
        readFully(in, b, 0, b.length);
    }

    public static void readFully(ByteArrayDataInput in, byte b[], int off, int len) throws IOException {
        in.readFully(b, off, len);
    }

    public static String readIdentifier(ByteArrayDataInput in) throws IOException {
        return readString(in);
    }

    public static Varies readVaries(ByteArrayDataInput in) throws IOException {
        return null;
    }

    public static ModifierData readModifierData(ByteArrayDataInput in) throws IOException {
        return null;
    }

    public static Advancement readAdvancement(ByteArrayDataInput in) throws IOException {
        return null;
    }

    public static AdvancementProgress readAdvancementProgress(ByteArrayDataInput in) throws IOException {
        return null;
    }

    public static Node readNode(ByteArrayDataInput in) throws IOException {
        return null;
    }

    public static Tags readTags(ByteArrayDataInput in) throws IOException {
        return null;
    }
}
