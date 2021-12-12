# Brick Breaker Game
# Intro
- This is a very simple arcade video game, Brick Breaker.
- Users are required to break a wall of bricks with a ball.
- A paddle is used to prevent the ball from falling to the ground.
- 10 points will be given to every user for every brick destroyed.
- Users are given 3 lives at the start of the game. Each life is deducted as the ball falls from the window. The game will be over if all 3 lives are deducted.
- To move the paddle, users are allowed to do so by using the `A key/left key` to allow it to move left; while users can move right by using the `D key/right key`.
- To start and pause the game, users are allowed to do so by pressing on the `spacebar key`.
- To restart or exit the game, users are allowed to do so by clicking on the `esc key` and choose to restart/exit.
- To enable the game console to change the level and ball speed, users are allowed to do so by clicking on `ALT+SHIFT+F1` keys.

# Refactor In Code
- This project was separated into 3 different packages, which are `Compenents`, `Controller` and `Display` respectively.
- All spacings are reformatted to make the codes neater to read.
- Some variable names are changed to make the codes easier to understand.
- Longer and complicated codes are simplified to make it shorter and understandable.
- Crack class is separated from the brick class.
- MVC is implemented for encapsulation.
- JUnit tests are also added to test the files.

# Additional Features
- An instruction page is added for user guidance.
- Background images are added into the home menu, instructions, and the game board windows.
- A timer is added to display the time taken for each user to finish a level.
- An additional level with the highest difficulty is added into the game.
- Individual points for each brick destroyed in every level are added.
- A popup screen displaying the total points obtained from all levels are added.
- The number of lives (balls) displayed in game are replaced with heart emojis.
- Maven is implemented into the project for file building.