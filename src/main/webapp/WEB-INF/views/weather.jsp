<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
 href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container{
   margin-top: 50px
}
.row {
  margin: 0px auto;
  width:500px
}
</style>
<script src="https://unpkg.com/vue@3.3.4/dist/vue.global.js"></script>
    <script src="https://unpkg.com/vue-demi"></script>
    <script src="https://unpkg.com/pinia@2.1.7/dist/pinia.iife.prod.js"></script>
    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/stompjs@2.3.3/lib/stomp.min.js"></script>
</head>
<%--
   SEOUL("60","127","서울"),
   BUSAN("98","76","부산"),
   DAEGU("89","90","대구"),
   INCHEON("55", "124", "인천"),
   GWANGJU("58", "74", "광주"),
   DAEJEON("67", "100", "대전"),
   ULSAN("102", "84", "울산"),
   SEJONG("66", "103", "세종"),
   JEJU("52", "38", "제주");
 --%>
<body>
  <div class="container">
    <div class="row">
     <select class="input-sm" @change="changeCity" v-model="store.city">
       <option value="SEOUL">서울</option>
       <option value="BUSAN">부산</option>
       <option value="DAEGU">대구</option>
       <option value="INCHEON">인천</option>
       <option value="GWANGJU">광주</option>
       <option value="DAEJEON">대전</option>
       <option value="ULSAN">울산</option>
       <option value="SEJONG">세종</option>
       <option value="JEJU">제주</option>
     </select>
    </div>
    <div class="row" style="margin-top: 10px">
      <h3>기온:{{store.weather.temp}}</h3>
      <h3>날씨:{{store.weather.rain}}</h3>
    </div>
  </div>
  
  <script>
   const {createApp} = Vue
   const {createPinia,defineStore} = Pinia
   
   const useWeatherStore=defineStore('weather',{
	   state:()=>({
		   weather:{},
		   city:'SEOUL'
	   }),
	   actions:{
		   setWeather(w){
			   this.weather=w
		   }
		   
	   }
   })
   
   const app=createApp({
	  setup(){
		  const store=useWeatherStore()
		  const sock=new SockJS('/ws')
		  const stomp=Stomp.over(sock)
		  
		  stomp.connect({},()=>{
			  stomp.subscribe('/sub/weather',msg=>{
				  store.setWeather(JSON.parse(msg.body))
			  })
		  })
		  function changeCity(){
			  stomp.send('/pub/weather',{},store.city)
		  }
		  // 
		  
		  return {
			    store,changeCity
		  }
	  } 
   })
   app.use(createPinia())
   app.mount('.container')
  </script>
</body>
</html>