package br.com.banco.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.banco.model.TransferenciaModel;

@Repository
public interface TransferenciaRepository extends JpaRepository<TransferenciaModel, Long> {
	
	List<TransferenciaModel> findByNomeOperador(String nomeOperador);

	 List<TransferenciaModel> findByDataTransferenciaBetween(LocalDate dataInicio, LocalDate dataFim);

}
