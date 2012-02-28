package soopia.hwp.hexdump.model;

import java.util.List;

import soopia.hwp.structure.IDataStructure;

public class FileModelEvent {
	private String filePath ;
	private List<IDataStructure> ds;
	public FileModelEvent(String filePath, List<IDataStructure> dsList) {
		this.filePath = filePath;
		this.ds = dsList;
	}
	public String getFilePath() {
		return filePath;
	}
	public List<IDataStructure> getDataStructureList() {
		return ds;
	}
	
}
