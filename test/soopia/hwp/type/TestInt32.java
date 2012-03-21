package soopia.hwp.type;

import static org.junit.Assert.*;

import java.nio.ByteBuffer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestInt32 {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		ByteBuffer data = ByteBuffer.wrap(
				new byte []{0x00, // dummy for test 
					0x0D, (byte)0xB4, (byte) 0xA2, 0x10}
		);
		assertEquals (
				new Integer(0x10A2B40D),
				new Int32(data, 1).getValue());
	}

}
