INSERT INTO users (id, username, email, password, provider, isblock) VALUES ('14e267e9-7725-450d-bb61-79a5f3fb78d1',
 'admin', 'admin@email', '$2a$10$uGPBQ3N73C7S6314sG6lQuTdbft05D26o0MFDO1AzX/MIUIF5tDQG', 'local', '0');
INSERT INTO users (id, username, email, password, provider, isblock) VALUES ('14e267e9-7725-450d-bb61-79a5f3fb78d2',
 'trainer', 'trainer@email', '$2a$10$uGPBQ3N73C7S6314sG6lQuTdbft05D26o0MFDO1AzX/MIUIF5tDQG', 'local', '0');
INSERT INTO users (id, username, email, password, provider, isblock) VALUES ('14e267e9-7725-450d-bb61-79a5f3fb78d3',
 'user', 'user@email', '$2a$10$uGPBQ3N73C7S6314sG6lQuTdbft05D26o0MFDO1AzX/MIUIF5tDQG', 'local', '0');

INSERT INTO roles (id, name) VALUES (4, 'ROLE_ADMIN');
INSERT INTO roles (id, name) VALUES (5, 'ROLE_MENTOR');
INSERT INTO roles (id, name) VALUES (6, 'ROLE_USER');

INSERT INTO user_role (user_id, role_id) VALUES ('14e267e9-7725-450d-bb61-79a5f3fb78d1', 4);
INSERT INTO user_role (user_id, role_id) VALUES ('14e267e9-7725-450d-bb61-79a5f3fb78d2', 5);
INSERT INTO user_role (user_id, role_id) VALUES ('14e267e9-7725-450d-bb61-79a5f3fb78d3', 6);

INSERT INTO carts (user_id) VALUES ('14e267e9-7725-450d-bb61-79a5f3fb78d1');
INSERT INTO carts (user_id) VALUES ('14e267e9-7725-450d-bb61-79a5f3fb78d2');
INSERT INTO carts (user_id) VALUES ('14e267e9-7725-450d-bb61-79a5f3fb78d3');

