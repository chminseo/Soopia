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

import soopia.hwp.codec.DecodingException;
import soopia.hwp.codec.DocInfoDecoder;
import soopia.hwp.codec.FileHeaderDecoder;
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
					ctx.setFileHeader(new FileHeaderDecoder().decode(null, buf, ctx));
				} else if ( name.toLowerCase().startsWith("section")){
					ctx.addSection(new SectionStream(ctx, name, buf));
				} else if ( name.toLowerCase().startsWith("bin")){
					ctx.addBinaryData(new BinaryData(ctx, name, buf));
				} else if ( name.toLowerCase().equals("docinfo") ){
					// FIXME 디코더 안에서 stream 인스턴스를 생성하고 context에 등록시키면 코드가 간결해진다.
					ctx.setDocInfo(new DocInfoDecoder().decode(null, buf, ctx));
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
			} catch (DecodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
