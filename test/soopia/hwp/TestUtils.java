package soopia.hwp;

import java.nio.ByteBuffer;

import soopia.hwp.codec.MockDocInfo;
import soopia.hwp.type.Dword;
import soopia.hwp.type.HwpContext;
import soopia.hwp.type.stream.RecordHeader;
/**
 * �� ��ǰ�� �ѱ۰���ǻ���� �ѱ� ���� ����(.hwp) ���� ������ �����Ͽ� �����Ͽ����ϴ�.
 * 
 * @author chmin
 *
 */
public class TestUtils {
	public static MockDocInfo newMockDocInfo(ByteBuffer data){
		return new MockDocInfo(new HwpContext(), data );
	}

	public static RecordHeader newRecordHeader(ByteBuffer buf, int offset ) {
		RecordHeader header = new RecordHeader();
		header.baseHeader = new Dword(buf, offset );
		return header;
	}
}
