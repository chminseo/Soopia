package soopia.hwp.hexdump.structure;

import static org.junit.Assert.*;

import java.nio.ByteBuffer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import soopia.hwp.structure.UInt16;
/**
 * unsigned __int16 ¿¡ ÇØ´ç ( 0 ~ 65535 )
 * @author chmin
 *
 */
public class TestUInt16 {

	ByteBuffer src  ;
	@Before
	public void setUp() throws Exception {
		src = ByteBuffer.allocate(5);
		src.put(new byte[]{0x10, (byte)0xc0, 0x21, 0x00, (byte)0xab});
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		UInt16 int16 = new UInt16(src, 2);
		assertArrayEquals (new byte[]{0x21, 0x00}, int16.getBytes());
		assertEquals (2, int16.getLength());
		assertEquals (2, int16.getOffset());
		assertEquals (
				ByteBuffer.allocate(2).put(new byte[]{0x21, 0x00}),
				int16.getBuffer());
		assertEquals (new Integer(0x21), int16.getValue());
		
	}
}
