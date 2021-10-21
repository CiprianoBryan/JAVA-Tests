package com.arimagroup.javatests.movies.data;

import com.arimagroup.javatests.movies.model.Genre;
import com.arimagroup.javatests.movies.model.Movie;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import javax.sql.DataSource;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

public class MovieRepositoryImplTest {
    @Test
    public void load_all_movies() throws SQLException {
        DataSource dataSource = new DriverManagerDataSource("jdbc:h2:mem:testDB;MODE=MYSQL", "sa", "");
        ScriptUtils.executeSqlScript(dataSource.getConnection(), new ClassPathResource("test-data.sql"));

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        MovieRepositoryImpl movieRepository = new MovieRepositoryImpl(jdbcTemplate);

        Collection<Movie> movies = movieRepository.findAll();
        assertThat(movies, CoreMatchers.is(Arrays.asList(
                new Movie(1, "Dark Knight", 152, Genre.ACTION),
                new Movie(2, "Memento", 113, Genre.THRILLER),
                new Movie(3, "Matrix", 136, Genre.ACTION)
        )));
    }
}