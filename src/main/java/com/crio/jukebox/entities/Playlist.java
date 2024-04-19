package com.crio.jukebox.entities;
//import java.util.*;
 //package com.crio.jukebox.entities.song.java;

/*public class playlist {
  private  int playlistid;
  private  String playlistname;
   private List<Integer> Songid;
  private  int userid;


       public int playlistid()
       {
        return playlistid;
       }

       public  String playlistname()
       {
        return playlistname;
       }

       public  int userid()
       {
        return userid;
       }



       public  List<Integer> songid()
       {
        return Songid;
       }*/
    
       
import java.util.List;
import java.util.stream.Collectors;

public class Playlist extends BaseEntity {

    private final String name;
    private List<Song> songs;

    public Playlist(String name, List<Song> songs){
        this.name = name;
        this.songs = songs;
    }

    public Playlist(String id, String name, List<Song> songs){
        this(name, songs);
        this.id = id;
    }

    //Copy Constructor
    public Playlist(Playlist entity){
        this(entity.getId(), entity.getName(), entity.getSongList());
    }

    //Getters
    public String getName(){
        return name;
    }
    public List<Song> getSongList(){
        return songs.stream().collect(Collectors.toList());
    }

    //Behaviours
    public void addSong(Song song){
        songs.add(song);
    }
    public void deleteSong(Song song){
        songs.removeIf(s-> s.getId() == song.getId());
    }
    public boolean checkIfSongExist(Song song){
        if(this.getSongList().isEmpty()){
            return false;
        }
        return this.getSongList().stream().anyMatch(s-> s.equals(song));
    }


    // Some formalities
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Playlist other = (Playlist) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Playlist [id=" + id + ", name=" + name + ", songIds=" + songs + "]";
    }

}



    

