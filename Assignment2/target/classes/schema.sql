create table newplayer (
	id identity,
	firstname varchar(20) not null,
	lastname varchar(20) not null,
  age int,
	currency varchar(20) not null,
	salary double,
	country varchar(20) not null,
	position varchar(20) not null,
	goals int,
	bookings int
);
