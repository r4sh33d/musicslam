package com.r4sh33d.musicslam.dataloaders;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

public class SearchResultsLoader extends WrappedAsyncTaskLoader<List<Object>> {


    private final String queryText;

    /**
     * Constructor of <code>WrappedAsyncTaskLoader</code>
     *
     * @param context The {@link Context} to use.
     */
    public SearchResultsLoader(Context context, String query) {
        super(context);
        this.queryText = query;
    }


    @Nullable
    @Override
    public List<Object> loadInBackground() {
        ArrayList<Object> arrayList = new ArrayList<>();

        if (!TextUtils.isEmpty(queryText)) {
            //Artists
            List artists = ArtistLoader.searchArtists(getContext(), queryText);
            if (!artists.isEmpty()) {
                arrayList.add("Artists");
                arrayList.addAll(artists);
            }
            //Albums
            List albums = AlbumLoader.searchAlbums(getContext(), queryText);
            if (!albums.isEmpty()) {
                arrayList.add("Albums");
                arrayList.addAll(albums);
            }
            //Songs
            List songs = SongLoader.searchSongs(getContext(), queryText);
            if (!songs.isEmpty()) {
                arrayList.add("Songs");
                arrayList.addAll(songs);
            }
        }
        return arrayList;
    }
}
