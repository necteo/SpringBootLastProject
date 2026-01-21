package com.sist.web.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sist.web.mapper.NoticeMapper;
import com.sist.web.vo.NoticeVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {
	
	private final NoticeMapper mapper;
	private final int ROW_SIZE = 10;

	@Override
	public Map<String, Object> noticeListData(String page) {
		int curpage = (page == null) ? 1 : Integer.parseInt(page);
		int start = (curpage - 1) * ROW_SIZE;
		List<NoticeVO> list = mapper.noticeListData(start);
		int count = mapper.noticeTotalCount();
		int totalpage = (int) (Math.ceil(1.0 * count / ROW_SIZE));
		count = count - start;
		
		Map<String, Object> map = new HashMap<>();
		map.put("list", list);
		map.put("curpage", curpage);
		map.put("totalpage", totalpage);
		map.put("count", count);
		return map;
	}

	@Override
	public void noticeInsert(NoticeVO vo, String uploadDir) throws Exception {
		File dir = new File(uploadDir);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		
		List<MultipartFile> files = vo.getFiles();
		String filename = "";
		boolean checked = false;
		for (MultipartFile file : files) {
			if (file.isEmpty()) {
				checked = false; // 파일이 없는 상태
			} else {
				String oname = file.getOriginalFilename();
				File f = new File(uploadDir + "/" + oname);
				if (f.exists()) {
					int count = 1;
					String name = oname.substring(0, oname.lastIndexOf("."));
					String ext = oname.substring(oname.lastIndexOf("."));
					while (f.exists()) {
						String newname = name + " (" + count + ")" + ext;
						f = new File(uploadDir + "/" + newname);
						count++;
					}
				}
				filename += f.getName() + ",";
				checked = true; // 파일이 존재하는 상태
				//////////// Upload
				Path path = Paths.get(uploadDir, f.getName());
				Files.copy(file.getInputStream(), path);
			}
		}
		// 데이터베이스 저장
		if (checked) {
			filename = filename.substring(0, filename.lastIndexOf(","));
			vo.setFilename(filename);
			vo.setFilecount(files.size());
		} else {
			vo.setFilename("");
			vo.setFilecount(0);
		}
		mapper.noticeInsert(vo);
	}

	@Override
	public NoticeVO noticeDetailData(int no) {
		mapper.hitIncrement(no);
		return mapper.noticeDetailData(no);
	}

	@Override
	public void noticeDelete(int no, String path) {
		NoticeVO info = mapper.noticeFileInfoData(no);
		if (info.getFilecount() != 0) {
			// 파일 삭제
			StringTokenizer st = new StringTokenizer(info.getFilename(), ",");
			while (st.hasMoreTokens()) {
				File f = new File(path + "/" + st.nextToken());
				f.delete();
			}
		}
		mapper.noticeDelete(no);
	}

	@Override
	public NoticeVO noticeUpdateData(int no) {
		return mapper.noticeDetailData(no);
	}

	@Override
	public void noticeUpdate(NoticeVO vo) {
		mapper.noticeUpdate(vo);
	}

}
