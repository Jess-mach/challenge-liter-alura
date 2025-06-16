package com.challenge.liter.alura.service;

import com.challenge.liter.alura.dto.DadosAutor;
import com.challenge.liter.alura.dto.DadosLivro;
import com.challenge.liter.alura.dto.DadosResposta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ConverteDadosTest {

    private ConverteDados converteDados;

    @BeforeEach
    void setUp() {
        converteDados = new ConverteDados();
    }

    @Test
    void deveConverterJsonParaDadosLivro() {
        String json = """
                {
                    "id": 1,
                    "title": "Dom Casmurro",
                    "authors": [
                        {
                            "name": "Machado de Assis",
                            "birth_year": 1839,
                            "death_year": 1908
                        }
                    ],
                    "languages": ["pt"],
                    "download_count": 1000
                }
                """;

        DadosLivro resultado = converteDados.obterDados(json, DadosLivro.class);

        assertNotNull(resultado);
        assertEquals(1L, resultado.id());
        assertEquals("Dom Casmurro", resultado.titulo());
        assertEquals(1, resultado.autores().size());
        assertEquals("Machado de Assis", resultado.autores().get(0).nome());
        assertEquals(1839, resultado.autores().get(0).anoNascimento());
        assertEquals(1908, resultado.autores().get(0).anoFalecimento());
        assertEquals("pt", resultado.idiomas().get(0));
        assertEquals(1000, resultado.numeroDownloads());
    }

    @Test
    void deveConverterJsonParaDadosResposta() {
        String json = """
                {
                    "results": [
                        {
                            "id": 1,
                            "title": "Emma",
                            "authors": [
                                {
                                    "name": "Jane Austen",
                                    "birth_year": 1775,
                                    "death_year": 1817
                                }
                            ],
                            "languages": ["en"],
                            "download_count": 2000
                        }
                    ]
                }
                """;

        DadosResposta resultado = converteDados.obterDados(json, DadosResposta.class);

        assertNotNull(resultado);
        assertEquals(1, resultado.resultados().size());
        assertEquals("Emma", resultado.resultados().get(0).titulo());
        assertEquals("Jane Austen", resultado.resultados().get(0).autores().get(0).nome());
    }

    @Test
    void deveLancarExcecaoParaJsonInvalido() {
        String jsonInvalido = "{ invalid json }";

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> converteDados.obterDados(jsonInvalido, DadosLivro.class));

        assertTrue(exception.getMessage().contains("Erro ao converter JSON"));
    }
}