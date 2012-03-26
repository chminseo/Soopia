package soopia.hwp.codec;


import soopia.hwp.Constant;
import soopia.hwp.type.Dword;
import soopia.hwp.type.HwpContext;
import soopia.hwp.type.IRecordStructure;
import soopia.hwp.type.StructCreationException;
import soopia.hwp.type.stream.DocInfoStream;
import soopia.hwp.type.stream.RecordHeader;
import soopia.hwp.util.ByteArraySource;
import soopia.hwp.util.IByteSource;
/**
 * �� ��ǰ�� �ѱ۰���ǻ���� �ѱ� ���� ����(.hwp) ���� ������ �����Ͽ� �����Ͽ����ϴ�.
 * 
 * @author chmin
 *
 */
public class DocInfoDecoder implements IDecoder<DocInfoStream> {

	@Override 
	public boolean isAvailable(String versionString) {
		return true;
	};
	
	@Override
	public DocInfoStream decode(DocInfoStream stream, IByteSource data, HwpContext context)
			throws DecodingException {

//		int pos = 0;
		RecordHeader header;
		if ( stream == null){
			stream = new DocInfoStream(context, data.jump(0).consume(data.capacity()));
			data.jump(0);
		}
		
		IRecordStructure record = null;
		IDecoder<IRecordStructure>decoder = null ;
		
		do {
			
			header = new RecordHeader();
			header.baseHeader = new Dword(data.mark().consume(4));// new Dword(data, pos);
			if ( header.getDataSize() >= 0xfff){/* 4095 ����Ʈ �ʰ� */
				header.extHeader = new Dword(data.consume(4));//new Dword(data, pos + header.getHeaderSize());
			}
			data.rollback();
			try {
				record = context.createRecordStructure(header, stream);
				decoder = (IDecoder<IRecordStructure>) context.getDecoder(record.getClass());
				byte [] recordBuf = data.consume((int)record.getLength());
				ByteArraySource bas = new ByteArraySource(recordBuf);
				record = decoder.decode(record, bas, context);
				stream.addRecord(record);
//				pos += record.getLength();
			} catch (StructCreationException e1) {
				e1.printStackTrace();
			} catch (NullPointerException npE) {
				System.out.println("not implemented RecordStructure : " + Constant.TAGNAMES[header.getTagID()-0x10]);
			}
		} while ( data.remaining() > 0 );
		return stream;
	}
	
	private int asTagID(Dword header){
		return header.getValue().intValue() & IRecordStructure.BIT_MASK_10;
	}
	

}
