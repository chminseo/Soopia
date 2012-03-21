package soopia.hwp.util;

import static org.junit.Assert.*;

import java.nio.ByteBuffer;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import soopia.hwp.util.Converter;
/**
 * �� ��ǰ�� �ѱ۰���ǻ���� �ѱ� ���� ����(.hwp) ���� ������ �����Ͽ� �����Ͽ����ϴ�.
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
			// offset(4)���� �о���� �� �ִ� ����Ʈ�� 1����Ʈ ��
			// �ʿ��� 2����Ʈ�� �ȵǹǷ� ���� �߻�.
			Converter.getUInt16(src, 4);
			fail ("exception expedted, but not thrown");
		} catch (Exception e) {}
		
	}
	@Test
	public void test_read_UINT32_long () {
		assertEquals (0xA0011001L, Converter.getUInt32(src, 1));
		/*
		 * �迭�� ����(2)�� �ּ��� �о�鿩���� ����(4)���� ���� ���
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
