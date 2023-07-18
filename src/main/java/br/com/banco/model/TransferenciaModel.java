package br.com.banco.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "transferencia")
public class TransferenciaModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
    @JoinColumn(name = "conta_id", nullable = false)
    private ContaModel contaId;
	
	@Column(name = "nome_operador_transacao")
    private String nomeOperador;
	
    @Column(name = "tipo", nullable = false)
    private String tipo;
	
	@Column(name = "valor", nullable = false)
    private double valor;
	
	@Column(name = "data_transferencia", nullable = false)
    private String dataTransferencia;

    //Construtor
	public TransferenciaModel(Long id, ContaModel contaId, String nomeOperador, double valor, String dataTransferencia) {
		super();
		this.id = id;
		this.contaId = contaId;
		this.nomeOperador = nomeOperador;
		this.valor = valor;
		this.dataTransferencia = dataTransferencia;
	}
	
	//Getters e Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ContaModel getContaId() {
		return contaId;
	}

	public void setContaId(ContaModel contaId) {
		this.contaId = contaId;
	}

	public String getNomeOperador() {
		return nomeOperador;
	}

	public void setNomeOperador(String nomeOperador) {
		this.nomeOperador = nomeOperador;
	}
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getDataTransferencia() {
		return dataTransferencia;
	}

	public void setDataTransferencia(String dataTransferencia) {
		this.dataTransferencia = dataTransferencia;
	}
}
