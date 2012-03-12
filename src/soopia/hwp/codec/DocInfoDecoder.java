package soopia.hwp.codec;

import java.nio.ByteBuffer;

import soopia.hwp.Constant;
import soopia.hwp.type.Dword;
import soopia.hwp.type.HwpContext;
import soopia.hwp.type.IRecordStructure;
import soopia.hwp.type.StructCreationException;
import soopia.hwp.type.record.DocPropertyRecord;
import soopia.hwp.type.stream.DocInfoStream;
import soopia.hwp.type.stream.RecordHeader;

public class DocInfoDecoder implements IDecoder<DocInfoStream> {

	@Override 
	public boolean isAvailable(String versionString) {
		return true;
	};
	
	@Override
	public DocInfoStream decode(DocInfoStream stream, ByteBuffer data, HwpContext context)
			throws DecodingException {

		int pos = 0;
		RecordHeader header;
		if ( stream == null){
			stream = new DocInfoStream(context, data);
		}
		
		IRecordStructure record = null;
		IDecoder<IRecordStructure>decoder = null ;
		
		do {
			
			header = new RecordHeader();
			header.baseHeader = new Dword(data, pos);
			if ( header.getDataSize() >= 0xfff){/* 4095 ����Ʈ �ʰ� */
				header.extHeader = new Dword(data, pos);
			}
			
			try {
				record = context.createRecordStructure(header, stream, pos);
				decoder = (IDecoder<IRecordStructure>) context.getDecoder(record.getClass());
				
				byte [] dataBuf = new byte[(int)header.getDataSize()];
				data.get(dataBuf);
				record = decoder.decode(record, ByteBuffer.wrap(dataBuf), context);				
				stream.addRecord(record);
				pos += record.getLength();
			} catch (StructCreationException e1) {
				e1.printStackTrace();
			} catch (NullPointerException npE) {
				System.out.println("not implemented RecordStructure : " + Constant.TAGNAMES[header.getTagID()-0x10]);
			}
		} while ( pos < stream.getLength() );
		return stream;
	}
	
	private int asTagID(Dword header){
		return header.getValue().intValue() & IRecordStructure.BIT_MASK_10;
	}
	

}