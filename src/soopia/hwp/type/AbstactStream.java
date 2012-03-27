package soopia.hwp.type;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
/**
 * �� ��ǰ�� �ѱ۰���ǻ���� �ѱ� ���� ����(.hwp) ���� ������ �����Ͽ� �����Ͽ����ϴ�.
 * 
 * �ѱ� �������� Stream�� ��Ÿ����. ( ǥ 2, p7)
 * 
 * FileHeader, DocInfo �� ���� Window Compound File �������� Stream �� �ش��ϴ� ����.
 * �ѱ� ������ StreamSturucture ��� ǥ���ȴ�.
 * 
 * @author yeori
 *
 */
public abstract class AbstactStream implements IStreamStruct {

	private String structureName;
	
	private int offset ;
//	private ByteBuffer data;
	
	private byte [] data ;
	private HwpContext context ;

	protected ArrayList<IRecordStructure> records;
	protected AbstactStream(HwpContext context, String structureName, byte [] data /*ByteBuffer data*/ ){
		this.context = context;
		this.structureName = structureName;
		this.offset = 0;
		this.data = data;
		this.records = new ArrayList<IRecordStructure>();
	}
	
	@Override
	public int getOffset(){
		return this.offset;
	}
	@Override
	public String getStrucureName() {
		return this.structureName;
	}
	
	@Override
	public long getLength() {
		return data.length;
	}
	
	@Override
	public byte[] getBytes() {
		byte [] b = new byte[data.length];
		System.arraycopy(data, 0, b, 0, b.length);
		return b;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(data);
		result = prime * result + offset;
		result = prime * result + ((records == null) ? 0 : records.hashCode());
		result = prime * result
				+ ((structureName == null) ? 0 : structureName.hashCode());
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
		AbstactStream other = (AbstactStream) obj;
		if (!Arrays.equals(data, other.data))
			return false;
		if (offset != other.offset)
			return false;
		if (records == null) {
			if (other.records != null)
				return false;
		} else if (!records.equals(other.records))
			return false;
		if (structureName == null) {
			if (other.structureName != null)
				return false;
		} else if (!structureName.equals(other.structureName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DefaultStructrue [ structureName="
				+ structureName + ", offset=" + offset + ", data-size=" + data.length + "]";
	}

	@Override
	public HwpContext getHwpContext() {
		return this.context;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<? extends IRecordStructure> getRecord(String recordType){
		List<IRecordStructure> list = new ArrayList<>();
		for( IRecordStructure rs : records){
			if ( recordType != null && rs.getTagName().equals(recordType) )
				list.add(rs);
			else if ( recordType == null)
				list.add(rs);
		}
		return list.size() == 0 ? Collections.EMPTY_LIST : list;
	}
}
