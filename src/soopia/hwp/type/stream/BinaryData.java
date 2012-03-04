package soopia.hwp.type.stream;

import java.nio.ByteBuffer;

import soopia.hwp.type.AbstactStream;
import soopia.hwp.type.HwpContext;

public class BinaryData extends AbstactStream {
	
	public BinaryData(HwpContext context, String structureName, ByteBuffer data) {
		super(context, structureName, data);
	}

}
