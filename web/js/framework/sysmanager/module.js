/**
 * author vince.su
 * 2012-09-25
 */

var modulegrid = null;
var modulequerygrid = null;

modulequerygrid = new QueryGrid(
		"moduleform",
		[ {name:'id',type:'text',text:'模块ID'},
		  {name:'fid',type:'text',text:'父模块ID'},
		  {name:'name',type:'text',text:'名称'}
//		  {name:'bt',type:'text',text:'省厅回盘起始时间',classname:'My97DatePicker'},
//		  {name:'et',type:'text',text:'省厅回盘截止时间',classname:'My97DatePicker'},
//		  {name:'yc0076',type:'select',text:'发送审核状态',options: [
//		                                         {value:"'038000','041200'",text:'全部',selected:"selected"},
//		                                         {value:"041200",text:'已发送审核'},
//		                                         {value:"038000",text:'未发送审核'}
//		                                         ] }
		 ],
		 null,
		 "getModule",
		 "restoreModule",
		 "removeModule",
		 "restoreModule",
		 "pages/framework/sysmanager/module_dialog.html",
		 "module_form"
);

modulegrid = $("#modulegrid").jqGrid(
	{	
		url:'service/dataInteraction?serviceid=getModuleList',
		mtype: "POST",
		datatype: "json", 
		colNames:['节点ID','父节点ID','名称','地址','标题'], 
		colModel:[ 
				   {name:'id',index:'id', width:100},	
				   {name:'fid',index:'fid', width:100},
		           {name:'name',index:'name', width:120},
		           {name:'address',index:'address', width:250},
		           {name:'title',index:'title', width:120}
		         ],
		 width:mainComponent.mainPanelWidth-20,
		 height: 250,
		 rowNum:10, 
		 rowList:[10,50,100],
		 pager: '#modulepager', 
		 //sortname: 'id',
		 viewrecords: true,
		 //sortorder: "desc", 
		 caption:"模块列表"

	}
);

jQuery("#modulegrid").jqGrid('navGrid','#modulepager',{edit:false,add:false,del:false});

modulequerygrid.grid = modulegrid;

modulequerygrid.createDoubleClick();

