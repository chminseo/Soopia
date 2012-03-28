package soopia.hwp.type;


import soopia.hwp.codec.AbstractRecordDecoder;
import soopia.hwp.codec.DecodingException;
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
public class MockDecoder extends AbstractRecordDecoder<NotImplementedRecord> {

	@Override
	public NotImplementedRecord decode(NotImplementedRecord target,
			IByteSource data, HwpContext context) throws DecodingException {
		super.decode(target, data, context);
		return target;
	}
	
	@Override
	public boolean isAvailable(String versionString) {
		return true;
	}

}
