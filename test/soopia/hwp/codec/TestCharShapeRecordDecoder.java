/**
 * �� ��ǰ�� �ѱ۰���ǻ���� �ѱ� ���� ����(.hwp) ���� ������ �����Ͽ� �����Ͽ����ϴ�.
 */
package soopia.hwp.codec;

import static org.junit.Assert.*;

import java.nio.ByteBuffer;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import soopia.hwp.TestUtils;
import soopia.hwp.type.record.CharShapeRecord;

public class TestCharShapeRecordDecoder {

	static byte [] data = new byte[]{
		0x15, 0x04, (byte)0xA0, 0x04, //header
		/*��        ��*/0x02, 0x00, 0x02, 0x00, 0x01, 0x00, 0x01, 0x00, 0x01, 0x00, 0x01, 0x00, 0x01, 0x00, 
		/*��        ��*/0x64, (byte)0xC8, 0x64, 0x64, 0x64, 0x64, 0x64, 
		/*��        ��*/0x00, 0x14, 0x00, 0x00, 0x00, 0x00, 0x00, // 20%
		/*���ũ��*/(byte)0x96, (byte)0x96, (byte)0x96, (byte)0x96, (byte)0x96, (byte)0x96, (byte)0x96, 
		/*������ġ*/0x00, 0x10, 0x00, 0x00, 0x00, 0x00, 0x00, // 16%
		
		(byte)0xE8, 0x03, 0x00, 0x00,// ����ũ��( HwpUnit ) 
		0x00, 0x08, 0x00, 0x00,  // �Ӽ�(ǥ-30 ����)
		0x10, 0x0A, // �׸��� X, Y ����
		0x00, 0x00, 0x00, 0x00, // ���ڻ�
		0x00, 0x00, 0x00, 0x00, // ���ٻ�
		(byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, // ������ 
		0x34, 0x56,	0x78, 0x00, // �׸��ڻ�
		0x02, 0x00, // �׵θ�/��� �����ͷ��ڵ� Id
		0x00, 0x00, 0x00, 0x00 // ��Ҽ���
	};
	static MockDocInfo docInfo ;
	private static CharShapeRecordDecoder decoder;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ByteBuffer buf = ByteBuffer.wrap(data);
		docInfo = TestUtils.newMockDocInfo(buf);
		decoder = new CharShapeRecordDecoder();
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test_decoding_CharShapeRecord() throws DecodingException {
		CharShapeRecord record = new CharShapeRecord(
				TestUtils.newRecordHeader(docInfo.getBuffer(), 0)
				, docInfo, 0);
		record = decoder.decode(record, record.getBuffer(), docInfo.getHwpContext());
		
	}

}