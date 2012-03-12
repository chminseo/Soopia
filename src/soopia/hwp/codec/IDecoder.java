package soopia.hwp.codec;

import java.nio.ByteBuffer;

import soopia.hwp.type.HwpContext;
import soopia.hwp.type.IDataType;

public interface IDecoder<T extends IDataType> {
	public T decode (T target,  ByteBuffer data, HwpContext context) throws DecodingException ;
	public boolean isAvailable(String versionString);
}
