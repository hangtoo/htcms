package common.component.applet.httpclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.log4j.Logger;

public class HttpPostGet {
	private static HttpClient httpClient = new HttpClient();
	
	protected Logger log = Logger.getLogger(this.getClass());
	
	public String getData(String url,String jsessionid) {
		String uri=getURI(url,jsessionid);
		return getData(uri);
	}
	
	public String getData(String url,String charset,boolean needline){
		// 设置代理
		// httpClient.getHostConfiguration().setProxy("10.8.22.1", 8080);
		
		if(log.isDebugEnabled())
			log.debug(url);
		
		GetMethod getMethod = new GetMethod(url);
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler());
		
		getMethod.getParams().setParameter("http.protocol.allow-circular-redirects", true);
		getMethod.setFollowRedirects(false);
		getMethod.setRequestHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; QQDownload 1.7; .NET CLR 1.1.4322; CIBA; .NET CLR 2.0.50727)");
		
		try {
			int statusCode = httpClient.executeMethod(getMethod);
			System.out.println(statusCode);
			if (statusCode == HttpStatus.SC_OK) {
/*				System.out.println("Method Failed:" + getMethod.getStatusLine());
				byte[] responseBody = getMethod.getResponseBody();
				System.out.println("******以下为返回内容********");
				System.out.println(new String(responseBody));
				System.out.println("*******以下为请求路径********");
				System.out.println(getMethod.getPath());
				System.out.println(getMethod.getFollowRedirects());
				System.out.println("*******以下为数据长度********");
				System.out.println(getMethod.getResponseContentLength());
				System.out.println("*********以下为Params*********");
				System.out.println(getMethod.getParams());
				System.out.println("*********以下为URI********");
				System.out.println(getMethod.getURI());
				Header[] header = getMethod.getResponseHeaders();
				for (Object obj : header) {
					System.out.println("Header 内容：" + obj);
				}*/
				
				getMethod.getParams().setContentCharset(charset);
				InputStream in = getMethod.getResponseBodyAsStream();
				
				BufferedReader br = new BufferedReader(new InputStreamReader(in,charset));
				StringBuilder b = new StringBuilder();
				
				String line;
				while((line=br.readLine())!=null) {
					b.append(line);
					if(needline){
						b.append("\r\n");
					}
				}
				
				return b.toString();
			}
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			getMethod.releaseConnection();
		}
		return null;
	}
	
	public String getData(String url) {
		return getData(url,"UTF-8",true);
	}
	
	public String postData(String url,NameValuePair[] data,String jsessionid) throws HttpException, IOException {
		String uri=getURI(url,jsessionid);
		return postData(uri,data);
	}
	
	// 填入各个表单域的值
	public String postData(String url,NameValuePair[] data) throws HttpException, IOException {
		if(log.isDebugEnabled()){
			log.debug(url);
			for(int i=0;i<data.length;i++){
				log.debug(data[i].getName()+":"+data[i].getValue());
			}
		}
		PostMethod postMethod = new UTF8PostMethod(url);
		
		// 将表单的值放入postMethod中
		postMethod.setRequestBody(data);

		// 执行postMethod
		//httpClient.getHostConfiguration().setProxy("10.8.22.1", 8080);
		int statusCode=httpClient.executeMethod(postMethod);
		
		return postMethod.getResponseBodyAsString();
/*		log.debug(postMethod.getStatusLine());
		
		log.debug(new String(postMethod.getResponseBody(),"UTF-8"));
		log.debug(postMethod.getResponseBodyAsString());
		
		return statusCode;*/
	}

	public static class UTF8PostMethod extends PostMethod {
		public UTF8PostMethod(String url) {
			super(url);
		}

		@Override
		public String getRequestCharSet() {
			// return super.getRequestCharSet();
			return "UTF-8";
		}
	}
	
	private String getURI(String url,String jsessionid){
		int q=url.indexOf("?");
		
		if(q!=-1){
			url=url.substring(0,q)+";jsessionid="+jsessionid+url.substring(q);
		}else
			url=url+";jsessionid="+jsessionid;
		return url;
	}

}
