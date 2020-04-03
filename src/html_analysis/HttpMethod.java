package html_analysis;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
//import org.apache.commons.httpclient.*;
//import org.apache.commons.httpclient.methods.GetMethod;
//import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


public class HttpMethod {
	public static Page sendRequestAndGetResponse(String url){
		Page page = null;
		/**
		 * 旧版本使用org.apache.commons.httpclient包
		 */
//		HttpClient httpClient = new HttpClient();
//		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
//		GetMethod getMethod = new GetMethod(url);
//		getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 5000);
//		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
//		
//		try{
//			int status = httpClient.executeMethod(getMethod);
//			if(status != HttpStatus.SC_OK){
//				System.err.println("Method faild:" + getMethod.getStatusLine());
//			}
//			byte[] responseBody = getMethod.getResponseBody();
//			String contentType = getMethod.getResponseHeader("Content-Type").getValue();
//			page = new Page(responseBody,url,contentType);
//		}catch(Exception e1){
//			e1.printStackTrace();
//		}finally{
//			getMethod.releaseConnection();
//		}
		
		
		/**
		 * 以下为使用新包HttpComponents.httpclient
		 */
		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet request = new HttpGet(url);
		CloseableHttpResponse response = null;
		
		try{
			response = client.execute(request);
			HttpEntity entity = response.getEntity();
			int status = response.getStatusLine().getStatusCode();
			if(status != HttpStatus.SC_OK){
				System.err.println("Method faild:" + response.getStatusLine().toString());
			}
			byte[] responseBody = EntityUtils.toByteArray(entity);
			String contentType = response.getFirstHeader("Content-Type").getValue();
			page = new Page(responseBody, url, contentType);
			
			return page;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			if(client != null){
				try {
					client.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(request != null){
				request.releaseConnection();;
			}
			if(response != null){
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
}
