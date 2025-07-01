# ChatSpring
Spring Chat 
Spring Boot чат-приложение
Этот проект — backend REST API для чат-приложения. Реализована регистрация, авторизация с использованием JWT, создание чат-комнат, отправка и удаление сообщений между пользователями.

Технологии
- **Java 17**
- **Spring Boot 3**
- **Spring Security (JWT)**
- **PostgreSQL**
- **JPA/Hibernate**
- **MapStruct**
- **Flyway**
- **Docker / Docker Compose**
- **Lombok**
- **Swagger** (работает без авторизации)


## Как запустить проект №1

### Шаг 1: Клонируй проект
через git clone

### Шаг 2: Spring MVN проект собираем 
mvn clean install

### Шаг 3: Включаем Докер с бд
docker-compose up -d



### Шаг 4: проверяем на браузере через сваггер или через постман запросы 


##  API Эндпоинты
## Swagger
http://localhost:8080/swagger-ui.html

### Аутентификация

POST /api/v1/auth/register — регистрация пользователя

POST /api/v1/auth/authenticate — вход и получаем токен

### Чат комнаты
GET /api/chatrooms — получить все инф про чаты которые у нас есть, в него входит пользователи, айди и является ли это групповым чатом 

POST /api/chatrooms — создать чат комнату

DELETE /api/chatrooms/{id} — удалить чат

###  Сообщения
POST /api/messages — отправить сообщение

GET /api/messages/chatroom/{chatRoomId} — все сообщения чата

DELETE /api/messages/{id} — удалить сообщение

### Users
GET  /api/users/all — можно получить все инф про пользователей их айди и username

GET  /api/users/username/{username} — можно получить инф про пользователя через его username

GET  /api/users/id/{id} — можно получить инф про пользователя через его id

