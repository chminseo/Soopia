package soopia.hwp.util;
/**
 * {@link java.nio.ByteBuffer} �� ������ Ưȭ�� api �̱� ������ Ŭ���̾�Ʈ ȯ�濡�� ����Ʈ ��Ʈ����
 * �پ��� ũ��� �߶� ��ȯ�ϴ� ������ �������� �ʴ�.
 * 
 * IByteSource�� Ŭ���̾�Ʈ ȯ�濡 ������ api�� �����Ѵ�.
 * 
 * @author chmin
 *
 */
public interface IByteSource {

	/**
	 *  ������ ������ ��ġ���� size ��ŭ ����Ʈ �迭�� ���� �� ��ȯ�Ѵ�.
	 * 
	 * @param size �о ��ȯ�� ����Ʈ�� ����
	 * @return ���̰� size�� ����Ʈ �迭
	 * 
	 */
	public byte[] consume(int size);

	/**
	 *  ������ ������ ��ġ���� size ��ŭ ����Ʈ �迭�� ���� �� ��ȯ�Ѵ�.
	 *  �׸��� �����͸� ���� ��ġ���� moveCount ��ŭ ����.
	 *  
	 * @param size
	 * @param moveCount
	 * @return ���̰� size �� ����Ʈ �迭
	 */
	public byte[] consume(int size, int moveCount);

	/**
	 * ������ ������ ��ġ���� size��ŭ ����Ʈ �迭�� ���� �� ��ȯ�Ѵ�.
	 * ������ ��ġ�� �̵���Ű�� �ʴ´�.
	 * 
	 * @param size
	 * @return
	 */
	public byte[] peep(int size);

	/**
	 * ���� �������� ��ġ�� ��ȯ�Ѵ�.
	 * 
	 * @return ���� �������� ��ġ
	 */
	public int position();

	/**
	 * ������ �о���� �� �ִ� ����Ʈ�� ������ ��ȯ
	 * 
	 * @return �о���� �� �ִ� ����Ʈ ����
	 */
	public int remaining();

	/**
	 * ���� ������ ��ġ�� ǥ���ص�.
	 * 
	 * @see #rollback(int)
	 * @return
	 */
	public IByteSource mark();

	/**
	 * �����͸� mark �� ��ġ�� ���� �̵���Ŵ
	 * 
	 * @see #mark()
	 * @return �ѹ� ��  �������� ��ġ
	 * @exception ByteSourceException ǥ�õ� mark�� ���� ��� ���� �߻�
	 */
	public IByteSource rollback();

	/**
	 * �����͸� ���� ��ġ���� �־��� ���ڸ�ŭ �ڷ� �̵���Ŵ
	 * 
	 * @param count
	 * @return �̵� �� �������� ��ġ�� ��ȯ
	 */
	public IByteSource back(int count);

	/**
	 * �����͸� ������ ��ġ���� 1��ŭ ������ �̵���Ŵ
	 * 1����Ʈ�� �ǳʶڴ�.
	 * @return �ǳʶ� �� �������� ��ġ
	 */
	public IByteSource skip();

	/**
	 * �����͸� ���� ��ġ���� �־��� ���ڸ�ŭ ������ �̵���Ŵ
	 * count��ŭ�� ����Ʈ�� �ǳʶٰ� �ȴ�.
	 * 
	 * @param count �ǳʶ� ����
	 * @return �ǳʶ� �� �������� ��ġ
	 */
	public IByteSource skip(int count);

	/**
	 * �����͸� �־��� ��ġ�� �̵���Ų��.
	 * 
	 * @param loc �����Ͱ� �̵��� ���ο� ��ġ
	 */
	public IByteSource jump(int loc);

	/**
	 * ��ü ����Ʈ �迭�� ũ�⸦ ��ȯ
	 * 
	 * @return ��ü ����Ʈ �迭�� ũ��
	 */
	public int capacity();

	public byte[] consumeAll();

}