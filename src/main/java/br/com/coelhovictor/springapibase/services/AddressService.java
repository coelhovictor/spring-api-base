package br.com.coelhovictor.springapibase.services;

import org.springframework.stereotype.Service;

import br.com.coelhovictor.springapibase.domain.Address;
import br.com.coelhovictor.springapibase.dtos.AddressDTO;

@Service
public class AddressService {

	public Address fromDTO(AddressDTO objDTO) {
		return new Address(objDTO.getId(), objDTO.getName(), objDTO.getNumber(), 
				objDTO.getCity(), objDTO.getState(), objDTO.getZip());
	}
	
}
