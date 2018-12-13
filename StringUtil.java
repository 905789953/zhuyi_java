package java.util;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 字符串工具类
 * 
 */
public class StringUtil
{

	/**
	 * 验证字符串是否为null，或空
	 * 
	 * @param str
	 *            字符串
	 * @return boolean
	 */
	public static boolean validate(String str)
	{
		if (str == null || str == "" || str.equalsIgnoreCase("null") || str.trim().length() < 1) { return false; }
		return true;
	}

	/**
	 * 验证对象是否为null
	 * 
	 * @param str
	 *            字符串
	 * @return boolean
	 */
	public static boolean validate(Object str)
	{
		if (str == null) { return false; }
		return true;
	}

	/**
	 * 验证整型是否为0，或null;
	 * 
	 * @param str
	 *            字符串
	 * @return boolean
	 */
	public static boolean validate(int str)
	{
		if (str == 0 || "null".equalsIgnoreCase(str + "")) { return false; }
		return true;
	}

	/**
	 * 验证字符串是否为null，或空
	 * 
	 * @param str
	 *            字符串
	 * @return boolean
	 */
	public static boolean validate(StringBuffer str)
	{
		if (str == null || "".equalsIgnoreCase(str.toString()) || str.toString().trim().length() < 1) { return false; }
		return true;
	}

	/**
	 * 验证集合是否为null或size==0
	 * 
	 * @param str
	 *            字符串
	 * @return boolean
	 */
	public static boolean validate(List<?> str)
	{
		if (str == null || str.size() == 0) { return false; }
		return true;
	}

	/**
	 * 验证集合是否为null或size==0
	 * 
	 * @param str
	 *            字符串
	 * @return boolean
	 */
	public static boolean validate(String[] str)
	{
		if (str == null || str.length == 0) { return false; }
		return true;
	}

	/**
	 * 乱码转中文 ("ISO-8859-1"),"GBK");
	 * 
	 * @param str
	 *            字符串
	 * @return String
	 */
	public static String formatStrGBK(String str)
	{
		String result = "";
		try
		{
			if (StringUtil.validate(str))
			{
				result = new String(str.getBytes("ISO-8859-1"), "GBK");
			}
		}
		catch (Exception ex)
		{
			log.error(ex.toString(), "");
		}
		return result.trim();
	}

	/**
	 * 乱码转中文 ("ISO-8859-1"),"UTF-8");
	 * 
	 * @param str
	 *            字符串
	 * @return String
	 */
	public static String formatStrUTF(String str)
	{
		String result = "";
		try
		{
			if (StringUtil.validate(str))
			{
				result = new String(str.getBytes("ISO-8859-1"), "UTF-8");
			}
		}
		catch (Exception ex)
		{
			log.error(ex.toString(), "");
		}
		return result.trim();
	}

	/**
	 * 乱码转中文 ("GBK"),"UTF-8");
	 * 
	 * @param str
	 *            字符串
	 * @return String
	 */
	public static String formatStrUTF(StringBuffer str)
	{
		String result = "";
		try
		{
			if (StringUtil.validate(str))
			{
				result = new String(str.toString().getBytes("GBK"), "UTF-8");
			}
		}
		catch (Exception ex)
		{
			log.error(ex.toString(), "");
		}
		return result.trim();
	}

	/**
	 * 去除NULL,前，后空格
	 * 
	 * @param str
	 *            字符串
	 * @return String
	 */
	public static String formatNullStr(String str)
	{
		return str == null || str.toUpperCase() == "NULL" ? "" : str.trim();
	}

	/**
	 * 把字符串格式化为指定长度的字符串,
	 * 
	 * @param str
	 *            源字符
	 * @param length
	 *            length
	 * @param age0
	 *            源字符串不够指定长度，指定添冲的字符
	 * @param isBefore
	 *            是否从前面还是后面增加
	 * @return String
	 */
	public static String formatLengthStr(String str, int length, String age0, boolean isBefore)
	{
		String newStr = (str == null ? "" : str.trim());

		while (newStr.length() < length)
		{
			if (isBefore)
			{
				newStr = age0 + newStr;
			}
			else
			{
				newStr = newStr + age0;
			}
		}
		if (newStr.length() > length)
		{
			if (isBefore)
			{
				newStr = newStr.substring(newStr.length() - length);
			}
			else
			{
				newStr = newStr.substring(0, length);
			}
		}
		return newStr;
	}

