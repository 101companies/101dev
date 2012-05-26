var SourceExplorer = function($) {

	return {
		init : function() {
			$('#detailcontent').bind("click",(function(s){console.log(s)}))
		},

		showSource : function(path) {
			$("#detailcontent").
				html("Loading source code...").
		      	load("../101repo" + path + ".html")
			$("#files").css("overflow-y", "auto")
		}
	}
}(jQuery)


// call init() on DOMReady
$(function(){
 SourceExplorer.init();
});