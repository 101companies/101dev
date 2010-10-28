// (C) 2009 Ralf Laemmel

package oo.gui.twin;

import java.util.Collection;
import java.util.LinkedList;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/** Counters with property-change notification */
public final class Counter {

	/** Private state of the counter */
	private int count = 0;

	/** Listeners */
	private Collection<PropertyChangeListener> listeners =
		new LinkedList<PropertyChangeListener>();
	
	/** Return the value of the counter */
	public int getCount() {
		return count; 
	}
	
	/** Increment the counter */
	public void step() {
		setCount(count+1);
	}
		
	/** Reset the counter to zero */
	public void reset() { 
		setCount(0);
	}
	
	/** Set the value of the counter */
	private void setCount(int newValue) {

		// Backup value
		int oldValue = count;
		
		// Do the update
		count = newValue;
		
		// Construct the event
		PropertyChangeEvent event =
			new PropertyChangeEvent(
				this,
				"count",
				oldValue,
				newValue);		
		
		// Notify all subscribed listeners
		for (PropertyChangeListener l : listeners)
			l.propertyChange(event);
	}	
	
	/** Add a listener */
	public void addListener(PropertyChangeListener listener) {
		listeners.add(listener);
	}	
}