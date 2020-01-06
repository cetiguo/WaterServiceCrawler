package crawler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import email.EmailSender;
import html_analysis.HttpMethod;
import html_analysis.Page;
import recognizer.Recognizer;

public class Crawler {
	
	final static String httpweburl = "http://www.whwater.com/gsfw/tstz/";

	public static void main(String[] args) {
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
//			crawler.crawling(searchURL.toArray(new String[searchURL.size()]));
			crawler.crawling(httpweburl + targetURL[0] + "/" + userArea[3] + "/");
			crawler.crawling(httpweburl + targetURL[1] + "/" + userArea[3] + "/");
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
			Elements ds = tds.nextAll();	//日期数据
			
//			for(Element a : as) {
//				System.out.println(a.outerHtml());
//			}
			
			
			JSONArray list = new JSONArray();
			for(int i=0;i<as.size();i++){
				JSONObject json = new JSONObject();
				json.put("date", ds.get(i));
				json.put("notice", as.get(i));
				list.add(json);
				
				System.out.print(as.get(i).text());
				System.out.print("\t");
				System.out.print(ds.get(i).text());
				System.out.println();
			}
			
			Element[] toSendElements = Recognizer.recognizeElements(list);
			if(toSendElements == null || toSendElements.length == 0){
				System.out.println("-------程序结束-------");
				return;
			}
			System.out.println("------------------------下面开始发送邮件------------------------");
			for(int i =0; i <toSendElements.length;i++){
				String href = toSendElements[i].attr("href");
				toSendElements[i].attr("href", "http://www.whwater.com" + href);
				String toSendStr = toSendElements[i].outerHtml();
				System.out.print("停水通知:" + "\t\t");
				System.out.println(toSendStr);
				EmailSender.sendMessage(toSendStr);
			}
			System.out.println("------------------------邮件发送完毕------------------------");
			System.out.println("-------程序结束-------");
				
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
				Elements as = tds.select("a");
				Elements ds = tds.nextAll();
//				for(Element a : as) {
//					System.out.println(a.text());
//				}
				for(int j=0;j<as.size();j++){
					System.out.print(as.get(j).text());
					System.out.print("\t");
					System.out.print(ds.get(j).text());
					System.out.println();
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
