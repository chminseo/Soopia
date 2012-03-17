package soopia.hwp.codec;

import java.nio.ByteBuffer;

import soopia.hwp.type.AbstactStream;
import soopia.hwp.type.HwpContext;

public class MockDocInfo extends AbstactStream {

	public MockDocInfo(HwpContext context, ByteBuffer data) {
		super(context, "MockDocInfo", data);
		// TODO Auto-generated constructor stub
	}
}