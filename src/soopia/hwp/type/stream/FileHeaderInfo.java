package soopia.hwp.type.stream;

import java.nio.ByteBuffer;

import soopia.hwp.type.AbstactStream;
import soopia.hwp.type.HwpContext;
import soopia.hwp.util.Converter;
/**
 * 본 제품은 한글과컴퓨터의 한글 문서 파일(.hwp) 공개 문서를 참고하여 개발하였습니다.
 * 
 * @author chmin
 *
 */
public class FileHeaderInfo extends AbstactStream {
	
	final public static int MASK_COMPRESS = 0x1;
	final public static int MASK_PASSWORD = 0x1 << 1;
	final public static int MASK_DISTRIBUTION = 0x1 << 2;
	public static final int MASK_SCRIPT_EMBEDED = 0x1 << 3;
	
	private String version;
	private boolean compressed ;
	private boolean password;
	private boolean distribution ;
	private boolean scriptEmbedded;
	
	public FileHeaderInfo(HwpContext context, byte [] data) {
		super(context, "FileHeader", data);
	}

	public String getVersionString() {
		
		return this.version;
	}
	
	public void setVersionString(String version) {
		this.version = version;
	}

	public boolean isCompressed() {
		return compressed;
	}
	
	public void setCompressed(boolean compressed) {
		this.compressed = compressed;
	}


	public boolean isPassword() {
		return password;
	}

	public void setPassword(boolean password) {
		this.password = password;
	}
	
	public boolean isDistribution() {
		return distribution;
	}

	public void setDistribution(boolean distribution) {
		this.distribution = distribution;
	}
	
	public boolean isScriptEmbedded() {
		return scriptEmbedded;
	}
	
	public void setScriptEmbedded(boolean scriptEmbedded) {
		this.scriptEmbedded = scriptEmbedded;
	}
	

}
