CREATE DATABASE music_store;
CREATE USER 'music_user'@'localhost' IDENTIFIED BY 'password123';
GRANT ALL PRIVILEGES ON music_store.* TO 'music_user'@'localhost';
FLUSH PRIVILEGES;