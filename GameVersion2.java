public class GameVersion2{
   public static void main(String[] args){
      //creating monster room
      MonsterRoom zombie = new MonsterRoom("Zombie Room","brain;more brains;5-star brain",0,"n1;w2");
      MonsterRoom witch = new MonsterRoom("Witch Room","potions;flying broom;crystal ball",1,"e0;s2");
      MonsterRoom player = new MonsterRoom("Monster Room","mirror",2,"w1;s0");
      
      //create a list of MonsterRoom objects
      MonsterRoom[] evilRooms = {zombie,witch,player};//NOTE: index of each objects must be the same as room number in each onject constructor
      
      // keep track of which room player is in 
      int playerRoomNumber = 0;

      // create player object, using the superclass Player
      Player aPlayer = new Player("Timmy", playerRoomNumber, "slice of pizza;football;sunglasses");

      // play the game, as long as player object has field continuePlay set to true
      while (aPlayer.getContinuePlay() == true) {
            playerRoomNumber =
                    evilRooms[playerRoomNumber].playRoom(aPlayer);
            }
        
    }

}
