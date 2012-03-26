package soopia.hwp.type.record;

import soopia.hwp.type.AbstractRecord;
import soopia.hwp.type.IStreamStruct;
import soopia.hwp.type.UInt32;
import soopia.hwp.type.stream.RecordHeader;
/**
 * 본 제품은 한글과컴퓨터의 한글 문서 파일(.hwp) 공개 문서를 참고하여 개발하였습니다.
 * 
 * 표 11에는 32바이트로 나오지만 실제 문서를 열어보면 64바이트로 되어있다.
 * (UInt16이 아니라 UInt32)
 * 
 * @author chmin
 * @tagID HWPTAG_ID_MAPPINGS
 * @page p.16 표 11
 * @version 5.0.3.0 
 */
public class IDMappingsRecord extends AbstractRecord {
	private UInt32 f0_numOfBinData ;	/* 바이너리 데이터  */
	private UInt32 f1_numOfKorFont ;	/* 한글 글꼴 */
	private UInt32 f2_numOfEngFont ;	/* 영어 글꼴 */
	private UInt32 f3_numOfChnFont ;	/* 한자 글꼴 */
	private UInt32 f4_numOfJpnFont ;	/* 일어 글꼴 */
	private UInt32 f5_numOfEtcFont ;	/* 기타 글꼴 */
	private UInt32 f6_numOfSymbolFont;	/* 기호 글꼴 */
	private UInt32 f7_numOfUserFont ;	/* 사용자 글꼴 */
	private UInt32 f8_numOfBorder ; 	/* 테두리/배경 */
	private UInt32 f9_numOfFontShape;	/* 글자 모양 */
	private UInt32 f10_numOfTab;		/* 탭 정의    */
	private UInt32 f11_numOfParaNum;	/* 문단번호 */
	private UInt32 f12_numOfParaSymbol;	/* 글머리표 */
	private UInt32 f13_numOfParaShape;	/* 문단모양  */
	private UInt32 f14_numOfStyle;		/* 스타일     */
	private UInt32 f15_numOfMemoShape;	/* 메모 모양 */
	
	public IDMappingsRecord(RecordHeader header, IStreamStruct ds) {
		super(header, ds);
	}

	public UInt32 getNumOfBinData() {
		return f0_numOfBinData;
	}

	public void setNumOfBinData(UInt32 numOfBinData) {
		this.f0_numOfBinData = numOfBinData;
	}

	public UInt32 getNumOfKorFont() {
		return f1_numOfKorFont;
	}

	public void setNumOfKorFont(UInt32 numOfKorFont) {
		this.f1_numOfKorFont = numOfKorFont;
	}

	public UInt32 getNumOfEngFont() {
		return f2_numOfEngFont;
	}

	public void setNumOfEngFont(UInt32 numOfEngFont) {
		this.f2_numOfEngFont = numOfEngFont;
	}

	public UInt32 getNumOfChnFont() {
		return f3_numOfChnFont;
	}

	public void setNumOfChnFont(UInt32 numOfChnFont) {
		this.f3_numOfChnFont = numOfChnFont;
	}

	public UInt32 getNumOfJpnFont() {
		return f4_numOfJpnFont;
	}

	public void setNumOfJpnFont(UInt32 numOfJpnFont) {
		this.f4_numOfJpnFont = numOfJpnFont;
	}

	public UInt32 getNumOfEtcFont() {
		return f5_numOfEtcFont;
	}

	public void setNumOfEtcFont(UInt32 numOfEtcFont) {
		this.f5_numOfEtcFont = numOfEtcFont;
	}

	public UInt32 getNumOfSymbolFont() {
		return f6_numOfSymbolFont;
	}

	public void setNumOfSymbolFont(UInt32 numOfSymbolFont) {
		this.f6_numOfSymbolFont = numOfSymbolFont;
	}

	public UInt32 getNumOfUserFont() {
		return f7_numOfUserFont;
	}

	public void setNumOfUserFont(UInt32 numOfUserFont) {
		this.f7_numOfUserFont = numOfUserFont;
	}

	public UInt32 getNumOfBorder() {
		return f8_numOfBorder;
	}

	public void setNumOfBorder(UInt32 numOfBorder) {
		this.f8_numOfBorder = numOfBorder;
	}

	public UInt32 getNumOfFontShape() {
		return f9_numOfFontShape;
	}

	public void setNumOfFontShape(UInt32 numOfFontShape) {
		this.f9_numOfFontShape = numOfFontShape;
	}

	public UInt32 getNumOfTab() {
		return f10_numOfTab;
	}

	public void setNumOfTab(UInt32 numOfTab) {
		this.f10_numOfTab = numOfTab;
	}

	public UInt32 getNumOfParaNum() {
		return f11_numOfParaNum;
	}

	public void setNumOfParaNum(UInt32 numOfParaNum) {
		this.f11_numOfParaNum = numOfParaNum;
	}

	public UInt32 getNumOfParaSymbol() {
		return f12_numOfParaSymbol;
	}

	public void setNumOfParaSymbol(UInt32 numOfParaSymbol) {
		this.f12_numOfParaSymbol = numOfParaSymbol;
	}

	public UInt32 getNumOfParaShape() {
		return f13_numOfParaShape;
	}

	public void setNumOfParaShape(UInt32 numOfParaShape) {
		this.f13_numOfParaShape = numOfParaShape;
	}

	public UInt32 getNumOfStyle() {
		return f14_numOfStyle;
	}

	public void setNumOfStyle(UInt32 numOfStyle) {
		this.f14_numOfStyle = numOfStyle;
	}

	public UInt32 getNumOfMemoShape() {
		return f15_numOfMemoShape;
	}

	public void setNumOfMemoShape(UInt32 numOfMemoShape) {
		this.f15_numOfMemoShape = numOfMemoShape;
	}
}
