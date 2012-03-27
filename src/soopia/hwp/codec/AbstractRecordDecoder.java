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
		 * 현재 읽기 전용으로 개발 중이므로 encoder 는 나중에 개발하도록 한다.
		 * 바이트 배열 출력을 위해서 임시로 저장해둠.
		 */
		if ( target instanceof AbstractRecord){
			((AbstractRecord)target).setBytes(data.mark().consumeAll());
			data.rollback();
		}
		return target;
	}
	
}
