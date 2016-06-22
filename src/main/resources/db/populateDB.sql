DELETE FROM user_roles;
DELETE FROM meals;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password');

INSERT INTO users (name, email, password)
VALUES ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO meals (date_time, description, calories, user_id) VALUES
  ('20-06-2016 10:00', 'Завтрак', 500, 100000),
  ('20-06-2016 15:00', 'Обед', 800, 100000),
  ('20-06-2016 19:00', 'Ужин', 300, 100000),
  ('21-06-2016 11:00', 'Завтрак', 300, 100000),
  ('21-06-2016 14:30', 'Обед', 1000, 100000),
  ('21-06-2016 20:00', 'Ужин', 800, 100000),
  ('21-06-2016 9:00', 'Завтрак', 500, 100001),
  ('21-06-2016 14:00', 'Обед', 700, 100001),
  ('21-06-2016 18:00', 'Ужин', 900, 100001);
