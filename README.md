# springboot-eks

## 1. Criar a Aplicação Spring Boot

Primeiro, crie uma aplicação Spring Boot. Isso pode ser feito utilizando o [Spring Initializr](https://start.spring.io/) ou qualquer outra ferramenta de sua preferência. Certifique-se de que sua aplicação está funcionando corretamente antes de seguir para o próximo passo.

## 2. Criar a Imagem Docker

Com a aplicação Spring Boot criada, o próximo passo é criar uma imagem Docker. Certifique-se de que o arquivo `Dockerfile` está presente no diretório do projeto.

### Exemplo de `Dockerfile`:
```Dockerfile
FROM openjdk:17-jdk-slim
ADD target/seu-app.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

### Comando para construir a imagem Docker:
```bash
docker build -t springboot-eks -f Dockerfile .
```

## 3. Fazer Push da Imagem Docker para o Elastic Container Registry (ECR)

Antes de enviar sua imagem Docker para o ECR, você precisará configurar o AWS CLI com suas credenciais:

### Configurar o AWS CLI:
```bash
aws configure
```

Depois, faça login no ECR e envie a imagem:

### Comandos:
```bash
(Get-ECRLoginCommand).Password | docker login --username AWS --password-stdin 851725535551.dkr.ecr.us-east-2.amazonaws.com

docker push 851725535551.dkr.ecr.us-east-2.amazonaws.com/springboot-eks:latest
```

## 4. Deploy da Imagem no EKS

Após enviar a imagem para o ECR, você pode puxá-la do ECR e implantá-la em um cluster EKS.

### Comando para criar o cluster EKS:
```bash
eksctl create cluster --name jt-cluster --version 1.28 --nodes=1 --node-type=t2.small --region us-east-2
```

### Comando para configurar o `kubectl` para usar o cluster EKS:
```bash
aws eks --region us-east-2 update-kubeconfig --name jt-cluster
```

Agora, você pode criar um `Deployment` no Kubernetes que utiliza a imagem que você enviou para o ECR.
