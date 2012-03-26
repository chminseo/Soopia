package soopia.hwp.type;

import static org.junit.Assert.*;

import java.nio.ByteBuffer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import soopia.hwp.type.Dword;
import soopia.hwp.util.ByteArraySource;
import soopia.hwp.util.IByteSource;
/**
 * 본 제품은 한글과컴퓨터의 한글 문서 파일(.hwp) 공개 문서를 참고하여 개발하였습니다.
 * 
 * @author chmin
 *
 */
public class TestDword {
	IByteSource src;
	@Before
	public void setUp() throws Exception {
		src = new ByteArraySource(new byte[]{0x9, 0x32, (byte)0xa0, 0x10, (byte)0xbc, 0x21, 0x10});
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		
		Dword dw = new Dword(src.skip().consume(4));
		assertEquals(4, dw.getLength());
		assertEquals(new Long(3155206194L), dw.getValue());
		assertArrayEquals(new byte[]{0x32, (byte)0xa0, 0x10, (byte)0xbc}, dw.getBytes() );
		
	}

}
