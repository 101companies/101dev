package org.softlang.jena.rdf;

import org.softlang.company.CompanyModel;
import com.hp.hpl.jena.rdf.model.*;
import java.util.HashSet;
import java.util.Set;

public class MentorCheck {

	public static boolean checkMentors(CompanyModel c) {
		StmtIterator stmtit = c.getModel().listStatements(
				new SimpleSelector(
						null,
						c.MENTOR, (RDFNode) null));
		Set<String> mentorURIs = new HashSet<String>();
		while (stmtit.hasNext())
			if (!mentorURIs
					.add(stmtit.next().getObject().asResource().getURI()))
				return false;
		return true;
	}

}
