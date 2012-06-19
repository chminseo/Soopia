package soopia.hwp.codec;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import soopia.hwp.TestUtils;
import soopia.hwp.type.record.ParaShapeRecord;
import soopia.hwp.type.record.ParaShapeRecord.PropertyHandler;
import soopia.hwp.type.stream.RecordHeader;
import soopia.hwp.util.ByteArraySource;

public class TestParaShapeRecordDecorder {

	/*
	 * [����]              [ù��]
	 * ���� : 3PT          �鿩���� 15PT
	 * ������ : 2PT
	 * 
	 * [����]
	 * �ٰ��� : ���ڿ� ���� 300%
	 * ���� �� : 10PT
	 * ���� �Ʒ� : 5PT
	 * 
	 * [�� ���� ����]
	 * �ѱ� ���� : ����   �ּ� ���� 80%
	 * ���� ���� : �ܾ�
	 * 
	 * -Ȯ��-------------------------------
	 * [���� ����]
	 * ����
	 * 
	 * [��Ÿ]
	 * ��� üũ ����
	 * -�Ǽ���-----------------------------
	 * [������]
	 * ����
	 * ä�� ��� : ������
	 * 
	 * [�� ���]        [���� �� ���]
	 * NONE             NONE
	 * 
	 * [�ڵ���]
	 * (v)������ �ڵ� ��   ( )���� ������ �� �ڵ� ��
	 * 
	 * [�⺻��]
	 * ���� �⺻ �� ���� 40 PT
	 */
	static byte [] DATA = new byte[]{
		0x19, 0x04, 0x60, 0x03, // HEADER 
		(byte)0x80, 0x29, 0x00, 0x00, // �Ӽ�
		0x58, 0x02, 0x00, 0x00,       // left
		(byte)0x90, 0x01, 0x00, 0x00, // right
		(byte)0xB8, 0x0B, 0x00, 0x00, // indentation
		(byte)0xD0, 0x07, 0x00, 0x00, // top(���� ���� ��)
		(byte)0xE8, 0x03, 0x00, 0x00, // bottom(���� ���� �Ʒ�)
		0x2C, 0x01, 0x00, 0x00,       // �ٰ���(2007���� �������� ���)
		0x00, 0x00,                   // ID OF HWP_TAB_DEF
		0x00, 0x00,                   // ID OF HWP_NUMBERING OR HWP_BULLET 
		0x01, 0x00,                   // ID OF HWP_BORDER_FILL
		0x00, 0x00,                   // ���� �׵θ� ���� ���� 
		0x00, 0x00,                   // ���� �׵θ� ������ ����
		0x00, 0x00,                   // ���� �׵θ� ���� ����
		0x00, 0x00,                   // ���� �׵θ� �Ʒ��� ����
		0x00, 0x00,	0x00, 0x00,       // �Ӽ�2
		0x00, 0x00, 0x00, 0x00,       // �Ӽ�3
		0x2C, 0x01, 0x00, 0x00        // �ٰ���
	};
	
	static ParaShapeRecordDecoder decoder ;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		decoder = new ParaShapeRecordDecoder();
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test_para_shape_decoder() throws DecodingException {
		ByteArraySource data = new ByteArraySource(DATA);
		MockDocInfo docInfo = TestUtils.newMockDocInfo(DATA);
		RecordHeader header = TestUtils.newRecordHeader(data.mark());
		ParaShapeRecord record = new ParaShapeRecord(header, docInfo);
		
		record = decoder.decode(record, data.rollback(), docInfo.getHwpContext());
		
		// MEMO 1pt = 100hu ������ ���⼭�� 1pt = 200hu �� ����Ǵ� �� �ϴ�.
		//      ���������� INT �ڷ������� ǥ�õǾ��ִµ�, �̰Ͱ� ������ �ִ����� Ȯ���� �ʿ���.
		assertEquals( 3 * 200, record.getPaddingLeft().getValue().intValue());
		assertEquals( 2 * 200, record.getPaddingRight().getValue().intValue());
		assertEquals(10 * 200, record.getPaddingTop().getValue().intValue());
		assertEquals( 5 * 200, record.getPaddingBottom().getValue().intValue());
		
		assertEquals(15 * 200, record.getIndentation().getValue().intValue());
		
		assertEquals(300, record.getLineHeight().getValue().intValue());
		assertEquals(0, record.getTabDefId().getValue().intValue());
		assertEquals(0, record.getNumberingBulletId().getValue().intValue());
		assertEquals(1, record.getBorderFillId().getValue().intValue());
		
		assertEquals(0, record.getBorderMarginLeft().getValue().intValue());
		assertEquals(0, record.getBorderMarginRight().getValue().intValue());
		assertEquals(0, record.getBorderMarginTop().getValue().intValue());
		assertEquals(0, record.getBorderMarginBottom().getValue().intValue());
		
		// checking properties
		
		PropertyHandler ph = record.getPropertyHandler();
		assertEquals(0, ph.getLineHeightType());
		assertEquals(0, ph.getHorizontalAlignment());
	}
}
