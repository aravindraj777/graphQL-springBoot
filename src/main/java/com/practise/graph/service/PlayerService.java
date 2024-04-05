package com.practise.graph.service;

import com.practise.graph.model.Player;
import com.practise.graph.model.Team;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class PlayerService {

     private List<Player> players = new ArrayList<>();

     AtomicInteger id = new AtomicInteger(0);

     public List<Player> findAll(){
         return players;
     }

     public Optional<Player> findOne(Integer id){
         return players.stream().filter(player -> player.Id() == id).findFirst();
     }

     public Player create(String name, Team team){
         Player player = new Player(id.incrementAndGet(),name,team);
         players.add(player);
         return player;
     }

     public Player delete(Integer id){
         Player player = players.stream().filter(c->c.Id() == id)
                 .findFirst().orElseThrow(()->new IllegalArgumentException());

         players.remove(player);
         return player;
     }


     public Player update(Integer id,String name,Team team){
         Player updatedPlayer = new Player(id,name,team);
         Optional<Player> optional = players.stream().filter(c->c.Id() == id).findFirst();
         if(optional.isPresent()){
             Player player = optional.get();
             int index = players.indexOf(player);
             players.set(index,updatedPlayer);
         }
         else {
             throw new IllegalArgumentException("Invalid Player");
         }
            return updatedPlayer;
     }


     @PostConstruct
     private void init(){
         players.add(new Player(id.incrementAndGet(),"Ms Dhoti",Team.CSK));
         players.add(new Player(id.incrementAndGet(),"Prohibit",Team.DC));
         players.add(new Player(id.incrementAndGet(),"Suresh",Team.MI));
         players.add(new Player(id.incrementAndGet(),"Rishabh",Team.GT));
         players.add(new Player(id.incrementAndGet(),"Rahul",Team.CSK));

     }
}
