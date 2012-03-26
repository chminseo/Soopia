package soopia.hwp.codec;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import soopia.hwp.TestUtils;
import soopia.hwp.type.record.BinDataRecord;
import soopia.hwp.util.ByteArraySource;
import soopia.hwp.util.Converter;
import soopia.hwp.util.IByteSource;
/**
 * 본 제품은 한글과컴퓨터의 한글 문서 파일(.hwp) 공개 문서를 참고하여 개발하였습니다.
 * 
 * @author chmin
 *
 */
public class TestBinDataRecordDecoder {

	static byte [] BIN_DATA ;
	
	static BinDataRecordDecoder decoder ;
	static BinDataRecord record;
	static MockDocInfo docInfo;
	
	static String LINK_DATA_FILEPATH ;
	@BeforeClass 
	public static void setUpClass() throws Exception {
		BIN_DATA = new byte [oleData.length + linkData.length + relPathData.length];
		System.arraycopy(oleData, 0, BIN_DATA, 0, oleData.length);
		System.arraycopy(linkData, 0, BIN_DATA, oleData.length, linkData.length);
		System.arraycopy(relPathData, 0, BIN_DATA, oleData.length + linkData.length, relPathData.length);
		
		decoder = new BinDataRecordDecoder();
		IByteSource data = new ByteArraySource(BIN_DATA);
		docInfo = TestUtils.newMockDocInfo(BIN_DATA);
		LINK_DATA_FILEPATH = Converter.getString(data.skip(24), 24 );
	}
	@Before
	public void setUp() throws Exception {
//		data.jump(0);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test_OLE_data() throws DecodingException {
		ByteArraySource ole = new ByteArraySource(oleData);
		BinDataRecord oleRecord = new BinDataRecord(
				TestUtils.newRecordHeader(ole.mark()), docInfo);
		ole.rollback();
		decoder.decode(oleRecord, ole, docInfo.getHwpContext());
		
		assertEquals (BinDataRecord.TYPE_STORAGE, oleRecord.getDataType());
		assertEquals ("0001", oleRecord.getBinDataId());
		assertEquals ("OLE", oleRecord.getExtension());
	}
	
	@Test
	public void test_LINK_data() throws DecodingException {
		ByteArraySource link = new ByteArraySource(linkData);
		link.mark();
		BinDataRecord linkRecord = new BinDataRecord(
				TestUtils.newRecordHeader(link),
				docInfo);
		link.rollback();
		decoder.decode(linkRecord, link, docInfo.getHwpContext());
		
		assertEquals (BinDataRecord.COMPRESS_DEFAULT, linkRecord.getCompressPolicy());
		assertEquals (BinDataRecord.ACCESS_NONE, linkRecord.getAccessState());
		assertEquals (BinDataRecord.TYPE_LINK, linkRecord.getDataType());
		assertEquals (LINK_DATA_FILEPATH, linkRecord.getAbsolutePath());
		assertEquals ("", linkRecord.getRelativePath());
	}
	
