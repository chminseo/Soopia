package soopia.hwp.hexdump.structure;

import static org.junit.Assert.*;

import java.nio.charset.Charset;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestWChar {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test_encoding_info() {
		// BC 14 D0 D5 "����"
		
		byte [] bs = new byte[]{0x14, (byte)0xbc, (byte)0xd5, (byte)0xd0};
		String str = new String (bs, Charset.forName("utf-16LE"));
		assertEquals("����", str);
		
//		str = "����";
//		bs = str.getBytes(Charset.forName("utf-16"));
//		printBytes(bs);
	}
	
//	private void printBytes(byte [] bs){
//		StringBuilder sb = new StringBuilder();
//		for (byte  b : bs){
//			sb.append( "0x" + Integer.toHexString(b) + " ");
//		}
//		System.out.println(sb.toString());
//	}

}
