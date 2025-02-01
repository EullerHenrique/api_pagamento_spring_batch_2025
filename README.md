# Api De Pagamento

## Conceito

### Spring Batch

- Spring Batch é um framework da Spring voltado para o processamento de grandes volumes de dados de forma eficiente e escalável. Ele fornece suporte para execução de tarefas em lote, como leitura, processamento e escrita de dados, garantindo robustez e reprocessamento em caso de falhas.

- Estrutura:
  - **JobLauncher**: Responsável por iniciar um `Job`, acionando sua execução.
  - **Job**: Representa um processo em lote completo. Cada `Job` é composto por um ou mais `Step(s)`.
  - **Step**: Cada `Step` executa uma unidade de trabalho, como leitura, processamento e escrita de dados.
  - **JobRepository**: Componente que gerencia o estado das execuções do batch, armazenando metadados sobre os jobs e passos executados.
  - **ItemReader**: Responsável por ler os dados da fonte (banco de dados, arquivos, filas, etc.).
  - **ItemProcessor**: Realiza transformações ou validações nos dados lidos antes de serem gravados.
  - **ItemWriter**: Escreve os dados processados no destino desejado.

- Fluxo:

  1. Um `Job` é iniciado pelo `JobLauncher`.
  2. O `Job` contém um ou mais `Steps`, que são executados em sequência.
  3. Cada `Step` pode ter um `ItemReader`, um `ItemProcessor` e um `ItemWriter`:
     - O `ItemReader` lê os dados de uma fonte.
     - O `ItemProcessor` processa e transforma os dados.
     - O `ItemWriter` grava os dados no destino final.
  4. O `JobRepository` armazena o estado das execuções, permitindo retomadas em caso de falha.
   
- **Principais vantagens:**

  - **Processamento escalável**: Suporte a execução paralela e particionada para lidar com grandes volumes de dados.
  - **Gerenciamento de estado**: Armazena o progresso dos jobs para permitir retomada em caso de falhas.
  - **Transações e controle de falhas**: Garante consistência dos dados e reprocessamento seguro.
  - **Flexível e configurável**: Suporte a diversas fontes de dados (banco de dados, arquivos, filas, etc.).
  - **Integração com o ecossistema Spring**: Facilita a implementação em aplicações que já utilizam Spring Boot e outros módulos Spring.

- **Em resumo**, Spring Batch é uma solução poderosa para processamento de dados em lote, oferecendo mecanismos para controle, monitoramento e escalabilidade de tarefas críticas.

<div align="center">
  
  <img src="https://github.com/EullerHenrique/api_pagamento_spring_batch_2025/blob/main/imgs/img_1.png" alt="Imagem 1"  height="250px"/>
  <br>
  <img src="https://github.com/EullerHenrique/api_pagamento_spring_batch_2025/blob/main/imgs/img_4.png" alt="Imagem 3" height="250px"/>
  <img src="https://github.com/EullerHenrique/api_pagamento_spring_batch_2025/blob/main/imgs/img_3.png" alt="Imagem 3"  height="250px"/>
  
</div>

