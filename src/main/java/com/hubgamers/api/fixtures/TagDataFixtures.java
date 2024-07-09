package com.hubgamers.api.fixtures;

import com.hubgamers.api.model.Tag;
import com.hubgamers.api.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class TagDataFixtures {
	
	private final TagRepository tagRepository;

	@Autowired
	public TagDataFixtures(TagRepository tagRepository) {
		this.tagRepository = tagRepository;
	}
	
	public void createData() {
		Set<String> names = Set.of("RPG", "FPS", "Competitive", "Casual", "MMO", "Battle Royale", "MOBA");
		if (tagRepository.count() == 0) {
			for (String name : names) {
				Tag tag = new Tag();
				tag.setName(name);
				tagRepository.save(tag);
			}
		}
	}
}
