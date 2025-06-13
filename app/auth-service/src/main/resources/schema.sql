CREATE TABLE IF NOT EXISTS `users` (
  id INT NOT NULL PRIMARY KEY,
  username VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  role VARCHAR(50) NOT NULL,
  -- Khai báo unique index
  UNIQUE INDEX idx_username (username)
);
