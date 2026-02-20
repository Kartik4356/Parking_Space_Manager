
-- 1. Employee Data Table (Tracks logins, logouts, and entries processed)
CREATE TABLE emp_data (
    name VARCHAR(100) NOT NULL,
    time VARCHAR(50) NOT NULL,  -- Storing the formatted time string from Java
    op VARCHAR(50),             -- Operation type (e.g., 'login', 'logout')
    S_done INT                  -- Number of entries done during the session
);

-- 2. Users Table (Handles authentication and employee shifts)
CREATE TABLE users (
    id VARCHAR(50) PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL,
    shift VARCHAR(50)
);

-- 3. Park Table (Tracks currently parked vehicles)
CREATE TABLE park (
    num VARCHAR(50) PRIMARY KEY, -- Car number plate
    type VARCHAR(50) NOT NULL,   -- 'Two_Wheeler' or 'Four_Wheeler'
    time VARCHAR(50) NOT NULL,   -- Time of entry
    date VARCHAR(50) NOT NULL,   -- Date of entry
    mob VARCHAR(20) NOT NULL     -- Contact number (VARCHAR used to prevent 10-digit int limits)
);