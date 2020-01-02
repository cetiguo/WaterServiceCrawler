package util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	public static String getToday(){
		String todayStr = "";
		Date today = new Date();
		DateFormat sdf = SimpleDateFormat.getDateInstance();
		todayStr = sdf.format(today);
		
		String[] todayStrArray = todayStr.split("-");
		if(todayStrArray == null || todayStrArray.length == 0){
			System.out.println("获取当日日期时日期转换失败");
			return todayStr;
		}
		StringBuffer sb = new StringBuffer(todayStrArray[0]);
		for(int i=1;i<todayStrArray.length;i++){
			if(todayStrArray[i].length() == 1){
				todayStrArray[i] = "0" + todayStrArray[i];
			}
			sb.append("-");
			sb.append(todayStrArray[i]);
		}
		
		return sb.toString();
	}
	
	public static String getTodayTime(){
		String timeStr = "";
		Date todayTime = new Date();
		DateFormat sdf = SimpleDateFormat.getTimeInstance();
		timeStr = sdf.format(todayTime);
		
		String[] nowTimeStrArray = timeStr.split(":");
		if(nowTimeStrArray == null || nowTimeStrArray.length == 0){
			System.out.println("获取当日日期时间时转换失败");
			return timeStr;
		}
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<nowTimeStrArray.length;i++){
			if(nowTimeStrArray[i].length() == 1){
				nowTimeStrArray[i] = "0" + nowTimeStrArray[i];
			}
			if(sb != null && sb.length() >= 0){
				sb.append(":");
			}
			sb.append(nowTimeStrArray[i]);
		}
		String dateStr = getToday() + " " + sb.toString();
		
		return dateStr;
		
		
		
	}
}
