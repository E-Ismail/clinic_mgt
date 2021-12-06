package org.sid.web;

import java.util.List;
import org.sid.dao.PatientRepository;
import org.sid.entities.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PatientRestController {
	@Autowired
	private PatientRepository patientrepository;

	// Pour retourner au format json.
	@GetMapping("/listPatients")

	public List<Patient> getListPatients() {
		return patientrepository.findAll();
	}

	@GetMapping("/listPatients/{id}") // PathParam
	public Patient getOnePatient(@PathVariable Long id) {
		return patientrepository.findById(id).get();
	}

}
