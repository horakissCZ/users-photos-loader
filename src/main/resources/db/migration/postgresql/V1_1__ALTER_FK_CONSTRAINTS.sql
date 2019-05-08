ALTER TABLE user_photos
DROP CONSTRAINT users1_id_fkey;

ALTER TABLE user_photos
DROP CONSTRAINT photos1_id_fkey;

ALTER TABLE reactions
DROP CONSTRAINT fb_link_fkey;

ALTER TABLE user_photos
ADD CONSTRAINT users1_id_fkey FOREIGN KEY (user_fb_id)
    REFERENCES users (user_fb_id) MATCH SIMPLE
    ON DELETE CASCADE ON UPDATE CASCADE;
    
ALTER TABLE user_photos
ADD CONSTRAINT photos1_id_fkey FOREIGN KEY (fb_link)
    REFERENCES photos (fb_link) MATCH SIMPLE
    ON DELETE CASCADE ON UPDATE CASCADE;
    
ALTER TABLE reactions
ADD CONSTRAINT fb_link_fkey FOREIGN KEY (fb_link_fk)
    REFERENCES photos (fb_link) MATCH SIMPLE
    ON DELETE CASCADE ON UPDATE CASCADE;
