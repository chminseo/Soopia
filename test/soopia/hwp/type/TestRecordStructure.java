package soopia.hwp.type;

import static org.junit.Assert.*;

import java.nio.ByteBuffer;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import soopia.hwp.type.AbstactStream;
import soopia.hwp.type.IStreamStruct;
/**
 * 본 제품은 한글과컴퓨터의 한글 문서 파일(.hwp) 공개 문서를 참고하여 개발하였습니다.
 * 
 * @author chmin
 *
 */
public class TestRecordStructure {
	
	@BeforeClass
	public static void setUpClass() throws Exception {
	}

	@AfterClass
	public static void tearDown() throws Exception {
	}
	
	@Test
	public void test_null(){}

	private IStreamStruct createMockStrcture() {
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
//		ByteBuffer data = ByteBuffer.allocate(buf.length).put(buf);
		return new MockStreamStructue(buf);
	}
	
	static class MockStreamStructue extends AbstactStream{

		public MockStreamStructue(byte [] data) {
			super(null, "Mock Structue", data);
			// TODO Auto-generated constructor stub
		}
		
	}
	
	

}
