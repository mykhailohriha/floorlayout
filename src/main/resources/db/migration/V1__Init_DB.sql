drop table if exists hibernate_sequence;
drop table if exists layout;
create table hibernate_sequence
(
    next_val bigint
) engine = InnoDB;
insert into hibernate_sequence
values (1);
create table layout
(
    id          integer not null,
    name        varchar(255),
    coordinates varchar(255),
    primary key (id)
) engine = InnoDB;


