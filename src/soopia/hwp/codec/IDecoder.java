package soopia.hwp.codec;


import soopia.hwp.type.HwpContext;
import soopia.hwp.type.IDataType;
import soopia.hwp.util.IByteSource;
/**
 * �� ��ǰ�� �ѱ۰���ǻ���� �ѱ� ���� ����(.hwp) ���� ������ �����Ͽ� �����Ͽ����ϴ�.
 * 
 * @author chmin
 *
 * @param <T>
 */
public interface IDecoder<T extends IDataType> {
	public T decode (T target,  IByteSource data, HwpContext context) throws DecodingException ;
	public boolean isAvailable(String versionString);
}
