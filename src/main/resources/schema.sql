DROP TABLE IF EXISTS Post;

CREATE TABLE Post (
  id INT NOT NULL,
  user_id INT NOT NULL,
  title varchar(255) NOT NULL,
  body varchar(255) NOT NULL,
  version INT,
  PRIMARY KEY (id)
);