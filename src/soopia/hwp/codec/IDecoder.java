package soopia.hwp.codec;

import java.nio.ByteBuffer;

import soopia.hwp.type.HwpContext;
import soopia.hwp.type.IDataType;
/**
 * 본 제품은 한글과컴퓨터의 한글 문서 파일(.hwp) 공개 문서를 참고하여 개발하였습니다.
 * 
 * @author chmin
 *
 * @param <T>
 */
public interface IDecoder<T extends IDataType> {
	public T decode (T target,  ByteBuffer data, HwpContext context) throws DecodingException ;
	public boolean isAvailable(String versionString);
}
