package soopia.hwp.hexdump.structure;

import static org.junit.Assert.*;

import java.nio.ByteBuffer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import soopia.hwp.structure.HwpByte;

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
		HwpByte hb = new HwpByte(4, src);
		assertArrayEquals(new byte[]{9}, hb.getBytes());
		assertArrayEquals(new byte[]{9}, hb.getBytes());
		assertEquals (1, hb.getLength());
		assertEquals (new Integer(9), hb.getValue());
	}
	@Test
	public void test_invalid_parameters() {
		try {
			new HwpByte(1, src);
			fail("RuntimeException expected, but no exception ocurred.");
		} catch (RuntimeException e) {}
	}

}
