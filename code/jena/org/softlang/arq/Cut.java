package org.softlang.arq;

import java.io.File;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.update.GraphStore;
import com.hp.hpl.jena.update.GraphStoreFactory;
import com.hp.hpl.jena.update.UpdateAction;

public class Cut {
	/**
	 * TODO!
	 * 
	 * "cut.ru" atm just copies salary values!
	 */
	private static String cutScript = "scripts" + File.separatorChar + "cut.ru";

	public static void cut(Model model) {
		GraphStore graphStore = GraphStoreFactory.create(model);
		UpdateAction.readExecute(cutScript, graphStore);
	}

}