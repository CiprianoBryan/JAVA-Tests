package com.arimagroup.javatests.movies.data;

import com.arimagroup.javatests.movies.model.Movie;

import java.util.Collection;

public interface MovieRepository {
    Movie findById(Long id);
    Collection<Movie> findAll();
    void saveOrUpdate(Movie movie);
}
