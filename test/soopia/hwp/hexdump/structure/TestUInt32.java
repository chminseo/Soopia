package soopia.hwp.hexdump.structure;

import static org.junit.Assert.*;

import java.nio.ByteBuffer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import soopia.hwp.type.UInt32;

public class TestUInt32 {

	ByteBuffer src ;
	@Before
	public void setUp() throws Exception {
		src = ByteBuffer.allocate(10);
		src.put(new byte[]{
				(byte)0x9a, (byte)0xab, 0x21, 0x00, 0x1f,
				0x5d, 0x2a, 0x09, 0x00, 0x11});
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		UInt32 int32 = new UInt32(src, 1);
		assertArrayEquals (
				new byte[]{(byte)0xab, 0x21, 0x00, 0x1f},
				int32.getBytes());
		assertEquals (4, int32.getLength());
		assertEquals (1, int32.getOffset());
		assertEquals (
				ByteBuffer.allocate(4).put(new byte[]{(byte)0xab, 0x21, 0x00, 0x1f}),
				int32.getBuffer());
		assertEquals (new Long(0x1F0021AB), int32.getValue());
	}

}
