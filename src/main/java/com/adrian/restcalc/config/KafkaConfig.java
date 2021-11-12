package com.adrian.restcalc.config;


import com.adrian.restcalc.domain.CalcResultDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;
import java.util.function.Supplier;

@Slf4j
@Configuration
public class KafkaConfig {

  @Bean
  public Consumer<KStream<Object, CalcResultDto>> logCalcResult() {
    return input ->
        input.foreach((key, value) -> log.info("Operation: {}. Result: {}", value.getOperation(), value.getResult()));
  }

  @Bean
  public Supplier<CalcResultDto> calcResultReady() {
    return () -> null;
  }

}
