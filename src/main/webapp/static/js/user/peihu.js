$(function() {
	$('.add').click(function(event) {
		$('.add-page').css({
			display: 'block',

		});
	});
	$('.add-page .tijiao').click(function(event) {
		var $tr=$('tr').eq(1).clone();
		var value1=$('.name').val();
		var value2=$('.sex').val();
		var value3=$('.phone').val();
		var value5=$('.code').val();
		var value6=$('.position').val();
		//alert(value1);
		$tr.find('td').eq(0).html(value1);
		$tr.find('td').eq(1).html(value2);
		$tr.find('td').eq(2).html(value3);
		$tr.find('td').eq(4).html("")
		$tr.find('td').eq(3).html(value5);
		$tr.find('td').eq(5).html(value6);

		
		
		$('.add-page').find('input').val('');
		$.ajax({
			url:"/ajax",
			data:{
				method:"addNurse",
				name:value1,
				gender:value2,
				phone:value3,
				idCard:value5,
				address:value6
			},
			success:function  (data) {
				if(data==1||data=='1'){
					$('.add-page').css({
						display: 'none',
					});
					$('table').append($tr);
				}
			}
		})
	});
	/*$(document).click(function(event) {
		alert("document")
	});*/
	$('.add-page .quxiao').click(function(event) {

		$('.add-page').css({
			display: 'none',
		});
	});
	
	$("table").on('click','.remove',function  () {
		var id = $(this).parent().parent().find(".nurseNumber").html()
		var $this = $(this)
		if(confirm("确定删除该人员吗？")){
			$.get("/ajax?method=deleteNurse&id="+id).done(function  (data) {
				if(data == 1 || data == '1'){
					$this.parent().parent().remove();
				}else{
					alert("删除失败！请刷新重试")
				}
			})
		}
		
	})

});



