package com.r4sh33d.musicslam.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface LastFmService {
    String ARTIST_METHOD = "artist.getinfo";

    @GET("/2.0/")
    Call<ArtistResponse.ArtistInfoContainer> getArtistInfo(@Query("method") String methodName,
                                                           @Query("artist") String artistName);
}
