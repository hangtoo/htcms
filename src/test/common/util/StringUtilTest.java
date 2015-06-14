package test.common.util;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import junit.framework.TestCase;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


public class StringUtilTest extends TestCase{
	public void testPattern(){
		Pattern pattern=Pattern.compile("\\$\\{(.+?)\\}");
		Matcher matcher=pattern.matcher("${aaaaaa}");
		while (matcher.find()){
			System.out.println(matcher.group(1).trim().toUpperCase());
		}
		
		pattern=Pattern.compile("\\$\\{111(.+?)222");
		matcher=pattern.matcher("${111test222");
		while (matcher.find()){
			System.out.println(matcher.group(1).trim());
		}
	}

}
