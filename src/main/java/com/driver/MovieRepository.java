package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class MovieRepository {
    HashMap<String,Movie> movies=new HashMap<>();
    HashMap<String,Director> directors=new HashMap<>();
    HashMap<String,List<String>> movieDirectorPair=new HashMap<>();
    List<String> MovieList=new ArrayList<>();
    public String addMovie(Movie movie){
        if(!movies.containsKey(movie.getName())){
            movies.put(movie.getName(),movie);
            return "Movie added successfully";
        }else {
            return null;
        }
    }
    public String addDirector(Director director){
        if(!directors.containsKey(director.getName())){
            directors.put(director.getName(),director);
            return "Director added successfully";
        }else {
            return null;
        }
    }
    public String addMovieDirectorPair(String movieName,String directorName){
        if(movieDirectorPair.containsKey(directorName)){
            List<String> moviesbydirector=movieDirectorPair.get(directorName);
            moviesbydirector.add(movieName);
            movieDirectorPair.put(directorName,moviesbydirector);
            return "Moive is added in Exsting Director Pair";
        }else{
             List<String> temp=new ArrayList<>();
             temp.add(movieName);
            movieDirectorPair.put(directorName,temp);
            return "Movie and director is added succesfully";
        }

    }
    public Movie getMovieByName(String name){
        if(movies.containsKey(name)){
            return movies.get(name);
        }
        else return null;
    }
    public Director getDirectorByName(String name){
        if(directors.containsKey(name)){
           return directors.get(name);
        }
        else return null;
    }
    public List<String> getMoviesByDirectorName(String name){
        if(movieDirectorPair.containsKey(name)){
            return movieDirectorPair.get(name);
        }else{
            return null;
        }
    }
    public List<String>  findAllMovies(){
        for(Movie movie: movies.values()){
            MovieList.add(movie.getName());
        }
        return MovieList;
    }
    public String deleteDirectorByName(String name){
        if(movieDirectorPair.containsKey(name)){
            movieDirectorPair.remove(name);
            return "Director and ist movie deleted succesfully";
        }
        else return null;
    }
    public String deleteAllDirectors(){
        movieDirectorPair.clear();
        return "Directors and movies are removed Suucessfully";
    }
}
