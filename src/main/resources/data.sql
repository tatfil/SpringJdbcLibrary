DROP TABLE IF EXISTS books CASCADE;
DROP TABLE IF EXISTS authors CASCADE;
DROP TABLE IF EXISTS patrons CASCADE;
DROP TABLE IF EXISTS authors_books CASCADE;
DROP TABLE IF EXISTS catalog CASCADE;

CREATE TABLE books(
                      id SERIAL,
                      isbn int,
                      title varchar (50) NOT NULL,
                      subject varchar (50),
                      publisher varchar (50) NOT NULL,
                      publication_date date,
                      barcode int,
                      borrowed date,
                      status varchar (50),

                      PRIMARY KEY (id)
);

CREATE TABLE authors(
                       id SERIAL,
                       name varchar (50) NOT NULL,
                       PRIMARY KEY (id)
);


CREATE TABLE patrons(
                         id SERIAL,
                         name varchar (50) NOT NULL,
                         address varchar (200) NOT NULL,
                         PRIMARY KEY (id)
);

CREATE TABLE authors_books(
                                 author_id INTEGER REFERENCES authors(id),
                                 book_id INTEGER REFERENCES books(id),
                                 PRIMARY KEY(author_id, book_id)
);

CREATE TABLE catalog(
                        id SERIAL,
                        book_id INTEGER references books (id),
                        author_id INTEGER references authors (id),
                        patron_id INTEGER references patrons (id),
                        PRIMARY KEY (id)
                    );
