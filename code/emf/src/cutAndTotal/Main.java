package cutAndTotal;

import java.io.File;
import java.io.IOException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceFactoryImpl;

import company.Company;
import company.CompanyPackage;

public class Main {

	public static String XMIYEAR2008 = "samples" + File.separatorChar
			+ "sampleCompany2008.xmi";

	public static String XMLYEAR2009 = "samples" + File.separatorChar
			+ "sampleCompany2009.xml";

	public static String XSD = "model" + File.separatorChar + "Company.xsd";

	public static void main(String[] args) throws IOException {

		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getPackageRegistry().put(CompanyPackage.eNS_URI,
				CompanyPackage.eINSTANCE);

		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
				.put("xmi", new XMIResourceFactoryImpl());

		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
				.put("xml", new XMLResourceFactoryImpl());

		// deserialize
		Resource input = resourceSet.createResource(URI
				.createFileURI(XMIYEAR2008));
		input.load(null);
		Company company = (Company) input.getContents().get(0);

		// total and cut
		System.out.println("Total salary = " + Total.totalCompany(company));
		System.out.println("Cutting...");
		Cut.cutCompany(company);
		System.out.println("New total salary = " + Total.totalCompany(company));
		

		// serialize
		Resource resource = resourceSet.createResource(URI
				.createFileURI(XMLYEAR2009));
		resource.getContents().add(company);
		resource.save(null);

	}
}
