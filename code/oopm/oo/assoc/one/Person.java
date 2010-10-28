// (C) 2009 Ralf LŠmmel

package oo.assoc.one;

/**
 * A person with a required residence
 */
public class Person {

    private Residence residence;

    /** Construction requires a residence */
    public Person(Residence residence) {
    	setResidence(residence);
    }
    
    public Residence getResidence() {
    	return residence; 
    }
    
    public void setResidence(Residence residence) { 
    	this.residence = residence; 
    }
}
