package soopia.hwp.hexdump.structure;

import static org.junit.Assert.*;

import java.nio.Buffer;
import java.nio.ByteBuffer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import soopia.hwp.structure.Word;

public class TestWord {

	ByteBuffer src ;
	@Before
	public void setUp() throws Exception {
		src = ByteBuffer.allocate(6);
		src.put(new byte[]{0x40, 0x00, 0x10, 0x12, 0x20, (byte)0xFD});
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		Word word = new Word(src, 2);
		assertEquals (new Integer(0x1210), word.getValue());
		assertEquals (2, word.getLength());
		assertEquals (2, word.getOffset());
	}

}
