package soopia.hwp.type;

import static org.junit.Assert.*;

import java.nio.ByteBuffer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import soopia.hwp.codec.DecodingException;
import soopia.hwp.codec.IdMappingRecordDecoder;
import soopia.hwp.type.record.IDMappingsRecord;
import soopia.hwp.type.stream.RecordHeader;

public class TestIdMappingRecordDecoder {
	IDMappingsRecord record;
	IdMappingRecordDecoder decoder ;
	IStreamStruct ds;
	ByteBuffer buf ;
	@Before
	public void setUp() throws Exception {
		buf = ByteBuffer.allocate(data.length - 4);
		buf.put(data, 4, data.length - 4);
		
		ds = new MockDocInfo(null, null);
		RecordHeader header = new RecordHeader();
		header.baseHeader = new Dword(buf, 0);
		
		record = new IDMappingsRecord(header, ds, 0);
		decoder = new IdMappingRecordDecoder();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test_decoding_IdMappingRecord() throws DecodingException {
		record = decoder.decode(record, buf, null);
		
	}
	
	public static class MockDocInfo extends AbstactStream {

		public MockDocInfo(HwpContext context, ByteBuffer data) {
			super(context, "MockDocInfo", data);
			// TODO Auto-generated constructor stub
		}
		
		@Override
		public ByteBuffer getBuffer() {
			ByteBuffer buf = ByteBuffer.allocate(data.length - 4);
			buf.put(data, 4, data.length - 4);
			return buf;
		}
	}
	
	static byte [] data = new byte[]{
		0x01, 0x00, 0x00, 0x04, 
		0x04, 0x00, 0x00, 0x00, 
		0x02, 0x00, 0x00, 0x00, 
		0x02, 0x00, 0x00, 0x00, 
		0x02, 0x00,	0x00, 0x00, 
		0x02, 0x00, 0x00, 0x00, 
		0x02, 0x00, 0x00, 0x00, 
		0x02, 0x00, 0x00, 0x00, 
		0x02, 0x00,	0x00, 0x00, 
		0x01, 0x00, 0x00, 0x00, 
		0x05, 0x00, 0x00, 0x00, 
		0x01, 0x00, 0x00, 0x00, 
		0x01, 0x00,	0x00, 0x00, 
		0x00, 0x00, 0x00, 0x00, 
		0x0D, 0x00, 0x00, 0x00, 
		0x0E, 0x00, 0x00, 0x00, 
		0x00, 0x00,	0x00, 0x00
	};
	

}
