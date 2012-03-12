package soopia.hwp.hexdump.structure;

import static org.junit.Assert.*;

import java.nio.ByteBuffer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import soopia.hwp.type.UInt16;
import soopia.hwp.type.UInt32;
/**
 * unsigned __int16 에 해당하는 자료형( 0 ~ 65535 )
 * @author chmin
 *
 */
public class TestUInt16 {

	ByteBuffer src  ;
	@Before
	public void setUp() throws Exception {
		src = ByteBuffer.allocate(9);
		src.put(new byte[]{0x10, (byte)0xc0, 0x21, 0x00, (byte)0xab, 0x10, (byte)0xc0, 0x0, 0x0});
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test_load() {
		UInt16 int16 = new UInt16(src, 2);
		assertArrayEquals (new byte[]{0x21, 0x00}, int16.getBytes());
		assertEquals (2, int16.getLength());
		assertEquals (2, int16.getOffset());
		assertEquals (
				ByteBuffer.allocate(2).put(new byte[]{0x21, 0x00}),
				int16.getBuffer());
		assertEquals (new Integer(0x21), int16.getValue());
	}
	@Test
	public void test_equals(){
		UInt16 int16a = new UInt16(src,  0);
		assertEquals(int16a, new UInt16(src, 5));
		assertFalse(int16a.equals(new UInt32(src, 5)));
		assertFalse(new UInt32(src, 5).equals(int16a));
	}
}
