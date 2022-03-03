package br.com.coelhovictor.springapibase.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.coelhovictor.springapibase.domain.Company;
import br.com.coelhovictor.springapibase.domain.Contract;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Integer> {

	@Transactional(readOnly = true)
	Page<Contract> findByCompany(Company company, Pageable pageable);
	
}
