INSERT INTO users (name, email, password)
VALUES ('Pablo', 'pablo@campingfire.com', '$2a$10$h1/7nEkT/zubSL4DY.1fEOiVX57DXMRYQiJ37Eby12SWNmGhspP.a'); -- password 'admin'

INSERT INTO users (name, email, password)
VALUES ('Marcos', 'marcos@email.com', '$2a$10$C1q4jBhTYRVgQMxHl9sgHOfwxkslGA4LPp1OmOmwf9UkLdC1ddXWC'); -- password 'marcos12'

INSERT INTO users (name, email, password)
VALUES ('José', 'jose@email.com', '$2a$10$wkCbpmPVH6uFXLDRMnjfguRQia24oXJqVZaJFBakMIYi7ogAWmZ3.'); -- password '123jose'


INSERT INTO campings (name, state, city, address, description, contact, id_user)
VALUES ('Acampamento Floresta', 'SP', 'Eldorado', 'Rua Salgueiro, 22', 'Árvores e mata', 11987654321, 1);

INSERT INTO campings (name, state, city, address, description, contact, id_user)
VALUES ('Acampamento Montanha', 'RJ', 'Parati', 'Tenente José Augusto Mendes Peixoto, 1500', 'Montanha', 11987656532, 1);

INSERT INTO campings (name, state, city, address, description, contact, id_user)
VALUES ('Acampamento Floresta II', 'BA', 'Parati', 'Rua Paula Vieira, 987', 'Floresta', 1198326566, 1);

INSERT INTO campings (name, state, city, address, description, contact, id_user)
VALUES ('Acampamento Areia', 'SP', 'Santos', 'Praia Grande', 'Dunas de areia', 11911156532, 2);

INSERT INTO campings (name, state, city, address, description, contact, id_user)
VALUES ('Acampamento Maré', 'SP', 'Santos', 'Praia do Boqueirão', 'Beira da água', 119118565343, 2);


INSERT INTO images (name, url, id_camping)
VALUES ('20200903_1029000000_1c.jpg', 'https://www.googleapis.com/download/storage/v1/b/camping-fire/o/20200903_1029000000_1c.jpg?generation=1599245464194045&alt=media', 1);

INSERT INTO images (name, url, id_camping)
VALUES ('20200903_1030000000_2c', 'https://www.googleapis.com/download/storage/v1/b/camping-fire/o/20200903_1030000000_2c.jpg?generation=1599245592361993&alt=media', 2);

INSERT INTO images (name, url, id_camping)
VALUES ('20200903_1030010000_3c.jpg', 'https://www.googleapis.com/download/storage/v1/b/camping-fire/o/20200903_1030010000_3c.jpg?generation=1599245689852169&alt=media', 3);


INSERT INTO roles (name)
VALUES ('ROLE_ADMIN');

INSERT INTO roles (name)
VALUES ('ROLE_USER');


INSERT INTO users_roles (id_user, id_role)
VALUES (1, 1);

INSERT INTO users_roles (id_user, id_role)
VALUES (2, 2);

INSERT INTO users_roles (id_user, id_role)
VALUES (3, 2);
