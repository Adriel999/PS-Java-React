package br.com.banco.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.banco.model.ContaModel;
import br.com.banco.service.ContaService;

@RestController
@RequestMapping("/api/conta")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @GetMapping
    public List<ContaModel> obterTodasContas() {
        return contaService.obterTodasContas();
    }

    @GetMapping("/{id}")
    public ContaModel obterContaPorId(@PathVariable Long id) {
        return contaService.obterContaPorId(id);
    }

    @PostMapping
    public ContaModel criarConta(@RequestBody ContaModel conta) {
        return contaService.criarConta(conta);
    }

    @PutMapping("/{id}")
    public ContaModel atualizarConta(@PathVariable Long id, @RequestBody ContaModel conta) {
        return contaService.atualizarConta(id, conta);
    }

    @DeleteMapping("/{id}")
    public void excluirConta(@PathVariable Long id) {
        contaService.excluirConta(id);
    }
}
