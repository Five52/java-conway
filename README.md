# Game of Life

## Description

This project is a project group involving [`Evrard Caron`](https://github.com/Five52) and [`Thomas Fortin`](https://github.com/ThomasFortin), for the M2 DNR2i at the University of Caen Normandie. The goal of this project is to create a Game Of Life in Java language.

This is a game of life in which instead of dead and alive cells we have pilchards and sharks.

## Working

At the initialization of the game, the user gives the width, the height. Moreover, the number of sharks and the number of pilchards are given and all of these elements are put in the game randomly, in the empty cells, we put a sea element.

Sharks and pilchards have a reproduction cycle and a maximum age (after what they die).

After this initialization, the pilchards can move on each turn, or reproduce if they can. It's all the pilchards can do.

The sharks have a more complicated way of life. Indeed, they have three stages :

* Baby : A baby shark can just move on Sea cells or on pilchards to eat them (it's done randomly)
* Teenage : A teenage shark is going on a random pilchard just around of him if there is one, it there is not, he's doing a random move. If it's able to, a reproduction is privileged
* Adult : An adult shark is a real hunter. He detects which pilchard is the nearest, he finds the shortest path and hunts it to eat. If it's able to, a reproduction is privileged

