(function($) {
	$.fn.dragable = function  (options) {
		var setting = {
			obj:$(this)
		}
		var $this = $(this)
		if(options){
			if(options.obj){
				options.obj = $(options.obj)
			}
			$.extend(setting,options)
		}
		var obj = setting.obj
		var objname = obj[0].className
		
		var startx,starty,isDown = false

		$(document).on('mousedown','.'+objname,function  (event) {

			startx = event.pageX
			starty = event.pageY
			isDown = true;
		})
		$(document).on("mouseup",obj,function  (event) {
			isDown = false;
		})

		$(document).on("mousemove",obj,function  (event) {
			if(isDown==false){
				return false;
			}


			var movedX = event.pageX - startx ;
			var movedY = event.pageY - starty ;
			var marginleft = parseInt($this.css("margin-left"))
			var margintop = parseInt($this.css("margin-top"))
			

			var finallyleft = marginleft+movedX;
			var finallytop = margintop+movedY

			
			$this.css({
				"margin-left":finallyleft,
				"margin-top":finallytop
			})
			
			startx +=movedX
			starty +=movedY
		})

	}
})(jQuery);













