package common.component.application.share.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.junit.Test;

import com.mysql.jdbc.StringUtils;

import common.base.BaseStrutsTestCase;
import common.component.IConstants;
import common.component.applet.httpclient.HttpPostGet;
import common.component.application.share.entity.Stock;
import common.component.application.share.service.StockService;
import common.component.permission.entity.Admin;
import common.exception.ExistException;
import common.util.DateUtil;
import common.util.LunarDateUtil;
import common.util.TemplateDataUtil;

public class StockServiceImplTest extends BaseStrutsTestCase{

	public StockServiceImplTest(String name) {
		super(name);
	}
	
	@Test
	public void test(){
		Stock bean=new Stock();
		bean.setName("市场总成交金额（元）");
		bean.setData(new BigDecimal("573,746,392,495.45".replaceAll(",","")));
		bean.setDate(DateUtil.convertShortStringToDate("2015-04-01"));
		bean.setAdd(new BigDecimal("-40,073,329,907.23".replaceAll(",","")));
		bean.setRate(Float.valueOf("-6.52"));
		bean.setHighdata(new BigDecimal("719,046,681,794.15".replaceAll(",","")));
		bean.setHighdate(DateUtil.convertShortStringToDate("2015-03-24"));
		
		System.out.println(bean.getData());
		System.out.println(bean.getDate());
		System.out.println(bean.getAdd());
		
		
	}
	
	private void init(){
		TemplateDataUtil.setFileTemplate("G:/workspace/struts2/resources/common/util/template.txt");
		//TemplateDataUtil.setFileTemplate("G:/workspace/struts2/resources/common/util/template2009.txt");
		
		Admin bean =new Admin();
		bean.setName("test");
		bean.setUsername("test");
		bean.setPassword("test");
		
		Map session=new Hashtable();
		session.put(IConstants.USER, bean);
		ServletActionContext.getContext().setSession(session);
	}
	
