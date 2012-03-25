/**
 * 본 제품은 한글과컴퓨터의 한글 문서 파일(.hwp) 공개 문서를 참고하여 개발하였습니다.
 */
package soopia.hwp.codec;

import static org.junit.Assert.*;

import java.nio.ByteBuffer;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import soopia.hwp.TestUtils;
import soopia.hwp.type.record.CharShapeRecord;

public class TestCharShapeRecordDecoder {

	static byte [] data = new byte[]{
		0x15, 0x04, (byte)0xA0, 0x04, //header
		/*글        꼴*/0x02, 0x00, 0x02, 0x00, 0x01, 0x00, 0x01, 0x00, 0x01, 0x00, 0x01, 0x00, 0x01, 0x00, 
		/*장        평*/0x64, (byte)0xC8, 0x64, 0x64, 0x64, 0x64, 0x64, 
		/*자        간*/0x00, 0x14, 0x00, 0x00, 0x00, 0x00, 0x00, // 20%
		/*상대크기*/(byte)0x96, (byte)0x96, (byte)0x96, (byte)0x96, (byte)0x96, (byte)0x96, (byte)0x96, 
		/*글자위치*/0x00, 0x10, 0x00, 0x00, 0x00, 0x00, 0x00, // 16%
		
		(byte)0xE8, 0x03, 0x00, 0x00,// 기준크기( HwpUnit ) 
		0x00, 0x08, 0x00, 0x00,  // 속성(표-30 참조)
		0x10, 0x0A, // 그림자 X, Y 간격
		0x00, 0x00, 0x00, 0x00, // 글자색
		0x00, 0x00, 0x00, 0x00, // 밑줄색
		(byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, // 음영색 
		0x34, 0x56,	0x78, 0x00, // 그림자색
		0x02, 0x00, // 테두리/배경 데이터레코드 Id
		0x00, 0x00, 0x00, 0x00 // 취소선색
	};
	static MockDocInfo docInfo ;
	private static CharShapeRecordDecoder decoder;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ByteBuffer buf = ByteBuffer.wrap(data);
		docInfo = TestUtils.newMockDocInfo(buf);
		decoder = new CharShapeRecordDecoder();
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test_decoding_CharShapeRecord() throws DecodingException {
		CharShapeRecord record = new CharShapeRecord(
				TestUtils.newRecordHeader(docInfo.getBuffer(), 0)
				, docInfo, 0);
		record = decoder.decode(record, record.getBuffer(), docInfo.getHwpContext());
		
	}

}
