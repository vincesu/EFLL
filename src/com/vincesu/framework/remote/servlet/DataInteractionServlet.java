package com.vincesu.framework.remote.servlet;

import java.io.IOException;
import java.rmi.server.RemoteServer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;


import com.vincesu.framework.remote.RemoteService;
import com.vincesu.framework.remote.RemoteUtil;
import com.vincesu.framework.remote.RequestEntity;
import com.vincesu.framework.remote.ResponseEntity;
import com.vincesu.framework.util.BeanUtil;

@SuppressWarnings("serial")
public class DataInteractionServlet extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse resp) 
	{
		String serviceid = req.getParameter("serviceid");
		
		List<Map> data = null;
		ResponseEntity response = null;
		
		data = RemoteUtil.setData(req.getParameterMap());
		
		RequestEntity request = new RequestEntity(req.getSession());
		request.setData(data);
		
		try {
			
			response = RemoteService.execute(serviceid, request);
			JSONObject result = new JSONObject(response);
			result.remove("type");
			result.put("type", response.getTypeName());
			
			resp.setContentType("text/json;charset=UTF-8");
			resp.getWriter().print(result.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
//		JSONArray cells = new JSONArray();
//		
//		try {
//			
//			response = RemoteService.execute(serviceid, request);
//			
//			if(response.getData()!=null)
//			{
//				for(Map m : response.getData())
//				{
//					cells.put(JSONUtil.Map2JSON(m));
//				}
//			}
//			
//		} catch (Exception e) {
//			response.setType(ResponseEntity.RESPONSE_TYPE_ERROR);
//			response.setMessage(e.getMessage());
//		}
//		
//		try {
//			
//			JSONObject result = new JSONObject();
//			result.put("type", response.getTypeName());
//			result.put("message", response.getMessage());
//			result.put("data",cells);
//			
//			resp.setContentType("text/json;charset=UTF-8");
////			System.out.println(result.toString());
//			resp.getWriter().print(result.toString());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
	}
}
