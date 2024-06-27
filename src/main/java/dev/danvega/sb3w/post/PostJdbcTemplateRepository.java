package dev.danvega.sb3w.post;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostJdbcTemplateRepository {

    private final JdbcTemplate jdbcTemplate;

    public PostJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Post post) {
        jdbcTemplate.update(
                "INSERT INTO post (id, user_id, title, body) VALUES (?, ?, ?, ?)",
                post.id(),
                post.userId(),
                post.title(),
                post.body()
        );
    }

    public List<Post> findAll() {
        return jdbcTemplate.query(
                "SELECT * FROM post",
                (rs, rowNum) -> new Post(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getString("title"),
                        rs.getString("body"),
                        null
                )
        );
    }

    public Post findById(Integer id) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM post WHERE id = ?",
                (rs, rowNum) -> new Post(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getString("title"),
                        rs.getString("body"),
                        null
                ),
                id
        );
    }
}
