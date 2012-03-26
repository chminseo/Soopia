package soopia.hwp.type;

import java.nio.ByteBuffer;
/**
 * 본 제품은 한글과컴퓨터의 한글 문서 파일(.hwp) 공개 문서를 참고하여 개발하였습니다.
 * 
 * @author chmin
 *
 */
public interface IDataType {
	
	public HwpContext getHwpContext();
	
	public String getStrucureName ();
	/**
	 * returns the number of bytes
	 * @return number of bytes
	 */
	public long getLength();
	
	public int getOffset();
	
//	public ByteBuffer getBuffer();
	
	public byte [] getBytes();
	
}