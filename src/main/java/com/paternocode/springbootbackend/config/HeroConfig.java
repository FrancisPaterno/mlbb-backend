package com.paternocode.springbootbackend.config;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.paternocode.springbootbackend.model.Hero;
import com.paternocode.springbootbackend.model.Role;
import com.paternocode.springbootbackend.repository.HeroRepository;

@Configuration
public class HeroConfig {

	@Bean
	CommandLineRunner commandLineRunner(HeroRepository heroRepository) {
			return args->{
				Hero heroAkai = new Hero("Akai", 9, Role.TANK, "Guard, Crowd Control", "bG_WtwNZ99U&t");
				Hero heroAldous = new Hero("Aldous", 64, Role.FIGHTER, "Burst, Support", "bG_WtwNZ99U&t");
				Hero heroAlice = new Hero("Alice", 4, Role.MAGE, "Charge, Regen", "bG_WtwNZ99U&t");
				Hero heroBenedetta = new Hero("Benedetta", 97, Role.ASSASSIN, "Chase, Burst", "bG_WtwNZ99U&t");
				Hero heroBrody = new Hero("Brody", 100, Role.MARKSMAN, "Burst, Reap", "bG_WtwNZ99U&t");
				Hero heroCarmilla = new Hero("Carmilla", 92, Role.SUPPORT, "Crowd Control, Damage", "bG_WtwNZ99U&t");
				Hero heroCecilion = new Hero("Cecilion", 91, Role.MAGE, "Poke, Burst", "bG_WtwNZ99U&t");
				Hero heroDiggie = new Hero("Diggie", 48, Role.SUPPORT, "Guard, Poke", "bG_WtwNZ99U&t");
				Hero heroDyrroth = new Hero("Dyrroth", 85, Role.FIGHTER, "Charge, Burst", "bG_WtwNZ99U&t");
				Hero heroEsmeralda = new Hero("Esmeralda", 81, Role.MAGE, "Regen, Mixed Damage", "bG_WtwNZ99U&t");
				Hero heroEstes = new Hero("Estes", 34, Role.SUPPORT, "Regen, Guard", "bG_WtwNZ99U&t");
				Hero heroFranco = new Hero("Franco", 10, Role.TANK, "Initiator, Control", "bG_WtwNZ99U&t");
				Hero heroFreya = new Hero("Freya", 22, Role.FIGHTER, "Chase, Damage", "bG_WtwNZ99U&t");
				Hero heroGatotkaca = new Hero("Gatotkaca", 41, Role.TANK, "Crowd Control, Burst", "bG_WtwNZ99U&t");
				Hero heroGord = new Hero("Gord", 23, Role.MAGE, "Poke, Burst", "bG_WtwNZ99U&t");
				Hero heroHanabi = new Hero("Hanabi", 60, Role.MARKSMAN, "Reap, Damage", "bG_WtwNZ99U&t");
				Hero heroHanzo = new Hero("Hanzo", 69, Role.ASSASSIN, "Poke, Burst", "bG_WtwNZ99U&t");
				Hero heroIrithel = new Hero("Irithel", 43, Role.MARKSMAN, "Reap, Burst", "bG_WtwNZ99U&t");
				Hero heroJawhead = new Hero("Jawhead", 54, Role.FIGHTER, "Charge, Burst", "bG_WtwNZ99U&t");
				Hero heroJohnson = new Hero("Johnson", 32, Role.TANK, "Crowd Control, Support", "bG_WtwNZ99U&t");
				heroRepository.saveAll(List.of(heroAkai, heroAldous,heroAlice, heroBenedetta, heroBrody, heroCarmilla, heroCecilion,
						heroDiggie, heroDyrroth, heroEsmeralda, heroEstes, heroFranco, heroFreya, heroGatotkaca, heroGord, heroHanabi,
						heroHanzo, heroIrithel, heroJawhead, heroJohnson
						));
			};
	}
}
