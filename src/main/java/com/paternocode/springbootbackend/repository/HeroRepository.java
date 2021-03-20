package com.paternocode.springbootbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paternocode.springbootbackend.model.Hero;

@Repository
public interface HeroRepository extends JpaRepository<Hero, Long>{
}
