package soopia.hwp.type;


import soopia.hwp.codec.DecodingException;
import soopia.hwp.codec.IDecoder;
import soopia.hwp.util.IByteSource;
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
			IByteSource data, HwpContext context) throws DecodingException {
		return target;
	}
	
	@Override
	public boolean isAvailable(String versionString) {
		return true;
	}

}
