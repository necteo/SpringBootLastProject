package com.sist.web.manager;
// apikey , base_date , base_time , nx , ny
//          yyyyMMdd     HH00
public enum CityGrid {
   SEOUL("60","127","서울"),
   BUSAN("98","76","부산"),
   DAEGU("89","90","대구"),
   INCHEON("55", "124", "인천"),
   GWANGJU("58", "74", "광주"),
   DAEJEON("67", "100", "대전"),
   ULSAN("102", "84", "울산"),
   SEJONG("66", "103", "세종"),
   JEJU("52", "38", "제주");
	
   private final String nx;
   private final String ny;
   private final String krName;
   
   CityGrid(String nx, String ny, String krName) {
	this.nx = nx;
	this.ny = ny;
	this.krName = krName;
   }

   public String getNx() {
	return nx;
   }
	
   public String getNy() {
		return ny;
   }
	
   public String getKrName() {
		return krName;
   }
   
   public static CityGrid from(String city)
   {
	   return CityGrid.valueOf(city.toUpperCase());
   }
}