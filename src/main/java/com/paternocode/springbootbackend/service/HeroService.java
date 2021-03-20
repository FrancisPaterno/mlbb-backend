package com.paternocode.springbootbackend.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.paternocode.springbootbackend.model.Hero;
import com.paternocode.springbootbackend.model.HeroSearchCriteria;
import com.paternocode.springbootbackend.repository.HeroCriteriaRepository;
import com.paternocode.springbootbackend.repository.HeroRepository;

@Service
public class HeroService {

	@Autowired
	private HeroRepository heroRepository;
	@Autowired
	private HeroCriteriaRepository criteriaRepository;
	
//	@Autowired
//	public HeroService(HeroRepository heroRepository, @Qualifier("main")HeroCriteriaRepository criteriaRepository) {
//		super();
//		this.heroRepository = heroRepository;
//		this.criteriaRepository = criteriaRepository;
//	}
	
	public List<Hero> getAllHeroes(){
		return heroRepository.findAll();
	}
	
	public Hero addHero(Hero hero) {
		return heroRepository.save(hero);
	}
	
	public ResponseEntity<Hero> getHeroById(Long id) {
		
		Hero hero = heroRepository.findById(id).orElseThrow(()->new IllegalStateException("Hero with id:" + id + " not found."));
		return ResponseEntity.ok(hero);
	}
	
	public ResponseEntity<Hero> updateHero(Long id, Hero updHero){
		Hero hero = heroRepository.findById(id).orElseThrow(()->new IllegalStateException("Hero with id:" + id + " not found."));
	
		hero.setName(updHero.getName());
		hero.setCode(updHero.getCode());
		hero.setSpecialty(updHero.getSpecialty());
		hero.setRole(updHero.getRole());
		hero.setVideoId(updHero.getVideoId());
		Hero updtdHero = heroRepository.save(hero);
		
		return ResponseEntity.ok(updtdHero);
	}
	
	public ResponseEntity<Map<String, Boolean>> deleteHero(Long id){
		Boolean heroExist = heroRepository.existsById(id);
		if(!heroExist) {
			throw new IllegalStateException("Hero with id:" + id + " does not exist.");
		}

		heroRepository.deleteById(id);
		Map<String, Boolean> res = new HashMap<String, Boolean>();
		res.put("Deleted", Boolean.TRUE);
		return ResponseEntity.ok(res);
	}
	
	public List<Hero> getHeroBySearchCategory(HeroSearchCriteria heroSearchCriteria){
		return this.criteriaRepository.findHeroByCriteria(heroSearchCriteria);
	}
}
