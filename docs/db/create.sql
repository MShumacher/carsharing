

CREATE TABLE model (
	id serial NOT NULL,
	name character varying(50) NOT NULL UNIQUE,
	brand_id integer NOT NULL,
	version integer NOT NULL,
	created BIGINT NOT NULL,
	updated BIGINT NOT NULL,
	CONSTRAINT model_pk PRIMARY KEY (id)
);


CREATE TABLE cars_photo (
	id serial NOT NULL,
	car_id integer NOT NULL,
	link character varying(300) NOT NULL UNIQUE,
	version integer NOT NULL,
	created BIGINT NOT NULL,
	updated BIGINT NOT NULL,
	CONSTRAINT cars_photo_pk PRIMARY KEY (id)
);


CREATE TABLE car (
	id serial NOT NULL,
	model_id integer NOT NULL,
	year integer NOT NULL,
	plate character varying(50) NOT NULL,
	mileage integer,
	seats integer,
	gearbox_id integer,
	body_type_id integer,
	drive_id integer,
	engine_type_id integer NOT NULL,
	charge numeric(4,2),
	conditions character varying(1000) NOT NULL,
	insurance character varying(500),
	version integer NOT NULL,
	created BIGINT NOT NULL,
	updated BIGINT NOT NULL,
	CONSTRAINT car_pk PRIMARY KEY (id)
);


CREATE TABLE message (
	id serial NOT NULL,
	message character varying(500) NOT NULL,
	ad_id bigint NOT NULL,
	sender_id integer NOT NULL,
	recipient_id integer NOT NULL,
	viewed BOOLEAN NOT NULL,
	version integer NOT NULL,
	created BIGINT NOT NULL,
	updated BIGINT NOT NULL,
	CONSTRAINT message_pk PRIMARY KEY (id)
);


CREATE TABLE user_account (
	id serial NOT NULL,
	email character varying(100) NOT NULL UNIQUE,
	password character varying(100) NOT NULL,
	name character varying(50) NOT NULL,
	photo_link character varying(500) UNIQUE,
	phone character varying(50) NOT NULL UNIQUE,
	role character varying(50) NOT NULL,
	version integer NOT NULL,
	created BIGINT NOT NULL,
	updated BIGINT NOT NULL,
	CONSTRAINT user_account_pk PRIMARY KEY (id)
);


CREATE TABLE car_parameter (
	id serial NOT NULL,
	name character varying(300) NOT NULL UNIQUE,
	version integer NOT NULL,
	created BIGINT NOT NULL,
	updated BIGINT NOT NULL,
	CONSTRAINT parameter_pk PRIMARY KEY (id)
);


CREATE TABLE car_2_car_parameter (
	car_parameter_id integer NOT NULL,
	car_id integer NOT NULL
);


CREATE TABLE calendar (
	id serial NOT NULL,
	renter_id integer NOT NULL,
	car_id integer NOT NULL,
	start DATETIME NOT NULL,
	end DATETIME NOT NULL,
	total_price numeric(12,2) NOT NULL,
	version integer NOT NULL,
	created BIGINT NOT NULL,
	updated BIGINT NOT NULL,
	CONSTRAINT calendar_pk PRIMARY KEY (id)
);


CREATE TABLE ad (
	id serial NOT NULL,
	car_id integer NOT NULL,
	user_account_id integer NOT NULL,
	address character varying(500) NOT NULL,
	price numeric(12,2) NOT NULL,
	body character varying(1000) NOT NULL,
	active BOOLEAN NOT NULL,
	version integer NOT NULL,
	created BIGINT NOT NULL,
	updated BIGINT NOT NULL,
	CONSTRAINT ad_pk PRIMARY KEY (id)
);


CREATE TABLE passport (
	id serial NOT NULL,
	user_account_id integer NOT NULL,
	full_name character varying(100) NOT NULL,
	number character varying(50) NOT NULL UNIQUE,
	issue_place character varying(100) NOT NULL,
	issue_date DATETIME NOT NULL,
	birth_place character varying(500) NOT NULL,
	birthday DATETIME NOT NULL,
	version integer NOT NULL,
	created BIGINT NOT NULL,
	updated BIGINT NOT NULL,
	CONSTRAINT passport_pk PRIMARY KEY (id)
);


CREATE TABLE driving_license (
	id serial NOT NULL,
	user_account_id integer NOT NULL,
	number character varying(50) NOT NULL UNIQUE,
	expiration_date DATETIME NOT NULL,
	categories character varying(50) NOT NULL,
	version integer NOT NULL,
	created BIGINT NOT NULL,
	updated BIGINT NOT NULL,
	CONSTRAINT driving_license_pk PRIMARY KEY (id)
);


