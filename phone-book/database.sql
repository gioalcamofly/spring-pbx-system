create table contacts
(
	id serial not null
		constraint phone_book_pk
			primary key,
	name varchar not null,
	email varchar,
	number varchar
);

alter table contacts owner to postgres;

create unique index phone_book_email_uindex
	on contacts (email);

create unique index phone_book_number_uindex
	on contacts (number);

