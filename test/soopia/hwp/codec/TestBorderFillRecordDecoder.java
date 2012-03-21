package soopia.hwp.codec;

import static org.junit.Assert.*;

import java.nio.ByteBuffer;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import soopia.hwp.TestUtils;
import soopia.hwp.type.record.BorderFillRecord;
import soopia.hwp.type.record.BorderFillRecord.BackgroundColorRef;

public class TestBorderFillRecordDecoder {
	/* 테두리만 */
	static byte [] BORDER = new byte[]{
		0x14, 0x04, (byte)0x80,	0x02, 
		0x00, 0x00,	
		// LRTB
		0x01, 0x03, (byte)0xAB, (byte)0xCD, (byte)0xEF, 0x00, // LEFT 
		0x01, 0x07, (byte)0x83, 0x4D, 0x00, 0x00, 		// RIGHT
		0x02, 0x0A, 0x00, 0x00, (byte)0xFF, 0x00, 		// TOP
		0x03, 0x0C, (byte)0xFF, (byte)0xFF, 0x00, 0x00, //BOTTOM 
		
		0x01, 0x00, 0x00, 0x00, 0x00, 0x00, // 대각선(?)
		
		0x00, 0x00, 0x00, 0x00, // 종류(type)
		
		0x00, 0x00, 0x00, 0x00 // size(표 23)
	};
	
	/* 테두리 + 색채우기 */
	static byte [] BORDER_COLOR = new byte[]{
		0x14, 0x04, 0x50, 0x03, 
		0x00,0x00, 
		
		0x01, 0x03, (byte)0xAB, (byte)0xCD, (byte)0xEF, 0x00, 
		0x01, 0x07, (byte)0x83, 0x4D, 0x00, 0x00,
		0x02, 0x0A, 0x00, 0x00, (byte)0xFF, 0x00, 
		0x03, 0x0C, (byte)0xFF, (byte)0xFF, 0x00, 0x00, 
		0x01, 0x00, 0x00, 0x00, 0x00, 0x00,	
		
		0x01, 0x00,	0x00, 0x00,//종류(type)
		/* 색채우기*/
		(byte)0xEF, (byte)0xCC, (byte)0xFC, 0x00, //  
		(byte)0xEE, (byte)0xEE, (byte)0xEE, 0x00, 
		0x01, 0x00, 0x00, 0x00,
		
		0x00, 0x00, 0x00, 0x00, // size(표 23)
		
		0x00 // what is this?
	};
	
	/* 테두리 + 색채우기 + 이미지 채우기 */
	static byte [] BORDER_COLOR_BGIMG = new byte[]{
		0x14, 0x04, (byte)0xC0, 0x03, 
		0x00, 0x00, 
		
		0x01, 0x03, (byte)0xAB, (byte)0xCD, (byte)0xEF, 0x00, 
		0x01, 0x07, (byte)0x83, 0x4D, 0x00,	0x00, 
		0x02, 0x0A, 0x00, 0x00, (byte)0xFF, 0x00, 
		0x03, 0x0C, (byte)0xFF, (byte)0xFF, 0x00, 0x00, 
		
		0x01, 0x00, 0x00, 0x00,	0x00, 0x00, 
		
		0x03, 0x00, 0x00, 0x00, // 종류(type)
		// 색채우기 정보
		(byte)0xEF, (byte)0xCC, (byte)0xFC, 0x00, 
		(byte)0xEE, (byte)0xEE, (byte)0xEE, 0x00, 
		0x01, 0x00, 0x00, 0x00, 
		// 이미지 채우기 정보
		0x00, 
		0x20, 0x0A, 0x00, 0x01, 0x00, 
		
		0x00, 0x00, 0x00, 0x00, // size(표 23)
		0x00, 0x00 // wtf !! ??
	};
	/* 테두리 + 그라데이션 + 이미지 채우기 */
	static byte [] BORDER_GRDTN_BGIMG = new byte[]{
		0x14, 0x04, (byte)0xE0, 0x04, 
		0x00, 0x00, 
		/* 테두리 LRTB */
		0x01, 0x03, (byte)0xAB, (byte)0xCD, (byte)0xEF, 0x00, 
		0x01, 0x07, (byte)0x83, 0x4D, 0x00,	0x00, 
		0x02, 0x0A, 0x00, 0x00, (byte)0xFF, 0x00, 
		0x03, 0x0C, (byte)0xFF, (byte)0xFF, 0x00, 0x00, 
		
		0x01, 0x00, 0x00, 0x00, 0x00, 0x00,// 대각선으로 추정됨 
		0x06, 0x00, 0x00, 0x00, // 종류(type)
		/* 그라데이션 정보 */
		0x04, // 그라데이션 유형 (표 25)
		0x23, 0x00, 0x00, 0x00, // 기울임 35
		0x50, 0x00, 0x00, 0x00, // 가로중심 80%
		0x14, 0x00, 0x00, 0x00, // 세로중심 20%
		(byte)0xFF, 0x00, 0x00, 0x00, // 번짐정도 255
		0x02, 0x00, 0x00, 0x00, // 그라데이션에 사용되는 색의 수 
		(byte)0xFF, (byte)0xFF, (byte)0xE1, 0x00, // 시작색
		(byte)0x95, 0x5B, 0x15, 0x00, // 끝 색
		/* 이미지 채우기 정보*/
		0x00, // 채우기 유형
		0x20, 0x0A, 0x00, 0x01, 0x00, // 이미지 정보 
		
		0x01, 0x00, 0x00, 0x00, 
		0x32, 
		0x00, 0x00
	};
	/* 테두리 + 그라데이션 */
	static byte [] BORDER_GRDTN = new byte[]{
		0x14, 0x04, 0x70, 0x04, 
		0x00,0x00, 
		0x01, 0x03, (byte)0xAB, (byte)0xCD, (byte)0xEF,	0x00, 
		0x01, 0x07, (byte)0x83, 0x4D, 0x00, 0x00, 
		0x02, 0x0A, 0x00, 0x00, (byte)0xFF, 0x00, 
		0x03, 0x0C, (byte)0xFF, (byte)0xFF, 0x00, 0x00, 
		0x01, 0x00, 0x00, 0x00, 0x00, 0x00, 
		0x04, 0x00, 0x00, 0x00, 
		
		0x04, 
		0x23, 0x00, 0x00, 0x00, 
		0x50, 0x00, 0x00, 0x00, 
		0x14, 0x00, 0x00, 0x00, 
		(byte)0xFF, 0x00, 0x00, 0x00, 
		0x02, 0x00, 0x00, 0x00, 
		(byte)0xFF, (byte)0xFF, (byte)0xE1, 0x00, 
		(byte)0x95, 0x5B, 0x15, 0x00, 
		
		0x01, 0x00, 0x00, 0x00, 
		0x32,
		0x00
	};
	/* 그라데이션만 */
	static byte [] GRDTN = new byte[]{
		0x14, 0x04, 0x70, 0x04, 
		0x00, 0x00, 
		0x00, 0x03, (byte)0xAB, (byte)0xCD, (byte)0xEF, 0x00, 
		0x00, 0x07, (byte)0x83, 0x4D, 0x00, 0x00, 
		0x00, 0x0A, 0x00, 0x00, (byte)0xFF, 0x00, 
		0x00, 0x0C, (byte)0xFF, (byte)0xFF, 0x00, 0x00, 
		0x01, 0x00, 0x00, 0x00, 0x00, 0x00, 
		0x04, 0x00, 0x00, 0x00,
		
		0x04, 
		0x23, 0x00, 0x00, 0x00, 
		0x50, 0x00, 0x00, 0x00, 
		0x14, 0x00, 0x00, 0x00, 
		(byte)0xFF, 0x00, 0x00, 0x00, 
		0x02, 0x00, 0x00, 0x00, 
		(byte)0xFF, (byte)0xFF, (byte)0xE1, 0x00, 
		(byte)0x95, 0x5B, 0x15, 0x00,
		
		0x01, 0x00, 0x00, 0x00, 
		0x32, 0x00
	};

