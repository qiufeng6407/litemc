package pub.qiuf.litemc.common.stream;

import java.io.EOFException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import pub.qiuf.litemc.common.bean.datatype.Slot;
import pub.qiuf.litemc.common.util.ByteUtils;
import pub.qiuf.litemc.common.util.CommUtils;
import pub.qiuf.litemc.common.util.GsonUtils;

import com.flowpowered.nbt.ByteArrayTag;
import com.flowpowered.nbt.ByteTag;
import com.flowpowered.nbt.CompoundMap;
import com.flowpowered.nbt.CompoundTag;
import com.flowpowered.nbt.DoubleTag;
import com.flowpowered.nbt.EndTag;
import com.flowpowered.nbt.FloatTag;
import com.flowpowered.nbt.IntArrayTag;
import com.flowpowered.nbt.IntTag;
import com.flowpowered.nbt.ListTag;
import com.flowpowered.nbt.LongTag;
import com.flowpowered.nbt.NBTConstants;
import com.flowpowered.nbt.ShortArrayTag;
import com.flowpowered.nbt.ShortTag;
import com.flowpowered.nbt.StringTag;
import com.flowpowered.nbt.Tag;
import com.flowpowered.nbt.TagType;
import com.google.common.base.Strings;
import com.google.gson.JsonObject;

public class LiteMcInputStream extends FilterInputStream implements LiteMcInput {

    public LiteMcInputStream(InputStream in) {
        super(in);
    }

    @Override
    public boolean readBoolean() throws IOException {
        int ch = in.read();
        if (ch < 0) {
            throw new EOFException();
        }
        if (ch == 0) {
            return false;
        } else if (ch == 1) {
            return true;
        } else {
            throw new RuntimeException("read boolean failed, unexcepted " + ch);
        }
    }

    @Override
    public byte readByte() throws IOException {
        int ch = in.read();
        if (ch < 0) {
            throw new EOFException();
        }
        return (byte) ch;
    }

    @Override
    public int readUnsignedByte() throws IOException {
        int ch = in.read();
        if (ch < 0)
            throw new EOFException();
        return ch;
    }

    @Override
    public short readShort() throws IOException {
        int ch1 = in.read();
        int ch2 = in.read();
        if ((ch1 | ch2) < 0)
            throw new EOFException();
        return (short) ((ch1 << 8) + (ch2 << 0));
    }

    @Override
    public int readUnsignedShort() throws IOException {
        int ch1 = in.read();
        int ch2 = in.read();
        if ((ch1 | ch2) < 0)
            throw new EOFException();
        return (ch1 << 8) + (ch2 << 0);
    }

    @Override
    public int readInt() throws IOException {
        int ch1 = in.read();
        int ch2 = in.read();
        int ch3 = in.read();
        int ch4 = in.read();
        if ((ch1 | ch2 | ch3 | ch4) < 0)
            throw new EOFException();
        return ((ch1 << 24) + (ch2 << 16) + (ch3 << 8) + (ch4 << 0));
    }

    @Override
    public long readLong() throws IOException {
        byte[] readBuffer_8 = new byte[8];
        readFully(readBuffer_8, 0, 8);
        return ByteUtils.decodeUnsignedLong(readBuffer_8);
    }

    @Override
    public long readUnsignedLong() throws IOException {
        byte[] readBuffer_8 = new byte[8];
        readFully(readBuffer_8, 0, 8);
        return ByteUtils.decodeUnsignedLong(readBuffer_8);
    }

    @Override
    public float readFloat() throws IOException {
        return Float.intBitsToFloat(readInt());
    }

    @Override
    public double readDouble() throws IOException {
        return Double.longBitsToDouble(readLong());
    }

    @Override
    public String readString() throws IOException {
        return readString(CommUtils.ENCODING);
    }

    @Override
    public String readString(String encoding) throws IOException {
        int len = readVarInt();
        byte[] b = new byte[len];
        readFully(b);
        return new String(b, encoding);
    }

    @Override
    public Object readChat() throws IOException {
        String chatMessage = readString();
        if (!Strings.isNullOrEmpty(chatMessage)) {
            return chatMessage;
        }
        JsonObject json = GsonUtils.GSON.fromJson(chatMessage, JsonObject.class);
        String result = CommUtils.decodeChatMessage(json);
        if (Strings.isNullOrEmpty(result)) {
            return chatMessage;
        }
        return result;
    }

    @Override
    public int readVarInt() throws IOException {
        int numRead = 0;
        int result = 0;
        byte read;
        do {
            read = (byte) in.read();
            int value = (read & 0b01111111);
            result |= (value << (7 * numRead));

            numRead++;
            if (numRead > 5) {
                throw new RuntimeException("VarInt is too big");
            }
        } while ((read & 0b10000000) != 0);

        return result;
    }

