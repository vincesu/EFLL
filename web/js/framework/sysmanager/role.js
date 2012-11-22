/**
 * author vince.su
 * 2012-09-26
 */

var rolegrid = null;
var rolequerygrid = null;
var cd = null;


rolequerygrid = new QueryGrid(
		"roleform",
		[ {name:'id',type:'text',text:'角色ID'},
		  {name:'rolename',type:'text',text:'名称'}
//			  {name:'name',type:'text',text:'名称'}
//		  {name:'bt',type:'text',text:'省厅回盘起始时间',classname:'My97DatePicker'},
//		  {name:'et',type:'text',text:'省厅回盘截止时间',classname:'My97DatePicker'},
//		  {name:'yc0076',type:'select',text:'发送审核状态',options: [
//		                                         {value:"'038000','041200'",text:'全部',selected:"selected"},
//		                                         {value:"041200",text:'已发送审核'},
//		                                         {value:"038000",text:'未发送审核'}
//		                                         ] }
		 ],
		 null,
		 "getRole",
		 "restoreRole",
		 "removeRole",
		 "restoreRole",
		 "pages/framework/sysmanager/role_dialog.html",
		 "role_form"
);

rolegrid = $("#rolegrid").jqGrid(
	{	
		url:'service/dataInteraction?serviceid=getRoleList',
		mtype: "POST",
		datatype: "json", 
		colNames:['ID','名称'], 
		colModel:[ 
				   {name:'id',index:'id', width:100},	
		           {name:'rolename',index:'rolename', width:120}
		         ],
		 width:mainComponent.mainPanelWidth-20,
		 height: 250,
		 rowNum:50, 
		 rowList:[50,100,150],
		 pager: '#rolepager', 
		 //sortname: 'id',
		 viewrecords: true,
		 //sortorder: "desc", 
		 caption:"模块列表"

	}
);

jQuery("#rolegrid").jqGrid('navGrid','#rolepager',{edit:false,add:false,del:false});

rolequerygrid.grid = rolegrid;

rolequerygrid.createDoubleClick();


function getPermissions()
{
	var selectedId = rolequerygrid.grid.jqGrid("getGridParam", "selrow");
	if(selectedId == null)
	{
		art.dialog("请选择一条记录");
		return;
	}
	var rowData = rolequerygrid.grid.jqGrid("getRowData", selectedId);
	cd = new ChooseDialog(
			"&id="+rowData.id,
			[ {name:'moduleid',type:'text',text:'模块ID'},
			  {name:'name',type:'text',text:'模块名称'}],
			"getPermissions",
			['模块ID','模块名称'], 
			[ 
			   {name:'moduleid',index:'moduleid', width:200},	
	           {name:'name',index:'name', width:400}
	         ],
			null
	);
	cd.open();
}

function removePermissions()
{
	var selectedId = rolequerygrid.grid.jqGrid("getGridParam", "selrow");
	if(selectedId == null)
	{
		art.dialog("请选择一条记录");
		return;
	}
	var rowData = rolequerygrid.grid.jqGrid("getRowData", selectedId);
	cd = new ChooseDialog(
			"&id="+rowData.id,
			[ {name:'moduleid',type:'text',text:'模块ID'},
			  {name:'name',type:'text',text:'模块名称'}],
			"getPermissions",
			['模块ID','模块名称'], 
			[ 
			   {name:'moduleid',index:'moduleid', width:200},	
	           {name:'name',index:'name', width:400}
	         ],
			function(param) {
				param[0]["roleid"]=rowData.id;
				var rs = new RemoteService("removePermissions");
				rs.send(param,suc,null);
			}
	); 
	function suc(resp) {
		if(resp == null || resp.type == "error")
			art.dialog("删除权限失败");
		else
			art.dialog("删除权限成功");
	}
	cd.open();
}

function addPermissions()
{
	var selectedId = rolequerygrid.grid.jqGrid("getGridParam", "selrow");
	if(selectedId == null)
	{
		art.dialog("请选择一条记录");
		return;
	}
	var rowData = rolequerygrid.grid.jqGrid("getRowData", selectedId);
	cd = new ChooseDialog(
			"&roleid="+rowData.id,
			[
			  {name:'id',type:'text',text:'模块ID'},
			  {name:'fid',type:'text',text:'父节点id'},
			  {name:'name',type:'text',text:'模块名称'}
			],
			"getPermissionsNotOwn",
			['模块ID','父节点id','模块名称','模块地址','模块标题'], 
			[ 
			   {name:'id',index:'id', width:100},	
	           {name:'fid',index:'fid', width:100},
			   {name:'name',index:'name', width:200},
			   {name:'address',index:'address', width:400},
			   {name:'title',index:'title', width:200}
	         ],
			function(param) {
				param[0]["roleid"]=rowData.id;
				var rs = new RemoteService("addPermissions");
				rs.send(param,suc,null);
			}
	); 
	function suc(resp) {
		if(resp == null || resp.type == "error")
			art.dialog("添加权限失败");
		else
			art.dialog("添加权限成功");
	}
	cd.open();
}