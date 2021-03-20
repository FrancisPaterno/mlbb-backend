package com.paternocode.springbootbackend;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paternocode.springbootbackend.controller.HeroController;
import com.paternocode.springbootbackend.model.Hero;
import com.paternocode.springbootbackend.model.Role;
import com.paternocode.springbootbackend.service.HeroService;

@WebMvcTest(HeroController.class)
public class HeroControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private HeroService heroService;
	
	@Test
	public void getHeroById() throws Exception {
		Hero hero = new Hero("Akai", 1, Role.TANK, "Guard, Crowd Control", "qPDheVUyH9k");
		
		Mockito.when(heroService.getHeroById(ArgumentMatchers.anyLong())).thenReturn(ResponseEntity.ok(hero));
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/heroes/4"))
		.andDo(MockMvcResultHandlers.print())
		.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Akai"))
		.andExpect(MockMvcResultMatchers.jsonPath("$.role").value("TANK"))
		.andExpect(MockMvcResultMatchers.jsonPath("$.specialty").value("Guard, Crowd Control"))
		.andExpect(MockMvcResultMatchers.jsonPath("$.videoId").value("qPDheVUyH9k"))
		.andExpect(MockMvcResultMatchers.status().isOk());
		
	}
	
	@Test
	public void saveHeroTest() throws Exception {
		Hero hero = new Hero("Akai", 1, Role.TANK, "Guard, Crowd Control", "qPDheVUyH9k");
		
		Mockito.when(heroService.addHero(ArgumentMatchers.any(Hero.class))).thenReturn(new ResponseEntity<Hero>(hero, HttpStatus.CREATED));

		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/heroes")
			.content(new ObjectMapper().writeValueAsString(hero))
			.contentType(MediaType.APPLICATION_JSON))
			.andDo(MockMvcResultHandlers.print())
			.andExpect(MockMvcResultMatchers.status().isCreated())
			.andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
			.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Akai"))
			.andExpect(MockMvcResultMatchers.jsonPath("$.role").value("TANK"))
			.andExpect(MockMvcResultMatchers.jsonPath("$.specialty").value("Guard, Crowd Control"))
			.andExpect(MockMvcResultMatchers.jsonPath("$.videoId").value("qPDheVUyH9k"));
		
	}
	
	@Test
	public void saveHerowithBlankNameTest() throws Exception{
		Hero hero = new Hero("", 1, Role.TANK, "Guard, Crowd Control", "qPDheVUyH9k");
		
		Mockito.when(heroService.addHero(ArgumentMatchers.any(Hero.class))).thenReturn(new ResponseEntity<Hero>(hero, HttpStatus.BAD_REQUEST));

		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/heroes")
			.content(new ObjectMapper().writeValueAsString(hero))
			.contentType(MediaType.APPLICATION_JSON))
			.andDo(MockMvcResultHandlers.print())
			.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	
	@Test
	public void getAllHeroes() throws Exception{
		Hero hereoAkai = new Hero("Akai", 1, Role.TANK, "Guard,Crowd Control", "xW1uRTIQzvA");
		Hero heroAldous = new Hero("Aldous", 2, Role.FIGHTER, "Burst, Support", "xM1Gh99VOx8");
		Hero heroAlice = new Hero("Alice", 2, Role.MAGE, "Charge, Regen", "aMdrI_zCrjo");
		List<Hero> heroList = new ArrayList<Hero>();
		heroList.add(hereoAkai);
		heroList.add(heroAldous);
		heroList.add(heroAlice);
		
		Mockito.when(heroService.getAllHeroes()).thenReturn(heroList);
		
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/heroes/all"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.jsonPath("$",Matchers.hasSize(3)))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("Akai")))
        .andExpect(MockMvcResultMatchers.jsonPath("$[1].role", Matchers.is("FIGHTER")))
		.andExpect(MockMvcResultMatchers.jsonPath("$[2].specialty", Matchers.is("Charge, Regen")));
		Mockito.verify(heroService, VerificationModeFactory.times(1)).getAllHeroes();
		Mockito.verifyNoMoreInteractions(heroService);
	}
	
	
}
