package soopia.hwp.hexdump.structure;

import static org.junit.Assert.*;

import java.nio.ByteBuffer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import soopia.hwp.structure.Dword;

public class TestDword {
	ByteBuffer src;
	@Before
	public void setUp() throws Exception {
		src = ByteBuffer.allocate(8);
		src.put(new byte[]{0x9, 0x32, (byte)0xa0, 0x10, (byte)0xbc, 0x21, 0x10});
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		Dword dw = new Dword(src, 1);
		assertEquals(4, dw.getLength());
		assertEquals(new Long(3155206194L), dw.getValue());
		assertArrayEquals(new byte[]{0x32, (byte)0xa0, 0x10, (byte)0xbc}, dw.getBytes() );
		
	}

}