CREATE TABLE brand (
	id serial NOT NULL,
	name character varying(50) NOT NULL UNIQUE,
	version integer NOT NULL,
	created BIGINT NOT NULL,
	updated BIGINT NOT NULL,
	CONSTRAINT brand_pk PRIMARY KEY (id)
);


CREATE TABLE gearbox (
	id serial NOT NULL,
	name character varying(50) NOT NULL UNIQUE,
	version integer NOT NULL,
	created BIGINT NOT NULL,
	updated BIGINT NOT NULL,
	CONSTRAINT gearbox_pk PRIMARY KEY (id)
);


CREATE TABLE body_type (
	id serial NOT NULL,
	name character varying(50) NOT NULL UNIQUE,
	version integer NOT NULL,
	created BIGINT NOT NULL,
	updated BIGINT NOT NULL,
	CONSTRAINT body_type_pk PRIMARY KEY (id)
);


CREATE TABLE drive (
	id serial NOT NULL,
	name character varying(50) NOT NULL UNIQUE,
	version integer NOT NULL,
	created BIGINT NOT NULL,
	updated BIGINT NOT NULL,
	CONSTRAINT drive_pk PRIMARY KEY (id)
);


CREATE TABLE engine_type (
	id serial NOT NULL,
	name character varying(50) NOT NULL UNIQUE,
	fuel_id integer NOT NULL,
	version integer NOT NULL,
	created BIGINT NOT NULL,
	updated BIGINT NOT NULL,
	CONSTRAINT engine_type_pk PRIMARY KEY (id)
);


CREATE TABLE fuel (
	id serial NOT NULL,
	name character varying(50) NOT NULL UNIQUE,
	version integer NOT NULL,
	created BIGINT NOT NULL,
	updated BIGINT NOT NULL,
	CONSTRAINT fuel_pk PRIMARY KEY (id)
);



ALTER TABLE model ADD CONSTRAINT model_fk0 FOREIGN KEY (brand_id) REFERENCES brand(id);

ALTER TABLE cars_photo ADD CONSTRAINT cars_photo_fk0 FOREIGN KEY (car_id) REFERENCES car(id);

ALTER TABLE car ADD CONSTRAINT car_fk0 FOREIGN KEY (model_id) REFERENCES model(id);
ALTER TABLE car ADD CONSTRAINT car_fk1 FOREIGN KEY (gearbox_id) REFERENCES gearbox(id);
ALTER TABLE car ADD CONSTRAINT car_fk2 FOREIGN KEY (body_type_id) REFERENCES body_type(id);
ALTER TABLE car ADD CONSTRAINT car_fk3 FOREIGN KEY (drive_id) REFERENCES drive(id);
ALTER TABLE car ADD CONSTRAINT car_fk4 FOREIGN KEY (engine_type_id) REFERENCES engine_type(id);

ALTER TABLE message ADD CONSTRAINT message_fk0 FOREIGN KEY (ad_id) REFERENCES ad(id);
ALTER TABLE message ADD CONSTRAINT message_fk1 FOREIGN KEY (sender_id) REFERENCES user_account(id);
ALTER TABLE message ADD CONSTRAINT message_fk2 FOREIGN KEY (recipient_id) REFERENCES user_account(id);

ALTER TABLE car_2_car_parameter ADD CONSTRAINT car_2_car_parameter_fk0 FOREIGN KEY (car_parameter_id) REFERENCES car_parameter(id);
ALTER TABLE car_2_car_parameter ADD CONSTRAINT car_2_car_parameter_fk1 FOREIGN KEY (car_id) REFERENCES car(id);

ALTER TABLE calendar ADD CONSTRAINT calendar_fk0 FOREIGN KEY (renter_id) REFERENCES user_account(id);
ALTER TABLE calendar ADD CONSTRAINT calendar_fk1 FOREIGN KEY (car_id) REFERENCES car(id);

ALTER TABLE ad ADD CONSTRAINT ad_fk0 FOREIGN KEY (car_id) REFERENCES car(id);
ALTER TABLE ad ADD CONSTRAINT ad_fk1 FOREIGN KEY (user_account_id) REFERENCES user_account(id);

ALTER TABLE passport ADD CONSTRAINT passport_fk0 FOREIGN KEY (user_account_id) REFERENCES user_account(id);

ALTER TABLE driving_license ADD CONSTRAINT driving_license_fk0 FOREIGN KEY (user_account_id) REFERENCES user_account(id);

ALTER TABLE engine_type ADD CONSTRAINT engine_type_fk0 FOREIGN KEY (fuel_id) REFERENCES fuel(id);