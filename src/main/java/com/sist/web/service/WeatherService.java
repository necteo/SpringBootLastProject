package com.sist.web.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import org.springframework.beans.factory.annotation.Value;
// https://rank.ezme.net/  .rank_word / .rank_img data-pagespeed-lazy-src
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sist.web.manager.CityGrid; // test => JUnit 
// <option value="SEOUL">서울</option>
/*
 *   {"response":{"header":{"resultCode":"00","resultMsg":"NORMAL_SERVICE"},"body":{"dataType":"JSON","items":{"item":[{"baseDate":"20260122","baseTime":"1100","category":"PTY","nx":60,"ny":127,"obsrValue":"0"},{"baseDate":"20260122","baseTime":"1100","category":"REH","nx":60,"ny":127,"obsrValue":"34"},{"baseDate":"20260122","baseTime":"1100","category":"RN1","nx":60,"ny":127,"obsrValue":"0"},{"baseDate":"20260122","baseTime":"1100","category":"T1H","nx":60,"ny":127,"obsrValue":"-8.1"},{"baseDate":"20260122","baseTime":"1100","category":"UUU","nx":60,"ny":127,"obsrValue":"3.2"},{"baseDate":"20260122","baseTime":"1100","category":"VEC","nx":60,"ny":127,"obsrValue":"272"},{"baseDate":"20260122","baseTime":"1100","category":"VVV","nx":60,"ny":127,"obsrValue":"0"},{"baseDate":"20260122","baseTime":"1100","category":"WSD","nx":60,"ny":127,"obsrValue":"3.2"}]},"pageNo":1,"numOfRows":10,"totalCount":8}}}
 */
@Service
public class WeatherService {
   
   private String apiKey="2d4038e3debd41b6c50c6ef42ebe44ee1eaaeb9f3a86151b7db0a2c83e0952ae";
   public Map<String,String> getWeather(String city)
   {
	   CityGrid grid=CityGrid.from(city);
	   
	   String date=LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
	   String time=LocalTime.now().format(DateTimeFormatter.ofPattern("HH00"));
	   String url=String.format("""
			      https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst?serviceKey=%s&pageNo=1&numOfRows=10&dataType=JSON&base_date=%s&base_time=%s&nx=%s&ny=%s
	              """,apiKey,date,time,grid.getNx(),grid.getNy());
	   System.out.println(url);
	   RestTemplate rt=new RestTemplate();
	   Map res=rt.getForObject(url, Map.class);
	   return parse(res);
   }
   public Map<String,String> parse(Map res)
   {
	   Map<String,String> map=new HashMap<>();
	   List items=(List)((Map)((Map)((Map)res.get("response")).get("body")).get("items")).get("item");
	   
	   for(Object o:items) {
		   Map item=(Map)o;
		   String cate=(String)item.get("category");
		   if(cate.equals("T1H"))
			   map.put("temp",item.get("obsrValue")+"℃");
		   if(cate.equals("PTY"))
			   map.put("rain", item.get("obsrValue").toString());   
	   }
	   
	   return map;
   }
   
}