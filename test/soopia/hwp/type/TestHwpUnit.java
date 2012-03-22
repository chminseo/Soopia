package soopia.hwp.type;

import static org.junit.Assert.*;

import java.nio.ByteBuffer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestHwpUnit {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		ByteBuffer buf =ByteBuffer.wrap( new byte[]{0x00, 0x00, (byte)0xE8, 0x03, 0x00, 0x00 } );
		HwpUnit unit = new HwpUnit(buf, 2);
		assertEquals (1000, unit.getValue().longValue());
		assertEquals (4, unit.getBytes().length);
		
		unit = new HwpUnit(buf, 0);
		assertEquals (0x3E80000L, unit.getValue().longValue());
		assertEquals (4, unit.getBytes().length);
	}

}
