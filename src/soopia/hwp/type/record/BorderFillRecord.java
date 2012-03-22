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
import soopia.hwp.type.record.BorderFillRecord.BackgroundImageRef;
import soopia.hwp.type.record.BorderFillRecord.GradationRef;
import soopia.hwp.type.stream.RecordHeader;
/**
 * �� ��ǰ�� �ѱ۰���ǻ���� �ѱ� ���� ����(.hwp) ���� ������ �����Ͽ� �����Ͽ����ϴ�.
 * 
 * 4.1.5 �׵θ�/ ���
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
	 * ���� ���� ��� �ݺ��ؼ� �̹��� ä���
	 * 
	 * @page p.20(ǥ-26 �̹��� ä��� ����)
	 */
	final public static int FILL_IMG_BOTH_ITR = 0x00;
	/**
	 * ���η� �ݺ��ؼ� ���ʸ� ä���
	 * 
	 * @page p.20(ǥ-26 �̹��� ä��� ����)
	 */
	final public static int FILL_IMG_TOP_HOR_ITR = 0x01;
	/**
	 * ���η� �ݺ��ؼ� �Ʒ��ʸ� ä���
	 * 
	 * @page p.20(ǥ-26 �̹��� ä��� ����)
	 */
	final public static int FILL_IMG_BOTTOM_HOR_ITR = 0x02;
	/**
	 * ���η� �ݺ��ؼ� ���ʸ� ä���
	 * 
	 * @page p.20(ǥ-26 �̹��� ä��� ����)
	 */
	final public static int FILL_IMG_LEFT_VER_ITR = 0x03;
	/**
	 * ���η� �ݺ��ؼ� �����ʸ� ä���
	 * 
	 * @page p.20(ǥ-26 �̹��� ä��� ����)
	 */
	final public static int FILL_IMG_RIGHT_VER_ITR = 0x04;
	/**
	 * ũ�⿡ ���缭 ä��� (��溸�� ���� �̹����� �÷��� ���� ä��)
	 * 
	 * @page p.20(ǥ-26 �̹��� ä��� ����)
	 */
	final public static int FILL_IMG_ADJ = 0x05;
	/**
	 * ����� ä���
	 * 
	 * @page p.20(ǥ-26 �̹��� ä��� ����)
	 */
	final public static int FILL_IMG_CENTER = 0x06;
	/**
	 * ��� ����
	 * 
	 * @page p.20(ǥ-26 �̹��� ä��� ����)
	 */
	final public static int FILL_IMG_DGR0 = 0x07;
	/**
	 * ��� �Ʒ���
	 * 
	 * @page p.20(ǥ-26 �̹��� ä��� ����)
	 */
	final public static int FILL_IMG_DGR180 = 0x08;
	/**
	 * ���� �����
	 * 
	 * @page p.20(ǥ-26 �̹��� ä��� ����)
	 */
	final public static int FILL_IMG_DGR270 = 0x09;
	/**
	 * ���� ����
	 * 
	 * @page p.20(ǥ-26 �̹��� ä��� ����)
	 */
	final public static int FILL_IMG_DGR315 = 0x0A;
	/**
	 * ���� �Ʒ���
	 * 
	 * @page p.20(ǥ-26 �̹��� ä��� ����)
	 */
	final public static int FILL_IMG_DGR225 = 0x0B;
	/**
	 * ������ �����
	 * 
	 * @page p.20(ǥ-26 �̹��� ä��� ����)
	 */
	final public static int FILL_IMG_DGR90 = 0x0C;
	/**
	 * ������ ����
	 * 
	 * @page p.20(ǥ-26 �̹��� ä��� ����)
	 */
	final public static int FILL_IMG_DGR45 = 0x0D;
	/**
	 * ������ �Ʒ���
	 * 
	 * @page p.20(ǥ-26 �̹��� ä��� ����)
	 */
	final public static int FILL_IMG_DGR135 = 0x0E;
	/**
	 * �̹��� ä���� ���� 
	 * 
	 * @page p.20(ǥ-26 �̹��� ä��� ����)
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
	 * �� ä���, �׶��̼� ä���, �̹��� ä��� ���� ����
	 * @see #TYPE_FILL_COLOR
	 * @see #TYPE_FILL_GRDTN
	 * @see #TYPE_FILL_IMAGE
	 * 
	 */
	private UInt32 fillType ;
	/**
	 * �� ä��� ����
	 */
	private BackgroundColorRef bgColorRef ;
	/**
	 * �׶��̼��� �����Ǿ����� �߰� 1��Ʈ�� ��Ÿ���� ����
	 * 
	 * (fillType & TYPE_FILL_GRDTN) > 0  �̸� 1, �ƴϸ�  0���� ����
	 */
	private Dword size;
	/**
	 * TODO ������ ���ڵ� �� ���� �뵵�� �� �� ���� 1~2 ����Ʈ��  ���´�.
	 */
	private byte [] dummy ;
	/**
	 * ���� ��濡 �̹����� ä��� 16���� ����� ��Ÿ���� ����.(0x00~ 0x0F)
	 * 
	 * @see #FILL_IMG_BOTH_ITR
	 * @see #FILL_IMG_NONE
	 * @page p.20 (ǥ-26 �̹��� ä��� ����)
	 */
	private HwpByte imageFillType;
	/**
	 * ��� �̹��� ����
	 */
	private BackgroundImageRef bgImageRef;
	/**
	 * �׶��̼� ����
	 */
	private GradationRef gradationRef;
	
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
	 * ���� �׵θ� ����
	 * 
	 * @author chmin
	 * @page p.18 ( ǥ-18)
	 */
	public static class HwpBorder {
		/*
		 * ǥ20- �׵θ��� ���� 
		 */
		UInt8 borderType;
		/*
		 * ǥ21- �׵θ��� ����
		 */
		UInt8 borderSize;
		/*
		 * �׵θ��� ����
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
		 * ����
		 * @page p.19 (ǥ-23 ä��� ����)
		 * 
		 */
		ColorRef bgColor ;
		/**
		 * ���̻�
		 * @page p.19 (ǥ-23 ä��� ����)
		 */
		ColorRef patternColor ;
		/**
		 * ä��� ���� ����
		 * @page p.19(ǥ-24 ä��� ���� ����)
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
	 * �׶��̼� ����
	 * 
	 * @author chmin
	 * @page p.19(ǥ-23)
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
	 * �̹���  ä���
	 * @author chmin
	 * @page p.20 (ǥ-27 �׸�����)
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
