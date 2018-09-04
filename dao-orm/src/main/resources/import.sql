


CREATE TABLE user_account (
	id serial NOT NULL,
	email character varying(100) NOT NULL UNIQUE,
	password character varying(100) NOT NULL,
	name character varying(50) NOT NULL,
	photo_link character varying(500) UNIQUE,
	phone character varying(50) NOT NULL UNIQUE,
	role character varying(50) NOT NULL,
	version integer NOT NULL,
	created_date TIMESTAMP(3) NOT NULL,
	last_modified_date TIMESTAMP(3) NOT NULL default now(3),
	created_by_id BIGINT UNSIGNED,
	last_modified_by_id BIGINT UNSIGNED,
	CONSTRAINT user_account_pk PRIMARY KEY (id),
	CONSTRAINT FOREIGN KEY (created_by_id) REFERENCES user_account(id) ON DELETE RESTRICT,
	CONSTRAINT FOREIGN KEY (last_modified_by_id) REFERENCES user_account(id) ON DELETE RESTRICT
);

    insert into user_account (email, password, name, phone, role, version, created_date) values ('admin@itools.ru', 'admin', 'admin', '911', 'ROLE_ADMIN', 1, now(3));


CREATE TABLE passport (
	id serial NOT NULL,
	user_account_id BIGINT UNSIGNED NOT NULL,
	full_name character varying(100) NOT NULL,
	number character varying(50) NOT NULL UNIQUE,
	issue_place character varying(100) NOT NULL,
	issue_date DATETIME NOT NULL,
	birth_place character varying(500) NOT NULL,
	birthday DATETIME NOT NULL,
	version integer NOT NULL,
	created_date TIMESTAMP(3) NOT NULL,
	last_modified_date TIMESTAMP(3) NOT NULL default now(3),
	created_by_id BIGINT UNSIGNED,
	last_modified_by_id BIGINT UNSIGNED,
	CONSTRAINT passport_pk PRIMARY KEY (id),
	CONSTRAINT FOREIGN KEY (created_by_id) REFERENCES user_account(id) ON DELETE RESTRICT,
	CONSTRAINT FOREIGN KEY (last_modified_by_id) REFERENCES user_account(id) ON DELETE RESTRICT,
	CONSTRAINT FOREIGN KEY (user_account_id) REFERENCES user_account(id) ON DELETE RESTRICT
);




CREATE TABLE driving_license (
	id serial NOT NULL,
	user_account_id BIGINT UNSIGNED NOT NULL,
	number character varying(50) NOT NULL UNIQUE,
	expiration_date DATETIME NOT NULL,
	categories character varying(50) NOT NULL,
	version integer NOT NULL,
	created_date TIMESTAMP(3) NOT NULL,
	last_modified_date TIMESTAMP(3) NOT NULL default now(3),
	created_by_id BIGINT UNSIGNED,
	last_modified_by_id BIGINT UNSIGNED,
	CONSTRAINT driving_license_pk PRIMARY KEY (id),
	CONSTRAINT FOREIGN KEY (created_by_id) REFERENCES user_account(id) ON DELETE RESTRICT,
	CONSTRAINT FOREIGN KEY (last_modified_by_id) REFERENCES user_account(id) ON DELETE RESTRICT,
	CONSTRAINT FOREIGN KEY (user_account_id) REFERENCES user_account(id) ON DELETE RESTRICT
);



CREATE TABLE brand (
	id serial NOT NULL,
	name character varying(50) NOT NULL UNIQUE,
	version integer NOT NULL,
	created_date TIMESTAMP(3) NOT NULL,
	last_modified_date TIMESTAMP(3) NOT NULL default now(3),
	created_by_id BIGINT UNSIGNED,
	last_modified_by_id BIGINT UNSIGNED,
	CONSTRAINT brand_pk PRIMARY KEY (id),
	CONSTRAINT FOREIGN KEY (created_by_id) REFERENCES user_account(id) ON DELETE RESTRICT,
	CONSTRAINT FOREIGN KEY (last_modified_by_id) REFERENCES user_account(id) ON DELETE RESTRICT
);