INSERT INTO trainings (id, description, name, price, user_id, training_rate, img_url) VALUES ('14e267e9-7725-450d-bb61-79a5f3fb78d5',
'Kotlin - это будущее Android разработки. Узнайте все, что нужно для работы с этим выразительным и лаконичным языком',
'Android разработка на Kotlin - с нуля до продвинутого уровня', 1000, '14e267e9-7725-450d-bb61-79a5f3fb78d2', 5, './assets/1.png');
INSERT INTO trainings (id, description, name, price, user_id, training_rate, img_url) VALUES ('14e267e9-7725-450d-bb61-79a5f3fb78d6',
'Python это язык с которого стоит начинать изучать программирование. Благодаря своей простоте и элегантности, Python позволяет
новичкам не вникать во множество сложных программных понятий и конструкций, присущихдругим языкам.',
'Полное руководство по Python 3: от новичка до специалиста', 2000, '14e267e9-7725-450d-bb61-79a5f3fb78d2', 3, './assets/2.jpg');
INSERT INTO trainings (id, description, name, price, user_id, training_rate, img_url) VALUES ('14e267e9-7725-450d-bb61-79a5f3fb78d7',
'Освой все, что необходимо для создания web-сайтов и начни зарабатывать на этом!', 'WEB-разработчик 2020', 3000,
'14e267e9-7725-450d-bb61-79a5f3fb78d2', 4, './assets/3.jpg');
INSERT INTO trainings (id, description, name, price, user_id, training_rate, img_url) VALUES ('14e267e9-7725-450d-bb61-79a5f3fb78d8',
'Полное руководство для разработки крутейших динамических приложений. От глубокой теории до практики на Angular',
'Angular 9. Теория и Практика 2020', 2500, '14e267e9-7725-450d-bb61-79a5f3fb78d2', 4, './assets/4.png');
INSERT INTO trainings (id, description, name, price, user_id, training_rate, img_url) VALUES ('18e267e9-7725-450d-bb61-79a5f3fb78d8',
'Все что нужно знать для начала работы проектировщиком интерфейсов или юзабилити-специалистом',
'UX c нуля: как стать проектировщиком интерфейсов', 25000, '14e267e9-7725-450d-bb61-79a5f3fb78d2', 5, './assets/5.png');
INSERT INTO trainings (id, description, name, price, user_id, training_rate, img_url) VALUES ('19e267e9-7725-450d-bb61-79a5f3fb78d8',
'Полный гайд по созданию сайтов и приложений на серверном JavaScript, включая базы данных и создание API. ',
'Node JS. Практический курс', 1500, '14e267e9-7725-450d-bb61-79a5f3fb78d2', 4, './assets/6.jpg');
INSERT INTO trainings (id, description, name, price, user_id, training_rate, img_url) VALUES ('20e267e9-7725-450d-bb61-79a5f3fb78d8',
'Этот курс подойдет для тех людей, кто не знает про GIT ничего и хотел бы изучить его "с нуля". Однако, курс также будет ' ||
'полезен тем людям, кто ежедневно использует GIT в своей работе, но хотел бы обрести большую уверенность в управлении этим ' ||
'нструментом.', 'Git: Полный курс для начинающих и не только ', 1000, '14e267e9-7725-450d-bb61-79a5f3fb78d2', 2, './assets/7.png');
INSERT INTO trainings (id, description, name, price, user_id, training_rate, img_url) VALUES ('21e267e9-7725-450d-bb61-79a5f3fb78d8',
'Начальный теоретический курс для яхтсменов.', 'Обучение яхтингу - от новичка до матроса!', 10500,
'14e267e9-7725-450d-bb61-79a5f3fb78d2', 1, './assets/8.png');
INSERT INTO trainings (id, description, name, price, user_id, training_rate, img_url) VALUES ('22e267e9-7725-450d-bb61-79a5f3fb78d8',
'Получаем учебную визу D (процесс, этапы, рекомендации) для переезда в Испанию.',
'Переезд в Испанию по учебной визе: пошаговая инструкция', 7499, '14e267e9-7725-450d-bb61-79a5f3fb78d2', 3, './assets/9.png');
INSERT INTO trainings (id, description, name, price, user_id, training_rate, img_url) VALUES ('23e267e9-7725-450d-bb61-79a5f3fb78d8',
'Быстрые и Легкие шаги для повышения уровня в программе 3D Studio Max на конкретных примерах.',
'3DS Max - Интерьер с нуля 2.0 ', 6650, '14e267e9-7725-450d-bb61-79a5f3fb78d2', 5, './assets/10.jpg');
INSERT INTO trainings (id, description, name, price, user_id, training_rate, img_url) VALUES ('24e267e9-7725-450d-bb61-79a5f3fb78d8',
'Исследуй своих конкурентов, получи необходимую информацию об их продукте и бизнесе и сделай правильные выводы.',
'Эксперт по конкурентной разведке', 7564, '14e267e9-7725-450d-bb61-79a5f3fb78d2', 1, './assets/11.png');
INSERT INTO trainings (id, description, name, price, user_id, training_rate, img_url) VALUES ('25e267e9-7725-450d-bb61-79a5f3fb78d8',
'В этом курсе изложены основы языка SQL для реляционных баз данных. Язык SQL кажется простым - и это В этом курсе изложены ' ||
'основы языка SQL для реляционных баз данных. Язык SQL кажется простым - и это действительно так.',
'Начальный курс SQL', 1355, '14e267e9-7725-450d-bb61-79a5f3fb78d2', 2, './assets/12.jpg');
INSERT INTO trainings (id, description, name, price, user_id, training_rate, img_url) VALUES ('26e267e9-7725-450d-bb61-79a5f3fb78d8',
'Изучите основы веб дизайна и разработку реальных современных веб сайтов при помощи HTML5, CSS3 и jQuery с нуля!',
'Создание современных адаптивных веб сайтов. HTML5, CSS3 ', 8745, '14e267e9-7725-450d-bb61-79a5f3fb78d2', 3, './assets/13.jpg');
INSERT INTO trainings (id, description, name, price, user_id, training_rate, img_url) VALUES ('27e267e9-7725-450d-bb61-79a5f3fb78d8',
'От Максима Дорофеева автора книг "Джедайские техники" и "Путь джедая"',
'Джедайские техники (а не какой-то там тайм-менеджмент)', 3684, '14e267e9-7725-450d-bb61-79a5f3fb78d2', 4, './assets/14.png');
INSERT INTO trainings (id, description, name, price, user_id, training_rate, img_url) VALUES ('28e267e9-7725-450d-bb61-79a5f3fb78d8',
'Как приручить свои эмоции.', 'Эмоциональный интеллект. ', 8641, '14e267e9-7725-450d-bb61-79a5f3fb78d2', 0, './assets/15.jpg');
INSERT INTO trainings (id, description, name, price, user_id, training_rate, img_url) VALUES ('29e267e9-7725-450d-bb61-79a5f3fb78d8',
'Изучите основы Java.', 'Java для начинающих.', 9876, '14e267e9-7725-450d-bb61-79a5f3fb78d2', 2, './assets/16.jpg');
INSERT INTO trainings (id, description, name, price, user_id, training_rate, img_url) VALUES ('30e267e9-7725-450d-bb61-79a5f3fb78d8',
'Изучите все этапы этичного хакинга и взлом Windows систем с помощью Metasploit',
'Этичный хакинг с Metasploit для начинающих ', 5588, '14e267e9-7725-450d-bb61-79a5f3fb78d2', 1, './assets/17.jpg');
INSERT INTO trainings (id, description, name, price, user_id, training_rate, img_url) VALUES ('31e267e9-7725-450d-bb61-79a5f3fb78d8',
'Тестирование баз данных на проникновение. SQL инъекции и SQL map',
'SQL инъекции и тестирование баз данных для начинающих', 4321, '14e267e9-7725-450d-bb61-79a5f3fb78d2', 3, './assets/18.png');
INSERT INTO trainings (id, description, name, price, user_id, training_rate, img_url) VALUES ('32e267e9-7725-450d-bb61-79a5f3fb78d8',
'Постановка голоса за 15 минут в день!', 'Возрождение природного голоса', 7564, '14e267e9-7725-450d-bb61-79a5f3fb78d2', 5, './assets/19.png');
INSERT INTO trainings (id, description, name, price, user_id, training_rate, img_url) VALUES ('33e267e9-7725-450d-bb61-79a5f3fb78d8',
'Лучшие вокальные приемы - Микст, Бэлтинг, Тванг, Фальцет.', 'Высокие ноты легко', 3548, '14e267e9-7725-450d-bb61-79a5f3fb78d2', 2, './assets/20.png');

