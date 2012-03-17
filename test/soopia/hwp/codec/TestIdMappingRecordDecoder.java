package soopia.hwp.codec;

import static org.junit.Assert.*;

import java.nio.ByteBuffer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import soopia.hwp.TestUtils;
import soopia.hwp.codec.DecodingException;
import soopia.hwp.codec.IdMappingRecordDecoder;
import soopia.hwp.type.record.IDMappingsRecord;

public class TestIdMappingRecordDecoder {
	IDMappingsRecord record;
	IdMappingRecordDecoder decoder ;
	MockDocInfo ds;
	ByteBuffer buf ;
	@Before
	public void setUp() throws Exception {
		buf = ByteBuffer.wrap(data);
		ds = TestUtils.newMockDocInfo(buf);
		record = new IDMappingsRecord(TestUtils.newRecordHeader(buf, 0), ds, 0);
		decoder = new IdMappingRecordDecoder();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test_decoding_IdMappingRecord() throws DecodingException {
		record = decoder.decode(record, buf, null);
		assertEquals(13, record.getNumOfParaShape().getValue().intValue());
		assertEquals(14, record.getNumOfStyle().getValue().intValue());
		
	}
	
	static byte [] data = new byte[]{
		0x01, 0x00, 0x00, 0x04, // header
		0x04, 0x00, 0x00, 0x00, // 바이너리 데이터
		0x02, 0x00, 0x00, 0x00, // 한글 글꼴 개수
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
