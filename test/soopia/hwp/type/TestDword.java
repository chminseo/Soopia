package soopia.hwp.type;

import static org.junit.Assert.*;

import java.nio.ByteBuffer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import soopia.hwp.type.Dword;
/**
 * 본 제품은 한글과컴퓨터의 한글 문서 파일(.hwp) 공개 문서를 참고하여 개발하였습니다.
 * 
 * @author chmin
 *
 */
public class TestDword {
	ByteBuffer src;
	@Before
	public void setUp() throws Exception {
		src = ByteBuffer.allocate(7);
		src.put(new byte[]{0x9, 0x32, (byte)0xa0, 0x10, (byte)0xbc, 0x21, 0x10});
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		src.clear();
		Dword dw = new Dword(src, 1);
		assertEquals(4, dw.getLength());
		assertEquals(new Long(3155206194L), dw.getValue());
		assertArrayEquals(new byte[]{0x32, (byte)0xa0, 0x10, (byte)0xbc}, dw.getBytes() );
		
	}

}
