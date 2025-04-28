## Имя

Тестовое задание GrandCapital

## Описание

Стек и тулы:

1. Java 17
2. Spring Boot 3.4.5
3. PostgreSQL
4. Redis
5. Luquibase
6. Docker
7. REST API
8. Maven
9. Lombok
10. JUnit, Mockito
11. Testcontainers

## Запуск

Пререквизиты:

1. JDK >= 17
2. Maven
3. Docker

Шаги запуска
1. Скачать код из репозитория в нужную директорию
```
git clone https://github.com/AleksandrMikhalevich/grandcapital.git
```

2. Запустить Docker daemon

3. Выполнить запуск Docker-контейнеров с БД в директории проекта
```
docker-compose up -d 
```

4. Провести сборку проекта в директории проекта
```
mvn clean install

или через wrapper

.\mvnw.cmd clean install
```

5. Запустить приложение из директории проекта, передав переменные окружения
```
java -jar target/GrandCapitalTestTask-0.0.1-SNAPSHOT.jar --spring.datasource.url=jdbc:postgresql://localhost:5431/grand-capital-task --spring.datasource.username=myuser --spring.datasource.password=secret --spring.data.redis.host=localhost
```


