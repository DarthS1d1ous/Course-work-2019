package com.borschevskydenis.movieshelper.ResultsFromServer;

import java.util.List;

public class Videos {

    /**
     * id : 12
     * results : [{"id":"533ec651c3a3685448000010","iso_639_1":"en","iso_3166_1":"US","key":"SPHfeNgogVs","name":"Finding Nemo 3D Trailer","site":"YouTube","size":720,"type":"Trailer"},{"id":"5a80bbcec3a36818950255a0","iso_639_1":"en","iso_3166_1":"US","key":"-aAHfOQ7Rbo","name":"Finding Nemo (2003) Teaser","site":"YouTube","size":360,"type":"Teaser"},{"id":"5a80bbea0e0a262aa6027273","iso_639_1":"en","iso_3166_1":"US","key":"WpOXa4uqqfA","name":"Finding Nemo (2003) Trailer 1","site":"YouTube","size":360,"type":"Trailer"},{"id":"5a80bc1ac3a36818a7026cc8","iso_639_1":"en","iso_3166_1":"US","key":"ZS_8btMjx2U","name":"Finding Nemo (2003) Trailer 2","site":"YouTube","size":360,"type":"Trailer"},{"id":"5a80bc280e0a262aa60272b1","iso_639_1":"en","iso_3166_1":"US","key":"TOQsDiEc7nk","name":"Finding Nemo (2003) Trailer 3","site":"YouTube","size":360,"type":"Trailer"}]
     */

    private int id;
    private List<ResultsBean> results;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * id : 533ec651c3a3685448000010
         * iso_639_1 : en
         * iso_3166_1 : US
         * key : SPHfeNgogVs
         * name : Finding Nemo 3D Trailer
         * site : YouTube
         * size : 720
         * type : Trailer
         */

        private String id;
        private String iso_639_1;
        private String iso_3166_1;
        private String key;
        private String name;
        private String site;
        private int size;
        private String type;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIso_639_1() {
            return iso_639_1;
        }

        public void setIso_639_1(String iso_639_1) {
            this.iso_639_1 = iso_639_1;
        }

        public String getIso_3166_1() {
            return iso_3166_1;
        }

        public void setIso_3166_1(String iso_3166_1) {
            this.iso_3166_1 = iso_3166_1;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSite() {
            return site;
        }

        public void setSite(String site) {
            this.site = site;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
