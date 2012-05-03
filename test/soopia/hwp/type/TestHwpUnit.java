package soopia.hwp.type;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import soopia.hwp.util.ByteArraySource;
import soopia.hwp.util.IByteSource;

public class TestHwpUnit {

	static IByteSource DATA ;
	@BeforeClass
	public static void setUpClass() throws Exception {
		DATA = new ByteArraySource(new byte[]{0x00, 0x00, (byte)0xE8, 0x03, 0x00, 0x00 });
	}

	@Before
	public void setUp() throws Exception {
		DATA.jump(0);
	}
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test_hwpUnit32() {
		HwpUnit unit = new HwpUnit(DATA.skip(2).consume(4));//new HwpUnit(buf, 2);
		assertEquals (1000, unit.getValue().longValue());
		assertEquals (4, unit.getBytes().length);
		
		unit = new HwpUnit(DATA.jump(0).consume(4));//new HwpUnit(buf, 0);
		assertEquals (0x3E80000L, unit.getValue().longValue());
		assertEquals (4, unit.getBytes().length);
	}
	
	@Test
	public void test_hwpUnit16() {
		HwpUnit16 hu = null;
		// E8 03
		hu= new HwpUnit16(DATA.skip(2).consume(2));
		assertEquals (1000, hu.getValue().intValue());
		assertEquals (2, hu.getBytes().length);
		// 00 E8
		hu= new HwpUnit16(DATA.jump(0).skip(1).consume(2));
		assertEquals (59392, hu.getValue().intValue());
	}

}
