package com.hubgamers.api.service;

import com.hubgamers.api.model.Tag;
import com.hubgamers.api.repository.TagRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class TagService {
	
	private final TagRepository tagRepository;

	public TagService(TagRepository tagRepository) {
		this.tagRepository = tagRepository;
	}
	
	public List<String> getColumns() {
		return Arrays.asList("id", "name");
	}
	
	public Iterable<Tag> getAllTags() {
		return tagRepository.findAll();
	}
	
	public Tag findTagById(Long id) {
		return tagRepository.findById(id).orElse(null);
	}
}
