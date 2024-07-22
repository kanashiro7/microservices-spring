package br.com.alurafood.pagamentos.controller;

import br.com.alurafood.pagamentos.dto.PagamentoDTO;
import br.com.alurafood.pagamentos.model.Pagamento;
import br.com.alurafood.pagamentos.service.PagamentoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

//Anotando para o spring que essa classe será nosso REST CONTROLLER
@RestController
//Colocarn a URL /pagamentos para que eu seja direcionado para a aba de pagamentos e analisar as requisições
@RequestMapping("/pagamentos")
public class PagamentoController {
    //Injeção de dependência da classe service
    @Autowired
    private PagamentoService service;

    //Requisição get para /pagamentos
    @GetMapping
    public Page<PagamentoDTO> listar(@PageableDefault(size=10)Pageable paginacao) {
        return service.obterTodos(paginacao);
    }

    //Requisição get para buscar todos os pagamentos por id no /id (path variable)
    @GetMapping("{id}")
    public ResponseEntity<PagamentoDTO> detalhar(@PathVariable @NotNull Long id) {
        PagamentoDTO dto = service.obterPorId(id);

        return ResponseEntity.ok(dto);
    }

    //Criar pagamento em formato
    @PostMapping
    public ResponseEntity<PagamentoDTO> cadastrar(@RequestBody @Valid PagamentoDTO dto, UriComponentsBuilder uriBuilder) {
        PagamentoDTO pagamento = service.criarPagamento(dto);
        //Irá expandir para a parte do Id
        URI endereco = uriBuilder.path("/pagamentos/{id}").buildAndExpand(pagamento.getId()).toUri();
        return ResponseEntity.created(endereco).body(pagamento);
    }
    //Para atualizar um pagamento
    @PutMapping
    public ResponseEntity<PagamentoDTO> atualizar(@PathVariable @NotNull Long id, @RequestBody @Valid PagamentoDTO dto) {
        PagamentoDTO atualizado = service.atualizarPagamento(id, dto);
        return ResponseEntity.ok(atualizado);
    }
    //Para deletar um pagamento
    @DeleteMapping("/{id}")
    public ResponseEntity<PagamentoDTO> remover(@PathVariable @NotNull Long id){
        service.excluirPagamento(id);
        return ResponseEntity.noContent().build();
    }
}