INSERT INTO rating (id, rate, review, user_id, training_id) VALUES ('14e267e9-7725-450d-bb61-80a5f3fb78d5', 5, 'Disgusting training',
'14e267e9-7725-450d-bb61-79a5f3fb78d2', '14e267e9-7725-450d-bb61-79a5f3fb78d5');
INSERT INTO rating (id, rate, review, user_id, training_id) VALUES ('14e267e9-7725-450d-bb61-80a5f3fb78d6', 3, 'Bad training',
'14e267e9-7725-450d-bb61-79a5f3fb78d2', '14e267e9-7725-450d-bb61-79a5f3fb78d6');
INSERT INTO rating (id, rate, review, user_id, training_id) VALUES ('14e267e9-7725-450d-bb61-80a5f3fb78d7', 4, 'Good training',
'14e267e9-7725-450d-bb61-79a5f3fb78d2', '14e267e9-7725-450d-bb61-79a5f3fb78d7');
INSERT INTO rating (id, rate, review, user_id, training_id) VALUES ('14e267e9-7725-450d-bb61-80a5f3fb78d8', 2, 'Awesome training',
'14e267e9-7725-450d-bb61-79a5f3fb78d2', '14e267e9-7725-450d-bb61-79a5f3fb78d8');

INSERT INTO trainings_rating (training_id, rating_id) VALUES ('14e267e9-7725-450d-bb61-79a5f3fb78d5', '14e267e9-7725-450d-bb61-80a5f3fb78d5');
INSERT INTO trainings_rating (training_id, rating_id) VALUES ('14e267e9-7725-450d-bb61-79a5f3fb78d6', '14e267e9-7725-450d-bb61-80a5f3fb78d6');
INSERT INTO trainings_rating (training_id, rating_id) VALUES ('14e267e9-7725-450d-bb61-79a5f3fb78d7', '14e267e9-7725-450d-bb61-80a5f3fb78d7');
INSERT INTO trainings_rating (training_id, rating_id) VALUES ('14e267e9-7725-450d-bb61-79a5f3fb78d8', '14e267e9-7725-450d-bb61-80a5f3fb78d8');

INSERT INTO categories (id, name, description) VALUES ('33e267e9-7725-450d-bb61-79a5f3fb7861', 'Дизайн', 'description category');
INSERT INTO categories (id, name, description) VALUES ('33e267e9-7725-450d-bb61-79a5f3fb7863', 'Путешествия', 'description category');
INSERT INTO categories (id, name, description) VALUES ('33e267e9-7725-450d-bb61-79a5f3fb7864', 'Музыка', 'description category');
INSERT INTO categories (id, name, description) VALUES ('33e267e9-7725-450d-bb61-79a5f3fb7865', 'Разработка', 'description category');
INSERT INTO categories (id, name, description) VALUES ('33e267e9-7725-450d-bb61-79a5f3fb7866', 'Бизнес', 'description category');
INSERT INTO categories (id, name, description) VALUES ('33e267e9-7725-450d-bb61-79a5f3fb7867', 'Саморазвитие', 'description category');

