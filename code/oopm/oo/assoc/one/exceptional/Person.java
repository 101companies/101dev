// (C) 2009 Ralf LŠmmel

package oo.assoc.one.exceptional;

/**
 * A person with a required residence
 */
public class Person {

    private Residence residence;

    /** Construction requires a residence */
    public Person(Residence residence) {
    	if (residence==null)
    		throw new IllegalArgumentException();
    	setResidence(residence);
    }
    
    public Residence getResidence() {
    	return residence; 
    }
    
    public void setResidence(Residence residence) { 
    	if (residence==null)
    		throw new IllegalArgumentException();
    	this.residence = residence; 
    }
}
