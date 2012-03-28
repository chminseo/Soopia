/**
 * �� ��ǰ�� �ѱ۰���ǻ���� �ѱ� ���� ����(.hwp) ���� ������ �����Ͽ� �����Ͽ����ϴ�.
 */
package soopia.hwp.type.record;

import soopia.hwp.type.AbstractRecord;
import soopia.hwp.type.ColorRef;
import soopia.hwp.type.HwpUnit;
import soopia.hwp.type.IStreamStruct;
import soopia.hwp.type.Int8;
import soopia.hwp.type.UInt16;
import soopia.hwp.type.UInt32;
import soopia.hwp.type.UInt8;
import soopia.hwp.type.Word;
import soopia.hwp.type.stream.RecordHeader;
import soopia.hwp.util.Converter;

/**
 * 4.1.6 ���ڸ��
 * 
 * @author chmin
 * @tagID HWPTAG_CHAR_SHAPE
 * @page p.14
 *
 */
public class CharShapeRecord extends AbstractRecord {

	public static final int PROP_BOLD = 0x02;
	public static final int PROP_ITALIC = 0x01;
	/**
	 * ���� ���� - ����
	 * 
	 * @see #getCharLinePos()
	 */
	public static final int PROP_CHARLINE_POS_NONE = 0x00;
	/**
	 * ���� ���� - ���� �Ʒ�
	 * 
	 * @see #getCharLinePos()
	 */
	public static final int PROP_CHARLINE_POS_OVER = 0x01;
	/**
	 * ���� ���� - ���� ��
	 * 
	 * @see #getCharLinePos()
	 */
	public static final int PROP_CHARLINE_POS_UNDER = 0x03;
	/**
	 * ���� �ܰ��� ���� - ����
	 */
	public static final int PROP_OUTLINE_NONE = 0x0;
	/**
	 * ���� �ܰ��� ���� - �Ǽ�
	 */
	public static final int PROP_OUTLINE_SOLID = 0x1;
	/**
	 * ���� �ܰ��� ���� - ����
	 */
	public static final int PROP_OUTLINE_DOTTED = 0x2;
	/**
	 * ���� �ܰ��� ���� - ���� �Ǽ�
	 */
	public static final int PROP_OUTLINE_THICK =0x3;
	/**
	 * ���� �ܰ��� ���� - �ļ�(�� ����)
	 */
	public static final int PROP_OUTLINE_DASHED =0x4;
	/**
	 * ���� �ܰ��� ���� - 1���⼱
	 */
	public static final int PROP_OUTLINE_ONE_DOT_LINE =0x5;
	/**
	 * ���� �ܰ��� ���� -2�� �⼱
	 */
	public static final int PROP_OUTLINE_TWO_DOT_LINE =0x6;
	/**
	 * �׸��� ���� - ����
	 */
	public static final int PROP_SHADOW_NONE = 0x0;
	/**
	 * �׸��� ���� - �񿬼�
	 */
	public static final int PROP_SHADOW_INTERMIT = 0x1;
	/**
	 * �׸��� ���� - ����
	 */
	public static final int PROP_SHADOW_CONTINUE = 0x2;
	/**
	 * ������ ���� - ���� 
	 */
	public static final int PROP_DCRITIC_NONE = 0x0;
	/**
	 * ������ ���� - ���� ���׶�� ������
	 */
	public static final int PROP_DCRITIC_CIRCLET_FILLED = 0x0;
	/**
	 * ������ ���� - ���̺� ���׶�� ������
	 */
	public static final int PROP_DCRITIC_CIRCLET_EMPTY = 0x0;
	/**
	 * ������ ���� - ������ ^
	 */
	public static final int PROP_DCRITIC_POINT = 0x0;
	/**
	 * ������ ���� - ������
	 */
	public static final int PROP_DCRITIC_TILDE = 0x0;
	/**
	 * ������ ���� - ���� ���ʿ� 1��
	 */
	public static final int PROP_DCRITIC_DOT1 = 0x0;
	/**
	 * ������ ���� - ���� ���ʿ� 2��
	 */
	public static final int PROP_DCRITIC_DOT2 = 0x0;
	private Word [] f0_fontIds ;
	private UInt8 [] f1_charWidthRatio ;
	private Int8 [] f2_charGapRatio ;
	private UInt8 [] f3_charSizeRatio ;
	private Int8 [] f4_charPosRatio;
	
	private HwpUnit f5_baseCharSize ;
	private UInt32 f6_fontProperty;
	private Int8 f7_shadowPosXRatio ;
	private Int8 f8_shadowPosYRation;
	
