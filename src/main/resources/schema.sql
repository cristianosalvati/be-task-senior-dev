CREATE SEQUENCE hibernate_sequence;

-- Tabella per gli employee (dipendenti)
CREATE TABLE Employee (
    employee_id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    role VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    date_insert TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    date_delete TIMESTAMP DEFAULT NULL,
    date_update TIMESTAMP DEFAULT 'admin',
    user_update VARCHAR(50) DEFAULT NULL
);

-- Enumerazione per lo stato del task
CREATE TYPE TaskStatus AS ENUM ('COMPLETED', 'PENDING', 'STARTED', 'CLOSED');

-- Tabella per i task
CREATE TABLE Task (
    task_id INT PRIMARY KEY AUTO_INCREMENT,
    manager_id INT,
    title VARCHAR(100) NOT NULL,
    description TEXT,
    due_date DATE,
    task_status TaskStatus DEFAULT 'pending',
    priority INT DEFAULT 0,
    date_insert TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    date_delete TIMESTAMP DEFAULT NULL,
    date_update TIMESTAMP DEFAULT NULL,
    user_update VARCHAR(50) DEFAULT 'admin',
    FOREIGN KEY (manager_id) REFERENCES Employee(employee_id)
);

-- Tabella di associazione tra employee e task per la gestione delle assegnazioni
CREATE TABLE Employee_Task (
    employee_id INT,
    task_id INT,
    date_insert TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    date_delete TIMESTAMP DEFAULT NULL,
    user_update VARCHAR(50) DEFAULT 'admin',
    PRIMARY KEY (employee_id, task_id, date_insert),
    FOREIGN KEY (employee_id) REFERENCES Employee(employee_id) ON DELETE CASCADE,
    FOREIGN KEY (task_id) REFERENCES Task(task_id) ON DELETE CASCADE
);
