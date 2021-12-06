package org.sid.web;

import java.util.List;

import javax.validation.Valid;

import org.sid.dao.PatientRepository;
import org.sid.entities.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PatientController {
	@Autowired
	private PatientRepository patientrepository;

	@GetMapping(path = "/index")
	public String index() {
		// forwarding
		return "index";
	}

	@GetMapping(path = "/patients")
	public String list(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size,
			@RequestParam(name = "keyword", defaultValue = "") String keyword) {
		// List<Patient> patients = patientrepository.findAll(PageRequest.of(page,
		// size));// Add pagination
		Page<Patient> pagePatients = patientrepository.findByNameContains(keyword, PageRequest.of(page, size));
		model.addAttribute("patients", pagePatients.getContent());
		model.addAttribute("pages", new int[pagePatients.getTotalPages()]);
		model.addAttribute("currentPage", page);
		model.addAttribute("keyword", keyword);
		model.addAttribute("size", size);
		// forwarding
		return "patients";
	}

	@GetMapping(path = "/deletePatient")
	public String deletePatient(@RequestParam(name = "id") Long id, String keyword, int page, int size) {
		patientrepository.deleteById(id);
		// redirection to /patients
		return "redirect:/patients?page=" + page + "&size=" + size + "&keyword=" + keyword;
	}

	@GetMapping(path = "/formPatient")
	public String formPatient(Model model) {
		model.addAttribute("patient", new Patient());
		model.addAttribute("mode", "new");
		return "formPatient";
	}

	@PostMapping("/savePatient")
	// On peut ajouter @Transactional mais la methode save est transactionnelle.
	// pour plusieurs operations aussi.
	// Dire a Spring d'utiliser Valid pour valider les champs de la form.
	public String savePatient(@Valid Patient patient, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return "/formPatient";
		patientrepository.save(patient);
		return "confirmation";
	}

	@GetMapping("/editPatient")
	// Dire a Spring d'utiliser Valid pour valider les champs de la form.
	public String editPatient(Model model, @RequestParam(name = "id") Long id) {
		Patient patient = patientrepository.findById(id).get();
		System.out.println("AMAIZING!");
		System.out.println(patient.toString());
		model.addAttribute("patient", patient);
		model.addAttribute("mode", "edit");
		return "formPatient";
	}

	/*
	 * Modifier pour RestController //Pour retourner au format json.
	 * 
	 * @GetMapping("/listPatients") //@ResponseBody par defaut le format est JSON
	 * //Ou @Produces
	 * 
	 * @ResponseBody public List<Patient> getListPatients(){ return
	 * patientrepository.findAll(); }
	 * 
	 * @GetMapping("/listPatients/{id}") //PathParam
	 * 
	 * @ResponseBody public Patient getOnePatient(@PathVariable Long id){ return
	 * patientrepository.findById(id).get(); }
	 */
}
