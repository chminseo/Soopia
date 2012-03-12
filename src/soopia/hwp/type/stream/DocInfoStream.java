package soopia.hwp.type.stream;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import soopia.hwp.type.AbstactStream;
import soopia.hwp.type.HwpContext;
import soopia.hwp.type.IRecordStructure;
import soopia.hwp.type.record.DocPropertyRecord;
import soopia.hwp.type.record.IDMappingsRecord;

public class DocInfoStream extends AbstactStream {
	
	public DocInfoStream(HwpContext context, ByteBuffer data) {
		super(context, "DocInfo", data);
	}
	
	public void addRecord(IRecordStructure record){
		this.records.add(record);
	}
}