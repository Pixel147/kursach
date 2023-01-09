CREATE TABLE user (
	id bigint NOT NULL,
	username varchar(20) NOT NULL,
	email varchar(50) NOT NULL,
	phone varchar(50) NOT NULL,
	name varchar(50) NOT NULL,
	surname varchar(50) NOT NULL,
	description varchar(255) NOT NULL,
    CONSTRAINT PK_USER PRIMARY KEY CLUSTERED(id ASC) 
);
CREATE TABLE client (
	id bigint NOT NULL,
	id_user bigint NOT NULL,
	birthday date NOT NULL,
    CONSTRAINT PK_CLIENT PRIMARY KEY CLUSTERED(id ASC) 
);
CREATE TABLE example (
	id bigint NOT NULL,
	title varchar(50) NOT NULL,
	description varchar(255) NOT NULL,
	image blob NOT NULL,
    CONSTRAINT PK_EXAMPLE PRIMARY KEY CLUSTERED(id ASC) 
);
CREATE TABLE employee (
	id bigint NOT NULL,
	id_user bigint NOT NULL,
	id_review bigint NOT NULL,
    CONSTRAINT PK_EMPLOYEE PRIMARY KEY CLUSTERED(id ASC)
);
CREATE TABLE services (
	id bigint NOT NULL,
	name varchar(50) NOT NULL,
	description varchar(255) NOT NULL,
	price bigint NOT NULL,
    CONSTRAINT PK_SERVICES PRIMARY KEY CLUSTERED(id ASC) 
);
CREATE TABLE review (
	id bigint NOT NULL,
	id_client bigint NOT NULL,
	avg_stars float NOT NULL,
	description varchar(255) NOT NULL,
    CONSTRAINT PK_REVIEW PRIMARY KEY CLUSTERED(id ASC) 
);
CREATE TABLE employee_example (
	id_employee bigint NOT NULL,
	id_example bigint NOT NULL
);
CREATE TABLE services_employee (
	id_employee bigint NOT NULL,
	id_services bigint NOT NULL
);

ALTER TABLE client ADD CONSTRAINT client_fk0 FOREIGN KEY (id_user) REFERENCES user(id)ON UPDATE CASCADE;
ALTER TABLE employee ADD CONSTRAINT employee_fk0 FOREIGN KEY (id_user) REFERENCES user(id)ON UPDATE CASCADE;
ALTER TABLE employee ADD CONSTRAINT employee_fk1 FOREIGN KEY (id_review) REFERENCES review(id)ON UPDATE CASCADE;
ALTER TABLE review ADD CONSTRAINT review_fk0 FOREIGN KEY (id_client) REFERENCES client(id)ON UPDATE CASCADE;
ALTER TABLE employee_example ADD CONSTRAINT employee_example_fk0 FOREIGN KEY (id_employee) REFERENCES employee(id)ON UPDATE CASCADE;
ALTER TABLE employee_example ADD CONSTRAINT employee_example_fk1 FOREIGN KEY (id_example) REFERENCES example(id)ON UPDATE CASCADE;
ALTER TABLE services_employee ADD CONSTRAINT services_employee_fk0 FOREIGN KEY (id_employee) REFERENCES employee(id)ON UPDATE CASCADE;
ALTER TABLE services_employee ADD CONSTRAINT services_employee_fk1 FOREIGN KEY (id_services) REFERENCES services(id)ON UPDATE CASCADE;