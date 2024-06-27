package dev.danvega.sb3w.post;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PostJdbcClientRepository {

    private static final Logger log = LoggerFactory.getLogger(PostRepository.class);
    private final JdbcClient jdbcClient;

    public PostJdbcClientRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Post> findAll() {
        return jdbcClient.sql("SELECT * FROM post")
                .query(Post.class)
                .list();
    }

    public Optional<Post> findById(String id) {
        return jdbcClient.sql("SELECT id,user_id,title,body FROM post WHERE id = :id")
                .param("id", id)
                .query(Post.class)
                .optional();
    }

    public void create(Post post) {
        int update = jdbcClient.sql("INSERT INTO post (id, user_id, title, body) VALUES (?, ?, ?, ?)")
                .params(List.of(post.id(), post.userId(), post.title(), post.body()))
                .update();
        log.info("Inserted {} rows", update);
    }

    public void update(Post post, String id) {
        var updated = jdbcClient.sql("UPDATE post SET user_id = ?, title = ?, body = ? where id = ?")
                .params(List.of(post.userId(),post.title(),post.body(), id))
                .update();

        log.info("Updated {} row(s)", updated);
    }

    public void delete(String id) {
        var updated = jdbcClient.sql("DELETE from post where id = :id")
                .param("id", id)
                .update();

        log.info("Deleted {} row(s)", updated);
    }

    public List<Post> findByUserId(Integer userId) {
        return jdbcClient.sql("SELECT * FROM post WHERE user_id = :userId")
                .param("userId", userId)
                .query(Post.class)
                .list();
    }

    public void saveAllPosts(List<Post> posts) {
        posts.forEach(this::create);
    }
}
