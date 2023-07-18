package br.com.banco.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.banco.repository.ContaRepository;
import br.com.banco.model.ContaModel;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

    public List<ContaModel> obterTodasContas() {
        return contaRepository.findAll();
    }

    public ContaModel obterContaPorId(Long id) {
        return contaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));
    }

    public ContaModel criarConta(ContaModel conta) {
        return contaRepository.save(conta);
    }

    public ContaModel atualizarConta(Long id, ContaModel contaAtualizada) {
        ContaModel conta = obterContaPorId(id);
        conta.setNomeResponsavel(contaAtualizada.getNomeResponsavel());
        return contaRepository.save(conta);
    }

    public void excluirConta(Long id) {
        ContaModel conta = obterContaPorId(id);
        contaRepository.delete(conta);
    }
}

