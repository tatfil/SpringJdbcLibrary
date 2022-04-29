DROP TABLE IF EXISTS authors CASCADE;
DROP TABLE IF EXISTS books CASCADE;
DROP TABLE IF EXISTS patrons CASCADE;
DROP TABLE IF EXISTS accounts CASCADE;
DROP TABLE IF EXISTS authors_books CASCADE;
DROP TABLE IF EXISTS accounts_books CASCADE;
DROP TABLE IF EXISTS library CASCADE;

CREATE TABLE authors(
                       id SERIAL,
                       name varchar (50) NOT NULL,
                       PRIMARY KEY (id)
);

CREATE TABLE books(
                      id SERIAL,
                      isbn int,
                      title varchar (50) NOT NULL,
                      barcode int,
                      borrowed date,
                      status varchar (50),
                      PRIMARY KEY (id)
);

CREATE TABLE patrons(
                         id SERIAL,
                         name varchar (50) NOT NULL,
                         address varchar (200) NOT NULL,
                         PRIMARY KEY (id)
);

CREATE TABLE accounts(
                         id SERIAL,
                         patron_id INTEGER references patrons(id),
                         state varchar (50),
                         PRIMARY KEY (id)
);

CREATE TABLE authors_books(
                                 author_id INTEGER REFERENCES authors(id),
                                 book_id INTEGER REFERENCES books(id),
                                 PRIMARY KEY(author_id, book_id)
);

CREATE TABLE accounts_books(
                                 account_id INTEGER REFERENCES accounts(id),
                                 book_id INTEGER REFERENCES books(id),
                                 PRIMARY KEY(account_id, book_id)
);

CREATE TABLE library(
                        id SERIAL,
                        book_id INTEGER references books(id),
                        author_id INTEGER references authors(id),
                        account_id INTEGER references accounts(id),
                        PRIMARY KEY (id)
                    );

CREATE UNIQUE INDEX idx_unique_book ON books (barcode);