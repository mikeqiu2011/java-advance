package com.mosh.generics;

public class User implements Comparable<User>{
    private int points;

    public User(int points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "User{" +
                "points=" + points +
                '}';
    }

    public int getPoints() {
        return points;
    }

    @Override
    public int compareTo(User other) {
        return this.getPoints() - other.getPoints();
    }
}
