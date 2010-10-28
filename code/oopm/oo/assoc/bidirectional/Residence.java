// (C) 2009 Ralf LŠmmel

package oo.assoc.bidirectional;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.LinkedList;

/**
 * A residence that also maintains the link back to its residents
 */
public class Residence {

    /* default */ List<Person> persons = new LinkedList<Person>();

    /** 
     * Provide access to the list of persons (residents).
     * The collection is for read-access only.
     * There are extra (bidirectional) add/remove operations.
     */
    public Collection<Person> getPersons() {
    	return Collections.unmodifiableCollection(persons);
    }
    
    /**
     *  Add a person (resident) for this residence
     */
    public void addPerson(Person p){
    	persons.add(p);
   		p.residences.add(this);
    }

    /**
     *  Remove a person (resident) from this residence
     */
    public void removePerson(Person p){
    	persons.remove(p);
   		p.residences.remove(this);
    }   
}
