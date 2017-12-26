/***

	管理模块的JS效果

***/
$(function(){
	//搜索
	$(".card .list-group-item").click(function(e){
		
		
		//移除样式
		$(".list-group-item").removeClass("active");
		$(this).addClass("active");
		
		var url = e.target.getAttribute('url');
		//加载其他内容到右侧模块
		$.ajax({
			url:url , 
			success:function(data){
				$("#rightContainer").html(data);
			},
			error:function(){
				alert("加载失败!");
			}
		})
	});
	
	//初次加载时选中第一个,触发点击事件
	$(".list-group-item:first").trigger("click");
}); 