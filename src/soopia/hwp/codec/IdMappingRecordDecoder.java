package soopia.hwp.codec;

import java.nio.ByteBuffer;

import soopia.hwp.type.HwpContext;
import soopia.hwp.type.record.IDMappingsRecord;

public class IdMappingRecordDecoder implements IDecoder<IDMappingsRecord> {

	@Override
	public IDMappingsRecord decode(IDMappingsRecord target, ByteBuffer data,
			HwpContext context) throws DecodingException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAvailable(String versionString) {
		// TODO Auto-generated method stub
		return true;
	}

}