## Proposta:
  - Git Hub:
    - [Desafio Pag Net](https://github.com/Pagnet/desafio-back-end)
      - "Sua tarefa é criar uma interface web que aceite upload do arquivo CNAB, normalize os dados e armazene-os em um banco de dados relacional e exiba essas informações em tela.."

### Regras de Negócio:

- O controle de unicidade das transações é feito por arquivo CNAB, o que significa que o processamento das transações é feito apenas uma vez por arquivo.
- O arquivo CNAB deve ser nomeado com um id ou timestamp, pois ele será passado como parâmetro do job e só pode ser importado uma única vez.
- Caso seja informado um arquivo já importado, deve ser informada uma mensagem de erro ao usuário.
- Caso haja erro no processamento é possível submeter o mesmo arquivo várias vezes para habilitar o restart de onde o processamento parou.
- Se o arquivo for muito grande, é possível utilizar uma estratégia de particionamento no job, melhorando assim a performance.

### Arquitetura:

![](https://github.com/EullerHenrique/api_pagamento_spring_batch_2025/blob/main/imgs/img_12.png)

### Processo:

![](https://github.com/EullerHenrique/api_pagamento_spring_batch_2025/blob/main/imgs/img_13.png)

### Tela:

![](https://github.com/EullerHenrique/api_pagamento_spring_batch_2025/blob/main/imgs/img_14.png)

## Referências:

   - Notium:
    - [Desafio Pag Net-Documento de Arquitetura](https://giulianabezerra.notion.site/Desafio-Backend-Pagnet-5bbd08f103e04d6d866b028cec6688b5)
   
  - Slides:  
    - [Palestra Giuliana Bezerra](https://github.com/EullerHenrique/api_pagamento_spring_batch_2025/blob/main/slides/slides_giuliana_bezerra.pdf)

  - Google:
    - [Git Hub Spring Batch](https://github.com/spring-projects/spring-batch)
    - [Site Spring Batch](https://spring.io/batch)
    - [Introduction to Spring Batch](https://www.baeldung.com/introduction-to-spring-batch)
    - [Introdução ao Spring Batch](https://www.devmedia.com.br/introducao-ao-spring-batch/33284)
    - [Frameworks Java, Spring Batch: Processamento de Dados em Lote](https://www.dio.me/articles/spring-batch-processamento-de-dados-em-lote)
   
  - Medium:
    - [Desenvolvimento com Spring Batch — Overview](https://giulianabezerra.medium.com/spring-batch-para-desenvolvimento-de-jobs-1674ec5b9a20)
    - [Desenvolvimento com Spring Batch — Steps](https://giulianabezerra.medium.com/desenvolvimento-com-spring-batch-steps-4d42af2696ec)
    - [Desenvolvimento com Spring Batch — Jobs](https://giulianabezerra.medium.com/desenvolvimento-com-spring-batch-jobs-b4363dd6c676)
    - [Desenvolvimento com Spring Batch — Execução do Job](https://giulianabezerra.medium.com/desenvolvimento-com-spring-batch-execu%C3%A7%C3%A3o-do-job-4bc406152f3d)
    - [Jobs aninhados com Spring Batch](https://giulianabezerra.medium.com/jobs-aninhados-com-spring-batch-8deb02bff1e1)
    - [Controle transacional para múltiplos bancos de dados com Spring Batch](https://giulianabezerra.medium.com/controle-transacional-para-multiplos-datasources-com-spring-batch-acd87095813d)
    - [Leitores compostos no Spring Batch](https://giulianabezerra.medium.com/leitores-compostos-no-spring-batch-2775f9d7a243)
    - [Tolerância a falhas com retry no Spring Batch](https://giulianabezerra.medium.com/tolerancia-a-falhas-com-retry-no-spring-batch-786db305ec13)
    - [Spring Batch para carregar dados de cache no Redis](https://giulianabezerra.medium.com/spring-batch-para-carregar-dados-de-cache-no-redis-c82f75c45bd6)
    - [Testes de Integração com Spring Batch](https://giulianabezerra.medium.com/testes-de-integracao-com-spring-batch-2e019787d081)
    - [Qual o melhor intervalo de commit para o seu job Spring Batch?](https://giulianabezerra.medium.com/qual-o-melhor-intervalo-de-commit-para-o-seu-job-spring-batch-3d32e01960a4)
    - [Desvendando os metadados do Spring Batch](https://giulianabezerra.medium.com/desvendando-os-metadados-do-spring-batch-8cd2eb897813)
    - [Visão geral de técnicas de escalabilidade com Spring Batch](https://giulianabezerra.medium.com/visao-geral-de-tecnicas-de-escalabilidade-com-spring-batch-a3789a6232d8)
    - [Processador de validação composto com Spring Batch](https://giulianabezerra.medium.com/processador-de-validacao-composto-com-spring-batch-bc572c129f84)
    - [Leitura de arquivos com conteúdo customizado utilizando Spring Batch](https://giulianabezerra.medium.com/leitura-de-arquivos-com-conteudo-customizado-utilizando-spring-batch-b72be2243c27)
    - [Criação de arquivos com nomes dinâmicos no Spring Batch](https://giulianabezerra.medium.com/criacao-de-arquivos-com-nomes-dinamicos-no-spring-batch-5e4667dda6e0)
      
  - YouTube: 
    - [Visão geral do Spring Batch - Motivação, Arquitetura e Funcionalidades](https://www.youtube.com/watch?v=xcWwKsnn2lA)
    - [O que você deveria saber sobre Spring Batch?](https://www.youtube.com/watch?v=ACaKKm00Tts)
    - [Tutoriais Spring Batch](https://www.youtube.com/playlist?list=PLiFLtuN04BS07Yw7rnoz1ytWCLu8yteVv)
    - [Implementando Desafio Pag Net Utilizando Spring Batch](https://www.youtube.com/playlist?list=PLiFLtuN04BS1c-JvhKFxYyeD-GVtnwUcx)
    - [Um guia para o Spring Batch 5.0](https://www.youtube.com/watch?v=Jzf9ofPy_xk)
    - [Jobs 90% mais rápidos utilizando Processamento Assincrono](https://www.youtube.com/watch?v=AbQcWO91Bx4&list=PLiFLtuN04BS07Yw7rnoz1ytWCLu8yteVv&index=11)

  - Cursos:
    - [Curso para desenvolvimento de jobs com Spring Batch](https://www.udemy.com/course/curso-para-desenvolvimento-de-jobs-com-spring-batch)
    - [Otimização de desempenho para jobs Spring Batch](https://www.udemy.com/course/otimizacao-de-desempenho-para-jobs-spring-batch)
   
  - Repositórios:
    - [Tests Spring Batch](https://github.com/giuliana-bezerra/TestsSpringBatch)
    - [Composite Reader Job](https://github.com/giuliana-bezerra/LeitorCompostoJob)
    - [Spring Batch Restartt](https://github.com/giuliana-bezerra/sb-restart)
    - [Spring Batch Job Structuring](https://github.com/giuliana-bezerra/sb-job-structuring)
    - [Spring Batch Conditional Flows](https://github.com/giuliana-bezerra/sb-conditional-flows)
    - [Spring Batch Transactions](https://github.com/giuliana-bezerra/sb-transactions)
    - [Spring Batch Distributed Transactions](https://github.com/giuliana-bezerra/sb-distributed-transactions)
    - [Spring Batch Integration Tests](https://github.com/giuliana-bezerra/sb-integration-tests)
    - [Spring Batch Flows](https://github.com/giuliana-bezerra/springbatch-flows)
    - [Spring Batch Commit Interval](https://github.com/giuliana-bezerra/sb-commit-interval)
    - [Spring Batch 5.0](https://github.com/giuliana-bezerra/guide-sb-v5)



