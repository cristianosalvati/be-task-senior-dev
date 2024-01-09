-- Employee  
INSERT INTO Employee (employee_id, first_name, last_name, role ,email, date_insert, date_delete, date_update, user_update)
VALUES
    (1, 'John', 'Doe', 'Developer', 'john.doe@example.com', DEFAULT, NULL, NULL, DEFAULT),
    (2, 'Alice', 'Smith', 'Senior developer', 'alice.smith@example.com', DEFAULT, NULL, NULL, DEFAULT),
    (3, 'Bob', 'Johnson', 'Architect', 'bob.johnson@example.com', DEFAULT, NULL, NULL, DEFAULT);

--  Task  
INSERT INTO Task (task_id, manager_id, title, description, due_date, task_status, date_insert, date_delete, date_update, user_update)
VALUES
    (1, 2, 'Task 1', 'Description for Task 1', '2023-12-01', 'PENDING', DEFAULT, NULL, NULL, DEFAULT),
    (2, 3, 'Task 2', 'Description for Task 2', '2023-12-15', 'STARTED', DEFAULT, NULL, NULL, DEFAULT),
    (3, 3, 'Task 3', 'Description for Task 3', '2023-12-30', 'PENDING', DEFAULT, NULL, NULL, DEFAULT),
    (4, 3, 'Task 4', 'Description for Task 4', '2024-01-06', 'PENDING', DEFAULT, NULL, NULL, DEFAULT);

-- Association between employees and tasks
INSERT INTO Employee_Task (employee_id, task_id, date_insert, user_update) VALUES
    (1, 1, DEFAULT, DEFAULT), -- John assign Task 1
    (1, 2, DEFAULT, DEFAULT), -- John assign Task 2
    (2, 3, DEFAULT, DEFAULT), -- Alice assign Task 3
    (3, 4, DEFAULT, DEFAULT); -- Bob assign Task 4
