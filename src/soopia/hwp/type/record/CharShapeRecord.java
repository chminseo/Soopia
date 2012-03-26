/**
 * 본 제품은 한글과컴퓨터의 한글 문서 파일(.hwp) 공개 문서를 참고하여 개발하였습니다.
 */
package soopia.hwp.type.record;

import soopia.hwp.type.AbstractRecord;
import soopia.hwp.type.IStreamStruct;
import soopia.hwp.type.stream.RecordHeader;

/**
 * @author chmin
 *
 */
public class CharShapeRecord extends AbstractRecord {

	/**
	 * @param header
	 * @param ds
	 * @param offset
	 */
	public CharShapeRecord(RecordHeader header, IStreamStruct ds) {
		super(header, ds);
		// TODO Auto-generated constructor stub
	}

}
