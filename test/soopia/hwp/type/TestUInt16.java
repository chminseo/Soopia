package soopia.hwp.type;

import static org.junit.Assert.*;

import java.nio.ByteBuffer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import soopia.hwp.type.UInt16;
import soopia.hwp.type.UInt32;
import soopia.hwp.util.ByteArraySource;
import soopia.hwp.util.IByteSource;
/**
 * 본 제품은 한글과컴퓨터의 한글 문서 파일(.hwp) 공개 문서를 참고하여 개발하였습니다.
 * 
 * unsigned __int16 에 해당하는 자료형( 0 ~ 65535 )
 * @author chmin
 *
 */
public class TestUInt16 {

	IByteSource src  ;
	@Before
	public void setUp() throws Exception {
		src = new ByteArraySource(
				new byte[]{0x10, (byte)0xc0, 0x21, 0x00, (byte)0xab, 0x10, (byte)0xc0, 0x0, 0x0});
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test_load() {
		UInt16 int16 = new UInt16(src.skip(2).consume(2));
		assertArrayEquals (new byte[]{0x21, 0x00}, int16.getBytes());
		assertEquals (new Integer(0x21), int16.getValue());
	}
	@Test
	public void test_equals(){
		UInt16 int16a = new UInt16(src.consume(2));//new UInt16(src,  0);
		assertEquals(int16a, new UInt16(src.skip(3).consume(2))); // new UInt16(src, 5)
	}
}
