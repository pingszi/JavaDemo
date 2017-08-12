package indi.pings.JavaDemo.javase.io;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.*;

/**
 * @Description: 对映射文件部分加锁
 * @author ping 
 * @date 2014年9月3日
 * @version V1.0
 */
public class LockingMappedFiles {

	static final int LENGTH = 0x8FFFFFF; //**128M
	static FileChannel fc;
	
	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {	
		fc = new RandomAccessFile("e:/bigFile.txt", "rw").getChannel();
		MappedByteBuffer out = fc.map(FileChannel.MapMode.READ_WRITE, 0, LENGTH);
		for(int i = 0; i < LENGTH; i++){
			out.put((byte)'x');
		}
		new LockAndModify(out , 0, 0 + LENGTH/3);
		new LockAndModify(out , LENGTH/2, LENGTH/2 + LENGTH/4);
	}
	
	private static class LockAndModify extends Thread{
		private ByteBuffer buff;
		private int start, end;
		
		LockAndModify(ByteBuffer mbb, int start, int end){
			this.start = start;
			this.end = end;
			mbb.limit(end);
			mbb.position(start);
			buff = mbb.slice();
			this.start();
		}

		@Override
		public void run() {
			try{
				FileLock fl = fc.lock(start, end, false);
				System.out.println("Locked: " + start + " to " + end);
				while(buff.position() < buff.limit() - 1){
					buff.put((byte)(buff.get() + 1));
				}
				fl.release();
				System.out.println("Release: " + start + " to " + end);
			}catch(IOException e){
				throw new RuntimeException(e);
			}
		}
		
	}
}
