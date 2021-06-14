create table records
(
	id serial not null
		constraint records_pk
			primary key,
	uuid uuid not null,
	domain_name varchar not null,
	caller_name varchar,
	caller_number varchar not null,
	destination_number varchar not null,
	direction varchar not null,
	call_start varchar not null,
	ring_start varchar not null,
	answer_start varchar,
	call_end varchar,
	duration integer,
	recording uuid,
	click_to_call boolean default false,
	click_to_call_data varchar,
	action varchar
);

alter table records owner to postgres;

create unique index records_uuid_uindex
	on records (uuid);

