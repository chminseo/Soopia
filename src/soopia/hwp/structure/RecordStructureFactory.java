package soopia.hwp.structure;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class RecordStructureFactory {
	public List<IRecordStructure> createRecordStructures(IDataStructure stream){
		ByteBuffer streamBuffer = stream.getBuffer();
		streamBuffer.flip();
		int offset = 0;
		ArrayList<IRecordStructure> list = new ArrayList<>();
		while ( offset < streamBuffer.capacity()){
			RecordStructure rs = new RecordStructure(stream, offset).init();
			offset += rs.getLength();
			list.add(rs);
		}
		return list;
	}
}
