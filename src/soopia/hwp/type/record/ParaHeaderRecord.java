package soopia.hwp.type.record;

import soopia.hwp.type.AbstractRecord;
import soopia.hwp.type.Dword;
import soopia.hwp.type.IStreamStruct;
import soopia.hwp.type.stream.RecordHeader;
/**
 * 본 제품은 한글과컴퓨터의 한글 문서 파일(.hwp) 공개 문서를 참고하여 개발하였습니다.
 * 
 * @author chmin
 *
 */
public class ParaHeaderRecord extends AbstractRecord {

	public ParaHeaderRecord(RecordHeader header, IStreamStruct ds, int offset) {
		super(header, ds, offset);
	}

}
