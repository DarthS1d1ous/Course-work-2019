package com.borschevskydenis.movieshelper.ResultsFromServer;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

//import androidx.room.Entity;
//import androidx.room.PrimaryKey;

public class MovieSearch {
    /**
     * page : 1
     * total_results : 244
     * total_pages : 13
     * results : [{"vote_count":1011,"id":984,"video":false,"vote_average":7.5,"title":"Dirty Harry","popularity":13.621,"poster_path":"/4j2tBAusIVev4ZaScncIHknP7eV.jpg","original_language":"en","original_title":"Dirty Harry","genre_ids":[28,80,53],"backdrop_path":"/t3OZS8yMs0NL4YgWWc04eXHTn1X.jpg","adult":false,"overview":"When a madman dubbed 'Scorpio' terrorizes San Francisco, hard-nosed cop, Harry Callahan \u2013 famous for his take-no-prisoners approach to law enforcement \u2013 is tasked with hunting down the psychopath. Harry eventually collars Scorpio in the process of rescuing a kidnap victim, only to see him walk on technicalities. Now, the maverick detective is determined to nail the maniac himself.","release_date":"1971-12-21"},{"vote_count":496,"id":25941,"video":false,"vote_average":6.8,"title":"Harry Brown","popularity":9.896,"poster_path":"/68V1ClrCT7ik7UDiAkIDliRaAXf.jpg","original_language":"en","original_title":"Harry Brown","genre_ids":[53,80,18,28],"backdrop_path":"/szj3uqOGDu8Uwvwo0Si42BCxI04.jpg","adult":false,"overview":"An elderly ex-serviceman and widower looks to avenge his best friend's murder by doling out his own form of justice.","release_date":"2009-11-11"},{"vote_count":13280,"id":671,"video":false,"vote_average":7.8,"title":"Harry Potter and the Philosopher's Stone","popularity":51.006,"poster_path":"/dCtFvscYcXQKTNvyyaQr2g2UacJ.jpg","original_language":"en","original_title":"Harry Potter and the Philosopher's Stone","genre_ids":[12,14,10751],"backdrop_path":"/pZIiPOoNhhzVpBuVEpDK7vbBz4l.jpg","adult":false,"overview":"Harry Potter has lived under the stairs at his aunt and uncle's house his whole life. But on his 11th birthday, he learns he's a powerful wizard -- with a place waiting for him at the Hogwarts School of Witchcraft and Wizardry. As he learns to harness his newfound powers with the help of the school's kindly headmaster, Harry uncovers the truth about his parents' deaths -- and about the villain who's to blame.","release_date":"2001-11-16"},{"vote_count":11228,"id":672,"video":false,"vote_average":7.7,"title":"Harry Potter and the Chamber of Secrets","popularity":35.6,"poster_path":"/sdEOH0992YZ0QSxgXNIGLq1ToUi.jpg","original_language":"en","original_title":"Harry Potter and the Chamber of Secrets","genre_ids":[12,14,10751],"backdrop_path":"/xACx8htL9Ubz1qGe0gU2ggHonxV.jpg","adult":false,"overview":"Ignoring threats to his life, Harry returns to Hogwarts to investigate \u2013 aided by Ron and Hermione \u2013 a mysterious series of attacks.","release_date":"2002-11-13"},{"vote_count":331,"id":2639,"video":false,"vote_average":7.4,"title":"Deconstructing Harry","popularity":7.045,"poster_path":"/krKRthefSZlUjnzDvN4isqty0R7.jpg","original_language":"en","original_title":"Deconstructing Harry","genre_ids":[35,18],"backdrop_path":"/1KC0Td0TfHuHEIzZ0dTGsEOkYcs.jpg","adult":false,"overview":"This film tells the story of a successful writer called Harry Block, played by Allen himself, who draws inspiration from people he knows in real-life, and from events that happened to him, sometimes causing these people to become alienated from him as a result.","release_date":"1997-12-12"},{"vote_count":10602,"id":674,"video":false,"vote_average":7.7,"title":"Harry Potter and the Goblet of Fire","popularity":27.745,"poster_path":"/6sASqcdrEHXxUhA3nFpjrRecPD2.jpg","original_language":"en","original_title":"Harry Potter and the Goblet of Fire","genre_ids":[12,14,10751],"backdrop_path":"/gzKW3emulMxIHzuXxZoyDB1lei9.jpg","adult":false,"overview":"Harry starts his fourth year at Hogwarts, competes in the treacherous Triwizard Tournament and faces the evil Lord Voldemort. Ron and Hermione help Harry manage the pressure \u2013 but Voldemort lurks, awaiting his chance to destroy Harry and all that he stands for.","release_date":"2005-11-16"},{"vote_count":278,"id":11219,"video":false,"vote_average":7.1,"title":"The Trouble with Harry","popularity":11.215,"poster_path":"/tXq2vB69rv6uqqkyiqfxci0TMgZ.jpg","original_language":"en","original_title":"The Trouble with Harry","genre_ids":[35,9648],"backdrop_path":"/myJvEASSSrHinqXcAIeIQVLyQnf.jpg","adult":false,"overview":"Trouble erupts in a small, quiet New England town when a man's body is found in the woods. The problem is that almost everyone in town thinks that they had something to do with his death.","release_date":"1955-10-03"},{"vote_count":10061,"id":767,"video":false,"vote_average":7.6,"title":"Harry Potter and the Half-Blood Prince","popularity":32.965,"poster_path":"/bFXys2nhALwDvpkF3dP3Vvdfn8b.jpg","original_language":"en","original_title":"Harry Potter and the Half-Blood Prince","genre_ids":[12,14,10751],"backdrop_path":"/gYsG0HDxFtdhC7MiMxirjogwtf2.jpg","adult":false,"overview":"As Harry begins his sixth year at Hogwarts, he discovers an old book marked as 'Property of the Half-Blood Prince', and begins to learn more about Lord Voldemort's dark past.","release_date":"2009-07-07"},{"vote_count":273,"id":8989,"video":false,"vote_average":5.7,"title":"Harry and the Hendersons","popularity":9.059,"poster_path":"/mhpajPCVjE3y4wgDLichDF5Peq8.jpg","original_language":"en","original_title":"Harry and the Hendersons","genre_ids":[35,10751,14],"backdrop_path":"/zpLxlU7tZ3wnAKudHOlhuluR5nm.jpg","adult":false,"overview":"Returning from a hunting trip in the forest, the Henderson family's car hits an animal in the road. At first they fear it was a man, but when they examine the \"body\" they find it's a \"bigfoot\". They think it's dead so they decide to take it home (there could be some money in this). As you guessed, it isn't dead. Far from being the ferocious monster they fear \"Harry\" to be, he's a friendly giant.","release_date":"1987-06-05"},{"vote_count":10312,"id":675,"video":false,"vote_average":7.6,"title":"Harry Potter and the Order of the Phoenix","popularity":28.02,"poster_path":"/4YnLxYLHhT4UQ8i9jxAXWy46Xuw.jpg","original_language":"en","original_title":"Harry Potter and the Order of the Phoenix","genre_ids":[12,14,10751,9648],"backdrop_path":"/gGt4ePOhD8ilxd3FYhKB06L2CyG.jpg","adult":false,"overview":"Returning for his fifth year of study at Hogwarts, Harry is stunned to find that his warnings about the return of Lord Voldemort have been ignored. Left with no choice, Harry takes matters into his own hands, training a small group of students \u2013 dubbed 'Dumbledore's Army' \u2013 to defend themselves against the dark arts.","release_date":"2007-06-28"},{"vote_count":11041,"id":12445,"video":false,"vote_average":8.1,"title":"Harry Potter and the Deathly Hallows: Part 2","popularity":36.951,"poster_path":"/fTplI1NCSuEDP4ITLcTps739fcC.jpg","original_language":"en","original_title":"Harry Potter and the Deathly Hallows: Part 2","genre_ids":[10751,14,12],"backdrop_path":"/n5A7brJCjejceZmHyujwUTVgQNC.jpg","adult":false,"overview":"Harry, Ron and Hermione continue their quest to vanquish the evil Voldemort once and for all. Just as things begin to look hopeless for the young wizards, Harry discovers a trio of magical objects that endow him with powers to rival Voldemort's formidable skills.","release_date":"2011-07-07"},{"vote_count":119,"id":11718,"video":false,"vote_average":5.5,"title":"Who's Harry Crumb?","popularity":7.821,"poster_path":"/eimYRIjRCIi7k7RR8D9RtVakPl8.jpg","original_language":"en","original_title":"Who's Harry Crumb?","genre_ids":[35,80,9648],"backdrop_path":"/dPK3UCOrDqLWuTKKJssKxNHz0vA.jpg","adult":false,"overview":"Harry Crumb is a bumbling and inept private investigator who is hired to solve the kidnapping of a young heiress which he's not expected to solve because his employer is the mastermind behind the kidnapping.","release_date":"1989-02-03"},{"vote_count":11065,"id":673,"video":false,"vote_average":7.9,"title":"Harry Potter and the Prisoner of Azkaban","popularity":27.138,"poster_path":"/jUFjMoLh8T2CWzHUSjKCojI5SHu.jpg","original_language":"en","original_title":"Harry Potter and the Prisoner of Azkaban","genre_ids":[12,14,10751],"backdrop_path":"/wUpBH6RIH4uOiWoPjj8MKUemu9F.jpg","adult":false,"overview":"Harry, Ron and Hermione return to Hogwarts for another magic-filled year. Harry comes face to face with danger yet again, this time in the form of escaped convict, Sirius Black\u2014and turns to sympathetic Professor Lupin for help.","release_date":"2004-05-31"},{"vote_count":10299,"id":12444,"video":false,"vote_average":7.7,"title":"Harry Potter and the Deathly Hallows: Part 1","popularity":31.045,"poster_path":"/bk1EWfRW0U76sWZiLDL1T7J0XV9.jpg","original_language":"en","original_title":"Harry Potter and the Deathly Hallows: Part 1","genre_ids":[12,14,10751],"backdrop_path":"/8YA36faYlkpfp6aozcGsqq68pZ9.jpg","adult":false,"overview":"Harry, Ron and Hermione walk away from their last year at Hogwarts to find and destroy the remaining Horcruxes, putting an end to Voldemort's bid for immortality. But with Harry's beloved Dumbledore dead and Voldemort's unscrupulous Death Eaters on the loose, the world is more dangerous than ever.","release_date":"2010-10-17"},{"vote_count":1844,"id":639,"video":false,"vote_average":7.4,"title":"When Harry Met Sally...","popularity":12.259,"poster_path":"/anYI1fzx90PgjO9PY3JStJZJmQT.jpg","original_language":"en","original_title":"When Harry Met Sally...","genre_ids":[35,10749,18],"backdrop_path":"/xd5SYDUhNVGLUeK2epbEjnVBpPN.jpg","adult":false,"overview":"During their travel from Chicago to New York, Harry and Sally debate whether or not sex ruins a friendship between a man and a woman. Eleven years later, and they're still no closer to finding the answer.","release_date":"1989-07-21"},{"vote_count":83,"id":47943,"video":false,"vote_average":6.7,"title":"With a Friend Like Harry...","popularity":7.011,"poster_path":"/6apR22heJAH1GMYyk3RsHf13UXQ.jpg","original_language":"fr","original_title":"Harry, un ami qui vous veut du bien","genre_ids":[35],"backdrop_path":"/r4CpcDEwAdEBObEPjBwvc4USapO.jpg","adult":false,"overview":"Harry knew Michel in high school; they meet again by accident, Harry inserts himself in Michel's life... and things take a sinister turn.","release_date":"2000-08-15"},{"vote_count":34,"id":42448,"video":false,"vote_average":7,"title":"Harry and Tonto","popularity":3.386,"poster_path":"/BbA9z4kFuIXmljZTHG3FjCWjQk.jpg","original_language":"en","original_title":"Harry and Tonto","genre_ids":[18,35],"backdrop_path":"/2gC5CF40aVLblClu4UCROj4vJ20.jpg","adult":false,"overview":"Harry is a retired teacher in his 70s living in the Upper West Side of New York City where his late wife and he raised his children--where he's lived all his life. When the building he lives in is torn down to make way for a parking garage, Harry and his beloved cat Tonto begin a journey across the United States, visiting his children, seeing a world he never seemed to have the time to see before, making new friends, and saying goodbye to old friends.","release_date":"1974-08-12"},{"vote_count":56,"id":518527,"video":false,"vote_average":6.3,"title":"Harry & Meghan: A Royal Romance","popularity":6.5,"poster_path":"/2ODzSaM8EKF28mGjNTH3ptb3BJu.jpg","original_language":"en","original_title":"Harry & Meghan: A Royal Romance","genre_ids":[10749],"backdrop_path":"/bDqchWjPhko0XvRBx4pErPMk0Hv.jpg","adult":false,"overview":"Examine the history of Prince Harry and Meghan Markle from the moment they met after being set up by friends, through their initial courtship when they were able to keep their romance under wraps, and ultimately the intense global media attention surrounding their relationship and Meghan\u2019s life as a divorced American actress.","release_date":"2018-05-13"},{"vote_count":372,"id":10152,"video":false,"vote_average":4.1,"title":"Dumb and Dumberer: When Harry Met Lloyd","popularity":10.767,"poster_path":"/eizaKEnF108gQq89f1XsAyVxjq6.jpg","original_language":"en","original_title":"Dumb and Dumberer: When Harry Met Lloyd","genre_ids":[35],"backdrop_path":"/dzGocMNPdWMfo52LcofnkfwrMaj.jpg","adult":false,"overview":"This wacky prequel to the 1994 blockbuster goes back to the lame-brained Harry and Lloyd's days as classmates at a Rhode Island high school, where the unprincipled principal puts the pair in remedial courses as part of a scheme to fleece the school.","release_date":"2003-06-13"},{"vote_count":39,"id":456806,"video":false,"vote_average":8.3,"title":"Harry Styles: Behind the Album","popularity":2.62,"poster_path":"/M9Ejub7fkAkBsqt6gz3gZJU5gO.jpg","original_language":"en","original_title":"Harry Styles: Behind the Album","genre_ids":[10402,99],"backdrop_path":"/ejsxgl5Mb0YXHPbZFkVas41ktg.jpg","adult":false,"overview":"Chronicles Harry\u2019s musical journey while creating his much anticipated debut solo album. The film features exclusive interviews and behind the scenes footage shot in Jamaica, Los Angeles and London during the making of the album and is complemented by Harry and his band performing songs from it for the first time at the world famous Abbey Road Studios in London.","release_date":"2017-05-15"}]
     */

