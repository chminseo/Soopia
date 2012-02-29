package soopia.hwp.structure;

import java.nio.ByteBuffer;
/**
 * Record structure consists of header(DWORD) and data.
 * 
 *    [31        20][19    10][9        0]
 *    +--------+--------+--------+--------+-----------------
 *    |SSSSSSSS|SSSSLLLL|LLLLLLTT|TTTTTTTT|   DATA
 *    +--------+--------+--------+--------+-----------------
 *    [    SIZE    ][ LEVEL  ][  TAG ID  ]
 *    
 *    Size = 0xFFF �̸� header �ٷ� �ڿ� UINT32 �ڷ����� �ϳ� �� �߰��Ǿ DATA�� ���̸� ��Ÿ����.
 * @author chmin
 *
 */
public class RecordStructure implements IDataStructure {

	@Override
	public String getFilePath() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getStrucureName() {
		// TODO Auto-generated method stub
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

}
