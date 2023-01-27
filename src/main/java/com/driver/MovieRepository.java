package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Repository
public class MovieRepository {
    HashMap<String,Movie> movies=new HashMap<>();
    HashMap<String,Director> directors=new HashMap<>();
    HashMap<String,List<String>> movieDirectorPair=new HashMap<>();

//    public MovieRepository() {
//        this.movies = new HashMap<String,Movie>();
//        this.directors = new HashMap<String,Director>();
//        this.movieDirectorPair = new HashMap<String,List<String>>();
//    }
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
        if(movies.containsKey(movieName) && directors.containsKey(directorName)){
        List<String> MovieList=new ArrayList<>();
            if(movieDirectorPair.containsKey(directorName))
                MovieList=movieDirectorPair.get(directorName);
                MovieList.add(movieName);
                movieDirectorPair.put(directorName,MovieList);
                return "movie pair created succefully";

        }
        else return null;
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
        List<String> moviesbyDirector=new ArrayList<>();
        if(movieDirectorPair.containsKey(name)){
            return moviesbyDirector=movieDirectorPair.get(name);
        }else{
            return null;
        }
    }
    public List<String>  findAllMovies(){
        List<String> findalmovies=new ArrayList<>();
        for(Movie movie: movies.values()){
            findalmovies.add(movie.getName());
        }
        return findalmovies;
    }
    public String deleteDirectorByName(String name){
        List<String> delmovies=new ArrayList<>();
        if(movieDirectorPair.containsKey(name)){
            delmovies=movieDirectorPair.get(name);
            for (String movie:delmovies){
                if(movies.containsKey(movie))
                    movies.remove(movie);
            }
            movieDirectorPair.remove(name);
        }
        if(directors.containsKey(name)){
            directors.remove(name);
        }
        return "All movies of director is deleted";
    }
    public String deleteAllDirectors(){
        List<String>deldire=new ArrayList<>();
        for(String director:movieDirectorPair.keySet()){
            deldire=movieDirectorPair.get(director);
            for(String s:deldire){
                if(movies.containsKey(s))
                    movies.remove(s);
            }
            movieDirectorPair.remove(director);
            directors.remove(director);
        }
        return "Directors and movies are removed Suucessfully";
    }
//    public String getDirectorbtMovie(String name){
//        List<String> list=new ArrayList<>();
//        for (String directornmae:movieDirectorPair.keySet()){
//            if(movieDirectorPair.get(directornmae));
//            for (String s:list){
//                if(s.equals(name))
//                    return directornmae;
//            }
//        }
//        return "not found";
//    }
}
