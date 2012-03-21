package soopia.hwp.type;

import static org.junit.Assert.*;

import java.nio.ByteBuffer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import soopia.hwp.type.HwpByte;
/**
 * 본 제품은 한글과컴퓨터의 한글 문서 파일(.hwp) 공개 문서를 참고하여 개발하였습니다.
 * 
 * @author chmin
 *
 */
public class TestHwpByte {

	ByteBuffer src ;
	@Before
	public void setUp() throws Exception {
		src = ByteBuffer.allocate(6);
		src.put(new byte[]{5, -6, 7, 8, 9, 10});
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		HwpByte hb = new HwpByte(src, 4);
		assertArrayEquals(new byte[]{9}, hb.getBytes());
		assertArrayEquals(new byte[]{9}, hb.getBytes());
		assertEquals (1, hb.getLength());
		assertEquals (new Integer(9), hb.getValue());
	}
	@Test
	public void test_invalid_parameters() {
		try {
			new HwpByte(src, 1);
			fail("RuntimeException expected, but no exception ocurred.");
		} catch (RuntimeException e) {}
	}
}
