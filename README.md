# 📘 Trabalho Prático 5 — Coloração de Grafos com DSatur

**Equipe:**
- João Vítor Silva
- Antonio Davi
- Pablo Dornelles

---

# 📌 Descrição

Este trabalho consiste na resolução de um problema de **coloração de grafos** aplicado ao **mapa político do Brasil**, modelado como um **grafo não direcionado**.

O objetivo é atribuir cores aos vértices de forma que **estados vizinhos não recebam a mesma cor**, utilizando o algoritmo **DSatur**.

A solução foi dividida em duas partes:

1. **Modelagem do mapa do Brasil como grafo**
2. **Implementação do algoritmo de coloração DSatur**

---

# 🔧 Modelagem do Grafo

Inicialmente, foi construído um grafo em que:

- cada vértice representa um estado brasileiro
- cada aresta representa uma fronteira entre dois estados

Os dados do grafo foram organizados no arquivo:

```text
dados/brasil.txt
```

Nesse arquivo:

- a primeira linha indica a quantidade de vértices
- a segunda linha indica a quantidade de arestas
- as linhas seguintes representam os pares de vértices adjacentes

O programa realiza a leitura desse arquivo e constrói a lista de adjacência correspondente.

---

# 💻 Implementação

O programa foi desenvolvido em **Java** e realiza:

- leitura do grafo a partir de arquivo
- construção da estrutura de adjacência
- impressão do número de vértices e arestas
- exibição da lista de adjacência com as siglas dos estados
- execução do algoritmo **DSatur**
- definição da ordem de coloração dos vértices
- atribuição das cores aos estados
- verificação da validade da coloração final
- exibição do total de cores utilizadas

Os principais arquivos do projeto são:

```text
src/Main.java
src/Graph.java
src/GraphColoringDSatur.java
```

---

# ▶️ Como Executar

Na raiz do projeto, execute:

```bash
javac src/*.java
java -cp src Main dados/brasil.txt
```

Certifique-se de que o arquivo abaixo existe:

```text
dados/brasil.txt
```

---

# 📊 Saída Esperada

O programa exibe:

- quantidade de vértices e arestas do grafo
- lista de adjacência dos estados
- ordem de coloração gerada pelo DSatur
- cor final atribuída a cada estado
- total de cores utilizadas
- confirmação de que a coloração é válida

Exemplo:

```text
=== GRAFO DO BRASIL ===
Vertices: 27
Arestas : 50

Lista de adjacencia:
AC ( 0): AM(2), RO(20)
...

=== RESULTADO DO DSATUR ===
Ordem de coloracao (DSatur):
 1) BA (4)
...

Cor final de cada estado:
AC ( 0) -> cor 1
...

Total de cores utilizadas: 4
Coloracao valida? SIM
```

## Apresentação em Vídeo

[Assista à apresentação do projeto aqui](https://youtu.be/MbJ58YNu3XY)
