package soopia.hwp.type.stream;

import java.nio.ByteBuffer;

import soopia.hwp.type.AbstactStream;
import soopia.hwp.type.HwpContext;

public class PreviewImageInfo extends AbstactStream {

	public PreviewImageInfo(HwpContext context, String structureName, ByteBuffer data) {
		super(context, structureName, data);
	}
}
