package yyf.common.sort;

import java.util.Arrays;

public class Sort {
	public static void main(String[] args) {
		Integer[] arrs = new Integer[] { 6, 5, 9, 123, 523, 2, 46, 324, 1, 13, 2542, 4 };
		sort5(arrs);
//		sort();
		System.out.println(Arrays.asList(arrs).toString());
	}

	public static void sort1(Integer[] arrs) {
		for (int i = 0; i < arrs.length; i++) {
			for (int j = 0; j < arrs.length - i - 1; j++) {
				if (arrs[j + 1] < arrs[j]) {
					Integer temp = arrs[j + 1];
					arrs[j + 1] = arrs[j];
					arrs[j] = temp;
				}
			}
		}
	}
	public static void sort2(Integer[] arrs) {
		for (int i = 0; i < arrs.length; i++) {
			Integer minIndex = i;
			for (int j = i; j < arrs.length; j++) {
				if (arrs[minIndex] > arrs[j]) {
					minIndex = j;
				}
			}
			Integer temp = arrs[minIndex];
			arrs[minIndex] = arrs[i];
			arrs[i] = temp;
		}
	}

	public static void sort3(Integer[] arrs) {
		for(int i =0;i<arrs.length-1;i++) {
			Integer current = arrs[i+1];
			Integer pre = i;
			while(pre>=0&&arrs[pre]>current) {
				arrs[pre+1] = arrs[pre];
				pre--;
			}
			arrs[pre+1] = current;
		}
	}

	public static void sort4(Integer[] arrs) {
		Integer gap = arrs.length / 2;
		while (gap > 0) {
			for (int i = gap; i < arrs.length; i++) {
				Integer ahead = i - gap;
				Integer later = i;
				while (ahead >= 0 && arrs[later] < arrs[ahead]) {
					Integer temp = arrs[later];
					arrs[later] = arrs[ahead];
					arrs[ahead] = temp;
					later = ahead;
					ahead -= gap;
				}
			}
			gap = gap / 2;
		}
	}
	
	public static Integer[] sort5(Integer[] arrs) {
		if(arrs.length>2) {
			Integer min = arrs.length/2;
			Integer[] left = Arrays.copyOfRange(arrs, 0, min);
			Integer[] right = Arrays.copyOfRange(arrs, min, arrs.length);
			left = sort5(left);
			right = sort5(right);
			Integer[] array = marge(left,right);
			System.out.println(Arrays.asList(array).toString());
			return null;
		}else if(arrs.length==2) {
			Integer temp = arrs[0];
			Integer temp2 = arrs[1];
			if(temp>temp2) {
				Integer ex = temp;
				arrs[0] = temp2;
				arrs[1] = ex;
			}
		}
		return null;
	}

	private static Integer[] marge(Integer[] left, Integer[] right) {
		Integer[] arrs = new Integer[left.length+right.length];
		for (int index = 0,i=0,j=0;index<arrs.length;index++) {
			if(i>=left.length) {
				arrs[index] = right[j++];
			}else if(j>=right.length) {
				arrs[index] = left[i++];
			}else if(left[i]>right[j]) {
				arrs[index] = right[j++];
			}else {
				arrs[index] = left[i++];
			}
		}
		return arrs;
	}
}
