package soopia.hwp.codec;


import soopia.hwp.Constant;
import soopia.hwp.type.Dword;
import soopia.hwp.type.HwpContext;
import soopia.hwp.type.IRecordStructure;
import soopia.hwp.type.StructCreationException;
import soopia.hwp.type.stream.RecordHeader;
import soopia.hwp.type.stream.SectionStream;
import soopia.hwp.util.ByteArraySource;
import soopia.hwp.util.IByteSource;
/**
 * 본 제품은 한글과컴퓨터의 한글 문서 파일(.hwp) 공개 문서를 참고하여 개발하였습니다.
 * 
 * @author chmin
 *
 */
public class SectionInfoDecoder implements IDecoder<SectionStream> {

	@Override
	public SectionStream decode(SectionStream stream, IByteSource data,
			HwpContext context) throws DecodingException {
		RecordHeader header;
		if ( stream == null){
			stream = new SectionStream(context, data.mark().consumeAll());
			data.rollback();
		}
		
		IRecordStructure record = null;
		IDecoder<IRecordStructure>decoder = null ;
		
		do {
			
			header = new RecordHeader();
			header.baseHeader = new Dword(data.mark().consume(4));
			if ( header.getDataSize() >= 0xfff){/* 4095 바이트 초과 */
				header.extHeader = new Dword(data.consume(4));
			}
			data.rollback();
			try {
				record = context.createRecordStructure(header, stream);
				decoder = (IDecoder<IRecordStructure>) context.getDecoder(record.getClass());
				
				byte [] recordBuf = data.consume((int)record.getLength());
				ByteArraySource bas = new ByteArraySource(recordBuf);
				record = decoder.decode(record, bas, context);
				stream.addRecord(record);
			} catch (StructCreationException e1) {
				e1.printStackTrace();
			} catch (NullPointerException npE) {
				System.out.println("not implemented RecordStructure : " + Constant.TAGNAMES[header.getTagID()-0x10]);
			}
		} while (data.remaining() > 0 );
		return stream;
	}

	@Override
	public boolean isAvailable(String versionString) {
		// TODO Auto-generated method stub
		return true;
	}

}
