package soopia.hwp.codec;

import static org.junit.Assert.*;

import java.nio.ByteBuffer;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import soopia.hwp.TestUtils;
import soopia.hwp.type.record.FaceNameRecord;
/**
 * 글꼴
 * 
 * @author chmin
 * @tagID HWPTAG_FACE_NAME
 * @page p.17
 */
public class TestFontFaceNameDecoder {
	
	static byte [] TWO_FACE_DATA = new byte[]{
		(byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff, // dummy for test
		// header(4) + property(1) :: 4+5 bytes
		0x13, 0x04, (byte)0xB0, 0x02,
		0x61,
		// len1(2) + face name array (2*len1) :: 4+17 bytes
		0x05,0x00, 
		0x68, (byte)0xD5, 0x08, (byte)0xCD, 0x6C, 
		(byte)0xB8, (byte)0xCB, (byte)0xB3, (byte)0xC0, (byte)0xC6, 
		// font type info (10) :: 27 bytes
		0x02, 0x03, 0x05, 0x04, 0x00, 
		0x01, 0x01, 0x01, 0x01, 0x01, 
		// len3(2) + base face name array (2*len3) :: 4+47 bytes
		0x09, 0x00, 
		0x48, 0x00, 0x43, 0x00, 0x52, 0x00, 0x20, 0x00, 0x44, 
		0x00, 0x6F, 0x00, 0x74, 0x00, 0x75, 0x00, 0x6D, 0x00 ,
		
		/* another face data */
		0x13, 0x04, (byte)0xF0, 0x02, 
		0x61, 
		0x08, 0x00, 
		0x43, 0x00, 0x6F, 0x00, 0x6E, 0x00, 0x73, 0x00, 0x6F, 0x00, 0x6C, 0x00, 0x61, 0x00, 0x73, 0x00,
		0x02, 0x0B, 0x06, 0x09, 0x02, 
		0x02, 0x04, 0x03, 0x02, 0x04, 
		0x08, 0x00, 
		0x43, 0x00, 0x6F, 0x00,	0x6E, 0x00, 0x73, 0x00, 
		0x6F, 0x00, 0x6C, 0x00, 0x61, 0x00, 0x73, 0x00
	};
	static FaceNameDecoder decoder = new FaceNameDecoder();
	static MockDocInfo docInfo;
	static ByteBuffer data ;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		data = ByteBuffer.wrap(TWO_FACE_DATA);
		docInfo = TestUtils.newMockDocInfo(data);
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test_decoding_font_face_info() throws DecodingException {
		FaceNameRecord fNameRecord = new FaceNameRecord(
				TestUtils.newRecordHeader(data, 4), 
				docInfo, 4);
		decoder.decode(fNameRecord , fNameRecord.getBuffer() , docInfo.getHwpContext());
		/* FONT PROPERTY */
		assertEquals(false, fNameRecord.hasAlternateFont());
		assertEquals(true, fNameRecord.hasFaceTypeInfo());
		assertEquals(true, fNameRecord.hasDefaultFont());
		/* FONT NAME */
		assertEquals ("함초롬돋움", fNameRecord.getFontName());
		assertEquals ("HCR Dotum", fNameRecord.getDefaultFontName());
		
		/* second font infomation */
		fNameRecord = new FaceNameRecord(TestUtils.newRecordHeader(data, 4+ 47), docInfo, 4 + 47);
		decoder.decode(fNameRecord, fNameRecord.getBuffer(), docInfo.getHwpContext());
		assertEquals(false, fNameRecord.hasAlternateFont());
		assertEquals(true, fNameRecord.hasFaceTypeInfo());
		assertEquals(true, fNameRecord.hasDefaultFont());
		assertEquals ("Consolas", fNameRecord.getFontName());
		assertEquals ("Consolas", fNameRecord.getDefaultFontName());
	}

}
