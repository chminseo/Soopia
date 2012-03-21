package soopia.hwp.type;

import java.nio.ByteBuffer;

import soopia.hwp.codec.DecodingException;
import soopia.hwp.codec.IDecoder;
/**
 * 본 제품은 한글과컴퓨터의 한글 문서 파일(.hwp) 공개 문서를 참고하여 개발하였습니다.
 * 
 * 미구현된 IRecorStructure에 대응하기 위해서 만든 mock decoder.
 * 구현이 끝나면 지워져야 함.
 * 
 * @author chmin
 *
 */
public class MockDecoder implements IDecoder<NotImplementedRecord> {

	@Override
	public NotImplementedRecord decode(NotImplementedRecord target,
			ByteBuffer data, HwpContext context) throws DecodingException {
		return target;
	}
	
	@Override
	public boolean isAvailable(String versionString) {
		return true;
	}

}
