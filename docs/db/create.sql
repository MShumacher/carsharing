
CREATE TABLE model (
	id serial NOT NULL,
	name character varying(50) NOT NULL UNIQUE,
	brand character varying(50) NOT NULL,
	version integer NOT NULL,
	created TIMESTAMP NOT NULL,
	updated TIMESTAMP NOT NULL default now(),
	CONSTRAINT model_pk PRIMARY KEY (id)
);



CREATE TABLE cars_photo (
	id serial NOT NULL,
	car_id integer NOT NULL,
	link character varying(300) NOT NULL UNIQUE,
	version integer NOT NULL,
	created TIMESTAMP NOT NULL,
	updated TIMESTAMP NOT NULL default now(),
	CONSTRAINT cars_photo_pk PRIMARY KEY (id)
);



CREATE TABLE car (
	id serial NOT NULL,
	user_account_id integer,
	model_id integer NOT NULL,
	year integer NOT NULL,
	plate character varying(50) NOT NULL,
	mileage integer,
	seats integer,
	gearbox character varying(50),
	body_type character varying(50),
	drive character varying(50),
	engine_type character varying(50) NOT NULL,
	fuel character varying(50) NOT NULL,
	charge numeric(4,2),
	conditions character varying(1000) NOT NULL,
	insurance character varying(500),
	version integer NOT NULL,
	created TIMESTAMP NOT NULL,
	updated TIMESTAMP NOT NULL default now(),
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
	created TIMESTAMP NOT NULL,
	updated TIMESTAMP NOT NULL default now(),
	CONSTRAINT message_pk PRIMARY KEY (id)
);



CREATE TABLE car_parameter (
	id serial NOT NULL,
	brand character varying(50) UNIQUE,
	gearbox character varying(50) UNIQUE,
	body_type character varying(50) UNIQUE,
	drive character varying(50) UNIQUE,
	engine_type character varying(50) UNIQUE,
	fuel character varying(50) UNIQUE,
	version integer NOT NULL,
	created TIMESTAMP NOT NULL,
	updated TIMESTAMP NOT NULL default now(),
	CONSTRAINT car_parameter_pk PRIMARY KEY (id)
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
	created TIMESTAMP NOT NULL,
	updated TIMESTAMP NOT NULL default now(),
	CONSTRAINT user_account_pk PRIMARY KEY (id)
);



CREATE TABLE parameter (
	id serial NOT NULL,
	name character varying(300) NOT NULL UNIQUE,
	version integer NOT NULL,
	created TIMESTAMP NOT NULL,
	updated TIMESTAMP NOT NULL default now(),
	CONSTRAINT parameter_pk PRIMARY KEY (id)
);



CREATE TABLE car_2_parameter (
	parameter_id integer NOT NULL,
	car_id integer NOT NULL
);



CREATE TABLE calendar (
	id serial NOT NULL,
	renter integer NOT NULL,
	car_id integer NOT NULL,
	start DATE NOT NULL,
	end DATE NOT NULL,
	total_price numeric(12,2) NOT NULL,
	version integer NOT NULL,
	created TIMESTAMP NOT NULL,
	updated TIMESTAMP NOT NULL default now(),
	CONSTRAINT calendar_pk PRIMARY KEY (id)
);



CREATE TABLE ad (
	id serial NOT NULL,
	user_account_id integer NOT NULL,
	address character varying(500) NOT NULL,
	price numeric(12,2) NOT NULL,
	body character varying(1000) NOT NULL,
	active BOOLEAN NOT NULL,
	version integer NOT NULL,
	created TIMESTAMP NOT NULL,
	updated TIMESTAMP NOT NULL default now(),
	CONSTRAINT ad_pk PRIMARY KEY (id)
);



CREATE TABLE passport (
	id serial NOT NULL,
	full_name character varying(100) NOT NULL,
	number character varying(50) NOT NULL UNIQUE,
	issue_place character varying(100) NOT NULL,
	issue_date DATE NOT NULL,
	birth_place character varying(500) NOT NULL,
	birhtday DATE NOT NULL,
	version integer NOT NULL,
	created TIMESTAMP NOT NULL,
	updated TIMESTAMP NOT NULL default now(),
	CONSTRAINT passport_pk PRIMARY KEY (id)
);



CREATE TABLE driving_license (
	id serial NOT NULL,
	number character varying(50) NOT NULL UNIQUE,
	expiration_date DATE NOT NULL,
	categories character varying(50) NOT NULL,
	version integer NOT NULL,
	created TIMESTAMP NOT NULL,
	updated TIMESTAMP NOT NULL default now(),
	CONSTRAINT drive_license_pk PRIMARY KEY (id)
);




ALTER TABLE cars_photo ADD CONSTRAINT cars_photo_fk0 FOREIGN KEY (car_id) REFERENCES car(id);

ALTER TABLE car ADD CONSTRAINT car_fk0 FOREIGN KEY (user_account_id) REFERENCES user_account(id);
ALTER TABLE car ADD CONSTRAINT car_fk1 FOREIGN KEY (model_id) REFERENCES model(id);

ALTER TABLE message ADD CONSTRAINT message_fk0 FOREIGN KEY (ad_id) REFERENCES ad(id);
ALTER TABLE message ADD CONSTRAINT message_fk1 FOREIGN KEY (sender_id) REFERENCES user_account(id);
ALTER TABLE message ADD CONSTRAINT message_fk2 FOREIGN KEY (recipient_id) REFERENCES user_account(id);




ALTER TABLE car_2_parameter ADD CONSTRAINT car_2_parameter_fk0 FOREIGN KEY (parameter_id) REFERENCES parameter(id);
ALTER TABLE car_2_parameter ADD CONSTRAINT car_2_parameter_fk1 FOREIGN KEY (car_id) REFERENCES car(id);

ALTER TABLE calendar ADD CONSTRAINT calendar_fk0 FOREIGN KEY (renter) REFERENCES user_account(id);
ALTER TABLE calendar ADD CONSTRAINT calendar_fk1 FOREIGN KEY (car_id) REFERENCES car(id);

ALTER TABLE ad ADD CONSTRAINT ad_fk0 FOREIGN KEY (id) REFERENCES car(id);
ALTER TABLE ad ADD CONSTRAINT ad_fk1 FOREIGN KEY (user_account_id) REFERENCES user_account(id);

ALTER TABLE passport ADD CONSTRAINT passport_fk0 FOREIGN KEY (id) REFERENCES user_account(id);

ALTER TABLE drive_license ADD CONSTRAINT drive_license_fk0 FOREIGN KEY (id) REFERENCES user_account(id);