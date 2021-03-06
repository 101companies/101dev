var App = {}
App.dataURL = "";

String.prototype.escape = function() {
	return this.replace(/([ #;&,.+*~\':"!^$[\]()=>|\/])/g,'\\$1')
}

String.prototype.startswith = function(str) {
	return (this.match("^"+str)==str)
}

String.prototype.endswith = function(str) {
	return (this.match(str+"$")==str)
}

String.prototype.ucfirst = function(){
	return this.charAt(0).toUpperCase() + this.slice(1);
}

Array.prototype.first = function() {
	return this[0];
}

Array.prototype.last = function() {
	return this[this.length-1];
}



$(document).ready(function () {

	/*var artefactname = window.location.search.replace("?","")
	if (!artefactname) 
		$("#graphicview").append($("<strong>").html("<br/>No artefact name given in URL"))
	else {
		$("#graphiccontent").
			append($("<div>").attr({id : "graphics"}).
				append($("<div>").attr({id : "svgwrapper"}).svg()).
				append($("<div>").attr({id : "imagewrapper"}))).
			append($("<div>").attr({id : "mapwrapper"}))
		loadImage(artefactname)
		loadAreas(artefactname)
		
	}*/
	var path = window.location.search.replace("?","")
	var datapath = App.dataURL + "/data/resources/" + path + "/index.json"
	if (path.endswith("/"))
		path = path.substring(0,path.length - 1)
	$("#selectionview").children().each(function(i){
		if (i > 0) $(this).width((100 / ($(this).parent().children().size() - 1) - 0.5) + "%")
	})
	$("#path").text(path)
	$("#export a").attr('href', datapath)

	if (false && path == "") {
		//$("#selectionview .viewinfo").append($("<b>").addClass("noSelInfo").text("No contribution selected"))
	} else {
		var splitp = path.split("/")
		if (splitp.length > 1)
			document.title = splitp[1]
		else
			document.title = splitp[0]
		$.ajax({url: datapath,
				dataType: 'json',
				success:  function(data) {
					FileExplorer.init(path, data)
					$('.default').dropkick({change : function(value, label){
						CExplorers[this.attr("name")].populate(path,data,value)
					}});
					CExplorers["sel1"].populate(path,data,"languages")
					CExplorers["sel2"].populate(path,data,"technologies")
					CExplorers["sel3"].populate(path,data,"terms")
				},
				error: function(s) {
					if (s.status == 404) 
						$("#selectionview .viewinfo").append($("<b>").addClass("noSelInfo").text("" + path + " not found."))
				}
			});
	}
	$("#sel1").val("languages")
	$("#sel2").val("technologies")
	$("#sel3").val("terms")
	$(".subselectionview").ajaxComplete(function() {
		$(this).show()
	})

});
