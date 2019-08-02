//作为表格编辑的辅助功能工具
var parentTab = self.parent.pubTab;
var pubEdit = {
	//在需要选择记录才能继续后续操作的方式下，作为一个一般标准版本
//	beforeSelect : function(grid, formType) {
//        var row = $(grid).datagrid('getSelected');
//        if (!row && (formType == 2 || formType == 3))
//        {
//            $.messager.alert('Warning','${action.text("public.rowselect")}');
//            return false;
//        };
//        return true;
//	},

	initColumns : function()
	{
		$(".txtCenter").parent().parent().css("text-align","center");
	},
	
	filterResizeColumn : function(field,width)
	{
			var filter=$(":input[filterName="+field+"]");
			if(filter.length>0)filter.width(width-20);
	},
		
	submitEditDialog : function(id, url) {
		$('#' + id + 'Form').form('submit', {  
    		url: url,  
    		onSubmit: function(){
    			return $(this).form('validate');
    		},  
    		success:function(data){
    			var json = $.parseJSON(data);
        		if (json.success)
        		{
        			parent.messagerShow({title:'Success', msg:json.msg});
        			$('#' + id + 'Grid').datagrid('reload');
        			$('#' + id + 'EditDlg').dialog('close');
        		} else
        			$.messager.alert('Error', json.msg);
    		}  
		});  
	},
	
	submitNormalForm : function(id, url, fn) {
		$('#' + id + 'Form').form('submit', {
    		url: url,  
    		success:function(data){
    			var json = $.parseJSON(data);
        		if (json.success)
        		{
        			if (fn)
        				fn(json);
        			parent.messagerShow({title:'Success22222', msg:'${action.text("public.success")}'});
        		} else
        			$.messager.alert('Error', json.msg);
    		}  
		});  
	},
	
	//编辑模式
	editType : function(id, isInsert) {
		if (isInsert)
			$('#' + id + 'Grid').data('formType', 1);
		else
			$('#' + id + 'Grid').data('formType', 2);
	},
	//判断是否是插入模式
	isInsert : function(id) {
		var formType = $('#' + id + 'Grid').data('formType');
		return (formType == 1);
	},
	
	//判断是否是编辑模式
	isEdit : function(id) {
		var formType = $('#' + id + 'Grid').data('formType');
		return (formType == 2);
	},
	
	activeLayout : function(value)
	{
		if (value == 'Y') {
			return  '<div class="active" style="cursor:pointer">&nbsp</div>' ;
		} else if (value == 'N') {
			return '<div class="noactive" style="cursor:pointer">&nbsp</div>' ;
		}else{
			return value;
		}
	},
	
	filters : function(grid)
	{
		var self=this;
		this.getFilter=function(filterName){
			if(filterName){
				var filter=$(":input[filterName="+fieldName+"]");
				if(filter.length>0)return filter; else return null;
			}
			var filters=[];
			var fieldNames = grid.datagrid('getColumnFields');
			fieldNames=fieldNames.concat(grid.datagrid('getColumnFields',true));
			for(var i=0;i<fieldNames.length;i++){
				var fieldName=fieldNames[i];
				var filter=$(":input[filterName="+fieldName+"]");
				if(filter.length>0)filters.push(filter);
			}
			return filters;
		};
		
		//获取选择框的值
		this.getFilterValue=function(filterName){
			if(filterName){
				var filter=self.getFilter(filterName);
				if(filter!=null)return filter.val(); else return null;
			}
			var filters=self.getFilter();
			var values={};
			for(var i=0;i<filters.length;i++){
				var name=filters[i].attr("filterName");
				var value=filters[i].val();
				if(value){values[name]=value;}
			}
			return values;
		};
		
		this.doFilter=function(param){
			grid.datagrid('load',param);
		};
		//清空筛选框
		this.clearFilter=function(filterName){
			if(filterName){
				var filter=self.getFilter(filterName);
				if(filter!=null)filter.val("");
			}else{
				var filters=self.getFilter();
				for(var i=0;i<filters.length;i++){
					filters[i].val("");
				}
			}
			self.doFilter(self.getFilterValue());
		};
		
		var filters=self.getFilter();
		for(var i=0;i<filters.length;i++)
		{
			var filter=filters[i];
			filter.bind("keypress",function(event){
				if(event.keyCode==13) {//响应回车事件
					self.doFilter(self.getFilterValue());
				 }
			});
			
			if(filter.is("select")){//响应下拉框的change事件
				filter.bind("change",function(){
					self.doFilter(self.getFilterValue());
				});
			}
		};
		
	},
	
	linkLayout : function(value)
	{
		return  '<a href="javascript:void(0)">' + value + '</a>';
	}
}