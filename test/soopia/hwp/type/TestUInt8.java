package soopia.hwp.type;

import static org.junit.Assert.*;

import java.nio.ByteBuffer;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import soopia.hwp.util.ByteArraySource;
import soopia.hwp.util.IByteSource;
/**
 * �� ��ǰ�� �ѱ۰���ǻ���� �ѱ� ���� ����(.hwp) ���� ������ �����Ͽ� �����Ͽ����ϴ�.
 * 
 * @author chmin
 *
 */
public class TestUInt8 {

	
	static IByteSource src ;
	@BeforeClass
	public static void setUpClass() throws Exception {
		src = new ByteArraySource(
				new byte[]{0x10, (byte)0xc0, 0x21, 0x00, (byte)0xab, 0x10, (byte)0xc0, 0x0, 0x0});
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		UInt8 ui = new UInt8(src.skip().consume(1));
		assertEquals (new Integer(0xc0), ui.getValue());
		assertEquals(1, ui.getLength());
	}
}
