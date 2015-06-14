package common.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;

/**
 * String Utility Class This is used to encode passwords programmatically
 * 
 * <p>
 * <a h ref="StringUtil.java.html"><i>View Source</i></a>
 * </p>
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
public class StringUtil {
	// ~ Static fields/initializers
	// =============================================

	private final static Logger log = Logger.getLogger(StringUtil.class);
	
	// ~ Methods
	// ================================================================

	/**
	 * Encode a string using algorithm specified in web.xml and return the
	 * resulting encrypted password. If exception, the plain credentials string
	 * is returned
	 * 
	 * @param password
	 *            Password or other credentials to use in authenticating this
	 *            username
	 * @param algorithm
	 *            Algorithm used to do the digest
	 * 
	 * @return encypted password based on the algorithm.
	 */
	public static String encodePassword(String password, String algorithm) {
		byte[] unencodedPassword = password.getBytes();

		MessageDigest md = null;

		try {
			// first create an instance, given the provider
			md = MessageDigest.getInstance(algorithm);
		} catch (Exception e) {
			log.error("Exception: " + e);

			return password;
		}

		md.reset();

		// call the update method one or more times
		// (useful when you don't know the size of your data, eg. stream)
		md.update(unencodedPassword);

		// now calculate the hash
		byte[] encodedPassword = md.digest();

		StringBuffer buf = new StringBuffer();

		for (int i = 0; i < encodedPassword.length; i++) {
			if ((encodedPassword[i] & 0xff) < 0x10) {
				buf.append("0");
			}

			buf.append(Long.toString(encodedPassword[i] & 0xff, 16));
		}

		return buf.toString();
	}

	/**
	 * Encode a string using Base64 encoding. Used when storing passwords as
	 * cookies.
	 * 
	 * This is weak encoding in that anyone can use the decodeString routine to
	 * reverse the encoding.
	 * 
	 * @param str
	 * @return String
	 */
	public static String encodeString(String str) {
		sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
		return encoder.encodeBuffer(str.getBytes()).trim();
	}

	/**
	 * Decode a string using Base64 encoding.
	 * 
	 * @param str
	 * @return String
	 */
	public static String decodeString(String str) {
		sun.misc.BASE64Decoder dec = new sun.misc.BASE64Decoder();
		try {
			return new String(dec.decodeBuffer(str));
		} catch (IOException io) {
			throw new RuntimeException(io.getMessage(), io.getCause());
		}
	}

	/**
	 * �滻ָ���ַ�
	 * 
	 * @param line
	 *            Դ�ַ�
	 * @param oldString
	 *            ԭ4���ַ�
	 * @param newString
	 *            ��Ҫ�滻���ַ�
	 * @return
	 */
	public static final String replace(String line, String oldString,
			String newString) {
		if (line == null) {
			return null;
		}
		int i = 0;
		if ((i = line.indexOf(oldString, i)) >= 0) {
			char[] line2 = line.toCharArray();
			char[] newString2 = newString.toCharArray();
			int oLength = oldString.length();
			StringBuffer buf = new StringBuffer(line2.length);
			buf.append(line2, 0, i).append(newString2);
			i += oLength;
			int j = i;
			while ((i = line.indexOf(oldString, i)) > 0) {
				buf.append(line2, j, i - j).append(newString2);
				i += oLength;
				j = i;
			}
			buf.append(line2, j, line2.length - j);
			return buf.toString();
		}
		return line;
	}

	/**
	 * 
	 * @param �ж��Ƿ��ǿ��ַ�
	 * @return
	 */
	public static final boolean isEmptyString(String s) {
		if (s == null)
			return true;
		if ("".equals(s.trim()))
			return true;
		return false;
	}

	/**
	 * ������ת�����ַ�
	 * 
	 * @param value
	 *            ��Ҫת��������
	 * @return
	 */
	public static final String convertNumberToString(Number value) {
		DecimalFormat formatter = new DecimalFormat();
		formatter.setGroupingUsed(false);
		String result = formatter.format(value.doubleValue());
		return result;
	}

	/**
	 * ����ֵ��ΪString[2]
	 */
	public static final String[] splitStringForOracle(String s) {
		if (s == null)
			return NullStringArray2;
		int length = s.length();
		if (length <= 650)
			return new String[] { s, null };
		String a = s.substring(0, 650);
		String b = s.substring(650, length);
		return new String[] { a, b };
	}

	private static final String[] NullStringArray2 = new String[2];

	/**
	 * ��Stringѹ�� example:com.jbbis.aic.alteration.entity.AlterRequisition ��
	 * integrateOpinion,getIntegrateOpinionInBytes(),setIntegrateOpinionInBytes
	 */
	public static byte[] zipString(String s) {
		if (s == null)
			return null;
		try {
			byte[] bytes = s.getBytes("GBK");
			BufferedInputStream in = new BufferedInputStream(
					new ByteArrayInputStream(bytes));
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			BufferedOutputStream out = new BufferedOutputStream(
					new GZIPOutputStream(baos));
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = in.read(buffer)) != -1) {
				out.write(buffer, 0, len);
			}
			in.close();
			out.close();
			return baos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * ��ѹString
	 */
	public static String unzipString(byte[] bytes) {
		if (bytes == null || bytes.length == 0)
			return null;
		try {
			BufferedInputStream in = new BufferedInputStream(
					new GZIPInputStream(new ByteArrayInputStream(bytes)));
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			BufferedOutputStream out = new BufferedOutputStream(baos);
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = in.read(buffer)) != -1) {
				out.write(buffer, 0, len);
			}
			in.close();
			out.close();
			bytes = baos.toByteArray();
			return new String(bytes, "GBK");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * ���ַ��ʽ����HTML��ʽ
	 * 
	 * @param s
	 * @return
	 */
	public static String stringToHtmlFormat(String s) {
		if (s == null)
			return null;
		String temp = replace(s, "\n", "<br>");
		temp = replace(temp, "\t", "&nbsp;&nbsp;&nbsp;&nbsp;");
		return replace(temp, " ", "&nbsp;");
	}

	/**
	 * ȥ���ַ��ǰ��ո�
	 * 
	 * @param str
	 *            �����ַ�
	 * @return ���ַ��ǰ��ո����ַ�
	 */
	public static String trim(String str) {
		if (str == null)
			return null;
		return str.trim();
	}
	/**
	 * ȥ����ߵ�'0'�ַ�
	 * @param str
	 * @return
	 */
	public static String leftTrimZero(String str){
		int len = str.length();
		int st = 0;
		char[] val = str.toCharArray();    /* avoid getfield opcode */
		while ((st < len) && (val[st] == '0')) {
		    st++;
		}
		return ( st < len+1) ? str.substring(st) : str;
	}

	/**
	 * ����Ƿ������Ƿ�Ϊ��
	 * 
	 * @param array
	 *            ���м�������
	 */
	public static boolean isArrayEmpty(Object[] array) {

		boolean result = false;
		if (array == null)
			result = true;
		else {
			if (array.length == 0)
				result = false;

		}
		result = false;
		return result;

	}

	/**
	 * ������պ�ȥ���ǰ�����ַ�
	 * 
	 * @param str
	 *            ��Ҫ������ַ�
	 * @return ������ϵ��ַ�
	 */
	public static String trimorempty(String str) {
		String result = "";
		if (str == null)
			result = "";
		else
			result = trim(str);
		return result;

	}

	/**
	 * ���ͷβ��־��ȡ���ַ�
	 * 
	 * @param srcStr
	 *            Դ�ַ�
	 * @param headToken
	 *            ͷ�ַ��־
	 * @param tailToken
	 *            β�ַ��־
	 * @return ���ַ�
	 * 
	 */
	public static String getSubStrByToken(String srcStr, String headToken,
			String tailToken) {
		String result = "";
		int startPos = srcStr.indexOf(headToken);
		int endPos = srcStr.indexOf(tailToken);
		if (startPos >= 0 && endPos > 0 && startPos < endPos) {
			result = srcStr.substring(startPos + headToken.length(), endPos);
		}
		return result;

	}

	public static String[] getSubStrArrayByToken(String srcStr,
			String headToken, String tailToken) {
		// String result = "";
		// int startPos = srcStr.indexOf(headToken);
		//		
		// int endPos = srcStr.indexOf(tailToken);
		// if (startPos >=0 && endPos > 0 && startPos < endPos) {
		// result = srcStr.substring(startPos+headToken.length(), endPos);
		// }
		// return result;
		int matches = Math.min(StringUtils.countMatches(srcStr, headToken),
				StringUtils.countMatches(srcStr, tailToken));

		if (matches == 0) {
			return null;
		} else {
			String[] result = new String[matches];
			String temp = srcStr;
			for (int i = 0; i < matches; i++) {
				result[i] = getSubStrByToken(temp, headToken, tailToken);
				int pos = srcStr.indexOf(tailToken);
				temp = srcStr.substring(pos + tailToken.length());
			}
			return result;
		}

	}

	/**
	 * @author zhangyb ��ݷָ�����ַ�����
	 * @param srcStr
	 *            Դ�ַ�
	 * @param delimiters
	 *            �ָ��
	 * @return �ַ�����
	 */
	public static String[] getStrArrayBySpit(String srcStr, String delimiters) {
		String[] result = null;
		int index = 0;
		if (srcStr != null) {
			StringTokenizer stk = new StringTokenizer(srcStr, delimiters);

			if (stk.hasMoreTokens())
				result = new String[stk.countTokens()];
			while (stk.hasMoreTokens()) {
				result[index] = stk.nextToken();
				index++;
			}
		}
		return result;
	}

	/**
	 * ������ 20060101 ���ַ�ת��Ϊ2006-01-01��ʽ ��ʽ���� 20060101 �Ļ���ԭ4�ַ�
	 * 
	 * @param date
	 * @return
	 */
	public static String getDateFormat(String date) {
		if (date != null && date.length() == 8) {
			date = date.substring(0, 4) + "-" + date.substring(4, 6) + "-"
					+ date.subSequence(6, date.length());
		}
		return date;
	}

	/**
	 * ����String��UTF8 Byte[]
	 * 
	 * @param src
	 * @return
	 * 
	 */
	public static byte[] getUTF8Bytes(String src) {

		try {
			return src.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			return ArrayUtils.EMPTY_BYTE_ARRAY;
		}

	}

	public static void main(String[] args) {
		String test = "${xxx.dd$}ddd${555.ddd$}";
		String[] substr = getSubStrArrayByToken(test, "${", "$}");
		for (int i = 0; i < substr.length; i++) {
			System.out.println(substr[i]);
		}
	}

	public static String getMapValue(Map map, String key) {
		String value = null;
		if (map.containsKey(key)) {
			if (map.get(key) != null)
				value = map.get(key).toString();
		}
		return trimorempty(value);
	}

	public static String setRegulateNum(String str) {
		String result = "";
		DecimalFormat df = new DecimalFormat("##.##");
		double d = 0.0;
		if (str != null && str.trim().length() > 0) {
			d = Double.parseDouble(str) / 100;
			result = df.format(Double.parseDouble(String.valueOf(d)));
		}
		return result;
	}

	public static String getRegulateNum(String str) {
		String result = "";
		DecimalFormat df = new DecimalFormat("##.##");
		double d = 0.0;
		if (str != null && str.trim().length() > 0) {
			d = Double.parseDouble(str) * 100;
			result = df.format(Double.parseDouble(String.valueOf(d)));
		}
		return result;
	}

	/**
	 * ʮ���Ƶ�IP��ַת��Ϊʮ����Ƶ�IP
	 * 
	 * @author zhangyb
	 * @param ip
	 * @return
	 */
	public static String IP2HexStr(String ip) {
		String hexStr = null;
		String[] ips = getStrArrayBySpit(ip, ".");
		for (int i = 0; i < ips.length; i++) {
			hexStr = hexStr.concat(Str2HexStr(ips[i]));
		}
		return hexStr;
	}

	/**
	 * ʮ����Ƶ�IP��ַת��Ϊʮ���Ƶ�IP
	 * 
	 * @param hexStr
	 * @return
	 */
	public static String HexStr2IP(String hexStr) {
		String ip = null;
		String subIP = "";
		if (hexStr.length() == 8) {
			for (int i = 0; i < 4; i++) {
				subIP = hexStr.substring(i * 2, (i + 1) * 2);
				if (i == 0)
					ip = String.valueOf(Integer.parseInt(subIP, 16));
				else
					ip = ip + "." + HexStr2Str(subIP);
			}
		}
		return ip;
	}

	/**
	 * ʮ�����ת��Ϊʮ����
	 * 
	 * @param hexStr
	 * @return
	 */
	public static String HexStr2Str(String hexStr) {
		String str = null;
		str = String.valueOf(Integer.parseInt(hexStr, 16));
		return str;
	}

	/**
	 * ʮ����ת��Ϊʮ�����
	 * 
	 * @param str
	 * @return
	 */
	public static String Str2HexStr(String str) {
		String hexStr = "";
		hexStr = Integer.toHexString(Integer.parseInt(str));
		if (hexStr.length() < 2)
			hexStr = "0" + hexStr;
		return hexStr;
	}
	
	public static String[] getArrayFromBean(Object bean ,String name){
		String array[] = null;
		try {
			array = BeanUtils.getArrayProperty(bean, name);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return array;
	}
	
	public static String getStrFromBean(Object bean ,String name){
		String str = null;
		try {
			str = BeanUtils.getSimpleProperty(bean, name);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return str;
	}
	
	public static String getStrForParseInt(String s){
		String result = trimorempty(s);
		if(result.length()>0)
			return result;
		else 
			return "0";
	}
	
	/**
	 * �ж��ַ��Ƿ���ڸ���ַ��������
	 * 
	 * @param str
	 * @param aStr
	 * @return
	 */
	public static boolean isSub(String str, String[] aStr) {
		boolean isSub = false;
		if (aStr != null) {
			for (int i = 0; i < aStr.length; i++) {
				if (str.equalsIgnoreCase(aStr[i])) {
					isSub = true;
					break;
				}
			}
		}
		return isSub;
	}
	
	public static String BigDecimal2String(BigDecimal bg){
		String result = "";
		if(bg!=null)
			result = bg.toString();
		return result;
	}
	/**
	 * ȡһ��list������ֵ����Сֵ��ƽ��ֵ�ͷ���ʱ��
	 * @param list
	 * @return
	 */
	public static HashMap getParamFromList(List list){
		HashMap map=new HashMap();
		Iterator iterator = list.iterator();
		String max="0";
		String min="0";
		String maxtime="";
		String mintime="";
		double avg=0;
		int i=1;
		if(iterator.hasNext())
		{	
			Object[] obj = (Object[])iterator.next();
			if(obj[1]!=null){
			max=obj[1].toString();
			maxtime=obj[0].toString();
			min=obj[1].toString();
			mintime=obj[0].toString();
			avg=Double.parseDouble(obj[1].toString());
			}
		}
		while(iterator.hasNext()){
			Object[] obj = (Object[])iterator.next();
			if(obj[1]!=null){
			if(Double.parseDouble(obj[1].toString())>Double.parseDouble(max))
				{
				  max=obj[1].toString();
				  maxtime=obj[0].toString();
				}
			if(Double.parseDouble(obj[1].toString())<Double.parseDouble(min))
			{
			  min=obj[1].toString();
			  mintime=obj[0].toString();
			}
			i++;
			avg+=Double.parseDouble(obj[1].toString());
			}
		}
		map.put("max", max);
		map.put("maxtime", maxtime);
		map.put("min", min);
		map.put("mintime", mintime);
		map.put("avg", String.valueOf(avg/i));
		return map;
	}

	public static String randomKey(int length) {
        StringBuffer result = new StringBuffer();
        SecureRandom random = new SecureRandom();
        for(int i = 0; i < length; i++)
            result.append(random.nextInt(10));

        return result.toString();
	}
}