INSERT INTO category_training (category_id, training_id) VALUES ('33e267e9-7725-450d-bb61-79a5f3fb7864', '33e267e9-7725-450d-bb61-79a5f3fb78d8');
INSERT INTO category_training (category_id, training_id) VALUES ('33e267e9-7725-450d-bb61-79a5f3fb7867', '33e267e9-7725-450d-bb61-79a5f3fb78d8');
INSERT INTO category_training (category_id, training_id) VALUES ('33e267e9-7725-450d-bb61-79a5f3fb7864', '32e267e9-7725-450d-bb61-79a5f3fb78d8');
INSERT INTO category_training (category_id, training_id) VALUES ('33e267e9-7725-450d-bb61-79a5f3fb7867', '32e267e9-7725-450d-bb61-79a5f3fb78d8');
INSERT INTO category_training (category_id, training_id) VALUES ('33e267e9-7725-450d-bb61-79a5f3fb7865', '31e267e9-7725-450d-bb61-79a5f3fb78d8');
INSERT INTO category_training (category_id, training_id) VALUES ('33e267e9-7725-450d-bb61-79a5f3fb7865', '30e267e9-7725-450d-bb61-79a5f3fb78d8');
INSERT INTO category_training (category_id, training_id) VALUES ('33e267e9-7725-450d-bb61-79a5f3fb7865', '29e267e9-7725-450d-bb61-79a5f3fb78d8');
INSERT INTO category_training (category_id, training_id) VALUES ('33e267e9-7725-450d-bb61-79a5f3fb7867', '28e267e9-7725-450d-bb61-79a5f3fb78d8');
INSERT INTO category_training (category_id, training_id) VALUES ('33e267e9-7725-450d-bb61-79a5f3fb7867', '27e267e9-7725-450d-bb61-79a5f3fb78d8');
INSERT INTO category_training (category_id, training_id) VALUES ('33e267e9-7725-450d-bb61-79a5f3fb7861', '26e267e9-7725-450d-bb61-79a5f3fb78d8');
INSERT INTO category_training (category_id, training_id) VALUES ('33e267e9-7725-450d-bb61-79a5f3fb7865', '26e267e9-7725-450d-bb61-79a5f3fb78d8');
INSERT INTO category_training (category_id, training_id) VALUES ('33e267e9-7725-450d-bb61-79a5f3fb7865', '25e267e9-7725-450d-bb61-79a5f3fb78d8');
INSERT INTO category_training (category_id, training_id) VALUES ('33e267e9-7725-450d-bb61-79a5f3fb7866', '24e267e9-7725-450d-bb61-79a5f3fb78d8');
INSERT INTO category_training (category_id, training_id) VALUES ('33e267e9-7725-450d-bb61-79a5f3fb7861', '23e267e9-7725-450d-bb61-79a5f3fb78d8');
INSERT INTO category_training (category_id, training_id) VALUES ('33e267e9-7725-450d-bb61-79a5f3fb7863', '22e267e9-7725-450d-bb61-79a5f3fb78d8');
INSERT INTO category_training (category_id, training_id) VALUES ('33e267e9-7725-450d-bb61-79a5f3fb7863', '21e267e9-7725-450d-bb61-79a5f3fb78d8');
INSERT INTO category_training (category_id, training_id) VALUES ('33e267e9-7725-450d-bb61-79a5f3fb7865', '20e267e9-7725-450d-bb61-79a5f3fb78d8');
INSERT INTO category_training (category_id, training_id) VALUES ('33e267e9-7725-450d-bb61-79a5f3fb7861', '19e267e9-7725-450d-bb61-79a5f3fb78d8');
INSERT INTO category_training (category_id, training_id) VALUES ('33e267e9-7725-450d-bb61-79a5f3fb7865', '19e267e9-7725-450d-bb61-79a5f3fb78d8');
INSERT INTO category_training (category_id, training_id) VALUES ('33e267e9-7725-450d-bb61-79a5f3fb7861', '18e267e9-7725-450d-bb61-79a5f3fb78d8');
INSERT INTO category_training (category_id, training_id) VALUES ('33e267e9-7725-450d-bb61-79a5f3fb7865', '18e267e9-7725-450d-bb61-79a5f3fb78d8');
INSERT INTO category_training (category_id, training_id) VALUES ('33e267e9-7725-450d-bb61-79a5f3fb7865', '14e267e9-7725-450d-bb61-79a5f3fb78d5');
INSERT INTO category_training (category_id, training_id) VALUES ('33e267e9-7725-450d-bb61-79a5f3fb7865', '14e267e9-7725-450d-bb61-79a5f3fb78d6');
INSERT INTO category_training (category_id, training_id) VALUES ('33e267e9-7725-450d-bb61-79a5f3fb7865', '14e267e9-7725-450d-bb61-79a5f3fb78d7');
INSERT INTO category_training (category_id, training_id) VALUES ('33e267e9-7725-450d-bb61-79a5f3fb7865', '14e267e9-7725-450d-bb61-79a5f3fb78d8');

INSERT INTO messages (id, text, sender_id) VALUES ('01e267e9-7725-450d-bb61-79a5f3fb7861', 'text 1',
 '14e267e9-7725-450d-bb61-79a5f3fb78d1');
INSERT INTO messages (id, text, sender_id) VALUES ('02e267e9-7725-450d-bb61-79a5f3fb7862', 'text 2',
 '14e267e9-7725-450d-bb61-79a5f3fb78d1');
INSERT INTO messages (id, text, sender_id) VALUES ('03e267e9-7725-450d-bb61-79a5f3fb7863', 'text 3',
 '14e267e9-7725-450d-bb61-79a5f3fb78d2');

INSERT INTO files (id, name, upload_date, content_type, training_id, type, path, description, content_name) VALUES (
'77e307e9-7725-450d-bb61-79a5f3fb7811', 'name file', '01.01.2020',
 'ARTICLE', '18e267e9-7725-450d-bb61-79a5f3fb78d8', 'video', 'path 1', 'Очень интересный материал.', 'Основы');
INSERT INTO files (id, name, upload_date, content_type, training_id, type, path, description, content_name) VALUES (
'77e317e9-7725-450d-bb61-79a5f3fb7812', 'name file', '01.01.2020',
 'ARTICLE', '18e267e9-7725-450d-bb61-79a5f3fb78d8', 'video', 'path 1', 'Очень интересный материал.', 'Практика');
INSERT INTO files (id, name, upload_date, content_type, training_id, type, path, description, content_name) VALUES (
'77e327e9-7725-450d-bb61-79a5f3fb7813', 'name file', '01.01.2020',
 'ARTICLE', '18e267e9-7725-450d-bb61-79a5f3fb78d8', 'video', 'path 1', 'Очень интересный материал.', 'Продвинутый уровень');

INSERT INTO files (id, name, upload_date, content_type, training_id, type, path, description, content_name) VALUES (
'77e327e9-7725-450d-bb61-79a5f3fb7811', 'name file', '01.01.2020',
 'ARTICLE', '19e267e9-7725-450d-bb61-79a5f3fb78d8', 'video', 'path 1', 'Очень интересный материал.', 'Основы');
INSERT INTO files (id, name, upload_date, content_type, training_id, type, path, description, content_name) VALUES (
'77e337e9-7725-450d-bb61-79a5f3fb7812', 'name file', '01.01.2020',
 'ARTICLE', '19e267e9-7725-450d-bb61-79a5f3fb78d8', 'video', 'path 1', 'Очень интересный материал.', 'Практика');
INSERT INTO files (id, name, upload_date, content_type, training_id, type, path, description, content_name) VALUES (
'77e347e9-7725-450d-bb61-79a5f3fb7813', 'name file', '01.01.2020',
 'ARTICLE', '19e267e9-7725-450d-bb61-79a5f3fb78d8', 'video', 'path 1', 'Очень интересный материал.', 'Продвинутый уровень');

