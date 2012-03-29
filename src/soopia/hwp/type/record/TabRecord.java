/**
 * 본 제품은 한글과컴퓨터의 한글 문서 파일(.hwp) 공개 문서를 참고하여 개발하였습니다.
 */
package soopia.hwp.type.record;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import soopia.hwp.type.AbstractRecord;
import soopia.hwp.type.HwpUnit;
import soopia.hwp.type.IStreamStruct;
import soopia.hwp.type.UInt16;
import soopia.hwp.type.UInt32;
import soopia.hwp.type.UInt8;
import soopia.hwp.type.record.TabRecord.TabData;
import soopia.hwp.type.stream.RecordHeader;
import soopia.hwp.util.Converter;

/**
 * <p>4.1.7 탭 정의</p>
 * 
 * @author chmin
 * @tagID HWPTAG_TAB_DEF
 * @page p.22
 */
public class TabRecord extends AbstractRecord {
	/**
	 * <p>문단 왼쪽끝 자동탭 설정</p>
	 * {@link #isLeftTabAlways()}
	 */
	final public static int LEFT_TAB_ALWAYS = 0x01;
	/**
	 * <p>문단 오른쪽 끝 자동탭 설정</p>
	 * {@link #isRightTabAlways()}
	 */
	final public static int RIGHT_TAB_ALWAYS = 0x02;
	/**
	 * <p>탭의 종류 - 왼쪽</p>
	 * 
	 * @see TabData#getOrientation()
	 * @see TabData#setOrientation()
	 */
	final public static int ORIENTATION_LEFT = 0x0;
	/**
	 * <p>탭의 종류 - 오른쪽</p>
	 * 
	 * @see TabData#getOrientation()
	 * @see TabData#setOrientation()
	 */
	final public static int ORIENTATION_RIGHT = 0x1;
	/**
	 * <p>탭의 종류 - 가운데</p>
	 * 
	 * @see TabData#getOrientation()
	 * @see TabData#setOrientation()
	 */
	final public static int ORIENTATION_CENTER = 0x2;
	/**
	 * <p>탭의 종류 - 소수점</p>
	 * 
	 * @see TabData#getOrientation()
	 * @see TabData#setOrientation()
	 */
	final public static int ORIENTATION_FP = 0x3;
	
	private UInt32 tabProperty;
	private ArrayList<TabData> tabs ;
	
