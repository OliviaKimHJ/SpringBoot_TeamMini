package com.example.sb.searchPlace;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import lombok.RequiredArgsConstructor;

@RequestMapping("/searchPlace")
@RequiredArgsConstructor
@Controller
public class SearchPlaceController {
	
	private final SearchPlaceService searchPlaceService;
	
	@GetMapping("/searchMap")
	public String searchMap() {
		return "searchMap";
	}
	
	@ResponseBody
	@PostMapping("/addPlace")
	public List<Integer> addPlace(@RequestParam String list) {
		
		System.out.println(list);
		List<Integer> pCodeList = new ArrayList<>();
		
		Gson gson = new Gson();
		// Map을 쓰는 이유가 key value를 가져올 수 있는 유일한 것이기 때문
		ArrayList<Map<String, String>> data = gson.fromJson(
					list, new TypeToken<ArrayList<Map<String, String>>>() 
						{}.getType()); // typeToken안에있는 리스트 안에있는 Map의 내용의 모양을 잡아준다.
		// 그냥 Map만있었으면 (list, Map.class)
		// String였던 list를 Arr<Map>으로 변환시켜줌
		
		for(Map<String, String> d : data) { 
			System.out.println(d.get("placeName"));
			System.out.println(d.get("latitude"));
			System.out.println(d.get("longitude"));
			
			SearchPlace placeInfo = new SearchPlace();
			
			placeInfo.setPlaceName(d.get("placeName"));// map의 get메서드는 key값을 입력하면 해당 값을 가져온다
			placeInfo.setLatitude(d.get("latitude"));
			placeInfo.setLongitude(d.get("longitude"));
			
			Integer pCode = searchPlaceService.savePlace(placeInfo);
			
			pCodeList.add(pCode);

		}

		return pCodeList; // map.js에 ajax로 넘어감
	}
	
}

