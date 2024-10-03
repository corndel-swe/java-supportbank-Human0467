package com.corndel.supportbank.exercises;

import kong.unirest.GetRequest;
import kong.unirest.Unirest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.ArrayList;

/**
 * This class represents a Pokemon. It uses Java's record syntax to
 * automatically generate a class with getters and utility methods.
 * See this url for more info:
 * https://www.baeldung.com/java-record-keyword
 */
@JsonIgnoreProperties(ignoreUnknown = true)
record Pokemon(Integer id, String name, Integer height, Integer weight) {
}

public class PokeAPI {
  /**
   * Get a Pokemon by its name.
   *
   * Makes a GET request to the PokeAPI, and uses Jackson to parse the JSON
   * response into a Pokemon object.
   *
   * @param name The name of the Pokemon to retrieve.
   * @return The Pokemon object.
   */
  public static Pokemon getPokemonByName(String name) throws Exception {
    // Create the url by appending the name to the base url
    String url = "https://pokeapi.co/api/v2/pokemon/{name}";

    // Make a GET request to the url
    // Hint: Use Unirest.get()
    String response = Unirest.get(url)
            .routeParam("name", name)
            .asString()
            .getBody();

    // Parse the response body into a Pokemon object
    // Hint: Use Jackson's ObjectMapper to map the response body to Pokemon.class
    ObjectMapper objectMapper = new ObjectMapper();

    return objectMapper.readValue(response, Pokemon.class);

    /* why doesn't this work? I added
    * https://mvnrepository.com/artifact/com.konghq/unirest-objectmapper-jackson/4.2.9
    * to the pom.xml and reloaded the project
    */

//    Pokemon pokemon = Unirest.get(url)
//            .routeParam("name", name)
//            .asObject(Pokemon.class)
//            .getBody();
//
//    return pokemon;
  }

//  public static ArrayList<Pokemon> getAllPokemon(){
//    // Create the url by appending the name to the base url
//    String url = "https://pokeapi.co/api/v2/pokemon/";
//
//    // Make a GET request to the url
//    // Hint: Use Unirest.get()
//    String response = Unirest.get(url)
//            .asString()
//            .getBody();
//
//    ArrayList<Pokemon> pokeArray = new ArrayList<>();
//
//    System.out.println(response);
//    ObjectMapper objectMapper = new ObjectMapper();
//    try {
//      var tree = objectMapper.readTree(response);
//      var pokemons = tree
//              .get("results");
//      for (var pokemon : pokemons) {
//
//      }
//    }catch(Exception e){
//      e.printStackTrace();
//    }
//    return new ArrayList<Pokemon>();
//  }

  /**
   * For debugging purposes..
   */
  public static void main(String[] args) {
    try {
      //Pokemon pokemon = getPokemonByName("pikachu");
      ArrayList<Pokemon> pokemon = getAllPokemon();
      //System.out.println(pokemon);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
