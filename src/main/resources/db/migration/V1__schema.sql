CREATE TABLE users (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    email varchar(255) DEFAULT NULL,
    first_name varchar(255) DEFAULT NULL,
    last_name varchar(255) DEFAULT NULL,
    password varchar(100) DEFAULT NULL,
    salary decimal(15,2) DEFAULT NULL,
    vacation_balance decimal(15,2) DEFAULT NULL,
    annual_bonus decimal(15,2) DEFAULT NULL,
    manager_id bigint(20) DEFAULT NULL,
    created_at timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    UNIQUE KEY uk_users_email (email),
	CONSTRAINT fk_users_manager_id FOREIGN KEY (manager_id) REFERENCES users (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


CREATE TABLE roles (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    name varchar(60) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY uk_roles_name (name)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;


CREATE TABLE user_roles (
  user_id bigint(20) NOT NULL,
  role_id bigint(20) NOT NULL,
  PRIMARY KEY (user_id,role_id),
  KEY fk_user_roles_role_id (role_id),
  CONSTRAINT fk_user_roles_role_id FOREIGN KEY (role_id) REFERENCES roles (id),
  CONSTRAINT fk_user_roles_user_id FOREIGN KEY (user_id) REFERENCES users (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE salary_history (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    employee_id bigint(20) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_salary_history_employee_id FOREIGN KEY (employee_id) REFERENCES users (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- For a many to many relationship
-- CREATE TABLE manager_employee (
--     manager bigint(20) NOT NULL,
--     employee bigint(20) NOT NULL,
--     PRIMARY KEY (user_id,role_id),
--     KEY fk_user_roles_role_id (role_id),
--
--     CONSTRAINT fk_manager_employee_manger FOREIGN KEY (manager) REFERENCES users (id),
--     CONSTRAINT fk_manager_employee_employee FOREIGN KEY (employee) REFERENCES users (id),
--
--
-- ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
