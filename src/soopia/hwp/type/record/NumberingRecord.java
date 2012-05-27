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
 * 4.1.8 ���� ��ȣ
 * 
 * @author chmin
 * @page p.22 (ǥ-33, ǥ-34, ǥ-35, ǥ-36, ǥ-37) 
 *
 */
public class NumberingRecord extends AbstractRecord {
	/**
	 * 1������ ��Ÿ���� �����
	 */
	final public static int LEVEL_1 = 0;
	/**
	 * 2������ ��Ÿ���� �����
	 */
	final public static int LEVEL_2 = 1;
	/**
	 * 3������ ��Ÿ���� �����
	 */
	final public static int LEVEL_3 = 2;
	/**
	 * 4������ ��Ÿ���� �����
	 */
	final public static int LEVEL_4 = 3;
	/**
	 * 5������ ��Ÿ���� �����
	 */
	final public static int LEVEL_5 = 4;
	/**
	 * 6������ ��Ÿ���� �����
	 */
	final public static int LEVEL_6 = 5;
	/**
	 * 7������ ��Ÿ���� �����
	 */
	final public static int LEVEL_7 = 6;
	
	final public static int NUMBERING_CNT = 7;
	
	/**
	 * ���� ��ȣ�� ���� ������ ��Ÿ���� �����
	 * 
	 * @see NumberingInfo#getAlignment()
	 * @see NumberingInfo#setAlignment(int)
	 * 
	 */
	final public static int ALIGNMENT_LEFT = 0;
	/**
	 * ���� ��ȣ�� ��� ������ ��Ÿ���� �����
	 * 
	 * @see NumberingInfo#getAlignment()
	 * @see NumberingInfo#setAlignment(int)
	 * 
	 */
	final public static int ALIGNMENT_CENTER = 1;
	/**
	 * ���� ��ȣ�� ������ ������ ��Ÿ���� �����
	 * 
	 * @see NumberingInfo#getAlignment()
	 * @see NumberingInfo#setAlignment(int)
	 * 
	 */
	final public static int ALIGNMENT_RIGHT = 2;
	
	/**
	 * �������� �Ÿ� ���� - ���� ũ�⿡ ���� % ����
	 */
	final public static int DIST_TYPE_RATIO = 0;
	/**
	 * �������� �Ÿ� ���� - ��
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
		 * 1~7������ ��Ÿ���� ���� ��ȯ
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
		 * �������κ����� �Ÿ����� ��ȯ��
		 * @return HwpUnit ������ ��
		 */
		public HwpUnit16 getDistanceFromText() {
			return distanceFromText;
		}
		
		/**
		 * �������� �Ÿ��� ������.
		 * @param startingNumber hwp unit ������ ��
		 */
		public void setDistanceFromText(HwpUnit16 distanceFromText) {
			this.distanceFromText = distanceFromText;
		}
		/**
		 * ������ �۲��� id�� ��ȯ��. ������ �۲��� ������ 0xFFFFFFFF �� ��ȯ.
		 * @return
		 */
		public UInt32 getFontFaceId() {
			return fontFaceId;
		}
		/**
		 * ���� ��ȣ�� �۲��� �����Ǿ��ִ��� ��Ÿ��
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
		 * ������ ���� ���� - ����(0), ���(1), ������(2)
		 * @return ����, ���, �������� ��Ÿ���� �� �� �ϳ�
		 * @see NumberingRecord#ALIGNMENT_LEFT
		 * @see NumberingRecord#ALIGNMENT_CENTER
		 * @see NumberingRecord#ALIGNMENT_RIGHT
		 */
		public int getAlignment () {
			return Converter.getBits(this.property.getValue().intValue(), 0, 2);
		}
		
		/**
		 * ��ȣ ������ ���� ����� ������.
		 * @param alignment ����, ���, ������ ������ ��Ÿ���� ��
		 * @see NumberingRecord#ALIGNMENT_LEFT
		 * @see NumberingRecord#ALIGNMENT_CENTER
		 * @see NumberingRecord#ALIGNMENT_RIGHT
		 */
		private void setAlignment (int alignment ) {
			// TODO not implemented
		}
		
		/**
		 * ���� ��ȣ �ʺ� ���� ��ȣ ���ڿ��� ���� �ʺ� ���� �������� ��Ÿ��.
		 * @return
		 */
		public boolean isWidthAutoAdjust() {
			return Converter.getBits(this.property.getValue().intValue(), 2, 1) == 1;
		}
		
		private void setWidthAutoAdjust(boolean autoAdjust) {
			// TODO not implemented
		}
		
		/**
		 * �������� �Ÿ� ����
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
