/**
 * author vince.su
 * 2012-09-22
 */

var mainComponent = new components("mainComponent","content");
var fu = new FormUtil();

$(function() {
	mainComponent.addAfterSelectTreeNodeEvent(function() {
		var s = null;
		var o = null;
		var comboxArray = $(".combox");
		
		for(var i=0;i<comboxArray.length;i++)
		{
			if(comboxArray[i].getAttribute("code") == null || comboxArray[i].getAttribute("code") == "")
				continue;
			
			s = document.createElement("select");
			
			var d = mainComponent.getEncodingList(comboxArray[i].getAttribute("code").toString());
			
			for(var j in d)
			{
				o = document.createElement("option");
				o.setAttribute("value", d[j].fieldValue);
				o.innerHTML=d[j].codingValue;
				s.appendChild(o);
			}
			
			s.setAttribute("id", comboxArray[i].getAttribute("id"));
			s.setAttribute("code", comboxArray[i].getAttribute("code"));
			s.setAttribute("class", "combox");
			
			replaceNode(s,comboxArray[i]);
		}
	});
	
	mainComponent.addLoginInitEvent(function() {
		var rs = new RemoteService("getEncoding");
		rs.send(null,suc,err,"正在获得编码表");
		function suc(resp) {
			mainComponent.encodeingArray = resp.data;
		}
		function err(resp) {
			alert("加载编码表失败!");
		}
	});
	
	init();
});

function init()
{
	var rs = new RemoteService("islogin");
	rs.send(null,suc,null,"正在登陆");
	function suc(resp)
	{
		if(resp == null || resp.type == "error")	return;
		if (resp.message == "notlogin") 
		{
			$("#content").load("pages/framework/login/login.html");

		} else 
		{
			mainComponent.createContent(resp.data);
			if(mainComponent.loginInitEvent)
				mainComponent.loginInitEvent();
		}
	}
}

function login()
{
	var param = new Array();
	param[0] = fu.getValue("login_form");
	var rs = new RemoteService("login");
	rs.send(param,suc,null,"正在登陆");
	function suc(resp)
	{
		if(resp == null || resp.type == "error")
		{
			alert("用户名或密码错误！");
			return;
		}
		mainComponent.createContent(resp.data);
		if(mainComponent.loginInitEvent)
			mainComponent.loginInitEvent();
	}
	function err(resp)
	{
		alert("登入失败");
	}
}
