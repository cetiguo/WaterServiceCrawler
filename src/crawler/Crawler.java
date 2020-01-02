package crawler;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import html_analysis.HttpMethod;
import html_analysis.Page;

public class Crawler {
	
	final static String httpweburl = "http://www.whwater.com/gsfw/tstz/";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Crawler crawler = new Crawler();
		String[] targetURL = new String[]{"tfxts","jhxts"};	//突发性停水、计划性停水
		//区域
		String[] userArea = new String[]{"wc","hy","hk","dhgx"};
		
		List<String> searchURL = new ArrayList<String>();
		for(int i=0;i<targetURL.length;i++){
			for(int j=0;j<userArea.length;j++){
				searchURL.add(httpweburl + targetURL[i] + "/" + userArea[j] + "/");
			}
		}
		if(searchURL == null || searchURL.size() == 0){
			System.out.println("未选择网址,进程结束！");
		}else{
			crawler.crawling(searchURL.toArray(new String[searchURL.size()]));
//			crawler.crawling(httpweburl + targetURL[0] + "/" + userArea[3] + "/");
//			crawler.crawling(httpweburl + targetURL[1] + "/" + userArea[3] + "/");
		}
		
	}
	
	public void crawling(String url) {
		if(url == null || "".equals(url)) 
			return;
		if(!url.endsWith("/")) {
			url += "/";
		}
		try {
			Page page = HttpMethod.sendRequestAndGetResponse(url);
			Document doc = page.getDoc();
			Elements tds = doc.select("td .liebiaobiaoti");
			Elements as = tds.select("a");
			for(Element a : as) {
				System.out.println(a.text());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void crawling(String[] urls){
		if(urls == null || urls.length == 0){
			return;
		}
		try {
			for(int i=0;i<urls.length;i++){
				if(!urls[i].endsWith("/")) {
					urls[i] += "/";
				}
				Page page = HttpMethod.sendRequestAndGetResponse(urls[i]);
				Document doc = page.getDoc();
				Elements tds = doc.select("td .liebiaobiaoti");
				Elements as = tds.nextAll();
//				Elements as = tds.select("a");
				for(Element a : as) {
					System.out.println(a.text());
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
