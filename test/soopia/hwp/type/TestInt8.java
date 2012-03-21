package soopia.hwp.type;

import static org.junit.Assert.*;

import java.nio.ByteBuffer;

import org.junit.Test;

public class TestInt8 {
	
	@Test
	public void test() {
		ByteBuffer data = ByteBuffer.wrap(new byte[]{0x22, 0x33 });
		assertEquals(0x33, new Int8(data, 1).getValue().byteValue());
	}

}
