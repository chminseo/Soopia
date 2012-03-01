package soopia.hwp.structure;

import java.nio.ByteBuffer;

/**
 * HWP 5.0 포맷 문서에서 "데이터 레코드"에 대한 자료구조
 * 
 * @author chmin
 * @page p.14
 */
public interface IRecordStructure extends IDataStructure {
	/**
	 * 데이터 레코드에서 HEADER 길이
	 * @return
	 */
	public Integer getHeaderLength();
	public ByteBuffer getHeaderBuffer();
	public byte [] getHeaderBytes();
	/**
	 * 데이터 레코드에서 DATA 길이
	 * @return
	 */
	public Integer getDataLength();
	public ByteBuffer getDataBuffer();
	public byte [] getDataBytes();
	/**
	 * HWP 5.0 포맷 문서에서 Tag ID 로 언급되는 문자열을 반환.
	 * 
	 */
	public String getTagName();
	/**
	 * HWP 5.0 포맷 문서에서 HWPTAG_BEGIN + n 으로 언급되는 정수값을 반환.
	 */
	public Integer getTagValue();
}