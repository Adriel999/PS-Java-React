package br.com.banco.controller;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import br.com.banco.service.TransferenciaService;
import br.com.banco.model.TransferenciaModel;

@RestController
@RequestMapping("/api/transferencia")
public class TransferenciaController {

    @Autowired
    private TransferenciaService transferenciaService;

    @GetMapping
    public List<TransferenciaModel> obterTodasTransferencias() {
        return transferenciaService.obterTodasTransferencias();
    }

    @GetMapping("/{id}")
    public TransferenciaModel obterTransferenciaPorId(@PathVariable Long id) {
        return transferenciaService.obterTransferenciaPorId(id);
    }
    
    @GetMapping("/operador/{nomeOperador}")
    public List<TransferenciaModel> obterTransferenciasPorOperador(@PathVariable String nomeOperador) {
        return transferenciaService.obterTransferenciasPorOperador(nomeOperador);
    }
    
    // Endpoint para buscar transferências dentro de um intervalo de datas
    @GetMapping("/buscarPorIntervaloData")
    public List<TransferenciaModel> buscarTransferenciasNoIntervalo(
            @RequestParam("dataInicio") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataInicio,
            @RequestParam("dataFim") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataFim) {
        return transferenciaService.buscarTransferenciasNoIntervalo(dataInicio, dataFim);
    }


    @PostMapping
    public TransferenciaModel criarTransferencia(@RequestBody TransferenciaModel transferencia) {
        return transferenciaService.criarTransferencia(transferencia);
    }

    @PutMapping("/{id}")
    public TransferenciaModel atualizarTransferencia(@PathVariable Long id, @RequestBody TransferenciaModel transferencia) {
        return transferenciaService.atualizarTransferencia(id, transferencia);
    }

    @DeleteMapping("/{id}")
    public void excluirTransferencia(@PathVariable Long id) {
        transferenciaService.excluirTransferencia(id);
    }
}

