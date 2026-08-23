# ☕ Data Structures in Java

Repositório desenvolvido durante as aulas da disciplina de **Estruturas de Dados** do curso de Bacharelado em Sistemas de Informação (BSI) no **IFBA**, referente ao semestre **2025.2**.

Para começar a explorar os códigos, basta acessar a pasta [`src`](src/).

Durante essa disciplina, também foram desenvolvidos dois projetos práticos para aplicar os conhecimentos aprendidos:

-[`LibQueue: Sistema de Gerenciamento de Biblioteca`](#https://github.com/Nuillexe/school-library-manager/tree/main): Utilização de listas e fila de prioridade 

-[`ChatTree:`](https://github.com/Nuillexe/chat-tree): Forum de conversa baseado em arvore


---

## 📌 Estruturas Implementadas

### 🔹 Interfaces (Contratos)
- `Empilhavel`: Operações para Pilha (LIFO)
- `Enfileiravel`: Operações para Fila (FIFO)
- `DuplamenteEnfileiravel`: Operações para Deque
- `Listavel`: Operações para Lista Indexada

### 🔹 [Estruturas Estáticas](src/repository/estaticas)
- **Pilha Estática**: `PilhaEstatica` / `PilhaEstaticaGenerica<T>`
- **Fila Estática Circular**: `FilaEstatica` / `FilaEstaticaGenerica<T>`
- **Lista Estática**: `ListaEstatica` / `ListaEstaticaGenerica<T>`

### 🔹 [Estruturas Dinâmicas](src/repository/dinamicas)
- **Nó Duplo**
- **Pilha Dinâmica**: `PilhaDinamica`
- **Fila Dinâmica**: `FilaDinamica` e `FilaDinamicaComDuplaTerminação`
- **Lista Dinâmica**: `ListaDinamica`

### 🔹 [Árvores](src/repository/arvores)
- **Nó Triplo**
- **Árvore Binária de Pesquisa (BST)**

---

## 📂 [Classes DAO](src/dao)
Módulos práticos desenvolvidos para aplicar e consolidar o uso das estruturas de dados em cenários do mundo real (Padrão *Data Access Object*).

---

## 🛠️ Tecnologias e Conceitos
- **Linguagem**: Java (JDK 17+)
- **Ambiente de Desenvolvimento**: IntelliJ IDEA / VS Code / Eclipse
- **Conceitos Aplicados**: Orientação a Objetos (POO), Generics, Tratamento de Exceções, Ponteiros e Referências, Padrão DAO.
