$(document).ready( function(){
	if ($("#itabs"))
	{
		$("#itabs div.itabdiv").click(function(){$(this).find('a').trigger('click')});
		$("#itabs").idTabs(function(id,list,set){
			$(".activeTab").removeClass('activeTab').addClass('freeTab');
			$(this).parent().removeClass('freeTab').addClass('activeTab');
			$(id).css("display","");
	     	return true; 
	  	});
	}
});