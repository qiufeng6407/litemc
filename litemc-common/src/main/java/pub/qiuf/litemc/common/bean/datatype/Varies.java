package pub.qiuf.litemc.common.bean.datatype;

import com.google.common.io.ByteArrayDataInput;

import java.io.IOException;

@Deprecated
public class Varies extends Packet {

    @Override
    public byte[] encode() throws IOException {
        return new byte[0];
    }

    @Override
    public void decode(ByteArrayDataInput in) throws IOException {

    }
}
