package test.common.util;

import static org.junit.Assert.*;

import org.junit.Test;

import common.util.PinYinUtil;

public class PinYinUtilTest {

	@Test
	public void testGetFirstSpell() {
		
		assertEquals("zbmc",  PinYinUtil.getFirstSpell("指标名称"));  
		assertEquals("brsz",  PinYinUtil.getFirstSpell("本日数值"));  
		assertEquals("bsrzj", PinYinUtil.getFirstSpell("比上日增减"));
		assertEquals("fd",    PinYinUtil.getFirstSpell("幅度%"));     
		assertEquals("bnzg",  PinYinUtil.getFirstSpell("本年最高"));  
		assertEquals("zgzrq", PinYinUtil.getFirstSpell("最高值日期"));

	}

	@Test
	public void testGetFullSpell() {
		assertEquals("zhibiaomingcheng",PinYinUtil.getFullSpell("指标名称"));
		assertEquals("benrishuzhi",PinYinUtil.getFullSpell("本日数值"));
		assertEquals("bishangrizengjian",PinYinUtil.getFullSpell("比上日增减"));
		assertEquals("fudu%",PinYinUtil.getFullSpell("幅度%"));
		assertEquals("bennianzuigao",PinYinUtil.getFullSpell("本年最高"));
		assertEquals("zuigaozhiriqi",PinYinUtil.getFullSpell("最高值日期"));
		
	}

}
