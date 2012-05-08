$(document).ready(function () {
  var artefactname = window.location.search.replace("?","")
  if (!artefactname) 
  	$("#graphic").append($("<strong>").html("<br/>No artefact name given in URL"))
  else {
  	loadGraphic(artefactname)
  	loadAreas(artefactname)
  }
});

var loadGraphic = function(artefactname) {
	$("#graphic").append($("<img>").attr({src:"docs/" + artefactname + "ArtefactView.megal.png", usemap : "#map"}))
}

var loadAreas = function(artefactname) {
	$("#graphic").append($("<map>").attr("id", "map"))
	var json = (function () {
    var json = null;
    $.ajax({
        'url': "./docs/" + artefactname + "ArtefactView.megal.map.json",
        'dataType': "json",
        'success': function (data) {
            alert(data);
        }
    });
    return json;
})(); 

}