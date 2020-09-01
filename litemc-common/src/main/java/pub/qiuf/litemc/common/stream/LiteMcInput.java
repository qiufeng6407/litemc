package pub.qiuf.litemc.common.stream;

import java.io.IOException;
import java.util.UUID;

import pub.qiuf.litemc.common.bean.datatype.Slot;

import com.flowpowered.nbt.Tag;

public interface LiteMcInput {
    /**
     * Either false or true<br>
     * True is encoded as 0x01, false as 0x00.
     * 
     * @return
     */
    boolean readBoolean() throws IOException;

    /**
     * An integer between -128 and 127<br>
     * Signed 8-bit integer, two's complement
     * 
     * @return
     */
    byte readByte() throws IOException;

    /**
     * An integer between 0 and 255<br>
     * Unsigned 8-bit integer
     * 
     * @return
     */
    int readUnsignedByte() throws IOException;

    /**
     * An integer between -32768 and 32767<br>
     * Signed 16-bit integer, two's complement
     * 
     * @return
     */
    short readShort() throws IOException;

    /**
     * An integer between 0 and 65535<br>
     * Unsigned 16-bit integer
     * 
     * @return
     */
    int readUnsignedShort() throws IOException;

    /**
     * An integer between -2147483648 and 2147483647<br>
     * Signed 32-bit integer, two's complement
     * 
     * @return
     */
    int readInt() throws IOException;

    /**
     * An integer between -9223372036854775808 and 9223372036854775807<br>
     * Signed 64-bit integer, two's complement
     * 
     * @return
     */
    long readLong() throws IOException;

    /**
     * addition
     * 
     * @return
     * @throws IOException
     */
    long readUnsignedLong() throws IOException;

    /**
     * A single-precision 32-bit IEEE 754 floating point number
     * 
     * @return
     */
    float readFloat() throws IOException;

    /**
     * A double-precision 64-bit IEEE 754 floating point number
     * 
     * @return
     */
    double readDouble() throws IOException;

    String readString() throws IOException;

    /**
     * A sequence of Unicode scalar values<br>
     * UTF-8 string prefixed with its size in bytes as a VarInt
     * 
     * @return
     */
    String readString(String encoding) throws IOException;

    /**
     * See Chat<br>
     * Encoded as a String
     * 
     * @return
     */
    Object readChat() throws IOException;

    /**
     * An integer between -2147483648 and 2147483647<br>
     * Variable-length data encoding a two's complement signed 32-bit integer;
     * more info in their section
     * 
     * @return
     */
    int readVarInt() throws IOException;

    /**
     * An integer between -9223372036854775808 and 9223372036854775807<br>
     * Variable-length data encoding a two's complement signed 64-bit integer;
     * more info in their section
     * 
     * @return
     */
    long readVarLong() throws IOException;

    /**
     * A 16×16×16 chunk of blocks<br>
     * See SMP Map Format
     * 
     * @return
     */
    Object readChunkSection() throws IOException;

    /**
     * Miscellaneous information about an entity<br>
     * See Entities#Entity Metadata Format
     * 
     * @return
     */
    Object readEntityMetadata() throws IOException;

    /**
     * An item stack in an inventory or container<br>
     * See Slot Data
     * 
     * @return
     */
    Slot readSlot() throws IOException;

    /**
     * Depends on context<br>
     * See NBT
     * 
     * @return
     */
    Tag<?> readTag() throws IOException;

    /**
     * An integer/block position: x (-33554432 to 33554431), y (-2048 to 2047),
     * z (-33554432 to 33554431)<br>
     * 
     * x as a 26-bit integer, followed by y as a 12-bit integer, followed by z
     * as a 26-bit integer (all signed, two's complement). See also the section
     * below.
     * 
     * @return
     */
    long[] readPosition() throws IOException;

    /**
     * A rotation angle in steps of 1/256 of a full turn<br>
     * Whether or not this is signed does not matter, since the resulting angles
     * are the same.
     * 
     * @return
     */
    int readAngle() throws IOException;

    /**
     * A UUID<br>
     * Encoded as an unsigned 128-bit integer (or two unsigned 64-bit integers:
     * the most significant 64 bits and then the least significant 64 bits)
     * 
     * @return
     */
    UUID readUUID() throws IOException;

    /**
     * Depends on context<br>
     * This is just a sequence of zero or more bytes, its meaning should be
     * explained somewhere else, e.g. in the packet description. The length must
     * also be known from the context.
     * 
     * @return
     */
    byte[] readByteArray(int length) throws IOException;

    void readFully(byte b[]) throws IOException;

    void readFully(byte b[], int off, int len) throws IOException;
}
