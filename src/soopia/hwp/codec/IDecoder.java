package soopia.hwp.codec;


import soopia.hwp.type.HwpContext;
import soopia.hwp.type.IDataType;
import soopia.hwp.util.IByteSource;
/**
 * 본 제품은 한글과컴퓨터의 한글 문서 파일(.hwp) 공개 문서를 참고하여 개발하였습니다.
 * 
 * @author chmin
 *
 * @param <T>
 */
public interface IDecoder<T extends IDataType> {
	public T decode (T target,  IByteSource data, HwpContext context) throws DecodingException ;
	public boolean isAvailable(String versionString);
}
