package com.challenge.liter.alura.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LivroTest {

    @Test
    void deveCriarLivroComParametros() {
        Autor autor = new Autor("Machado de Assis", 1839, 1908);
        Livro livro = new Livro("Dom Casmurro", autor, "PT", 1000);

        assertEquals("Dom Casmurro", livro.getTitulo());
        assertEquals(autor, livro.getAutor());
        assertEquals("PT", livro.getIdioma());
        assertEquals(1000, livro.getNumeroDownloads());
    }

    @Test
    void deveCriarLivroVazio() {
        Livro livro = new Livro();

        assertNull(livro.getTitulo());
        assertNull(livro.getAutor());
        assertNull(livro.getIdioma());
        assertNull(livro.getNumeroDownloads());
    }

    @Test
    void deveGerarToStringCorretamente() {
        Autor autor = new Autor("Jane Austen", 1775, 1817);
        Livro livro = new Livro("Emma", autor, "EN", 2000);

        String resultado = livro.toString();

        assertTrue(resultado.contains("Emma"));
        assertTrue(resultado.contains("Jane Austen"));
        assertTrue(resultado.contains("EN"));
        assertTrue(resultado.contains("2000"));
    }

    @Test
    void deveTravarAutorNulo() {
        Livro livro = new Livro("Livro Sem Autor", null, "PT", 100);

        String resultado = livro.toString();

        assertTrue(resultado.contains("Autor desconhecido"));
    }

    @Test
    void deveTravarDownloadsNulo() {
        Autor autor = new Autor("Autor Teste", 1900, 2000);
        Livro livro = new Livro("Livro Teste", autor, "PT", null);

        String resultado = livro.toString();

        assertTrue(resultado.contains("0"));
    }
}