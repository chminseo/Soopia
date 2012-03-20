package soopia.hwp.type.record;

import java.util.Arrays;

import soopia.hwp.type.AbstractRecord;
import soopia.hwp.type.HwpByte;
import soopia.hwp.type.IStreamStruct;
import soopia.hwp.type.stream.RecordHeader;
/**
 * 
 * @author chmin
 * @tagID HWPTAG_FACE_NAME
 * @page p.17
 */
public class FaceNameRecord extends AbstractRecord {

	final public static int MASK_ALTERNATE_FONT = 0x80;
	final public static int MASK_FONT_TYPE_INFO = 0x40;
	final public static int MASK_DEFAULT_FONT= 0x20;
	
	private HwpByte faceProperty;
	private String fontName ;
	private byte [] fontTypeInfo ;
	private String defaultFontName;
	public FaceNameRecord(RecordHeader header, IStreamStruct ds, int offset) {
		super(header, ds, offset);
	}
	/**
	 * 대체 글꼴 존재 여부
	 * @return
	 */
	public boolean hasAlternateFont() {
		return (faceProperty.getValue() & MASK_ALTERNATE_FONT ) != 0 ;
	}

	public void setFaceProperty(HwpByte bit) {
		this.faceProperty = bit;
	}
	/**
	 * 글꼴 유형 정보 존재 여부
	 * @return
	 */
	public boolean hasFaceTypeInfo() {
		return (this.faceProperty.getValue() & MASK_FONT_TYPE_INFO ) != 0;
	}
	/**
	 * 기본 글꼴 존재 여부
	 * @return
	 */
	public boolean hasDefaultFont() {
		return (this.faceProperty.getValue() & MASK_DEFAULT_FONT ) != 0;
	}
	/**
	 * hwp 파일에 명시된 글꼴명
	 * @return
	 */
	public String getFontName(){
		return this.fontName;
	}
	public void setFontName(String fontName) {
		this.fontName = fontName;
	}
	/**
	 * hwp 파일에 지정된 기본 글꼴명
	 * @return
	 */
	public String getDefaultFontName() {
		return this.defaultFontName ;
	}
	
	public void setDefaultFontName(String defaultFontName){
		this.defaultFontName = defaultFontName;
	}

	public byte[] getFontTypeInfo(){
		return Arrays.copyOf(this.fontTypeInfo, this.fontTypeInfo.length);
	}
	public void setFontTypeInfo(byte[] typeInfo) {
		this.fontTypeInfo = typeInfo;
	}
	
}
