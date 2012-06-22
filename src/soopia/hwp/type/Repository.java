package soopia.hwp.type;

import java.util.HashMap;

import soopia.hwp.Constant;
import soopia.hwp.codec.BinDataRecordDecoder;
import soopia.hwp.codec.BorderFillRecordDecoder;
import soopia.hwp.codec.CharShapeRecordDecoder;
import soopia.hwp.codec.DocPropRecordDecoder;
import soopia.hwp.codec.FaceNameDecoder;
import soopia.hwp.codec.IDecoder;
import soopia.hwp.codec.IdMappingRecordDecoder;
import soopia.hwp.codec.NumberingRecordDecoder;
import soopia.hwp.codec.ParaShapeRecordDecoder;
import soopia.hwp.codec.TabRecordDecoder;
import soopia.hwp.type.record.BinDataRecord;
import soopia.hwp.type.record.BorderFillRecord;
import soopia.hwp.type.record.CharShapeRecord;
import soopia.hwp.type.record.DocPropertyRecord;
import soopia.hwp.type.record.FaceNameRecord;
import soopia.hwp.type.record.IDMappingsRecord;
import soopia.hwp.type.record.NumberingRecord;
import soopia.hwp.type.record.ParaShapeRecord;
import soopia.hwp.type.record.TabRecord;
/**
 * 본 제품은 한글과컴퓨터의 한글 문서 파일(.hwp) 공개 문서를 참고하여 개발하였습니다.
 * 
 * @author chmin
 *
 */
public class Repository {
	
	/*
	 * mapping <tagID, RecordStructure class>
	 */
	private HashMap<Integer, Class<? extends IRecordStructure>> recordStructMap = new HashMap<>();
	/*
	 * mapping <RecordStructure class, Record Decoder>
	 */
	private HashMap<Class<?extends IRecordStructure>, IDecoder<? extends IRecordStructure>> decoderMap = new HashMap<>();
	/*
	 * mock decoder for records structures not implmented .
	 */
	private static IDecoder<? extends IRecordStructure> mock = new MockDecoder();
	
	private Repository() {}
	
	public Class <? extends IRecordStructure > getRecordStructClass(Integer tagID){
		return  recordStructMap.containsKey(tagID) ?
			recordStructMap.get(tagID) :
				NotImplementedRecord.class;
	}

	public IDecoder<? extends IRecordStructure> getDecoder ( Class<?> recordClass){
		IDecoder<? extends IRecordStructure> decoder = 
				(IDecoder<? extends IRecordStructure>) decoderMap.get(recordClass);
		
		return decoder == null ? mock : decoder ;
	}
	
	final private static Repository rep = new Repository();
	static {
		rep.installDefault();
	}
	public static Repository getInstance(){
		return rep;
	}
	
	private void installDecoder(){
		decoderMap.put(DocPropertyRecord.class, new DocPropRecordDecoder());
		decoderMap.put(IDMappingsRecord.class, new IdMappingRecordDecoder());		
		decoderMap.put(BinDataRecord.class, new BinDataRecordDecoder());
		decoderMap.put(FaceNameRecord.class, new FaceNameDecoder());
		decoderMap.put(BorderFillRecord.class, new BorderFillRecordDecoder());
		decoderMap.put(CharShapeRecord.class, new CharShapeRecordDecoder());
		decoderMap.put(TabRecord.class, new TabRecordDecoder());
		decoderMap.put(NumberingRecord.class, new NumberingRecordDecoder());
		// TODO BulletRecordDecoder 자리
		decoderMap.put(ParaShapeRecord.class, new ParaShapeRecordDecoder());
	}
	public void installDefault(){
		String [] tagNames = Constant.TAGNAMES;
		int idx = 0;
		recordStructMap.put(Constant.HWPTAG_BEGIN + idx, DocPropertyRecord.class);
		recordStructMap.put(Constant.HWPTAG_BEGIN + (++idx), IDMappingsRecord.class);
		recordStructMap.put(Constant.HWPTAG_BEGIN + (++idx), BinDataRecord.class);
		recordStructMap.put(Constant.HWPTAG_BEGIN + (++idx), FaceNameRecord.class);
		recordStructMap.put(Constant.HWPTAG_BEGIN + (++idx), BorderFillRecord.class);
		recordStructMap.put(Constant.HWPTAG_BEGIN + (++idx), CharShapeRecord.class);
		recordStructMap.put(Constant.HWPTAG_BEGIN + (++idx), TabRecord.class);
		recordStructMap.put(Constant.HWPTAG_BEGIN + (++idx), NumberingRecord.class);
		++idx; // TODO Bullet Record 구현 안됨.
		recordStructMap.put(Constant.HWPTAG_BEGIN + (++idx), ParaShapeRecord.class);
		
		installDecoder();
		
		for( ++idx ; idx < tagNames.length ; idx++){
			recordStructMap.put(Constant.HWPTAG_BEGIN + idx, NotImplementedRecord.class);
			decoderMap.put(NotImplementedRecord.class, mock);
		}
	}
}
