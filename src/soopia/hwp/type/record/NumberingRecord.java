package soopia.hwp.type.record;

import java.util.ArrayList;
import java.util.Arrays;

import soopia.hwp.codec.DecodingException;
import soopia.hwp.type.AbstractRecord;
import soopia.hwp.type.HwpUnit16;
import soopia.hwp.type.IStreamStruct;
import soopia.hwp.type.UInt16;
import soopia.hwp.type.UInt32;
import soopia.hwp.type.stream.RecordHeader;
import soopia.hwp.util.Converter;
/**
 * 4.1.8 문단 번호
 * 
 * @author chmin
 * @page p.22 (표-33, 표-34, 표-35, 표-36, 표-37) 
 *
 */
public class NumberingRecord extends AbstractRecord {
	/**
	 * 1수준을 나타내는 상수값
	 */
	final public static int LEVEL_1 = 0;
	/**
	 * 2수준을 나타내는 상수값
	 */
	final public static int LEVEL_2 = 1;
	/**
	 * 3수준을 나타내는 상수값
	 */
	final public static int LEVEL_3 = 2;
	/**
	 * 4수준을 나타내는 상수값
	 */
	final public static int LEVEL_4 = 3;
	/**
	 * 5수준을 나타내는 상수값
	 */
	final public static int LEVEL_5 = 4;
	/**
	 * 6수준을 나타내는 상수값
	 */
	final public static int LEVEL_6 = 5;
	/**
	 * 7수준을 나타내는 상수값
	 */
	final public static int LEVEL_7 = 6;
	
	final public static int NUMBERING_CNT = 7;
	
	/**
	 * 문단 번호의 왼쪽 정렬을 나타내는 상수값
	 * 
	 * @see NumberingInfo#getAlignment()
	 * @see NumberingInfo#setAlignment(int)
	 * 
	 */
	final public static int ALIGNMENT_LEFT = 0;
	/**
	 * 문단 번호의 가운데 정렬을 나타내는 상수값
	 * 
	 * @see NumberingInfo#getAlignment()
	 * @see NumberingInfo#setAlignment(int)
	 * 
	 */
	final public static int ALIGNMENT_CENTER = 1;
	/**
	 * 문단 번호의 오른쪽 정렬을 나타내는 상수값
	 * 
	 * @see NumberingInfo#getAlignment()
	 * @see NumberingInfo#setAlignment(int)
	 * 
	 */
	final public static int ALIGNMENT_RIGHT = 2;
	
	/**
	 * 본문과의 거리 종류 - 글자 크기에 대한 % 비율
	 */
	final public static int DIST_TYPE_RATIO = 0;
	/**
	 * 본문과의 거리 종류 - 값
	 */
	final public static int DIST_TYPE_VALUE = 1;
	
	
	private ArrayList<NumberingInfo> numberingInfoList = new ArrayList<>();
	
	private UInt16 startingNumber ;
	public NumberingRecord(RecordHeader header, IStreamStruct ds) {
		super(header, ds);
	}
	
	public void addNumberingInfo(UInt32 property, HwpUnit16 revisedWidth,
			HwpUnit16 distFromText, UInt32 fontId, char[] charArray) throws DecodingException {
		
		if ( numberingInfoList.size() == NUMBERING_CNT ){
			throw new DecodingException("too much numbering info. alreay 7 infomation filled." );
		}
		
		NumberingInfo ni = new NumberingInfo(
				numberingInfoList.size(), 
				property, 
				revisedWidth, 
				distFromText, 
				fontId, 
				charArray );
		
		numberingInfoList.add(ni);
	}
	
	public NumberingInfo getNumberingInfoAt(int index) {
		if ( index < LEVEL_1 || index > LEVEL_7 ){
			throw new IndexOutOfBoundsException("index should be between 0 and 6 inclusive, but " + index );
		}
		return numberingInfoList.get(index);
	}
	
	public UInt16 getStartingNumber() {
		return startingNumber;
	}

	public void setStartingNumber(UInt16 startingNumber) {
		this.startingNumber = startingNumber;
	}

	public static class NumberingInfo {
		private int level ; // 0 ~ 6 
		private UInt32 property ;
		private HwpUnit16 revisionWidth ;
		private HwpUnit16 distanceFromText;
		private UInt32 fontFaceId ;
		private char [] formatChars;
		
		private UInt32 startingNumber ;
		
		public NumberingInfo(int level, UInt32 property,
				HwpUnit16 revisionWidth, HwpUnit16 distanceFromText,
				UInt32 fontFaceId, char[] formatChars) {
			super();
			this.level = level;
			this.property = property;
			this.revisionWidth = revisionWidth;
			this.distanceFromText = distanceFromText;
			this.fontFaceId = fontFaceId;
			this.formatChars = formatChars;
		}

		/**
		 * 1~7순위를 나타내는 값을 반환
		 * @return
		 * @see NumberingRecord#LEVEL_1
		 * @see NumberingRecord#LEVEL_2
		 * @see NumberingRecord#LEVEL_3
		 * @see NumberingRecord#LEVEL_4
		 * @see NumberingRecord#LEVEL_5
		 * @see NumberingRecord#LEVEL_6
		 * @see NumberingRecord#LEVEL_7
		 */
		public int getLevel() {
			return level;
		}
		
