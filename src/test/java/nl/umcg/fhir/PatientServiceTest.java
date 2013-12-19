package nl.umcg.fhir;

import static org.junit.Assert.assertEquals;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.hl7.fhir.instance.formats.JsonComposer;
import org.hl7.fhir.instance.formats.XmlComposer;
import org.hl7.fhir.instance.model.Patient;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class PatientServiceTest {

	private PatientService patientService;

	@Before
	public void setUp() {
		patientService = new PatientService();
	}

	@Test
	public void testGetById() {
		Patient patient = patientService.getById("7767853");
		assertEquals("7767853", patient.getIdentifier().get(0).getValueSimple());
		System.out.println(patient.getIdentifier().toString());
	}

	@Test
	public void demoJson() throws Exception {
		Patient patient = patientService.getById("7767853");
		new JsonComposer().compose(System.out, patient, true);
	}

	@Test
	public void demoXml() throws Exception {
		Patient patient = patientService.getById("7767853");
		new XmlComposer().compose(System.out, patient, true);
	}

}
