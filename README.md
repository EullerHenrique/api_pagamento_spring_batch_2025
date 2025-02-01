# Api De Pagamento

## Conceito

### Spring Batch

- Spring Batch é um framework da Spring voltado para o processamento de grandes volumes de dados de forma eficiente e escalável. Ele fornece suporte para execução de tarefas em lote, como leitura, processamento e escrita de dados, garantindo robustez e reprocessamento em caso de falhas.

- Principais vantagens:

  - Processamento escalável: Suporte a execução paralela e particionada para lidar com grandes volumes de dados.
  - Gerenciamento de estado: Armazena o progresso dos jobs para permitir retomada em caso de falhas.
  - Transações e controle de falhas: Garante consistência dos dados e reprocessamento seguro.
  - Flexível e configurável: Suporte a diversas fontes de dados (banco de dados, arquivos, filas, etc.).
  - Integração com o ecossistema Spring: Facilita a implementação em aplicações que já utilizam Spring Boot e outros módulos Spring.

- Em resumo, Spring Batch é uma solução poderosa para processamento de dados em lote, oferecendo mecanismos para controle, monitoramento e escalabilidade de tarefas críticas.

- Obs:
  - Para mais informações teóricas, acesse a branch [Study](https://github.com/EullerHenrique/api_pagamento_spring_batch_2025/tree/study)

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


## Referência:
  - YouTube:
    - [Implementando Desafio Pag Net Utilizando Spring Batch](https://www.youtube.com/playlist?list=PLiFLtuN04BS1c-JvhKFxYyeD-GVtnwUcx)
  
  - Notium:
    - [Desafio Pag Net-Documento de Arquitetura](https://giulianabezerra.notion.site/Desafio-Backend-Pagnet-5bbd08f103e04d6d866b028cec6688b5)

