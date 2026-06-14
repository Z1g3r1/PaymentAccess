# PaymentAccess – Сервис приёма платежей через YooKassa API

Демонстрационный проект интеграции платёжного шлюза YooKassa с использованием Spring Boot.
Позволяет создавать платежи, перенаправлять пользователя на оплату и отслеживать статус транзакций.

## 🚀 Ключевые возможности

- **Форма оплаты** – ввод суммы и email, создание платежа
- **Интеграция с YooKassa API** – отправка запросов на создание платежа, обработка ответа
- **Заглушка (mock) платёжного шлюза** – для демонстрации работы без реального магазина
- **Админ-панель** – просмотр всех платежей (доступ только для администратора)
- **Аутентификация администратора** – HTTP Basic Auth или форма входа (in-memory пользователь)
- **Сохранение платежей в базе данных** – PostgreSQL на продакшене, H2 для локальной разработки

## 🛠 Технологический стек

Java 21, Spring Boot, Spring Security, Spring Data JPA, PostgreSQL, H2, Thymeleaf, Bootstrap, Maven, Git, Render, YooKassa API.

## 🔗 Живое демо

- **Веб-сайт:** [https://paymentaccess-xxxx.onrender.com](https://paymentaccess-xxxx.onrender.com)
- **Страница оплаты:** `/payments`
- **Админ-панель:** `/payments/all` (логин: `admin`, пароль: `admin123`)

## 📥 Как запустить локально

1. Клонируй репозиторий: `git clone https://github.com/Z1g3r1/PaymentAccess.git`
2. Убедись, что установлен JDK 21 и Maven.
3. Для работы с H2 (в памяти) запусти:
   ```bash
   mvn spring-boot:run -Dspring-boot.run.profiles=dev
4. Открой в браузере http://localhost:8080.

Для работы с локальной PostgreSQL настрой application-dev.properties (или передай переменные окружения).

📄 Лицензия
MIT