    private int page;
    private int total_results;
    private int total_pages;
    private List<ResultsBean> results;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean{
        /**
         * vote_count : 1011
         * id : 984
         * video : false
         * vote_average : 7.5
         * title : Dirty Harry
         * popularity : 13.621
         * poster_path : /4j2tBAusIVev4ZaScncIHknP7eV.jpg
         * original_language : en
         * original_title : Dirty Harry
         * genre_ids : [28,80,53]
         * backdrop_path : /t3OZS8yMs0NL4YgWWc04eXHTn1X.jpg
         * adult : false
         * overview : When a madman dubbed 'Scorpio' terrorizes San Francisco, hard-nosed cop, Harry Callahan – famous for his take-no-prisoners approach to law enforcement – is tasked with hunting down the psychopath. Harry eventually collars Scorpio in the process of rescuing a kidnap victim, only to see him walk on technicalities. Now, the maverick detective is determined to nail the maniac himself.
         * release_date : 1971-12-21
         */

        private int vote_count;
        private int id;
        private boolean video;
        private double vote_average;
        private String title;
        private double popularity;
        private String poster_path;
        private String original_language;
        private String original_title;
        private String backdrop_path;
        private boolean adult;
        private String overview;
        private String release_date;
        private List<Integer> genre_ids;

        public int getVote_count() {
            return vote_count;
        }

        public void setVote_count(int vote_count) {
            this.vote_count = vote_count;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public boolean isVideo() {
            return video;
        }

        public void setVideo(boolean video) {
            this.video = video;
        }

        public double getVote_average() {
            return vote_average;
        }

        public void setVote_average(double vote_average) {
            this.vote_average = vote_average;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public double getPopularity() {
            return popularity;
        }

        public void setPopularity(double popularity) {
            this.popularity = popularity;
        }

        public String getPoster_path() {
            return poster_path;
        }

        public void setPoster_path(String poster_path) {
            this.poster_path = poster_path;
        }

        public String getOriginal_language() {
            return original_language;
        }

        public void setOriginal_language(String original_language) {
            this.original_language = original_language;
        }

        public String getOriginal_title() {
            return original_title;
        }

        public void setOriginal_title(String original_title) {
            this.original_title = original_title;
        }

        public String getBackdrop_path() {
            return backdrop_path;
        }

        public void setBackdrop_path(String backdrop_path) {
            this.backdrop_path = backdrop_path;
        }

        public boolean isAdult() {
            return adult;
        }

        public void setAdult(boolean adult) {
            this.adult = adult;
        }

        public String getOverview() {
            return overview;
        }

        public void setOverview(String overview) {
            this.overview = overview;
        }

        public String getRelease_date() {
            return release_date;
        }

        public void setRelease_date(String release_date) {
            this.release_date = release_date;
        }

        public List<Integer> getGenre_ids() {
            return genre_ids;
        }

        public void setGenre_ids(List<Integer> genre_ids) {
            this.genre_ids = genre_ids;
        }

//        private ResultsBean(Parcel in){
//            Log.d(LOG_TAG, "MyObject(Parcel parcel)");
//            vote_count = in.readInt();
//            id = in.readInt();
//            boolean[] booleanValues1 = new boolean[1];
//            in.readBooleanArray(booleanValues1);
//            this.video=booleanValues1[0];
//            vote_average = in.readDouble();
//            title = in.readString();
//            popularity = in.readDouble();
//            poster_path = in.readString();
//            original_language = in.readString();
//            original_title = in.readString();
//            backdrop_path = in.readString();
//            boolean[] booleanValues2 = new boolean[1];
//            in.readBooleanArray(booleanValues2);
//            this.adult=booleanValues2[0];
//            overview = in.readString();
//            release_date = in.readString();
//            genre_ids = new ArrayList<>();
//            in.readList(genre_ids,Integer.class.getClassLoader());
//        }
//
//        @Override
//        public int describeContents() {
//            return 0;
//        }
//
//        @Override
//        public void writeToParcel(Parcel dest, int flags) {
//            Log.d(LOG_TAG, "writeToParcel");
//            dest.writeInt(vote_count);
//            dest.writeInt(id);
//            boolean[] booleanValues1 = new boolean[1];
//            dest.writeBooleanArray(booleanValues1);
//            this.video=booleanValues1[0];
//            dest.writeDouble(vote_average);
//            dest.writeString(title);
//            dest.writeDouble(popularity);
//            dest.writeString(poster_path);
//            dest.writeString(original_language);
//            dest.writeString(original_title);
//            dest.writeString(backdrop_path);
//            boolean[] booleanValues2 = new boolean[1];
//            dest.writeBooleanArray(booleanValues2);
//            this.adult=booleanValues2[0];
//            dest.writeString(overview);
//            dest.writeString(release_date);
//            dest.writeList(genre_ids);
//        }
//        final static String LOG_TAG = "myLogs";
//
//
//        public static final Parcelable.Creator<ResultsBean> CREATOR = new Parcelable.Creator<ResultsBean>() {
//            @Override
//            public ResultsBean createFromParcel(Parcel in) {
//                Log.d(LOG_TAG, "createFromParcel");
//                return new ResultsBean(in);
//            }
//
//            @Override
//            public ResultsBean[] newArray(int size) {
//                return new ResultsBean[size];
//            }
//        };
    }
}