    @Override
    public long readVarLong() throws IOException {
        int numRead = 0;
        long result = 0;
        byte read;
        do {
            read = (byte) in.read();
            int value = (read & 0b01111111);
            result |= (value << (7 * numRead));

            numRead++;
            if (numRead > 10) {
                throw new RuntimeException("VarLong is too big");
            }
        } while ((read & 0b10000000) != 0);

        return result;
    }

    @Override
    public Object readChunkSection() throws IOException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object readEntityMetadata() throws IOException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Slot readSlot() throws IOException {
        Slot slot = new Slot();
        slot.setBlockId(readShort());
        if (slot.getBlockId() == -1) {
            return slot;
        }
        slot.setItemCount(readByte());
        slot.setItemDamage(readShort());
        if (slot.getBlockId() == 0) {
            return slot;
        }
        slot.setTag(readTag());
        return slot;
    }

    @Override
    public Tag<?> readTag() throws IOException {
        return readTag(0);
    }

    private Tag<?> readTag(int depth) throws IOException {
        int typeId = readByte() & 0xFF;
        TagType type = TagType.getById(typeId);

        String name;
        if (type != TagType.TAG_END) {
            int nameLength = readShort() & 0xFFFF;
            byte[] nameBytes = new byte[nameLength];
            readFully(nameBytes);
            name = new String(nameBytes, NBTConstants.CHARSET.name());
        } else {
            name = "";
            return new EndTag();
        }
        return readTagPayload(type, name, depth);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private Tag<?> readTagPayload(TagType type, String name, int depth) throws IOException {
        switch (type) {
        case TAG_END:
            if (depth == 0) {
                throw new IOException("TAG_End found without a TAG_Compound/TAG_List tag preceding it.");
            } else {
                return new EndTag();
            }
        case TAG_BYTE:
            return new ByteTag(name, readByte());
        case TAG_SHORT:
            return new ShortTag(name, readShort());

        case TAG_INT:
            return new IntTag(name, readInt());

        case TAG_LONG:
            return new LongTag(name, readLong());

        case TAG_FLOAT:
            return new FloatTag(name, readFloat());

        case TAG_DOUBLE:
            return new DoubleTag(name, readDouble());

        case TAG_BYTE_ARRAY:
            int length = readInt();
            byte[] bytes = new byte[length];
            readFully(bytes);
            return new ByteArrayTag(name, bytes);

        case TAG_STRING:
            length = readShort();
            bytes = new byte[length];
            readFully(bytes);
            return new StringTag(name, new String(bytes, NBTConstants.CHARSET.name()));

        case TAG_LIST:
            TagType childType = TagType.getById(readByte());
            length = readInt();

            Class<? extends Tag<?>> clazz = childType.getTagClass();
            List<Tag<?>> tagList = new ArrayList<>(length);
            for (int i = 0; i < length; i++) {
                Tag<?> tag = readTagPayload(childType, "", depth + 1);
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
                Tag<?> tag = readTag(depth + 1);
                if (tag instanceof EndTag) {
                    break;
                } else {
                    compoundTagList.put(tag);
                }
            }
            return new CompoundTag(name, compoundTagList);

        case TAG_INT_ARRAY:
            length = readInt();
            int[] ints = new int[length];
            for (int i = 0; i < length; i++) {
                ints[i] = readInt();
            }
            return new IntArrayTag(name, ints);

        case TAG_SHORT_ARRAY:
            length = readInt();
            short[] shorts = new short[length];
            for (int i = 0; i < length; i++) {
                shorts[i] = readShort();
            }
            return new ShortArrayTag(name, shorts);

        default:
            throw new IOException("Invalid tag type: " + type + ".");
        }
    }

    @Override
    public long[] readPosition() throws IOException {
        long result = readLong();

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

    @Override
    public int readAngle() throws IOException {
        return readUnsignedByte();
    }

    @Override
    public UUID readUUID() throws IOException {
        return new UUID(readUnsignedLong(), readUnsignedLong());
    }

    @Override
    public byte[] readByteArray(int length) throws IOException {
        byte[] b = new byte[length];
        readFully(b);
        return b;
    }

    @Override
    public void readFully(byte b[]) throws IOException {
        readFully(b, 0, b.length);
    }

    @Override
    public void readFully(byte b[], int off, int len) throws IOException {
        if (len < 0) {
            throw new IndexOutOfBoundsException();
        }
        int n = 0;
        while (n < len) {
            int count = in.read(b, off + n, len - n);
            if (count < 0) {
                throw new EOFException();
            }
            n += count;
        }
    }

}