CREATE TABLE gearbox (
	id serial NOT NULL,
	name character varying(50) NOT NULL UNIQUE,
	version integer NOT NULL,
	created_date TIMESTAMP(3) NOT NULL,
	last_modified_date TIMESTAMP(3) NOT NULL default now(3),
	created_by_id BIGINT UNSIGNED,
	last_modified_by_id BIGINT UNSIGNED,
	CONSTRAINT gearbox_pk PRIMARY KEY (id),
	CONSTRAINT FOREIGN KEY (created_by_id) REFERENCES user_account(id) ON DELETE RESTRICT,
	CONSTRAINT FOREIGN KEY (last_modified_by_id) REFERENCES user_account(id) ON DELETE RESTRICT
);



CREATE TABLE body_type (
	id serial NOT NULL,
	name character varying(50) NOT NULL UNIQUE,
	version integer NOT NULL,
	created_date TIMESTAMP(3) NOT NULL,
	last_modified_date TIMESTAMP(3) NOT NULL default now(3),
	created_by_id BIGINT UNSIGNED,
	last_modified_by_id BIGINT UNSIGNED,
	CONSTRAINT body_type_pk PRIMARY KEY (id),
	CONSTRAINT FOREIGN KEY (created_by_id) REFERENCES user_account(id) ON DELETE RESTRICT,
	CONSTRAINT FOREIGN KEY (last_modified_by_id) REFERENCES user_account(id) ON DELETE RESTRICT
);



CREATE TABLE drive (
	id serial NOT NULL,
	name character varying(50) NOT NULL UNIQUE,
	version integer NOT NULL,
	created_date TIMESTAMP(3) NOT NULL,
	last_modified_date TIMESTAMP(3) NOT NULL default now(3),
	created_by_id BIGINT UNSIGNED,
	last_modified_by_id BIGINT UNSIGNED,
	CONSTRAINT drive_pk PRIMARY KEY (id),
	CONSTRAINT FOREIGN KEY (created_by_id) REFERENCES user_account(id) ON DELETE RESTRICT,
	CONSTRAINT FOREIGN KEY (last_modified_by_id) REFERENCES user_account(id) ON DELETE RESTRICT
);



CREATE TABLE fuel (
	id serial NOT NULL,
	name character varying(50) NOT NULL UNIQUE,
	version integer NOT NULL,
	created_date TIMESTAMP(3) NOT NULL,
	last_modified_date TIMESTAMP(3) NOT NULL default now(3),
	created_by_id BIGINT UNSIGNED,
	last_modified_by_id BIGINT UNSIGNED,
	CONSTRAINT fuel_pk PRIMARY KEY (id),
	CONSTRAINT FOREIGN KEY (created_by_id) REFERENCES user_account(id) ON DELETE RESTRICT,
	CONSTRAINT FOREIGN KEY (last_modified_by_id) REFERENCES user_account(id) ON DELETE RESTRICT
);




CREATE TABLE engine_type (
	id serial NOT NULL,
	name character varying(50) NOT NULL UNIQUE,
	fuel_id BIGINT UNSIGNED NOT NULL,
	version integer NOT NULL,
	created_date TIMESTAMP(3) NOT NULL,
	last_modified_date TIMESTAMP(3) NOT NULL default now(3),
	created_by_id BIGINT UNSIGNED,
	last_modified_by_id BIGINT UNSIGNED,
	CONSTRAINT engine_type_pk PRIMARY KEY (id),
	CONSTRAINT FOREIGN KEY (created_by_id) REFERENCES user_account(id) ON DELETE RESTRICT,
	CONSTRAINT FOREIGN KEY (last_modified_by_id) REFERENCES user_account(id) ON DELETE RESTRICT,
	CONSTRAINT FOREIGN KEY (fuel_id) REFERENCES fuel(id) ON DELETE RESTRICT
);



