create TABLE acounts (
  id INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL,
   patron_id INTEGER,
   state VARCHAR(50),
   CONSTRAINT acounts_pkey PRIMARY KEY (id)
);

create TABLE authors (
  id INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL,
   name VARCHAR(50) NOT NULL,
   CONSTRAINT authors_pkey PRIMARY KEY (id)
);

create TABLE books (
  id INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL,
   isbn INTEGER,
   title VARCHAR(50) NOT NULL,
   barcode INTEGER,
   borrowed date,
   status VARCHAR(50),
   CONSTRAINT books_pkey PRIMARY KEY (id)
);

create TABLE patrons (
  id INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL,
   name VARCHAR(50) NOT NULL,
   address VARCHAR(200) NOT NULL,
   CONSTRAINT patrons_pkey PRIMARY KEY (id)
);

create TABLE accounts (
  id INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL,
   patron_id INTEGER,
   state VARCHAR(50),
   CONSTRAINT accounts_pkey PRIMARY KEY (id)
);

create TABLE library (
  id INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL,
   book_id INTEGER,
   author_id INTEGER,
   account_id INTEGER,
   CONSTRAINT library_pkey PRIMARY KEY (id)
);

create unique index idx_unique_book on books(barcode);

create TABLE accounts_books (
  account_id INTEGER NOT NULL,
   book_id INTEGER NOT NULL,
   CONSTRAINT accounts_books_pkey PRIMARY KEY (account_id, book_id)
);

create TABLE authors_books (
  author_id INTEGER NOT NULL,
   book_id INTEGER NOT NULL,
   CONSTRAINT authors_books_pkey PRIMARY KEY (author_id, book_id)
);

alter table accounts_books add CONSTRAINT accounts_books_account_id_fkey FOREIGN KEY (account_id) REFERENCES accounts (id) ON update NO ACTION ON delete NO ACTION;

alter table accounts_books add CONSTRAINT accounts_books_book_id_fkey FOREIGN KEY (book_id) REFERENCES books (id) ON update NO ACTION ON delete NO ACTION;

alter table accounts add CONSTRAINT accounts_patron_id_fkey FOREIGN KEY (patron_id) REFERENCES patrons (id) ON update NO ACTION ON delete NO ACTION;

alter table authors_books add CONSTRAINT authors_books_author_id_fkey FOREIGN KEY (author_id) REFERENCES authors (id) ON update NO ACTION ON delete NO ACTION;

alter table authors_books add CONSTRAINT authors_books_book_id_fkey FOREIGN KEY (book_id) REFERENCES books (id) ON update NO ACTION ON delete NO ACTION;

alter table library add CONSTRAINT library_account_id_fkey FOREIGN KEY (account_id) REFERENCES accounts (id) ON update NO ACTION ON delete NO ACTION;

alter table library add CONSTRAINT library_author_id_fkey FOREIGN KEY (author_id) REFERENCES authors (id) ON update NO ACTION ON delete NO ACTION;

alter table library add CONSTRAINT library_book_id_fkey FOREIGN KEY (book_id) REFERENCES books (id) ON update NO ACTION ON delete NO ACTION;