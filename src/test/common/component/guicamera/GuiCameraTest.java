package test.common.component.guicamera;

import org.junit.Test;

import common.component.guicamera.GuiCamera;

public class GuiCameraTest {

	@Test
	public void testSnapShot() {
		GuiCamera cam = new GuiCamera("Test", "png");
		cam.snapShot();
	}

}
