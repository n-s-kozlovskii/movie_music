package com.surfcourse.nek.moviemusic.songs.dummy;

import com.surfcourse.nek.moviemusic.songs.Song;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

  /**
   * An array of sample (dummy) items.
   */
  public static final List<Song> ITEMS = new ArrayList<Song>();

  private static final int COUNT = 25;

  static {
    // Add some sample items.
    for (int i = 1; i <= COUNT; i++) {
      addItem(createSong(i));
    }
  }

  private static void addItem(Song item) {
    ITEMS.add(item);
  }

  private static Song createSong(int position) {
    return new Song(position, "Item " + position, "Author " + position, "Movie " + position, "");
  }
}
