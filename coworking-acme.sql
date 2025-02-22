--Creación de la BD
create database coworking-acme;

--Creación de las tablas

create table espacio (

	id serial not null primary key,
	nombre varchar(255) not null,
	tipo varchar(255) not null,
	capacidad_maxima int8 not null,
	disponibilidad varchar(255) default 'INACTIVO'

);

create table reserva  (

	id serial not null primary key,
	reserva date not null,
	inicio time not null,
	fin time not null,
	estado varchar(255) default 'PENDIENTE',
	espacio_id int8 not null,
	foreign key(espacio_id) references espacio(id)

);

create table espacio_compartido  (

	id serial not null primary key,
	espacio_id int8 not null,
	reserva_id int8 not null,
	foreign key(espacio_id) references espacio(id),
	foreign key(reserva_id) references reserva(id)
);

--Inserciones
insert into espacio (nombre, tipo, disponibilidad, capacidad_maxima) 
values
('Zona Rock Star', 'OFICINA_PRIVADA', 'INACTIVO', 8),
('Contratos y negocios', 'SALA_REUNIONES', 'INACTIVO', 12),
('Ambiente feliz', 'ESCRITORIO_COMPARTIDO', 'ACTIVO', 10),
('Zona Rosa', 'OFICINA_PRIVADA', 'INACTIVO', 10),
('Parvulos', 'ESCRITORIO_COMPARTIDO', 'ACTIVO', 23),
('Galletas Saladas', 'OFICINA_PRIVADA', 'INACTIVO', 6),
('Caos Sabor Miel', 'SALA_REUNIONES', 'ACTIVO', 8),
('Leña Oscuro', 'ESCRITORIO_COMPARTIDO', 'ACTIVO', 4),
('Verde flores', 'SALA_REUNIONES', 'ACTIVO', 5),
('Araña lucidas', 'OFICINA_PRIVADA', 'ACTIVO', 5);

insert into reserva (estado, fin, inicio, reserva, espacio_id)
values
('CONFIRMADA', '12:00:00', '07:00:00', '2024-07-13', 1),
('PENDIENTE', '11:20:00', '06:30:00', '2025-03-12', 2),
('CANCELADA', '15:00:00', '14:00:00', '2025-02-25', 7),
('CONFIRMADA', '12:00:00', '06:00:00', '2024-12-13', 3),
('PENDIENTE', '10:00:00', '06:00:00', '2025-02-22', 4),
('CONFIRMADA', '18:30:00', '14:30:00', '2025-02-20', 5),
('CONFIRMADA', '12:30:00', '8:30:00', '2025-02-21', 6),
('PENDIENTE', '12:00:00', '06:00:00', '2025-02-23', 8),
('CANCELADA', '09:30:00', '07:00:00', '2024-12-05', 9),
('CANCELADA', '10:00:00', '06:00:00', '2025-02-21', 10);


insert into espacio_compartido (reserva_id, espacio_id) 
values
(4, 3),
(6, 5),
(8, 8);


