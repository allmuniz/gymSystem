# Sistema de Gestão de Academia

Este projeto é um sistema de gestão para academias, desenvolvido para facilitar o gerenciamento de usuários, planos, matrículas, pagamentos e agenda de aulas. O sistema foi projetado com foco em atender as principais necessidades de uma academia moderna, garantindo eficiência e organização.

## Requisitos Funcionais

### 1. Cadastro de Usuários
- **Cadastro de novos usuários:** O sistema deve permitir o cadastro de diferentes tipos de usuários, incluindo alunos, instrutores e administradores. As informações necessárias para o cadastro incluem nome, CPF, endereço, telefone e e-mail.
- **Atualização e remoção de usuários:** O sistema deve permitir que as informações dos usuários sejam atualizadas ou removidas conforme necessário.

### 2. Gestão de Planos
- **Criação de planos:** O sistema deve permitir a criação de diversos tipos de planos, como mensal, trimestral, semestral e anual, com seus respectivos preços e benefícios.
- **Atualização e remoção de planos:** O sistema deve permitir a atualização das informações dos planos e a remoção dos mesmos.

### 3. Gestão de Matrículas
- **Matrícula em planos:** O sistema deve permitir que os alunos se matriculem em planos específicos.
- **Acompanhamento do status da matrícula:** O sistema deve permitir o acompanhamento do status das matrículas, que podem estar ativas, expiradas ou canceladas.

### 4. Controle de Pagamentos
- **Registro e gerenciamento de pagamentos:** O sistema deve registrar e gerenciar os pagamentos realizados pelos alunos.
- **Notificações de pagamento:** O sistema deve enviar notificações para pagamentos pendentes e próximos do vencimento.

### 5. Agenda de Aulas
- **Criação e gestão de horários de aulas:** O sistema deve permitir a criação e gestão de horários para aulas individuais e coletivas.
- **Inscrição em aulas:** O sistema deve permitir que alunos se inscrevam em aulas específicas.

## Regras de Negócio

### 1. Cadastro de Usuários
- **CPF único:** Cada CPF deve ser único no sistema, sem duplicatas.

### 2. Gestão de Planos
- **Exclusão de planos:** Planos só podem ser excluídos se não houver alunos matriculados neles.
- **Notificação de alterações:** Quaisquer alterações em planos devem notificar os alunos matriculados sobre as mudanças.

### 3. Gestão de Matrículas
- **Renovação automática:** Matrículas devem ser renovadas automaticamente se o aluno tiver um pagamento recorrente configurado.
- **Suspensão por inadimplência:** Alunos com inadimplência superior a 30 dias devem ter suas matrículas suspensas até que a situação seja regularizada.

### 4. Controle de Pagamentos
- **Acesso às dependências:** Alunos só podem acessar as dependências da academia se estiverem com os pagamentos em dia.
- **Registro de pagamentos:** Pagamentos devem ser registrados no sistema em até 24 horas após a confirmação.

### 5. Agenda de Aulas
- **Criação de aulas:** Aulas só podem ser criadas por instrutores ou administradores.
- **Inscrição em aulas:** Alunos só podem se inscrever em aulas se estiverem com matrícula ativa e pagamentos em dia.
- **Limite de participantes:** As aulas devem ter um limite máximo de participantes, conforme definido pela academia.
