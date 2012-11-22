/**
 * author vince.su
 * 2012-10-21
 */

function components(id,contentid) 
{
	// 变量名 也是标识id 如：var theId = new components("theId","content");
	this.id = id;
	// 主div id
	this.contentid = contentid;
	// 导航栏id
	this.navigationId = "navigation_"+this.contentid;
	// 主视图div id
	this.mainpanelId = "mainpanel_"+this.contentid;
	// 选项卡div id
	this.tabId = "tab_"+this.contentid;
	// footer div id
	this.footerId = "footer_"+this.contentid;
	//主显示区域宽度
	this.mainPanelWidth = null;
	
	/*
	 * 事件
	 */
	this.events = {
			afterSelectTreeNodeEvent : null,
			loginInitEvent: null
			
	};
	
	/*
	 * 组件 
	 */
	//编码表
	this.encodeingArray = null;
	//模块树形节点
	this.tree = null;
	//选项卡控件
	this.tabs = null;
}

components.prototype.trees = new Array();

/*
 * 事件
 */
components.prototype.addAfterSelectTreeNodeEvent = function(func) {
	this.addEvent("afterSelectTreeNodeEvent",func);
};

components.prototype.afterSelectTreeNodeEvent = function() {
	if(this.events["afterSelectTreeNodeEvent"])
		this.events["afterSelectTreeNodeEvent"]();
};

components.prototype.addLoginInitEvent = function(func) {
	this.addEvent("loginInitEvent",func);
};

components.prototype.loginInitEvent = function() {
	if(this.events["loginInitEvent"])
		this.events["loginInitEvent"]();
};

components.prototype.addEvent = function(event,func) {
	
	if(!func)	return;
	
	if(this.events[event])  
	{
		this.events[event] = function() {
			this.events[event]();
			func();
		};
		
	} else  {
		
		this.events[event] = func;
	}
};

/*
 * 创建主框架
 */
components.prototype.createContent = function(data) {
	
	var deity = this;
	
	if(document.getElementById(this.contentid) == null)
	{
		alert("无法找到id为"+this.contentid+"的主控件");
		return;
	}
	document.getElementById(this.contentid).innerHTML="<div id=\""+this.navigationId+"\" class=\"navigation\"></div><div id=\""+this.mainpanelId+"\" class=\"mainpanel\"><div id=\""+this.tabId+"\"></div>	<div id=\""+this.footerId+"\" class=\"footer\"></div></div>";
	this.createTree();
	
	for(var i=0;i<data.length;i++)
	{
		if(data[i].address == null || data[i].address == "")
			this.tree.add(data[i].id,data[i].fid,data[i].name,"",data[i].title);
		else
			this.tree.add(data[i].id,data[i].fid,data[i].name,"javascript:"+this.id+".selectTreeNode("+data[i].id+",'"+data[i].name+"','"+data[i].address+"');",data[i].title);
	}
	this.mainPanelWidth = document.getElementById(this.mainpanelId).scrollWidth;
	document.getElementById(this.navigationId).innerHTML = this.tree.toString();
	this.tabs = new TabPanel({  
	        renderTo:deity.tabId,  
	        width:deity.mainPanelWidth,  
	        height:500,  
	        //border:'none',  
	        active : 0,
	        //maxLength : 10,  
	        items : [
	            {id:'-1',title:'主页',html:"&nbsphello world!",closable: true}
	        ]
	    });
};

components.prototype.createTree = function()
{
	var l = this.trees.length;
	this.tree = new dTree(this.id+'.trees['+l.toString()+']');
	this.trees[l] = this.tree;
	this.tree.add(0,-1,"系统");
	return this.tree;
};

components.prototype.selectTreeNode = function(id,title,url)
{
	var deity = this;
	$.ajax({
	    url: url,
	    success: function (data) {
	    	deity.tabs.addTab({id:id.toString(),
	    	    title:title ,
	    	    html:data,
	    	    closable: true,
	    	    disabled:false,
	    	    icon:"css/images/new.gif"
	    	 });
	    	if(deity.afterSelectTreeNodeEvent)
	    		deity.afterSelectTreeNodeEvent();
	    },
	    cache: false
	});
};

components.prototype.getEncodingList = function(fieldName) {
	
	var result = new Array();
	
	if(this.encodeingArray)
	{
		for(var i=0;i<this.encodeingArray.length;i++)
		{
			if(this.encodeingArray[i].fieldName == fieldName)
			{
				result.push(this.encodeingArray[i]);
			}
		}
	}
	
	return result;
};

