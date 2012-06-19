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
	 * [여백]              [첫줄]
	 * 왼쪽 : 3PT          들여쓰기 15PT
	 * 오른쪽 : 2PT
	 * 
	 * [간격]
	 * 줄간격 : 글자에 따라 300%
	 * 문단 위 : 10PT
	 * 문단 아래 : 5PT
	 * 
	 * [줄 나눔 기준]
	 * 한글 단위 : 글자   최소 공백 80%
	 * 영어 단위 : 단어
	 * 
	 * -확장-------------------------------
	 * [문단 종류]
	 * 없음
	 * 
	 * [기타]
	 * 모두 체크 없음
	 * -탭설정-----------------------------
	 * [탭종류]
	 * 왼쪽
	 * 채울 모양 : 선없음
	 * 
	 * [탭 목록]        [지운 탭 목록]
	 * NONE             NONE
	 * 
	 * [자동탭]
	 * (v)내어쓰기용 자동 탭   ( )문단 오른쪽 끝 자동 탭
	 * 
	 * [기본탭]
	 * 구역 기본 탭 간격 40 PT
	 */
	static byte [] DATA = new byte[]{
		0x19, 0x04, 0x60, 0x03, // HEADER 
		(byte)0x80, 0x29, 0x00, 0x00, // 속성
		0x58, 0x02, 0x00, 0x00,       // left
		(byte)0x90, 0x01, 0x00, 0x00, // right
		(byte)0xB8, 0x0B, 0x00, 0x00, // indentation
		(byte)0xD0, 0x07, 0x00, 0x00, // top(문단 간격 위)
		(byte)0xE8, 0x03, 0x00, 0x00, // bottom(문단 간격 아래)
		0x2C, 0x01, 0x00, 0x00,       // 줄간격(2007이하 버전에서 사용)
		0x00, 0x00,                   // ID OF HWP_TAB_DEF
		0x00, 0x00,                   // ID OF HWP_NUMBERING OR HWP_BULLET 
		0x01, 0x00,                   // ID OF HWP_BORDER_FILL
		0x00, 0x00,                   // 문단 테두리 왼쪽 간격 
		0x00, 0x00,                   // 문단 테두리 오른쪽 간격
		0x00, 0x00,                   // 문단 테두리 위쪽 간격
		0x00, 0x00,                   // 문단 테두리 아래쪽 간격
		0x00, 0x00,	0x00, 0x00,       // 속성2
		0x00, 0x00, 0x00, 0x00,       // 속성3
		0x2C, 0x01, 0x00, 0x00        // 줄간격
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
		
		// MEMO 1pt = 100hu 이지만 여기서는 1pt = 200hu 로 적용되는 듯 하다.
		//      문서에서는 INT 자료형으로 표시되어있는데, 이것과 관련이 있는지는 확인이 필요함.
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
