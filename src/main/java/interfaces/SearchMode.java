package interfaces;

public enum SearchMode {
    NAME,
    LASTNAME,
    USERNAME;

    public String getText(){
        String s = "";
        switch (this){
            case NAME     -> s = "Name";
            case LASTNAME -> s = "Lastname";
            case USERNAME -> s = "Username";
        }
        return s;
    }
}