INSERT INTO files (id, name, upload_date, content_type, training_id, type, path, description, content_name) VALUES (
'77e357e9-7725-450d-bb61-79a5f3fb7811', 'name file', '01.01.2020',
 'ARTICLE', '20e267e9-7725-450d-bb61-79a5f3fb78d8', 'video', 'path 1', 'Очень интересный материал.', 'Основы');
INSERT INTO files (id, name, upload_date, content_type, training_id, type, path, description, content_name) VALUES (
'77e367e9-7725-450d-bb61-79a5f3fb7812', 'name file', '01.01.2020',
 'ARTICLE', '20e267e9-7725-450d-bb61-79a5f3fb78d8', 'video', 'path 1', 'Очень интересный материал.', 'Практика');
INSERT INTO files (id, name, upload_date, content_type, training_id, type, path, description, content_name) VALUES (
'77e377e9-7725-450d-bb61-79a5f3fb7813', 'name file', '01.01.2020',
 'ARTICLE', '20e267e9-7725-450d-bb61-79a5f3fb78d8', 'video', 'path 1', 'Очень интересный материал.', 'Продвинутый уровень');

INSERT INTO files (id, name, upload_date, content_type, training_id, type, path, description, content_name) VALUES (
'77e387e9-7725-450d-bb61-79a5f3fb7811', 'name file', '01.01.2020',
 'ARTICLE', '21e267e9-7725-450d-bb61-79a5f3fb78d8', 'video', 'path 1', 'Очень интересный материал.', 'Основы');
INSERT INTO files (id, name, upload_date, content_type, training_id, type, path, description, content_name) VALUES (
'77e397e9-7725-450d-bb61-79a5f3fb7812', 'name file', '01.01.2020',
 'ARTICLE', '21e267e9-7725-450d-bb61-79a5f3fb78d8', 'video', 'path 1', 'Очень интересный материал.', 'Практика');
INSERT INTO files (id, name, upload_date, content_type, training_id, type, path, description, content_name) VALUES (
'77e407e9-7725-450d-bb61-79a5f3fb7813', 'name file', '01.01.2020',
 'ARTICLE', '21e267e9-7725-450d-bb61-79a5f3fb78d8', 'video', 'path 1', 'Очень интересный материал.', 'Продвинутый уровень');

INSERT INTO files (id, name, upload_date, content_type, training_id, type, path, description, content_name) VALUES (
'77e417e9-7725-450d-bb61-79a5f3fb7811', 'name file', '01.01.2020',
 'ARTICLE', '22e267e9-7725-450d-bb61-79a5f3fb78d8', 'video', 'path 1', 'Очень интересный материал.', 'Основы');
INSERT INTO files (id, name, upload_date, content_type, training_id, type, path, description, content_name) VALUES (
'77e427e9-7725-450d-bb61-79a5f3fb7812', 'name file', '01.01.2020',
 'ARTICLE', '22e267e9-7725-450d-bb61-79a5f3fb78d8', 'video', 'path 1', 'Очень интересный материал.', 'Практика');
INSERT INTO files (id, name, upload_date, content_type, training_id, type, path, description, content_name) VALUES (
'77e437e9-7725-450d-bb61-79a5f3fb7813', 'name file', '01.01.2020',
 'ARTICLE', '22e267e9-7725-450d-bb61-79a5f3fb78d8', 'video', 'path 1', 'Очень интересный материал.', 'Продвинутый уровень');

INSERT INTO files (id, name, upload_date, content_type, training_id, type, path, description, content_name) VALUES (
'77e447e9-7725-450d-bb61-79a5f3fb7811', 'name file', '01.01.2020',
 'ARTICLE', '23e267e9-7725-450d-bb61-79a5f3fb78d8', 'video', 'path 1', 'Очень интересный материал.', 'Основы');
INSERT INTO files (id, name, upload_date, content_type, training_id, type, path, description, content_name) VALUES (
'77e457e9-7725-450d-bb61-79a5f3fb7812', 'name file', '01.01.2020',
 'ARTICLE', '23e267e9-7725-450d-bb61-79a5f3fb78d8', 'video', 'path 1', 'Очень интересный материал.', 'Практика');
INSERT INTO files (id, name, upload_date, content_type, training_id, type, path, description, content_name) VALUES (
'77e467e9-7725-450d-bb61-79a5f3fb7813', 'name file', '01.01.2020',
 'ARTICLE', '23e267e9-7725-450d-bb61-79a5f3fb78d8', 'video', 'path 1', 'Очень интересный материал.', 'Продвинутый уровень');

INSERT INTO files (id, name, upload_date, content_type, training_id, type, path, description, content_name) VALUES (
'77e477e9-7725-450d-bb61-79a5f3fb7811', 'name file', '01.01.2020',
 'ARTICLE', '24e267e9-7725-450d-bb61-79a5f3fb78d8', 'video', 'path 1', 'Очень интересный материал.', 'Основы');
INSERT INTO files (id, name, upload_date, content_type, training_id, type, path, description, content_name) VALUES (
'77e487e9-7725-450d-bb61-79a5f3fb7812', 'name file', '01.01.2020',
 'ARTICLE', '24e267e9-7725-450d-bb61-79a5f3fb78d8', 'video', 'path 1', 'Очень интересный материал.', 'Практика');
