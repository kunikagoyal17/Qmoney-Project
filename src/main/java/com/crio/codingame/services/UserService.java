package com.crio.codingame.services;

//import java.util.List;
//import java.util.stream.Collectors;
import java.util.*;
import java.util.List;
//import java.util.stream.Collectors;

import com.crio.codingame.dtos.UserRegistrationDto;
import com.crio.codingame.entities.Contest;
import com.crio.codingame.entities.ContestStatus;
import com.crio.codingame.entities.RegisterationStatus;
import com.crio.codingame.entities.ScoreOrder;
import com.crio.codingame.entities.User;
import com.crio.codingame.exceptions.ContestNotFoundException;
import com.crio.codingame.exceptions.InvalidOperationException;
import com.crio.codingame.exceptions.UserNotFoundException;
import com.crio.codingame.repositories.IContestRepository;
import com.crio.codingame.repositories.IUserRepository;
//import com.crio.codingame.repositories.UserRepository;

public class UserService implements IUserService {

    private final IUserRepository userRepository;
    private final IContestRepository contestRepository;

    public UserService(IUserRepository userRepository, IContestRepository contestRepository) {
        this.userRepository = userRepository;
        this.contestRepository = contestRepository;
    }
    // TODO: CRIO_TASK_MODULE_SERVICES
    // Create and store User into the repository.
    
     // Create and store User into the repository.
     @Override
     public User create(String name) {
         User u = new User(name, 0);
         return userRepository.save(u);
         // this.Name=name;
         // return Name;
         // return null;
     }
 

    // TODO: CRIO_TASK_MODULE_SERVICES
    // Get All Users in Ascending Order w.r.t scores if ScoreOrder ASC.
    // Or
    // Get All Users in Descending Order w.r.t scores if ScoreOrder DESC.

    @Override
    public List<User> getAllUserScoreOrderWise(ScoreOrder scoreOrder) {
        // List<User> asc = new ArrayList<User>();
        // user.UserRepository(user);
        // List<User> user= new ArrayList<User>();
        List<User> user = userRepository.findAll();
      //  List<User> res= new ArrayList<User>();

        if(scoreOrder.equals(scoreOrder.ASC))
        { 
         Collections.sort(user,  Comparator.comparing(User::getScore));     
        }

        else{
         // return   List<User> res= Colections.sort(user)
          
               Collections.sort(user, Comparator.comparing(User::getScore).reversed());     
        }
            //  List<User> res= Collections.sort(user,  Comparator<User>);
       return user;
        
    }


    
    @Override
    public UserRegistrationDto attendContest(String contestId, String userName)
            throws ContestNotFoundException, UserNotFoundException, InvalidOperationException {
        Contest contest = contestRepository.findById(contestId).orElseThrow(
                () -> new ContestNotFoundException("Cannot Attend Contest. Contest for given id:"
                        + contestId + " not found!"));
        User user = userRepository.findByName(userName).orElseThrow(() -> new UserNotFoundException(
                "Cannot Attend Contest. User for given name:" + userName + " not found!"));
        if (contest.getContestStatus().equals(ContestStatus.IN_PROGRESS)) {
            throw new InvalidOperationException("Cannot Attend Contest. Contest for given id:"
                    + contestId + " is in progress!");
        }
        if (contest.getContestStatus().equals(ContestStatus.ENDED)) {
            throw new InvalidOperationException(
                    "Cannot Attend Contest. Contest for given id:" + contestId + " is ended!");
        }
        if (user.checkIfContestExists(contest)) {
            throw new InvalidOperationException("Cannot Attend Contest. Contest for given id:"
                    + contestId + " is already registered!");
        }
        user.addContest(contest);
        userRepository.save(user);
        return new UserRegistrationDto(contest.getName(), user.getName(),
                RegisterationStatus.REGISTERED);
    }

    // TODO: CRIO_TASK_MODULE_SERVICES
    // Withdraw the user from the contest
    // Hint :- Refer Unit Testcases withdrawContest method

    // @Override
    // public UserRegistrationDto withdrawContest(String contestId, String userName) throws ContestNotFoundException, UserNotFoundException, InvalidOperationException {
    //     Contest contest = contestRepository.findById(contestId).orElseThrow(() -> new ContestNotFoundException("Cannot Attend Contest. Contest for given id:"+contestId+" not found!"));
    //     User user = userRepository.findByName(userName).orElseThrow(() -> new UserNotFoundException("Cannot Attend Contest. User for given name:"+ userName+" not found!"));
    //        if (user.checkIfContestExists(contest))
    //        {
    //           throw new InvalidOperationException ("Contest not exist"+contestId );
    //        }
    //         if(contest.getContestStatus()!=ContestStatus.NOT_STARTED)
    //         {
    //             throw new InvalidOperationException("Contest has already begun" + contestId);
    //         }

    //         user.deleteContest(contest);
    
    @Override
    public UserRegistrationDto withdrawContest(String contestId, String userName) throws ContestNotFoundException, UserNotFoundException, InvalidOperationException {
        
        Contest contest= contestRepository.findById(contestId).orElseThrow(()->  new ContestNotFoundException());

        User user=userRepository.findByName(userName).orElseThrow(()-> new UserNotFoundException());



        if(!user.checkIfContestExists(contest) || contest.getContestStatus()!=ContestStatus.NOT_STARTED){
            throw new InvalidOperationException();
        }

        user.deleteContest(contest);
        userRepository.save(user);


        
        
     return new UserRegistrationDto(contest.getName(), userName, RegisterationStatus.NOT_REGISTERED);

     
    }



       
    public int compare(User u1,User u2)
    {     
    
        return u1.getScore()-u2.getScore();
        }

   // @Override
  //  public User create(String name) {
  //  }


  //  @Override
  //  public List<User> getAllUserScoreOrderWise(ScoreOrder scoreOrder){
  //  }

  /*   @Override
    public UserRegistrationDto attendContest(String contestId, String userName) throws ContestNotFoundException, UserNotFoundException, InvalidOperationException {
        Contest contest = contestRepository.findById(contestId).orElseThrow(() -> new ContestNotFoundException("Cannot Attend Contest. Contest for given id:"+contestId+" not found!"));
        User user = userRepository.findByName(userName).orElseThrow(() -> new UserNotFoundException("Cannot Attend Contest. User for given name:"+ userName+" not found!"));
        if(contest.getContestStatus().equals(ContestStatus.IN_PROGRESS)){
            throw new InvalidOperationException("Cannot Attend Contest. Contest for given id:"+contestId+" is in progress!");
        }
        if(contest.getContestStatus().equals(ContestStatus.ENDED)){
            throw new InvalidOperationException("Cannot Attend Contest. Contest for given id:"+contestId+" is ended!");
        }
        if(user.checkIfContestExists(contest)){
            throw new InvalidOperationException("Cannot Attend Contest. Contest for given id:"+contestId+" is already registered!");
        }
        user.addContest(contest);
        userRepository.save(user);
        return new UserRegistrationDto(contest.getName(), user.getName(),RegisterationStatus.REGISTERED);
    }


    @Override
    public UserRegistrationDto withdrawContest(String contestId, String userName) throws ContestNotFoundException, UserNotFoundException, InvalidOperationException {
    }*/
    
}
