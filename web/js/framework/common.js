/**
 * author vince.su
 * 2012-09-26
 */

function ChooseDialog(
		urlparam,
		queryfield,
		serviceid,
		colnames,
		colmodel,
		execFunc,
		multiselect
	)
{
	this.urlparam = urlparam;
	if(this.urlparam == null)
		this.urlparam="";
	this.queryfield = queryfield;
	this.serviceid = serviceid;
	this.colnames = colnames;
	this.colmodel = colmodel;
	this.execFunc = execFunc;
	this.createCompleted = null;
	if(multiselect == null)
		this.multiselect = true;
	else
		this.multiselect = multiselect;
}

ChooseDialog.prototype.queryfieldid = "choosedialogform";
ChooseDialog.prototype.gridid = "choosedialoggrid";
ChooseDialog.prototype.pagerid = "choosedialogpager";

ChooseDialog.prototype.open = function()
{
	var deity = this;
	this.dialog = art.dialog(
		{
			id: "choosedialog",
			title: "",
			ok: function () {
				if(deity.execFunc!=null)
				{
					var param = new Array();
					if(deity.grid.getGridParam().multiselect)
					{
						var selectedIds = deity.grid.jqGrid("getGridParam", "selarrrow");
						for(var i in selectedIds)
						{
							param[i] = deity.grid.jqGrid("getRowData", selectedIds[i]);
						}
					} else
					{
						debugger;
						var selectedId = deity.grid.jqGrid("getGridParam", "selrow");
						if(selectedId != null)
							param[0] = deity.grid.jqGrid("getRowData", selectedId);
					}
					deity.execFunc(param);
				}
			},
			cancelVal: '关闭',
			cancel: true
		}
	);
	
	$.ajax({
	    url: "pages/framework/dialog/ChooseDialog.html",
	    success: function (data) {
	        deity.dialog.content(data);
	        
	        deity.grid = $("#"+deity.gridid).jqGrid(
	        		{	
	        			url:'service/dataInteraction?serviceid='+deity.serviceid+deity.urlparam,
	        			mtype: "POST",
	        			datatype: "json", 
	        			colNames:deity.colnames, 
	        			colModel:deity.colmodel,
//		        			autowidth: true,
	        			height: 210,
	        			rowNum:50, 
	        			rowList:[50,100,150],
	        			pager: '#'+deity.pagerid, 
	        			//sortname: 'id',
	        			viewrecords: true,
	        			//sortorder: "desc", 
	        			caption:"请选择",
	        			multiselect: deity.multiselect
	        		}
	        	);
	        	
	        	deity.querygird = new QueryGrid(
	        			deity.queryfieldid,
	        			deity.queryfield,
	        			deity.grid,
	        			null,
	        			null,
	        			null,
	        			null,
	        			null,
	        			null
	        			);
	        	
	        	if(deity.createCompleted !=null)
	        		deity.createCompleted();
	        	
	        	deity.dialog.position("50%","50%");
	        
	    },
	    cache: false
	});
};

