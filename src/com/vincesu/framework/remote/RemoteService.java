package com.vincesu.framework.remote;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.lang.reflect.Method;
import java.util.Vector;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import com.vincesu.framework.remote.RequestEntity;
import com.vincesu.framework.remote.ResponseEntity;
import com.vincesu.framework.remote.ServiceEntity;

public class RemoteService {

	private static Vector<ServiceEntity> _serviceVec;

	static {
		_serviceVec = new Vector<ServiceEntity>();

		File file = null;

		try {
				XmlPullParserFactory factory = XmlPullParserFactory
						.newInstance();
				factory.setNamespaceAware(true);
				XmlPullParser xpp = factory.newPullParser();
				file = new File(RemoteService.class.getClassLoader().getResource("/").getPath(), "services-config.xml");
				xpp.setInput(new FileReader(file.getAbsolutePath()));

				int eventType = xpp.getEventType();
				String tagName = null;
//				ServiceEntity se = new ServiceEntity(null, null, null);
				String sid=null,classname=null,method=null;
				int i = -1;

				while (eventType != xpp.END_DOCUMENT) {
					if (eventType == xpp.START_TAG) {
						tagName = xpp.getName();
						if (tagName.equals("serviceid")) {
							i = 1;
						} else if (tagName.equals("class")) {
							i = 2;
						} else if (tagName.equals("method")) {
							i = 3;
						}

					} else if (eventType == xpp.END_TAG) {
						if (xpp.getName().equals("service")) {
							RemoteService.addService(sid,classname,method);
						}
					} else if (eventType == xpp.TEXT) {
						switch (i) {
						case 1:
							sid = xpp.getText();
							i=-1;
							break;
						case 2:
							classname = xpp.getText();
							i=-1;
							break;
						case 3:
							method = xpp.getText();
							i=-1;
							break;
						}

					}
					eventType = xpp.next();
				}
//				System.out.println(RemoteService._serviceVec.size());

		} catch (Exception e) {
			System.out.println("加载service-config失败,错误信息：" + e.getMessage());
			new Exception("加载service-config失败,错误信息：" + e.getMessage());
		}

	}

	public static ResponseEntity execute(String serviceid, RequestEntity req)
			throws Exception {
		ResponseEntity response = null;
		ServiceEntity se = RemoteService.getServiceEntity(serviceid);
		if (se == null)
			throw new Exception("没有此service服务");

		try {
			Class c = Class.forName(se.getClassname());
			Object instance = c.newInstance();
			Method method = c.getMethod(se.getMethod(),
					new Class[] { RequestEntity.class });
			response = (ResponseEntity) method.invoke(instance,
					new Object[] { req });
		} catch (Exception e) {
			throw new Exception("执行service失败，错误信息：" + e.getMessage());
		}
		return response;
	}

	protected static void addService(String sid, String cn, String method) {
		RemoteService._serviceVec.add(new ServiceEntity(sid, cn, method));
	}

	protected static ServiceEntity getServiceEntity(String serviceid) {
		for (ServiceEntity se : RemoteService._serviceVec) {
			if (se.getServiceid().equals(serviceid)) {
				return se;
			}
		}
		return null;
	}

}
