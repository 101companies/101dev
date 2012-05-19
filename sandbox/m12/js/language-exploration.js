var colorSchema = ['#00A8F0', '#C0D800', '#CB4B4B', '#4DA74D', '#9440ED']

var initLanguageExplorer = function(contrib, summary) {
	var tree = new dhtmlXTreeObject("languages","30%","30%",0);
	tree.setImagePath("./imgs/")
  	tree.enableCheckBoxes(false)
  	tree.enableTreeImages(false)
  	tree.enableCheckBoxes(false)
  	tree.loadXML("filebase.xml")
    tree.enableHighlighting(true)
    tree.attachEvent("onClick", function(id) {
      tree.selectItem(id, false)
      if (id.indexOf("l:") != 0) {
        viewSource(id)
      }
      return true
    })
    stats = []
    i = 0
  	$.each(summary.languages, function(language, data) {
  		if (language) {
        stats.push({data : [[0,data.ncloc]], label : language})
  			tree.insertNewChild(0, "l:" + language, "<b style=\"background-color: " + colorSchema[i] +"\">" + language + "</b>")
  			$.each(data.files, function(id, fn) {
  				tree.insertNewChild("l:" + language, fn.filename, id)
  			})
        i++
  		}
  	})

  graph = Flotr.draw(document.getElementById("langstats"), stats, {
    HtmlText : false,
    colors: colorSchema,
    fontSize: 6.5,
    grid : {
      verticalLines : false,
      horizontalLines : false,
      outlineWidth: 0,
      lineColor: "red"
    },
    xaxis : { showLabels : true, margin:false },
    yaxis : { showLabels : true, margin:false },
    pie : {
      show : true, 
      explode : 5
    },
    mouse : { 
      track : true,
      lineColor: "#274779",
      trackFormatter: 
        function(obj){
          return obj.y +' NCLOC';
        }, 
    },
    legend : {
      show: false,
      position : "no"
    }
  });
}