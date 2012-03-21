package soopia.hwp.codec;

import java.nio.ByteBuffer;

import soopia.hwp.type.AbstactStream;
import soopia.hwp.type.HwpContext;
/**
 * 본 제품은 한글과컴퓨터의 한글 문서 파일(.hwp) 공개 문서를 참고하여 개발하였습니다.
 * 
 * @author chmin
 *
 */
public class MockDocInfo extends AbstactStream {

	public MockDocInfo(HwpContext context, ByteBuffer data) {
		super(context, "MockDocInfo", data);
		// TODO Auto-generated constructor stub
	}
}