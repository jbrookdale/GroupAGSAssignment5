class ConnectFourHardComputerPlayer {

    public int numPieces;
    public static final int maxPieces = 42, Empty = 2;
    public static boolean[][][] map;
    static int w = 10;
    static int h =7;
    static int n =4;
    public static final int winPlaces =(4 * w *h) - (3*w*n) - (3*h*n) + (3*w) + (3*h) - (4*n) + (2*n*n);
    public static void main(String[] args) {
        // Tests go here
    	System.out.println(winPlaces);
    }
    
    

      public void Connect4State() {
      // Initialize the map
      int i, j, k, count = 0;
      if (map == null) {
        map = new boolean[10][7][winPlaces];
        for (i = 0; i < 10; i++)
          for (j = 0; j < 7; j++) 
            for (k = 0; k < winPlaces; k++)
              map[i][j][k] = false;
      }

        // this loop Set the horizontal win positions
        for (i = 0; i < 10; i++)
          for (j = 0; j < 4; j++) {
            for (k = 0; k < 4; k++)
              map[j + k][i][count] = true;
            count++;
          }
        
        // this loop Set the vertical win positions
        for (i = 0; i < 7; i++){
          for (j = 0; j < 3; j++) {
            for (k = 0; k < 4; k++){
              map[i][j + k][count] = true;
            count++;
            }
          }
         }

        // Set the forward diagonal win positions 
        for (i = 0; i < 3; i++){
          for (j = 0; j < 4; j++) {
            for (k = 0; k < 4; k++){
              map[j + k][i + k][count] = true;
            count++;
            }
          }
        }

        // Set the backward diagonal win positions 
        for (i = 0; i < 3; i++){
          for (j = 6; j >= 3; j--) {
            for (k = 0; k < 4; k++){
              map[j - k][i + k][count] = true;
            count++;
            }
          }
        }
   }
      
}
      