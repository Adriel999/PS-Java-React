package br.com.banco.service;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.banco.model.TransferenciaModel;
import br.com.banco.repository.TransferenciaRepository;
@Service
public class TransferenciaService {

    @Autowired
    private TransferenciaRepository transferenciaRepository;

    public List<TransferenciaModel> obterTodasTransferencias() {
        return transferenciaRepository.findAll();
    }

    public TransferenciaModel obterTransferenciaPorId(Long id) {
        return transferenciaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transferencia não encontrada"));
    }

    public TransferenciaModel criarTransferencia(TransferenciaModel transferencia) {
        return transferenciaRepository.save(transferencia);
    }

    public TransferenciaModel atualizarTransferencia(Long id, TransferenciaModel transferenciaAtualizada) {
        TransferenciaModel transferencia = obterTransferenciaPorId(id);
        transferencia.setDataTransferencia(transferenciaAtualizada.getDataTransferencia());
        transferencia.setValor(transferenciaAtualizada.getValor());
        transferencia.setTipo(transferenciaAtualizada.getTipo());
        transferencia.setNomeOperador(transferenciaAtualizada.getNomeOperador());
        transferencia.setContaId(transferenciaAtualizada.getContaId());
        return transferenciaRepository.save(transferencia);
    }

    public void excluirTransferencia(Long id) {
        TransferenciaModel transferencia = obterTransferenciaPorId(id);
        transferenciaRepository.delete(transferencia);
    }

    public List<TransferenciaModel> obterTransferenciasPorOperador(String nomeOperador) {
        return transferenciaRepository.findByNomeOperador(nomeOperador);
    }

    public List<TransferenciaModel> buscarTransferenciasNoIntervalo(LocalDate dataInicio, LocalDate dataFim) {
        return transferenciaRepository.findByDataTransferenciaBetween(dataInicio, dataFim);
    }
}

