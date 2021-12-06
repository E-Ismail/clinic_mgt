package org.sid.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Medecin {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull // Annotation de validation
	@Size(min = 4, max = 15, message="Name is invalide!") //Ecrit egalement un message d'erreur par defaut, respectant l'internationalisation 
	private String name;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd") // Format should be compatible to DB.
	private Date dateNaissance;
	@NotNull // Annotation de validation
	@Size(min = 4, max = 100, message="Specialite is invalide!")
	private String specialite;
	@DecimalMin("4") @DecimalMax("20")
	private int score;
}
