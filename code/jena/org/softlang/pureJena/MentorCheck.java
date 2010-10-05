package org.softlang.pureJena;

import java.util.HashSet;
import java.util.Set;

import org.softlang.util.CompanyConstants;
import com.hp.hpl.jena.rdf.model.*;

public class MentorCheck {

	public static boolean checkMentors(Model model) {
		StmtIterator stmtit = model.listStatements(new SimpleSelector(null, CompanyConstants.MENTOR, (RDFNode) null));
		Set<String> mentorURIs = new HashSet<String>();
		while (stmtit.hasNext())
			if (!mentorURIs
					.add(stmtit.next().getObject().asResource().getURI()))
				return false;
		return true;
	}

}
