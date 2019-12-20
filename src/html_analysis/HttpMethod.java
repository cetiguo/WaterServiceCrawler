package html_analysis;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

public class HttpMethod {
	public static Page sendRequestAndGetResponse(String url){
		Page page = null;
		HttpClient httpClient = new HttpClient();
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
		GetMethod getMethod = new GetMethod(url);
		getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 5000);
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
		
		try{
			int status = httpClient.executeMethod(getMethod);
			if(status != HttpStatus.SC_OK){
				System.err.println("Method faild:" + getMethod.getStatusLine());
			}
			byte[] responseBody = getMethod.getResponseBody();
			String contentType = getMethod.getResponseHeader("Content-Type").getValue();
			page = new Page(responseBody,url,contentType);
		}catch(Exception e1){
			e1.printStackTrace();
		}finally{
			getMethod.releaseConnection();
		}
		return page;
	}
}
