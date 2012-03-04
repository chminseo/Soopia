package soopia.hwp.type.stream;

import java.nio.ByteBuffer;

import soopia.hwp.type.AbstactStream;
import soopia.hwp.type.HwpContext;
import soopia.hwp.util.Converter;

public class FileHeaderInfo extends AbstactStream {
	
	final public static int MASK_COMPRESS = 0x1;
	final public static int MASK_PASSWORD = 0x1 << 1;
	final public static int MASK_DISTRIBUTION = 0x1 << 2;
	
	private String version;
	private boolean compressed ;
	private boolean password;
	private boolean distribution ;
	private boolean scriptEmbedded;
	
	public FileHeaderInfo(HwpContext context, ByteBuffer data) {
		super(context, "FileHeader", data);
		
		/* signature */
		data.position(32);
		/* version */
		int val = Converter.getInt(data);
		version = String.valueOf( (val >> 24) & 0xff);
		version += "." + String.valueOf( (val >> 16) & 0xff);
		version += "." + String.valueOf( (val >>  8) & 0xff );
		version += "." + String.valueOf( (val >>  0) & 0xff );
		
		int prop = Converter.getInt(data);
		compressed = (prop & MASK_COMPRESS) > 0 ? true : false;
		password = (prop & MASK_PASSWORD ) > 0 ? true : false;
		distribution = (prop & MASK_DISTRIBUTION ) > 1 ? true : false;
		
	}

	public String getVersionString() {
		
		return this.version;
	}

	public boolean isCompressed() {
		return compressed;
	}

	public boolean isPassword() {
		return password;
	}

	public boolean isDistribution() {
		return distribution;
	}

	public boolean isScriptEmbedded() {
		return scriptEmbedded;
	}
	
	

}
