public class Chroma {
    // input from Image Reader somehow
    // will run with both cb and cr tables
    public void subSample(int[][] table){
        for (int i = 0; i < table.length; i+=2){
            for (int j = 0; j < table.length; j+=2){
                int average = (table[i][j] + table[i+1][j] + table[i][j+1] + table[i+1][j+1])/4;
                table[i][j] = average;
                table[i+1][j] = average;
                table[i][j+1] = average;
                table[i+1][j+1] = average;
            }
        }
    }

}
