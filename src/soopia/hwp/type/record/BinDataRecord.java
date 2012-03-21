package soopia.hwp.type.record;

import soopia.hwp.type.AbstractRecord;
import soopia.hwp.type.IStreamStruct;
import soopia.hwp.type.stream.RecordHeader;
/**
 * �� ��ǰ�� �ѱ۰���ǻ���� �ѱ� ���� ����(.hwp) ���� ������ �����Ͽ� �����Ͽ����ϴ�.
 * 
 * 4.1.3 ���̳ʸ� ������
 * 
 * ���� ���� ���Ե� ���̳ʸ� �����͸� ��Ÿ��(�׸�, OLE, STORAGE ��)
 * 
 * @author chmin
 * @tagID HWPTAG_BIN_DATA
 * @page p.16
 *
 */
public class BinDataRecord extends AbstractRecord {
	final public static int TYPE_LINK = 0x0;
	final public static int TYPE_EMBEDDING = 0x1;
	final public static int TYPE_STORAGE = 0x2;
	
	/**
	 * ���丮���� ����Ʈ ��带 ����
	 */
	final public static int COMPRESS_DEFAULT = 0x0;
	/**
	 * ������ ������
	 */
	final public static int COMPRESS_ALWAYS = 0x1;
	/**
	 * ������ �������� ����
	 */
	final public static int COMPRESS_NEVER = 0x2;
	/**
	 * ���� access �� ���� ���� ����
	 */
	final public static int ACCESS_NONE = 0x0;
	/**
	 * access ����
	 */
	final public static int ACCESS_OK = 0x1;
	/**
	 * access ���з� ���� �߻�
	 */
	final public static int ACCESS_FAILED = 0x2;
	/**
	 * access ����, ���õ� ����
	 */
	final public static int ACCESS_IGNORED = 0x3;
	
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
	
	private int compressPolicy ;
	
	private int accessState ;
	
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

	public int getCompressPolicy() {
		// TEST not tested
		return compressPolicy;
	}

	public void setCompressPolicy(int compressPolicy) {
		// TEST not tested
		this.compressPolicy = compressPolicy;
	}

	public int getAccessState() {
		return accessState;
	}

	public void setAccessState(int accessState) {
		this.accessState = accessState;
	}
	
}
