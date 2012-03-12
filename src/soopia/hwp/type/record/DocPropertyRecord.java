package soopia.hwp.type.record;

import soopia.hwp.type.AbstractRecord;
import soopia.hwp.type.IStreamStruct;
import soopia.hwp.type.UInt16;
import soopia.hwp.type.UInt32;
import soopia.hwp.type.stream.RecordHeader;

/**
 * ���� ������ HWPTAG_DOCUMENT_PROPERTIES ���ڵ� ������
 * (ǥ 9 ����)
 * 
 * @author chmin
 * @page p.15
 */
public class DocPropertyRecord extends AbstractRecord {
	/**
	 * ���� ����
	 */
	private UInt16 f0_2_numOfSections;
	/**
	 * ������ ���� ��ȣ
	 */
	private UInt16 f1_2_startOfPageNum;
	/**
	 * ���� ���� ��ȣ
	 */
	private UInt16 f2_2_startNumOfFootNote;
	/**
	 * ���� ���� ��ȣ
	 */
	private UInt16 f3_2_startNumOfEndNote;
	/**
	 * �׸� ���� ��ȣ
	 */
	private UInt16 f4_2_startNumOfPicture;
	/**
	 * ǥ ���� ��ȣ
	 */
	private UInt16 f5_2_startNumOfTable;
	/**
	 * ���� ���� ��ȣ
	 */
	private UInt16 f6_2_startNumOfExpression;
	
	private UInt32 f7_4_listID;
	/**
	 * ���� ID
	 */
	private UInt32 f8_4_paraID;
	/**
	 * ���� ������ ���� ���� ��ġ
	 */
	private UInt32 f9_4_posInPara;
	
	
	
	/**
	 * 
	 * @param ds
	 * @param offset
	 */
	public DocPropertyRecord(RecordHeader header, IStreamStruct ds, int offset) {
		super(header, ds, offset);
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
