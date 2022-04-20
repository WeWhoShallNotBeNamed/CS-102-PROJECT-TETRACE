public class Hangman{

    //properties
    private String[] words = {"catalog","database","studio","specification","termination"}; // fixed list - as an example; it can
    private StringBuffer secretWord;                                                        // be equipped through the construction.
    private StringBuffer allLetters;
    private StringBuffer usedLetters;
    private int numberOfIncorrectTries;
    private int maxAllowedIncorrectTries;
    private StringBuffer knownSoFar;

    //Constructor
    Hangman(){
        this.maxAllowedIncorrectTries = 6;
        this.secretWord = chooseSecretWord();
        this.usedLetters = new StringBuffer("");
        this.knownSoFar = new StringBuffer("");
        for(int i = 0; i < secretWord.length(); i++){
            knownSoFar.append("-"); // .append(str) adds str to current string
        }
        this.allLetters = new StringBuffer("a b c d e f g h i j k l m n o p q r s t u v w x y z"); //lower case letters in English alphabet (26)
    }

    //Methods
    public StringBuffer getAllLetters(){return this.allLetters;}
    public StringBuffer getUsedLetters(){return this.usedLetters;}
    public int getNumOfIncorrectTries(){return this.numberOfIncorrectTries;}
    public int getMaxAllowedIncorrectTries(){return this.maxAllowedIncorrectTries;}
    public StringBuffer getKnownSoFar(){return this.knownSoFar;}

    public boolean isGameOver(){      //this method returns -true- if the player correctly guesses all the letters,
        if(knownSoFar == secretWord){ //thus knownSoFar is compeletely opened, or when the player runs out of tries,
            return true;              //otherwise it returns -false-
        }else if(numberOfIncorrectTries == maxAllowedIncorrectTries){return true;}
        else{return false;}
    }

    public boolean hasLost(){
        if(numberOfIncorrectTries==maxAllowedIncorrectTries){
            return true;
        }else{return false;}
    }

    public int tryThis(char inputChar){
        int occurrence = 0;
        for(int i = 0; i < secretWord.length(); i++){
            if (secretWord.charAt(i).equals(inputChar)){
                occurrence++;
            }
        }
        return occurrence;
    }

    public void chooseSecretWord() {
        this.secretWord = new StringBuffer();

        // This is the fixed word list that the game will use.
        String[] wordList = { "angry",
                              "apple",
                              "brother",
                              "home",
                              "homework",
                              "sister",
                              "street",
                              "truth" };

        // Choosing a random word from the word list.
        int ind = (int) (Math.random() * wordList.length);
        this.secretWord.append(wordList[ind]);
    }
}