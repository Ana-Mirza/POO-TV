Name: Mirza Ana-Maria

Group: 321CA

# POO-TV

Description
-
Poo-Tv is a backend implementation of a platform for watching movies/series
from the user's perspective.
The basic functionalities offered for this platform include
logging in, registering, and moving on a specific page available.
From the movies page, the user can also search for a specific movie, filter the
movie list based on desired actors, genres, and even sort movies
based on duration and rating. In order to watch a movie, the user has to purchase
it for the standard price (2 tokens), or for free if the
user has premium account. A user can upgrade his account to
premium for 10 tokens and get 15 free movies, then he pays 2 tokens
for each purchase movie just like standard users. After purchasing
the movie, the user can watch it, then like and rate the
movie from 1 to 5. The tokens are taken explicitly by the
user from his balance. At the end, the user can log out and
the platform is restores to unauthenticated homepage.

Implementation
-
For the implementation of this platform, a visitor pattern was used
to describe each action's behaviour on a specific page. Each action
performed by the user has a different effect based on the page he
is currently on. If the action does not have permission on a page,
the output given is an error, otherwise, the output given will contain
information about the current page, user, and movie list visible.
In order to eliminate duplicate code, the output was build in wrapper
classes using a strategy pattern.
For the filtering action of the current movie list, a strategy pattern
was used as well, to implement the different types of filtering (by content/sort).
The factory design pattern was used for the instantiation of specific
pages and actions.


### Storing of input
The input consisting of actions given by the users, users available,
and movies are stored in the class 'Input' from package 'fileio'.
The initial input about users and movies available are also 
stored in a database that stores the program's status. That is
the current user, current page, movies available, total movies on
the platform, and total users registered.

### Flow of the game
The platform is initialized at the beginning using a Singleton pattern
and initializes the database of the platform. In order to execute the
actions given by the user, the program uses a 'for' structure to
parse the input commands, create specific instance of action,
and visit the current page with given action. Next, the action
creates an output based on its effect on the page, or an error
if something was not permitted. The changes are stored in the
platform's database, and the next action is executed.

### Interfaces Used
In order to wrap common functionalities together, several interfaces
and abstract classes were used:

* 'Action' interface to implement the specific action classes
* 'Feature' abstract class for feature classes
* 'Page' abstract class for the implementation of the pages available on the
platform
* 'OutputStrategy' abstract class for the implementation
of output classes
* 'FilterStrategy' interface for the filter strategy classes.


Feedback
-
The idea of this homework is very interesting and practical,
but its functionality was poorly detailed in the
homework's description. The output implementation was very misguiding.
For example, it was mentioned that only four on-page
actions had output and the others not, which was not
true in the tests. There were many other problems with
the action's effect on certain pages. I had to rewrite my
code based on the output expected on the tests, which
is not ok.
