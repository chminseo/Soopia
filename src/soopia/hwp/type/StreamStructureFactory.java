package soopia.hwp.type;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.poi.poifs.eventfilesystem.POIFSReader;
import org.apache.poi.poifs.eventfilesystem.POIFSReaderEvent;
import org.apache.poi.poifs.eventfilesystem.POIFSReaderListener;
import org.apache.poi.poifs.filesystem.DocumentInputStream;
import org.apache.poi.util.IOUtils;

import soopia.hwp.codec.DecodingException;
import soopia.hwp.codec.DocInfoDecoder;
import soopia.hwp.codec.FileHeaderDecoder;
import soopia.hwp.codec.SectionInfoDecoder;
import soopia.hwp.type.stream.BinaryData;
import soopia.hwp.type.stream.LinkDocInfo;
import soopia.hwp.type.stream.PreviewImageInfo;
import soopia.hwp.type.stream.PreviewTextInfo;
import soopia.hwp.type.stream.SummaryInfo;
import soopia.hwp.util.ByteArraySource;
import soopia.hwp.util.IByteSource;
/**
 * 본 제품은 한글과컴퓨터의 한글 문서 파일(.hwp) 공개 문서를 참고하여 개발하였습니다.
 * 
 * @author chmin
 *
 */
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
			initHwpContext(ctx, handler.bufferMap);
			return ctx;
		} catch (IOException e) {
			throw e;
		}
	}
	
	private void initHwpContext(HwpContext ctx, HashMap<String, IByteSource> map){
		String name = "FileHeader";
//		ByteBuffer buf = null ;
		IByteSource buf = null;
		
		try {
			/* FileHeader부터 먼저 처리한다. */
			buf = map.get(name);
			ctx.setFileHeader(new FileHeaderDecoder().decode(null, buf, ctx));
			Iterator<String> keys = map.keySet().iterator();
			while ( keys.hasNext()){
				name = keys.next();
				buf = map.get(name);
				if ( name.toLowerCase().startsWith("section")){
					ctx.addSection(new SectionInfoDecoder().decode(null, buf, ctx));
				} else if ( name.toLowerCase().startsWith("bin")){
					ctx.addBinaryData(new BinaryData(ctx, name, buf.consumeAll()));
				} else if ( name.toLowerCase().equals("docinfo") ){
					// FIXME 디코더 안에서 stream 인스턴스를 생성하고 context에 등록시키면 코드가 간결해진다.
					ctx.setDocInfo(new DocInfoDecoder().decode(null, buf, ctx));
				} else if ( name.toLowerCase().endsWith("summaryinformation") ){
					ctx.setSummary(new SummaryInfo(ctx, name, buf.consumeAll()));
				} else if ( name.toLowerCase().equals("prvimage")){
					ctx.setPreviewImage(new PreviewImageInfo(ctx, name, buf.consumeAll()));
				} else if ( name.toLowerCase().endsWith("linkdoc")) {
					ctx.addDocOption(new LinkDocInfo(ctx, name, buf.consumeAll()));
				} else if ( name.toLowerCase().endsWith("drmlicense") ){
					;
				} else if ( name.toLowerCase().endsWith("prvtext")){
					ctx.setPreviewText(new PreviewTextInfo(ctx, name, buf.consumeAll()));
				}
			}
		} catch (DecodingException e) {
			e.printStackTrace();
		}
	}
	
	private static class ContextHandler implements POIFSReaderListener {
		InputStream is;
		HwpContext ctx;
		HashMap<String, IByteSource> bufferMap = new HashMap<>();
		public ContextHandler(InputStream is, HwpContext ctx){
			this.is = is;
			this.ctx = ctx;
		}
		@Override
		public void processPOIFSReaderEvent(POIFSReaderEvent event) {
			DocumentInputStream dis = event.getStream();
			ByteArraySource buf = null;
			String name = event.getName(); /* stream name */
			
			try {
				/*
				 * 기존의 구현은 FileHeader가 가장 먼저 해석되는 것을 가정하고 있으나
				 * 실제로 문서의 구조에 따라서 다른 스트림 데이터가 먼저 등장할 수 있다.
				 * 이럴 경우 이후의 작업에서 FileHeader 정보를 참조하는 곳에서 NullPointerException이 발생한다.
				 * 
				 * 일단 스트림 이름으로 바이트 스트림을 저장한 후 FileHeader부터 처리해야 한다.
				 */
				buf = new ByteArraySource(IOUtils.toByteArray(dis));
				bufferMap.put(name, buf);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
