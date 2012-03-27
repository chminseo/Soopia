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
	
	@Override
	public byte[] consumeAll() {
		return consume(remaining());
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
	public IByteSource mark() {
		mark = pointer;
		return this;
	}
	/* (non-Javadoc)
	 * @see soopia.hwp.util.IByteSource#rollback()
	 */
	@Override
	public IByteSource rollback(){
		// TEST
		if ( mark < 0 ){
			throw new IllegalStateException("mark net set. mark = " + mark);
		}
		pointer = mark;
		return this;
	}
	
	/* (non-Javadoc)
	 * @see soopia.hwp.util.IByteSource#back(int)
	 */
	@Override
	public IByteSource back(int count){
		// TEST
		if ( count < 0 ){
			throw new IllegalArgumentException("back(" + count + 
					"), negative back count use skip(int)" );
		}
		if ( count > 0 ) movePointerRelative(count * -1);
		return this;
	}
	
	/* (non-Javadoc)
	 * @see soopia.hwp.util.IByteSource#skip()
	 */
	@Override
	public IByteSource skip(){
		movePointerRelative(1);
		return this;
	}
	protected void movePointerRelative(int size){
		checkPointerRange(0, data.length, pointer + size);
		pointer += size;
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
	public IByteSource skip(int count){
		if ( count < 0 ){
			throw new IllegalArgumentException("skip(" + count + "), negative skip count. use back(int)" );
		}
		if ( count > 0) movePointerRelative(count);
		return this;
	}
	/* (non-Javadoc)
	 * @see soopia.hwp.util.IByteSource#jump(int)
	 */
	@Override
	public IByteSource jump(int loc) {
		checkPointerRange(0, data.length, loc);
		pointer = loc;
		return this;
	}
	/* (non-Javadoc)
	 * @see soopia.hwp.util.IByteSource#capacity()
	 */
	@Override
	public int capacity(){
		return data.length;
	}
	
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("ByteArraySource[mark=" + mark + 
		", position=" + pointer + ", length=" + data.length + "]\n[ 0- p : ");
		for( int i = 0 ; i < pointer ; i++){
			sb.append(Converter.toHexString(data[i]) + " ");
		}
		sb.append("]\n[p-end : ");
		for (int i = pointer; i < data.length ; i++) {
			sb.append(Converter.toHexString(data[i]) + " ");
		}
		sb.append("]\n");
		return sb.toString();
	}
}
