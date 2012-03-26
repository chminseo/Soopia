package soopia.hwp.util;
/**
 * {@link java.nio.ByteBuffer} 는 서버에 특화된 api 이기 때문에 클라이언트 환경에서 바이트 스트림을
 * 다양한 크기로 잘라서 반환하는 데에는 적합하지 않다.
 * 
 * IByteSource는 클라이언트 환경에 적합한 api를 제공한다.
 * 
 * @author chmin
 *
 */
public interface IByteSource {

	/**
	 *  현재의 포인터 위치에서 size 만큼 바이트 배열을 읽은 후 반환한다.
	 * 
	 * @param size 읽어서 반환할 바이트의 길이
	 * @return 길이가 size인 바이트 배열
	 * 
	 */
	public byte[] consume(int size);

	/**
	 *  현재의 포인터 위치에서 size 만큼 바이트 배열을 읽은 후 반환한다.
	 *  그리고 포인터를 현재 위치에서 moveCount 만큼 전진.
	 *  
	 * @param size
	 * @param moveCount
	 * @return 길이가 size 인 바이트 배열
	 */
	public byte[] consume(int size, int moveCount);

	/**
	 * 현재의 포인터 위치에서 size만큼 바이트 배열을 읽은 후 반환한다.
	 * 포인터 위치는 이동시키지 않는다.
	 * 
	 * @param size
	 * @return
	 */
	public byte[] peep(int size);

	/**
	 * 현재 포인터의 위치를 반환한다.
	 * 
	 * @return 현재 포인터의 위치
	 */
	public int position();

	/**
	 * 앞으로 읽어들일 수 있는 바이트의 개수를 반환
	 * 
	 * @return 읽어들일 수 있는 바이트 개수
	 */
	public int remaining();

	/**
	 * 현재 포인터 위치를 표시해둠.
	 * 
	 * @see #rollback(int)
	 * @return
	 */
	public int mark();

	/**
	 * 포인터를 mark 된 위치로 강제 이동시킴
	 * 
	 * @see #mark()
	 * @return 롤백 후  포인터의 위치
	 * @exception ByteSourceException 표시된 mark가 없을 경우 예외 발생
	 */
	public int rollback();

	/**
	 * 포인터를 현재 위치에서 주어진 숫자만큼 뒤로 이동시킴
	 * 
	 * @param count
	 * @return 이동 후 포인터의 위치를 반환
	 */
	public int back(int count);

	/**
	 * 포인터를 현재의 위치에서 1만큼 앞으로 이동시킴
	 * 1바이트를 건너뛴다.
	 * @return 건너뛴 후 포인터의 위치
	 */
	public int skip();

	/**
	 * 포인터를 현재 위치에서 주어진 숫자만큼 앞으로 이동시킴
	 * count만큼의 바이트를 건너뛰게 된다.
	 * 
	 * @param count 건너뛸 숫자
	 * @return 건너뛴 후 포인터의 위치
	 */
	public int skip(int count);

	/**
	 * 포인터를 주어진 위치로 이동시킨다.
	 * 
	 * @param loc 포인터가 이동할 새로운 위치
	 */
	public void jump(int loc);

	/**
	 * 전체 바이트 배열의 크기를 반환
	 * 
	 * @return 전체 바이트 배열의 크기
	 */
	public int capacity();

}