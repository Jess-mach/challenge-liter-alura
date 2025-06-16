package com.challenge.liter.alura.principal;

import com.challenge.liter.alura.dto.DadosLivro;
import com.challenge.liter.alura.dto.DadosResposta;
import com.challenge.liter.alura.model.Autor;
import com.challenge.liter.alura.model.Livro;
import com.challenge.liter.alura.repository.AutorRepository;
import com.challenge.liter.alura.repository.LivroRepository;
import com.challenge.liter.alura.service.ConsumoApi;
import com.challenge.liter.alura.service.ConverteDados;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class ClassePrincipal  {

    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "https://gutendex.com/books/?search=";

    private LivroRepository repositorioLivro;
    private AutorRepository repositorioAutor;

    public ClassePrincipal(LivroRepository repositorioLivro, AutorRepository repositorioAutor) {
        this.repositorioLivro = repositorioLivro;
        this.repositorioAutor = repositorioAutor;
    }

    public void exibeMenu() {
        var opcao = -1;
        while (opcao != 0) {
            var menu = """

                    ===============================================
                    |            LITERALURA - CATÁLOGO            |
                    ===============================================

                    1 - Buscar livro pelo título
                    2 - Listar livros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos em um determinado ano
                    5 - Listar livros em um determinado idioma

                    0 - Sair

                    ===============================================
                    """;

            System.out.println(menu);
            System.out.print("Escolha uma opção: ");

            try {
                opcao = leitura.nextInt();
                leitura.nextLine();

                switch (opcao) {
                    case 1:
                        buscarLivroWeb();
                        break;
                    case 2:
                        listarLivrosRegistrados();
                        break;
                    case 3:
                        listarAutoresRegistrados();
                        break;
                    case 4:
                        listarAutoresVivosNoAno();
                        break;
                    case 5:
                        listarLivrosPorIdioma();
                        break;
                    case 0:
                        System.out.println("Encerrando aplicação...");
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
                leitura.nextLine(); // Limpa o buffer
            }
        }
    }

    private void buscarLivroWeb() {
        System.out.print("Digite o nome do livro que deseja buscar: ");
        var nomeLivro = leitura.nextLine();

        try {
            var json = consumo.obterDados(ENDERECO + nomeLivro.replace(" ", "%20"));
            var dadosResposta = conversor.obterDados(json, DadosResposta.class);

            if (dadosResposta.resultados().isEmpty()) {
                System.out.println("Livro não encontrado!");
                return;
            }

            DadosLivro dadosLivro = dadosResposta.resultados().get(0);

            // Verificar se o livro já existe
            Optional<Livro> livroExistente = repositorioLivro.findByTituloContainingIgnoreCase(dadosLivro.titulo());
            if (livroExistente.isPresent()) {
                System.out.println("Livro já está registrado no banco de dados!");
                System.out.println(livroExistente.get());
                return;
            }

            // Buscar ou criar autor
            String nomeAutor = dadosLivro.autores().isEmpty() ? "Autor desconhecido" : dadosLivro.autores().get(0).nome();
            Optional<Autor> autorExistente = repositorioAutor.findByNomeContainingIgnoreCase(nomeAutor);

            Autor autor;
            if (autorExistente.isPresent()) {
                autor = autorExistente.get();
            } else {
                var dadosAutor = dadosLivro.autores().isEmpty() ? null : dadosLivro.autores().get(0);
                autor = new Autor(nomeAutor,
                        dadosAutor != null ? dadosAutor.anoNascimento() : null,
                        dadosAutor != null ? dadosAutor.anoFalecimento() : null);
                repositorioAutor.save(autor);
            }

            // Criar e salvar livro
            String idioma = dadosLivro.idiomas().isEmpty() ? "Desconhecido" : dadosLivro.idiomas().get(0);
            Livro livro = new Livro(dadosLivro.titulo(), autor, idioma, dadosLivro.numeroDownloads());
            repositorioLivro.save(livro);

            System.out.println("Livro salvo com sucesso!");
            System.out.println(livro);

        } catch (Exception e) {
            System.out.println("Erro ao buscar livro: " + e.getMessage());
        }
    }

    private void listarLivrosRegistrados() {
        List<Livro> livros = repositorioLivro.findAll();

        if (livros.isEmpty()) {
            System.out.println("Nenhum livro registrado no banco de dados.");
            return;
        }

        System.out.println("\n===== LIVROS REGISTRADOS =====");
        livros.forEach(System.out::println);
    }

    private void listarAutoresRegistrados() {
        List<Autor> autores = repositorioAutor.findAll();

        if (autores.isEmpty()) {
            System.out.println("Nenhum autor registrado no banco de dados.");
            return;
        }

        System.out.println("\n===== AUTORES REGISTRADOS =====");
        autores.forEach(System.out::println);
    }

    private void listarAutoresVivosNoAno() {
        System.out.print("Digite o ano que deseja pesquisar: ");
        try {
            var ano = leitura.nextInt();
            leitura.nextLine();

            List<Autor> autoresVivos = repositorioAutor.findAutoresVivosNoAno(ano);

            if (autoresVivos.isEmpty()) {
                System.out.println("Nenhum autor encontrado vivo no ano " + ano);
                return;
            }

            System.out.println("\n===== AUTORES VIVOS NO ANO " + ano + " =====");
            autoresVivos.forEach(System.out::println);

        } catch (Exception e) {
            System.out.println("Erro: Digite um ano válido!");
            leitura.nextLine();
        }
    }

    private void listarLivrosPorIdioma() {
        var menuIdioma = """

                Digite o idioma desejado:
                es - Espanhol
                en - Inglês
                fr - Francês
                pt - Português

                """;

        System.out.println(menuIdioma);
        System.out.print("Escolha um idioma: ");
        var idioma = leitura.nextLine().toUpperCase();

        List<Livro> livrosPorIdioma = repositorioLivro.findByIdiomaContainingIgnoreCase(idioma);

        if (livrosPorIdioma.isEmpty()) {
            System.out.println("Nenhum livro encontrado no idioma especificado.");
            return;
        }

        System.out.println("\n===== LIVROS EM " + idioma.toUpperCase() + " =====");
        livrosPorIdioma.forEach(System.out::println);
    }
}