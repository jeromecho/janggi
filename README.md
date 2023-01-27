## Janggi (Korean Chess) 

A 2-player game for playing Janggi (Korean Chess). My brother and I used to play this in Calgary when we
visited family when I was 7. I've admired the game since and coded a version of the game since 
wooden janggi boards are heavy.

**Tech/Tools**
- Java Swing 
- JUnit (unit-testing)
- JSON (persistence)
- UML

**Things I was scared of but that I got better at**
- Object-oriented programming

Plus, it's persistent through JSON!

This was my term project for CPSC210: Software Construction from Sept-Dec 2022, where I earned a grade of **99.55%**. The small difference 
was due to running out of time for writing enough unit tests to satisfy the grader within the deadline (for an application 
of this size, 95 unit tests was insufficient).

Below is a picture of my UML. I really like it because it shows the amount of unnecessary effort I put into
the project. After it, follows a write-up that goes more into the details of the project.

## UML

![UML_Design_Diagram-1](https://user-images.githubusercontent.com/71617542/214977260-c0a6c28e-92ba-43a4-89c9-60d8a97ba0a1.png)


## Proposal 

**"Casual Janggi"** is an application where users are
able to play the Korean chess game *janggi* against 
another player. Users will interact with a 
GUI to drop and place pieces on the board. Users are also
able to open up several different games, which they can exit 
or enter as wanted. The
application will display a pop-up message 
once a winner is decided. Users are those that are:

- Knowledgeable in the rules of 
*janggi*
- Sociable and have another user with whom they 
can play the game with

I am especially interested in this program due 
to my love for strategy games, military 
strategy, and my Korean heritage. I am also
excited by the ability to program a challenging
object-oriented program that still seems achievable
within this course. There do not exist 
any easily searchable videos on programming
a *janggi* game, so I will be able to 
exercise my intuitive and conceptual understanding 
of the concepts taught in CPS210. Finally, the 
extensibility of my program, such
as the option to add an option to play
against an AI in the future, excites me. 

## User Stories

- As a user, I want to be able to create a new 
game and add it to my list of games 
- As a user, I want to be able to select a specific
piece on the board and move it to a new position
provided moving to that new position is valid for the
piece I selected
- As a user, I want to be able to surrender
if I am no longer able to make valid moves 
- As a user, I want to be able to be able to remove
my enemy's piece from the board if I move my piece
to the position that my enemy's piece occupies
- As a user, I want to be able to save my list of games 
to file
- As a user, I want to be able to load my list of games 
from file

## Instructions for Grader

- You can generate the first required event related to adding Xs to a Y by clicking 
the "ADD GAME" button present in the main menu
- You can generate the second required event related to adding Xs to a Y by selecting one of the 
added games in the scrolling pane of games on the right side of the main menu
- You can locate my visual component by selecting a game and pressing play game. There, 
you will see an interactive janggi board for you to interact with.
- You can save the state of my application by clicking the "SAVE GAMES" button in the 
main menu view.
- You can reload the state of my application by clicking the "LOAD GAMES" button in the 
main menu view.

## Phase 4: Task 2
 
Tue Nov 29 09:36:14 PST 2022
added game asdas

Tue Nov 29 09:36:16 PST 2022
added game gdf

Tue Nov 29 09:36:17 PST 2022
added game dfv

Tue Nov 29 09:36:34 PST 2022
Removed game dfv

## Phase 4: Task 3 

The changes that address design in terms of: 1) coupling and 
cohesion, 2) design patterns, and 3) micro-optimizations are as follows: 

- I can decrease coupling and increase cohesion in my UI package by more
clearly delineating the responsibilities between my Views and Components. 
For example, while GameView and AddGameView moves the responsibility of
generating components to separate component classes, MenuView does not.
I can refactor my code by creating more classes in the "components" package
for the components of my MenuView (such as a class called "ButtonsDisplay"). 
As my application grows, this will greatly improve cohesion and decrease coupling. 
 
- While the Composite Pattern does not apply, since my design does not have 
recursive hierarchical nesting, I could implement the Observer Pattern
to manage the movement of pieces on the board. For example, Game can be 
an observer of Gameboard, updating the status of the Game whenever there
is a change in the subject, the Gameboard.
 
- For my application right now, 
the specific location of where the files are saved and loaded from does not matter, 
and for my final GUI, I only want files to be saved and loaded from the 
same file. I can implement the Singleton Pattern with JsonWriter and 
JsonReader to ensure that the instances for each class are always one, and only one, instance with a fixed string path to save files to.
 
- I notice that GameManager and JanggiGui both have association arrows 
with multiplicity one to EventLog. For a small program such as mine, 
instantiating EventLog ahead of time has no noticeably significant 
impact on my application, but I would optimize the design by performing 
lazy instantiation of EventLog, giving a multiplicity of "0..1" and only 
instantiating EventLog right before we use it.
 





