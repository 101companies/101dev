This files describes how to install Megal/yEd, that is the
configuration of yEd for MegaL/GML visual language.

== Yed Installation ==
Download Yed from http://www.yworks.com/en/products_yed_download.html

== MegaL/yEd configuration ==
The objective is to replace the some part of the configuration 
that comes with yed, and in particular to add the MegaL/yEd palettes.
 
yEd saves its configuration in the user home directory. The exact
location depends on the operating system. On windows for instance 
it is in C:\Documents and Settings\<theUser>\Application Data\yWorks\yEd.

Create an archive of the configuration directory.
Remove the yEd/palettes directory.
Replace it with the provided file.

== MegaL/yEd usage ==
Please not that due to a bug in yEd, before creating a node or an
edge, always select first the default node or edge in the palette
with a double click. The default is shown in blue.
Otherwise the "description" field is not copied appropriately.

