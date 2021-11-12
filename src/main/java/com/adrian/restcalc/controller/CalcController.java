package com.adrian.restcalc.controller;

import com.adrian.restcalc.config.MsgChannels;
import com.adrian.restcalc.domain.CalcResultDto;
import com.adrian.restcalc.domain.NumbersDto;
import lombok.AllArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/calc")
@AllArgsConstructor
public class CalcController {

  private final StreamBridge streamBridge;

  @PostMapping("/add")
  public double addNumbers(@RequestBody NumbersDto numbersDto) {
    double result = numbersDto.getValA() + numbersDto.getValB();
    streamBridge.send(MsgChannels.CALC_RESULT_TOPIC, new CalcResultDto("add", result));
    return result;
  }

  @PostMapping("/subtract")
  public double subtractNumbers(@RequestBody NumbersDto numbersDto) {
    double result = numbersDto.getValA() - numbersDto.getValB();
    streamBridge.send(MsgChannels.CALC_RESULT_TOPIC, new CalcResultDto("subtract", result));
    return result;
  }

  @PostMapping("/multiply")
  public double multiplyNumbers(@RequestBody NumbersDto numbersDto) {
    double result = numbersDto.getValA() * numbersDto.getValB();
    streamBridge.send(MsgChannels.CALC_RESULT_TOPIC, new CalcResultDto("multiply", result));
    return result;
  }

  @PostMapping("/divide")
  public double divideNumbers(@RequestBody NumbersDto numbersDto) {
    double result = numbersDto.getValA() / numbersDto.getValB();
    streamBridge.send(MsgChannels.CALC_RESULT_TOPIC, new CalcResultDto("divide", result));
    return result;
  }
}
