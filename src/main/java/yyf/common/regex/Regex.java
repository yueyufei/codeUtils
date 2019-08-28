package yyf.common.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
	/**
	 * 匹配双字节字符(包括汉字在内)：[^x00-xff]  评注：可以用来计算字符串的长度（一个双字节字符长度计2，ASCII字符计1） 
	 * 匹配空白行的正则表达式：ns*r  评注：可以用来删除空白行  匹配HTML标记的正则表达式：<(S*?)[^>]*&gt;.*?|<.*? /> 
	 * 评注：网上流传的版本太糟糕，上面这个也仅仅能匹配部分，对于复杂的嵌套标记依旧无能为力  匹配首尾空白字符的正则表达式：^s*|s*$ 
	 * 评注：可以用来删除行首行尾的空白字符(包括空格、制表符、换页符等等)，非常有用的表达式 
	 * 匹配Email地址的正则表达式：w+([-+.]w+)*@w+([-.]w+)*.w+([-.]w+)*  评注：表单验证时很实用 
	 * 匹配网址URL的正则表达式：[a-zA-z]+://[^s]*  评注：网上流传的版本功能很有限，上面这个基本可以满足需求 
	 * 匹配帐号是否合法(字母开头，允许5-16字节，允许字母数字下划线)：^[a-zA-Z][a-zA-Z0-9_]{4,15}$  评注：表单验证时很实用 
	 * 匹配国内电话号码：d{3}-d{8}|d{4}-d{7}  评注：匹配形式如 0511-4405222 或 021-87888822 
	 * 匹配腾讯QQ号：[1-9][0-9]{4,}  评注：腾讯QQ号从10000开始  匹配中国邮政编码：[1-9]d{5}(?!d) 
	 * 评注：中国邮政编码为6位数字  匹配身份证：d{15}|d{18}  评注：中国的身份证为15位或18位  匹配ip地址：d+.d+.d+.d+ 
	 * 评注：提取ip地址时有用  匹配特定数字：  ^[1-9]d*$ //匹配正整数  ^-[1-9]d*$ //匹配负整数  ^-?[1-9]d*$
	 * //匹配整数  ^[1-9]d*|0$ //匹配非负整数（正整数 + 0）  ^-[1-9]d*|0$ //匹配非正整数（负整数 + 0） 
	 * ^[1-9]d*.d*|0.d*[1-9]d*$ //匹配正浮点数  ^-([1-9]d*.d*|0.d*[1-9]d*)$ //匹配负浮点数 
	 * ^-?([1-9]d*.d*|0.d*[1-9]d*|0?.0+|0)$ //匹配浮点数 
	 * ^[1-9]d*.d*|0.d*[1-9]d*|0?.0+|0$ //匹配非负浮点数（正浮点数 + 0） 
	 * ^(-([1-9]d*.d*|0.d*[1-9]d*))|0?.0+|0$ //匹配非正浮点数（负浮点数 + 0） 
	 * 评注：处理大量数据时有用，具体应用时注意修正  匹配特定字符串：  ^[A-Za-z]+$ //匹配由26个英文字母组成的字符串  ^[A-Z]+$
	 * //匹配由26个英文字母的大写组成的字符串  ^[a-z]+$ //匹配由26个英文字母的小写组成的字符串  ^[A-Za-z0-9]+$
	 * //匹配由数字和26个英文字母组成的字符串  ^w+$ //匹配由数字、26个英文字母或者下划线组成的字符串 
	 * 在使用RegularExpressionValidator验证控件时的验证功能及其验证表达式介绍如下:  只能输入数字：“^[0-9]*$” 
	 * 只能输入n位的数字：“^d{n}$”  只能输入至少n位数字：“^d{n,}$”  只能输入m-n位的数字：“^d{m,n}$” 
	 * 只能输入零和非零开头的数字：“^(0|[1-9][0-9]*)$”  只能输入有两位小数的正实数：“^[0-9]+(.[0-9]{2})?$” 
	 * 只能输入有1-3位小数的正实数：“^[0-9]+(.[0-9]{1,3})?$”  只能输入非零的正整数：“^+?[1-9][0-9]*$” 
	 * 只能输入非零的负整数：“^-[1-9][0-9]*$”  只能输入长度为3的字符：“^.{3}$” 
	 * 只能输入由26个英文字母组成的字符串：“^[A-Za-z]+$”  只能输入由26个大写英文字母组成的字符串：“^[A-Z]+$” 
	 * 只能输入由26个小写英文字母组成的字符串：“^[a-z]+$”  只能输入由数字和26个英文字母组成的字符串：“^[A-Za-z0-9]+$” 
	 * 只能输入由数字、26个英文字母或者下划线组成的字符串：“^w+$” 
	 * 验证用户密码:“^[a-zA-Z]w{5,17}$”正确格式为：以字母开头，长度在6-18之间，  只能包含字符、数字和下划线。 
	 * 验证是否含有^%&',;=?$"等字符：“[^%&',;=?$x22]+”  只能输入汉字：“^[u4e00-u9fa5],{0,}$” 
	 * 验证Email地址：“^w+[-+.]w+)*@w+([-.]w+)*.w+([-.]w+)*$” 
	 * 验证InternetURL：“^http://([w-]+.)+[w-]+(/[w-./?%&=]*)?$” 
	 * 验证电话号码：“^((d{3,4})|d{3,4}-)?d{7,8}$” 
	 * 正确格式为：“XXXX-XXXXXXX”，“XXXX-XXXXXXXX”，“XXX-XXXXXXX”， 
	 * “XXX-XXXXXXXX”，“XXXXXXX”，“XXXXXXXX”。  验证身份证号（15位或18位数字）：“^d{15}|d{}18$” 
	 * 验证一年的12个月：“^(0?[1-9]|1[0-2])$”正确格式为：“01”-“09”和“1”“12” 
	 * 验证一个月的31天：“^((0?[1-9])|((1|2)[0-9])|30|31)$”  正确格式为：“01”“09”和“1”“31”。 
	 * 匹配中文字符的正则表达式： [u4e00-u9fa5]  匹配双字节字符(包括汉字在内)：[^x00-xff]  匹配空行的正则表达式：n[s| ]*r 
	 * 匹配HTML标记的正则表达式：/<(.*)>.*|<(.*) />/  匹配首尾空格的正则表达式：(^s*)|(s*$) 
	 * 匹配Email地址的正则表达式：w+([-+.]w+)*@w+([-.]w+)*.w+([-.]w+)* 
	 * 匹配网址URL的正则表达式：http://([w-]+.)+[w-]+(/[w- ./?%&=]*)? 
	 * 
	 * @param str
	 * @return
	 */

	public static String replaceChars(String str) {
		String res = str.replaceAll("(\0|\\s*|\r|\n)", "");
		return res;
	}

	/**
	 * 正则匹配
	 * 
	 * @param line
	 * @return
	 */
	public static String regix(String line) {
		Pattern compile = Pattern.compile("[\\(\\（][0-9]{6}[\\)\\）]");
		Matcher matcger = compile.matcher(line);
		if (matcger.find()) {
			return matcger.group();
		}
		return null;
	}

	/**
	 * 正则匹配文本是否为基本中文汉字
	 * 
	 * @param line
	 * @return
	 */
	public static Boolean regexBaseChinese(String line) {
		Pattern compile = Pattern.compile("[\\u4e00-\\u9fcb]+");
		return compile.matcher(line).find();
	}

	/**
	 * 正则匹配文本扩展A
	 * 
	 * @param line
	 * @return
	 */
	public static Boolean regexExtendChineseA(String line) {
		Pattern compile = Pattern.compile("[\\u3400-\\u4db5]+");
		return compile.matcher(line).find();
	}

	/**
	 * 正则匹配文本扩展B
	 * 
	 * @param line
	 * @return
	 */
	public static Boolean regexExtendChineseB(String line) {
		Pattern compile = Pattern.compile("[\\u20000-\\u2a6d6]+");
		return compile.matcher(line).find();
	}

	/**
	 * 正则匹配文本扩展C
	 * 
	 * @param line
	 * @return
	 */
	public static Boolean regexExtendChineseC(String line) {
		Pattern compile = Pattern.compile("[\\u2a700-\\u2b734]+");
		return compile.matcher(line).find();
	}

	/**
	 * 正则匹配文本扩展D
	 * 
	 * @param line
	 * @return
	 */
	public static Boolean regexExtendChineseD(String line) {
		Pattern compile = Pattern.compile("[\\u2b740-\\u2b81d]+");
		return compile.matcher(line).find();
	}

	/**
	 * 检索出第一个结束符号位置
	 * 
	 * @param content
	 * @return
	 */
	public static Integer getindex(String content) {
		Matcher flags = Pattern.compile("[。！!？?；;]").matcher(content);
		Integer num = 0;
		Integer index = -1;
		while (flags.find()) {
			num++;
			if (1 == num) {
				index = flags.start();
				break;
			}
		}
		return index;
	}

	/**
	 * 替换所有空白符，换行符，制表符，空格符
	 * 
	 * @param str
	 * @return
	 */
	public static String replaceAllS(String str) {
		return replace(str, "[\\s\\t\\r\\n]");
	}

	/**
	 * 替换所有图片位置符号
	 * 
	 * @param str
	 * @return
	 */
	public static String replaceAllImg(String str) {
		return replace(str, "\\{IMG:\\d+\\}");
	}

	/**
	 * 替换所有的微博用户名称
	 * 
	 * @param str
	 * @return
	 */
	public static String replaceAllWeiBoUser(String str) {
		return replace(str, "@[\\u4e00-\\u9fa5a-zA-Z0-9_-]{4,30}");
	}

	/**
	 * 替换微博话题
	 * 
	 * @param str
	 * @return
	 */
	public static String replaceAllWeiBoTopic(String str) {
		return replace(str, "#[^#]+#");
	}

	/**
	 * 匹配所有英文，空格，标点
	 * 
	 * @param str
	 * @return
	 */
	public static String replaceAllpPunctuation(String str) {
		return replace(str, "[\\p{P}+~$`^=|<>～｀＄＾＋＝｜＜＞￥×]");
	}

	public static String[] splitByPunctuation(String str) {
		return split(str, "[\\p{P}+~$`^=|<>～｀＄＾＋＝｜＜＞￥×]");
	}

	/**
	 * 去除标点符号
	 * 
	 * @return
	 */
	public static String removePunctuation(String str) {
		return replace(str, "[\\pP\\p{Punct}]");
	}

	/**
	 * 匹配所有表情
	 * 
	 * @param str
	 * @return
	 */
	public static String replaceAllemoji(String str) {
		System.out.println(str);
		str = replace(str, "\\[\\W{1,5}\\]");
		System.out.println(str);
		return replace(str, "\\[\\w+\\]");
	}

	/**
	 * 匹配所有url
	 * 
	 * @param str
	 * @return
	 */
	public static String replaceAllUrl(String str) {
		return replace(str, "http://.+/\\w+");
	}

	public static String replaceAllNumLetter(String str) {
		return replace(str, "[\\w\\d]");
	}
	// reg =
	// /[\u3002|\uff1f|\uff01|\uff0c|\u3001|\uff1b|\uff1a|\u201c|\u201d|\u2018|\u2019|\uff08|\uff09|\u300a|\u300b|\u3008|\u3009|\u3010|\u3011|\u300e|\u300f|\u300c|\u300d|\ufe43|\ufe44|\u3014|\u3015|\u2026|\u2014|\uff5e|\ufe4f|\uffe5]/;

	public static String replace(String str, String rule) {
		Pattern p = Pattern.compile(rule);
		Matcher m = p.matcher(str);
		str = m.replaceAll("");
		return str;
	}

	public static String[] split(String str, String rule) {
		String[] split = str.split(rule);
		return split;
	}

	public static String[] getBetween(String str) {
		String repStr = str.replaceAll("</em><em>", "");
		System.out.println(repStr);
		int tindex = repStr.indexOf("<em>");
		int indext = repStr.lastIndexOf("</em>");
		if (tindex < indext) {
			repStr = repStr.substring(tindex + 4, indext);
		}
		System.out.println(repStr);
		String[] words = repStr.split("</em>.*[^</em>]<em>");
		return words;
	}

	public static String regex(String str) {
		Pattern p = Pattern.compile("\u200b");
		Matcher matcher = p.matcher(str);
		str = matcher.replaceAll("");
		return str;
	}

	public static String removeAllS(String str) {
		Pattern p = Pattern.compile("[\\s\\\\n\n]");
		Matcher matcher = p.matcher(str);
		str = matcher.replaceAll("");
		return str;
	}
	public static void main(String[] args) {
		String str = "忎腑鍥介摱琛屼繚闄╃洃鐫ｇ鐞嗗鍛樹細瑗勯槼鐩戠鍒嗗眬鎵瑰噯锛屼腑鍥介偖鏀垮偍钃勯摱琛岃偂浠芥湁闄愬叕鍙歌闃冲競瑗勫窞鍖烘 敮琛屾洿鍚嶄负涓浗閭斂鍌ㄨ搫閾惰鑲′唤鏈夐檺鍏徃瑗勯槼甯傝埅绌鸿矾鏀锛岀幇浜堜互鍏憡銆/font>銆2019骞鏈7鏃/font>銆鏈烘瀯鍚嶇О锛氫腑鍥介偖鏀垮偍钃勯摱琛岃偂浠芥湁闄愬叕鍙歌闃冲競鑸┖璺敮琛/font>銆璁剧珛鏃ユ湡锛008骞5鏈6鏃/font>銆鍙戣瘉鏃ユ湡锛019骞3鏈7鏃/font>銆鏈烘瀯鍦板潃锛氭箹鍖楃渷瑗勯槼甯傝宸炲尯浜ら璺鍙/font>銆鏈烘瀯缂栫爜锛欱0018S342060076銆璁稿彲璇佹祦姘村彿锛0596498銆鑱旂郴鐢佃瘽锛710-3341811銆鍙戣瘉鏈哄叧锛氫腑鍥介摱琛屼繚闄╃洃鐫ｇ鐞嗗鍛樹細瑗勯槼鐩戠鍒嗗眬銆浠ヤ笂鐩稿叧淇℃伅鍙湪涓浗閾惰淇濋櫓鐩戠潱绠 ＄悊濮斿憳浼氱綉绔欎笂锛ahref=\"http://www.cbrc.gov.cn\"target=\"_blank\">www.cbrc.gov.cn锛夋煡璇";
		Boolean line = regexBaseChinese(str);
		System.out.println(line);
	}

}
