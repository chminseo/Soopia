package soopia.hwp.type;

import static org.junit.Assert.*;

import java.nio.ByteBuffer;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestColorRef {

	static ByteBuffer colorBuf ;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		byte [] b = new byte[]{
			(byte)0xAB, (byte)0xCD, (byte)0xEF, 0x00, 
			(byte)0x83, 0x4D, 0x00, 0x00, 
			0x00, 0x00, (byte)0xFF, 0x00, 
			(byte)0xFF, (byte)0xFF, 0x00, 0x00 
		};
		colorBuf = ByteBuffer.wrap(b);
	}

	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		// 0xBBGGRR format
		int val = 0x00EFCDAB;
		ColorRef cr = new ColorRef(colorBuf, 0);
		assertEquals (val, cr.getValue().intValue());
		assertEquals (0xAB, cr.getRed().intValue());
		assertEquals (0xCD, cr.getGreen().intValue());
		assertEquals (0xEF, cr.getBlue().intValue());
		assertEquals ("#ABCDEF", cr.toWebColorString());
		
		val = 0x00834D00;
		assertEquals ("#834D00", new ColorRef(colorBuf, 4).toWebColorString());
		assertEquals ("#0000FF", new ColorRef(colorBuf, 8).toWebColorString());
		assertEquals ("#FFFF00", new ColorRef(colorBuf, 12).toWebColorString());
	}

}
