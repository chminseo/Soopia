package soopia.hwp.type;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * 본 제품은 한글과컴퓨터의 한글 문서 파일(.hwp) 공개 문서를 참고하여 개발하였습니다.
 * 
 * 한글 문서에서 Stream을 나타낸다. ( 표 2, p7)
 * 
 * FileHeader, DocInfo 와 같이 Window Compound File 구조에서 Stream 에 해당하는 영역.
 * 한글 파일은 StreamSturucture 들로 표현된다.
 * 
 * @author yeori
 *
 */
public abstract class AbstactStream implements IStreamStruct {

	private String structureName;
	
	private int offset ;
	private ByteBuffer data;
	
	private HwpContext context ;

	protected ArrayList<IRecordStructure> records;
	protected AbstactStream(HwpContext context, String structureName, ByteBuffer data ){
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
		return data.capacity();
	}

	@Override
	public ByteBuffer getBuffer() {
		return this.data;
	}
	
	@Override
	public byte[] getBytes() {
		byte [] b = new byte[data.capacity()];
		this.data.clear();
		this.data.get(b);
		return b;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + offset;
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
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (offset != other.offset)
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
				+ structureName + ", offset=" + offset + ", data-size=" + data.capacity() + "]";
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
