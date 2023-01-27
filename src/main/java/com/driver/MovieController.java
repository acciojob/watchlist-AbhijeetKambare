package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    MovieService movieService;
    @PostMapping("/add-movie")
    public ResponseEntity addMovie(@RequestBody Movie movie){
        String response= movieService.addMovie(movie);
        if(response==null) return new ResponseEntity<>("It is Already Present", HttpStatus.ALREADY_REPORTED);
        else return new ResponseEntity<>(response,HttpStatus.CREATED);
    }
    @PostMapping("/add-director")
    public ResponseEntity addDirector(@RequestBody Director director){
        String resopnse= movieService.addDirector(director);
        if(resopnse==null) return new ResponseEntity<>(resopnse,HttpStatus.ALREADY_REPORTED);
        else return new ResponseEntity<>(resopnse,HttpStatus.CREATED);
    }
    @PutMapping ("/add-movie-director-pair")
    public ResponseEntity addMovieDirectorPair(@RequestParam("m") String movieName,@RequestParam("d") String directorName){
        movieService.addMovieDirectorPair(movieName,directorName);
        return new ResponseEntity<>("movie pair successfuly created",HttpStatus.CREATED);
    }
    @GetMapping("/get-movie-by-name/{name}")
    public ResponseEntity  getMovieByName(@PathVariable("name") String movieName){
        Movie movie=movieService.getMovieByName(movieName);
        if(movie==null) return new ResponseEntity<>("Inavlid Information",HttpStatus.NOT_FOUND);
        else return new ResponseEntity<>(movie,HttpStatus.FOUND);
    }
    @GetMapping("/get-director-by-name/{name}")
    public ResponseEntity getDirectorByName(@PathVariable("name") String directorName){
        Director director=movieService.getDirectorByName(directorName);
        if(director==null) return new ResponseEntity<>("Invalid information",HttpStatus.NOT_FOUND);
        else return new ResponseEntity<>(director,HttpStatus.FOUND);
    }
    @GetMapping("/get-movies-by-director-name/{director}")
    public ResponseEntity  getMoviesByDirectorName(@PathVariable("director") String directorName){
        List<String> response=movieService.getMoviesByDirectorName(directorName);
        if(response==null) return new ResponseEntity<>("directorName not found",HttpStatus.NOT_FOUND);
        else return new ResponseEntity<>(response,HttpStatus.FOUND);
    }
    @GetMapping("/get-all-movies")
    public ResponseEntity  findAllMovies(){
        List<String> response=movieService.findAllMovies();
        return new ResponseEntity<>(response,HttpStatus.FOUND);
    }
    @DeleteMapping("/delete-director-by-name")
    public ResponseEntity deleteDirectorByName(@RequestParam("Dn") String directorName){
        String response=movieService.deleteDirectorByName(directorName);
        if(response==null) return new ResponseEntity<>("Director dont have any movie",HttpStatus.NOT_FOUND);
        else return new ResponseEntity<>(response,HttpStatus.FOUND);
    }
    @DeleteMapping("/delete-all-directors")
    public ResponseEntity deleteAllDirectors(){
        String response=movieService.deleteAllDirectors();
        return new ResponseEntity<>(response,HttpStatus.GONE);
    }
}
