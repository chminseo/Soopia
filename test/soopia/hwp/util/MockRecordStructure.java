package soopia.hwp.util;

import java.nio.ByteBuffer;

import soopia.hwp.Constant;
import soopia.hwp.type.HwpContext;
import soopia.hwp.type.IRecordStructure;
import soopia.hwp.type.IStreamStruct;
/**
 * 본 제품은 한글과컴퓨터의 한글 문서 파일(.hwp) 공개 문서를 참고하여 개발하였습니다.
 * 
 * @author chmin
 *
 */
public class MockRecordStructure implements IRecordStructure {

	private byte[] buffer;

	public MockRecordStructure(byte[] paraTextBytes) {
		this.buffer = paraTextBytes;
	}
//	@Override
//	public String getFilePath() {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public String getStrucureName() {
		// TODO Auto-generated method stub;
		return null;
	}

	@Override
	public long getLength() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getOffset() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ByteBuffer getBuffer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] getBytes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getHeaderLength() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ByteBuffer getHeaderBuffer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] getHeaderBytes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getDataLength() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ByteBuffer getDataBuffer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] getDataBytes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTagName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getTagValue() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int getLevel() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public HwpContext getHwpContext() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IStreamStruct getParentStream() {
		// TODO Auto-generated method stub
		return null;
	}

}
