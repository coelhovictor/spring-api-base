package br.com.coelhovictor.springapibase.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.coelhovictor.springapibase.domain.Contract;
import br.com.coelhovictor.springapibase.dtos.ContractDTO;
import br.com.coelhovictor.springapibase.repositories.ContractRepository;
import br.com.coelhovictor.springapibase.services.exceptions.NotFoundException;

@Service
public class ContractService {

	@Autowired
	private ContractRepository repository;
	
	@Autowired
	private CompanyService companyService;

	public Contract findById(Integer id) {
		return repository.findById(id).orElseThrow(() -> 
			new NotFoundException("Contract", id));
	}
	
	public Page<Contract> findAll(Integer page, Integer linesPerPage,
			String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, 
				Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);
	}
	
	public void insert(Contract obj) {
		obj.setId(null);
		repository.save(obj);
	}

	public Contract fromDTO(ContractDTO objDTO) {
		return new Contract(objDTO.getId(), objDTO.getStartDate(), 
				objDTO.getEndDate(), objDTO.getValue(), 
				companyService.findById(objDTO.getCompanyId()));
	}
	
}