	@Test
	public void test_relative_path_data() throws DecodingException, UnsupportedEncodingException {
		ByteArraySource src = new ByteArraySource(relPathData);
		src.mark();
		String absPath = Converter.getString(src.skip(8), 2 * 0x49);
		String relPath = Converter.getString(src.skip(2), 2 * 0x1C);
		BinDataRecord relPathRecord = new BinDataRecord(
				TestUtils.newRecordHeader(src.rollback() ),
				docInfo);
		
		src.rollback();
		decoder.decode(relPathRecord, src, docInfo.getHwpContext());
		assertEquals (BinDataRecord.TYPE_LINK, relPathRecord.getDataType());
		assertEquals (absPath, relPathRecord.getAbsolutePath());
		assertEquals (relPath, relPathRecord.getRelativePath());
	}
	static byte [] oleData = new byte []{
		// header(4) + 속성(2) + BinDataId(2) + LEN(2) + WCHAR(2*LEN) 
		0x12, 0x04, (byte)0xC0, 0x00, 
		0x02, 0x00,
		0x01, 0x00,
		0x03, 0x00,
		0x4F, 0x00, 0x4C, 0x00,	0x45, 0x00, // "OLE"
	};
	static byte [] linkData = new byte[]{
		// header(4) + 속성(2)
		0x12, 0x04, (byte)0xE0, 0x01, 
		0x00, 0x00,
		// ABS_LEN(2) + WCHAR( 2* ABS_LEN )
		0x0C, 0x00, 
		0x46, 0x00, 0x3A, 0x00, 
		0x5C, 0x00,	0x69, 0x00, 
		0x6E, 0x00, 0x74, 0x00, 
		0x65, 0x00, 0x6C, 0x00, 
		0x2E, 0x00, 0x6A, 0x00,	
		0x70, 0x00,	0x67, 0x00, 
		// REL_LEN(2) + WCHAR(2 * REL_LEN)
		0x00, 0x00
	};
	static byte [] relPathData = new byte[]{
		0x12, 0x04, 0x00, 0x0D, // header
		0x00, 0x00, 			// property at Table 13 (p.16)
	
		/*  len(2) + WCHAR(2*len) */
		0x49, 0x00, 
		0x43, 0x00, 0x3A, 0x00, 0x5C, 0x00,	0x44, 0x00, 0x6F, 0x00, 0x63, 0x00, 0x75, 0x00, 0x6D, 0x00, 
		0x65, 0x00, 0x6E, 0x00, 0x74, 0x00,	0x73, 0x00, 0x20, 0x00, 0x61, 0x00, 0x6E, 0x00, 0x64, 0x00, 
		0x20, 0x00, 0x53, 0x00, 0x65, 0x00,	0x74, 0x00, 0x74, 0x00, 0x69, 0x00, 0x6E, 0x00, 0x67, 0x00, 
		0x73, 0x00, 0x5C, 0x00, 0x63, 0x00,	0x68, 0x00, 0x6D, 0x00, 0x69, 0x00, 0x6E, 0x00, 0x5C, 0x00, 
		0x4D, 0x00, 0x79, 0x00, 0x20, 0x00,	0x44, 0x00, 0x6F, 0x00, 0x63, 0x00, 0x75, 0x00, 0x6D, 0x00, 
		0x65, 0x00, 0x6E, 0x00, 0x74, 0x00,	0x73, 0x00, 0x5C, 0x00, 0x67, 0x00, 0x6F, 0x00, 0x6F, 0x00, 
		0x67, 0x00, 0x6C, 0x00, 0x65, 0x00,	0x64, 0x00, 0x6F, 0x00, 0x63, 0x00, 0x73, 0x00, 0x69, 0x00, 
		0x6D, 0x00, 0x67, 0x00, 0x5C, 0x00,	0x6C, 0x00, 0x69, 0x00, 0x6E, 0x00, 0x6B, 0x00, 0x5F, 0x00, 
		0x69, 0x00, 0x6D, 0x00, 0x61, 0x00,	0x67, 0x00, 0x65, 0x00, 0x2E, 0x00, 0x6A, 0x00, 0x70, 0x00, 
		0x67, 0x00, 
		/*  len(2) + WCHAR(2*len) */
		0x1C, 0x00, 
		0x67, 0x00,	0x6F, 0x00, 0x6F, 0x00, 0x67, 0x00, 
		0x6C, 0x00, 0x65, 0x00, 0x64, 0x00, 0x6F, 0x00, 
		0x63, 0x00,	0x73, 0x00, 0x69, 0x00, 0x6D, 0x00, 
		0x67, 0x00, 0x5C, 0x00, 0x6C, 0x00, 0x69, 0x00, 
		0x6E, 0x00,	0x6B, 0x00, 0x5F, 0x00, 0x69, 0x00, 
		0x6D, 0x00, 0x61, 0x00, 0x67, 0x00, 0x65, 0x00, 
		0x2E, 0x00,	0x6A, 0x00, 0x70, 0x00, 0x67, 0x00
	};

}