	/**
	 * 去掉重复值
	 * 
	 * @param str
	 *            字符串
	 * @return StringBuffer
	 */
	public static StringBuffer deleteDuplicate(StringBuffer str)
	{
		if (StringUtil.validate(str) == false) { return null; }
		StringBuffer sb = new StringBuffer();
		boolean flag = false;
		String tmpStr[] = str.toString().split(",");
		for (int i = 0; i < tmpStr.length; i++)
		{
			for (int j = i + 1; j < tmpStr.length; j++)
			{
				if (tmpStr[i].equalsIgnoreCase(tmpStr[j]))
				{
					flag = true;
					break;
				}
			}
			if (flag == false)
			{
				if (validate(sb) == false)
				{
					sb.append(tmpStr[i]);
				}
				else
				{
					sb.append(",").append(tmpStr[i]);
				}
			}
			flag = false;
		}
		return sb;
	}

	/**
	 * 格式化从DB中提取的字段
	 * 
	 * @param str
	 *            字符串
	 * @return String
	 */
	public static String formatDbColumn(int str)
	{
		return String.valueOf(str);
	}

	/**
	 * 格式化从DB中提取的字段
	 * 
	 * @param str
	 *            Object
	 * @return String
	 */
	public static String formatDbColumn(Object str)
	{
		return StringUtil.validate(str) ? String.valueOf(str).trim() : "";
	}

	/**
	 * 格式化从DB中提取的字段
	 * 
	 * @param str
	 *            字符串
	 * @return String
	 */

	public static String formatDbColumn(String str)
	{
		return str != null ? str.trim() : "";
	}

	/**
	 * 判断str在list是否存在
	 * 
	 * @param list
	 *            WID,GH,XM,XB,CSRQ,GJHDQ,HJ,ZJH,ZJLX,MQNX
	 * @param str
	 *            GH
	 * @return boolean
	 */
	public static boolean isContaints(String list, String str)
	{
		if (StringUtil.validate(list) == false) { return false; }
		if (StringUtil.validate(str) == false) { return false; }
		String[] tmp = list.split(",");
		for (int i = 0; i < tmp.length; i++)
		{
			if (str.equalsIgnoreCase(tmp[i])) { return true; }
		}
		return false;
	}

	/**
	 * 把字符串全部大写
	 * 
	 * @param str
	 *            字符串
	 * @return String
	 */
	public static String toUpperCase(String str)
	{
		if (validate(str)) { return str.trim().toUpperCase(); }
		return "";
	}

	/**
	 * 把字符串全部小写
	 * 
	 * @param str
	 *            字符串
	 * @return String
	 */
	public static String toLowerCase(String str)
	{
		if (validate(str)) { return str.trim().toLowerCase(); }
		return "";
	}

	/**
	 * 把字符串的值添加到缓冲中
	 * 
	 * @param sb
	 *            缓冲区
	 * @param value
	 *            追加的字符
	 */
	public static void setStringBufferValue(StringBuffer sb, String value)
	{
		if (StringUtil.validate(sb) == false)
		{
			sb.append(value);
		}
		else
		{
			sb.append(",").append(value);
		}
	}

	/**
	 * 把字符串的值添加到缓冲中
	 * 
	 * @param sb
	 *            StringBuffer
	 * @param value
	 *            值
	 * @param regex
	 *            分隔符
	 */
	public static void setStringBufferValue(StringBuffer sb, String value, String regex)
	{
		if (StringUtil.validate(sb) == false)
		{
			sb.append(value);
		}
		else
		{
			sb.append(regex).append(value);
		}
	}

	/**
	 * 按格式插入值
	 * 
	 * @param regex
	 *            "{0} and {1} and {2}"
	 * @param value
	 *            String value[]={"wid='11001'","name='11001'","loc='11001'"};
	 * @return String
	 */
	public static String format(String regex, String[] value)
	{
		String str = regex;
		for (int i = 0; i < value.length; i++)
		{
			if (regex.contains("{" + i + "}"))
			{
				str = str.replaceAll("\\{" + i + "}", value[i]);
			}
		}
		return str;
	}

