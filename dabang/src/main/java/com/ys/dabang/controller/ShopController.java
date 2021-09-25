package com.ys.dabang.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ys.dabang.service.ShopService;

import lombok.AllArgsConstructor;
import medios.cmmn.PageInfoDTO;

@Controller
@AllArgsConstructor
@RequestMapping(value = "/shop/**")
public class ShopController {

	private ShopService service;

	private static final Logger logger = LoggerFactory.getLogger(ShopController.class);

	// 쇼핑 리스트 뿌리기
	@CrossOrigin(origins = "*", maxAge = 3600)
	@GetMapping(value = "/getList")
	public @ResponseBody Map<String, Object> getList(
			@RequestParam(value = "nextToken", required = false, defaultValue = "1") int nextToken) throws Exception {
		PageInfoDTO pageInfoDTO = new PageInfoDTO(nextToken);
		Map<String, Object> param = pageInfoDTO.makePageNumber();

		param.put("START", param.get("start"));
		param.put("END", param.get("end"));
		List<Map<String, Object>> temp = service.getList(param);

		List itemList = new ArrayList<>();
		Map<String, Object> pageInfo = new HashMap<String, Object>();

		Map<String, Object> resultMap = new HashMap<String, Object>();

		for (int i = 0; i < temp.size(); i++) {
			itemList.add(temp.get(i));
		}

		int realEnd = (int) (Math.ceil(Integer.parseInt(temp.get(0).get("TOTAL_COUNT").toString()) / 10.0));
		System.out.println("realEnd = > " + realEnd);

		pageInfo.put("TOTAL_COUNT", temp.get(0).get("TOTAL_COUNT"));
		pageInfo.put("NTEXT_TOKEN", param.get("nextToken"));
		pageInfo.put("PER_PAGE", param.get("perPage"));
		pageInfo.put("REAL_END", realEnd);

		resultMap.put("pageInfo", pageInfo);
		resultMap.put("items", itemList);

		System.out.println("쇼팡 리스들 = > " + resultMap);

		return resultMap;

	}

	// 쇼핑 페이지로이동
	// 쇼핑 리스트 뿌리기
	@CrossOrigin(origins = "*", maxAge = 3600)
	@GetMapping(value = "/moveShop")
	public String moveShop(Model model) {

		model.addAttribute("shopList", service.moveShop());

		return "/shopList";
	}
	
	// 쇼핑 페이지 작성 페이지
	
	@CrossOrigin(origins = "*", maxAge = 3600)
	@GetMapping(value = "/writeShop")
	public String writeShop(Model model) {
		
		return "/writeShop";
	}
	
}
