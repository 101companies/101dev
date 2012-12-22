package org.softlang.gremlinWrapper

import net.fortytwo.sesametools.reposail.RepositorySail;

import org.openrdf.repository.Repository;
import org.openrdf.repository.http.HTTPRepository;

import com.tinkerpop.gremlin.groovy.Gremlin;
import com.tinkerpop.gremlin.groovy.GremlinGroovyPipeline;
import com.tinkerpop.blueprints.*;
import com.tinkerpop.blueprints.impls.sail.*;
import com.tinkerpop.blueprints.impls.sail.impls.*;
import com.tinkerpop.pipes.util.*;

import edu.uci.ics.jung.algorithms.scoring.EdgeScorer;

class Wrapper {

	private static final String repoURI = 'http://sl-mac.uni-koblenz.de:8081/openrdf-sesame/repositories/wiki101/';
	private static final String resourceBase = 'http://101companies.org/resource/';

	static Repository repo;
	static SailGraph graph;

	static  {
		Gremlin.load();
		repo = new HTTPRepository(repoURI);
		repo.initialize();
		graph = new LinkedDataSailGraph(new SailGraph(new RepositorySail(repo)));
	}

	public static boolean isPageRes(v) {
		return v.toString().startsWith(resourceBase);
	}

	public static SailVertex getVertex(pageName) {
		return graph.v(resourceBase + pageName)
	}

	public static List<SailEdge> exploreIn(pageName) {
		return getVertex(pageName).inE.filter { isPageRes(it.getVertex(Direction.IN).getId())}.toList()
	}

	public static List<SailEdge> exploreOut(pageName) {
		return getVertex(pageName).outE.filter { isPageRes(it.getVertex(Direction.IN).getId())}.toList()
	}

}