	/**
	 * 按格式插入值
	 * 
	 * @param regex
	 *            "{0} and {1} and {2}"
	 * @param value
	 *            "wid='11001',name='11001',loc='11001'"
	 * @return String
	 */
	public static String format(String regex, String value)
	{
		String str = regex;
		String valu[] = value.split(",");
		for (int i = 0; i < valu.length; i++)
		{
			if (regex.contains("{" + i + "}"))
			{
				str = str.replaceAll("\\{" + i + "}", valu[i]);
			}
		}
		return str;
	}

	/**
	 * 得到字符串指定的字节长度
	 * 
	 * @param str
	 *            字符串
	 * @param byteLeng
	 *            指定的长度
	 * @return String
	 */
	public static String substrByBytelength(String str, int byteLeng)
	{
		if (str != null && str.trim().length() > 0)
		{
			int count = 0;
			String strA = "";
			for (int i = 0; i < str.length(); i++)
			{
				char c = str.charAt(i);
				count += String.valueOf(c).getBytes().length;
				if (count > byteLeng) { return strA; }
				strA += String.valueOf(c);
			}
			return strA;
		}
		return "";
	}

	/**
	 * 验证Int
	 * 
	 * @param strInt
	 *            字符串
	 * @return boolean
	 */
	public static boolean validateInt(String strInt)
	{
		try
		{
			if (StringUtil.validate(strInt))
			{
				Integer.parseInt(strInt);
				return true;
			}
		}
		catch (Exception e)
		{
			log.error("系统错误：", e.getMessage());
		}
		return false;
	}

	/**
	 * 验证Double
	 * 
	 * @param strDouble
	 *            字符串
	 * @return boolean
	 */
	public static boolean validateDouble(String strDouble)
	{
		try
		{
			Double.parseDouble(strDouble);
		}
		catch (Exception e)
		{
			return false;
		}
		return true;
	}

	/**
	 * 验证是否是日期
	 * 
	 * @param strDate
	 *            字符串
	 * @return boolean
	 */
	public static boolean validateDate(String strDate)
	{
		try
		{
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			df.setLenient(false);
			Date d = df.parse(strDate);
			df.format(d);
		}
		catch (Exception e)
		{
			return false;
		}
		return true;
	}

	/**
	 * 验证是否是日期时间
	 * 
	 * @param strDateTime
	 *            字符串
	 * @return boolean
	 */
	public static boolean validateTime(String strDateTime)
	{
		try
		{
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			df.setLenient(false);
			Date d = df.parse(strDateTime);
			df.format(d);
		}
		catch (Exception e)
		{
			return false;
		}
		return true;
	}

	/**
	 * 验证byte[]是否为空
	 * 
	 * @param str
	 *            字符串
	 * @return boolean
	 */
	public static boolean validate(byte[] str)
	{
		if (str == null || str.length == 0) { return false; }
		return true;
	}

	/**
	 * 从List中删除指定项
	 * 
	 * @param list
	 *            目标list
	 * @param item
	 *            指定的项
	 * @return String
	 */
	public static String deleteItem(String list, String item)
	{
		if (StringUtil.isContaints(list, item))
		{
			if (list.startsWith(item + ","))
			{
				list = list.substring(item.length() + 1, list.length());
			}
			else if (list.endsWith("," + item))
			{
				list = list.substring(0, list.length() - item.length() - 1);
			}
			else if (list.equals(item))
			{
				list = "";
			}
			else
			{
				list = list.replace(item + ",", "");
			}
		}
		return list;
	}

	/**
	 * 添加指定项到list
	 * 
	 * @param list
	 *            目标list
	 * @param item
	 *            指定项
	 * @return String
	 */
	public static String addItem(String list, String item)
	{
		StringBuffer sb = new StringBuffer();
		if (StringUtil.validate(list))
		{
			sb.append(list).append(",").append(item);
		}
		else
		{
			sb = new StringBuffer(item);
		}
		return sb.toString();
	}

	/**
	 * 替换字符
	 * 
	 * @param s
	 *            源字符串
	 * @param oldSub
	 *            旧字符
	 * @param newSub
	 *            新字符
	 * @return String
	 */
	public static String replace(String s, char oldSub, char newSub)
	{
		return replace(s, oldSub, new Character(newSub).toString());
	}

