package pub.qiuf.litemc.common.util;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import pub.qiuf.litemc.common.bean.datatype.Slot;

import com.flowpowered.nbt.ByteArrayTag;
import com.flowpowered.nbt.ByteTag;
import com.flowpowered.nbt.CompoundTag;
import com.flowpowered.nbt.DoubleTag;
import com.flowpowered.nbt.FloatTag;
import com.flowpowered.nbt.IntArrayTag;
import com.flowpowered.nbt.IntTag;
import com.flowpowered.nbt.ListTag;
import com.flowpowered.nbt.LongTag;
import com.flowpowered.nbt.ShortArrayTag;
import com.flowpowered.nbt.ShortTag;
import com.flowpowered.nbt.StringTag;
import com.flowpowered.nbt.Tag;
import com.flowpowered.nbt.TagType;
import com.google.common.base.Strings;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public abstract class CommUtils {
    public static final String ENCODING = "UTF-8";
    public static final Gson GSON = new Gson();

    @SuppressWarnings("unchecked")
    public static String decodeChat(Map<String, Object> root) throws Exception {
        StringBuffer result = new StringBuffer();
        if (root.containsKey("extra")) {
            List<Object> extra = (List<Object>) root.get("extra");
            for (Object extraRoot : extra) {
                if (extraRoot instanceof Map) {
                    result.append(decodeChat((Map<String, Object>) extraRoot));
                } else if (extraRoot instanceof String) {
                    result.append(extraRoot.toString());
                }
            }
        }
        if (root.containsKey("text")) {
            result.append(root.get("text").toString());
        } else if (root.containsKey("translate")) {
            result.append("translate");
        } else if (root.containsKey("score")) {
            result.append("score");
        }
        return result.toString();
    }

    public static byte[] encodeString(String str) throws Exception {
        if (Strings.isNullOrEmpty(str)) {
            str = "";
        }
        byte[] strBytes = str.getBytes(ENCODING);
        ByteArrayDataOutput packet = ByteStreams.newDataOutput();
        packet.write(VarUtils.encodeVarInt(strBytes.length));
        packet.write(strBytes);
        return packet.toByteArray();
    }

    public static byte[] encodeSlot(Slot slot) throws Exception {
        ByteArrayDataOutput slotData = ByteStreams.newDataOutput();
        slotData.writeShort(slot.getBlockId());
        if (slot.getBlockId() != -1) {
            slotData.writeByte(slot.getItemCount());
            slotData.writeShort(slot.getItemDamage());
            slotData.write(encodeTag(slot.getTag()));
        }
        return slotData.toByteArray();
    }

    public static byte[] encodeTag(Tag<?> tag) throws Exception {
        ByteArrayDataOutput tagData = ByteStreams.newDataOutput();
        tagData.writeByte(tag.getType().getId());
        tagData.writeShort(tag.getName().getBytes(ENCODING).length);
        tagData.write(tag.getName().getBytes(ENCODING));
        tagData.write(encodeTagPayload(tag));
        return tagData.toByteArray();
    }

    public static byte[] encodeTagPayload(Tag<?> tag) throws Exception {
        ByteArrayDataOutput tagPayloadData = ByteStreams.newDataOutput();
        switch (tag.getType()) {
        case TAG_END:
            break;
        case TAG_BYTE:
            tagPayloadData.writeByte(((ByteTag) tag).getValue());
            break;
        case TAG_SHORT:
            tagPayloadData.writeShort(((ShortTag) tag).getValue());
            break;
        case TAG_INT:
            tagPayloadData.writeInt(((IntTag) tag).getValue());
            break;
        case TAG_LONG:
            tagPayloadData.writeLong(((LongTag) tag).getValue());
            break;
        case TAG_FLOAT:
            tagPayloadData.writeFloat(((FloatTag) tag).getValue());
            break;
        case TAG_DOUBLE:
            tagPayloadData.writeDouble(((DoubleTag) tag).getValue());
            break;
        case TAG_BYTE_ARRAY:
            ByteArrayTag byteArrayTag = (ByteArrayTag) tag;
            byte[] byteArrayBytes = byteArrayTag.getValue();
            tagPayloadData.writeInt(byteArrayBytes.length);
            tagPayloadData.write(byteArrayBytes);
            break;
        case TAG_STRING:
            StringTag stringTag = (StringTag) tag;
            byte[] stringBytes = stringTag.getValue().getBytes(ENCODING);
            tagPayloadData.writeShort(stringBytes.length);
            tagPayloadData.write(stringBytes);
            break;
        case TAG_LIST:
            @SuppressWarnings("unchecked")
            ListTag<Tag<?>> listTag = (ListTag<Tag<?>>) tag;
            Class<? extends Tag<?>> clazz = listTag.getElementType();
            List<Tag<?>> tags = (List<Tag<?>>) listTag.getValue();
            int size = tags.size();
            tagPayloadData.writeByte(TagType.getByTagClass(clazz).getId());
            tagPayloadData.writeInt(size);
            for (Tag<?> tag1 : tags) {
                tagPayloadData.write(encodeTagPayload(tag1));
            }
            break;
        case TAG_COMPOUND:
            CompoundTag compoundTag = (CompoundTag) tag;
            for (Tag<?> childTag : compoundTag.getValue().values()) {
                tagPayloadData.write(encodeTag(childTag));
            }
            tagPayloadData.writeByte(TagType.TAG_END.getId());
            break;
        case TAG_INT_ARRAY:
            IntArrayTag intArrayTag = (IntArrayTag) tag;
            int[] ints = intArrayTag.getValue();
            tagPayloadData.writeInt(ints.length);
            for (int i = 0; i < ints.length; i++) {
                tagPayloadData.writeInt(ints[i]);
            }
            break;

        case TAG_SHORT_ARRAY:
            ShortArrayTag shortArrayTag = (ShortArrayTag) tag;
            short[] shorts = shortArrayTag.getValue();
            tagPayloadData.writeInt(shorts.length);
            for (int i = 0; i < shorts.length; i++) {
                tagPayloadData.writeShort(shorts[i]);
            }
            break;
        default:
            throw new IOException("Invalid tag type: " + tag.getType() + ".");
        }
        return tagPayloadData.toByteArray();
    }

    public static String decodeChatMessage(JsonObject json) {
        StringBuffer sb = new StringBuffer();
        JsonElement text = json.get("text");
        JsonArray extra = json.getAsJsonArray("extra");
        if (text != null && Strings.isNullOrEmpty(text.getAsString())) {
            sb.append(text.getAsString());
        }
        if (extra != null) {
            for (int i = 0; i < extra.size(); i++) {
                sb.append(decodeChatMessage(extra.get(i).getAsJsonObject()));
            }
        }
        return sb.toString();
    }

    public static byte encodeBoolean(boolean onGround) throws Exception {
        return (byte) (onGround ? 0x01 : 0x00);
    }

    public static float[] calculateYawAndPitch(double[] from, double[] to) throws Exception {
        double dx = to[0] - from[0];
        double dy = to[1] - from[1];
        double dz = to[2] - from[2];
        return calculateYawAndPitch(dx, dy, dz);
    }

    public static float[] calculateYawAndPitch(double dx, double dy, double dz) throws Exception {
        double r = Math.sqrt(dx * dx + dy * dy + dz * dz);
        double yaw = -Math.atan2(dx, dz) / Math.PI * 180;
        if (yaw < 0) {
            yaw = 360 - yaw;
        }
        double pitch = -Math.asin(dy / r) / Math.PI * 180;
        return new float[] { (float) yaw, (float) pitch };
    }
}
