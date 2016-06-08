//http://www.themelexus.com/demo/opencart/golmart/demo4/
$(document).ready(function() {
	$('#sliderlayer').revolution({
		delay : 9000,
		startheight : 312,
		startwidth : 870,
		hideThumbs : 200,
		thumbWidth : 100,
		thumbHeight : 50,
		thumbAmount : 5,
		navigationType : "none",
		navigationArrows : "verticalcentered",
		navigationStyle : "round",
		navOffsetHorizontal : 0,
		navOffsetVertical : 20,
		touchenabled : "on",
		onHoverStop : "on",
		shuffle : "off",
		stopAtSlide : -1,
		stopAfterLoops : -1,
		hideCaptionAtLimit : 0,
		hideAllCaptionAtLilmit : 0,
		hideSliderAtLimit : 0,
		fullWidth : "off",
		shadow : 0
	});
	$("button.btn-switch").bind("click", function(e) {
		e.preventDefault();
		var theid = $(this).attr("id");
		var row = $("#products");
		if ($(this).hasClass("active")) {
			return false;
		} else {
			if (theid == "list-view") {
				$('#list-view').addClass("active");
				$('#grid-view').removeClass("active");
				row.removeClass('product-grid');
				row.addClass('product-list');

			} else if (theid == "grid-view") {
				$('#grid-view').addClass("active");
				$('#list-view').removeClass("active");
				row.removeClass('product-list');
				row.addClass('product-grid');
			}
		}
	});
});