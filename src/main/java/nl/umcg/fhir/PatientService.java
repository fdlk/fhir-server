package nl.umcg.fhir;

import nl.umcg.fhir.model.datatype.HumanName;
import nl.umcg.fhir.model.datatype.Address.AddressUse;
import nl.umcg.fhir.model.datatype.Identifier.IdentifierUse;
import nl.umcg.fhir.model.datatype.codeableconcept.AdministrativeGender;
import nl.umcg.fhir.model.resource.Narrative;
import nl.umcg.fhir.model.resource.Patient;
import nl.umcg.fhir.model.resource.Reference;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PatientService {

	@RequestMapping("/patient/@{nummer}")
	@ResponseBody
	public Patient getById(@PathVariable String nummer) {
		Patient patient = new Patient();
		Reference umcg = new Reference("UMCG");
		patient.addIdentifier().setValue(nummer)
				.setSystem("http://umcg.nl/mrn").setLabel("UMCG nummer")
				.setUse(IdentifierUse.official).setAssigner(umcg);
		patient.setActive(true);
		patient.setDeceased(false);
		patient.setGender(AdministrativeGender.MALE);
		patient.setBirthDate("2013-08");
		patient.addAddress().setUse(AddressUse.home).addLine("Hanzeplein 1").setZip("9700 AB")
				.setCity("Groningen");
		HumanName name = patient.addName();
		name.addGiven("Jan").addGiven("J.").addGiven("F.");
		name.addFamily("Fictief");
		name.setText("Jan J.F. Fictief");
		patient.setText(new Narrative().setStatus("generated").setDiv(
				name.getText() + " " + nummer));
		return patient;
	}

}
