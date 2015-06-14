package common.component.applet;

import java.util.Hashtable;
import java.util.Map;

import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class GlobeData {
	//栏目模版文件流缓存
	public static Map<String,POIFSFileSystem> templateBooks=new Hashtable<String,POIFSFileSystem>();

}
