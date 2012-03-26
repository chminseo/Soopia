package soopia.hwp.type;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import soopia.hwp.util.ByteArraySource;
import soopia.hwp.util.IByteSource;

public class TestHwpUnit {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test_hwpUnit() {
		IByteSource buf = new ByteArraySource(new byte[]{0x00, 0x00, (byte)0xE8, 0x03, 0x00, 0x00 });
		HwpUnit unit = new HwpUnit(buf.skip(2).consume(4));//new HwpUnit(buf, 2);
		assertEquals (1000, unit.getValue().longValue());
		assertEquals (4, unit.getBytes().length);
		
		unit = new HwpUnit(buf.jump(0).consume(4));//new HwpUnit(buf, 0);
		assertEquals (0x3E80000L, unit.getValue().longValue());
		assertEquals (4, unit.getBytes().length);
	}

}
