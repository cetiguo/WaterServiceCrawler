package crawler;

import java.util.ArrayList;
import java.util.List;

import html_analysis.HttpMethod;
import html_analysis.Page;

public class Crawler {
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Crawler crawler = new Crawler();
		String[] targetURL = new String[]{"tfxts","jhxts"};	//突发性停水、计划性停水
		//区域
		String[] userArea = new String[]{"wc","hy","hk","dhgx"};
		
		List<String> searchURL = new ArrayList<String>();
		for(int i=0;i<targetURL.length;i++){
			for(int j=0;j<userArea.length;j++){
				searchURL.add("https://whwater.com/gsfw/tstz/" + targetURL[i] + "/" + userArea[j] + "/");
			}
		}
		if(searchURL == null || searchURL.size() == 0){
			crawler.crawling(searchURL.toArray(new String[searchURL.size()]));
		}else{
			System.out.println("未选择网址,进程结束！");
		}
		
	}
	
	public void crawling(String[] urls){
		if(urls == null || urls.length == 0){
			return;
		}
		for(int i=0;i<urls.length;i++){
			Page page = HttpMethod.sendRequestAndGetResponse(urls[i]);
			
		}
		
	}

}
