package soopia.hwp.type.stream;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import soopia.hwp.type.AbstactStream;
import soopia.hwp.type.HwpContext;
import soopia.hwp.type.IRecordStructure;
import soopia.hwp.type.RecordStructureFactory;

public class DocInfoStream extends AbstactStream {
//	private List<? extends IRecordStructure> records ;
	
	private static final RecordStructureFactory factory = new RecordStructureFactory();
	
	public DocInfoStream(HwpContext context, String structureName, ByteBuffer data) {
		super(context, structureName, data);
		
		records = (ArrayList<? extends IRecordStructure>) factory.createRecordStructures(this);
	}
	
//	@Override
//	@SuppressWarnings("unchecked")
//	public List<? extends IRecordStructure> getRecord(String recordType){
//		List<IRecordStructure> list = new ArrayList<>();
//		for( IRecordStructure rs : records){
//			if ( rs.getTagName().equals(recordType) )
//				list.add(rs);
//		}
//		return list.size() == 0 ? Collections.EMPTY_LIST : list;
//	}
}
