package org.sid;

import java.util.Date;

import org.sid.dao.MedecinRepository;
import org.sid.dao.PatientRepository;
import org.sid.entities.Medecin;
import org.sid.entities.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringmvcApplication implements CommandLineRunner {

	@Autowired
	private PatientRepository patientRepository;
	@Autowired
	private MedecinRepository medecinRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringmvcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		/*
		 * patientRepository.save(new Patient(null, "Hassan", new Date(), false,8));
		 * patientRepository.save(new Patient(null, "Mohammed", new Date(), false,9));
		 * patientRepository.save(new Patient(null, "Anass", new Date(), false,12));
		 * patientRepository.save(new Patient(null, "Youssef", new Date(), false,18));
		 * medecinRepository.save(new Medecin(null,"Hind",new Date(),"Moroccan",20));
		 * medecinRepository.save(new Medecin(null,"Houda",new Date(),"Couscous",17));
		 * medecinRepository.save(new Medecin(null,"Nabil",new Date(),"Rappeur",16));
		 * medecinRepository.save(new Medecin(null,"Ilyass",new
		 * Date(),"Diagnostic",19));
		 */
		patientRepository.findAll().forEach(p -> {
			System.out.println(p.toString());
		});
		
		medecinRepository.findAll().forEach(p -> {
			System.out.println(p.toString());
		});
	}

}
