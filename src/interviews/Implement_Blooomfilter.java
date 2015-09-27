package interviews;

import java.util.List;

public class Implement_Blooomfilter {
	/**
	 * The hash functions used in a Bloom filter should be independent and
	 * uniformly distributed.
	 * 
	 * A larger filter will have less false positives, and a smaller one more.
	 * 
	 * Your false positive rate will be approximately (1-e^(-kn/m))^k, so you can
	 * just plug the number n of elements you expect to insert, and try various
	 * values of k and m to configure your filter for your application.2
	 */
	@SuppressWarnings("unused")
	private class BloomFilter {
		public int size;
		public int charBitSize = 8;
		public char[] table;
		public List<HashFunction> hashFunc;
		
		public BloomFilter (int size, List<HashFunction> hashs) {
			this.table = new char[size];
			this.hashFunc = hashs;
			this.size = size;
		}
		 
		public boolean contains(char k) {
			int funcNum = this.hashFunc.size();
			long code = 0;
			for (int i = 0; i < funcNum; i++) {
				code = hashFunc.get(i).getHashValue(k);
				if (code / charBitSize > size) {
					return false;
				} else {
					if (getBit(code) == 1) continue;
					else return false;
				}
			}
			return true;
		}
		public void put(char k) {
			int funcNum = this.hashFunc.size();
			long code = 0;
			for (int i = 0; i < funcNum; i++) {
				code = hashFunc.get(i).getHashValue(k);
				// 具体要看怎么hash
				if (code/charBitSize > size) {
					return;
				} else {
					setBit(code);
				}
			}
		}
		public void remove(char k) {
			// normally bloomfilter cannot remove
			// since different elements might be hashed to same position
			
		}
		
		
		public void setBit(long code) {
			table[(int) (code / charBitSize)] |= (1 << (code % charBitSize));
		}
		public int getBit(long code) {
			if ((table[(int)(code / charBitSize)] & (1 << (code % charBitSize))) == 0) {
				return 0;
			}
			return 1;
		}
	}
}
class HashFunction {
	public int getHashValue(char k) {
		return 0;
	}
}