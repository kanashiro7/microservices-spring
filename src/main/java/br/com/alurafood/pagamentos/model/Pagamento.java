package br.com.alurafood.pagamentos.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;

//Identificando a entidade na tabela
@Entity
//Trocando o nome da tabela para "pagamentos"
@Table(name="pagamentos")
//Diminuindo o boilerplate da aplicação com o Lombok
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pagamento {
    //Identificando o id na tabela
    @Id
    //Criando uma estratégia no banco de dados para o Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @NotNull
    @Positive
    //O valor não pode estar vazio e precisa ser positivo
    private BigDecimal valor;

    @NotBlank
    @Size(max = 100)
    //O nome não pode estar em branco e o tamanho máximo do nome seria 100
    private String nome;

    @NotBlank
    @Size(max= 19)
    private String numero;

    @NotBlank
    @Size(max= 7)
    private String expiracao;

    @NotBlank
    @Size(min= 3, max=3)
    private String codigo;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;

    @NotNull
    private Long pedidoId;

    @NotNull
    private Long formaDePagamentoId;
}
