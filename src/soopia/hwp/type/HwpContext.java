package soopia.hwp.type;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import soopia.hwp.Constant;
import soopia.hwp.codec.IDecoder;
import soopia.hwp.type.record.StyleRecord;
import soopia.hwp.type.stream.DocInfoStream;
import soopia.hwp.type.stream.FileHeaderInfo;
import soopia.hwp.type.stream.PreviewImageInfo;
import soopia.hwp.type.stream.PreviewTextInfo;
import soopia.hwp.type.stream.RecordHeader;
import soopia.hwp.type.stream.SummaryInfo;
/**
 * 본 제품은 한글과컴퓨터의 한글 문서 파일(.hwp) 공개 문서를 참고하여 개발하였습니다.
 * 
 * @author chmin
 *
 */
public class HwpContext {
	private DocInfoStream docInfo ;
	private FileHeaderInfo fileHeader ;
	private ArrayList<IStreamStruct> sections;
	private SummaryInfo summary;
	private ArrayList<IStreamStruct> binDatas = new ArrayList<>();
	private PreviewTextInfo previewText;
	private PreviewImageInfo previewImage;
	
	private ArrayList<IStreamStruct> docOptions;
	
	public HwpContext (){
		;
	}
	public IDataType getFileHeaderInfo(){
		return fileHeader;
	}
	
	public void setFileHeader(FileHeaderInfo headerInfo) {
		this.fileHeader = headerInfo;
	}

	public IDataType getDocInfo() {
		return docInfo;
	}

	public void setDocInfo(DocInfoStream docInfo) {
		this.docInfo = docInfo;
	}

	public IDataType getSummary() {
		return summary;
	}

	public void setSummary(IDataType summary) {
		this.summary = (SummaryInfo) summary;
	}

	public IDataType getPreviewText() {
		return previewText;
	}

	public void setPreviewText(IDataType previewText) {
		this.previewText = (PreviewTextInfo) previewText;
	}

	public IDataType getPreviewImage() {
		return previewImage;
	}

	public void setPreviewImage(IDataType previewImage) {
		this.previewImage = (PreviewImageInfo) previewImage;
	}
	
	public void addSection (IStreamStruct sectionStructure){
		if ( this.sections == null) this.sections = new ArrayList<IStreamStruct>();
		sections.add(sectionStructure);
	}
	
	public void addBinaryData ( IStreamStruct binaryDataStructure){
		if ( this.binDatas == null) this.binDatas = new ArrayList<>();
		this.binDatas.add(binaryDataStructure);
	}
	
	public void addDocOption (IStreamStruct docOption){
		if ( this.docOptions == null) this.docOptions = new ArrayList<>();
		this.docOptions.add(docOption);
	}

	public List<IStreamStruct> getSections() {
		return this.sections;
	}

	public List<IStreamStruct> getBinaryData() {
		return this.binDatas;
	}
	
	public IDecoder<? extends IRecordStructure> getDecoder(Class<? extends IDataType> recordClass) {
		String version = ((FileHeaderInfo)this.fileHeader).getVersionString();
		Repository rep = Repository.getInstance();
		IDecoder<? extends IRecordStructure> decoder = rep.getDecoder(recordClass);
		return decoder;
	}	
	
	public IRecordStructure createRecordStructure(RecordHeader header,
			IStreamStruct stream) throws StructCreationException {
		int tagID = header.getTagID();
		Repository rep = Repository.getInstance();
		Class<? extends IRecordStructure> cls = rep.getRecordStructClass(tagID);
		
		IRecordStructure rs;
		try {
			Constructor<?> construct = cls.getConstructor(
					RecordHeader.class,
					IStreamStruct.class);
			rs = (IRecordStructure) construct.newInstance(header, stream);
			return rs;
		} catch ( NoSuchMethodException nsmE){
			throw new StructCreationException("no such method", nsmE);
		} catch ( InvocationTargetException itE){
			throw new StructCreationException("invocation target problem", itE);
		} catch ( IllegalAccessException iaE){
			throw new StructCreationException("Illegal Access", iaE);
		} catch (InstantiationException istE){
			throw new StructCreationException("fail to instantiate it", istE);
		}
	}
	
	public int getStyleSize() {
		Iterator<IRecordStructure> it = docInfo.recordIterator();
		int size = 0;
		while ( it.hasNext() ) {
			IRecordStructure record = it.next();
			if ( record.getTagName().equals(Constant.STYLE) ) {
				size ++;
			}
		}
		return size;
	}
	public StyleRecord getStyleAt(int index) {
		return docInfo.getStyleAt(index);
	}
}
