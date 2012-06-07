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
	contribname = window.location.search.replace("?","")
	$("#selectionview").children().each(function(i){
		if (i > 0) $(this).width((100 / ($(this).parent().children().size() - 1) - 0.5) + "%")
	})
	if (contribname == "") {
		$("#selectionview .viewinfo").append($("<b>").addClass("noSelInfo").text("No contribution selected"))
	} else {
		$.ajax({url: "../101repo/contributions/" + contribname + "/index.summary.json",
				dataType: 'json',
				success:  function(data) {
					FileExplorer.init(contribname, data)
					LanguageExplorer.init(contribname, data.allFiles.languageDistribution)
					TechnologyExplorer.init(contribname, data.allFiles.technologyDistribution)
					TagExplorer.init(contribname)
				},
				error: function(s) {
					if (s.status == 404) 
						$("#selectionview .viewinfo").append($("<b>").addClass("noSelInfo").text("Contribution " + contrib + " not found."))
				}
			});
	}
	/*tree.setImagePath("./imgs/");
	tree.enableCheckBoxes(false);
	tree.enableTreeImages(false);
	tree.loadXML("foo.xml");
	tree._hAdI = true;
	tree.attachEvent("onOpenStart", function(id,state){if (state < 0) {tree.insertNewChild(id,id+100,"New Node 2",0,0,0,0,"")};return true});
	//$("body").click(function(){(tree.insertNewChild(1,4,"New Node 2",0,0,0,0,""))});*/
});

