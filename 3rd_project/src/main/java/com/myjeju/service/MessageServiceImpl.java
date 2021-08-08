package com.myjeju.service;

import java.util.HashMap;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;


@Service("MessageService")
public class MessageServiceImpl implements MessageService  {
	
	@Override
	public void sendMessage(String toNumber, String randomNumber) {

		Message coolsms = new Message("NCSJLYPVHAUND1TW", "ASBO6T5TVWQGMDNXARLFLIDCTTZZKIFC");

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("to", toNumber);
		params.put("from", "01093812489");
		params.put("type", "SMS");
		params.put("text", "[JEJU ISLAND] 인증번호 "+randomNumber+" 를 입력하세요.");
		params.put("app_version", "test app 1.2"); // application name and version

		try {
			JSONObject obj = (JSONObject) coolsms.send(params);
			System.out.println(obj.toString());
		} catch (CoolsmsException e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCode());
		}
	}

}