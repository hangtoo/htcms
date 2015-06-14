package common.component.applet;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import org.apache.commons.httpclient.NameValuePair;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import sun.plugin.javascript.JSObject;

import common.component.applet.excel.ExcelDispatch;
import common.component.applet.httpclient.HttpDataAnalyse;
import common.component.applet.httpclient.HttpPostGet;
import common.component.applet.util.Function;
import common.component.applet.util.ImageButton;
import common.util.ExcelUtil;
import common.util.StringUtil;

public class GlobeApplet extends JApplet {
	
	private static final String EXTENSION = ".xls";
	protected Logger log = Logger.getLogger(this.getClass());
	
	private String templatePath;
	
	private String password;
	
	private HttpPostGet http=new HttpPostGet();
	
	private HttpDataAnalyse analyse=new HttpDataAnalyse();
	
	private ExcelDispatch dispatch;
	
	private ExcelUtil excelgen=new ExcelUtil();
	
	private String catalogueId;
	
	private String catalogueName;
	
	private String beanId;
	
	private String editurl;
	
	private String saveurl;
	
	private String updateurl;
	
	private String _fileName;
	private String _operateUrl;
	
	
	private String createtime;
	private String creator;
	
	@Override
	public void init() {
		super.init();
		
		templatePath=getPara("PATH");
		password=getPara("PASSWORD");
		catalogueId=getPara("BEAN.CATALOGUE.ID");
		catalogueName=getPara("BEAN.CATALOGUE.NAME");
		editurl=getPara("EDITURL");
		
		saveurl=getPara("SAVEURL");
		
		updateurl=getPara("UPDATEURL");
		
		dispatch=new ExcelDispatch(templatePath);
		
		JButton newone=new ImageButton(Function.createImageIcon("/common/component/applet/new.gif")
				,Function.createImageIcon("/common/component/applet/new.gif")
				,Function.createImageIcon("/common/component/applet/new.gif"));//new JButton("新增");
		newone.setToolTipText("新增");
		newone.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				try {
					if (log.isDebugEnabled()){
						log.debug(catalogueId);
						log.debug(beanId);
					}
					
					if(catalogueId==null){
						JOptionPane.showMessageDialog(null, "请选择相应的功能模块!");
						return;
					}
					newOneExcel(catalogueId,catalogueName);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		JButton edit=new ImageButton(Function.createImageIcon("/common/component/applet/open.gif")
				,Function.createImageIcon("/common/component/applet/open.gif")
				,Function.createImageIcon("/common/component/applet/open.gif"));//new JButton("编辑");
		edit.setToolTipText("编辑");
		edit.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				try {
					
					if (log.isDebugEnabled()){
						log.debug(catalogueId);
						log.debug(beanId);
					}
					
					if(beanId==null||catalogueId==null){
						JOptionPane.showMessageDialog(null, "请选择需要操作的记录!");
						return;
					}
					editExcel(catalogueId,catalogueName
							,editurl+"?bean.id="+beanId+"&bean.catalogue.id="+catalogueId);
				} catch (Exception e1) {
					e1.printStackTrace();
					log.error(e1);
				}
			}
		});
		
		JButton close=new ImageButton(Function.createImageIcon("/common/component/applet/close-on.gif")
				,Function.createImageIcon("/common/component/applet/close.gif")
				,Function.createImageIcon("/common/component/applet/close.gif"));//new JButton("关闭");
		close.setToolTipText("关闭");
		close.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				try {
					closeExcel();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		JButton save=new ImageButton(Function.createImageIcon("/common/component/applet/save.gif")
				,Function.createImageIcon("/common/component/applet/save.gif")
				,Function.createImageIcon("/common/component/applet/save.gif"));//new JButton("保存");
		save.setToolTipText("保存");
		save.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				try {
					if (log.isDebugEnabled()){
						log.debug(catalogueId);
						log.debug(beanId);
						
						log.debug(creator);
						log.debug(createtime);
					}
					saveExcel(beanId,catalogueId,catalogueName,creator,createtime);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		//this.setSize(139,50);
		this.setSize(100,35);
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setBackground(new Color(192,192,192));
		this.add(newone);
		this.add(edit);
		this.add(close);
		this.add(save);
		
	}
	
	@Override
	public void destroy() {
		super.destroy();
		closeExcel();
		if (log.isDebugEnabled())
			log.debug("destory");
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			log.error(e);
		}
	}
	
	private POIFSFileSystem getTemplate(String catalogueid,String cataloguename) throws Exception{
		POIFSFileSystem fs=GlobeData.templateBooks.get(catalogueid);
		
		if(fs==null){
			fs=getTemplateSource(templatePath+cataloguename);
		}
		return fs;
	}
	
	private POIFSFileSystem getTemplateSource(String url) throws Exception {
		POIFSFileSystem fs = new POIFSFileSystem(getStream(url));
		return fs;
	}
	
	
	private InputStream getStream(String url) throws Exception {
		//LocalizedResourceHelper helper = new LocalizedResourceHelper();
		//java.util.Locale userLocale = RequestContextUtils.getLocale(request);
		//Resource inputFile = helper.findLocalizedResource(url, EXTENSION,null);
		if (log.isDebugEnabled())
			log.debug(url);
		
		if(!url.toLowerCase().endsWith(EXTENSION)){
			url=url+EXTENSION;
		}
		
		File inputFile=new File(url);
		
		if (log.isDebugEnabled())
			log.debug("Loading Excel workbook from " + inputFile);
		return new FileInputStream(inputFile);
	}
	
	private String getPara(String key){
		if (log.isDebugEnabled())
			log.debug(key);
		
		String ret=null;
		try{
			ret=getParameter(key);
			if (log.isDebugEnabled())
				log.debug(ret);
		}catch(Exception e){
			log.error(e);
			return "";
		}
		return ret;
	}
	
	//保存excel中数据
	public void saveExcel(String beanid,String catalogueid,String cataloguename,String creator,String createtime) throws Exception{
		
		if(StringUtil.isEmptyString(_fileName)){
			log.error("no record selected to deal");
			JOptionPane.showMessageDialog(null, "请选择需要操作的记录!");
			return;
		}
		
		String ret=operateExcel(beanid,catalogueid,cataloguename,creator,createtime,_fileName,_operateUrl);

		log.error("ret: "+ret);
		
		//TOTEST
		
		Map retModel=analyse.getModel(ret);
		
		if(!"200".equalsIgnoreCase(""+retModel.get("BEAN.RETCODE"))){
			log.error("deal fail "+ret);
			JOptionPane.showMessageDialog(null, "保存失败!"+retModel.get("BEAN.RETMSG"));
		}else{
			
			JOptionPane.showMessageDialog(null, "保存成功!");
			String js = "javascript:window.location.href=window.location.href;";
			try {
				JSObject.getWindow(this).eval(js);
			} catch (Exception e) {
				log.error(e);
			}
		}
	}
	
	//操作excel中数据
	private String operateExcel(String id,String catalogueid,String cataloguename,String creator,String createtime,String operatefilename,String operateurl) throws Exception{
		POIFSFileSystem fs=getTemplateSource(operatefilename);
		HSSFWorkbook hssfworkbook=new HSSFWorkbook(fs);
		
		POIFSFileSystem fs_temp=getTemplate(catalogueid,cataloguename);
		HSSFWorkbook templateBook=new HSSFWorkbook(fs_temp);
		
		Map<String,String> model=excelgen.getExcelModel(hssfworkbook, templateBook);
		
		Iterator<String> key=model.keySet().iterator();
		List<NameValuePair> datas=new ArrayList<NameValuePair>();
		while(key.hasNext()){
			String name=key.next();
			NameValuePair data=new NameValuePair();
			data.setName("columns['"+name+"']");
			data.setValue(model.get(name));
			datas.add(data);
			if(log.isDebugEnabled())
				log.debug(name+":"+model.get(name));
		}
		
		NameValuePair ele=new NameValuePair();
		ele.setName("bean.catalogue.id");
		ele.setValue(catalogueid);
		datas.add(ele);
		
		ele=new NameValuePair();
		ele.setName("bean.catalogue.name");
		ele.setValue(cataloguename);
		datas.add(ele);
		
		ele=new NameValuePair();
		ele.setName("bean.id");
		ele.setValue(id);
		datas.add(ele);
		
		ele=new NameValuePair();
		ele.setName("bean.creator");
		ele.setValue(creator);
		datas.add(ele);
		
		//TODO need test 20090831
		ele=new NameValuePair();
		ele.setName("bean.createTime");
		ele.setValue(createtime);
		datas.add(ele);
		
		NameValuePair[] data=new NameValuePair[datas.size()];
		datas.toArray(data);
		
		if (log.isDebugEnabled())
			log.debug(operateurl);
		
		return http.postData(operateurl, data,getJSessionid());
		
	}
	
	//根据模版生成临时文件,并打开编辑
	public void newOneExcel(String catalogueid,String cataloguename)throws Exception{
    	POIFSFileSystem fs=getTemplate(catalogueid,cataloguename);

    	HSSFWorkbook workbook=new HSSFWorkbook(fs);
		excelgen.buildExcelDocument(null, workbook);
		
		if (log.isDebugEnabled())
			log.debug(templatePath);
		
		String _fileName=templatePath+"temp.xls";
		if (log.isDebugEnabled())
			log.debug(_fileName);
		try{
			File documentFile=new File(_fileName);
			
			if(!documentFile.canWrite()){//to test 2009.8.19
				JOptionPane.showMessageDialog(null,"已经打开一个新增界面，请先关闭Excel新增界面！");
				return;
			}
			FileOutputStream out=new FileOutputStream(documentFile);
			workbook.write(out);
			out.flush();
		}catch(Exception e){
			log.error(e);
			JOptionPane.showMessageDialog(null,"已经打开一个新增界面，请先关闭Excel新增界面！");
			return;//to test 2009.8.19
		}
		this._fileName=_fileName;
		this._operateUrl=saveurl;
		dispatch.openExcelfile(_fileName, password);
	}
	
	//根据模版及数据生成临时文件,并打开编辑
	public void editExcel(String catalogueid,String cataloguename,String url)throws Exception{
    	POIFSFileSystem fs=getTemplate(catalogueid,cataloguename);

    	HSSFWorkbook workbook=new HSSFWorkbook(fs);
    	
    	String ret=http.getData(url,getJSessionid());
    	
    	if(ret==null){
    		JOptionPane.showMessageDialog(null, "请选择编辑的记录！");
    		return;
    	}
    		
    	Map model=analyse.getModel(ret);
    	
    	if(log.isDebugEnabled()){
    		Iterator key=model.keySet().iterator();
    		while(key.hasNext()){
    			Object thekey=key.next();
    			log.debug(thekey+":"+model.get(thekey));
    		}
    	}
    	
    	//TODO 测试 session过期判断
    	if(model.get("BEAN.ID")==null){
			JOptionPane.showMessageDialog(null,"登录过期，请重新登录！");
			return;
    	}
    	
		excelgen.buildExcelDocument(model, workbook);
		
		
		
		String _fileName=templatePath+"temp_edit.xls";
		try{
			File documentFile=new File(_fileName);
			
			if(!documentFile.canWrite()){//to test 2009.8.17
				JOptionPane.showMessageDialog(null,"请先关闭Excel编辑界面！");
				return;
			}
			FileOutputStream out=new FileOutputStream(documentFile);
			workbook.write(out);
			out.flush();
		}catch(Exception e){
			log.error(e);
			JOptionPane.showMessageDialog(null,"请先关闭Excel编辑界面！");
			return;//to test 2009.8.17
		}
		this._fileName=_fileName;
		this._operateUrl=updateurl;
		dispatch.openExcelfile(_fileName, password);
	}
	
	public void closeExcel(){
		dispatch.closeExcelfile();
	}

	public String getTemplatePath() {
		return templatePath;
	}

	public void setTemplatePath(String templatePath) {
		this.templatePath = templatePath;
	}


	public String getCatalogueId() {
		return catalogueId;
	}


	public void setCatalogueId(String catalogueId) {
		this.catalogueId = catalogueId;
	}


	public String getCatalogueName() {
		return catalogueName;
	}


	public void setCatalogueName(String catalogueName) {
		this.catalogueName = catalogueName;
	}


	public String getEditurl() {
		return editurl;
	}


	public void setEditurl(String editurl) {
		this.editurl = editurl;
	}


	public String getBeanId() {
		return beanId;
	}


	public void setBeanId(String beanId) {
		this.beanId = beanId;
	}
	
	private String getJSessionid(){
		return getPara("jsessionid");
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}
	
}
