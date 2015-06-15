package test.common.util;

import static org.junit.Assert.*;

import org.junit.Test;

import common.util.PassHtmlUtil;

public class PassHtmlUtilTest {

	@Test
	public void testReplaceTag() {
		fail("Not yet implemented");
	}

	@Test
	public void testHasSpecialChars() {
		fail("Not yet implemented");
	}

	@Test
	public void testFilterHtml() {
		String text=PassHtmlUtil.filterHtml("<p style='TEXT-JUSTIFY: inter-ideograph; TEXT-ALIGN: left; LINE-HEIGHT: 150%; MARGIN: 6pt 0cm; FONT-FAMILY: 'Calibri','sans-serif'; FONT-SIZE: 10.5pt' align='left'></p>"
				+ "<p>对于安卓4.0系统，使用电量控制窗口小部件可以轻松控制当前不用的功能，从而延长手机电池使用时间。默认情况下，这个窗口小部件显示在最左侧的主屏幕中。当该窗口小部件中显示了您觉得用不着的功能时，就可以关闭这些功能，从而延长电池使用时间。<br>如果当前的所有主屏幕中都没有电量控制窗口小部件，请执行以下步骤添加该窗口小部件：<br>1．转至【所有应用】屏幕，触摸【窗口小部件】标签。 </p><p>2．滑动手指浏览窗口小部件，从中找出【电量控制】，然后触摸并按住该窗口小部件。</p>"
				+ "<p></p>"
				+ "<p style='TEXT-ALIGN: center'><span style='LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; COLOR: black'><img src='E:/resource/images/uploadfile/20130516133022937001.gif' border='0'></span></p>");
		assertEquals(text,"对于安卓4.0系统，使用电量控制窗口小部件可以轻松控制当前不用的功能，从而延长手机电池使用时间。默认情况下，这个窗口小部件显示在最左侧的主屏幕中。当该窗口小部件中显示了您觉得用不着的功能时，就可以关闭这些功能，从而延长电池使用时间。如果当前的所有主屏幕中都没有电量控制窗口小部件，请执行以下步骤添加该窗口小部件：1．转至【所有应用】屏幕，触摸【窗口小部件】标签。 2．滑动手指浏览窗口小部件，从中找出【电量控制】，然后触摸并按住该窗口小部件。");
	}

	@Test
	public void testFiterHtmlTag() {
		fail("Not yet implemented");
	}

	@Test
	public void testReplaceHtmlTag() {
		fail("Not yet implemented");
	}

}
