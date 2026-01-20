package com.sist.web.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sist.web.mapper.ReserveMapper;
import com.sist.web.vo.ReserveVO;
import com.sist.web.vo.SeoulVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReserveServiceImpl implements ReserveService {

	private final ReserveMapper mapper;
	private final String[] TIMES = {
		"09:00", "10:00", "11:00", "12:00", "12:30", "13:00", "13:30", "14:00", "15:00", 
		"16:00", "17:00", "18:00", "18:30", "19:00", "20:00", "20:30", "21:00", "22:00"
	};
	
	public List<String> timeRand() {
		List<String> res = new ArrayList<>();
		int[] com = new int[(int) (Math.random() * 6) + 5];
		boolean bClick = false;
		int su = 0;
		for (int i = 0; i < com.length; i++) {
			bClick = true;
			while (bClick) {
				su = (int) (Math.random() * 18);
				bClick = false;
				for (int j = 0; j < i; j++) {
					if (com[j] == su) {
						bClick = true;
						break;
					}
				}
			}
			com[i] = su;
		}
		Arrays.sort(com);
		for (int i = 0; i < com.length; i++) {
			res.add(TIMES[com[i]]);
		}
		return res;
	}

	@Override
	public Map<String, Object> seoulReserveData(int page, String address) {
		Map<String, Object> map = new HashMap<>();
		map.put("start", (page - 1) * 10);
		map.put("address", address);
		List<SeoulVO> list = mapper.seoulReserveData(map);
		int totalpage = mapper.seoulReserveTotalPage(address);
		
		map.put("list", list);
		map.put("curpage", page);
		map.put("totalpage", totalpage);
		return map;
	}
	
	@Override
	public String reserveInsert(ReserveVO vo) {
		String res = "";
		try {
			mapper.reserveInsert(vo);
			res = "YES";
		} catch (Exception e) {
			e.printStackTrace();
			res = "NO";
		}
		return res;
	}
	
	@Override
	public List<ReserveVO> reserveMyData(String id) {
		return mapper.reserveMyData(id);
	}

	@Override
	public List<ReserveVO> reserveAdminData() {
		return mapper.reserveAdminData();
	}

}
