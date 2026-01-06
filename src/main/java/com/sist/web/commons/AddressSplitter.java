package com.sist.web.commons;

import java.util.List;

import com.sist.web.vo.VO;

public class AddressSplitter {
	
	public static <T extends VO> void splitAddress(List<T> list) {
		for (T vo : list) {
			String[] addrs = vo.getAddress().split(" ");
			vo.setAddress(addrs[0] + " " + addrs[1]);
		}
	}

}
