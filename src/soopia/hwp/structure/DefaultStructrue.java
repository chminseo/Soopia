package soopia.hwp.structure;

import java.nio.ByteBuffer;

public class DefaultStructrue implements IDataStructure {

	private String filePath;
	private String structureName;
	private ByteBuffer data;
	
	public DefaultStructrue(String filePath, String structureName, ByteBuffer data ){
		this.filePath = filePath;
		this.structureName = structureName;
		this.data = data;
	}
	@Override
	public String getFilePath() {
		return this.filePath;
	}
	@Override
	public String getStrucureName() {
		return this.structureName;
	}
	
	@Override
	public long getLength() {
		return data.position();
	}

	@Override
	public ByteBuffer getBytes() {
		return this.data;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result
				+ ((filePath == null) ? 0 : filePath.hashCode());
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
		DefaultStructrue other = (DefaultStructrue) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (filePath == null) {
			if (other.filePath != null)
				return false;
		} else if (!filePath.equals(other.filePath))
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
		return "DefaultStructrue [file=" + filePath + ", structureName="
				+ structureName + ", length=" + getLength() + "]";
	}
}
