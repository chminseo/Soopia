package soopia.hwp.type;

import java.util.ArrayList;
import java.util.List;

import soopia.hwp.type.stream.FileHeaderInfo;

public class HwpContext {
	private IDataType docInfo ;
	private IDataType fileHeader ;
	private ArrayList<IStreamStruct> sections;
	private IDataType summary;
	private ArrayList<IStreamStruct> binDatas;
	private IDataType previewText;
	private IDataType previewImage;
	
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

	public void setDocInfo(IDataType docInfo) {
		this.docInfo = docInfo;
	}

	public IDataType getSummary() {
		return summary;
	}

	public void setSummary(IDataType summary) {
		this.summary = summary;
	}

	public IDataType getPreviewText() {
		return previewText;
	}

	public void setPreviewText(IDataType previewText) {
		this.previewText = previewText;
	}

	public IDataType getPreviewImage() {
		return previewImage;
	}

	public void setPreviewImage(IDataType previewImage) {
		this.previewImage = previewImage;
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

}
