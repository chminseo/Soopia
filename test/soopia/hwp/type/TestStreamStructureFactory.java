package soopia.hwp.type;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import soopia.hwp.Constant;
import soopia.hwp.type.HwpContext;
import soopia.hwp.type.StreamStructureFactory;
import soopia.hwp.type.record.DocPropertyRecord;
import soopia.hwp.type.stream.DocInfoStream;
import soopia.hwp.type.stream.FileHeaderInfo;
/**
 * 본 제품은 한글과컴퓨터의 한글 문서 파일(.hwp) 공개 문서를 참고하여 개발하였습니다.
 * 
 * @author chmin
 *
 */
public class TestStreamStructureFactory {
	static StreamStructureFactory factory ;
	static String fName ;
	static File hwpFile = null;
	static HwpContext ctx ;
	@BeforeClass
	public static void setUp() throws Exception {
		fName = System.getProperty("sample.file");
		factory = new StreamStructureFactory();
		hwpFile = new File (fName);
		ctx = factory.createHwpContext(hwpFile);
	}

	@AfterClass
	public static void tearDown() throws Exception {
	}
	@Test
	public void test_hwp_fileInfo_creation() throws IOException {
		FileHeaderInfo fInfo = (FileHeaderInfo) ctx.getFileHeaderInfo();
		assertEquals ("5.0.3.0", fInfo.getVersionString());
		assertFalse (fInfo.isCompressed());
		assertFalse (fInfo.isPassword());
		assertFalse (fInfo.isDistribution());
		
		DocInfoStream docInfo = (DocInfoStream)ctx.getDocInfo();
		assertEquals (1, 
				docInfo.getRecord(Constant.DOCUMENT_PROPERTIES)
				.size());
	}
	@Test
	public void test_docInfo_creation() throws IOException {
		DocInfoStream docInfo = (DocInfoStream) ctx.getDocInfo();
		DocPropertyRecord record = (DocPropertyRecord) docInfo.getRecord(Constant.DOCUMENT_PROPERTIES).get(0);
		assertEquals (new Integer(1), record.getStartOfPageNum().getValue());
		assertEquals (new Integer(1), record.getStartNumOfFootNote().getValue());
		
	}

}
