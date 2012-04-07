package mega.test.JAXB;

public class MatchingSet{
	
	Object xin;
	Object sinfile;
	Object type;
	Object xs;
	Object temp;
	Object obj;
	Object xout;
	Object soutfile;
	Object sout;
	
	Object sout_writer;
	
	public String toString(){
		return
		
			
		"Description:\n"
		+"[ (string, xin), (string, xout),\n"
		+"(’Type’, type), (’XmlSerializer’, xs),\n"
		+"(object, obj), (object, oin), (object, oout),\n"
		+"(’FileStream’, sin),\n"
		+"(’FileStream’, sout) ],\n"
		+"\nValues:\n"
		+"\nxin="+xin
		+"\nxout="+xout
		+"\ntype="+type
		+"\nobj/oin/out="+obj
		+"\nfile correspond. sin="+sinfile+"@"+sinfile.hashCode()
		+"\nsout="+sout
		+"\nxs="+xs
		;
		
	}
	
	public MatchingSet clone(){
		MatchingSet n=new MatchingSet();
		n.xin=this.xin;
		n.sinfile=this.sinfile;
		n.type=this.type;
		n.xs=this.xs;
		n.temp=this.temp;
		n.obj=this.obj;
		n.xout=this.xout;
		n.soutfile=this.soutfile;
		n.sout=this.sout;
		n.sout_writer=this.sout_writer;
		return n;
			
	}
	
	
}
