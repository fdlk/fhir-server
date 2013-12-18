package nl.umcg.fhir;

import static org.junit.Assert.assertEquals;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import nl.umcg.fhir.model.resource.Patient;
import nl.umcg.fhir.model.resource.patient.DeceasedBoolean;
import nl.umcg.fhir.model.resource.patient.DeceasedDateTime;
import nl.umcg.fhir.model.resource.patient.MultipleBirthBoolean;
import nl.umcg.fhir.model.resource.patient.MultipleBirthOrder;

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
		assertEquals("7767853", patient.getIdentifier().get(0).getValue());
		System.out.println(patient.getIdentifier().toString());
	}

	@Test
	public void demoJson() throws Exception {
		Patient patient = patientService.getById("7767853");
		// System.out.println(new Gson().toJson(patient));
		// new JsonComposer().compose(System.out, patient, true);
		ObjectMapper mapper = new ObjectMapper() {

		};
		mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		mapper.setSerializationInclusion(Include.NON_EMPTY);
		mapper.getJsonFactory().createJsonGenerator(System.out)
				.writeObject(patient);
	}

	@Test
	public void demoXml() throws Exception {
		Patient patient = patientService.getById("7767853");
		Marshaller marshaller = JAXBContext.newInstance(Patient.class,
				DeceasedBoolean.class, DeceasedDateTime.class,
				MultipleBirthBoolean.class, MultipleBirthOrder.class)
				.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		marshaller.marshal(patient, System.out);
	}

}
