package soopia.hwp.codec;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import soopia.hwp.TestUtils;
import soopia.hwp.type.record.TabRecord;
import soopia.hwp.type.record.TabRecord.TabData;
import soopia.hwp.util.ByteArraySource;
import soopia.hwp.util.IByteSource;

public class TestTabRecordDecoder {

	static byte [] TAB_THREE = new byte[]{
		0x16, 0x04, 0x00, 0x02, //header
		0x01, 0x00, 0x00, 0x00, // property
		
		0x03, 0x00, 0x00, 0x00, // tab count
		/*  */
		0x20, 0x4E, 0x00, 0x00,	0x00, 0x01, 0x00, 0x00, 
		0x40, (byte)0x9C, 0x00, 0x00, 0x00, 0x03, 0x00, 0x00, 
		0x60, (byte)0xEA, 0x00, 0x00, 0x00, 0x03, 0x00, 0x00
	};
	
	static byte [] TAB_FOUR = new byte[]{
		0x16, 0x04, (byte)0x80, 0x02, 
		0x01, 0x00, 0x00, 0x00, 
		0x04, 0x00, 0x00, 0x00, 
		0x00, 0x00, 0x00, 0x00,	0x01, 0x07, 0x00, 0x00, 
		0x20, 0x4E, 0x00, 0x00, 0x01, 0x07, 0x00, 0x00, 
		0x40, (byte)0x9C, 0x00, 0x00, 0x01, 0x07, 0x00, 0x00, 
		0x60, (byte)0xEA, 0x00, 0x00, 0x01, 0x07, 0x00, 0x00};
	
	static TabRecordDecoder decoder;
	static IByteSource data ;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		decoder = new TabRecordDecoder();
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test_read_tabInfo() throws DecodingException {
		MockDocInfo docInfo = TestUtils.newMockDocInfo(TAB_THREE);
		data = new ByteArraySource(TAB_THREE);
		TabRecord record = new TabRecord(
				TestUtils.newRecordHeader(data.mark()), docInfo);
		record = decoder.decode(record, data.rollback(), docInfo.getHwpContext());
		
		assertEquals(1, record.getTabProperty().getValue().intValue());
		assertEquals(true, record.isLeftTabAlways());
		assertEquals(false, record.isRightTabAlways());
		assertEquals(3, record.getTabCount());
		
		/* 0x60, (byte)0xEA, 0x00, 0x00, 
		 * 0x00, 
		 * 0x03, 
		 * 0x00, 0x00 
		 * */
		TabData tab = record.getTabAt(2);
		assertEquals(60000, tab.getWidth().getValue().longValue());
		assertEquals(TabRecord.ORIENTATION_LEFT,
				tab.getOrientation().getValue().intValue());
		assertEquals(3, tab.getFillStyleId().getValue().intValue());
		
	}
}
