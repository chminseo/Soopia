/**
 * �� ��ǰ�� �ѱ۰���ǻ���� �ѱ� ���� ����(.hwp) ���� ������ �����Ͽ� �����Ͽ����ϴ�.
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
 * <p>4.1.7 �� ����</p>
 * 
 * @author chmin
 * @tagID HWPTAG_TAB_DEF
 * @page p.22
 */
public class TabRecord extends AbstractRecord {
	/**
	 * <p>���� ���ʳ� �ڵ��� ����</p>
	 * {@link #isLeftTabAlways()}
	 */
	final public static int LEFT_TAB_ALWAYS = 0x01;
	/**
	 * <p>���� ������ �� �ڵ��� ����</p>
	 * {@link #isRightTabAlways()}
	 */
	final public static int RIGHT_TAB_ALWAYS = 0x02;
	/**
	 * <p>���� ���� - ����</p>
	 * 
	 * @see TabData#getOrientation()
	 * @see TabData#setOrientation()
	 */
	final public static int ORIENTATION_LEFT = 0x0;
	/**
	 * <p>���� ���� - ������</p>
	 * 
	 * @see TabData#getOrientation()
	 * @see TabData#setOrientation()
	 */
	final public static int ORIENTATION_RIGHT = 0x1;
	/**
	 * <p>���� ���� - ���</p>
	 * 
	 * @see TabData#getOrientation()
	 * @see TabData#setOrientation()
	 */
	final public static int ORIENTATION_CENTER = 0x2;
	/**
	 * <p>���� ���� - �Ҽ���</p>
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
	 * �� �Ӽ��� ��ȯ
	 * @see #LEFT_TAB_ALWAYS
	 * @see #RIGHT_TAB_ALWAYS
	 * @return {@link #LEFT_TAB_ALWAYS} �� {@link #RIGHT_TAB_ALWAYS} �� �������� 
	 */
	public UInt32 getTabProperty(){
		return tabProperty;
	}
	/**
	 * �� �Ӽ��� ����
	 * @see #LEFT_TAB_ALWAYS
	 * @see #RIGHT_TAB_ALWAYS
	 * @param property {@link #LEFT_TAB_ALWAYS} �� {@link #RIGHT_TAB_ALWAYS} �� ��������
	 */
	public void setTabProperty(UInt32 property){
		tabProperty = property;
	}
	/**
	 * ������ �ǵ��� ��ȯ
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<TabData> getTabList () {
		return tabs == null ? Collections.EMPTY_LIST : tabs;
	}
	/**
	 * �־��� ��ġ�� �� ��ü�� ��ȯ
	 * @param index
	 * @return index ��ġ�� �� ��ü
	 */
	public TabData getTabAt(int index) {
		if ( this.tabs == null )
			throw new IndexOutOfBoundsException("number of tab is zero");
		return this.tabs.get(index);
	}
	/**
	 * ���� �� �������� �߰���
	 * @param tab
	 */
	public void addTab(TabData tab){
		if ( this.tabs == null)
			this.tabs = new ArrayList<>();
		this.tabs.add(tab);
	}
	/**
	 * ���� ���� ��ȯ
	 * @return
	 */
	public int getTabCount() {
		return this.tabs == null ? 0 : this.tabs.size();
	}
	/**
	 * ���� ���� �� �ڵ��� ���� ����
	 * 
	 * @return �����Ǿ������� true, �ƴϸ� false
	 */
	public boolean isLeftTabAlways(){
		return (this.tabProperty.getValue().longValue() & LEFT_TAB_ALWAYS) != 0 ;
	}
	/**
	 * ���� ������ �� �ڵ��� ���� ����
	 * @return �����Ǿ������� true, �ƴϸ� false
	 */
	public boolean isRightTabAlways(){
		return (this.tabProperty.getValue().longValue() & RIGHT_TAB_ALWAYS) != 0 ;
	}
	/**
	 * �ϳ��� �� ������ ��Ÿ���� Ŭ����
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
		 * <p>���� �ʺ� ��ȯ(������ HWPUNIT )</p>
		 * 1 inch = 7200 HU<br/>
		 * 1 pt = 100 HU<br/>
		 * <p><b>�������� 5.0.3.0���� ������ �� �ʺ��� �ι�� ���� �����Ǿ�����.</b> </p>
		 * @return HWPUNIT ������ �� �ʺ�
		 */
		public HwpUnit getWidth() {
			return size;
		}
		/**
		 * <p>���� �ʺ� ����(������ HWPUNIT)</p>
		 * 1 inch = 7200 HU<br/>
		 * 1 pt = 100 HU<br/>
		 * <p><b>�������� 5.0.3.0���� ������ �� �ʺ��� �ι�� ���� �����Ǿ�����.</b> </p>
		 * @param size
		 */
		public void setSize(HwpUnit size) {
			this.size = size;
		}
		/**
		 * <p>���� ���� ��ȯ</p> 
		 * ���� �����ʿ� ������ ���ڿ����� ��ġ�Ǵ� ������ ��Ÿ��
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
		 * <p>���� ���� ����</p> 
		 * ���� �����ʿ� ������ ���ڿ����� ��ġ�Ǵ� ������ ������
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
		 * �� ������ ä��� �� ������ ��ȯ
		 * @return ǥ-20 �׵θ��� ���� ������
		 */
		public UInt8 getFillStyleId() {
			return fillStyleId;
		}
		/**
		 * �� ������ ä��� �� ������ ����
		 * @param fillStyleId ǥ-20 �׵θ��� ���� ������
		 */
		public void setFillStyleId(UInt8 fillStyleId) {
			this.fillStyleId = fillStyleId;
		}
		/**
		 * 8��Ʈ ä��� ���� 2����Ʈ ��ȯ
		 * @return 2����Ʈ 0x00 0x00
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
