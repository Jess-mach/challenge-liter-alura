package com.challenge.liter.alura.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AutorTest {

    @Test
    void deveCriarAutorComParametros() {
        Autor autor = new Autor("Machado de Assis", 1839, 1908);

        assertEquals("Machado de Assis", autor.getNome());
        assertEquals(1839, autor.getAnoNascimento());
        assertEquals(1908, autor.getAnoFalecimento());
        assertNotNull(autor.getLivros());
        assertTrue(autor.getLivros().isEmpty());
    }

    @Test
    void deveCriarAutorVazio() {
        Autor autor = new Autor();

        assertNull(autor.getNome());
        assertNull(autor.getAnoNascimento());
        assertNull(autor.getAnoFalecimento());
        assertNotNull(autor.getLivros());
    }

    @Test
    void deveDefinirLivrosCorretamente() {
        Autor autor = new Autor("Jane Austen", 1775, 1817);
        List<Livro> livros = new ArrayList<>();

        Livro livro1 = new Livro("Emma", autor, "EN", 2000);
        Livro livro2 = new Livro("Pride and Prejudice", autor, "EN", 3000);

        livros.add(livro1);
        livros.add(livro2);

        autor.setLivros(livros);

        assertEquals(2, autor.getLivros().size());
        assertEquals(autor, livro1.getAutor());
        assertEquals(autor, livro2.getAutor());
    }

    @Test
    void deveGerarToStringCorretamente() {
        Autor autor = new Autor("Machado de Assis", 1839, 1908);
        Livro livro = new Livro("Dom Casmurro", autor, "PT", 1000);
        autor.setLivros(List.of(livro));

        String resultado = autor.toString();

        assertTrue(resultado.contains("Machado de Assis"));
        assertTrue(resultado.contains("1839"));
        assertTrue(resultado.contains("1908"));
        assertTrue(resultado.contains("Dom Casmurro"));
    }

    @Test
    void deveTravarValoresNulos() {
        Autor autor = new Autor("Autor Desconhecido", null, null);

        String resultado = autor.toString();

        assertTrue(resultado.contains("N/A"));
    }
}