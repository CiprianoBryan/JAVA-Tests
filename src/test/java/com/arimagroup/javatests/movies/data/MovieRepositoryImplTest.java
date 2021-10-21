package com.arimagroup.javatests.movies.data;

import com.arimagroup.javatests.movies.model.Genre;
import com.arimagroup.javatests.movies.model.Movie;
import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import javax.sql.DataSource;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

public class MovieRepositoryImplTest {
    private MovieRepositoryImpl movieRepository;
    private DataSource dataSource;

    @Before
    public void setUp() throws Exception {
        dataSource = new DriverManagerDataSource("jdbc:h2:mem:testDB;MODE=MYSQL", "sa", "");
        ScriptUtils.executeSqlScript(dataSource.getConnection(), new ClassPathResource("test-data.sql"));

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        movieRepository = new MovieRepositoryImpl(jdbcTemplate);
    }

    @Test
    public void load_all_movies() throws SQLException {
        Collection<Movie> movies = movieRepository.findAll();
        assertThat(movies, CoreMatchers.is(Arrays.asList(
                new Movie(1, "Dark Knight", 152, Genre.ACTION),
                new Movie(2, "Memento", 113, Genre.THRILLER),
                new Movie(3, "Matrix", 136, Genre.ACTION)
        )));
    }

    @Test
    public void load_movie_by_id() {
        Movie movie = movieRepository.findById(2L);
        assertThat(movie, CoreMatchers.is(new Movie(2, "Memento", 113, Genre.THRILLER)));
    }

    @Test
    public void insert_a_movie() {
        Movie movie = new Movie("Super 8", 112, Genre.THRILLER);
        movieRepository.saveOrUpdate(movie);
        assertThat(movieRepository.findById(4L),
                CoreMatchers.is(new Movie(4, "Super 8", 112, Genre.THRILLER)));
    }

    @After
    public void tearDown() throws Exception {
        // Delete database after test
        final Statement s = dataSource.getConnection().createStatement();
        s.execute("drop all objects delete files");
    }
}