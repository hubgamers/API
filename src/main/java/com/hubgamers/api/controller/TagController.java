package com.hubgamers.api.controller;

import com.hubgamers.api.model.Tag;
import com.hubgamers.api.response.ResponseJson;
import com.hubgamers.api.service.TagService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/tag")
public class TagController {
	
	private final TagService tagService;

	public TagController(TagService tagService) {
		this.tagService = tagService;
	}
	
	@GetMapping("/columns")
	public List<String> getColumns() {
		return tagService.getColumns();
	}
	
	@GetMapping("/all")
	public ResponseJson<Iterable<Tag>> getAllTags() {
		return new ResponseJson<>(tagService.getAllTags(), HttpStatus.OK.value());
	}
}
