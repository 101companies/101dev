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
  $("#selectionview").children().each(function(){
    $(this).width((100 / ($(this).parent().children().size() - 1) - 1) + "%")
  })
});

var loadImage= function(artefactname) {
	$("#imagewrapper").append($("<img>").attr({src:"../MegaModels/implementations/" + artefactname + "/" + artefactname + "ArtefactView.megal.png", usemap : "#map"}))
}

var loadAreas = function(artefactname) {
	$("#mapwrapper").append($("<map>").attr({name: "map"}))
	$.getJSON("../MegaModels/implementations/" + artefactname + "/" + artefactname + "ArtefactView.megal.map.json", function(data) {
    $.each(data, function(i, d){
      d.title = d.href
      d.href = "javascript:void(0);"
      $("#mapwrapper map").append(
          $("<area/>").attr(d).
            bind("mouseenter", function(){hightlight(d)}).
            bind("mouseleave", function(){resethighlight()}).
            bind("click", function(){displayComponent(artefactname, d)})
      );
    })
  })
}