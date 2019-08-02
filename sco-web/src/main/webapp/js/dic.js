var Dic={
	Active:[{id:'Y',text:'激活'},{id:'N',text:'禁用'}],
	Sex:[{id:'M',text:'男'},{id:'F',text:'女'}],
	//用户所属组织类型
	OrgTypes : [ {
		id : 'S',
		text : '供应商',
		href:'supplier_showSupplierForUserOrgMain_1.html'
	}, {
		id : 'R',
		text : '零售商',
		href:'retailer_showRetailerForUserOrgMain_1.html'
	} ],
	//配置数据权限菜单，forUser表示用户数据权限，forRole表示角色数据权限
	DataPowerTypes : [ {
		id : 'S',
		text : '供应商',
		iconCls : 'supplier',
		href : 'supplier_showSupplierDataPowerMain_1.html',
		funcKey :'com.escm.datapower.supplier',
		forUser:true,
		forRole:false
	}, {
		id : 'R',
		text : '零售商',
		iconCls : 'role',
		href : 'retailer_showRetailerDataPowerMain_1.html',
		hasFunc :'${action.hasFuncPower("com.escm.datapower.retailer")}',
		forUser:true,
		forRole:true
	} ],
	//主要用于在表格中换对应的类型ID转换为类型TEXT
	formatter:function(name){
		return function(value){
			var text=value;
			$.each(Dic[name],function(i,obj){
				if(value==obj.id){text=obj.text;return false;}
			});
			return text;
		};
	},
	//根据ID查找一个类型的TEXT
	findById:function(name,id){
		var result=null;
		$.each(Dic[name],function(i,obj){
			if(id==obj.id){result=obj;return false;}
		});
		return result;
	}
};
