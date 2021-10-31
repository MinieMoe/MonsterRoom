import java.util.*;

public class MonsterRoom extends Room{
   
   //constructor
   public MonsterRoom(String roomName, String objects, int roomNum, String listOfExits){
      //must include args of Room()
      super(roomName,objects,roomNum,listOfExits);   
   }
   
   //overidding playRoom()
   public int playRoom(Player player){
   
      //code blocks to describe the room player is in before giving the name of the room using either switch or if-else
         //if(MonsterRoom.getRoomName().equals(""))
         //since MonsterRoom inherits from Room, we can use getRoomName() from Room --> MonsterRoom.getRoomName()
         
      String room = super.getRoomName();//if we use getRoomNumber() instead, then the case should be the roomnumber
      
      switch(room){
         case "Zombie Room":
            System.out.println("You open the door to a new room.\nIt's painted with red blood and reeks of rooten human flesh.\n" +
                               "Flies are hovering over a stack of decaying human bodies.\nAmong which, you see a zombie munching on human brains.\n");
            break;
            
         case "Witch Room":
            System.out.println("You open the door to a new room.\nIt's full of pleasant smells from pots of black roses hanging on the walls.\n" +
                               "It looks like a vintage lab decorated with shelves of dark magic books and tables full of potions.\n" +
                               "Behind which, you see an old witch stirring a giant pot.\nWith dark makeup on her face, she looks like she hasn't grown out of her a teenage edgy phase.\n");
            break;
            
         case "Monster Room":
            System.out.println("You open the door to a new room.\nUnlike the other rooms you've been to, it's quite a simple room.\n" +
                              "There's nothing but darkness and a big mirrow illuminated by an unknown light source in the middle of the room.\n");
            break;
      }
            
      //re-using the playRoom() from super class
      int newRoomNum = super.playRoom(player);//storing the new room number if the user chooses to exit to new room
      
      return newRoomNum;
   }
   
   //overidding userPicksUp(): in this method, player can't really pick up items in the room; instead; they choose the object and something happens. Some ojects can be picked up (re-use the super.userPicksUp(player))
   //code blocks with new methods for player to use in Monster room
         //each item in each room has different uses

