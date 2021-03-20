package com.paternocode.springbootbackend.repository;

import java.util.List;

import com.paternocode.springbootbackend.model.Hero;
import com.paternocode.springbootbackend.model.HeroSearchCriteria;

public interface HeroCriteriaRepository {
	public List<Hero> findHeroByCriteria(HeroSearchCriteria searchCriteria);
}
