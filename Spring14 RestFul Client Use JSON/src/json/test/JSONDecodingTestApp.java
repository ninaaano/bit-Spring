package json.test;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;


public class JSONDecodingTestApp {

	public static void main(String[] args) {
		
		String data = "{\"address\":\"����\",\"age\":1000,\"name\":\"ȫ�浿\"}";
		JSONObject jsonObj = (JSONObject)JSONValue.parse(data);
		
		System.out.println("JSON Object Ȯ�� : "+jsonObj);
		
		System.out.println("==> JSON Object Data ����");
		System.out.println(jsonObj.get("address"));
		System.out.println("\n\n");
		
		//////////////////////////////////////////////
		
		String arrayDate = "[\"����\",1000,\"ȫ�浿\"]";
		JSONArray jsonArray = (JSONArray)JSONValue.parse(arrayDate);
		
		System.out.println("JSON Object Ȯ�� : "+jsonObj);
		
		System.out.println("==> JSON Object Data ����");
		System.out.println(jsonArray.get(0));
	}
}