CREATE TABLE car_parameter (
	id serial NOT NULL,
	name character varying(300) NOT NULL UNIQUE,
	version integer NOT NULL,
	created_date TIMESTAMP(3) NOT NULL,
	last_modified_date TIMESTAMP(3) NOT NULL default now(3),
	created_by_id BIGINT UNSIGNED,
	last_modified_by_id BIGINT UNSIGNED,
	CONSTRAINT parameter_pk PRIMARY KEY (id),
	CONSTRAINT FOREIGN KEY (created_by_id) REFERENCES user_account(id) ON DELETE RESTRICT,
	CONSTRAINT FOREIGN KEY (last_modified_by_id) REFERENCES user_account(id) ON DELETE RESTRICT
);



CREATE TABLE model(
	id serial NOT NULL,
	name character varying(50) NOT NULL,
	brand_id BIGINT UNSIGNED NOT NULL,
	version integer NOT NULL,
	created_date TIMESTAMP(3) NOT NULL,
	last_modified_date TIMESTAMP(3) NOT NULL default now(3),
	created_by_id BIGINT UNSIGNED,
	last_modified_by_id BIGINT UNSIGNED,
	CONSTRAINT model_pk PRIMARY KEY (id),
	CONSTRAINT brand_id_name_key UNIQUE (brand_id, name),
	CONSTRAINT FOREIGN KEY (brand_id) REFERENCES brand(id) ON DELETE RESTRICT,
	CONSTRAINT FOREIGN KEY (created_by_id) REFERENCES user_account(id) ON DELETE RESTRICT,
	CONSTRAINT FOREIGN KEY (last_modified_by_id) REFERENCES user_account(id) ON DELETE RESTRICT
);



CREATE TABLE car (
	id serial NOT NULL,
	model_id BIGINT UNSIGNED NOT NULL,
	year integer NOT NULL,
	plate character varying(50) NOT NULL,
	mileage integer,
	seats integer,
	gearbox_id BIGINT UNSIGNED,
	body_type_id BIGINT UNSIGNED,
	drive_id BIGINT UNSIGNED,
	engine_type_id BIGINT UNSIGNED NOT NULL,
	charge numeric(4,2),
	conditions character varying(1000) NOT NULL,
	insurance character varying(500),
	version integer NOT NULL,
	created_date TIMESTAMP(3) NOT NULL,
	last_modified_date TIMESTAMP(3) NOT NULL default now(3),
	created_by_id BIGINT UNSIGNED,
	last_modified_by_id BIGINT UNSIGNED,
	CONSTRAINT car_pk PRIMARY KEY (id),
	CONSTRAINT FOREIGN KEY (created_by_id) REFERENCES user_account(id) ON DELETE RESTRICT,
	CONSTRAINT FOREIGN KEY (last_modified_by_id) REFERENCES user_account(id) ON DELETE RESTRICT,
	CONSTRAINT FOREIGN KEY (model_id) REFERENCES model(id) ON DELETE RESTRICT,
	CONSTRAINT FOREIGN KEY (gearbox_id) REFERENCES gearbox(id) ON DELETE RESTRICT,
	CONSTRAINT FOREIGN KEY (body_type_id) REFERENCES body_type(id) ON DELETE RESTRICT,
	CONSTRAINT FOREIGN KEY (drive_id) REFERENCES drive(id) ON DELETE RESTRICT,
	CONSTRAINT FOREIGN KEY (engine_type_id) REFERENCES engine_type(id) ON DELETE RESTRICT
);



CREATE TABLE cars_photo (
	id serial NOT NULL,
	car_id BIGINT UNSIGNED NOT NULL,
	link character varying(300) NOT NULL UNIQUE,
	version integer NOT NULL,
	created_date TIMESTAMP(3) NOT NULL,
	last_modified_date TIMESTAMP(3) NOT NULL default now(3),
	created_by_id BIGINT UNSIGNED,
	last_modified_by_id BIGINT UNSIGNED,
	CONSTRAINT cars_photo_pk PRIMARY KEY (id),
	CONSTRAINT FOREIGN KEY (car_id) REFERENCES car(id) ON DELETE RESTRICT,
	CONSTRAINT FOREIGN KEY (created_by_id) REFERENCES user_account(id) ON DELETE RESTRICT,
	CONSTRAINT FOREIGN KEY (last_modified_by_id) REFERENCES user_account(id) ON DELETE RESTRICT
);



