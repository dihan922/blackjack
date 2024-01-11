# Blackjack

A simple, text-based version of Blackjack

## Getting Started

Follow these instructions to get Blackjack running on your computer.

1. **Clone the Repository:**
    ```bash
    git clone https://github.com/dihan922/blackjack
    cd blackjack
    ```

2. **Compile file:**
    ```bash
    javac BlackJack.java
    ```

## Usage

1. **Start Program:**
    ```bash
    java BlackJack
    ```

2. **Gameplay:**
    - The user types a number amount of money to bet to start a turn.
    - On the turn, the user can add as many cards as they'd like to their hand (hit), however they will automatically lose if the total exceeds 21.
    - The user wins if one of the following conditions is met after they stop drawing cards (stand): they have a higher total than the dealer's unknown total, or the dealer's total exceeds 21.
    - At the end of the turn, the user can decide to keep playing or cash out.

## License

This project is licensed under the [MIT License](https://opensource.org/licenses/MIT).
