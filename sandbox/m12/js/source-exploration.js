var SourceExplorer = function($) {
	var currentSource = ""


	var setInfoText = function(text) {
		$("#detailview .viewinfo").text(text)
	}

	return {
		init : function() {
			$('#detailcontent').bind("click",(function(s){console.log(s)}))
			$("#files").css("overflow-y", "auto")
		},

		showPage: function(url) {
			setInfoText("Wiki view")
			$("#detailcontent").html($("<iframe>").attr({src : url}))
		},

		showSource: function(path,meta) {
			

			setInfoText("Source view")
			if (path != currentSource) {
				currentSource = path
				$("#detailcontent").
					html("Loading source code...").
					load("../" + path + ".geshi.html", function() {
						if (meta && meta[0].lines) {
							baseid = $("#detailcontent pre").attr("id")
							var i = meta[0].lines.from
							while (i <= meta[0].lines.to) {
								$("#" + baseid + "-" + i).addClass("highlight")
								i++
							}
						}
					})
			} 
		},

		showSourceAndHighlight : function(path, ranges) {
			$("#detailcontent pre .li1").removeClass("highlight")
			this.showSource(path, function() {
				baseid = $("#detailcontent pre").attr("id")
				alert(baseid)
				$.each(ranges, function(i, range) {
					var i = range.from
					while (i <= range.to) {
						$("#" + baseid + "-" + i).addClass("highlight")
						i++
					}
					//$("#detailcontent").animate({scrollTop: ($("#" + baseid + "-" + range.from).offset().top - $("#" + baseid + "-1").offset().top)}, 200)
				})
			})
		}
	}
}(jQuery)


// call init() on DOMReady
$(function(){
	SourceExplorer.init();
});