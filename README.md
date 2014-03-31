Adversarial-Search-
===================

Using Adversarial Search Algos - Greedy, MinMax and AlphBeta for a 2 player War game

This is WAR!!
This project is a 2 player game where each player has an army whose objective is to capture cities in such a way as to maximise the total resources captured. 
The game finishes when all the cities are occupied by either of the teams. 

About Cities
Cities may be connected to each other. Each city has a resource which is a positive number,associated with it.

About Capturing a City
A player can para-troop drop its army onto an unoccupied city.
A player can force march its troop to a nearby connected city and if they do so, they automatically capture all the opposition cities that are connected to the recently occupied city.

Algorithms used
Player 1 always uses the greedy algorithm to decide its next move.
Player 2 can use greedy, MinMax or AlphaBeta pruning searches on the game states to decide on the next mode depending upon the mode of the game.

More Details with the pdf attached.