	static ByteBuffer data;
	static MockDocInfo docInfo ;
	static BorderFillRecordDecoder decoder ;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		byte [] dummy = new byte [] { 3, 9, 3, 4, 9, 3};
		data = ByteBuffer.allocate(
			dummy.length
			+ BORDER.length
			+ BORDER_COLOR.length 
			+ BORDER_COLOR_BGIMG.length 
			+ BORDER_GRDTN_BGIMG.length
			+ BORDER_GRDTN.length
			+ GRDTN.length
		);
		data.put(dummy);
		data.put(BORDER);
		data.put(BORDER_COLOR);
		data.put(BORDER_COLOR_BGIMG);
		data.put(BORDER_GRDTN_BGIMG);
		data.put(BORDER_GRDTN);
		data.put(GRDTN);
		
		docInfo = TestUtils.newMockDocInfo(data);
		decoder = new BorderFillRecordDecoder();
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test_create_border_record() throws DecodingException {
		BorderFillRecord record = new BorderFillRecord(
				TestUtils.newRecordHeader(data, 6), 
				docInfo, 6
		);
		decoder.decode(record, record.getBuffer(), docInfo.getHwpContext());
		assertEquals (0, record.getBorderFillPropety().getValue().intValue());
		
		// LRTB
		// 0x01, 0x03, (byte)0xAB, (byte)0xCD, (byte)0xEF, 0x00, // LEFT 
		// 0x01, 0x07, (byte)0x83, 0x4D, 0x00, 0x00, 		// RIGHT
		// 0x02, 0x0A, 0x00, 0x00, (byte)0xFF, 0x00, 		// TOP
		// 0x03, 0x0C, (byte)0xFF, (byte)0xFF, 0x00, 0x00, //BOTTOM
		assertEquals ("#FFFF00", record.getBottomBorder().toWebColorString());
		assertEquals (false, record.isColorFilled());
		assertEquals (false, record.isImageFilled());
		assertEquals (false, record.isGradationFilled());
		assertArrayEquals(new byte[]{}, record.getDummyBytes());
	}
	@Test
	public void test_boder_and_color_fill() throws DecodingException {
		BorderFillRecord record = new BorderFillRecord(
				TestUtils.newRecordHeader(data, 6+BORDER.length), 
				docInfo, 6+BORDER.length
		);
		decoder.decode(record, record.getBuffer(), docInfo.getHwpContext());
		BackgroundColorRef bg = record.getBackgroundColorRef();
		
		// (byte)0xEF, (byte)0xCC, (byte)0xFC, 0x00, //  
		// (byte)0xEE, (byte)0xEE, (byte)0xEE, 0x00, 
		// 0x01, 0x00, 0x00, 0x00,
		assertEquals ("#EFCCFC", bg.getBgColor().toWebColorString());
		assertEquals ("#EEEEEE", bg.getPatternColor().toWebColorString());
		assertEquals (new Integer(1), bg.getPatternType().getValue());
		assertEquals (new Long(0), record.getSizeInfo().getValue());
		
		assertEquals (1, record.getDummyBytes().length);
	}

}
