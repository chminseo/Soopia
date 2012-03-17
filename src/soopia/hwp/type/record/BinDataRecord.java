package soopia.hwp.type.record;

import soopia.hwp.type.AbstractRecord;
import soopia.hwp.type.IStreamStruct;
import soopia.hwp.type.stream.RecordHeader;

public class BinDataRecord extends AbstractRecord {
	final public static int TYPE_LINK = 0x0;
	final public static int TYPE_EMBEDDING = 0x1;
	final public static int TYPE_STORAGE = 0x2;
	
	private int dataType ;
	/*
	 * used for TYPE_LINK 
	 */
	private String absDataPath;
	/*
	 * used for TYPE_EMBEDDING
	 */
	private String relativeDataPath = "";
	/*
	 * used for TYPE_EMBEDDING
	 */
	private String binDataId;
	private String extension ;
	public BinDataRecord(RecordHeader header, IStreamStruct ds, int offset) {
		super(header, ds, offset);
	}
	
	public int getDataType() {
		return this.dataType;
	}
	public void setDataType(int bitType) {
		this.dataType = bitType;
	}
	public String getBinDataId(){
		// TODO 구현해야함
		return this.binDataId;
	}
	public void setBinDataId(String binDataId) {
		this.binDataId = binDataId;
	}

	public String getExtension() {
		return this.extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	
	public String getAbsolutePath(){
		return this.absDataPath;
	}
	public void setAbsolutePath(String path){
		this.absDataPath = path;
	}

	public String getRelativePath() {
		return this.relativeDataPath;
	}

	public void setRelativePath(String relPath) {
		this.relativeDataPath = relPath;
	}
}
