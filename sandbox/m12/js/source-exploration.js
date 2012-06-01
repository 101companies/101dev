var SourceExplorer = function($) {
	var currentSource = ""

	return {
		init : function() {
			$('#detailcontent').bind("click",(function(s){console.log(s)}))
			$("#files").css("overflow-y", "auto")
		},

		showSource: function(path,callback) {
			if (path != currentSource) {
				currentSource = path
				$("#detailcontent").
					html("Loading source code...").
					load("../101repo" + path + ".html", function() {if (callback) callback()})
			} else {
				if (callback)
					callback()
			}
		},

		showSourceAndHighlight : function(path, ranges) {
			$("#detailcontent pre .li1").removeClass("highlight")
			this.showSource(path, function() {
				baseid = $("#detailcontent pre").attr("id")
				$.each(ranges, function(i, range) {
					var i = range.from
					while (i <= range.to) {
						$("#" + baseid + "-" + i).addClass("highlight")
						i++
					}
					$("#detailcontent").animate({scrollTop: ($("#" + baseid + "-" + range.from).offset().top - $("#" + baseid + "-1").offset().top)}, 200)
				})
			})
		}
	}
}(jQuery)


// call init() on DOMReady
$(function(){
	SourceExplorer.init();
});