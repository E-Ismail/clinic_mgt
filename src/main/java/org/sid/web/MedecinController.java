package org.sid.web;

import org.sid.dao.MedecinRepository;
import org.sid.entities.Medecin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MedecinController {
	
	/*
	 * @GetMapping("/medecins") public String medecins() { return "/medecins"; }
	 */
	@Autowired
	private MedecinRepository medecinRepository;
	@GetMapping(path = "/medecins")
	public String listOfDoctors(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size,
			@RequestParam(name = "keyword", defaultValue = "") String keyword) {
		// List<Patient> patients = patientrepository.findAll(PageRequest.of(page,
		// size));// Add pagination
		Page<Medecin> pageMedecins= medecinRepository.findByNameContains(keyword, PageRequest.of(page, size));
		model.addAttribute("medecins", pageMedecins.getContent());
		model.addAttribute("pages", new int[pageMedecins.getTotalPages()]);
		model.addAttribute("currentPage", page);
		model.addAttribute("keyword", keyword);
		model.addAttribute("size", size);
		// forwarding
		return "medecins";
	}
	
	@GetMapping(path = "/deleteDoctor")
	public String deleteDoctor(@RequestParam(name = "id") Long id, String keyword, int page, int size) {
		medecinRepository.deleteById(id);
		// redirection to /patients
		return "redirect:/medecins?page=" + page + "&size=" + size + "&keyword=" + keyword;
	}

}
