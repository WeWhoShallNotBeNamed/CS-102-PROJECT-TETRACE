# CS-102 Project: Tet-Race

Tet-Race is a Java-based desktop puzzle game derived from classic Tetris. Players clear rows by placing falling blocks without leaving empty spaces. The game introduces additional mechanics including power-ups, barriers, grid-shifting, and time-freezing elements. It features an in-game coin economy for purchasing tools, alongside multiple gameplay modes (Story, Limitless, and Multiplayer).

## Features
* **Game Modes:** Story Mode (level-based progression), Limitless Mode (endless survival), and Multiplayer Mode.
* **In-Game Economy:** Leveling up awards coins, which can be spent on utility tools in the shop.
* **Dynamic Modifiers:** Random obstacles such as screen changes, time-freezes, and barriers.

## Architecture & Dependencies
The project relies on inheritance to manage game entities and UI components:
* **Power-Ups:** `BlackOut` and `CutBack` inherit from the `PowerUp` base class.
* **Tetrominoes:** `J_Shape`, `L_Shape`, `O_Shape`, `S_Shape`, `T_Shape`, and `Z_Shape` inherit from `Tetrade`.
* **UI Components:** `Oldschool` and `Story_Label` inherit from `Tetris_Label`.