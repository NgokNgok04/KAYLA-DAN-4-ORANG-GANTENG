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
        
        int colIndex = letterPart - 'A';
        int rowIndex = Integer.parseInt(numberPart)-1;
        return new Pair<>(rowIndex, colIndex);
    }

    public static String convertPairToToken(Pair<Integer, Integer> pair) {
        int rowIndex = pair.getFirst();
        int colIndex = pair.getSecond();

        char letterPart = (char) ('A' + colIndex);
        String numberPart = Integer.toString(rowIndex+1);

        return letterPart +"0" +numberPart;
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

    public static Pair<Integer,Integer> convertIdxToPair(int idx){
        int colIdx = idx % 5;
        int rowIdx = Math.floorDiv(idx, 5);
        return new Pair<Integer,Integer>(rowIdx, colIdx);
    }
}