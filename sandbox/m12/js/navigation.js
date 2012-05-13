var displayComponent = function(r,d) {
	var isExternal = d.title.indexOf("http://") == 0;
	if (isExternal) {
		$("#detailcontent").html($("<iframe>").attr({src:d.title}))
	} else {
		$("#detailcontent").load("implementations/" + r + "/" + d.title);
	}
}