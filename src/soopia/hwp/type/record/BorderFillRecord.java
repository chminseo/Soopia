package soopia.hwp.type.record;

import soopia.hwp.type.AbstractRecord;
import soopia.hwp.type.ColorRef;
import soopia.hwp.type.Dword;
import soopia.hwp.type.IStreamStruct;
import soopia.hwp.type.Int32;
import soopia.hwp.type.UInt16;
import soopia.hwp.type.UInt32;
import soopia.hwp.type.UInt8;
import soopia.hwp.type.stream.RecordHeader;
/**
 * 본 제품은 한글과컴퓨터의 한글 문서 파일(.hwp) 공개 문서를 참고하여 개발하였습니다.
 * 
 * 4.1.5 테두리/ 배경
 * 
 * @author chmin
 * @tagID HWPTAG_BORDER_FILL
 * @page p.18
 */
public class BorderFillRecord extends AbstractRecord {

	final public static int TYPE_FILL_COLOR = 0x1;
	final public static int TYPE_FILL_IMAGE = 0x2;
	final public static int TYPE_FILL_GRDTN = 0x4;
	
	private UInt16 property ;
	private HwpBorder leftBorder;
	private HwpBorder rightBorder;
	private HwpBorder topBorder;
	private HwpBorder bottomBorder;
	/**
	 * 색 채우기, 그라데이션 채우기, 이미지 채우기
	 */
	private UInt32 fillType ;
	/**
	 * 색 채우기 정보
	 */
	private BackgroundColorRef bgColorRef ;
	/**
	 * 그라데이션이 설정되었을때 추가 1비트를 나타내는 정보
	 * 
	 * (fillType & TYPE_FILL_GRDTN) > 0  이면 1, 아니면  0으로 설정
	 */
	private Dword size;
	/**
	 * TODO 데이터 레코드 맨 끝에 용도를 알 수 없는 1~2 바이트가  남는다.
	 */
	private byte [] dummy ;
	public BorderFillRecord(RecordHeader header, IStreamStruct ds, int offset) {
		super(header, ds, offset);
	}

	public UInt16 getBorderFillPropety(){
		return this.property;
	}
	public void setBorderFillProperty(UInt16 property) {
		this.property = property;
	}
	
	public HwpBorder getLeftBorder() {
		return leftBorder;
	}
	public void setLeftBorder(HwpBorder hwpBorder) {
		this.leftBorder = hwpBorder;
	}
	public HwpBorder getRightBorder() {
		return rightBorder;
	}
	public void setRightBorder(HwpBorder hwpBorder) {
		this.rightBorder = hwpBorder;	
	}
	public HwpBorder getTopBorder() {
		return topBorder;
	}
	public void setTopBorder(HwpBorder hwpBorder) {
		this.topBorder = hwpBorder;
	}
	public HwpBorder getBottomBorder() {
		return bottomBorder;
	}
	public void setBottomBorder(HwpBorder hwpBorder) {
		this.bottomBorder = hwpBorder;
	}
	public UInt32 getFillType() {
		return fillType;
	}
	public void setFillType(UInt32 fillType) {
		this.fillType = fillType;
	}
	
	public boolean isColorFilled(){
		return (fillType.getValue().intValue() 
				& BorderFillRecord.TYPE_FILL_COLOR) > 0 ;
	}
	public boolean isImageFilled(){
		return (fillType.getValue().intValue() 
				& BorderFillRecord.TYPE_FILL_IMAGE) > 0;
	}
	public boolean isGradationFilled(){
		return (fillType.getValue().intValue() 
				& BorderFillRecord.TYPE_FILL_GRDTN) > 0;
	}

	public BackgroundColorRef getBackgroundColorRef() {
		return bgColorRef;
	}
	public void setBackgroundColorRef (BackgroundColorRef bgColorRef){
		this.bgColorRef = bgColorRef;
	}
	public Dword getSizeInfo(){
		return size;
	}
	public void setSizeInfo(Dword size) {
		this.size = size;
	}
	public void setDummyBytes(byte[] dummy) {
		this.dummy = dummy;
	}

	public byte [] getDummyBytes() {
		return dummy;
	}
	
	public static class HwpBorder {
		/*
		 * 표20- 테두리선 종류 
		 */
		UInt8 borderType;
		/*
		 * 표21- 테두리선 굵기
		 */
		UInt8 borderSize;
		/*
		 * 테두리선 색상
		 */
		ColorRef color;
		public HwpBorder(UInt8 borderType, UInt8 borderSize, ColorRef color) {
			super();
			this.borderType = borderType;
			this.borderSize = borderSize;
			this.color = color;
		}
		
		public String toWebColorString() {
			return color.toWebColorString();
		}
	}
	
	public static class BackgroundColorRef {
		/**
		 * 배경색
		 * @page p.19 (표-23 채우기 정보)
		 * 
		 */
		ColorRef bgColor ;
		/**
		 * 무늬색
		 * @page p.19 (표-23 채우기 정보)
		 */
		ColorRef patternColor ;
		/**
		 * 채우기 문의 종류
		 * @page p.19(표-24 채우기 무늬 정보)
		 */
		Int32 patternType;
		public BackgroundColorRef(ColorRef bgColor, ColorRef patternColor,
				Int32 patternType) {
			super();
			this.bgColor = bgColor;
			this.patternColor = patternColor;
			this.patternType = patternType;
		}
		public ColorRef getBgColor() {
			return bgColor;
		}
		public ColorRef getPatternColor() {
			return patternColor;
		}
		public Int32 getPatternType() {
			return patternType;
		}
	}
	
	public static class GradationRef {
//		Int16 grdType ;
	}
	
}
