package soopia.hwp.type;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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
import soopia.hwp.util.Converter;
import soopia.hwp.util.IByteSource;
/**
 * �� ��ǰ�� �ѱ۰���ǻ���� �ѱ� ���� ����(.hwp) ���� ������ �����Ͽ� �����Ͽ����ϴ�.
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
			/* FileHeader���� ���� ó���Ѵ�. */
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
					// FIXME ���ڴ� �ȿ��� stream �ν��Ͻ��� �����ϰ� context�� ��Ͻ�Ű�� �ڵ尡 ����������.
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
	
	private static void printBytes(String name, IByteSource data) {
		data.mark();
		StringBuilder sb = new StringBuilder();
		
		System.out.println("[" + name + "]");
		while ( data.remaining() > 0 ) {
			for( int c = 0 ; c < 16 ; c ++ ) {
				if ( data.remaining() ==0 ) break;
				sb.append(Converter.toHexString(data.consume(1)[0]) + " ");
			}
			sb.append(System.getProperty("line.separator"));
		}
		
		System.out.println(sb.toString() + "\n");
		data.rollback();
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
				 * ������ ������ FileHeader�� ���� ���� �ؼ��Ǵ� ���� �����ϰ� ������
				 * ������ ������ ������ ���� �ٸ� ��Ʈ�� �����Ͱ� ���� ������ �� �ִ�.
				 * �̷� ��� ������ �۾����� FileHeader ������ �����ϴ� ������ NullPointerException�� �߻��Ѵ�.
				 * 
				 * �ϴ� ��Ʈ�� �̸����� ����Ʈ ��Ʈ���� ������ �� FileHeader���� ó���ؾ� �Ѵ�.
				 */
				buf = new ByteArraySource(IOUtils.toByteArray(dis));
				bufferMap.put(name, buf);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
