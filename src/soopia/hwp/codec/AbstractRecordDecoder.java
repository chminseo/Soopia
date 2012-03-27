package soopia.hwp.codec;

import soopia.hwp.type.AbstractRecord;
import soopia.hwp.type.HwpContext;
import soopia.hwp.type.IDataType;
import soopia.hwp.util.IByteSource;

public abstract class AbstractRecordDecoder< T extends IDataType> implements IDecoder<T> {

	@Override
	public T decode(T target, IByteSource data, HwpContext context)
			throws DecodingException {
		/* TODO
		 * ���� �б� �������� ���� ���̹Ƿ� encoder �� ���߿� �����ϵ��� �Ѵ�.
		 * ����Ʈ �迭 ����� ���ؼ� �ӽ÷� �����ص�.
		 */
		if ( target instanceof AbstractRecord){
			((AbstractRecord)target).setBytes(data.mark().consumeAll());
			data.rollback();
		}
		return target;
	}
	
}
