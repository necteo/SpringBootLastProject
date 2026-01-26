package com.sist.web.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.sist.web.service.WeatherService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class WeatherController {
  private final WeatherService wService;
  private final SimpMessagingTemplate template;
  
  @GetMapping("/weather2")
  public String weather_page() {
	  return "weather";
  }
  
  @MessageMapping("/weather")
  public void changeCity(@Payload String city)
  { 
	  System.out.println(wService.getWeather(city));
	  template.convertAndSend(
		 "/sub/weather",
		 wService.getWeather(city)
	  );
  }
}