INSERT INTO files (id, name, upload_date, content_type, training_id, type, path, description, content_name) VALUES (
'77e497e9-7725-450d-bb61-79a5f3fb7813', 'name file', '01.01.2020',
 'ARTICLE', '24e267e9-7725-450d-bb61-79a5f3fb78d8', 'video', 'path 1', 'Очень интересный материал.', 'Продвинутый уровень');

INSERT INTO files (id, name, upload_date, content_type, training_id, type, path, description, content_name) VALUES (
'77e507e9-7725-450d-bb61-79a5f3fb7811', 'name file', '01.01.2020',
 'ARTICLE', '25e267e9-7725-450d-bb61-79a5f3fb78d8', 'video', 'path 1', 'Очень интересный материал.', 'Основы');
INSERT INTO files (id, name, upload_date, content_type, training_id, type, path, description, content_name) VALUES (
'77e517e9-7725-450d-bb61-79a5f3fb7812', 'name file', '01.01.2020',
 'ARTICLE', '25e267e9-7725-450d-bb61-79a5f3fb78d8', 'video', 'path 1', 'Очень интересный материал.', 'Практика');
INSERT INTO files (id, name, upload_date, content_type, training_id, type, path, description, content_name) VALUES (
'77e527e9-7725-450d-bb61-79a5f3fb7813', 'name file', '01.01.2020',
 'ARTICLE', '25e267e9-7725-450d-bb61-79a5f3fb78d8', 'video', 'path 1', 'Очень интересный материал.', 'Продвинутый уровень');

INSERT INTO files (id, name, upload_date, content_type, training_id, type, path, description, content_name) VALUES (
'77e537e9-7725-450d-bb61-79a5f3fb7811', 'name file', '01.01.2020',
 'ARTICLE', '26e267e9-7725-450d-bb61-79a5f3fb78d8', 'video', 'path 1', 'Очень интересный материал.', 'Основы');
INSERT INTO files (id, name, upload_date, content_type, training_id, type, path, description, content_name) VALUES (
'77e547e9-7725-450d-bb61-79a5f3fb7812', 'name file', '01.01.2020',
 'ARTICLE', '26e267e9-7725-450d-bb61-79a5f3fb78d8', 'video', 'path 1', 'Очень интересный материал.', 'Практика');
INSERT INTO files (id, name, upload_date, content_type, training_id, type, path, description, content_name) VALUES (
'77e557e9-7725-450d-bb61-79a5f3fb7813', 'name file', '01.01.2020',
 'ARTICLE', '26e267e9-7725-450d-bb61-79a5f3fb78d8', 'video', 'path 1', 'Очень интересный материал.', 'Продвинутый уровень');

INSERT INTO files (id, name, upload_date, content_type, training_id, type, path, description, content_name) VALUES (
'77e567e9-7725-450d-bb61-79a5f3fb7811', 'name file', '01.01.2020',
 'ARTICLE', '27e267e9-7725-450d-bb61-79a5f3fb78d8', 'video', 'path 1', 'Очень интересный материал.', 'Основы');
INSERT INTO files (id, name, upload_date, content_type, training_id, type, path, description, content_name) VALUES (
'77e577e9-7725-450d-bb61-79a5f3fb7812', 'name file', '01.01.2020',
 'ARTICLE', '27e267e9-7725-450d-bb61-79a5f3fb78d8', 'video', 'path 1', 'Очень интересный материал.', 'Практика');
INSERT INTO files (id, name, upload_date, content_type, training_id, type, path, description, content_name) VALUES (
'77e587e9-7725-450d-bb61-79a5f3fb7813', 'name file', '01.01.2020',
 'ARTICLE', '27e267e9-7725-450d-bb61-79a5f3fb78d8', 'video', 'path 1', 'Очень интересный материал.', 'Продвинутый уровень');

INSERT INTO files (id, name, upload_date, content_type, training_id, type, path, description, content_name) VALUES (
'77e597e9-7725-450d-bb61-79a5f3fb7811', 'name file', '01.01.2020',
 'ARTICLE', '28e267e9-7725-450d-bb61-79a5f3fb78d8', 'video', 'path 1', 'Очень интересный материал.', 'Основы');
INSERT INTO files (id, name, upload_date, content_type, training_id, type, path, description, content_name) VALUES (
'77e607e9-7725-450d-bb61-79a5f3fb7812', 'name file', '01.01.2020',
 'ARTICLE', '28e267e9-7725-450d-bb61-79a5f3fb78d8', 'video', 'path 1', 'Очень интересный материал.', 'Практика');
INSERT INTO files (id, name, upload_date, content_type, training_id, type, path, description, content_name) VALUES (
'77e617e9-7725-450d-bb61-79a5f3fb7813', 'name file', '01.01.2020',
 'ARTICLE', '28e267e9-7725-450d-bb61-79a5f3fb78d8', 'video', 'path 1', 'Очень интересный материал.', 'Продвинутый уровень');

INSERT INTO files (id, name, upload_date, content_type, training_id, type, path, description, content_name) VALUES (
'77e627e9-7725-450d-bb61-79a5f3fb7811', 'name file', '01.01.2020',
 'ARTICLE', '29e267e9-7725-450d-bb61-79a5f3fb78d8', 'video', 'path 1', 'Очень интересный материал.', 'Основы');
INSERT INTO files (id, name, upload_date, content_type, training_id, type, path, description, content_name) VALUES (
'77e637e9-7725-450d-bb61-79a5f3fb7812', 'name file', '01.01.2020',
 'ARTICLE', '29e267e9-7725-450d-bb61-79a5f3fb78d8', 'video', 'path 1', 'Очень интересный материал.', 'Практика');
