package br.com.coelhovictor.springapibase.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.coelhovictor.springapibase.domain.Company;
import br.com.coelhovictor.springapibase.domain.Owner;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

	@Transactional(readOnly = true)
	Page<Company> findByOwner(Owner owner, Pageable pageable);
	
}
