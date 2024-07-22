package br.com.alurafood.pagamentos.config;

import org.modelmapper.ModelMapper;
import org.springframework.cglib.core.Local;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@Configuration
public class config {
    @Bean
    public ModelMapper obterModelMapper(){
        return new ModelMapper();
    }
}

