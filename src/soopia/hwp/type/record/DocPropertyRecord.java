package soopia.hwp.type.record;

import java.nio.ByteBuffer;

import soopia.hwp.type.AbstractRecord;
import soopia.hwp.type.IDataType;
import soopia.hwp.type.IRecordStructure;
import soopia.hwp.type.IStreamStruct;
import soopia.hwp.type.UInt16;
import soopia.hwp.type.UInt32;
import soopia.hwp.util.Converter;
/**
 * 문서 정보의 HWPTAG_DOCUMENT_PROPERTIES 레코드 데이터
 * (표 9 참조)
 * 
 * @author chmin
 * @page p.15
 */
public class DocPropertyRecord extends AbstractRecord {
	/**
	 * 구역 갯수
	 */
	private UInt16 f0_2_numOfSections;
	/**
	 * 페이지 시작 번호
	 */
	private UInt16 f1_2_startOfPageNum;
	/**
	 * 각주 시작 번호
	 */
	private UInt16 f2_2_startNumOfFootNote;
	/**
	 * 미주 시작 번호
	 */
	private UInt16 f3_2_startNumOfEndNote;
	/**
	 * 그림 시작 번호
	 */
	private UInt16 f4_2_startNumOfPicture;
	/**
	 * 표 시작 번호
	 */
	private UInt16 f5_2_startNumOfTable;
	/**
	 * 수식 시작 번호
	 */
	private UInt16 f6_2_startNumOfExpression;
	
	private UInt32 f7_4_listID;
	/**
	 * 문단 ID
	 */
	private UInt32 f8_4_paraID;
	/**
	 * 문단 내에서 글자 단위 위치
	 */
	private UInt32 f9_4_posInPara;
	
	
	
	/**
	 * 
	 * @param ds
	 * @param offset
	 */
	public DocPropertyRecord(IStreamStruct ds, int offset) {
		super(ds, offset);
		
		ByteBuffer buf = ds.getBuffer();
		
		this.f0_2_numOfSections = new UInt16(buf, 0);
		this.f1_2_startOfPageNum = new UInt16(buf, 2);
		this.f2_2_startNumOfFootNote = new UInt16(buf, 4);
		this.f3_2_startNumOfEndNote = new UInt16(buf, 6);
		this.f4_2_startNumOfPicture = new UInt16(buf, 8);
		this.f5_2_startNumOfTable = new UInt16(buf,  10);
		this.f6_2_startNumOfExpression = new UInt16(buf, 12);
		this.f7_4_listID = new UInt32(buf, 14);
		this.f8_4_paraID = new UInt32(buf, 18);
		this.f9_4_posInPara = new UInt32(buf, 22);
	}



	public UInt16 getNumOfSections() {
		return f0_2_numOfSections;
	}
	public UInt16 getStartOfPageNum() {
		return f1_2_startOfPageNum;
	}
	public UInt16 getStartNumOfFootNote() {
		return f2_2_startNumOfFootNote;
	}
	public UInt16 getStartNumOfEndNote() {
		return f3_2_startNumOfEndNote;
	}
	public UInt16 getStartNumOfPicture() {
		return f4_2_startNumOfPicture;
	}
	public UInt16 getStartNumOfTable() {
		return f5_2_startNumOfTable;
	}
	public UInt16 getStartNumOfExpression() {
		return f6_2_startNumOfExpression;
	}
	public void setFStartNumOfExpression(UInt16 f6_2_startNumOfExpression) {
		this.f6_2_startNumOfExpression = f6_2_startNumOfExpression;
	}
	public UInt32 getListID() {
		return f7_4_listID;
	}
	public UInt32 getParaID() {
		return f8_4_paraID;
	}
	public UInt32 getCursorPosInPara() {
		return f9_4_posInPara;
	}
}
