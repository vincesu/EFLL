/**
 * author vince.su
 * 2012/9/4
 */

function RemoteService(serviceid)
{
	this._serviceid =serviceid; 
}

RemoteService.prototype.send = function(param,callback,errorFunc,message)
{
	if(this._serviceid == null)	return;
	var messagedislog = null;
	if(message !=null)
	{
		messagedislog = art.dialog({
		    content: message,
		    lock: true
		});
	}
	var data = {data:param,serviceid:this._serviceid};
	var responseEntity = null;
	$.ajax({
		type: "post",
		url: "service/dataInteraction",
		cache: false,
		data: data,
		dataType: "json",
		error: function(a,b,c) {
			if(messagedislog!=null)
				messagedislog.close();
			if(errorFunc!=null)
			{
				responseEntity = {type:"error",message:b};
				errorFunc(responseEntity);
			}
		},
		success: function(data, textStatus) {
			if(messagedislog!=null)
				messagedislog.close();
			if(callback!=null)
				callback(data);
		}
	});
};

function FormUtil() {}

FormUtil.prototype.setValue = function(formid,data)
{
	var item = document.getElementById(formid);
	this._setValue(item, data);
};

FormUtil.prototype._setValue = function(item,data)
{
	if(item.tagName.toUpperCase() == "SELECT" || item.children.length == 0)
	{
		if(item.name != null && item.name!="")
		{
			if(data == null)
				item.value = null;
			else if(data[item.name] != null)
				item.value = data[item.name];
		}
		
	} else
	{
		var nodes = item.children;
		for(var i=0;i<nodes.length;i++)
		{
			this._setValue(nodes[i], data)
		}
	}
};

FormUtil.prototype.getValue = function(formid)
{
	var result = {};
	var item = document.getElementById(formid);
	this._getValue(item, result);
	return result;
};

FormUtil.prototype._getValue = function(item,data)
{
	if(item.tagName.toUpperCase() == "SELECT" || item.children.length == 0)
	{
		if(item.name!=null && item.name!="")
			data[item.name] = item.value;
		
	} else
	{
		var nodes = item.children;
		for(var i=0;i<nodes.length;i++)
		{
			this._getValue(nodes[i], data)
		}
	}
};

function getParam(name)
{
    var reg = new RegExp("(^|\\?|&)"+ name +"=([^&]*)(\\s|&|$)", "i"); 
    if (reg.test(location.href))
    {
    	return unescape(RegExp.$2.replace(/\+/g, " "));
    }
    return null;
}

function getQueryStringRegExp(name)
{
    var reg = new RegExp("(^|\\?|&)"+ name +"=([^&]*)(\\s|&|$)", "i"); 
    if (reg.test(location.href))
    {
    	return unescape(RegExp.$2.replace(/\+/g, " "));
    }
    return null;
}

//快排类加二分查找
function Half(sAry,compare)
{
	this.compareFunc = compare;
	this.sortArray = sAry;
}

Half.prototype.swap = function(i,j)
{
	if(this.sortArray == null || i==j)	return;
	var tmp = this.sortArray[i];
	this.sortArray[i] = this.sortArray[j];
	this.sortArray[j] = tmp;
};

Half.prototype.partition = function(low,high)
{
	var pivot,pos,i,j;
	pos = low;
	pivot = this.sortArray[pos];
	for(i=low+1;i<=high;i++)
	{
		if (this.compareFunc(pivot,this.sortArray[i])==1)
		{
			pos++;
			this.swap(pos,i);
		}
	}
	this.swap(low,pos);
	return pos;
};

Half.prototype.quickSort = function(low,high)
{
	var pos = null;
	if (low<high)
	{
		pos = this.partition(low,high);
		this.quickSort(low,pos-1);
		this.quickSort(pos+1,high);
	}
};

/*
 * 快排
 */
Half.prototype.QuickSort = function()
{
	if(this.sortArray == null || this.compareFunc == null)	return;
	this.quickSort(0,this.sortArray.length-1);
};

/*
 * 二分查找
 */
Half.prototype.Find = function(target)
{
	if(this.sortArray == null || this.compareFunc == null)	return;
	var mid = null,low=0,high=this.sortArray.length-1,tmp;
	mid = (low+high)/2;
	var ok = false;
	while(low<=high)
	{
		mid = Math.floor((low+high)/2);
		tmp = this.compareFunc(target,this.sortArray[mid]); 
		if(tmp == 0)
		{
			ok=true;
			break;
			
		} else if(tmp == 1)
		{
			low = mid+1;
			
		} else
		{
			high = mid-1;
		}
	}
	if(ok)	return mid;
	else	return null;
};

function replaceNode(newNode,oldNode) 
{
	var p = oldNode.parentNode;
	
	if(!p)	return;
	
	p.replaceChild(newNode,oldNode);
	
}


