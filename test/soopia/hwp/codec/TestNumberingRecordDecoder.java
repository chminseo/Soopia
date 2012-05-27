package soopia.hwp.codec;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import soopia.hwp.TestUtils;
import soopia.hwp.type.record.NumberingRecord;
import soopia.hwp.type.record.NumberingRecord.NumberingInfo;
import soopia.hwp.util.ByteArraySource;

public class TestNumberingRecordDecoder {

	/*
	 * 17 04 C0 0A  header 
	 * 0C 00 00 00 00 00 : 32 00 FF FF FF FF : 03 00 - 5E 00 31 00 2E 00 - 1수준
	 * 0C 01 00 00 00 00 : 32 00 FF FF FF FF : 03 00 - 5E 00 32 00 2E 00 - 2수준
	 * 0C 00 00 00 00 00 : 32 00 FF FF FF FF : 03 00 - 5E 00 33 00 29 00
	 * 0C 01 00 00 00 00 : 32 00 FF FF FF FF : 03 00 - 5E 00 34 00 29 00 
	 * 0C 00 00 00 00 00 : 32 00 FF FF FF FF : 04 00 - 28 00 5E 00 35 00 29 00 
	 * 0C 01 00 00 00 00 : 32 00 FF FF FF FF : 04 00 - 28 00 5E 00 36 00 29 00 
	 * 2C 00 00 00 00 00 : 32 00 FF FF FF FF : 02 00 - 5E 00 37 00 - 7 수준
	 * 
	 * 01 00 ?? (시작 번호 추정)
	 * 01 00 00 00 - 1수준 시작번호 
	 * 01 00 00 00 - 2수준 시작번호
	 * 01 00 00 00 
	 * 01 00 00 00 
	 * 01 00 00 00 
	 * 01 00 00 00
	 * 01 00 00 00 - 7수준 시작번호
	 */
	
	static byte [] TYPE_A = new byte[]{
		0x17, 0x04, (byte)0xE0, 0x0A, 
		
		0x0C, 0x00, 0x00, 0x00, 0x00, 0x00, 0x32, 0x00, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, 
		0x03, 0x00, 0x5E, 0x00, 0x31, 0x00, 0x2E, 0x00, 
		
		0x0C, 0x01, 0x00, 0x00, 0x00, 0x00, 0x64, 0x00,	0x06, 0x00, 0x00, 0x00, 
		0x03, 0x00, 0x5E, 0x00, 0x32, 0x00, 0x2E, 0x00, 
		
		0x0C, 0x00, 0x00, 0x00,	0x00, 0x00, 0x32, 0x00, 0x07, 0x00, 0x00, 0x00, 
		0x03, 0x00, 0x5E, 0x00, 0x33, 0x00, 0x2E, 0x00, 
		
		0x0C, 0x01, 0x00, 0x00, 0x00, 0x00, 0x32, 0x00, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, 
		0x03, 0x00, 0x5E, 0x00,	0x34, 0x00, 0x29, 0x00, 
		
		0x0C, 0x00, 0x00, 0x00, 0x00, 0x00, 0x32, 0x00, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, 
		0x04, 0x00, 0x28, 0x00, 0x5E, 0x00, 0x35, 0x00, 0x29, 0x00, 
		
		0x0C, 0x01, 0x00, 0x00, 0x00, 0x00,	0x32, 0x00, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, 
		0x04, 0x00, 0x28, 0x00, 0x5E, 0x00, 0x36, 0x00, 0x29, 0x00,	
		
		0x6E, 0x00, 0x00, 0x00, (byte)0xD0, 0x07, 0x64, 0x00, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, 
		0x03, 0x00, 0x5E, 0x00,	0x37, 0x00, 0x29, 0x00, 
		
		0x04, 0x00, 
		0x04, 0x00, 0x00, 0x00, 
		0x05, 0x00, 0x00, 0x00, 
		0x03, 0x00,	0x00, 0x00, 
		0x06, 0x00, 0x00, 0x00, 
		0x01, 0x00, 0x00, 0x00, 
		0x01, 0x00, 0x00, 0x00, 
		0x01, 0x00,	0x00, 0x00};
			
	static NumberingRecordDecoder decoder ;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		decoder = new NumberingRecordDecoder();
	}
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test_numbering_format() throws UnsupportedEncodingException {
		byte [] b = new byte[]{0x5E, 0x00, 0x31, 0x00, 0x2E, 0x00};
		assertEquals("^1.", new String(b, "UTF-16LE"));
		
		b = new byte[]{0x5E, 0x00, 0x32, 0x00, 0x2E, 0x00};
		assertEquals("^2.", new String(b, "UTF-16LE"));
		
		b = new byte[]{0x5E, 0x00, 0x33, 0x00, 0x29, 0x00};
		assertEquals("^3)", new String(b, "UTF-16LE"));
		
		assertEquals("(^5)", new String(new byte[]{0x28, 0x00, 0x5E, 0x00, 0x35, 0x00, 0x29, 0x00}, "UTF-16LE"));
	}
	
	@Test
	public void test_Numbering() throws Exception {
		MockDocInfo docInfo = TestUtils.newMockDocInfo(TYPE_A);
		ByteArraySource data = new ByteArraySource(TYPE_A);
		NumberingRecord record = new NumberingRecord(TestUtils.newRecordHeader(data.mark()), docInfo);
		
		record = decoder.decode(record, data.rollback(), docInfo.getHwpContext());
		
		NumberingInfo info = record.getNumberingInfoAt(NumberingRecord.LEVEL_7);
		assertEquals (0x6E, info.getProperty().getValue().intValue()); //property 0x6E
		assertEquals (new Integer(2000), info.getRevisionWidth().getValue() ); // 너비조정(20pt)
		assertEquals (100, info.getDistanceFromText().getValue().intValue()); // 본문과의 거리 ( 100% )
		assertEquals (-1, info.getFontFaceId().getValue().intValue()); // 글자 모양 아이디 참조값
		assertEquals (false, info.hasFontFace() );
		
		// property inspection
		assertEquals (NumberingRecord.LEVEL_7, info.getLevel());
		assertEquals (1, info.getStartingNumber().getValue().intValue());
		assertEquals (true, info.isWidthAutoAdjust());
		assertEquals ( NumberingRecord.ALIGNMENT_RIGHT, info.getAlignment());
		assertEquals (0, info.getDistanceType());
		char [] ch = info.getFormatChars();
		// 자동으로 내어쓰기 [true]
	}
}
