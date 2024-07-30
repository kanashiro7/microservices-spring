package br.com.alurafood.pagamentos.service;

import br.com.alurafood.pagamentos.dto.PagamentoDTO;
import br.com.alurafood.pagamentos.model.Pagamento;
import br.com.alurafood.pagamentos.model.Status;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import br.com.alurafood.pagamentos.repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

//Anotar a classe como Service para o Spring identificar o service da nossa aplicação
@Service
public class PagamentoService {
    //Injetando a dependência da interface repository
    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private ModelMapper modelMapper;

    //Metodo para obter todos os pagamentos
    public Page<PagamentoDTO> obterTodos(Pageable paginacao) {
        return pagamentoRepository
                .findAll(paginacao)
                .map(p -> modelMapper.map(p, PagamentoDTO.class));
    }

    //Criando um método para obter todos por Id (visualizar os pagamentos por Id
    public PagamentoDTO obterPorId (Long Id) {
        Pagamento pagamento = pagamentoRepository.findById(Id)
                .orElseThrow(() -> new EntityNotFoundException());

        return modelMapper.map(pagamento, PagamentoDTO.class);
    }

    //Criando um método para criar um pagamento
    //Setar o status como CRIADO - não precisando passar o status no corpo da requisição
    public PagamentoDTO criarPagamento (PagamentoDTO dto) {
        Pagamento pagamento = modelMapper.map(dto, Pagamento.class);
        pagamento.setStatus(Status.CRIADO);
        pagamentoRepository.save(pagamento);

        return modelMapper.map(pagamento, PagamentoDTO.class);
    }

    public PagamentoDTO atualizarPagamento (Long id, PagamentoDTO dto){
        Pagamento pagamento = modelMapper.map(dto, Pagamento.class);
        pagamento.setId(id);
        pagamento = pagamentoRepository.save(pagamento);
        return modelMapper.map(pagamento, PagamentoDTO.class);
    }

    public void excluirPagamento(Long id){
        pagamentoRepository.deleteById(id);
    }
}
