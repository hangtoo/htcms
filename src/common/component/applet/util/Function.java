package common.component.applet.util;

import java.net.URL;

import javax.swing.ImageIcon;


public class Function {
    public static final URL findAsResource(String path) {
        URL url = null;
        ClassLoader contextClassLoader = Thread.currentThread().
                                         getContextClassLoader();
        if (contextClassLoader != null) {
            url = Function.class.getResource(path);
        }
        return url;
  }


    public static ImageIcon createImageIcon(String path) {
        URL imgURL = findAsResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            return null;
        }
    }

}
