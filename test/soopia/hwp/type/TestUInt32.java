package soopia.hwp.type;

import static org.junit.Assert.*;

import java.nio.ByteBuffer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import soopia.hwp.type.UInt32;
import soopia.hwp.util.ByteArraySource;
import soopia.hwp.util.IByteSource;
/**
 * �� ��ǰ�� �ѱ۰���ǻ���� �ѱ� ���� ����(.hwp) ���� ������ �����Ͽ� �����Ͽ����ϴ�.
 * 
 * @author chmin
 *
 */
public class TestUInt32 {

	IByteSource src ;
	@Before
	public void setUp() throws Exception {
		src = new ByteArraySource(new byte[]{
				(byte)0x9a, (byte)0xab, 0x21, 0x00, 0x1f,
				0x5d, 0x2a, 0x09, 0x00, 0x11});
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		UInt32 int32 = new UInt32(src.skip().consume(4));//new UInt32(src, 1);
		assertArrayEquals (
				new byte[]{(byte)0xab, 0x21, 0x00, 0x1f},
				int32.getBytes());
		assertEquals (4, int32.getLength());
		
		assertEquals (new Long(0x1F0021AB), int32.getValue());
	}
}