	/**
	 * 替换字符
	 * 
	 * @param s
	 *            源字符串
	 * @param oldSub
	 *            旧字符
	 * @param newSub
	 *            新字符
	 * @return String
	 */
	public static String replace(String s, char oldSub, String newSub)
	{
		if ((s == null) || (newSub == null)) { return null; }

		char[] c = s.toCharArray();

		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < c.length; i++)
		{
			if (c[i] == oldSub)
			{
				sb.append(newSub);
			}
			else
			{
				sb.append(c[i]);
			}
		}

		return sb.toString();
	}

	/**
	 * 替换字符
	 * 
	 * @param s
	 *            源字符串
	 * @param oldSub
	 *            旧字符
	 * @param newSub
	 *            新字符
	 * @return String
	 */
	public static String replace(String s, String oldSub, String newSub)
	{
		if ((s == null) || (oldSub == null) || (newSub == null)) { return null; }

		int y = s.indexOf(oldSub);

		if (y >= 0)
		{
			StringBuffer sb = new StringBuffer();

			int length = oldSub.length();
			int x = 0;

			while (x <= y)
			{
				sb.append(s.substring(x, y));
				sb.append(newSub);
				x = y + length;
				y = s.indexOf(oldSub, x);
			}

			sb.append(s.substring(x));

			return sb.toString();
		}
		return s;
	}

	/**
	 * 替换字符
	 * 
	 * @param s
	 *            源字符串
	 * @param oldSubs
	 *            旧字符数组
	 * @param newSubs
	 *            新字符数组
	 * @return String
	 */
	public static String replace(String s, String[] oldSubs, String[] newSubs)
	{
		if ((s == null) || (oldSubs == null) || (newSubs == null)) { return null; }

		if (oldSubs.length != newSubs.length) { return s; }

		for (int i = 0; i < oldSubs.length; i++)
		{
			s = replace(s, oldSubs[i], newSubs[i]);
		}

		return s;
	}

	/**
	 * 分隔字符串
	 * 
	 * @param s
	 *            源字符串
	 * @param delimiter
	 *            分隔符
	 * @return String[]
	 */
	public static String[] spliceString(String s, String delimiter)
	{
		if (StringUtil.validate(s)) { return s.split(delimiter); }
		return null;
	}

	/**
	 * 格式化成gb2312
	 * 
	 * @param str
	 *            源字符串
	 * @return String
	 */
	public static String formatStrGb2312(String str)
	{
		String result = "";
		try
		{
			if (StringUtil.validate(str))
			{
				result = new String(str.toString().getBytes(), "gb2312");
			}
		}
		catch (Exception ex)
		{
			log.error(ex.toString(), "");
		}
		return result.trim();
	}

	/**
	 * 获取货币的金额
	 * 
	 * @param symbolCurr
	 *            带货币符号的金额
	 * @return String
	 */
	public static String formatCurrencyNum(String symbolCurr)
	{
		StringBuffer retStr = new StringBuffer();
		try
		{
			symbolCurr = symbolCurr.trim();
			String ntNumber = symbolCurr.replaceAll("\\D", "");
			if (symbolCurr.contains("."))
			{
				String digits = symbolCurr.substring(symbolCurr.indexOf(".") + 1, symbolCurr.length());
				String integer = ntNumber.substring(0, ntNumber.lastIndexOf(digits));
				retStr.append(integer).append(".").append(digits);
			}
			else
			{
				retStr.append(ntNumber);
			}
		}
		catch (Exception e)
		{
			log.error(e.toString(), "");
		}
		return retStr.toString();
	}

	/**
	 * MD5密码加密
	 * 
	 * @param input
	 *            输入的字符串
	 * @return String
	 */
	public static String encode(String input)
	{
		if (!StringUtil.validate(input)) { return input; }
		char hexDigits[] =
		{ '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		try
		{
			byte[] strTemp = input.getBytes();
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++)
			{
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		}
		catch (Exception e)
		{
			return null;
		}
	}

	/**
	 * CHS
	 */
	private static final String[] CHS =
	{ "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖", "拾" };

	/**
	 * 转金额大写
	 * 
	 * @param money
	 *            金额数字串
	 * @return 金额中文大写 将输入数字串money分割成4位数，将四位数转换成金额大写后再加上单位（"","万","亿"）
	 */
	public static String formatToCMoney(String money)
	{
		StringBuffer chRMB = new StringBuffer();
		money = money.replaceAll("[,，]", "");// 去除分隔符逗号
		double t = Double.parseDouble(money);
		if (t < 0) { return "金额不能是负的！"; }
		int dotPos = money.indexOf('.');
		String strz;// 截取整数部分
		if (dotPos == -1)// 没小数点的时候等于-1
		{
			strz = money;
		}
		else
		{
			strz = money.substring(0, dotPos);// 整数部分
		}
		StringBuffer sb = new StringBuffer(strz);
		while (sb.length() >= 1 && sb.charAt(0) == '0')
		{// 去掉数前面多余的零
			sb.deleteCharAt(0);
		}
		if (sb.length() == 0)
		{// 输入为‘00’的情况
			return "零";
		}
		strz = sb.toString();
		String cur = "";
		boolean zero = false;// 记录是否应该加‘零’如 1，0000，0000，0001，读作 壹万亿零壹圆整
		String temp;
		String digits;// 每次截取的最后四位数
		while (strz.length() > 0)
		{

			if (strz.length() >= 4)
			{
				digits = strz.substring(strz.length() - 4);
				strz = strz.substring(0, strz.length() - 4);
			}
			else
			{
				digits = strz;
				strz = "";

			}
			temp = fourMaxChange(digits);
			if (temp.equals("零"))
			{
				if (cur.equals("亿"))
				{
					chRMB.insert(0, "亿");
				}
				if (zero == true)
				{
					if (!chRMB.substring(0, 1).equals("零"))
					{
						chRMB.insert(0, "零");
					}
					zero = false;
				}
			}
			else
			{
				temp += cur;
				chRMB.insert(0, temp);
				if (digits.charAt(0) == '0')
				{
					if (chRMB.length() >= 1 && !chRMB.substring(0, 1).equals("零"))
					{
						chRMB.insert(0, "零");
					}
				}
				zero = true;

			}
			if (cur.equals(""))
			{// 改变单位
				cur = "万";
			}
			else if (cur.equals("亿"))
			{
				cur = "万";
			}
			else
			{
				cur = "亿";
			}

		}
		String strx;
		if (dotPos != -1)
		{// 有小数
			strx = money.substring(dotPos + 1);
			if (strx.length() > 2) { return "金额只允许两位小数！"; }
			int xiaoshu = Integer.parseInt(strx);// 小数部分
			if (xiaoshu == 0 || strx.length() == 0)
			{
				chRMB.append("圆整");// 整
			}
			else
			{
				chRMB.append("圆");
			}
			if (strx.length() == 1)
			{
				chRMB.append(CHS[xiaoshu]).append("分");
			}
			else
			{
				if (xiaoshu / 10 != 0)
				{
					chRMB.append(CHS[xiaoshu / 10]).append("角");
				}
				else
				{
					chRMB.append("零");
				}
				if (xiaoshu % 10 != 0)
				{
					chRMB.append(CHS[xiaoshu % 10]).append("分");
				}
			}
		}
		else
		{
			chRMB.append("圆整");
		}

		return chRMB.toString();
	}

	/** */
	/**
	 * 
	 * @param number
	 *            输入数字串
	 * @return 转换后的大写金额
	 * @throws NumberFormatException
	 *             不合法的数字 将最多四位整数转换成大写金额
	 */
	public static String fourMaxChange(String number) throws NumberFormatException
	{// 将最多四位整数转换成大写金额
		StringBuffer rs = new StringBuffer();
		int money;
		money = Integer.parseInt(number);
		boolean zero = false;
		if (money / 1000 != 0)
		{
			zero = true;
			rs.append(CHS[money / 1000]).append("仟");
			money %= 1000;
		}
		if (money / 100 != 0)
		{
			zero = true;
			rs.append(CHS[money / 100]).append("佰");
			money %= 100;
		}
		else if (zero == true)
		{
			rs.append(CHS[0]);// 零
			money %= 100;
		}

		if (money / 10 != 0)
		{
			zero = true;
			rs.append(CHS[money / 10]).append("拾");
			money %= 10;
		}
		else if (zero == true && !rs.substring(rs.length() - 1).equals("零"))
		{
			rs.append(CHS[0]);// 零
			money %= 10;
		}
		if (money != 0)
		{
			zero = true;
			rs.append(CHS[money]);
		}
		else if (zero == true && rs.substring(rs.length() - 1).equals("零"))
		{
			rs.delete(rs.length() - 1, rs.length());
		}
		if (zero == true) { return rs.toString(); }
		return CHS[0];// 零
	}
}
