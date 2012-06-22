package soopia.hwp;

import java.nio.ByteBuffer;

import soopia.hwp.codec.MockDocInfo;
import soopia.hwp.type.Dword;
import soopia.hwp.type.HwpContext;
import soopia.hwp.type.stream.RecordHeader;
import soopia.hwp.util.IByteSource;
/**
 * �� ��ǰ�� �ѱ۰���ǻ���� �ѱ� ���� ����(.hwp) ���� ������ �����Ͽ� �����Ͽ����ϴ�.
 * 
 * @author chmin
 *
 */
public class TestUtils {
	public static MockDocInfo newMockDocInfo(byte [] data){
		return new MockDocInfo(new HwpContext(), data );
	}

//	public static RecordHeader newRecordHeader(ByteBuffer buf, int offset ) {
//		RecordHeader header = new RecordHeader();
//		header.baseHeader = new Dword(buf, offset );
//		return header;
//	}
	
	public static RecordHeader newRecordHeader(IByteSource buf) {
		RecordHeader header = new RecordHeader();
		header.baseHeader = new Dword(buf.consume(4));
		return header;
	}
}
