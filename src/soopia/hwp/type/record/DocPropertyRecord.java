package soopia.hwp.type.record;

import soopia.hwp.type.AbstractRecord;
import soopia.hwp.type.IStreamStruct;
import soopia.hwp.type.UInt16;
import soopia.hwp.type.UInt32;
import soopia.hwp.type.stream.RecordHeader;

/**
 * 본 제품은 한글과컴퓨터의 한글 문서 파일(.hwp) 공개 문서를 참고하여 개발하였습니다.
 * 
 * 문서 정보의 HWPTAG_DOCUMENT_PROPERTIES 레코드 데이터
 * (표 9 참조)
 * 
 * @author chmin
 * @tagID HWPTAG_DOCUMENT_PROPERITES
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
	public DocPropertyRecord(RecordHeader header, IStreamStruct ds) {
		super(header, ds);
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
	
	
	public void setNumberOfSection(UInt16 numOfSection) {
		this.f0_2_numOfSections = numOfSection;
	}
	public void setStartOfPageNum(UInt16 uInt16) {
		this.f1_2_startOfPageNum = uInt16;
	}
	public void setStartNumOfFootNote (UInt16 numOfFootNote) {
		this.f2_2_startNumOfFootNote = numOfFootNote;
	}
	public void setStartNumOfEndNote (UInt16 numOfEndNote){
		this.f3_2_startNumOfEndNote = numOfEndNote;
	}
	public void setStartNumOfPicture (UInt16 numOfPicture){
		this.f4_2_startNumOfPicture = numOfPicture;
	}
	public void setStartNumOfTable (UInt16 numOfTable ){
		this.f5_2_startNumOfTable = numOfTable;
	}
	public void setStartNumOfExpression (UInt16 numOfExpression ){
		this.f6_2_startNumOfExpression = numOfExpression;
	}
	public void setListID(UInt32 listID ){
		this.f7_4_listID = listID;
	}
	public void setParaID (UInt32 paraID ){
		this.f8_4_paraID = paraID;
	}
	public void setCursorPosInPara(UInt32 cusorPosInPara ){
		this.f9_4_posInPara = cusorPosInPara;
	}
	

}
