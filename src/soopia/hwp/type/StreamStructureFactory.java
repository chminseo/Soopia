package soopia.hwp.type;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.poifs.eventfilesystem.POIFSReader;
import org.apache.poi.poifs.eventfilesystem.POIFSReaderEvent;
import org.apache.poi.poifs.eventfilesystem.POIFSReaderListener;
import org.apache.poi.poifs.filesystem.DocumentInputStream;
import org.apache.poi.util.IOUtils;

import soopia.hwp.type.stream.BinaryData;
import soopia.hwp.type.stream.DocInfoStream;
import soopia.hwp.type.stream.FileHeaderInfo;
import soopia.hwp.type.stream.LinkDocInfo;
import soopia.hwp.type.stream.PreviewImageInfo;
import soopia.hwp.type.stream.PreviewTextInfo;
import soopia.hwp.type.stream.SectionStream;
import soopia.hwp.type.stream.SummaryInfo;

public class StreamStructureFactory {
	
	public HwpContext createHwpContext ( final File hwpFile) throws IOException {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(hwpFile);
			return createHwpContext(fis);
		} catch (FileNotFoundException e) {
			throw e;
		} finally {
			fis.close();
		}
	}
	
	public HwpContext createHwpContext ( InputStream is) throws IOException {
		HwpContext ctx = new HwpContext();
		POIFSReader poiReader = new POIFSReader();
		ContextHandler handler = new ContextHandler(is, ctx);
		poiReader.registerListener(handler);
		try {
			poiReader.read(is);
			return ctx;
		} catch (IOException e) {
			throw e;
		}
	}
	
	public static class ContextHandler implements POIFSReaderListener {
		InputStream is;
		HwpContext ctx;
		public ContextHandler(InputStream is, HwpContext ctx){
			this.is = is;
			this.ctx = ctx;
		}
		@Override
		public void processPOIFSReaderEvent(POIFSReaderEvent event) {
			DocumentInputStream dis = event.getStream();
			ByteBuffer buf = null;
			String name = event.getName();
			try {
				buf = ByteBuffer.wrap(IOUtils.toByteArray(dis));
				if ( name.toLowerCase().equals("fileheader") ){
					FileHeaderInfo headerInfo = new FileHeaderInfo(ctx, buf);
					ctx.setFileHeader(headerInfo);
				} else if ( name.toLowerCase().startsWith("section")){
//					IDataType stream = new SectionStream(ctx, name, buf);
					ctx.addSection(new SectionStream(ctx, name, buf));
				} else if ( name.toLowerCase().startsWith("bin")){
					ctx.addBinaryData(new BinaryData(ctx, name, buf));
				} else if ( name.toLowerCase().equals("docinfo") ){
					ctx.setDocInfo(new DocInfoStream(ctx, name, buf));
				} else if ( name.toLowerCase().endsWith("summaryinformation") ){
					ctx.setSummary(new SummaryInfo(ctx, name, buf));
				} else if ( name.toLowerCase().equals("prvimage")){
					ctx.setPreviewImage(new PreviewImageInfo(ctx, name, buf));
				} else if ( name.toLowerCase().endsWith("linkdoc")) {
					ctx.addDocOption(new LinkDocInfo(ctx, name, buf));
				} else if ( name.toLowerCase().endsWith("drmlicense") ){
					;
				} else if ( name.toLowerCase().endsWith("prvtext")){
					ctx.setPreviewText(new PreviewTextInfo(ctx, name, buf));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	
//	public List<IDataType> createDataStructure ( final File hwpFile) {
//		
//		FileInputStream fis = null;
//		try {
//			fis = new FileInputStream(hwpFile);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//		POIFSReader poiReader = new POIFSReader();
//		POIListener listener = new POIListener(hwpFile);
//		poiReader.registerListener(listener);
//		try {
//			poiReader.read(fis);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		return listener.dsList;
//	}
	
	
//	public static class POIListener implements POIFSReaderListener{
//		File hwpFile ;
//		List<IDataType> dsList ;
//		public POIListener(File hwpFile){
//			this.hwpFile = hwpFile;
//			this.dsList = new ArrayList<>();
//		}
//		@Override
//		public void processPOIFSReaderEvent(POIFSReaderEvent event) {
//			DocumentInputStream dis = event.getStream();
//			ByteBuffer buf = null;
//			String path = hwpFile.getAbsolutePath();
//			try {
//				buf = ByteBuffer.wrap(IOUtils.toByteArray(dis));
////				System.out.println(event.getName());
//				dsList.add(new StreamStructrue(event.getName(), buf));
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			
//		}
//		
//	}
}
