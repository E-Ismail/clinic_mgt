package org.sid.dao;

import org.sid.entities.Medecin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedecinRepository extends JpaRepository<Medecin, Long> {

	public Page<Medecin> findByNameContains(String keyword,Pageable pageable);
}