	private ColorRef f9_fontColor ;
	private ColorRef f10_underlineColor;
	private ColorRef f11_backgroundColor ;
	private ColorRef f12_shadowColor ;
	private UInt16 f13_borderFillId;
	private ColorRef f14_strokeColor ;
	/**
	 * @param header
	 * @param ds
	 * @param offset
	 */
	public CharShapeRecord(RecordHeader header, IStreamStruct ds) {
		super(header, ds);
		// TODO Auto-generated constructor stub
	}
	public Word[] getFontIds() {
		return f0_fontIds;
	}
	public void setFontIds(Word[] fontIds) {
		this.f0_fontIds = fontIds;
	}
	public UInt8[] getCharWidthRatio() {
		return f1_charWidthRatio;
	}
	public void setCharWidthRatio(UInt8[] charWidthRatio) {
		this.f1_charWidthRatio = charWidthRatio;
	}
	public Int8[] getCharGapRatio() {
		return f2_charGapRatio;
	}
	public void setCharGapRatio(Int8[] charGapRatio) {
		this.f2_charGapRatio = charGapRatio;
	}
	public UInt8[] getCharSizeRatio() {
		return f3_charSizeRatio;
	}
	public void setCharSizeRatio(UInt8[] charSizeRatio) {
		this.f3_charSizeRatio = charSizeRatio;
	}
	public Int8[] getCharPosRatio() {
		return f4_charPosRatio;
	}
	public void setCharPosRatio(Int8[] charPosRatio) {
		this.f4_charPosRatio = charPosRatio;
	}
	public HwpUnit getBaseCharSize() {
		return f5_baseCharSize;
	}
	public void setBaseCharSize(HwpUnit baseCharSize) {
		this.f5_baseCharSize = baseCharSize;
	}
	public UInt32 getFontProperty() {
		return f6_fontProperty;
	}
	public void setFontProperty(UInt32 fontProperty) {
		this.f6_fontProperty = fontProperty;
	}
	public Int8 getShadowPosXRatio() {
		return f7_shadowPosXRatio;
	}
	public void setShadowPosXRatio(Int8 shadowPosXRatio) {
		this.f7_shadowPosXRatio = shadowPosXRatio;
	}
	public Int8 getShadowPosYRation() {
		return f8_shadowPosYRation;
	}
	public void setShadowPosYRation(Int8 shadowPosYRation) {
		this.f8_shadowPosYRation = shadowPosYRation;
	}
	public ColorRef getFontColor() {
		return f9_fontColor;
	}
	public void setFontColor(ColorRef fontColor) {
		this.f9_fontColor = fontColor;
	}
	public ColorRef getUnderlineColor() {
		return f10_underlineColor;
	}
	public void setUnderlineColor(ColorRef underlineColor) {
		this.f10_underlineColor = underlineColor;
	}
	public ColorRef getBackgroundColor() {
		return f11_backgroundColor;
	}
	public void setBackgroundColor(ColorRef backgroundColor) {
		this.f11_backgroundColor = backgroundColor;
	}
	public ColorRef getShadowColor() {
		return f12_shadowColor;
	}
	public void setShadowColor(ColorRef shadowColor) {
		this.f12_shadowColor = shadowColor;
	}
	public UInt16 getBorderFillId() {
		return f13_borderFillId;
	}
	public void setBorderFillId(UInt16 borderFillId) {
		this.f13_borderFillId = borderFillId;
	}
	public ColorRef getStrokeColor() {
		return f14_strokeColor;
	}
	public void setStrokeColor(ColorRef strokeColor) {
		this.f14_strokeColor = strokeColor;
	}
	
