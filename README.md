# 🦅 Aquila-X: Sistema de Gestão Operacional de Drones

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white)
![MongoDB](https://img.shields.io/badge/MongoDB-4EA94B?style=for-the-badge&logo=mongodb&logoColor=white)

## 📌 Sobre o Projeto

O **Aquila-X** é um sistema de gestão operacional e telemetria desenvolvido para a frota de drones autônomos da Securus Dynamics (empresa fictícia do cenário do projeto). O foco da aplicação é garantir o controle seguro, o monitoramento em tempo real e a execução de missões táticas de veículos não tripulados.

Este projeto destaca-se pela sua **arquitetura híbrida de bases de dados**, separando o domínio transacional (MySQL) do grande volume de dados de logs e telemetria (MongoDB), demonstrando a aplicação de padrões reais de engenharia de software e sistemas distribuídos.

## 🚀 Principais Funcionalidades

*   **Autenticação e Segurança:** Controlo de acesso e validação de operadores do sistema.
*   **Gestão de Frota e Missões:** Acompanhamento do status de cada drone (disponível, em manutenção, em missão) e criação de novas operações táticas.
*   **Motor de Regras de Negócio:** Validação rigorosa para a alocação de veículos e verificação de requisitos antes do voo.
*   **Simulação Operacional:** Análise de eventos críticos durante a missão, incluindo simulação de falhas de sistema (pane) e acionamento de protocolos de evasão tática.
*   **Telemetria e Auditoria:** Registo contínuo de dados de voo e eventos operacionais para análise posterior.
*   **Interface Gráfica (Web):** Dashboard para operação e visualização dos dados da frota.

## 🛠️ Tecnologias e Arquitetura

O ecossistema do Aquila-X foi construído sobre uma arquitetura RESTful, utilizando as seguintes tecnologias:

*   **Back-end:** Java com Spring Boot.
*   **APIs:** Desenvolvimento de Controllers RESTful para exposição de endpoints de navegação e operação.
*   **Persistência Relacional (MySQL):** Utilização de JPA/Hibernate para a gestão de dados estruturados e transacionais (operadores, cadastro de drones, missões).
*   **Persistência NoSQL (MongoDB):** Armazenamento de alta performance para a volumetria de logs de telemetria e auditoria de eventos.
*   **Front-end:** HTML, CSS e JavaScript puros para a interface web de operação.
*   **Gestão de Dependências:** Maven.

## ⚙️ Como Executar o Projeto

Como a aplicação utiliza uma arquitetura híbrida, é necessário ter instâncias locais do MySQL e MongoDB rodando na sua máquina.

### Pré-requisitos
*   Java Development Kit (JDK) 17+
*   Maven
*   MySQL Server (local ou via Docker)
*   MongoDB (local ou via Docker)

### Passos para Instalação

1. **Clone este repositório:**
   ```bash
   git clone [https://github.com/DandiReis/Aquila-X-2.0.git](https://github.com/DandiReis/Aquila-X-2.0.git)
