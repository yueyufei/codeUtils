package yyf.common.weibo;

/**
 * 对微博的工具类
 * 
 * @author shuiyuan
 * @create 2014年9月11日
 */
public class WeiboUtil
{
	// 62进制字典
	private static String str62keys = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

	/**
	 * 将新浪微博mid转换成id
	 * 
	 * @param mid
	 * @return
	 */
	public static String Mid2Id(String mid)
	{
		String id = "";
		for (int i = mid.length() - 4; i > -4; i = i - 4) // 从最后往前以4字节为一组读取URL字符
		{
			int offset1 = i < 0 ? 0 : i;
			int len = i < 0 ? mid.length() % 4 : 4;
			String str = mid.substring(offset1, offset1 + len);

			str = Str62toInt(str);
			if (offset1 > 0) // 若不是第一组，则不足7位补0
			{
				while (str.length() < 7)
				{
					str = "0" + str;
				}
			}
			id = str + id;
		}

		return id;
	}

	/**
	 * 新浪微博id转换为mid
	 * 
	 * @param id
	 * @return
	 */
	public static String Id2Mid(String id)
	{
		String mid = "", strTemp;
		int startIdex, len;

		for (int i = id.length() - 7; i > -7; i = i - 7) // 从最后往前以7字节为一组读取mid
		{
			startIdex = i < 0 ? 0 : i;
			len = i < 0 ? id.length() % 7 : 7;

//			System.out.println(startIdex + "\t" + len);

			strTemp = id.substring(startIdex, startIdex + len);

//			System.out.println(strTemp);

			mid = IntToStr62(Integer.parseInt(strTemp)) + mid;
		}
		return mid;
	}

	// 62进制转成10进制
	public static String Str62toInt(String str62)
	{
		long i64 = 0;
		for (int i = 0; i < str62.length(); i++)
		{
			long Vi = (long) Math.pow(62, (str62.length() - i - 1));
			char t = str62.charAt(i);
			i64 += Vi * GetInt10(String.valueOf(t));
		}
		return String.valueOf(i64);
	}

	// 10进制转成62进制
	public static String IntToStr62(int int10)
	{
		String s62 = "";
		int r = 0;
		while (int10 != 0)
		{
			r = int10 % 62;
			s62 = Get62key(r) + s62;
			int10 = int10 / 62;
		}
		return s62;
	}

	// 获取key对应的62进制整数
	private static long GetInt10(String key)
	{
		return str62keys.indexOf(key);
	}

	// 获取62进制整数对应的key
	private static String Get62key(int int10)
	{
		if (int10 < 0 || int10 > 61)
			return "";
		return str62keys.substring(int10, int10 + 1);
	}
	
	/**
	 * 根据wid与uid构造微博URL
	 * @param wid
	 * @param uid
	 * @return
	 */
	public static String generateWeiboURL(String wid,String uid)
	{
		String mid = WeiboUtil.Id2Mid(wid);

		// 根据微博用户id与mid来构造微博的url
		String weiboUrl = "http://weibo.com/" + uid + "/" + mid;
		
		return weiboUrl;
	}


}
