package yyf.common.hash;

import java.util.Vector;

public class BloomFilter {

	public static int seed = 31;
	public static int getHashCode(String str){
	    int hash = 0;
	    for(int i = 0;i!= str.length();++i)
	    {
	        hash =  seed * hash + str.charAt(i);
	    }
	    return hash;
	}

	public static int APHash(String str) {
		int hash = 0;

		for (int i = 0; i < str.length(); i++) {
			hash ^= ((i & 1) == 0) ? ((hash << 7) ^ str.charAt(i) ^ (hash >> 3))
					: (~((hash << 11) ^ str.charAt(i) ^ (hash >> 5)));
		}

		return hash;
	}

	/*
	 * public static void main(String[] args) { int length = 50000; String
	 * str1="http://blog.csdn.net/bojie5744";
	 * System.out.println("str1 :"+str1.hashCode()+" system");
	 * System.out.println("str1 :"+getHashCode(str1)+" custom");
	 * 
	 * Map<String,String> map = new HashMap<String,String>(); for(int
	 * i=0;i!=length;++i){ String str = UUID.randomUUID().toString();
	 * map.put(getHashCode(str)+"", str); }
	 * System.out.println("冲突数为： "+(length-map.size())); }
	 */
public static void main(String[] args) {
		Vector<Integer> vec = new Vector<>(5000);
		int size = vec.capacity();
		// int size = 1;
	String str = "九月九日忆山东兄弟";
	int k1 = getHashCode(str);
	int k2 = APHash(str);
		int mark1 = Math.abs(k1 % (size / 2) - 1);
		int mark2 = size - Math.abs(k2 % (size / 2)) - 1;
		System.out.println(mark1);
		System.out.println(mark2);
}
}
