package br.com.coelhovictor.springapibase.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.coelhovictor.springapibase.domain.Contract;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Integer> {

}
