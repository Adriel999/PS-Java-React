package br.com.banco.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "conta")
public class ContaModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_conta")
	private Long id;
	
	@Column(name = "nome_responsavel", nullable = false)
	private String nomeResponsavel;

	//Construtores
	public ContaModel() {
		
	}
	
	public ContaModel(Long id, String nomeResponsavel) {
		super();
		this.id = id;
		this.nomeResponsavel = nomeResponsavel;
	}
	
	//Getters e Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeResponsavel() {
		return nomeResponsavel;
	}

	public void setNomeResponsavel(String nomeResponsavel) {
		this.nomeResponsavel = nomeResponsavel;
	}	
}