	/**
	 * @param header
	 * @param ds
	 */
	public TabRecord(RecordHeader header, IStreamStruct ds) {
		super(header, ds);
	}
	/**
	 * 탭 속성을 반환
	 * @see #LEFT_TAB_ALWAYS
	 * @see #RIGHT_TAB_ALWAYS
	 * @return {@link #LEFT_TAB_ALWAYS} 와 {@link #RIGHT_TAB_ALWAYS} 의 설정상태 
	 */
	public UInt32 getTabProperty(){
		return tabProperty;
	}
	/**
	 * 탭 속성을 지정
	 * @see #LEFT_TAB_ALWAYS
	 * @see #RIGHT_TAB_ALWAYS
	 * @param property {@link #LEFT_TAB_ALWAYS} 와 {@link #RIGHT_TAB_ALWAYS} 의 설정상태
	 */
	public void setTabProperty(UInt32 property){
		tabProperty = property;
	}
	/**
	 * 설정된 탭들을 반환
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<TabData> getTabList () {
		return tabs == null ? Collections.EMPTY_LIST : tabs;
	}
	/**
	 * 주어진 위치의 탭 객체를 반환
	 * @param index
	 * @return index 위치의 탭 객체
	 */
	public TabData getTabAt(int index) {
		if ( this.tabs == null )
			throw new IndexOutOfBoundsException("number of tab is zero");
		return this.tabs.get(index);
	}
	/**
	 * 탭을 맨 마지막에 추가함
	 * @param tab
	 */
	public void addTab(TabData tab){
		if ( this.tabs == null)
			this.tabs = new ArrayList<>();
		this.tabs.add(tab);
	}
	/**
	 * 탭의 개수 반환
	 * @return
	 */
	public int getTabCount() {
		return this.tabs == null ? 0 : this.tabs.size();
	}
	/**
	 * 문단 왼쪽 끝 자동탭 설정 여부
	 * 
	 * @return 설정되어있으면 true, 아니면 false
	 */
	public boolean isLeftTabAlways(){
		return (this.tabProperty.getValue().longValue() & LEFT_TAB_ALWAYS) != 0 ;
	}
	/**
	 * 문단 오른쪽 끝 자동탭 설정 여부
	 * @return 설정되어있으면 true, 아니면 false
	 */
	public boolean isRightTabAlways(){
		return (this.tabProperty.getValue().longValue() & RIGHT_TAB_ALWAYS) != 0 ;
	}
	/**
	 * 하나의 탭 정보를 나타내는 클래스
	 * @author chmin
	 *
	 */
	public static class TabData {
		HwpUnit size ;
		UInt8 tabOrientation ;
		UInt8 fillStyleId ;
		UInt16 padding ;
		public TabData(HwpUnit size, UInt8 orientation, UInt8 fillStyleId) {
			super();
			this.size = size;
			this.tabOrientation = orientation;
			this.fillStyleId = fillStyleId;
			this.padding = new UInt16(new byte[]{0,0});
		}
		/**
		 * <p>탭의 너비를 반환(단위는 HWPUNIT )</p>
		 * 1 inch = 7200 HU<br/>
		 * 1 pt = 100 HU<br/>
		 * <p><b>문서버전 5.0.3.0에서 지정한 탭 너비의 두배로 값이 설정되어있음.</b> </p>
		 * @return HWPUNIT 단위의 탭 너비
		 */
		public HwpUnit getWidth() {
			return size;
		}
		/**
		 * <p>탭의 너비를 지정(단위는 HWPUNIT)</p>
		 * 1 inch = 7200 HU<br/>
		 * 1 pt = 100 HU<br/>
		 * <p><b>문서버전 5.0.3.0에서 지정한 탭 너비의 두배로 값이 설정되어있음.</b> </p>
		 * @param size
		 */
		public void setSize(HwpUnit size) {
			this.size = size;
		}
		/**
		 * <p>탭의 방향 반환</p> 
		 * 탭의 오른쪽에 인접한 문자열들이 배치되는 방향을 나타냄
		 * 
		 * @see TabRecord#ORIENTATION_LEFT
		 * @see TabRecord#ORIENTATION_RIGHT
		 * @see TabRecord#ORIENTATION_CENTER
		 * @see TabRecord#ORIENTATION_FP
		 * 
		 * @return
		 */
		public UInt8 getOrientation() {
			return tabOrientation;
		}
		/**
		 * <p>탭의 방향 설정</p> 
		 * 탭의 오른쪽에 인접한 문자열들이 배치되는 방향을 설정함
		 * 
		 * @see TabRecord#ORIENTATION_LEFT
		 * @see TabRecord#ORIENTATION_RIGHT
		 * @see TabRecord#ORIENTATION_CENTER
		 * @see TabRecord#ORIENTATION_FP
		 * 
		 * @return
		 */
		public void setOrientation(UInt8 type) {
			this.tabOrientation = type;
		}
		/**
		 * 탭 구간을 채우는 선 종류값 반환
		 * @return 표-20 테두리선 종류 참조값
		 */
		public UInt8 getFillStyleId() {
			return fillStyleId;
		}
		/**
		 * 탭 구간을 채우는 선 종류값 지정
		 * @param fillStyleId 표-20 테두리선 종류 참조값
		 */
		public void setFillStyleId(UInt8 fillStyleId) {
			this.fillStyleId = fillStyleId;
		}
		/**
		 * 8비트 채우기 위한 2바이트 반환
		 * @return 2바이트 0x00 0x00
		 */
		public UInt16 getPadding() {
			return padding;
		}
		public void setPadding(UInt16 padding) {
			this.padding = padding;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((fillStyleId == null) ? 0 : fillStyleId.hashCode());
			result = prime * result
					+ ((padding == null) ? 0 : padding.hashCode());
			result = prime * result + ((size == null) ? 0 : size.hashCode());
			result = prime * result + ((tabOrientation == null) ? 0 : tabOrientation.hashCode());
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
			TabData other = (TabData) obj;
			if (fillStyleId == null) {
				if (other.fillStyleId != null)
					return false;
			} else if (!fillStyleId.equals(other.fillStyleId))
				return false;
			if (padding == null) {
				if (other.padding != null)
					return false;
			} else if (!padding.equals(other.padding))
				return false;
			if (size == null) {
				if (other.size != null)
					return false;
			} else if (!size.equals(other.size))
				return false;
			if (tabOrientation == null) {
				if (other.tabOrientation != null)
					return false;
			} else if (!tabOrientation.equals(other.tabOrientation))
				return false;
			return true;
		}	
	}
}
