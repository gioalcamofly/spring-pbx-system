create table pbx
(
	id serial not null
		constraint pbx_pk
			primary key,
	name varchar not null,
	url varchar not null,
	endpoints varchar not null
);

alter table pbx owner to postgres;

create unique index pbx_name_uindex
	on pbx (name);