INSERT INTO files (id, name, upload_date, content_type, training_id, type, path, description, content_name) VALUES (
'77e647e9-7725-450d-bb61-79a5f3fb7813', 'name file', '01.01.2020',
 'ARTICLE', '29e267e9-7725-450d-bb61-79a5f3fb78d8', 'video', 'path 1', 'Очень интересный материал.', 'Продвинутый уровень');

INSERT INTO files (id, name, upload_date, content_type, training_id, type, path, description, content_name) VALUES (
'77e657e9-7725-450d-bb61-79a5f3fb7811', 'name file', '01.01.2020',
 'ARTICLE', '30e267e9-7725-450d-bb61-79a5f3fb78d8', 'video', 'path 1', 'Очень интересный материал.', 'Основы');
INSERT INTO files (id, name, upload_date, content_type, training_id, type, path, description, content_name) VALUES (
'77e667e9-7725-450d-bb61-79a5f3fb7812', 'name file', '01.01.2020',
 'ARTICLE', '30e267e9-7725-450d-bb61-79a5f3fb78d8', 'video', 'path 1', 'Очень интересный материал.', 'Практика');
INSERT INTO files (id, name, upload_date, content_type, training_id, type, path, description, content_name) VALUES (
'77e677e9-7725-450d-bb61-79a5f3fb7813', 'name file', '01.01.2020',
 'ARTICLE', '30e267e9-7725-450d-bb61-79a5f3fb78d8', 'video', 'path 1', 'Очень интересный материал.', 'Продвинутый уровень');

INSERT INTO files (id, name, upload_date, content_type, training_id, type, path, description, content_name) VALUES (
'77e687e9-7725-450d-bb61-79a5f3fb7811', 'name file', '01.01.2020',
 'ARTICLE', '31e267e9-7725-450d-bb61-79a5f3fb78d8', 'video', 'path 1', 'Очень интересный материал.', 'Основы');
INSERT INTO files (id, name, upload_date, content_type, training_id, type, path, description, content_name) VALUES (
'77e697e9-7725-450d-bb61-79a5f3fb7812', 'name file', '01.01.2020',
 'ARTICLE', '31e267e9-7725-450d-bb61-79a5f3fb78d8', 'video', 'path 1', 'Очень интересный материал.', 'Практика');
INSERT INTO files (id, name, upload_date, content_type, training_id, type, path, description, content_name) VALUES (
'77e707e9-7725-450d-bb61-79a5f3fb7813', 'name file', '01.01.2020',
 'ARTICLE', '31e267e9-7725-450d-bb61-79a5f3fb78d8', 'video', 'path 1', 'Очень интересный материал.', 'Продвинутый уровень');

INSERT INTO files (id, name, upload_date, content_type, training_id, type, path, description, content_name) VALUES (
'77e717e9-7725-450d-bb61-79a5f3fb7811', 'name file', '01.01.2020',
 'ARTICLE', '32e267e9-7725-450d-bb61-79a5f3fb78d8', 'video', 'path 1', 'Очень интересный материал.', 'Основы');
INSERT INTO files (id, name, upload_date, content_type, training_id, type, path, description, content_name) VALUES (
'77e727e9-7725-450d-bb61-79a5f3fb7812', 'name file', '01.01.2020',
 'ARTICLE', '32e267e9-7725-450d-bb61-79a5f3fb78d8', 'video', 'path 1', 'Очень интересный материал.', 'Практика');
INSERT INTO files (id, name, upload_date, content_type, training_id, type, path, description, content_name) VALUES (
'77e737e9-7725-450d-bb61-79a5f3fb7813', 'name file', '01.01.2020',
 'ARTICLE', '32e267e9-7725-450d-bb61-79a5f3fb78d8', 'video', 'path 1', 'Очень интересный материал.', 'Продвинутый уровень');

INSERT INTO files (id, name, upload_date, content_type, training_id, type, path, description, content_name) VALUES (
'77e747e9-7725-450d-bb61-79a5f3fb7811', 'name file', '01.01.2020',
 'ARTICLE', '33e267e9-7725-450d-bb61-79a5f3fb78d8', 'video', 'path 1', 'Очень интересный материал.', 'Основы');
INSERT INTO files (id, name, upload_date, content_type, training_id, type, path, description, content_name) VALUES (
'77e757e9-7725-450d-bb61-79a5f3fb7812', 'name file', '01.01.2020',
 'ARTICLE', '33e267e9-7725-450d-bb61-79a5f3fb78d8', 'video', 'path 1', 'Очень интересный материал.', 'Практика');
INSERT INTO files (id, name, upload_date, content_type, training_id, type, path, description, content_name) VALUES (
'77e767e9-7725-450d-bb61-79a5f3fb7813', 'name file', '01.01.2020',
 'ARTICLE', '33e267e9-7725-450d-bb61-79a5f3fb78d8', 'video', 'path 1', 'Очень интересный материал.', 'Продвинутый уровень');

INSERT INTO files (id, name, upload_date, content_type, training_id, type, path, description, content_name) VALUES (
'77e267e9-7725-450d-bb61-79a5f3fb7811', 'name file', '01.01.2020',
 'ARTICLE', '14e267e9-7725-450d-bb61-79a5f3fb78d5', 'video', 'path 1', 'Подробные инструкции по установке Android Studio на различные платформы. ' ||
  'Настройка SDK.', 'Установка Android Studio');
