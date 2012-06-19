package soopia.hwp.codec;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestBulletRecordDecoder {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test_bullet_char() throws UnsupportedEncodingException {
		String encoding = "UTF-16LE";
		
		byte [] data = new byte[]{(byte)0xB6, (byte)0x25};
		System.out.println(new String(data, encoding));
		
		// 6C F0 - [¡Ü]
		data = new byte[]{(byte)0xF0, (byte)0x6C};
		System.out.println("value : " + new String(data));
		
		String val = "¡Ü";
		data = val.getBytes(encoding);
		System.out.println("::" + Arrays.toString(data));
		
		data = new byte[]{ (byte)0xCF, 0x25};
		System.out.println(data[0] + ", " + new String(data, encoding));
		
		// FE F0
		data = new byte[]{(byte)0xFE, (byte)0xF0};
		System.out.println(new String(data, encoding));
		
		SortedMap<String,Charset> set = Charset.availableCharsets();
		Set<String> key = set.keySet();
		Iterator<String> it = key.iterator();
		while ( it.hasNext() ) {
//			System.out.println(it.next());
			it.next();
		}
		
	}

}
