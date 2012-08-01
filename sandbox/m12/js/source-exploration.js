var SourceExplorer = function($) {
	var currentSource = ""


	var setInfoText = function(text) {
		$("#detailview .viewinfo").text(text)
	}

	var highlightSource = function(meta) {
		$("#detailcontent pre ol li").removeClass("highlight")
		if (meta && meta[0].lines) {
			baseid = $("#detailcontent pre").attr("id")
			var i = meta[0].lines.from
			while (i <= meta[0].lines.to) {
			$("#" + baseid + "-" + i).addClass("highlight")
				i++
			}
			$("#detailcontent").animate({scrollTop: ($("#" + baseid + "-" + meta[0].lines.from).offset().top - $("#" + baseid + "-1").offset().top)}, 200)
		}
		
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
					load("http://explorer.101companies.org/data/resources/" + path + ".geshi.html", function() {
						highlightSource(meta)
					})
			} else {
				highlightSource(meta)
			}
		},
	}
}(jQuery)


// call init() on DOMReady
$(function(){
	SourceExplorer.init();
});