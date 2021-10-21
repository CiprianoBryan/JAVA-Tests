package com.arimagroup.javatests.movies.service;

import com.arimagroup.javatests.movies.data.MovieRepository;
import com.arimagroup.javatests.movies.model.Genre;
import com.arimagroup.javatests.movies.model.Movie;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class MovieServiceTest {
    private MovieService movieService;

    @Before
    public void setUp() throws Exception {
        MovieRepository movieRepository = Mockito.mock(MovieRepository.class);
        Mockito.when(movieRepository.findAll()).thenReturn(
                Arrays.asList(
                        new Movie(1, "Dark Night", 152, Genre.ACTION),
                        new Movie(2, "Memento", 113, Genre.THRILLER),
                        new Movie(3, "Super 8", 112, Genre.THRILLER),
                        new Movie(4, "Scream", 111, Genre.HORROR),
                        new Movie(5, "Home Alone", 103, Genre.COMEDY),
                        new Movie(6, "Matrix", 136, Genre.ACTION)
                )
        );

        movieService = new MovieService(movieRepository);
    }

    @Test
    public void return_movies_by_genre() {
        Collection<Movie> movies = movieService.findMoviesByGenre(Genre.THRILLER);
        assertThat(getMovieIds(movies), CoreMatchers.is(Arrays.asList(2, 3)));
    }

    @Test
    public void return_movies_by_duration() {
        Collection<Movie> movies = movieService.findByLength(113);
        assertThat(getMovieIds(movies), CoreMatchers.is(Arrays.asList(2, 3, 4, 5)));
    }

    public List<Integer> getMovieIds(Collection<Movie> movies) {
        return movies.stream().map(Movie::getId).collect(Collectors.toList());
    }
}