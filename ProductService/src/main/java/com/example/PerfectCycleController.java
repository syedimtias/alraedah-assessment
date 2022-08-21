package com.example;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//Java program to calculate cycles of
// length n in a given graph
@RestController
public class PerfectCycleController {

	@PostMapping(value = "/v1/perfectcycles", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> perfectCycles(@RequestBody(required=true) Map<String, List<Integer>> input) {
		if(input == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		Map<String, Boolean> output = new LinkedHashMap<>();
		input.forEach((key, value) -> {
			output.put(key, detectCycle(key, value));
		});
		return new ResponseEntity<>(output, HttpStatus.OK);
	}

	private Boolean detectCycle(String key, List<Integer> data) {
		if(data == null || data.size() == 0)
			return false;
		
		boolean perfectCycle = true;
		boolean[] vistedList = new boolean[data.size()];
		int arrayLength = data.size() - 1;
		int nextElement = data.get(0);
		//Visit the element and mark as visited
		//If beginning element is not marked by default its not a cycle.
		for (int i = 0; i <= arrayLength; i++) {
			System.out.println("Next Element index: " + nextElement);
			if (nextElement > arrayLength || nextElement < 0) {
				return false;
			} else {
				vistedList[nextElement] = true;
			}
			nextElement = data.get(nextElement);

		}
		//All need to be visited for a perfect cycle
		for (int i = 0; i < vistedList.length; i++) {
			if (!vistedList[i]) {
				perfectCycle = false;
				break;
			}
		}
		System.out.println(key + ": " + perfectCycle);
		return perfectCycle;
	}

}