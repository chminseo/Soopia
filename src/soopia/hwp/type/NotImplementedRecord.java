package soopia.hwp.type;

import soopia.hwp.type.stream.RecordHeader;
/**
 * 본 제품은 한글과컴퓨터의 한글 문서 파일(.hwp) 공개 문서를 참고하여 개발하였습니다.
 * 
 * @author chmin
 *
 */
public class NotImplementedRecord extends AbstractRecord {

	public NotImplementedRecord(RecordHeader header, IStreamStruct ds) {
		super(header, ds);
	}

	@Override
	public String getTagName() {
		return "[NI] " + super.getTagName();
	}
}
