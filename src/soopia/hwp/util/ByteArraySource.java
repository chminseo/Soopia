package soopia.hwp.util;

/**
 * 
 * @author chmin
 *
 */
public class ByteArraySource implements IByteSource {
	private int pointer ;
	private int mark = -1;
	private byte [] data;
	
	public ByteArraySource (byte [] data){
		this.pointer = 0;
		this.data = data;
	}
	/* (non-Javadoc)
	 * @see soopia.hwp.util.IByteSource#consume(int)
	 */
	@Override
	public byte [] consume(int size){
		return consume(size, size);
	}
	/* (non-Javadoc)
	 * @see soopia.hwp.util.IByteSource#consume(int, int)
	 */
	@Override
	public byte [] consume(int size, int moveCount){
		// TEST
		if ( size < 0){
			throw new NegativeArraySizeException("consum(" + 
					size + ", " + 
					moveCount + "), negative array index");
		}
		return readAndShift(size, moveCount);
	}
	
	/* (non-Javadoc)
	 * @see soopia.hwp.util.IByteSource#peep(int)
	 */
	@Override
	public byte [] peep(int size){
		// TEST
		if ( size < 0){
			throw new NegativeArraySizeException("peep(" + 
					size + "), negative array index");
		}
		return readAndShift(size, 0);
	}
	protected byte [] readAndShift(int size, int shift){
		checkPointerRange(0, data.length, pointer+shift);
		
		byte [] ret = new byte[size];
		System.arraycopy(data, pointer, ret, 0, size);
		pointer += shift;
		return ret;
	}
	/* (non-Javadoc)
	 * @see soopia.hwp.util.IByteSource#position()
	 */
	@Override
	public int position(){
		return pointer;
	}
	/* (non-Javadoc)
	 * @see soopia.hwp.util.IByteSource#remaining()
	 */
	@Override
	public int remaining() {
		return data.length - pointer;
	}
	/* (non-Javadoc)
	 * @see soopia.hwp.util.IByteSource#mark()
	 */
	@Override
	public int mark() {
		// TEST
		mark = pointer;
		return mark;
	}
	/* (non-Javadoc)
	 * @see soopia.hwp.util.IByteSource#rollback()
	 */
	@Override
	public int rollback(){
		// TEST
		if ( mark < 0 ){
			throw new IllegalStateException("mark net set. mark = " + mark);
		}
		pointer = mark;
		return pointer;
	}
	
	/* (non-Javadoc)
	 * @see soopia.hwp.util.IByteSource#back(int)
	 */
	@Override
	public int back(int count){
		// TEST
		if ( count < 0 ){
			throw new IllegalArgumentException("back(" + count + 
					"), negative back count use skip(int)" );
		}
		if ( count == 0) return pointer;
		return movePointerRelative(count * -1);
	}
	
	/* (non-Javadoc)
	 * @see soopia.hwp.util.IByteSource#skip()
	 */
	@Override
	public int skip(){
		return movePointerRelative(1);
	}
	protected int movePointerRelative(int size){
		checkPointerRange(0, data.length, pointer + size);
		pointer += size;
		return pointer;
	}
	/*
	 * start <= p <= end
	 */
	private void checkPointerRange(int start, int end, int p){
		if ( p < start || p > end ){
			throw new IndexOutOfBoundsException("data range[" + start +
					"," + end +
					"], but pointer = " + p);
		}
	}
	/* (non-Javadoc)
	 * @see soopia.hwp.util.IByteSource#skip(int)
	 */
	@Override
	public int skip(int count){
		// TEST
		if ( count < 0 ){
			throw new IllegalArgumentException("skip(" + count + "), negative skip count. use back(int)" );
		}
		if ( count == 0) return pointer;
		return movePointerRelative(count);
	}
	/* (non-Javadoc)
	 * @see soopia.hwp.util.IByteSource#jump(int)
	 */
	@Override
	public void jump(int loc) {
		checkPointerRange(0, data.length, loc);
		pointer = loc;
	}
	/* (non-Javadoc)
	 * @see soopia.hwp.util.IByteSource#capacity()
	 */
	@Override
	public int capacity(){
		return data.length;
	}
}
