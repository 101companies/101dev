var hightlight = function(d){
  var svg = $('#svgwrapper').svg('get');
  var coords = $.map(d.coords.split(","), function(a) {return parseInt(a)});
  switch (d.shape) {
    case "rect":
      svg.rect(null, 
        coords[0] - 10, 
        coords[1] - 10, 
        coords[2] - coords[0], 
        coords[3] - coords[1], 
        5, 5, {fill: 'rgba(110,210,250,0.2)', stroke: 'rgb(110,110,140)', strokeWidth: 3});
      break;
    case "poly":
      var xylist = [];
       $.each(coords, function(i,v) {if (i % 2 == 0) xylist[i / 2] = [coords[i] - 10, coords[i + 1] - 10]})
      //alert(JSON.stringify(xylist));
      svg.polygon(null, xylist, {fill: 'rgba(110,210,250,0.7)', stroke: 'rgb(110,110,140)', strokeWidth: 1})
      break;

  }
}

var resethighlight = function() {
  $('#svgwrapper').svg('get').clear();
}