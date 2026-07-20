-- seeds the first user so the app has a principal to authenticate as locally; not for production use
INSERT INTO user (id, username, password)
VALUES ('11111111-1111-1111-1111-111111111111', 'Marty', 'McFly');