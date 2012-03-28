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

	public static final int FONT_PROP_BOLD = 0x02;
	public static final int FONT_PROP_ITALIC = 0x01;
	
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
		return (this.f6_fontProperty.getValue().intValue() & FONT_PROP_ITALIC) != 0 ;
	}
	public boolean isBold() {
		return (this.f6_fontProperty.getValue().intValue() & FONT_PROP_BOLD) != 0 ;
	}
	/**
	 * 테두리선 종류(없음, 글자 위, 글자 아래)
	 * @return
	 */
	public int getUnderlineType(){
		/*
		 * 0 : no underline
		 * 1 : over the characters
		 * 2 : under the characters
		 */
		return Converter.getBits(this.f6_fontProperty.getValue().intValue(), 2, 2);
	}
	/**
	 * 테두리선의 모양 (표-20 참조)
	 * @return
	 */
	public int getUnderLineStyle(){
		/*
		 * (표-20 테두리선의 모양) 
		 */ 
		return Converter.getBits(this.f6_fontProperty.getValue().intValue(), 4, 4);
	}
	/**
	 * 폰트 외곽선의 종류
	 * (폰트 자체의 테두리선을 의미함)
	 * @return
	 */
	public int getFontOutline(){
		/*
		 * 외곽선의 종류 (표-30)
		 */
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
	 * 취소선의 스타일 (없음, 실선, 긴점선 등..)
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
	 * 0 강조점 없음<br/>
	 * 1 검정 동그라미 (글자위)<br/>
	 * 2 속 빈 동그라미  (글자위)<br/>
	 * 3 세모꼴 (글자위)<br/>
	 * 4 물결모양(글자위)<br/>
	 * 5 점 하나(글자 왼쪽)<br/>
	 * 6 점 두개(글자 왼쪽)<br/>
	 * 
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
	 * 취소선 모양
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
