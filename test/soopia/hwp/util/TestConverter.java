package soopia.hwp.util;

import static org.junit.Assert.*;

import java.nio.ByteBuffer;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import soopia.hwp.util.Converter;
/**
 * 본 제품은 한글과컴퓨터의 한글 문서 파일(.hwp) 공개 문서를 참고하여 개발하였습니다.
 * 
 * @author chmin
 *
 */
public class TestConverter {
	static byte [] src ;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		/* Littlen Endian */
		src = new byte[]{0x10, 0x01, 0x10, 0x01, (byte)0xA0};
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void test_read_UINT16_integer () {
		assertEquals (0x0110, Converter.getUInt16(src, 0));
		assertEquals (0x1001, Converter.getUInt16(src, 1));
		try {
			// offset(4)에서 읽어들일 수 있는 바이트는 1바이트 뿐
			// 필요한 2바이트가 안되므로 예외 발생.
			Converter.getUInt16(src, 4);
			fail ("exception expedted, but not thrown");
		} catch (Exception e) {}
		
	}
	@Test
	public void test_read_UINT32_long () {
		assertEquals (0xA0011001L, Converter.getUInt32(src, 1));
		/*
		 * 배열의 길이(2)가 최소한 읽어들여야할 길이(4)보다 작은 경우
		 */
		try {
			Converter.getUInt32(new byte[]{0x10, 0x11}, 0);
			fail ("exception expedted, but not thrown.");
		} catch ( Exception e){}
	}
	@Test
	public void test_read_int_from_bytebuffer() {
		ByteBuffer buf = ByteBuffer.allocate(5);
		buf.put(new byte[]{0x08, 0x10, 0x33, (byte)0xA0, 0x14});
		
		buf.clear().position(1);
		assertEquals (346043152, Converter.getInt(buf));
	}
	@Test
	public void test_read_partial_bits(){
		assertEquals (0x0A, Converter.getBits(0x21AC, 4, 4));
		assertEquals (0x1A, Converter.getBits(0x21AC, 4, 8));
	}

}
