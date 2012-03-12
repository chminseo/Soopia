package soopia.hwp.type.stream;

import java.nio.ByteBuffer;

import soopia.hwp.type.AbstactStream;
import soopia.hwp.type.HwpContext;
import soopia.hwp.type.IRecordStructure;

public class SectionStream extends AbstactStream {

	public SectionStream(HwpContext context, String structureName, ByteBuffer data) {
		super(context, structureName, data);
	}

	public SectionStream(HwpContext context, ByteBuffer data) {
		super( context, "Section", data);
	}

	public void addRecord(IRecordStructure record) {
		this.records.add(record);
	}

}
