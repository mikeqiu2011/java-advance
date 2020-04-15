package com.mosh.streams;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Movie {
    private String title;
    private int likes;

    public Genre getGenre() {
        return genre;
    }

    private Genre genre;

    private static List<Movie> movies = Arrays.asList(
            new Movie("b", 30, Genre.ACTION),
            new Movie("a", 15, Genre.ACTION),
            new Movie("c", 20, Genre.TRILLER)
    );

    public String getTitle() {
        return title;
    }

    public Movie(String title, int likes, Genre genre) {
        this.title = title;
        this.likes = likes;
        this.genre = genre;
    }

    public int getLikes() {
        return likes;
    }

    @Override
    public String toString() {
        return title + ": " + likes;
    }

    private static void imperativePrint(List<Movie> movies){
        movies.forEach(System.out::println);

        //imperative programming
        int count=0;
        for (Movie movie : movies){

            if (movie.getLikes() > 10)
                count++;
        }
        System.out.println("count is: " + count);
    }

    //declarative
    private static void declarativePrint(List<Movie> movies){
//        long count = movies.stream()
//                        .filter(movie -> movie.getLikes() > 10)
//                        .count();
//
//        System.out.println(count);

        movies.stream()
                .map(Movie::getTitle)
                .forEach(System.out::println);
    }

    private static void showArrayStream(){
//        int[] numbers = {1,2,3};
//        long count = Arrays.stream(numbers).filter(x -> x>2).count();
//        System.out.println(count);
//
//        Arrays.stream(numbers).forEach(num -> System.out.println("," + num));

//        Stream.of(1,2,3,4).forEach(System.out::println);

//        Stream.generate(Math::random)
//                .limit(10)
//                .forEach(System.out::println);

//        Stream.iterate(1, num -> num+1)
//                .limit(10)
//                .forEach(System.out::println);

        List<Integer> list1 = Arrays.asList(1,2,3);
        List<Integer> list2 = Arrays.asList(4,5,6);

        Stream.of(list1,list2)
                .flatMap(list -> list.stream())
                .forEach(System.out::println);
    }

    public static void main(String[] args) {

//        movies.stream()
//                .map(Movie::getLikes)
//                .distinct()
//                .peek(num -> System.out.println("filtered: " + num))
//                .sorted(Comparator.reverseOrder())
//                .forEach(System.out::println);

//        System.out.println(movies.stream()
//                .min(Comparator.comparing(Movie::getLikes))
//                .get()
//        );

//        int reduce = movies.stream()
//                .map(Movie::getLikes)
//                .reduce(0, Integer::sum);
//        String result = movies.stream()
//                .filter(m -> m.getLikes() > 15)
//                .map(m -> m.getTitle())
//                .collect(Collectors.joining(", "));
//
//        System.out.println(result);

//        Map<Genre, String> collect =
//                movies.stream().collect(Collectors.groupingBy(
//                                        Movie::getGenre,
//                                        Collectors.mapping(
//                                                Movie::getTitle,
//                                                Collectors.joining(", "))));
//
//        System.out.println(collect);

//        Map<Boolean, List<Movie>> collect =
//                movies.stream().collect(Collectors.partitioningBy(m -> m.getLikes() > 15));
//
//        System.out.println(collect);

        IntStream.rangeClosed(1,5).forEach(System.out::println);
//                .collect(Collectors.toMap(Movie::getTitle, Function.identity()));

//        System.out.println(collect);
//        collect.entrySet().forEach(System.out::println);


//        imperativePrint(movies);
//        declarativePrint(movies);
//        showArrayStream();






    }
}
