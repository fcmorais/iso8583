# Processador de Mensagens ISO8583

## Descrição
Este projeto implementa um simples processador de mensagens ISO8583. Utiliza a biblioteca `j8583` para parsear mensagens ISO8583 contidas em arquivos `.dat`, transformando-as em um formato de texto legível e salvando os resultados em arquivos `.txt`.

## Funcionalidades
- Leitura de mensagens ISO8583 de arquivos.
- Parseamento das mensagens ISO8583 usando a biblioteca `j8583`.
- Geração de arquivos de texto com o conteúdo parseado das mensagens.

## Como Executar
Para executar este projeto, siga os passos abaixo:

1. Certifique-se de ter o Java instalado em sua máquina. Este projeto requer Java 8 ou superior.

2. Compile o projeto usando Maven. No diretório raiz do projeto, execute o comando:
   ```
   mvn clean package
   ```
   Isso irá gerar um arquivo JAR na pasta `target`.

3. Execute o arquivo JAR gerado usando o seguinte comando:
   ```
   java -jar target/iso8583-0.0.1-SNAPSHOT-jar-with-dependencies.jar
   ```
   Certifique-se de substituir o nome do arquivo JAR pelo nome correto, caso a versão do seu projeto seja diferente.

## Entradas
O programa espera encontrar os arquivos `financial_transaction_message.dat` e `message_with_hex_bcd.dat` no diretório `src/main/resources`. Esses arquivos devem conter as mensagens ISO8583 para serem parseadas.

## Saídas
Para cada arquivo de entrada, o programa gera um arquivo de saída correspondente com o mesmo nome, precedidos de `translate` e com a extensão `.txt`, contendo o conteúdo parseado da mensagem ISO8583. Os arquivos de saída são salvos no diretório raiz do projeto.

## Erros e Exceções
O programa trata exceções de leitura de arquivo e parseamento de mensagem. Em caso de erros, as mensagens de erro são exibidas no console. Se uma mensagem não puder ser completamente parseada devido a um erro, o programa tentará salvar qualquer parte da mensagem que tenha sido parseada com sucesso até o ponto do erro.

## Notas Adicionais
- A configuração do parseamento das mensagens ISO8583 é definida através de um arquivo de configuração padrão utilizado pela biblioteca `j8583`.
- É importante verificar se os arquivos de entrada estão no formato correto esperado pela biblioteca para evitar erros de parseamento.
