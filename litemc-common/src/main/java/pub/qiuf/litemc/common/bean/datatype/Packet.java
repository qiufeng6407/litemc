package pub.qiuf.litemc.common.bean.datatype;

import com.google.common.io.ByteArrayDataInput;

public abstract class Packet {
    public abstract byte[] encode() throws Exception;
    public abstract void decode(ByteArrayDataInput in) throws Exception;
}
