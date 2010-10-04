declare default element namespace "http://www.softlang.org/company.xsd";
declare namespace c101 = "http://planet-sl.org/101companies"; 

declare function c101:deptdepth ($root as node()?) as xs:integer?
{
	if ($root/dept)
	then max($root/*/c101:deptdepth(.)) + 1
	else
		if ($root/subunit/du)
		then max($root/subunit/du/c101:deptdepth(.)) + 1
		else 0
};

<result>
	{c101:deptdepth(/company)}
</result>
