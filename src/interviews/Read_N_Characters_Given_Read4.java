package interviews;

public class Read_N_Characters_Given_Read4 {
	/**
	 * The API: int read4(char[] buf) reads 4 characters at a time from a file.
	 * The return value is the actual number of characters read.
	 * 
	 * For example, it returns 3 if there is only 3 characters left in the file.
	 * By using the read4 API, implement the function int read(char[] buf, int
	 * n) that reads n characters from the file.
	 * 
	 * Note: The read function will only be called once for each test case.
	 * 
	 * [分析] 由于每次只能读进4个字符，而n未必是4的倍数。所以一直读到len+4>n时停止。剩下m = n-len < 4 个字符。
	 * 建立一个新的缓存remain读入4个字符，然后将其中的m个写入buff尾部。要注意在整个读入过程中的每个阶段判断是否已经读完文件。
	 * 
	 * [注意事项] 最后一次读出来的buff长度有可能等于4，所以在while循环的判断条件中，需要加上 readBytes < n
	 * System.arrayCopy:
	 * src - the source array.
	 * srcPos - starting position in the source array.
	 * dest - the destination array.
	 * destPos - starting position in the destination data.
	 * length - the number of array elements to be copied.
	 */
	public int readGreat(char[] buf, int n) {
        char[] buffer = new char[4];
        int ptr = 0;
        while (ptr < n) {
            int bufferCount = read4(buffer);
            int bufferPtr = 0;
            while (bufferPtr < bufferCount && ptr < n) {
                buf[ptr++] = buffer[bufferPtr++];
            }
            if (bufferCount < 4) break;
        }
        return ptr;
    }
	
	
	public int readn(char[] buf, int n) {
		char[] buffer = new char[4];
		int readBytes = 0;
		// check whether end of file
		boolean eof = false;
		while (!eof && readBytes < n) {
			int sz = read4(buffer);
			// we reach the end of the document
			if (sz < 4)
				eof = true;
			int bytes = Math.min(n - readBytes, sz);
			// copy the buffer array in read4 to our read array			
			for(int i = 0; i < bytes; i++){
				buf[readBytes++] = buffer[i];
			}
		}
		return readBytes;
	}

	public int read4(char[] beffer) {
		return 0;
	}

	/**
	 * II: 如果read函数可能被多次调用 [分析]
	 * 跟之前的一道题目比，这一题要复杂不少。主要是因为read函数可以调用多次以后，有可能文件中的部分内容被读出来，
	 * 但是暂时没有用到。因此需要额外的空间来缓存读出来的字符。
	 * 
	 * [注意事项] 对于几个变量的解读： 
	 * － buffer 存储从文件中读出来的字符 
	 * － offset 上一次读取之后buffer中剩下字符的偏移量
	 * － bufsize buffer中剩下字符的个数
	 */
	
	public char[] buffer = new char[4];
    public int bufferPtr = 0;
    public int bufferCount = 0;
    
    public int read(char[] buf, int n) {
        int ptr = 0;
        while(ptr < n) {
            // last read completely finished, no remaining part
            // thus begin a new read4
            if(bufferPtr == 0) {
                bufferCount = read4(buffer);
            }
            // no bytes left for read in file, run out of file
            if(bufferCount == 0) break;
            // read the bytes currently in the buffer
            while(bufferPtr < bufferCount && ptr < n) {
                buf[ptr++] = buffer[bufferPtr++];
            }
            // if finished all the bytes, restore bufferPtr to 0
            if(bufferPtr == bufferCount) bufferPtr = 0;
        }
        return ptr;
    }
	
//	private char[] buffer = new char[4];
//	int offset = 0, bufsize = 0;
//
//	/**
//	 * @param buf	Destination buffer
//	 * @param n		Maximum number of characters to read
//	 * @return 		The number of characters read
//	 */
//	public int read(char[] buf, int n) {
//		int readBytes = 0;
//		boolean eof = false;
//		while (!eof && readBytes < n) {
//			// if there is position occupied by the last time read
//			int sz = (bufsize > 0) ? bufsize : read4(buffer);
//			// we have no place or reach the end of the document
//			if (bufsize == 0 && sz < 4)
//				eof = true;
//			// count the left and copy the left to the array
//			int bytes = Math.min(n - readBytes, sz);
//			for(int i = offset; i < bytes; i++){
//				buf[readBytes++] = buffer[i];
//			}
//			offset = (offset + bytes) % 4;
//			bufsize = sz - bytes;
//		}
//		return readBytes;
//	}
}
