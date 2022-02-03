#  To-Do App

Api RESTful para aplicativo clássico de tarefas pendentes em que um usuário pode escrever todas as coisas que deseja realizar.

##  Histórias de usuários

-    [x] O usuário pode inserir uma tarefa
-    [x] O usuário pode marcar uma tarefa como `concluded` (concluída)
-    [x] O usuário pode remover uma tarefa
-    [x] O usuário pode editar uma tarefa
-    [x] O usuário pode ver uma lista com todas as tarefas concluídas
-    [x] O usuário pode ver uma lista com todas as tarefas ativas
-    [x] O usuário pode ver a data em que criou a tarefa
-    [x] O usuário pode ver a data em que concluiu a tarefa

Created in: 2021-Jan  
Updated in: 2022-Fev

#### Home:
```shell
curl --location --request GET 'http://localhost:8080'
```

#### Create Task:
```shell
curl --location --request POST 'http://localhost:8080/tasks' \
--header 'Content-Type: application/json' \
--data-raw '{
"description": "my task"
}'
```

#### Read One Task:
```shell
curl --location --request GET 'http://localhost:8080/tasks/1'
```

#### Read All Tasks:
```shell
curl --location --request GET 'http://localhost:8080/tasks'
```

#### Conclude task:
```shell
curl --location --request PUT 'http://localhost:8080/tasks/1/conclude'
```

#### Deconclude Task:
```shell
curl --location --request PUT 'http://localhost:8080/tasks/1/deconclude'
```

#### Update task:
```shell
curl --location --request PUT 'http://localhost:8080/tasks/1' \
--header 'Content-Type: application/json' \
--data-raw '{
"description":"my task 2"
}'
```

#### User Login:
```shell
curl --location --request POST 'http://localhost:8080/login' \
--header 'Content-Type: application/json' \
--data-raw '{
"email": "email@email.com",
"password": "12345678"
}'
```
