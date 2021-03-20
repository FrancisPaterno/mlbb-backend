package com.paternocode.springbootbackend.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paternocode.springbootbackend.model.Hero;
import com.paternocode.springbootbackend.model.HeroSearchCriteria;
import com.paternocode.springbootbackend.service.HeroService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/v1/heroes")
public class HeroController {

	private final HeroService heroService;
	
	public HeroController(HeroService heroService) {
		super();
		this.heroService = heroService;
	}

	@GetMapping("/all")
	public List<Hero> getAllHeroes(){
		return heroService.getAllHeroes();
	}
	
	@GetMapping
	public List<Hero> getHeroesBySearchCategory(HeroSearchCriteria heroSearchCriteria){
		return heroService.getHeroBySearchCategory(heroSearchCriteria);
	}
	
	@PostMapping
	public ResponseEntity<Hero> addHero(@RequestBody @Valid Hero hero) {
		return heroService.addHero(hero);
	}
	
	@GetMapping("{heroId}")
	public ResponseEntity<Hero> getHeroById(@PathVariable("heroId") Long id) {
		return heroService.getHeroById(id);
	}
	
	@PutMapping("{heroId}")
	public ResponseEntity<Hero> updateHero(@PathVariable("heroId") Long id, @RequestBody @Valid Hero hero){
		return heroService.updateHero(id, hero);
	}
	
	@DeleteMapping(path = "{heroId}")
	public ResponseEntity<Map<String, Boolean>> deleteHero(@PathVariable("heroId") Long id){
		return heroService.deleteHero(id);
	}
}
