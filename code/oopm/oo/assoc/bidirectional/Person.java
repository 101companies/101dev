// (C) 2009 Ralf LŠmmel

package oo.assoc.bidirectional;

import java.util.List;
import java.util.LinkedList;
import java.util.Collection;
import java.util.Collections;

/**
 * A person that can have any number of residences with
 * navigation from residences back to persons (residents)
 */
public class Person {

    /* default */ List<Residence> residences = new LinkedList<Residence>();
    
    /** 
     * Provide read access to the list of residences.
     * The collection is for read-access only.
     * There are extra (bidirectional) add/remove operations.
     */
    public Collection<Residence> getResidences() {
    	return Collections.unmodifiableCollection(residences);
    }
    
    /**
     *  Add a residence for this person (resident)
     */
    public void addResidence(Residence r){
    	residences.add(r);
   		r.persons.add(this);
    }

    /**
     *  Remove a residence for this person (resident)
     */
    public void removeResidence(Residence r){
    	residences.remove(r);
   		r.persons.remove(this);
    }   
}
