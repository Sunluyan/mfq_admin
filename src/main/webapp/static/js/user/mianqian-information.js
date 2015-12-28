$(function() {
	
	var flag=true;
	$('.agree').click(function(event) {
		if(flag==true){
			$('.shenpi-information').css({
				display: 'block',
				
			});
			flag = false;

		}else{
			$('.shenpi-information').css({
				display: 'none',
				
			});
			flag = true;
		}
	});

	$('.refuse').click(function(event) {
		if(flag==true){
			$('.refuse-information').css({
				display: 'block',
				
			});
			flag = false;

		}else{
			$('.refuse-information').css({
				display: 'none',
				
			});
			flag = true;
		}
	});

	$('.tijiao').click(function(event) {
		$('.shenpi-information').css({
				display: 'none',
				
			});
	});
	$('.tijiao-2').click(function(event) {
		$('.refuse-information').css({
				display: 'none',
				
			});
	});
	$('.quxiao').click(function(event) {
		$('.refuse-information').css({
				display: 'none',
				
			});
		$('.shenpi-information').css({
				display: 'none',
				
			});
	});
});