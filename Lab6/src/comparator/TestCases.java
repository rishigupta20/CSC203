package comparator;

import java.util.*;

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.*;

public class TestCases
{
   private static final Song[] songs = new Song[] {
         new Song("Decemberists", "The Mariner's Revenge Song", 2005),
         new Song("Rogue Wave", "Love's Lost Guarantee", 2005),
         new Song("Avett Brothers", "Talk on Indolence", 2006),
         new Song("Gerry Rafferty", "Baker Street", 1998),
         new Song("City and Colour", "Sleeping Sickness", 2007),
         new Song("Foo Fighters", "Baker Street", 1997),
         new Song("Queen", "Bohemian Rhapsody", 1975),
         new Song("Gerry Rafferty", "Baker Street", 1978)
      };

   @Test
   public void testArtistComparator()
   {
      ArtistComparator a1 = new ArtistComparator();
      assertTrue(a1.compare(songs[0], songs[1]) < 0);
      assertFalse(a1.compare(songs[1], songs[2]) < 0);
      assertTrue(a1.compare(songs[4], songs[5]) < 0);
      assertTrue(a1.compare(songs[2], songs[5]) < 0);
   }
   @Test
   public void testLambdaTitleComparator() {
      Comparator<Song> cmp_title = ((Song s1, Song s2) -> s1.getTitle().compareTo(s2.getTitle()));
      Song[] songs1 = new Song[] {
              new Song("Decemberists", "The Mariner's Revenge Song", 2005),
              new Song("Rogue Wave", "Love's Lost Guarantee", 2005),
              new Song("Avett Brothers", "Talk on Indolence", 2006),
              new Song("Gerry Rafferty", "Baker Street", 1998),
              new Song("City and Colour", "Sleeping Sickness", 2007),
              new Song("Foo Fighters", "Baker Street", 1997),
              new Song("Queen", "Bohemian Rhapsody", 1975),
              new Song("Gerry Rafferty", "Baker Street", 1978)
      };
      Arrays.sort(songs1, cmp_title);
      Song[] songs1_sorted = new Song[]{
              new Song("Gerry Rafferty", "Baker Street", 1998),
              new Song("Foo Fighters", "Baker Street", 1997),
              new Song("Gerry Rafferty", "Baker Street", 1978),
              new Song("Queen", "Bohemian Rhapsody", 1975),
              new Song("Rogue Wave", "Love's Lost Guarantee", 2005),
              new Song("City and Colour", "Sleeping Sickness", 2007),
              new Song("Avett Brothers", "Talk on Indolence", 2006),
              new Song("Decemberists", "The Mariner's Revenge Song", 2005),
      };
      assertArrayEquals(songs1, songs1_sorted);

      Song[] songs2 = new Song[] {
              new Song("Zara", "Mello", 1998),
              new Song("Susan", "Aello", 1992)
      };
      Arrays.sort(songs2, cmp_title);
      Song[] songs2_sorted = new Song[] {
              new Song("Susan", "Aello", 1992),
              new Song("Zara", "Mello", 1998)
      };
      assertArrayEquals(songs2, songs2_sorted);
   }
   @Test
   public void testYearExtractorComparator()
   {
      Comparator<Song> cmp_year = (Comparator.comparing(Song::getYear));
      Song[] songs1 = new Song[] {
              new Song("Decemberists", "The Mariner's Revenge Song", 2005),
              new Song("Rogue Wave", "Love's Lost Guarantee", 2005),
              new Song("Avett Brothers", "Talk on Indolence", 2006),
              new Song("Gerry Rafferty", "Baker Street", 1998),
              new Song("City and Colour", "Sleeping Sickness", 2007),
              new Song("Foo Fighters", "Baker Street", 1997),
              new Song("Queen", "Bohemian Rhapsody", 1975),
              new Song("Gerry Rafferty", "Baker Street", 1978)
      };
      Arrays.sort(songs1, cmp_year);
      Song[] songs_sorted = new Song[]{
              new Song("Queen", "Bohemian Rhapsody", 1975),
              new Song("Gerry Rafferty", "Baker Street", 1978),
              new Song("Foo Fighters", "Baker Street", 1997),
              new Song("Gerry Rafferty", "Baker Street", 1998),
              new Song("Decemberists", "The Mariner's Revenge Song", 2005),
              new Song("Rogue Wave", "Love's Lost Guarantee", 2005),
              new Song("Avett Brothers", "Talk on Indolence", 2006),
              new Song("City and Colour", "Sleeping Sickness", 2007),
      };
      assertArrayEquals(songs1, songs_sorted);
      Song[] songs2 = new Song[]{
              new Song("Queen", "Bohemian Rhapsody", 1975),
              new Song("Queen", "Bohemian Rhapsody", 1967),
      };
      Arrays.sort(songs2, cmp_year);
      Song[] songs2_sorted = new Song[]{
              new Song("Queen", "Bohemian Rhapsody", 1967),
              new Song("Queen", "Bohemian Rhapsody", 1975),
      };
      assertArrayEquals(songs2, songs2_sorted);
   }

