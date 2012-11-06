package clueGame;

public class BadConfigFormatException extends Exception{
	private String name;

    public BadConfigFormatException(String name){
        this.name = name;
    }

    public String toString(){
        return "[" + name + "] is not a valid set of rooms";
    }
}
