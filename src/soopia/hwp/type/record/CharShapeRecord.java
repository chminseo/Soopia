/**
 * 본 제품은 한글과컴퓨터의 한글 문서 파일(.hwp) 공개 문서를 참고하여 개발하였습니다.
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
 * 4.1.6 글자모양
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
	 * 밑줄 종류 - 없음
	 * 
	 * @see #getCharLinePos()
	 */
	public static final int PROP_CHARLINE_POS_NONE = 0x00;
	/**
	 * 밑줄 종류 - 글자 아래
	 * 
	 * @see #getCharLinePos()
	 */
	public static final int PROP_CHARLINE_POS_OVER = 0x01;
	/**
	 * 밑줄 종류 - 글자 위
	 * 
	 * @see #getCharLinePos()
	 */
	public static final int PROP_CHARLINE_POS_UNDER = 0x03;
	/**
	 * 글자 외곽선 종류 - 없음
	 */
	public static final int PROP_OUTLINE_NONE = 0x0;
	/**
	 * 글자 외곽선 종류 - 실선
	 */
	public static final int PROP_OUTLINE_SOLID = 0x1;
	/**
	 * 글자 외곽선 종류 - 점선
	 */
	public static final int PROP_OUTLINE_DOTTED = 0x2;
	/**
	 * 글자 외곽선 종류 - 굵은 실선
	 */
	public static final int PROP_OUTLINE_THICK =0x3;
	/**
	 * 글자 외곽선 종류 - 파선(긴 점선)
	 */
	public static final int PROP_OUTLINE_DASHED =0x4;
	/**
	 * 글자 외곽선 종류 - 1점쇄선
	 */
	public static final int PROP_OUTLINE_ONE_DOT_LINE =0x5;
	/**
	 * 글자 외곽선 종류 -2점 쇄선
	 */
	public static final int PROP_OUTLINE_TWO_DOT_LINE =0x6;
	/**
	 * 그림자 종류 - 없음
	 */
	public static final int PROP_SHADOW_NONE = 0x0;
	/**
	 * 그림자 종류 - 비연속
	 */
	public static final int PROP_SHADOW_INTERMIT = 0x1;
	/**
	 * 그림자 종류 - 연속
	 */
	public static final int PROP_SHADOW_CONTINUE = 0x2;
	/**
	 * 강조점 종류 - 없음 
	 */
	public static final int PROP_DCRITIC_NONE = 0x0;
	/**
	 * 강조점 종류 - 검정 동그라미 강조점
	 */
	public static final int PROP_DCRITIC_CIRCLET_FILLED = 0x0;
	/**
	 * 강조점 종류 - 속이빈 동그라미 강조점
	 */
	public static final int PROP_DCRITIC_CIRCLET_EMPTY = 0x0;
	/**
	 * 강조점 종류 - 뒤집힌 ^
	 */
	public static final int PROP_DCRITIC_POINT = 0x0;
	/**
	 * 강조점 종류 - 물결모양
	 */
	public static final int PROP_DCRITIC_TILDE = 0x0;
	/**
	 * 강조점 종류 - 글자 왼쪽에 1점
	 */
	public static final int PROP_DCRITIC_DOT1 = 0x0;
	/**
	 * 강조점 종류 - 글자 왼쪽에 2점
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
	 * 밑줄 종류(없음, 글자 위, 글자 아래)<br/>
	 * - 없음 {@link #PROP_CHARLINE_POS_NONE}<br/>
	 * - 글자 위 {@link #PROP_CHARLINE_POS_OVER}<br/>
	 * - 글자 아래 {@link #PROP_CHARLINE_POS_UNDER}<br/>
	 * 
	 * @return
	 */
	public int getCharLinePos(){
		return Converter.getBits(this.f6_fontProperty.getValue().intValue(), 2, 2);
	}
	/**
	 * 밑줄의 모양 (표-20 참조)
	 * 
	 * @return
	 */
	public int getCharLineStyle(){
		return Converter.getBits(this.f6_fontProperty.getValue().intValue(), 4, 4);
	}
	/**
	 * 폰트 외곽선의 종류(폰트 자체의 테두리선을 의미함) <br/>
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
	 * 그림자 종류 (표-30)
	 * @return
	 */
	public int getShadowType(){
		/*
		 * 그림자 종류
		 * 0 : 없음
		 * 1 : 비연속
		 * 2 : 연속
		 */
		return Converter.getBits(this.f6_fontProperty.getValue().intValue(), 11, 2);
	}
	/**
	 * 양각 설정 여부
	 * @return
	 */
	public boolean isEmboss(){
		return Converter.getBits(this.f6_fontProperty.getValue().intValue(), 13, 1) != 0 ;
	}
	/**
	 * 음각 설정 여부
	 * @return
	 */
	public boolean isIntaglio(){
		return Converter.getBits(this.f6_fontProperty.getValue().intValue(), 14, 1) != 0 ;
	}
	/**
	 * 글자가 위첨자인지 나타냄
	 * @return
	 */
	public boolean isSuperScript(){
		return Converter.getBits(this.f6_fontProperty.getValue().intValue(), 15, 1) != 0 ;
	}
	/**
	 * 글자가 아래첨자인지 나타냄
	 * @return
	 */
	public boolean isSubScript(){
		return Converter.getBits(this.f6_fontProperty.getValue().intValue(), 16, 1) != 0 ;
	}
	/**
	 * 취소선 사용여부 (없음, 있음)
	 * 
	 * @return
	 */
	public int isStrokeLineEnabled(){
		return Converter.getBits(this.f6_fontProperty.getValue().intValue(), 18, 3);
	}
	/**
	 * <p>
	 * 글자 위 강조점(구별점)의 종류
	 * </p>
	 * 0 강조점 없음 {@link #PROP_DCRITIC_NONE}<br/>
	 * 1 검정 동그라미 (글자위){@link #PROP_DCRITIC_CIRCLET_FILLED}<br/>
	 * 2 속 빈 동그라미  (글자위){@link #PROP_DCRITIC_CIRCLET_EMPTY}<br/>
	 * 3 세모꼴 (글자위){@link #PROP_DCRITIC_POINT}<br/>
	 * 4 물결모양(글자위){@link #PROP_DCRITIC_TILDE}<br/>
	 * 5 점 하나(글자 왼쪽){@link #PROP_DCRITIC_DOT1}<br/>
	 * 6 점 두개(글자 왼쪽){@link #PROP_DCRITIC_DOT2}<br/>
	 * 
	 * @return
	 */
	public int getDiacriticalMark (){
		return Converter.getBits(this.f6_fontProperty.getValue().intValue(), 21, 4);
	}
	/**
	 * 글꼴에 어울리는 빈칸 사용 여부
	 * 
	 * @return
	 */
	public boolean isSuitableBlankUse() {
		return Converter.getBits(this.f6_fontProperty.getValue().intValue(), 25, 1) != 0 ;
	}
	/**
	 * 취소선 모양 ( 표-20 참조)
	 * 
	 * @return
	 */
	public int getStrokeLineStyle(){
		return Converter.getBits(this.f6_fontProperty.getValue().intValue(), 26, 4);
	}
	/**
	 * <a href="http://www.terms.co.kr/kerning.htm">커닝</a> 설정 여부
	 * 
	 * @return
	 */
	public boolean isKerningEnabled(){
		return Converter.getBits(this.f6_fontProperty.getValue().intValue(), 30, 1) != 0 ;		
	}
}
