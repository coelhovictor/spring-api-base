package br.com.coelhovictor.springapibase.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.coelhovictor.springapibase.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	User findByUsernameIgnoreCase(String username);
	
	User findByEmailIgnoreCase(String email);
	
}
