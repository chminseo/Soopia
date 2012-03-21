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
/**
 * 본 제품은 한글과컴퓨터의 한글 문서 파일(.hwp) 공개 문서를 참고하여 개발하였습니다.
 * 
 * @author chmin
 *
 */
public class DocInfoStream extends AbstactStream {
	
	public DocInfoStream(HwpContext context, ByteBuffer data) {
		super(context, "DocInfo", data);
	}
	
	public void addRecord(IRecordStructure record){
		this.records.add(record);
	}
}