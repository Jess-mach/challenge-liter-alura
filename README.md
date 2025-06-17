📚 LiterAlura - Catálogo de Livros

📖 Sobre o Projeto

**LiterAlura** é uma aplicação de catálogo de livros desenvolvida como parte do Challenge da Alura. A aplicação permite buscar livros através da API Gutendex, armazenar informações no banco de dados e realizar consultas avançadas sobre livros e autores.

### 🎯 Funcionalidades Principais

- ✅ **Busca de livros** pela API Gutendx
- ✅ **Armazenamento** em banco PostgreSQL
- ✅ **Prevenção de duplicatas** automática
- ✅ **Listagem de livros** registrados
- ✅ **Listagem de autores** registrados
- ✅ **Busca de autores vivos** em ano específico
- ✅ **Filtro de livros por idioma**

## 🛠️ Tecnologias Utilizadas

### Backend
- **Java 17** - Linguagem de programação
- **Spring Boot 3.5.0** - Framework principal
- **Spring Data JPA** - Persistência de dados
- **Jackson** - Serialização/Deserialização JSON
- **Maven** - Gerenciamento de dependências

### Banco de Dados
- **PostgreSQL** - Banco principal
- **H2** - Banco para testes

### Testes
- **JUnit 5** - Framework de testes
- **Mockito** - Mocks e stubs
- **Spring Boot Test** - Testes de integração
- **JaCoCo** - Cobertura de código

### API Externa
- **Gutendx API** - Fonte de dados de livros


## 🎮 Como Usar

### Menu Principal
```
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
```

### Exemplos de Uso

#### 1. Buscar Livro
- Escolha opção `1`
- Digite: `Dom Casmurro`
- A aplicação buscará na API e salvará no banco

#### 2. Autores Vivos em Determinado Ano
- Escolha opção `4`
- Digite: `1850`
- Visualize autores que estavam vivos em 1850

#### 3. Filtrar por Idioma
- Escolha opção `5`
- Escolha: `pt` (Português)
- Veja todos os livros em português

## 🏗️ Arquitetura

### Estrutura do Projeto
```
src/
├── main/java/com/challenge/liter/alura/
│   ├── LiterAluraApplication.java          # Classe principal
│   ├── dto/                                # Data Transfer Objects
│   │   ├── DadosAutor.java
│   │   ├── DadosLivro.java
│   │   └── DadosResposta.java
│   ├── model/                              # Entidades JPA
│   │   ├── Autor.java
│   │   └── Livro.java
│   ├── repository/                         # Repositórios JPA
│   │   ├── AutorRepository.java
│   │   └── LivroRepository.java
│   ├── service/                            # Serviços
│   │   ├── ConsumoApi.java
│   │   ├── ConverteDados.java
│   │   └── IConverteDados.java
│   └── principal/                          # Lógica principal
│       └── ClassePrincipal.java
└── test/                                   # Testes unitários e integração
```


## 🧪 Executando Testes

### Todos os Testes
```bash
mvn test
```

### Testes por Categoria
```bash
# Testes unitários
mvn test -Dtest="**/*Test"

# Testes de integração
mvn test -Dtest="**/*IntegrationTest"

# Testes com cobertura
mvn jacoco:report
```

## 📊 Funcionalidades Técnicas

### 1. Consumo de API
- **HTTP Client nativo** do Java 11+
- **Timeout configurável** (50s conexão, 120s leitura)
- **Tratamento de erros** robusto

### 2. Persistência de Dados
- **Spring Data JPA** com repositórios customizados
- **Queries nativas** para consultas complexas
- **Constraints de unicidade** para evitar duplicatas

### 3. Conversão de Dados
- **Jackson ObjectMapper** para JSON
- **Records** para DTOs imutáveis
- **Annotations @JsonAlias** para mapeamento

### 4. Validações
- **Verificação de duplicatas** antes de salvar
- **Tratamento de dados nulos** em autores/livros
- **Validação de entrada** do usuário

## 🔍 Queries Importantes

### Autores Vivos em Determinado Ano
```sql
SELECT a FROM Autor a 
WHERE a.anoNascimento <= :ano 
AND (a.anoFalecimento IS NULL OR a.anoFalecimento >= :ano)
```

### Busca de Livros por Idioma
```sql
SELECT l FROM Livro l 
WHERE UPPER(l.idioma) LIKE UPPER(CONCAT('%', :idioma, '%'))
```

## 📈 Melhorias Futuras

### Próximas Funcionalidades
- [ ] Interface Web com Spring Boot + Thymeleaf
- [ ] Cache com Redis para consultas frequentes
- [ ] Paginação para listagens grandes
- [ ] Export de dados em CSV/Excel
- [ ] API REST para integração externa

### Otimizações Técnicas
- [ ] Pool de conexões configurado
- [ ] Logging estruturado com Logback
- [ ] Monitoramento com Actuator
- [ ] Docker Compose para desenvolvimento
- [ ] CI/CD com GitHub Actions

## 👨‍💻 Autor

**Jessica Machado**


## 🙏 Agradecimentos

- **Alura** - Pela oportunidade do challenge
- **Gutendx API** - Pela disponibilização dos dados
- **Spring Community** - Pelo excelente framework
- **PostgreSQL Team** - Pelo banco de dados robusto

---

⭐ **Se este projeto te ajudou, deixe uma estrela!** ⭐