	public boolean isItalic() {
		return (this.f6_fontProperty.getValue().intValue() & PROP_ITALIC) != 0 ;
	}
	public boolean isBold() {
		return (this.f6_fontProperty.getValue().intValue() & PROP_BOLD) != 0 ;
	}
	/**
	 * ���� ����(����, ���� ��, ���� �Ʒ�)<br/>
	 * - ���� {@link #PROP_CHARLINE_POS_NONE}<br/>
	 * - ���� �� {@link #PROP_CHARLINE_POS_OVER}<br/>
	 * - ���� �Ʒ� {@link #PROP_CHARLINE_POS_UNDER}<br/>
	 * 
	 * @return
	 */
	public int getCharLinePos(){
		return Converter.getBits(this.f6_fontProperty.getValue().intValue(), 2, 2);
	}
	/**
	 * ������ ��� (ǥ-20 ����)
	 * 
	 * @return
	 */
	public int getCharLineStyle(){
		return Converter.getBits(this.f6_fontProperty.getValue().intValue(), 4, 4);
	}
	/**
	 * ��Ʈ �ܰ����� ����(��Ʈ ��ü�� �׵θ����� �ǹ���) <br/>
	 * - {@link #PROP_OUTLINE_NONE}<br/>
	 * - {@link #PROP_OUTLINE_SOLID}<br/>
	 * - {@link #PROP_OUTLINE_DOTTED}<br/>
	 * - {@link #PROP_OUTLINE_THICK}<br/>
	 * - {@link #PROP_OUTLINE_DASHED}<br/>
	 * - {@link #PROP_OUTLINE_ONE_DOT_LINE}<br/>
	 * - {@link #PROP_OUTLINE_TWO_DOT_LINE}<br/>
	 * @return one of above
	 */
	public int getFontOutline(){
		return Converter.getBits(this.f6_fontProperty.getValue().intValue(), 8, 3);
	}
	/**
	 * �׸��� ���� (ǥ-30)
	 * @return
	 */
	public int getShadowType(){
		/*
		 * �׸��� ����
		 * 0 : ����
		 * 1 : �񿬼�
		 * 2 : ����
		 */
		return Converter.getBits(this.f6_fontProperty.getValue().intValue(), 11, 2);
	}
	/**
	 * �簢 ���� ����
	 * @return
	 */
	public boolean isEmboss(){
		return Converter.getBits(this.f6_fontProperty.getValue().intValue(), 13, 1) != 0 ;
	}
	/**
	 * ���� ���� ����
	 * @return
	 */
	public boolean isIntaglio(){
		return Converter.getBits(this.f6_fontProperty.getValue().intValue(), 14, 1) != 0 ;
	}
	/**
	 * ���ڰ� ��÷������ ��Ÿ��
	 * @return
	 */
	public boolean isSuperScript(){
		return Converter.getBits(this.f6_fontProperty.getValue().intValue(), 15, 1) != 0 ;
	}
	/**
	 * ���ڰ� �Ʒ�÷������ ��Ÿ��
	 * @return
	 */
	public boolean isSubScript(){
		return Converter.getBits(this.f6_fontProperty.getValue().intValue(), 16, 1) != 0 ;
	}
	/**
	 * ��Ҽ� ��뿩�� (����, ����)
	 * 
	 * @return
	 */
	public int isStrokeLineEnabled(){
		return Converter.getBits(this.f6_fontProperty.getValue().intValue(), 18, 3);
	}
	/**
	 * <p>
	 * ���� �� ������(������)�� ����
	 * </p>
	 * 0 ������ ���� {@link #PROP_DCRITIC_NONE}<br/>
	 * 1 ���� ���׶�� (������){@link #PROP_DCRITIC_CIRCLET_FILLED}<br/>
	 * 2 �� �� ���׶��  (������){@link #PROP_DCRITIC_CIRCLET_EMPTY}<br/>
	 * 3 ����� (������){@link #PROP_DCRITIC_POINT}<br/>
	 * 4 ������(������){@link #PROP_DCRITIC_TILDE}<br/>
	 * 5 �� �ϳ�(���� ����){@link #PROP_DCRITIC_DOT1}<br/>
	 * 6 �� �ΰ�(���� ����){@link #PROP_DCRITIC_DOT2}<br/>
	 * 
	 * @return
	 */
	public int getDiacriticalMark (){
		return Converter.getBits(this.f6_fontProperty.getValue().intValue(), 21, 4);
	}
	/**
	 * �۲ÿ� ��︮�� ��ĭ ��� ����
	 * 
	 * @return
	 */
	public boolean isSuitableBlankUse() {
		return Converter.getBits(this.f6_fontProperty.getValue().intValue(), 25, 1) != 0 ;
	}
	/**
	 * ��Ҽ� ��� ( ǥ-20 ����)
	 * 
	 * @return
	 */
	public int getStrokeLineStyle(){
		return Converter.getBits(this.f6_fontProperty.getValue().intValue(), 26, 4);
	}
	/**
	 * <a href="http://www.terms.co.kr/kerning.htm">Ŀ��</a> ���� ����
	 * 
	 * @return
	 */
	public boolean isKerningEnabled(){
		return Converter.getBits(this.f6_fontProperty.getValue().intValue(), 30, 1) != 0 ;		
	}
}
