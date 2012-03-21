package soopia.hwp.codec;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

import soopia.hwp.type.HwpContext;
import soopia.hwp.type.IStreamStruct;
import soopia.hwp.type.stream.FileHeaderInfo;
import soopia.hwp.util.Converter;
/**
 * 본 제품은 한글과컴퓨터의 한글 문서 파일(.hwp) 공개 문서를 참고하여 개발하였습니다.
 * 
 * @author chmin
 *
 */
public class FileHeaderDecoder implements IDecoder<FileHeaderInfo> {
	
	@Override 
	public boolean isAvailable(String versionString) {
		return true;
	};

	@Override
	public FileHeaderInfo decode(FileHeaderInfo fileHeader, ByteBuffer data, HwpContext context) 
			throws DecodingException{
		if ( fileHeader == null){
			fileHeader = new FileHeaderInfo(context, data);
		}
		/* signature : skip */
		byte [] signature = new byte [32];
		data.get(signature);
		checkValidSignatue(signature);

		/* version */
		int val = Converter.getInt(data);
		String version = String.valueOf( (val >> 24) & 0xff);
		version += "." + String.valueOf( (val >> 16) & 0xff);
		version += "." + String.valueOf( (val >>  8) & 0xff );
		version += "." + String.valueOf( (val >>  0) & 0xff );
		fileHeader.setVersionString(version);
		
		int prop = Converter.getInt(data);
		fileHeader.setCompressed((prop & FileHeaderInfo.MASK_COMPRESS) > 0 ? true : false);
		fileHeader.setPassword((prop & FileHeaderInfo.MASK_PASSWORD ) > 0 ? true : false);
		fileHeader.setDistribution((prop & FileHeaderInfo.MASK_DISTRIBUTION ) > 1 ? true : false);
		fileHeader.setScriptEmbedded((prop & FileHeaderInfo.MASK_SCRIPT_EMBEDED ) > 1 ? true : false );
		// TODO 미구현된 속성 
		//      - DRM 보안 문서 여부
		//      - XML TEMPLATE 스토리지 존재 여부
		//      - 문서 이력 관리 존재 여부
		//      - 전자 서명 정보 존재 여부
		//      - 공인인증서 암호화 여부
		//      - 전자 서명 예비 저장 여부
		//      - 공인 인증서 DRM 보안 문서 여부
		//      - CCL 문서 여부
		return fileHeader;
	}

	private void checkValidSignatue(byte [] data) throws DecodingException {
		try {
			"HWP Document File".equals (new String(data, "UTF-16LE").trim());
		} catch (UnsupportedEncodingException e) {
			throw new DecodingException(" UTF-16LE is not supoorted at this system");
		}
	}

}
