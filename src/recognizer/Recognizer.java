package recognizer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.jsoup.nodes.Element;


public class Recognizer {
	protected static String[] myPositions = {"凤凰花园","光谷大道","高新四路","高新六路","长咀社区"};
	
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
		
		
		return elementsParam;
	}
}
