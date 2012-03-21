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
	
	private UInt16 property ;
	private HwpBorder leftBorder;
	private HwpBorder rightBorder;
	private HwpBorder topBorder;
	private HwpBorder bottomBorder;
	/**
	 * �� ä���, �׶��̼� ä���, �̹��� ä���
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
	
	public static class GradationRef {
//		Int16 grdType ;
	}
	
}
