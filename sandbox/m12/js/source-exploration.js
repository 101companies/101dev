var viewSource = function(path) {
	$("#detailcontent").
		html("Loading source code...").
      	load("../101repo" + path + ".html")
	$("#files").css("overflow-y", "auto")
}