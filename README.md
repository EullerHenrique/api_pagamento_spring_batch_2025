# Conceito:
  ### Spring Batch
  - ....
    
  - Obs
    - Para mais informações teóricas, acesse a branch [Study](https://github.com/EullerHenrique/api_pagamento_spring_batch_2025/tree/study)

# Proposta:
  - [GitHub: Desafio Pag Net](https://github.com/Pagnet/desafio-back-end)

## Regras de Negócio:

- O controle de unicidade das transações é feito por arquivo CNAB, o que significa que o processamento das transações é feito apenas uma vez por arquivo.
- O arquivo CNAB deve ser nomeado com um id ou timestamp, pois ele será passado como parâmetro do job e só pode ser importado uma única vez.
- Caso seja informado um arquivo já importado, deve ser informada uma mensagem de erro ao usuário.
- Caso haja erro no processamento é possível submeter o mesmo arquivo várias vezes para habilitar o restart de onde o processamento parou.
- Se o arquivo for muito grande, é possível utilizar uma estratégia de particionamento no job, melhorando assim a performance.

## Arquitetura:

![](https://github.com/EullerHenrique/api_pagamento_spring_batch_2025/blob/main/imgs/img_12.png)

## Processo:

![](https://github.com/EullerHenrique/api_pagamento_spring_batch_2025/blob/main/imgs/img_13.png)

## Tela:

![](https://github.com/EullerHenrique/api_pagamento_spring_batch_2025/blob/main/imgs/img_14.png)


# Referência:
  - YouTube:
    - [Implementando Desafio Pag Net Utilizando Spring Batch](https://www.youtube.com/playlist?list=PLiFLtuN04BS1c-JvhKFxYyeD-GVtnwUcx)
  
  - Notium:
    - [Documento de Arquitetura](https://giulianabezerra.notion.site/Desafio-Backend-Pagnet-5bbd08f103e04d6d866b028cec6688b5)