		public UInt32 getProperty() {
			return property;
		}

		public void setProperty(UInt32 property) {
			this.property = property;
		}

		public HwpUnit16 getRevisionWidth() {
			return revisionWidth;
		}
		
		public void setRevisionWidth(HwpUnit16 revisionWidth) {
			this.revisionWidth = revisionWidth;
		}
		
		/**
		 * 본문으로부터의 거리값을 반환함
		 * @return HwpUnit 형식의 값
		 */
		public HwpUnit16 getDistanceFromText() {
			return distanceFromText;
		}
		
		/**
		 * 본문과의 거리를 설정함.
		 * @param startingNumber hwp unit 형식의 값
		 */
		public void setDistanceFromText(HwpUnit16 distanceFromText) {
			this.distanceFromText = distanceFromText;
		}
		/**
		 * 설정된 글꼴의 id를 반환함. 설정된 글꼴이 없으면 0xFFFFFFFF 을 반환.
		 * @return
		 */
		public UInt32 getFontFaceId() {
			return fontFaceId;
		}
		/**
		 * 문단 번호에 글꼴이 설정되어있는지 나타냄
		 * @return
		 */
		public boolean hasFontFace() {
			return 0xFFFFFFFF != this.getFontFaceId().getValue().intValue();
		}
		
		public void setFontFaceId(UInt32 fontFaceId) {
			this.fontFaceId = fontFaceId;
		}
		
		public char[] getFormatChars() {
			return formatChars;
		}
		
		public void setFormatChars(char[] formatChars) {
			this.formatChars = formatChars;
		}

		public UInt32 getStartingNumber() {
			return startingNumber;
		}
		
		public void setStartingNumber(UInt32 startingNumber) {
			this.startingNumber = startingNumber;
		}
		
		/**
		 * 문단의 정렬 종류 - 왼쪽(0), 가운데(1), 오른쪽(2)
		 * @return 왼쪽, 가운데, 오른쪽을 나타내는 값 중 하나
		 * @see NumberingRecord#ALIGNMENT_LEFT
		 * @see NumberingRecord#ALIGNMENT_CENTER
		 * @see NumberingRecord#ALIGNMENT_RIGHT
		 */
		public int getAlignment () {
			return Converter.getBits(this.property.getValue().intValue(), 0, 2);
		}
		
		/**
		 * 번호 문단의 정렬 방식을 설정함.
		 * @param alignment 왼쪽, 가운데, 오른쪽 정렬을 나타내는 값
		 * @see NumberingRecord#ALIGNMENT_LEFT
		 * @see NumberingRecord#ALIGNMENT_CENTER
		 * @see NumberingRecord#ALIGNMENT_RIGHT
		 */
		private void setAlignment (int alignment ) {
			// TODO not implemented
		}
		
		/**
		 * 문단 번호 너비를 문단 번호 문자열의 실제 너비에 맞출 것인지를 나타냄.
		 * @return
		 */
		public boolean isWidthAutoAdjust() {
			return Converter.getBits(this.property.getValue().intValue(), 2, 1) == 1;
		}
		
		private void setWidthAutoAdjust(boolean autoAdjust) {
			// TODO not implemented
		}
		
		/**
		 * 본문과의 거리 종류
		 * @return
		 */
		public int getDistanceType() {
			return Converter.getBits(this.property.getValue().intValue(), 4, 1) ;
		}
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime
					* result
					+ ((distanceFromText == null) ? 0 : distanceFromText
							.hashCode());
			result = prime * result
					+ ((fontFaceId == null) ? 0 : fontFaceId.hashCode());
			result = prime * result + Arrays.hashCode(formatChars);
			result = prime * result + level;
			result = prime * result
					+ ((property == null) ? 0 : property.hashCode());
			result = prime * result
					+ ((revisionWidth == null) ? 0 : revisionWidth.hashCode());
			result = prime * result + startingNumber.hashCode();
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			NumberingInfo other = (NumberingInfo) obj;
			if (distanceFromText == null) {
				if (other.distanceFromText != null)
					return false;
			} else if (!distanceFromText.equals(other.distanceFromText))
				return false;
			if (fontFaceId == null) {
				if (other.fontFaceId != null)
					return false;
			} else if (!fontFaceId.equals(other.fontFaceId))
				return false;
			if (!Arrays.equals(formatChars, other.formatChars))
				return false;
			if (level != other.level)
				return false;
			if (property == null) {
				if (other.property != null)
					return false;
			} else if (!property.equals(other.property))
				return false;
			if (revisionWidth == null) {
				if (other.revisionWidth != null)
					return false;
			} else if (!revisionWidth.equals(other.revisionWidth))
				return false;
			if (startingNumber != other.startingNumber)
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "NumberingInfo [level=" + level + ", property=" + property
					+ ", revisionWidth=" + revisionWidth
					+ ", distFromText=" + distanceFromText
					+ ", fontId=" + fontFaceId + ", formatChars="
					+ Arrays.toString(formatChars) + ", startingNumber="
					+ startingNumber + "]";
		}
	}
}
