package br.com.coelhovictor.springapibase.dtos;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotNull;

import br.com.coelhovictor.springapibase.domain.enums.Role;
import br.com.coelhovictor.springapibase.services.validation.RoleValid;

@RoleValid
public class RolesDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull(message = "Required field")
	private List<String> roles;
	
	public RolesDTO() {
	}

	public Set<Role> getRoles() {
		Set<Role> roles = new HashSet<>();
		for(String item : this.roles) {
			Role role = Role.toEnum(item);
			if(role != null && roles.contains(role))
				roles.add(role);
		}
		return roles;
	}
	
	public List<String> getRolesNames() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	
}
