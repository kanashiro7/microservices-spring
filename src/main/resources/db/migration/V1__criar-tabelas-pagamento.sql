CREATE TABLE pagamentos (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    VALOR decimal(19,2) NOT NULL,
    NOME varchar(100) NOT NULL,
    NUMERO varchar(19) NOT NULL,
    EXPIRACAO varchar(7) NOT NULL,
    CODIGO varchar(3) NOT NULL,
    STATUS varchar(255)  NOT NULL,
    forma_de_pagamento_id bigint(20) NOT NULL,
    pedido_id bigint(20) NOT NULL,
    PRIMARY KEY (ID)
); 