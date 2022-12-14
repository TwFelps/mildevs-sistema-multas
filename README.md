# mildevs - Sistema de Multas
Modele e codifique um sistema que controla multas de trânsito. Nesse sistema estarão presentes as entidades: (mildevs-multas)

# Entidades
Condutor(nroCnh, dataEmissao, orgaoEmissor, pontuacao, Veiculo)

Veiculo (placa, ano, modelo, marca, Condutor, List multas))

Multa(codigoMulta, valor, pontuacao, Veiculo)

# Requisitos
Relacione as entidades conforme especificado no problema, depois crie um DAO para cada uma delas com as funcionalidades básicas de consulta, listagem, inserção e remoção. Valide os relacionamentos criados pelo Hibernate.

- É possível criar um condutor sem um veículo;

- Não é possível criar uma multa para um veículo inexistente;

- Não é possível criar uma um veículo sem um condutor associado;

- Crie a funcionalidade vendaVeiculo, que transfere um veículo de um condutor pro outrol

- É possível listar multas por veículo;

- Crie um menu que tem 3 submenus (Condutor, Veículo, Multa) para controlar a manipulação de cada uma das entidades do seu sistema;

Pesquisa
Pesquise sobre as propriedades fetch, cascade e optional que podem estar presentes nas anotações de relacionamentos. Insira dentro do seu projeto um arquivo txt em que você escreve o seu entendimento sobre elas.

# Autor
@TwFelps
