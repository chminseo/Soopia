package soopia.hwp.hexdump.structure;

import static org.junit.Assert.*;

import java.io.File;
import java.nio.ByteBuffer;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import soopia.hwp.structure.IDataStructure;
import soopia.hwp.structure.IRecordStructure;
import soopia.hwp.structure.RecordStructureFactory;
import soopia.hwp.structure.StreamStructrue;
import soopia.hwp.structure.StreamStructureFactory;

public class TestRecordStructure {
	static RecordStructureFactory factory ;
	
	@BeforeClass
	public static void setUpClass() throws Exception {
		factory = new RecordStructureFactory();
	}

	@AfterClass
	public static void tearDown() throws Exception {
		factory = null ;
	}

	@Test
	public void test_record_structure_factory() {
		IDataStructure mockDS = createMockStrcture();
		List<IRecordStructure> structs = factory.createRecordStructures(mockDS);
		assertEquals (2, structs.size());
		
		IRecordStructure rs = structs.get(0);
		
		assertEquals(new Integer(26), rs.getDataLength());
		assertEquals(new Integer(4), rs.getHeaderLength());
		assertEquals(new Integer(0x010), rs.getTagValue());
		assertEquals("HWPTAG_DOCUMENT_PROPERTIES", rs.getTagName());
		
		ByteBuffer buf = rs.getBuffer();
		assertEquals (0, buf.position());
		assertEquals(30, buf.capacity());
		
		byte [] data = rs.getBytes();
		assertEquals(30, data.length);
		assertEquals((byte)0xA0, data[2]);
		
		rs = structs.get(1);
		
		assertEquals(new Integer(64), rs.getDataLength());
		assertEquals(new Integer(4), rs.getHeaderLength());
		assertEquals(new Integer(0x011), rs.getTagValue());
		assertEquals("HWPTAG_ID_MAPPINGS", rs.getTagName());
		
		buf = rs.getBuffer();
		assertEquals (0, buf.position());
		assertEquals(68, buf.capacity());
		
		data = rs.getBytes();
		assertEquals(68, data.length);
		assertEquals((byte)0x02, data[8]);
	}

	private IDataStructure createMockStrcture() {
		/* 두 개의 레코드 데이터
		 * Tag ID : 0x10
		 * Level  : 0x00
		 * SIZE   : 0x1A
		 * 
		 * Tag ID : 0x11
		 * Level  : 0x00
		 * SIZE   : 0x40
		 * */
		byte [] buf = new byte[]{0x10, 0x00, (byte)0xA0, 0x01, 0x01, 0x00, 0x01, 0x00, 0x01, 0x00, 0x01, 0x00, 0x01, 0x00, 0x01, 0x00, 
				0x01, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x12, 0x00, 0x00, 0x00, 0x11, 0x00, 
				0x00, 0x04, 0x00, 0x00, 0x00, 0x00, 0x02, 0x00, 0x00, 0x00, 0x02, 0x00, 0x00, 0x00, 0x02, 0x00, 
				0x00, 0x00, 0x02, 0x00, 0x00, 0x00, 0x02, 0x00, 0x00, 0x00, 0x02, 0x00, 0x00, 0x00, 0x02, 0x00, 
				0x00, 0x00, 0x01, 0x00, 0x00, 0x00, 0x05, 0x00, 0x00, 0x00, 0x03, 0x00, 0x00, 0x00, 0x01, 0x00, 
				0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x0C, 0x00, 0x00, 0x00, 0x0E, 0x00, 0x00, 0x00, 0x00, 0x00, 
				0x00, 0x00};
		ByteBuffer data = ByteBuffer.allocate(buf.length).put(buf);
		return new MockStreamStructue(data);
	}
	
	static class MockStreamStructue extends StreamStructrue{

		public MockStreamStructue(ByteBuffer data) {
			super("C:" + File.separator + "mock.hwp", "Mock Structue", data);
			// TODO Auto-generated constructor stub
		}
		
	}
	
	

}
