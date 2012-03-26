package soopia.hwp.type;

import static org.junit.Assert.*;

import java.nio.ByteBuffer;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import soopia.hwp.util.ByteArraySource;
import soopia.hwp.util.IByteSource;
/**
 * 본 제품은 한글과컴퓨터의 한글 문서 파일(.hwp) 공개 문서를 참고하여 개발하였습니다.
 * 
 * @author chmin
 *
 */
public class TestUInt8 {

	
	static IByteSource src ;
	@BeforeClass
	public static void setUpClass() throws Exception {
		src = new ByteArraySource(
				new byte[]{0x10, (byte)0xc0, 0x21, 0x00, (byte)0xab, 0x10, (byte)0xc0, 0x0, 0x0});
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		UInt8 ui = new UInt8(src.skip().consume(1));
		assertEquals (new Integer(0xc0), ui.getValue());
		assertEquals(1, ui.getLength());
	}
}
