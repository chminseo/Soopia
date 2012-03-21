package soopia.hwp.type;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;
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
	public void test_encoding_info() throws UnsupportedEncodingException {
		// BC 14 D0 D5 "����"
		byte [] bs = new byte[]{0x14, (byte)0xbc, (byte)0xd5, (byte)0xd0};
		String str = new String (bs, Charset.forName("utf-16LE"));
		assertEquals("����", str);
		
		bs = new byte[]{0x1C, (byte)0xC8, 
				(byte)0xA9, (byte)0xBA, 0x7C, (byte)0xB7, 0x78, (byte)0xC7};
		assertEquals ("�������", new String(bs, "UTF-16LE"));
	}
	
//	private void printBytes(byte [] bs){
//		StringBuilder sb = new StringBuilder();
//		for (byte  b : bs){
//			sb.append( "0x" + Integer.toHexString(b) + " ");
//		}
//		System.out.println(sb.toString());
//	}

}
