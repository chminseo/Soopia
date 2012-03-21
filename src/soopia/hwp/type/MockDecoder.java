package soopia.hwp.type;

import java.nio.ByteBuffer;

import soopia.hwp.codec.DecodingException;
import soopia.hwp.codec.IDecoder;
/**
 * �� ��ǰ�� �ѱ۰���ǻ���� �ѱ� ���� ����(.hwp) ���� ������ �����Ͽ� �����Ͽ����ϴ�.
 * 
 * �̱����� IRecorStructure�� �����ϱ� ���ؼ� ���� mock decoder.
 * ������ ������ �������� ��.
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
