package com.borschevskydenis.movieshelper.ResultsFromServer;

import java.util.List;

public class Genres {
    private List<GenresBean> genres;

    public List<GenresBean> getGenres() {
        return genres;
    }

    public void setGenres(List<GenresBean> genres) {
        this.genres = genres;
    }

    public static class GenresBean {
        /**
         * id : 28
         * name : боевик
         */

        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
