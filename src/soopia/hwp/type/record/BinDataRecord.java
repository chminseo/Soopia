package soopia.hwp.type.record;

import soopia.hwp.type.AbstractRecord;
import soopia.hwp.type.IStreamStruct;
import soopia.hwp.type.stream.RecordHeader;
/**
 * 4.1.3 바이너리 데이터
 * 
 * 문서 내에 포함된 바이너리 데이터를 나타냄(그림, OLE, STORAGE 등)
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
	 * 스토리지의 디폴트 모드를 따라감
	 */
	final public static int COMPRESS_DEFAULT = 0x0;
	/**
	 * 무조건 압축함
	 */
	final public static int COMPRESS_ALWAYS = 0x1;
	/**
	 * 무조건 압축하지 않음
	 */
	final public static int COMPRESS_NEVER = 0x2;
	/**
	 * 아직 access 된 적이 없는 상태
	 */
	final public static int ACCESS_NONE = 0x0;
	/**
	 * access 성공
	 */
	final public static int ACCESS_OK = 0x1;
	/**
	 * access 실패로 에러 발생
	 */
	final public static int ACCESS_FAILED = 0x2;
	/**
	 * access 실패, 무시된 상태
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