INSERT INTO files (id, name, upload_date, content_type, training_id, type, path, description, content_name) VALUES (
'77e267e9-7725-450d-bb61-79a5f3fb7812', 'name file', '01.01.2020',
 'ARTICLE', '14e267e9-7725-450d-bb61-79a5f3fb78d5', 'video', 'path 1', '
Разбор осовных возможностей Android Studio. Основной функционал. Поддержка программы чтения с экрана включена.', 'Краткий обзор Android Studio');
INSERT INTO files (id, name, upload_date, content_type, training_id, type, path, description, content_name) VALUES (
'77e267e9-7725-450d-bb61-79a5f3fb7813', 'name file', '01.01.2020',
 'ARTICLE', '14e267e9-7725-450d-bb61-79a5f3fb78d5', 'video', 'path 1', '
Запуск android-приложения на реальном или ' ||
 'виртуальном устройстве андроид. Использование Eclipse и инструментов командной строки.',
  'Запуск первого приложения на эмуляторе');

INSERT INTO files (id, name, upload_date, content_type, training_id, type, path, description, content_name) VALUES (
'77e267e9-7725-450d-bb61-79a5f3fb7821',
 'name file', '01.01.2020',
 'ARTICLE', '14e267e9-7725-450d-bb61-79a5f3fb78d6', 'video', 'path 1',
  'Обзор различий синтаксиса python2 и python3. Print - функция.',
   'Python 2 vs Python 3');
INSERT INTO files (id, name, upload_date, content_type, training_id, type, path, description, content_name) VALUES (
'77e267e9-7725-450d-bb61-79a5f3fb7822',
 'name file', '01.01.2020',
 'ARTICLE', '14e267e9-7725-450d-bb61-79a5f3fb78d6', 'video', 'path 1',
  'Обзор средств для работы с различными сетевыми протоколами и форматами интернета. Разбор и создание почтовых сообщений ' ||
   'для работы.',
   'Стандартная библиотека');
INSERT INTO files (id, name, upload_date, content_type, training_id, type, path, description, content_name) VALUES (
'77e267e9-7725-450d-bb61-79a5f3fb7823',
 'name file', '01.01.2020',
 'ARTICLE', '14e267e9-7725-450d-bb61-79a5f3fb78d6', 'video', 'path 1',
  'Начало работы: использование IDLE или Python Shell. Выбор IDE или редактора кода для оптимизации процесса.',
   'Редакторы кода для Python');

INSERT INTO files (id, name, upload_date, content_type, training_id, type, path, description, content_name) VALUES (
'77e267e9-7725-450d-bb61-79a5f3fb7831',
 'name file', '01.01.2020',
 'ARTICLE', '14e267e9-7725-450d-bb61-79a5f3fb78d7', 'video', 'path 1',
  'Все возможности Photoshop для ПК и iPad. Узнайте как использование различных функций Photoshop помогают создавать изображения GIF.',
   'Работа с Adobe Photoshop в вебе');
INSERT INTO files (id, name, upload_date, content_type, training_id, type, path, description, content_name) VALUES (
'77e267e9-7725-450d-bb61-79a5f3fb7832',
 'name file', '01.01.2020',
 'ARTICLE', '14e267e9-7725-450d-bb61-79a5f3fb78d7', 'video', 'path 1',
  'Процесс перевода макета из Photoshop или Sketch в код. Загрузка макета дизайна и превращение его в спецификацию и стайлгайд, адаптированный под нужды вашей платформы.',
   'Работа с современными редакторами: Avocode, Zeplin, Figma');
INSERT INTO files (id, name, upload_date, content_type, training_id, type, path, description, content_name) VALUES (
'77e267e9-7725-450d-bb61-79a5f3fb7833',
 'name file', '01.01.2020',
 'ARTICLE', '14e267e9-7725-450d-bb61-79a5f3fb78d7', 'video', 'path 1',
  'Создаем свой первый проект. Основы HTML.',
   'Создание проекта');

INSERT INTO files (id, name, upload_date, content_type, training_id, type, path, description, content_name) VALUES (
'77e267e9-7725-450d-bb61-79a5f3fb7841',
 'name file', '01.01.2020',
 'ARTICLE', '14e267e9-7725-450d-bb61-79a5f3fb78d8', 'video', 'path 1',
  'Зачем нужен Angular.js и почему именно он. Angular.js 2.0',
   'Что такое Angular');
INSERT INTO files (id, name, upload_date, content_type, training_id, type, path, description, content_name) VALUES (
'77e267e9-7725-450d-bb61-79a5f3fb7842',
 'name file', '01.01.2020',
 'ARTICLE', '14e267e9-7725-450d-bb61-79a5f3fb78d8', 'video', 'path 1',
  'Компоненты ключевая особенность Angular. Компонентное построение приложения.',
   'Создание компонента с Angular CLI');
INSERT INTO files (id, name, upload_date, content_type, training_id, type, path, description, content_name) VALUES (
'77e267e9-7725-450d-bb61-79a5f3fb7843',
 'name file', '01.01.2020',
 'ARTICLE', '14e267e9-7725-450d-bb61-79a5f3fb78d8', 'video', 'path 1',
  'Стилизация компонента может производиться как с помощью установки стилей в самом компоненте,' ||
   ' так и с помощью подключения внешних css-файлов.',
   'Шаблоны и стили');