package br.com.coelhovictor.springapibase.dtos;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.coelhovictor.springapibase.domain.Contract;
import br.com.coelhovictor.springapibase.services.validation.ContractValid;

@ContractValid
public class ContractDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "Required field")
	private Date startDate;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date endDate;
	
	@Range(min = 100, max = 100000000, message = "The range must be "
			+ "between 100 and 100000000")
	@NotNull(message = "Required field")
	private Double value;
	
	@NotNull(message = "Required field")
	private Integer companyId;
	
	public ContractDTO() {
	}

	public ContractDTO(Contract obj) {
		this.id = obj.getId();
		this.startDate = obj.getStartDate();
		this.endDate = obj.getEndDate();
		this.value = obj.getValue();
		this.companyId = obj.getCompany().getId();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

}
