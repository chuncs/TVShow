package com.udacity.classroom.yongchun.tvshow.database;

import java.util.List;

public class Responses {

    private int page;
    private int total_results;
    private int total_pages;
    private List<Result> results;

    private class Result {
        private String name;
        private int id;
        private String poster_path;
    }

}
