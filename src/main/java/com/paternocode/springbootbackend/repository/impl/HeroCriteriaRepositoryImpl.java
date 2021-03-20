package com.paternocode.springbootbackend.repository.impl;

import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.paternocode.springbootbackend.model.Hero;
import com.paternocode.springbootbackend.model.HeroSearchCriteria;
import com.paternocode.springbootbackend.model.Role;
import com.paternocode.springbootbackend.repository.HeroCriteriaRepository;

@Repository("main")
public class HeroCriteriaRepositoryImpl implements HeroCriteriaRepository{

	private final EntityManager entityManager;
	private final CriteriaBuilder criteriaBuilder;
	
	public HeroCriteriaRepositoryImpl(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
		this.criteriaBuilder = this.entityManager.getCriteriaBuilder();
	}

	@Override
	public List<Hero> findHeroByCriteria(HeroSearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		CriteriaQuery<Hero> criteriaQuery = criteriaBuilder.createQuery(Hero.class);
		Root<Hero> heroRoot = criteriaQuery.from(Hero.class);
		Predicate predicate = getPredicate(searchCriteria, heroRoot);
		
		if(predicate != null)
			criteriaQuery.where(predicate);
		
		TypedQuery<Hero> typedQuery = entityManager.createQuery(criteriaQuery);
		
		return typedQuery.getResultList();
	}
	
	private Predicate getPredicate(HeroSearchCriteria heroSearchCriteria, Root<Hero> root) {
		Predicate predicate = null;
		if(Objects.nonNull(heroSearchCriteria.getSearchVal()) && Objects.nonNull(heroSearchCriteria.getCategory())) {
			switch (heroSearchCriteria.getCategory()) {
			case NAME:
				predicate = criteriaBuilder.like(root.get("name"), "%"+ heroSearchCriteria.getSearchVal() + "%");
				break;
			case CODE:
				try {
					int num = Integer.parseInt(heroSearchCriteria.getSearchVal());
					if(num>0)
						predicate = criteriaBuilder.equal(root.get("code"), Integer.parseInt(heroSearchCriteria.getSearchVal()));
				} catch (NumberFormatException e) {
					// TODO: handle exception
					predicate = null;
				}
				
				
				break;
			case ROLE:
				switch (heroSearchCriteria.getSearchVal().trim().toUpperCase()) {
				case "TANK":
					predicate = criteriaBuilder.equal(root.get("role"), Role.TANK);
					break;
				case "SUPPORT":
					predicate = criteriaBuilder.equal(root.get("role"), Role.SUPPORT);
					break;
				case "MARKSMAN":
					predicate = criteriaBuilder.equal(root.get("role"), Role.MARKSMAN);
					break;
				case "MAGE":
					predicate = criteriaBuilder.equal(root.get("role"), Role.MAGE);
					break;
				case "FIGHTER":
					predicate = criteriaBuilder.equal(root.get("role"), Role.FIGHTER);
					break;
				case "ASSASSIN":
					predicate = criteriaBuilder.equal(root.get("role"), Role.ASSASSIN);
					break;
				}
				
				break;
			case SPECIALTIES:
				predicate = criteriaBuilder.like(root.get("specialty"), "%"+ heroSearchCriteria.getSearchVal() + "%");
				break;
			}
			
		}
		return predicate;
	}
	
	
}
