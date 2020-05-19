package com.example.demo;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

import com.example.demo.modelo.TsscAdmin;
import com.example.demo.modelo.TsscGame;
import com.example.demo.servicios.AdminServiceImp;
import com.example.demo.servicios.GameServiceImp;

@EntityScan("com.example.demo.modelo")
@SpringBootApplication
public class Taller2Application {

	@Bean
	public Java8TimeDialect java8TimeDialect() {
		return new Java8TimeDialect();
	}
	
	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(Taller2Application.class, args);
		
		AdminServiceImp adminS = ctx.getBean(AdminServiceImp.class);
		TsscAdmin admin = new TsscAdmin();
		admin.setPassword("{noop}123456");
		admin.setSuperAdmin(AdminServiceImp.SUPER_ADMIN);
		admin.setUser("Mascapo");
		adminS.save(admin);
		TsscAdmin admin2 = new TsscAdmin();
		admin2.setPassword("{noop}1234");
		admin2.setSuperAdmin(AdminServiceImp.ADMIN);
		admin2.setUser("rongas");
		adminS.save(admin2);
		
		GameServiceImp gameS = ctx.getBean(GameServiceImp.class);
		TsscGame game = new TsscGame();
		game.setName("Halito");
		game.setScheduledDate(LocalDate.of(2220, 10, 12));
		game.setScheduledTime(LocalTime.of(20, 15));
		game.setNSprints(20);
		game.setNGroups(14);
		gameS.createGame(game);
	}

}
