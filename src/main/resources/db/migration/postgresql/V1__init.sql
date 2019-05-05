CREATE TABLE users (
  user_fb_id          VARCHAR PRIMARY KEY,
  name                VARCHAR NOT NULL,
  gender              VARCHAR,
  profile_picture_url VARCHAR
);

CREATE TABLE photos (
  fb_link        VARCHAR PRIMARY KEY,
  image_file_url VARCHAR,
  album_name     VARCHAR
);

CREATE TABLE user_photos (
  user_fb_id VARCHAR,
  fb_link    VARCHAR,

  PRIMARY KEY(user_fb_id, fb_link),

  CONSTRAINT users1_id_fkey FOREIGN KEY (user_fb_id)
    REFERENCES users (user_fb_id) MATCH SIMPLE
    ON DELETE CASCADE,
  CONSTRAINT photos1_id_fkey FOREIGN KEY (fb_link)
    REFERENCES photos (fb_link) MATCH SIMPLE
    ON DELETE CASCADE
);

CREATE TABLE reactions (
  id               SERIAL  PRIMARY KEY,
  type             VARCHAR NOT NULL,
  fb_link_fk       VARCHAR,
  num_of_reactions INTEGER,
    
  CONSTRAINT fb_link_fkey FOREIGN KEY (fb_link_fk)
    REFERENCES photos (fb_link) MATCH SIMPLE
    ON DELETE CASCADE
);
