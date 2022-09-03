## Cria Jar do projeto ##
mvn clean install package

## Executa criação da imagem Docker ##
docker rm fmba-backend-veiculo --force
docker-compose up