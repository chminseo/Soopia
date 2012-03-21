package soopia.hwp.type.stream;

import java.nio.ByteBuffer;

import soopia.hwp.type.AbstactStream;
import soopia.hwp.type.HwpContext;
import soopia.hwp.type.IRecordStructure;
/**
 * 본 제품은 한글과컴퓨터의 한글 문서 파일(.hwp) 공개 문서를 참고하여 개발하였습니다.
 * 
 * @author chmin
 *
 */
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
