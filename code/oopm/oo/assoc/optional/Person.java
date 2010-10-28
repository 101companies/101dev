// (C) 2009 Ralf LŠmmel

package oo.assoc.optional;

/**
 * A person with an optional residence
 */
public class Person {

    private Residence residence;

    public Residence getResidence() {
    	return residence; 
    }
    
    public void setResidence(Residence residence) { 
    	this.residence = residence; 
    }
}
