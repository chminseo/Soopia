package soopia.hwp;

import java.nio.ByteBuffer;

import soopia.hwp.codec.MockDocInfo;
import soopia.hwp.type.Dword;
import soopia.hwp.type.HwpContext;
import soopia.hwp.type.stream.RecordHeader;
/**
 * 본 제품은 한글과컴퓨터의 한글 문서 파일(.hwp) 공개 문서를 참고하여 개발하였습니다.
 * 
 * @author chmin
 *
 */
public class TestUtils {
	public static MockDocInfo newMockDocInfo(ByteBuffer data){
		return new MockDocInfo(new HwpContext(), data );
	}

	public static RecordHeader newRecordHeader(ByteBuffer buf, int offset ) {
		RecordHeader header = new RecordHeader();
		header.baseHeader = new Dword(buf, offset );
		return header;
	}
}
