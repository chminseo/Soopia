package soopia.hwp.type.record;

import soopia.hwp.type.AbstractRecord;
import soopia.hwp.type.ColorRef;
import soopia.hwp.type.Dword;
import soopia.hwp.type.HwpByte;
import soopia.hwp.type.IStreamStruct;
import soopia.hwp.type.Int32;
import soopia.hwp.type.Int8;
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
	
	/**
	 * 가로 세로 모두 반복해서 이미지 채우기
	 * 
	 * @page p.20(표-26 이미지 채우기 유형)
	 */
	final public static int FILL_IMG_BOTH_ITR = 0x00;
	/**
	 * 가로로 반복해서 위쪽만 채우기
	 * 
	 * @page p.20(표-26 이미지 채우기 유형)
	 */
	final public static int FILL_IMG_TOP_HOR_ITR = 0x01;
	/**
	 * 가로로 반복해서 아래쪽만 채우기
	 * 
	 * @page p.20(표-26 이미지 채우기 유형)
	 */
	final public static int FILL_IMG_BOTTOM_HOR_ITR = 0x02;
	/**
	 * 세로로 반복해서 왼쪽만 채우기
	 * 
	 * @page p.20(표-26 이미지 채우기 유형)
	 */
	final public static int FILL_IMG_LEFT_VER_ITR = 0x03;
	/**
	 * 세로로 반복해서 오른쪽만 채우기
	 * 
	 * @page p.20(표-26 이미지 채우기 유형)
	 */
	final public static int FILL_IMG_RIGHT_VER_ITR = 0x04;
	/**
	 * 크기에 맞춰서 채우기 (배경보다 작은 이미지를 늘려서 가득 채움)
	 * 
	 * @page p.20(표-26 이미지 채우기 유형)
	 */
	final public static int FILL_IMG_ADJ = 0x05;
	/**
	 * 가운데로 채우기
	 * 
	 * @page p.20(표-26 이미지 채우기 유형)
	 */
	final public static int FILL_IMG_CENTER = 0x06;
	/**
	 * 가운데 위로
	 * 
	 * @page p.20(표-26 이미지 채우기 유형)
	 */
	final public static int FILL_IMG_DGR0 = 0x07;
	/**
	 * 가운데 아래로
	 * 
	 * @page p.20(표-26 이미지 채우기 유형)
	 */
	final public static int FILL_IMG_DGR180 = 0x08;
	/**
	 * 왼쪽 가운데로
	 * 
	 * @page p.20(표-26 이미지 채우기 유형)
	 */
	final public static int FILL_IMG_DGR270 = 0x09;
	/**
	 * 왼쪽 위로
	 * 
	 * @page p.20(표-26 이미지 채우기 유형)
	 */
	final public static int FILL_IMG_DGR315 = 0x0A;
	/**
	 * 왼쪽 아래로
	 * 
	 * @page p.20(표-26 이미지 채우기 유형)
	 */
	final public static int FILL_IMG_DGR225 = 0x0B;
	/**
	 * 오른쪽 가운데로
	 * 
	 * @page p.20(표-26 이미지 채우기 유형)
	 */
	final public static int FILL_IMG_DGR90 = 0x0C;
	/**
	 * 오른쪽 위로
	 * 
	 * @page p.20(표-26 이미지 채우기 유형)
	 */
	final public static int FILL_IMG_DGR45 = 0x0D;
	/**
	 * 오른쪽 아래로
	 * 
	 * @page p.20(표-26 이미지 채우기 유형)
	 */
	final public static int FILL_IMG_DGR135 = 0x0E;
	/**
	 * 이미지 채우지 않음 
	 * 
	 * @page p.20(표-26 이미지 채우기 유형)
	 */
	final public static int FILL_IMG_NONE = 0x0F;
	
	final public static int IMG_EFCT_REAL_PIC = 0x0;
	final public static int IMG_EFCT_GRAYSCALE = 0x1;
	final public static int IMG_EFCT_BLACK_WHITE = 0x2;
	final public static int IMG_EFCT_PATTERN8X8= 0x4;
	
	
	private UInt16 property ;
	private HwpBorder leftBorder;
	private HwpBorder rightBorder;
	private HwpBorder topBorder;
	private HwpBorder bottomBorder;
	/**
	 * 색 채우기, 그라데이션 채우기, 이미지 채우기 설정 상태
	 * @see #TYPE_FILL_COLOR
	 * @see #TYPE_FILL_GRDTN
	 * @see #TYPE_FILL_IMAGE
	 * 
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
	/**
	 * 본문 배경에 이미지를 채우는 16가지 방식을 나타내는 정보.(0x00~ 0x0F)
	 * 
	 * @see #FILL_IMG_BOTH_ITR
	 * @see #FILL_IMG_NONE
	 * @page p.20 (표-26 이미지 채우기 유형)
	 */
	private HwpByte imageFillType;
	/**
	 * 배경 이미지 정보
	 */
	private BackgroundImageRef bgImageRef;
	/**
	 * 그라데이션 정보
	 */
	private GradationRef gradationRef;
	
	public BorderFillRecord(RecordHeader header, IStreamStruct ds) {
		super(header, ds);
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
	public HwpByte getImageFillType(){
		return this.imageFillType;
	}
	public void setImageFillType(HwpByte imageFillType) {
		// TEST created and not tested method stub
		this.imageFillType = imageFillType;
	}
	public BackgroundImageRef getBackgroundImageRef(){
		return bgImageRef;
	}
	public void setBackgroundColorRef(BackgroundImageRef backgroundImageRef) {
		this.bgImageRef = backgroundImageRef;
	}
	public void setGradationRef(GradationRef grd) {
		this.gradationRef = grd;
	}
	public GradationRef getGradationRef() {
		return gradationRef;
	}
	/**
	 * 본문 테두리 정보
	 * 
	 * @author chmin
	 * @page p.18 ( 표-18)
	 */
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
		 * 채우기 무늬 종류
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
	/**
	 * 그라데이션 정보
	 * 
	 * @author chmin
	 * @page p.19(표-23)
	 */
	public static class GradationRef{
		Int8 gradationType;
		Int32 gradationTilt;
		Int32 gradationXPos;
		Int32 gradationYPos;
		Int32 gradationBlur;
		ColorRef [] colors;
		HwpByte centerBlur ;
		public GradationRef(Int8 grdntType, 
				Int32 grdntTilt, 
				Int32 grdntXPos,
				Int32 grdntYPos, 
				Int32 grdntBlur, ColorRef[] colors) {
			this.gradationType = grdntType;
			this.gradationTilt = grdntTilt;
			this.gradationXPos = grdntXPos;
			this.gradationYPos = grdntYPos;
			this.gradationBlur = grdntBlur;
			this.colors = colors;
		}
		
		public Int8 getGradationType() {
			return gradationType;
		}
		public void setGradationType(Int8 gradationType) {
			this.gradationType = gradationType;
		}
		public Int32 getGradationTilt() {
			return gradationTilt;
		}
		public void setGradationTilt(Int32 gradationTilt) {
			this.gradationTilt = gradationTilt;
		}
		public Int32 getGradationXPos() {
			return gradationXPos;
		}
		public void setGradationXPos(Int32 gradationXPos) {
			this.gradationXPos = gradationXPos;
		}
		public Int32 getGradationYPos() {
			return gradationYPos;
		}
		public void setGradationYPos(Int32 gradationYPos) {
			this.gradationYPos = gradationYPos;
		}
		public Int32 getGradationBlur() {
			return gradationBlur;
		}
		public void setGradationBlur(Int32 gradationBlur) {
			this.gradationBlur = gradationBlur;
		}
		public ColorRef[] getColors() {
			return colors;
		}
		public void setColors(ColorRef[] colors) {
			this.colors = colors;
		}

		public void setGradationCenterBlur(HwpByte centerBlur) {
			this.centerBlur = centerBlur;
		}

		public HwpByte getGradationCenterBlur() {
			return centerBlur;
		}
	}
	
	/**
	 * 이미지  채우기
	 * @author chmin
	 * @page p.20 (표-27 그림정보)
	 */
	public static class BackgroundImageRef {
		Int8 brightness;
		Int8 contrast;
		HwpByte imageEffect;
		UInt16 imageId;
		public BackgroundImageRef(Int8 brightness, Int8 contrast,
				HwpByte imageEffect, UInt16 imageId) {
			super();
			this.brightness = brightness;
			this.contrast = contrast;
			this.imageEffect = imageEffect;
			this.imageId = imageId;
		}
		public Integer getBrightness() {
			return brightness.getValue().intValue();
		}
		public Integer getContrast() {
			return contrast.getValue().intValue();
		}
		public Integer getImageId() {
			return imageId.getValue().intValue();
		}
	}

}
