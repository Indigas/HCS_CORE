package sk.durovic.jms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

@Component
public class JmsConfig {

    @Bean
    public MessageConverter jacksonConverter(){
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();

        return converter;
    }
}
