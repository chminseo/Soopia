package soopia.hwp.type.record;

import soopia.hwp.type.AbstractRecord;
import soopia.hwp.type.HwpByte;
import soopia.hwp.type.IStreamStruct;
import soopia.hwp.type.UInt16;
import soopia.hwp.type.stream.RecordHeader;

public class StyleRecord extends AbstractRecord {
	/**
	 * 문단 스타일
	 */
	final public static int STYLE_PARA = 0;
	/**
	 * 글자 스타일
	 */
	final public static int STYLE_FONT = 1;
	final public static int STYLE_NAME_EN = 100;
	final public static int STYLE_nAME_KR = 101;
	private int index ;
	private HwpByte property ;
	private String styleNameKr;
	private String styleNameEn;
	private HwpByte nextStyleId;
	private UInt16 unknown0214 = new UInt16(new byte[]{0x12, 0x04}); // 0x12 0x04 in utf16-LE
	private UInt16 langId;
	private UInt16 paraShapeId;
	private UInt16 fontShapeId;
	
	public StyleRecord(RecordHeader header, IStreamStruct ds) {
		super(header, ds);
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public HwpByte getProperty() {
		return property;
	}

	public void setProperty(HwpByte property) {
		this.property = property;
	}

	public String getStyleNameKr() {
		return styleNameKr;
	}

	public void setStyleNameKr(String styleNameKr) {
		this.styleNameKr = styleNameKr;
	}

	public String getStyleNameEn() {
		return styleNameEn;
	}

	public void setStyleNameEn(String styleNameEn) {
		this.styleNameEn = styleNameEn;
	}

	public HwpByte getNextStyleId() {
		return nextStyleId;
	}

	public void setNextStyleId(HwpByte nextStyleId) {
		this.nextStyleId = nextStyleId;
	}

	public UInt16 getUnknown0412() {
		return unknown0214;
	}

	public void setUnknown0214(UInt16 unknown0214) {
		this.unknown0214 = unknown0214;
	}

	public UInt16 getLangId() {
		return langId;
	}

	public void setLangId(UInt16 langId) {
		this.langId = langId;
	}

	public UInt16 getParaShapeId() {
		return paraShapeId;
	}

	public void setParaShapeId(UInt16 paraShapeId) {
		this.paraShapeId = paraShapeId;
	}

	public UInt16 getFontShapeId() {
		return fontShapeId;
	}

	public void setFontShapeId(UInt16 fontShapeId) {
		this.fontShapeId = fontShapeId;
	}

	public boolean isFontStyle() {
		return property.getValue().intValue() == STYLE_FONT ;
	}
	public boolean isParagraghStyle() {
		return property.getValue().intValue() == STYLE_PARA;
	}
	
	

}
