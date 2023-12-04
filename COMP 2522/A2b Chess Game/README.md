## Chess Game Project

## Purpose
- Undertake a larger project to expand on OOP and software development skills.
- Develop a collection of classes for a Chess game and design a game board using GUI components and layouts.

## Instruction
1. <b>Purpose</b> Testing the flexibility of A2a code design with the addition of new features. Working with streaming.
2. <b>Description</b> You are to add the following features to your Chess Program (A2a). <br><br>MOVEMENTS MUST BE DONE POLYMORPHICALLY – marks lost failing to follow good OOP practice. No attack moves are required nor special moves. Only the moves listed below are required and will be marked.<br><br>
Proper chess movements according to the chess piece: [final square must be empty]


## Demo
https://github.com/jenkwon92/BCIT_CST_TERM2/assets/70299766/e7d7a05a-5df2-44cc-a42c-9a7990c6eaf9



## Key Features & Requirements
1. Game Board:
    - An 8x8 board, alternating between black and white squares resembling a chessboard.
    - Should respond to clicks (e.g., change in color, display message).
2. Player Movement:
    - Move a piece by clicking on it and then clicking on a vacant square.
    - Correct chess movements or turns are not required.
3. Design:
    - Work on the design and gather feedback from the lab instructor.
    - The submitted design should be very similar to the final code.
    - Create all three types of UML design diagrams.
4. Piece Movement:
    - pawn – can move forward 1 or 2 on that piece’s FIRST move, 1 forward afterwards. 
    - Castle/Rook – can move horizontal or vertical unlimited number of spaces as long as the way is clear of other pieces
    - Bishop – can move on the diagonal unlimited number of spaces as long as the way is clear of other pieces
    - Knight – moves in an “L” pattern of 2-1 or 1-2 in any direction, the path does not need to be clear 
    - Queen – can move in any direction unlimited number of spaces as long as the way is clear of other pieces
    - King – can move in any direction 1 space
5. Turn:
    - Players taking turns during game

## Milestone
<details><summary>First submission
</summary>
<img width="625" alt="image" src="https://github.com/jenkwon92/BCIT_CST_TERM2/assets/70299766/9dd6f22f-35ca-421d-995e-e7ba560d41bf">
<img width="695" alt="image" src="https://github.com/jenkwon92/BCIT_CST_TERM2/assets/70299766/07c2c31e-72d8-427d-91d7-102e9d0ddbd8">
<img width="636" alt="image" src="https://github.com/jenkwon92/BCIT_CST_TERM2/assets/70299766/a93beb59-77c5-41e2-bbad-54f5a390f171">
</details>

>Can your MoveListener determine if a move is valid? Or is that something a Piece is better suited?
Does a Tile need to be aware of it's location? Or is it enough that the Board knows the locations?
Boards understand paths which a Piece can use to determine if the path is valid
Think about all of your possible sequences including ones that are wrong. This will help you work out the methods
<details><summary>Second submission
</summary>
<img width="696" alt="image" src="https://github.com/jenkwon92/BCIT_CST_TERM2/assets/70299766/73b93433-dad5-4033-a6fe-4004f0e696b4">
<img width="479" alt="image" src="https://github.com/jenkwon92/BCIT_CST_TERM2/assets/70299766/e8ca5736-0ed5-44e4-80f7-34381d83b089">
<img width="641" alt="image" src="https://github.com/jenkwon92/BCIT_CST_TERM2/assets/70299766/21001320-6a3c-426d-a105-fefa6bc8628c">
</details>

>My comments are suggestions to get you to think about your design. It's not that your design won't work but the aim isn't to get a chess game working but to build a flexible product that can be easily modified to handle new features without redoing your previous code. 
<br><br>
This isn't a design course so yes I expect you are struggling. I'm not expecting a perfect design or anything close. I'm hoping you can take this opportunity to try your best to put as much OOP into your program rather than code it like a C application. This means to think differently. So I asked you for a design so I can comment on it and make suggestions and ask you questions for you to explore and think on. That is the best way to learn about design. You can look at lots of other designs for other applications but you will learn more from this approach of trying, getting feedback, and thinking on that feedback then reviewing other designs.
<br><br>
My focus on design here is more about OOP than full design. So what you need to always be asking is: "What does this class know about the world?" Piece understands if a given path is valid for it. Board can create a path based on a user's selection of start and end (since the Board lays out the Tiles). Etc.
<br><br>
When a user clicks on a Tile there are 3 possibilities: 1) there is something on the Tile and it's owned by that player 2) there is something on the Tile and it's owned by the other player 3) there is nothing on that tile. Given the above there are 6 permutations including 1st click=empty, 1st click=other player piece, 1st click = piece, 2nd click=empty, 1st click=piece, 2nd click same player piece, etc Each of these can  be sequenced with specific responses.
<br><br>
ChessBoard - does it not contain Tiles? I asked this earlier. Something must exist on your board unless you plan on drawing up a grid. However a) that is difficult b) you will then have to calculate where the user clicked rather than simply responding to a click on an object. If you do have a Tile object it should be listed. EventListener should be then attached to each Tile object (you can use the same listener on each tile)
<br><br>
Piece - you are assuming that your movement is on a 2D grid. But what if that changes? Will you need to alter each Piece canMove() to handle this? If yes, is there a better way? (Yes) 
<br><br>
Again, it's not that your design won't work but it's not very flexible to changes and would require you to modify previously written code and is not taking advantage of OOP. I would like you to explore options (such as I suggest you to think about). See if you can design your program to be decoupled upon assumptions about the game as much as possible (such as the board is an 8x8 2D grid). 
## Final product
### Contents of Folder
```
Classes 
├── Bishop.java          # Represents a Bishop in a chess game, including its movement and capture logic.
├── ChessBoard.java      # Manages the graphical display of the chessboard and interaction with the game pieces.
├── ChessGame.java       # Handles the overall game logic, turn management, and rules of the chess game.
├── EventListener.java   # Manages user interactions and events within the community features of the game.
├── King.java            # Represents a King in a chess game, including its movement and check/checkmate logic.
├── Knight.java          # Represents a Knight in a chess game, capable of L-shaped movements and jumps.
├── Pawn.java            # Represents a Pawn in a chess game, including its unique first-move and promotion logic.
├── Piece.java           # An abstract class that serves as a template for all the different types of chess pieces
├── Player.java          # Manages player-specific information, such as color, and whether it's their turn.
├── Queen.java           # Represents a Queen in a chess game, with the most powerful movements in all directions
└── Rook.java            # Represents a Rook in a chess game, with horizontal and vertical movement capabilities.
```
### Images
<img width="359" alt="image" src="https://github.com/jenkwon92/BCIT_CST_TERM2/assets/70299766/8ff4fac0-ab21-4772-b9e0-41006b832c14">
<img width="362" alt="image" src="https://github.com/jenkwon92/BCIT_CST_TERM2/assets/70299766/6807d1d6-aead-4f65-9573-5c2bf689b3ed">
<img width="361" alt="image" src="https://github.com/jenkwon92/BCIT_CST_TERM2/assets/70299766/1182625c-6a80-44bc-9fe6-b222f46fd2ed">
<img width="359" alt="image" src="https://github.com/jenkwon92/BCIT_CST_TERM2/assets/70299766/8988f562-3bc0-45d2-89c4-20de0861c60e">
<img width="359" alt="image" src="https://github.com/jenkwon92/BCIT_CST_TERM2/assets/70299766/12410b4e-459b-4131-9dda-921a74898609">

## Known Bugs and Limitations

## Features for Future
