/**
 * author vince.su
 * 2012-09-27 
 */

var usergrid = null;
var userquerygrid = null;
var cd = null;

userquerygrid = new QueryGrid(
		"userform",
		[ {name:'rolename',type:'角色名称',text:'角色名称'},
		  {name:'username',type:'text',text:'用户名'}
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
		 "getUser",
		 "restoreUser",
		 "removeUser",
		 "restoreUser",
		 "pages/framework/sysmanager/user_dialog.html",
		 "user_form"
);

usergrid = $("#usergrid").jqGrid(
	{	
		url:'service/dataInteraction?serviceid=getUsers',
		mtype: "POST",
		datatype: "json", 
		colNames:['用户ID','用户名','角色ID','角色名称'], 
		colModel:[ 
				   {name:'id',index:'id', width:100},	
		           {name:'username',index:'username', width:120},
		           {name:'roleid',index:'roleid', width:120},
		           {name:'rolename',index:'rolename', width:120}
		         ],
		 width:mainComponent.mainPanelWidth-20,
		 height: 250,
		 rowNum:50, 
		 rowList:[50,100,150],
		 pager: '#userpager', 
		 //sortname: 'id',
		 viewrecords: true,
		 //sortorder: "desc", 
		 caption:"模块列表"

	}
);

jQuery("#usergrid").jqGrid('navGrid','#userpager',{edit:false,add:false,del:false});

userquerygrid.grid = usergrid;

userquerygrid.createDoubleClick();


function chooseRole()
{
	cd = new ChooseDialog(
			null,
			[ {name:'id',type:'text',text:'角色ID'},
			  {name:'rolename',type:'text',text:'名称'}],
			"getRoleList",
			['ID','名称'], 
			[ 
			   {name:'id',index:'id', width:200},	
	           {name:'rolename',index:'rolename', width:400}
	         ],
			function(param) {
				document.getElementById("roleId").value = param[0].id;
			},
			false
	); 
	cd.open();
}
