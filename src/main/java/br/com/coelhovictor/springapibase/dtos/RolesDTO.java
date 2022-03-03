package br.com.coelhovictor.springapibase.dtos;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import br.com.coelhovictor.springapibase.domain.enums.Role;

public class RolesDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull(message = "Required field")
	private Set<String> roles;
	
	public RolesDTO() {
	}

	public Set<Role> getRoles() {
		return roles.stream().map(x -> Role.valueOf(x))
				.collect(Collectors.toSet());
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}
	
}
