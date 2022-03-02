package br.com.coelhovictor.springapibase.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.coelhovictor.springapibase.domain.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {

}
