package soopia.hwp.hexdump.structure;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import soopia.hwp.Constant;
import soopia.hwp.type.HwpContext;
import soopia.hwp.type.IDataType;
import soopia.hwp.type.IRecordStructure;
import soopia.hwp.type.StreamStructureFactory;
import soopia.hwp.type.record.DocPropertyRecord;
import soopia.hwp.type.stream.DocInfoStream;
import soopia.hwp.type.stream.FileHeaderInfo;

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
