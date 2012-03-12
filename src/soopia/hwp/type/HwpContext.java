package soopia.hwp.type;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import soopia.hwp.codec.DocPropRecordDecoder;
import soopia.hwp.codec.IDecoder;
import soopia.hwp.type.record.DocPropertyRecord;
import soopia.hwp.type.stream.DocInfoStream;
import soopia.hwp.type.stream.FileHeaderInfo;
import soopia.hwp.type.stream.PreviewImageInfo;
import soopia.hwp.type.stream.PreviewTextInfo;
import soopia.hwp.type.stream.RecordHeader;
import soopia.hwp.type.stream.SummaryInfo;

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
	
	public IDecoder<?> getDecoder(Class<? extends IDataType> recordClass) {
		String version = ((FileHeaderInfo)this.fileHeader).getVersionString();
		Repository rep = Repository.getInstance();
		IDecoder<? extends IRecordStructure> decoder = rep.getDecoder(recordClass);
		return decoder;
	}	
	
	public IRecordStructure createRecordStructure(RecordHeader header,
			IStreamStruct stream, int pos) throws StructCreationException {
		int tagID = header.getTagID();
		Repository rep = Repository.getInstance();
		Class<? extends IRecordStructure> cls = rep.getRecordStructClass(tagID);
		
		IRecordStructure rs;
		try {
			Constructor<?> construct = cls.getConstructor(
					RecordHeader.class,
					IStreamStruct.class,
					int.class);
			rs = (IRecordStructure) construct.newInstance(header, stream, pos);
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
}
