package br.com.coelhovictor.springapibase.domain.enums;

public enum Role {

	ADMIN(1, "ROLE_ADMIN");

	private int id;
	private String description;
	
	private Role(int id, String description) {
		this.id = id;
		this.description = description;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public static Role toEnum(Integer id) {
		if(id == null)
			return null;
		
		for(Role x : Role.values()) {
			if(id.equals(x.getId()))
				return x;
		}
	
		throw new IllegalArgumentException("Invalid id " + id);
	}
	
	public static Role toEnum(String name) {
		if(name == null)
			return null;
		
		for(Role x : Role.values()) {
			if(name.equals(x.toString()))
				return x;
		}
		return null;
	}
	
}
