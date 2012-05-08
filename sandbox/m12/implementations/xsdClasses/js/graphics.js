var hightlight = function(d){
  var svg = $('#svgwrapper').svg('get');
  if (d.shape == "rect") {
    var coords = d.coords.split(",")
    //alert(d.id)
    svg.rect(null, 
      parseInt(coords[0]) - 10, 
      parseInt(coords[1]) - 10, 
      parseInt(coords[2]) - parseInt(coords[0]), 
      parseInt(coords[3]) - parseInt(coords[1]), 
      5, 5, {color: "red", fill: 'rgba(10,10,190,0.9)', stroke: 'rgb(110,110,140)', strokeWidth: 4});
  }
}

var resethighlight = function() {
  $('#svgwrapper').svg('get').clear();
}