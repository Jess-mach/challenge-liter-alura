package com.challenge.liter.alura;

import com.challenge.liter.alura.principal.ClassePrincipal;
import com.challenge.liter.alura.repository.AutorRepository;
import com.challenge.liter.alura.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AluraApplication implements CommandLineRunner {

	@Autowired
	private LivroRepository livroRepository;

	@Autowired
	private AutorRepository autorRepository;

	public static void main(String[] args) {
		SpringApplication.run(AluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ClassePrincipal principal = new ClassePrincipal(livroRepository, autorRepository);
		principal.exibeMenu();
	}
}
