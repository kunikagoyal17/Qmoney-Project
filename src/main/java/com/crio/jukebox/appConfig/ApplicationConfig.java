 package com.crio.jukebox.appConfig;
  import com.crio.jukebox.repository.IUserRepository;
import com.crio.jukebox.repository.PlaylistRepository;
import com.crio.jukebox.repository.SongData;
import com.crio.jukebox.repository.SongRepository;
import com.crio.jukebox.commands.CommandInvoker;
import com.crio.jukebox.commands.CreatePlaylistCommand;
import com.crio.jukebox.commands.CreateUserCommand;
import com.crio.jukebox.commands.DeletePlaylistCommand;
import com.crio.jukebox.commands.ICommand;
import com.crio.jukebox.commands.ModifyPlaylistCommand;
import com.crio.jukebox.commands.PlayPlaylistCommand;
import com.crio.jukebox.commands.PlaySongCommand;
import com.crio.jukebox.repository.DataLoader;
import com.crio.jukebox.repository.IPlaylistRepository;
import com.crio.jukebox.repository.ISongRepository;
import com.crio.jukebox.repository.UserRepository;
// import com.crio.jukebox.repository.data.SongData;
import com.crio.jukebox.service.*;


public class ApplicationConfig {

    private final IUserRepository iUserRepository= new UserRepository();

    private final IPlaylistRepository iPlaylistRepository = new PlaylistRepository();

    private final ISongRepository iSongRepository = new SongRepository();


    private final IUserService iUserService = new UserService(iUserRepository);

    private final IPlaylistService iPlaylistService = new PlaylistService(iPlaylistRepository, iUserRepository, iSongRepository);

    //private com.crio.jukebox.service.IUserRepository IUserrepository;

    private final ISongService iSongService = new SongService( iSongRepository  ,iUserRepository);
    


    private final ICommand createUserCommand = new CreateUserCommand(iUserService);

    private final ICommand createPlaylistCommand = new CreatePlaylistCommand(iPlaylistService);
    private final ICommand deletePlaylistCommand = new DeletePlaylistCommand(iPlaylistService);
    private final ICommand modifyPlaylistCommand = new ModifyPlaylistCommand(iPlaylistService);
    private final ICommand playPlaylistCommand = new PlayPlaylistCommand(iPlaylistService);

    private final ICommand playSongCommand = new PlaySongCommand(iSongService);


    private final CommandInvoker commandInvoker = new CommandInvoker();

    private final DataLoader dataLoader = new DataLoader();

    public CommandInvoker getCommandInvoker(){
        commandInvoker.register("CREATE-USER", createUserCommand);
        commandInvoker.register("CREATE-PLAYLIST", createPlaylistCommand);
        commandInvoker.register("MODIFY-PLAYLIST", modifyPlaylistCommand);
        commandInvoker.register("DELETE-PLAYLIST", deletePlaylistCommand);
        commandInvoker.register("PLAY-PLAYLIST", playPlaylistCommand);
        commandInvoker.register("PLAY-SONG", playSongCommand);
        return commandInvoker;
    }

    public DataLoader getDataLoader(){
        dataLoader.register("LOAD-DATA", new SongData(iSongRepository));
        return dataLoader;
    }

}
    

