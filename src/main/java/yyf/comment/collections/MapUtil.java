package yyf.comment.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MapUtil {
	/**
	 * map按照value降序
	 * 
	 * @param map
	 * @return
	 */
	public <K, V extends Comparable<? super V>> Map<K, V> sortByValueDesc(Map<K, V> map) {
		Map<K, V> result = new LinkedHashMap<>();

		map.entrySet().stream().sorted(Map.Entry.<K, V>comparingByValue().reversed())
				.forEachOrdered(e -> result.put(e.getKey(), e.getValue()));
		return result;
	}

	/**
	 * map按照value升序
	 * 
	 * @param map
	 * @return
	 */
	public <K, V extends Comparable<? super V>> Map<K, V> sortByValueASC(Map<K, V> map) {
		Map<K, V> result = new LinkedHashMap<>();

		map.entrySet().stream().sorted(Map.Entry.<K, V>comparingByValue())
				.forEachOrdered(e -> result.put(e.getKey(), e.getValue()));
		return result;
	}

	/**
	 * map按照key降序
	 * 
	 * @param map
	 * @return
	 */
	public <K extends Comparable<? super K>, V> Map<K, V> sortByKeyDesc(Map<K, V> map) {
		Map<K, V> result = new LinkedHashMap<>();

		map.entrySet().stream().sorted(Map.Entry.<K, V>comparingByKey().reversed())
				.forEachOrdered(e -> result.put(e.getKey(), e.getValue()));
		return result;
	}


	/**
	 * map按照key升序
	 * 
	 * @param map
	 * @return
	 */
	public <K extends Comparable<? super K>, V> Map<K, V> sortByKeyASC(Map<K, V> map) {
		Map<K, V> result = new LinkedHashMap<>();

		map.entrySet().stream().sorted(Map.Entry.<K, V>comparingByKey().reversed())
				.forEachOrdered(e -> result.put(e.getKey(), e.getValue()));
		return result;
	}

	/**
	 * 
	 * 
	 * @param oriMap
	 *            原map集合
	 * @param isRise
	 *            isRise为true升序，false为降序
	 * @return map按照key排序后的map集合
	 */
	public static Map<String, String> sortMapByKey(Map<String, String> oriMap, final boolean isRise) {
		if (oriMap == null || oriMap.isEmpty()) {
			return null;
		}

		Map<String, String> sortMap = new TreeMap<>(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				if (isRise) {
					// 升序排序
					return o1.compareTo(o2);
				} else {
					// 降序排序
					return o2.compareTo(o1);
				}
			}
		});
		sortMap.putAll(oriMap);
		return sortMap;
	}

	/**
	 * 
	 * 
	 * @param oriMap
	 *            原map集合
	 * @param isRise
	 *            isRise为true升序，false为降序
	 * @return map按照value排序后的集合
	 */
	public static Map<String, String> sortMapByValue(Map<String, String> oriMap, final boolean isRise) {
		if (oriMap == null || oriMap.isEmpty()) {
			return null;
		}

		Map<String, String> sortedMap = new LinkedHashMap<>();
		// 将oriMap.entrySet()转换成List
		List<Map.Entry<String, String>> entryList = new ArrayList<>(oriMap.entrySet());
		// 通过比较器来实现排序
		Collections.sort(entryList, new Comparator<Map.Entry<String, String>>() {
			@Override
			public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
				if (isRise) {
					// 升序排序
					return o1.getValue().compareTo(o2.getValue());
				} else {
					// 降序排序
					return o2.getValue().compareTo(o1.getValue());
				}
			}
		});

		Iterator<Map.Entry<String, String>> iterator = entryList.iterator();
		Map.Entry<String, String> tmpEntry;
		while (iterator.hasNext()) {
			tmpEntry = iterator.next();
			sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());
		}
		return sortedMap;
	}


}