	private void batchSave(Map<String,String> data,Date tdate){
		
		StockService service=(StockService)this.getInstance("stockService");
		
		for(int i=0;i<15;i++){
			Stock bean=new Stock();
			bean.setName(data.get("name"+i));
			
			if(StringUtils.isNullOrEmpty(bean.getName())){
				continue;
			}
			
			String obj=data.get("data"+i).replaceAll(",","");
			if(!StringUtils.isNullOrEmpty(obj)){
				bean.setData(new BigDecimal(obj));
			}
			
/*			obj=data.get("date"+i);
			if(!StringUtils.isNullOrEmpty(obj)){
				bean.setDate(DateUtil.convertShortStringToDate(obj));
			}*/
			bean.setDate(tdate);
			
			obj=data.get("add"+i).replaceAll(",","");
			if(!StringUtils.isNullOrEmpty(obj)){
				bean.setAdd(new BigDecimal(obj));
			}
			
			obj=data.get("rate"+i);
			if(!StringUtils.isNullOrEmpty(obj)){
				bean.setRate(Float.valueOf(obj));
			}
			
			obj=data.get("highdata"+i).replaceAll(",","");
			if(!StringUtils.isNullOrEmpty(obj)){
				bean.setHighdata(new BigDecimal(obj));
			}
			
			obj=data.get("highdate"+i);
			if(!StringUtils.isNullOrEmpty(obj)){
				bean.setHighdate(DateUtil.convertShortStringToDate(obj));
			}
			
			try {
				service.save(bean);
			} catch (ExistException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	@Test
	public void testday(){
		
		//String solar=LunarDateUtil.lunarTosolar("2013-01-01");
		//System.out.println(solar);
		//assertEquals("2013-01-01",LunarDateUtil.solarTolunar(solar));
		
/*		String lunar=LunarDateUtil.solarTolunar("2013-02-10");
		System.out.println(lunar);
		
		lunar=LunarDateUtil.solarTolunar("2014-01-31");
		System.out.println(lunar);*/
		//assertEquals("2013-02-09",LunarDateUtil.lunarTosolar(lunar));
		
		Date day;
		Date startdate=DateUtil.convertShortStringToDate("2015-06-25");
		for(int i=0;i<1;i++){
			for(int j=0;j<21;j++){
				day=DateUtil.addDay(startdate,i,j);
				if(day.getMonth()==0&&day.getDate()==1){//1.1跳过
					System.out.println(day.getDate()+DateUtil.convertDateToShortString(day));
				}
				if(day.getMonth()==4&&(day.getDate()>=1&&day.getDate()<=3)){//5.1跳过
					System.out.println(day.getDate()+DateUtil.convertDateToShortString(day));
				}
				if(day.getMonth()==9&&(day.getDate()>=1&&day.getDate()<=7)){//10.1跳过
					System.out.println(day.getDate()+DateUtil.convertDateToShortString(day));
				}
				
				Date d=LunarDateUtil.solarTolunar(day);
				if(d.getMonth()==0&&(d.getDate()>=1&&d.getDate()<=7)){
					System.out.println(day.getDate()+DateUtil.convertDateToShortString(day));
				}
					
			}
		}
		
	}
	
	/**
	 * 2007-01-01
	 */
	@Test
	public void testBatchSave(){
		init();
		
		Date startdate=DateUtil.convertShortStringToDate("2015-07-06");
		int week=0;
		Date day=null;
		Date beforeday;
		
		for(int i=0;i<1;i++){
			for(int j=0;j<2;j++){
				
				beforeday=day;
				
				day=DateUtil.addDay(startdate,i,j);
				
				if(beforeday!=null&&day.compareTo(beforeday)==0){
					continue;
				}
				
				week=DateUtil.getWeekday(day);
				if(week>5){
					continue;
				}
				
				if(day.getMonth()==0&&day.getDate()==1){//1.1跳过
					continue;
				}
				if(day.getMonth()==4&&(day.getDate()>=1&&day.getDate()<=3)){//5.1跳过
					continue;
				}
				if(day.getMonth()==9&&(day.getDate()>=1&&day.getDate()<=7)){//10.1跳过
					continue;
				}
				
				Date d=LunarDateUtil.solarTolunar(day);
				if(d.getMonth()==0&&(d.getDate()>=1&&d.getDate()<=7)){
					continue;
				}
				
				getData(day);
				try {
					Thread.sleep(Double.valueOf(1000*60*Math.random()).intValue());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

	}
	
	@Test
	public void getData(Date tdate){
		
		HttpPostGet http=new HttpPostGet();
		//http://www.szse.cn/szseWeb/FrontController.szse?ACTIONID=7&AJAX=AJAX-TRUE&CATALOGID=1803&TABKEY=tab1&txtQueryDate=2015-06-12&REPORT_ACTION=search
		String ret=http.getData("http://www.szse.cn/szseWeb/FrontController.szse?ACTIONID=7&AJAX=AJAX-TRUE&CATALOGID=1803&TABKEY=tab1&txtQueryDate="+DateUtil.convertDateToShortString(tdate)+"&REPORT_ACTION=search","GB2312",true);

		if(ret==null||ret.indexOf("没有找到符合条件的数据")!=-1||ret.indexOf("！")!=-1)
			return;
		
	
		Map<String,String> data=TemplateDataUtil.getData(ret);
		
/*		for (String key : data.keySet()) {
			   System.out.println("key= "+ key + " and value= " + data.get(key));
		}*/
		
		batchSave(data,tdate);
		
	}
	
	@Test
	public void testSave() throws Exception {
		StockService service=(StockService)this.getInstance("stockService");
		Stock bean =new Stock();
		bean.setName("test");
		
		Map session=new Hashtable();
		session.put(IConstants.USER, bean);
		ServletActionContext.getContext().setSession(session);
//		servletContext.setAttribute(IConstants.USER, bean);
		
		
		Stock stock=service.save(bean);
		assertNotNull(stock);
		
		List<Stock> stocks=service.getList(" and name='test' and deleted is null ");
		if(stocks.size()>=1){
			service.delete(stocks.get(0).getId());//删除记录
		}
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdate() {
		StockService service=(StockService)this.getInstance("stockService");

		List<Stock> stocks=service.getList(" and name='test' ");
		
		if(stocks.size()>=1){
			Stock bean=stocks.get(0);
			
			bean.setName("test1");
			
			Map session=new Hashtable();
			session.put(IConstants.USER, bean);
			ServletActionContext.getContext().setSession(session);

			Stock stock=service.update(bean);
			assertNotNull(stock);
		}
	}

	@Test
	public void testGetByIdSerializable() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetByIdString() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetList() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetListString() {
		fail("Not yet implemented");
	}

	@Test
	public void testValid() {
		fail("Not yet implemented");
	}

}