CREATE TABLE car_2_car_parameter (
	car_id BIGINT UNSIGNED NOT NULL,
	car_parameter_id BIGINT UNSIGNED NOT NULL,
	CONSTRAINT car_id_car_parameter_id_key UNIQUE (car_id, car_parameter_id),
	CONSTRAINT FOREIGN KEY (car_parameter_id) REFERENCES car_parameter(id) ON DELETE RESTRICT,
	CONSTRAINT FOREIGN KEY (car_id) REFERENCES car(id) ON DELETE RESTRICT
);



CREATE TABLE calendar (
	id serial NOT NULL,
	renter_id BIGINT UNSIGNED NOT NULL,
	car_id BIGINT UNSIGNED NOT NULL,
	start DATETIME NOT NULL,
	end DATETIME NOT NULL,
	total_price numeric(12,2) NOT NULL,
	version integer NOT NULL,
	created_date TIMESTAMP(3) NOT NULL,
	last_modified_date TIMESTAMP(3) NOT NULL default now(3),
	created_by_id BIGINT UNSIGNED,
	last_modified_by_id BIGINT UNSIGNED,
	CONSTRAINT calendar_pk PRIMARY KEY (id),
	CONSTRAINT FOREIGN KEY (created_by_id) REFERENCES user_account(id) ON DELETE RESTRICT,
	CONSTRAINT FOREIGN KEY (last_modified_by_id) REFERENCES user_account(id) ON DELETE RESTRICT,
	CONSTRAINT FOREIGN KEY (renter_id) REFERENCES user_account(id) ON DELETE RESTRICT,
	CONSTRAINT FOREIGN KEY (car_id) REFERENCES car(id) ON DELETE RESTRICT
);




CREATE TABLE ad (
	id serial NOT NULL,
	car_id BIGINT UNSIGNED NOT NULL,
	user_account_id BIGINT UNSIGNED NOT NULL,
	address character varying(500) NOT NULL,
	price numeric(12,2) NOT NULL,
	body character varying(1000) NOT NULL,
	active BOOLEAN NOT NULL,
	version integer NOT NULL,
	created_date TIMESTAMP(3) NOT NULL,
	last_modified_date TIMESTAMP(3) NOT NULL default now(3),
	created_by_id BIGINT UNSIGNED,
	last_modified_by_id BIGINT UNSIGNED,
	CONSTRAINT ad_pk PRIMARY KEY (id),
	CONSTRAINT FOREIGN KEY (created_by_id) REFERENCES user_account(id) ON DELETE RESTRICT,
	CONSTRAINT FOREIGN KEY (last_modified_by_id) REFERENCES user_account(id) ON DELETE RESTRICT,
	CONSTRAINT FOREIGN KEY (car_id) REFERENCES car(id) ON DELETE RESTRICT,
	CONSTRAINT FOREIGN KEY (user_account_id) REFERENCES user_account(id) ON DELETE RESTRICT
);



CREATE TABLE message (
	id serial NOT NULL,
	message character varying(500) NOT NULL,
	ad_id BIGINT UNSIGNED NOT NULL,
	sender_id BIGINT UNSIGNED NOT NULL,
	recipient_id BIGINT UNSIGNED NOT NULL,
	viewed BOOLEAN NOT NULL,
	version integer NOT NULL,
	created_date TIMESTAMP(3) NOT NULL,
	last_modified_date TIMESTAMP(3) NOT NULL default now(3),
	created_by_id BIGINT UNSIGNED,
	last_modified_by_id BIGINT UNSIGNED,
	CONSTRAINT message_pk PRIMARY KEY (id),
	CONSTRAINT FOREIGN KEY (created_by_id) REFERENCES user_account(id) ON DELETE RESTRICT,
	CONSTRAINT FOREIGN KEY (last_modified_by_id) REFERENCES user_account(id) ON DELETE RESTRICT,
	CONSTRAINT FOREIGN KEY (ad_id) REFERENCES ad(id) ON DELETE RESTRICT,
	CONSTRAINT FOREIGN KEY (sender_id) REFERENCES user_account(id) ON DELETE RESTRICT,
	CONSTRAINT FOREIGN KEY (recipient_id) REFERENCES user_account(id) ON DELETE RESTRICT
);

