create schema if not exists ${schema};
CREATE TABLE IF NOT EXISTS ${schema}.employees(
    employee_id varchar(45) NOT NULL,
    first_name character varying(200),
    last_name character varying(250) NOT NULL,
    email character varying(25) NOT NULL,
    phone_number character varying(20),
    salary numeric(8,2),
    create_dttm timestamp without time zone,
    update_dttm timestamp without time zone,
    CONSTRAINT emp_emp_id_pk PRIMARY KEY (employee_id)
);


CREATE TABLE IF NOT EXISTS ${schema}.address (
	address_id varchar(45) NOT NULL,
	employee_id varchar(45) NOT NULL,
	street varchar(2000) NULL,
	city varchar(250) NOT NULL,
	state varchar(250) NOT NULL,
	zipcode varchar(20) NULL,
	create_dttm timestamp NULL,
	update_dttm timestamp NULL,
	CONSTRAINT add_add_id_pk PRIMARY KEY (address_id),
	CONSTRAINT address_employee_id_fkey FOREIGN KEY (employee_id) REFERENCES ${schema}.employees(employee_id)
);


