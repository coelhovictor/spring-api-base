package br.com.coelhovictor.springapibase.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.coelhovictor.springapibase.domain.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

}
