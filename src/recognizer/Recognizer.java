package recognizer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.jsoup.nodes.Element;

import com.alibaba.fastjson.JSONArray;

import util.DateUtil;


public class Recognizer {
	protected static String[] myPositions = {"凤凰花园","光谷大道","高新四路","高新六路","长咀社区"};
	
	public static Element[] recognizeElements(JSONArray list){
		if(list == null || list.size() == 0){
			System.out.println("无待识别的HTML页面元素数据");
			return null;
		}
		List<Element> eleList = new ArrayList<Element>();
		for(int i = 0;i < list.size(); i ++){
			Element dateEle = ((Element)list.getJSONObject(i).get("date"));
			String dateStr = dateEle.text();
			String today = DateUtil.getToday();
//			String today = "2020-01-04";
			boolean timeFlag = false;
			boolean posiFlag = false;
			if(today.equals(dateStr)) 
				timeFlag = true;
			for(int j=0;j<myPositions.length;j++){
				if(((Element)list.getJSONObject(i).get("notice")).text().indexOf(myPositions[j]) > -1){
					posiFlag = true;
					break;
				}
			}
			if(timeFlag && posiFlag){
//			if(timeFlag){
				eleList.add((Element)list.getJSONObject(i).get("notice"));
			}
		}
		
		return eleList.toArray(new Element[eleList.size()]);
	}
	
	public static Element[] recognizeElements(Element[] elementsParam){
		if(elementsParam == null || elementsParam.length == 0){
			System.out.println("无待识别的HTML页面元素数据");
			return null;
		}
		List<Element> eleList = new ArrayList<Element>();
		for(int i = 0;i < elementsParam.length; i ++){
			String text = elementsParam[i].text();
			text.substring(0,text.indexOf("日")+1);
		}
		
		
		return eleList.toArray(new Element[eleList.size()]);
	}
}
