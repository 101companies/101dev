// (C) 2009 Ralf LŠmmel

package oo.assoc.many;

import java.util.List;
import java.util.LinkedList;

/**
 * A person that can have any number of residences
 */
public class Person {

    private List<Residence> residences = new LinkedList<Residence>();
    
    /** 
     * Provide access to the list of residences
     */
    public List<Residence> getResidences() {
    	return residences;
    }
}
