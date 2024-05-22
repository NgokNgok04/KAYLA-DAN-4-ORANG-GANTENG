package utils;
public class Pair<A,B> {
    private A first;
    private B second;

    public Pair(A first, B second){
        this.first = first;
        this.second = second;
    }
    
    public static Pair<Integer, Integer> convertTokenToPair(String token) {
        char letterPart = token.charAt(0);
        String numberPart = token.substring(1);

        int rowIndex = letterPart - 'A' + 1;
        int colIndex = Integer.parseInt(numberPart);

        return new Pair<>(rowIndex, colIndex);
    }



    public A getFirst(){
        return this.first;
    }

    public B getSecond(){
        return this.second;
    }

    public void setSecond(B value){
        if (this.second instanceof Integer && value instanceof Integer){
            this.second = value;
        }
    }
    public int convertPairToIdx(){
        if (this.first instanceof Integer && this.second instanceof Integer){
            int rowIdx = (Integer) this.first;
            int colIdx = (Integer) this.second;
            return (rowIdx*5 + colIdx);
        }

        return -1;
    }

}