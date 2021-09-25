# LeagueOMon

## A turn base League of Legends and Pokemon crossover

Content:
- About
- Features
- User Stories

#About

Stuck in silver? Toxic players? Boosted teammates? Fear not! Professor Oak is
here to help! Being the nice guy he is, he is willing to offer you one of the new
S series champions to aid on your adventure. Join the world of LeagueOMon and with your
newfound champion, climb your way up to challenger.

The Story is about a player, who for external circumstances is stuck in "silver" rank. The player goes
to Professor Oak, a local professor who researches LeagueOMon. LeagueOMon are wild monsters that you can capture to fight
on your behalf. He gives you choice of two very powerful LeagueOMon : Master Yi and Tom Kench. With one
of them, the player goes on a journey to climb to the top to become a challenger.   

#Information

This project is of interest to me because I never made a proper game before. In the past, I've coded 
calculators, converters and reddit bots. None were more than 2 files large and all were made using
python. I wanted to challenge myself to see if I can make a game.

This is for people who like League of Legend and Pokemon and want to see them together in a game.
 


# Features
- Music - To be implemented
- Turn base battles
- Attack animations - To be implemented
- An story - In progress
- Online images - To be implemented
- Save system - To be implemented
- Leveling system - To be implemented
- DeBuffing, buffing system



# User Stories Phase 1
- As a user, I want to read text
- As a user, I want to pick options that change the story
- As a user, I want to battle other people
- As a user, I want to level up my leagueOMon
- As a user, I want to add new leagueOMon to my team
- As a user, I want to fight wild LeagueOMon to level up my leagueOMon
- As a user, I want to Buff and deBuff my enemies
- As a user, I want to fight LeagueOMon of increasing difficulty
- As a user, I want to remove Pokemon from my team.
- As a user, I want to choose between 4 moves to fight other people.


#User Stories Phase 2
- As a user, I want to save my progress in game with a savepoint.
- As a user, I want to save my LeagueOMon team.
- As a user, I want to be able to load my player Data.
- As a user, I want to be able to load my LeagueOMon team.
- As a user, I want to restart my game.


#User Stories Phase 3 (GUI SUPPORT)
- As a user, I want to Change LeagueOMon in battle.
- As a user, I want to load and save the application such that my progress in the story is remembered.
- As a user, I want to add leagueOMon to my team.

#Phase4: Task2 :
- Type hierarchy in code. Abstract Class: LeagueOMon
Class: Blitzcrank,CannonMinion,Kayn to name a few.
Interface: LeagueOMonMoveset

#Phase3: Task3 :
Clearly, the UI could have been managed much more efficiently. For one, I should have the GUI run such that I could have
while loops in my methods by using SwingWorkers. In addition, I should have type Hierarchy the StoryGUI's
to increase cohesion and readability. There is most likely a better implementation text cycling in my StoryGUI. The one I
was very constrained and buggy. If I were to have more time, I would have overhauled the whole GUI portion.

# Known bugs (to be fix)
- BlitzCrank Overdrive not resetting properly
- Infinite Mana in battles
