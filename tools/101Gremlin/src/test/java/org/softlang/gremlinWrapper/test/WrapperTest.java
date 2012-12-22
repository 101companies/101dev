package org.softlang.gremlinWrapper.test;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;
import org.softlang.gremlinWrapper.Wrapper;

import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Vertex;

public class WrapperTest {

	@Test
	public void testIsResPage1() {
		boolean t1 = Wrapper.isPageRes("http://101companies.org/resource/Contribution-3Axslt");
		assertTrue("Actual page resource", t1);
	}

	@Test
	public void testIsResPage2() {
		boolean t2 = Wrapper.isPageRes("http://semantic-mediawiki.org/swivt/1.0#Subject");
		assertFalse("Non-page resource", t2);
	}

	@Test
	public void testGetVertex() {
		int in = 0;
		Iterator<Vertex> it = Wrapper.getVertex("Base")
				.getVertices(Direction.IN).iterator();
		while (it.hasNext()) {
			if (Wrapper.isPageRes(it.next().getId()))
				in++;
		}
		assertEquals(3, in);
	}
	
	@Test
	public void testExploreIn() {
		assertEquals(3, Wrapper.exploreIn("Base").size());
	}
	
	@Test
	public void testExploreOut() {
		assertEquals(0, Wrapper.exploreOut("Base").size());
	}

}