   @Test
   public void testComposedComparator()
   {
      ArtistComparator a1 = new ArtistComparator();
      Comparator<Song> cmp_year = (Comparator.comparing(Song::getYear));
      ComposedComparator c1 = new ComposedComparator(a1, cmp_year);
      Song[] songs = new Song[]{
              new Song("Queen", "Bohemian Rhapsody", 1975),
              new Song("Gerry Rafferty", "Baker Street", 1978),
              new Song("Foo Fighters", "Baker Street", 1997),
              new Song("Gerry Rafferty", "Baker Street", 1998),
              new Song("Decemberists", "The Mariner's Revenge Song", 2005),
              new Song("Rogue Wave", "Love's Lost Guarantee", 2005),
              new Song("Avett Brothers", "Talk on Indolence", 2006),
              new Song("City and Colour", "Sleeping Sickness", 2007),
      };
      Arrays.sort(songs, cmp_year);
      assertFalse(c1.compare(songs[0], songs[1]) < 0);
      assertTrue(c1.compare(songs[1], songs[3]) < 0);
   }

   @Test
   public void testThenComparing()
   {
      Song[] songs = new Song[] {
              new Song("Decemberists", "The Mariner's Revenge Song", 2005),
              new Song("Rogue Wave", "Love's Lost Guarantee", 2005),
              new Song("Avett Brothers", "Talk on Indolence", 2006),
              new Song("Gerry Rafferty", "Baker Street", 1998),
              new Song("City and Colour", "Sleeping Sickness", 2007),
              new Song("Foo Fighters", "Baker Street", 1997),
              new Song("Queen", "Bohemian Rhapsody", 1975),
              new Song("Gerry Rafferty", "Baker Street", 1978)
      };
      Comparator<Song> cmp1 = (Comparator.comparing(Song::getTitle));
      Comparator<Song> cmp2 = cmp1.thenComparing(Song::getArtist);
      assertTrue(cmp2.compare(songs[0], songs[1]) > 0);
      assertTrue(cmp2.compare(songs[4], songs[6]) > 0);
   }

   @Test
   public void runSort()
   {
      List<Song> songList = new ArrayList<>(Arrays.asList(songs));
      List<Song> expectedList = Arrays.asList(
         new Song("Avett Brothers", "Talk on Indolence", 2006),
         new Song("City and Colour", "Sleeping Sickness", 2007),
         new Song("Decemberists", "The Mariner's Revenge Song", 2005),
         new Song("Foo Fighters", "Baker Street", 1997),
         new Song("Gerry Rafferty", "Baker Street", 1978),
         new Song("Gerry Rafferty", "Baker Street", 1998),
         new Song("Queen", "Bohemian Rhapsody", 1975),
         new Song("Rogue Wave", "Love's Lost Guarantee", 2005)
         );
      Comparator<Song> cmp1 = new ArtistComparator();
      Comparator<Song> cmp2 = cmp1.thenComparing(Song::getTitle);
      Comparator<Song> cmp3 = cmp2.thenComparing(Song::getYear);
      songList.sort(cmp3);
      assertEquals(songList, expectedList);
   }
}
