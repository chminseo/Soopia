package soopia.hwp.type;

import java.nio.ByteBuffer;

/**
 * 본 제품은 한글과컴퓨터의 한글 문서 파일(.hwp) 공개 문서를 참고하여 개발하였습니다.
 * 
 * HWP 5.0 포맷 문서에서 "데이터 레코드"에 대한 자료구조
 * 
 * @author chmin
 * @page p.14
 */
public interface IRecordStructure extends IDataType {
	public static final int BIT_MASK_10 = 0x3ff;
	public static final int BIT_MASK_12 = 0xfff;

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
	
	public int getLevel();
	/**
	 * HWP 5.0 포맷 문서에서 Tag ID 로 언급되는 문자열을 반환.
	 * 
	 */
	public String getTagName();
	/**
	 * HWP 5.0 포맷 문서에서 HWPTAG_BEGIN + n 으로 언급되는 정수값을 반환.
	 */
	public Integer getTagValue();
	
	public IStreamStruct getParentStream();
}