   protected void userPicksUp(Player player){
      
      //storing all of the items initially in room before getting picked up by the user
      //String initialItemsInRoom = super.getRoomObjects();
      
      //call the pick up method from the Room class   
      super.userPicksUp(player);
      
      //get the item that player just picked up, from the inventory. The item player just picked up should be the last item on the inventory list
      //then pass item player just picked up to switch case
      String[] inventory = player.getInventory().split(";");
      String chosenItem = inventory[inventory.length - 1];
      
      
      /***************A BUNCH OF CODES WERE REPLACED WITH 2 LINES OF CODE THAT WORKS MUCH BETTER...TIMES WASTTEEEEDDD AAAAAAAAAh!**************************** 
      //ask the player what they just picked up (because I can't get the item that was picked up from super.userPicksUp(player))
      //then check the item re-entered is in the room
      //and check if the item re-entered is the same as the item player entered before (aka. the last item on inventory array: inventory[inventory.length - 1])
      String[] inventory = player.getInventory().split(";");
      boolean correctConfirm = false;
      String confirmed = "";// variable to hold the correctly re-entered item checked in the while loop
      
      System.out.println("Are you sure you want to pick it up? Enter the name of the item you just picked up to confirm.");
      Scanner confirm = new Scanner(System.in);
      String confirmItem = confirm.nextLine();

      while(correctConfirm == false){
         
         //need to check the item re-entered is in the room
         if (Utilities.isItemInContainer(confirmItem, initialItemsInRoom)) {
            if (confirmItem.equals(inventory[inventory.length - 1])){
            confirmed = confirmItem; //if the item is in the room and the user enters the correct name, the player has entered correctly and can proceed
            correctConfirm = true;
            }else{
               System.out.println("The item you entered is not the same as the item you want to pick up earlier. Please try again...");
               confirmItem = confirm.nextLine();//ask the user to enter the code again
            }
         }else{
            System.out.println("Oh...I forgot...that item is not in the room...my bad...Let's try again");
            super.userPicksUp(player);
            //updating the cofirmItem because the user just give a new input
            System.out.println("Confirm the item you just pick up");
            confirmItem = confirm.nextLine();
         }

      }
      /***************************************WASTETEEDDDD******************************************************/
      
      
      //each chosen item will create unique incident happening to player
         switch (chosenItem){
            case "brain":
               System.out.println("You pick up a brain.\nSuddenly, you feel an intense bite pain on your neck.\nThe zombie attacked you.\nBut nothing else bad happens..\n");
               break;
            case "more brains":
               System.out.println("You pick up a lot of brains.\nSuddenly, you feel your body hits the floor, which is getting warmer and wetter as your blood's pouring out.\n"+
                                 "You've realized that you got biten by the zombie. It attacked you because you took too many of its food.\n" +
                                 "You're lying there...soaked in your own blood...devoured by the zombie until your vision goes out.\n"+
                                 "You should've chosen something else (X_X)\n" +
                                 "*** ENDING 1: Bathe in your own blood ***");
               player.setContinuePlay(false);//end game because player dies
               break;
            case "5-star brain":
               System.out.println("You pick up a 5-star-Gordon-Ramsey-approved brain.\nAstonished by your choice of food,\nthe zombie assumes you're one of its kind and lets you live.\n");
               break;
            case "potions":
                System.out.println("You pick up a bunch of potions, which catches the witch's attention.\nThe witch asks if you want to try out one of her potions\n" +
                                    "A mysterious force drives you to take her offer.\nYou reluctantly drink the potion she picked out for you.\nNothing happens.\n");
                break;
            case "flying broom":
               System.out.println("The witch yells: 'Noooo!!! You can't just pick up my flying broom!!!'\n"+
                                  "'Flying broom goes brr brr' - you said\n" +
                                  "To the wall, where you head\n" +
                                  "To the wall goes your head\n" +
                                  "You shouldn't have gone for the head (X_X)\n" +
                                  "*** ENDING 2: Death by Meme ***");
               player.setContinuePlay(false);//end game because player dies
               break;
             case "crystal ball":
               System.out.println("You pick up a crystal ball.\nThe crystal ball asks: 'Do you want to see your future?'\nCuriously, you say yes.\n" +
                                  "In the ball you see...you're looking at yourself in the mirror.\n");
               break;
             case "mirror":
             
               System.out.println("You look at your reflection on the mirror...\n");
               
               //checking if they player has brain, potions, or both in their inventory to decide what they will see in the mirror
               ArrayList <String> conditionItems = new ArrayList <String>();
               for (String item: inventory){//inventory array is already declared in line 53
                  if(item.equals("brain")){
                     conditionItems.add("brain");}
                  if(item.equals("potions")){
                     conditionItems.add("potions");}
               }
               //if-else statements to print out info on what happens to the player depending if they have brain, potions, or both
               StringBuilder reflection = new StringBuilder();
               StringBuilder ending = new StringBuilder();
               if (conditionItems.size() <= 2 && conditionItems.size() > 0){
                  System.out.println("You see a hideous creature looking back at you.\n");
                  
                  for (String Item: conditionItems){
                     if(Item.equals("brain")){
                        reflection.append("The bite mark earlier has turned into a zombie.\n");
                        ending.append("*** ENDING 3 - Monster Room: BRRRRaaAAAaAAIinnnnnnnssss... ***");}
                     if(Item.equals("potions")){
                        reflection.append("The potion the witch gave you has turned you into a scrawny creature with boney wings.\n");
                        ending.append("*** ENDING 4 - Monster Room: The Witch's Pet ***");}
                  }
                  
                  System.out.println(reflection.toString());                
                  System.out.println("You have become a monster yourself.\nWelcome to your own Monster Room!");
                  
                  //when the player are turned into monsters by both the zombie and the witch, then it's an ending with a combined monster
                  if (conditionItems.size() == 2){
                     ending.setLength(0);//delete both of ending 3 and 4
                     ending.append("*** ENDING 5 - Monster Room: Flying Zombie ***");}
                  
                  System.out.println(ending.toString()); 
                  player.setContinuePlay(false);//end game because player becomes a monster
                }else{
                     System.out.println("You just see yourself...nothing unsual...meh...\n" + 
                                       "You have been to different kinds of Monster Room, but nothing happens.\n" +
                                       "'This haunted house is soooooooo laaaaaaammmmme...'- You say to yourself as you leave the Monster House.\n"
                                       + "*** ENDING 6: I WANT A REFUND!!! ***");
                     player.setContinuePlay(false);//end game because player left the monster house
                }
               break;
             /*default:
               System.out.println("That's not the item you've just picked up. If you have a goldfish brain, type inventory to check what you just picked up.\n");*/
            }
         }
      }