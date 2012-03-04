package soopia.hwp.type;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import soopia.hwp.Constant;
import soopia.hwp.type.record.DocPropertyRecord;
import soopia.hwp.util.Converter;

public class RecordStructureFactory {
	public List<? extends IRecordStructure> createRecordStructures(IStreamStruct stream){
		ByteBuffer streamBuffer = stream.getBuffer();
		streamBuffer.clear();
		int offset = 0;
		ArrayList<IRecordStructure> list = new ArrayList<>();
		while ( offset < streamBuffer.capacity()){
////			Dword header = new Dword (streamBuffer, 0);
////			String tagName = getTagName (header);
////			ICodec codec = findCodec ( tagName );
//			
////			IRecordStructure rs = codec.getDecoder().decode(streamBuffer);
////			AbstractRecord rs = new AbstractRecord(stream, offset).init();
////			offset += rs.getLength();
////			list.add(rs);
//			
			// TODO 레코드 타입을 보고 적절히 캐스팅한다.
			AbstractRecord rs = new MockRecord(stream, offset).init();
			offset += rs.getLength();
			list.add(rs);
		}
		return list;
	}
	
	public List<? extends IRecordStructure> createRS2(IDataType stream){
		ByteBuffer src = stream.getBuffer();
		src.clear();
		ArrayList<IRecordStructure> list = new ArrayList<>();
		int offset = 0;
		while ( src.hasRemaining()){
			int header = Converter.getInt(src);
			Class recordCls = findRecordClass(header);
		}
		return list;
	}
	
	static Map<Integer, Class> recordClassMap = new HashMap<>();
	
	private Class findRecordClass(int header ) {
		// TODO Auto-generated method stub
		int tagId = getTagId(header);
		
		return null;
	}

	private int getTagId(int header){
		return header & AbstractRecord.BIT_MASK_10;
	}
	private String getTagName(int header){
		return Constant.TAGNAMES[header & AbstractRecord.BIT_MASK_10 - Constant.HWPTAG_BEGIN];
	}
}
