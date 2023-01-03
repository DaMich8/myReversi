import java.util.HashSet;
import java.util.Set;

class Board{
    public final char[][] field;
    int WCount, BCount, emptySpace;
    private char[] gameFieldX = new char[]{'A','B','C','D','E','F','G','H'};
    Board()
    {
        field = new char[][]{
                {'-','-','-','-','-','-','-','-',},
                {'-','-','-','-','-','-','-','-',},
                {'-','-','-','-','-','-','-','-',},
                {'-','-','-','W','B','-','-','-',},
                {'-','-','-','B','W','-','-','-',},
                {'-','-','-','-','-','-','-','-',},
                {'-','-','-','-','-','-','-','-',},
                {'-','-','-','-','-','-','-','-',},
        };
    }
    void updateScores()
    {
        WCount = 0;
        BCount = 0;
        emptySpace = 0;
        for(int i = 0; i < 8; i++)
        {
            for(int j=0;j<8; j++)
            {
                if(field[i][j]=='W')
                    WCount++;
                else if(field[i][j]=='B')
                    BCount++;
                else
                    emptySpace++;
            }
        }
    }
    private void displayBoard(Board b)
    {
        System.out.print("\n  ");
        for(int i=0; i<8;i++) System.out.print(gameFieldX[i]+" ");
        System.out.println();
        for(int i=0; i<8;i++)
        {
            System.out.print((i+1)+" ");
            for(int j=0;j<8;j++)
            {
                System.out.print(field[i][j]+" ");
            }
            System.out.println();
        }
    }
    public  void placeAPeaces(Move mo, char player, char opponent)
    {
        int i = mo.x, j = mo.y;
        field[i][j] = player;
        int rezI = i, rezJ = j;
        if (i - 1 >= 0 && field[i - 1][j] == opponent)
        {
            i--;
            while (i >= 0 && field[i][j] == opponent)
                i--;
            if (i >= 0 && field[i][j] == player)
            {
                while (i != rezI)
                    field[i++][j] = player;
            }
        }
        i = rezI;
        if (i + 1 < 8 && field[i + 1][j] == opponent)
        {
            i++;
            while (i < 8 && field[i][j] == opponent)
                i++;
            if (i < 8 && field[i][j] == player)
            {
                while (i != rezI)
                    field[i--][j] = player;
            }
        }
        i = rezI;
        if (j - 1 >= 0 && field[i][j - 1] == opponent)
        {
            j--;
            while (j >= 0 && field[i][j] == opponent)
                j--;
            if (j >= 0 && field[i][j] == player)
            {
                while (j != rezJ)
                    field[i][j++] = player;
            }
        }
        j = rezJ;
        if (j + 1 < 8 && field[i][j + 1] == opponent)
        {
            j++;
            while (j < 8 && field[i][j] == opponent)
                j++;
            if (j < 8 && field[i][j] == player)
            {
                while (j != rezJ)
                    field[i][j--] = player;
            }
        }
        j = rezJ;
        if (i - 1 >= 0 && j + 1 < 8 && field[i - 1][j + 1] == opponent)
        {
            i--;
            j++;
            while (i >= 0 && j < 8 && field[i][j] == opponent)
            {
                i--;
                j++;
            }
            if (i >= 0 && j < 8 && field[i][j] == player)
            {
                while (i != rezI && j != rezJ)
                    field[i++][j--] = player;
            }
        }
        i = rezI;
        j = rezJ;
        if(i - 1 >= 0 && j - 1 >= 0 && field[i-1][j-1] == opponent)
        {
            i--;
            j--;
            while(i > 0 && j>0 && field[i][j] == opponent)
            {
                i--;
                j--;
            }
            if(i >= 0 && j >= 0 && field[i][j] == player)
            {
                while(i != rezI && j != rezJ)
                    field[i++][j++] = player;
            }
        }
        i = rezI;
        j = rezJ;
        if(i + 1 < 8 && j + 1 < 8 && field[i+1][j+1]==opponent)
        {
            i++;
            j++;
            while(i < 8 && j < 8 && field[i][j]==opponent)
            {
                i++;
                j++;
            }
            if(i < 8 && j < 8 && field[i][j]==player)
            {
                while(i != rezI && j != rezJ)
                    field[i--][j--] = player;
            }
        }
        i = rezI;
        j = rezJ;
        if(i + 1 < 8 && j - 1 >=0 && field[i + 1][j - 1] == opponent)
        {
            i++;
            j--;
            while (i < 8 && j>=0 && field[i][j] == opponent)
            {
                i++;
                j--;
            }
            if(i<8 && j>=0 && field[i][j] == player)
            {
                while(i != rezI && j != rezJ)
                    field[i--][j++] = player;
            }
        }
    }
    private static void findPlaceAbleLocations(char[][] field, char player, char opponent, HashSet<Move> placeAblePositions)
    {
        for(int i = 0; i < 8; i++)
        {
            for(int j = 0; j < 8; j++)
            {
                if(field[i][j] == opponent)
                {
                    int rezI = i, rezJ = j;
                    if(i - 1 >= 0 && j - 1 >= 0 && field[i-1][j-1] == '-')
                    {
                        i++;
                        j++;
                        while(i < 7 && j < 7 && field[i][j] == opponent)
                        {
                            i++;
                            j++;
                        }
                        if(i < 8 && j < 8 && field[i][j] == player)
                            placeAblePositions.add(new Move(rezI - 1, rezJ - 1));
                    }
                    i = rezI;
                    j = rezJ;
                    if(i - 1 >= 0 && field[i-1][j] == '-')
                    {
                        i = i+1;
                        while(i < 8&& field[i][j] == opponent)
                            i++;
                        if(i < 8 && field[i][j] == player)
                            placeAblePositions.add(new Move(rezI - 1, rezJ));
                    }
                    i=rezI;
                    if(i - 1 >= 0 && j + 1 < 8 && field[i-1][j+1] == '-')
                    {
                        i++;
                        j--;
                        while(i < 8 && j > 0 && field[i][j] == opponent)
                        {
                            i++;
                            j--;
                        }
                        if(i < 8 && j >= 0 && field[i][j] == player)
                            placeAblePositions.add(new Move(rezI - 1, rezJ + 1));
                    }
                    i = rezI;
                    j = rezJ;
                    if(j - 1 >= 0 && field[i][j-1] == '-')
                    {
                        j++;
                        while(j < 8 && field[i][j] == opponent)
                            j++;
                        if(j < 8 && field[i][j] == player)
                            placeAblePositions.add(new Move(rezI, rezJ - 1));
                    }
                    j=rezJ;
                    if(j + 1 < 8 && field[i][j+1] == '-')
                    {
                        j--;
                        while(j > 0 && field[i][j] == opponent)
                            j--;
                        if(j >= 0 && field[i][j] == player)
                            placeAblePositions.add(new Move(rezI, rezJ + 1));
                    }
                    j = rezJ;
                    if(i + 1 < 8 && j - 1 >= 0 && field[i+1][j-1] == '-')
                    {
                        i--;
                        j++;
                        while(i > 0 && j < 8 && field[i][j] == opponent)
                        {
                            i--;
                            j++;
                        }
                        if(i >= 0 && j < 8 && field[i][j] == player)
                            placeAblePositions.add(new Move(rezI + 1, rezJ - 1));
                    }
                    i = rezI;
                    j = rezJ;
                    if(i + 1 < 8 && field[i+1][j] == '-')
                    {
                        i--;
                        while(i > 0 && field[i][j] == opponent)
                            i--;
                        if(i >= 0 && field[i][j] == player)
                            placeAblePositions.add(new Move(rezI + 1, rezJ));
                    }
                    i=rezI;
                    if(i + 1 <8 && j + 1 < 8 && field[i+1][j+1] == '-')
                    {
                        i--;
                        j--;
                        while(i > 0 && j > 0 && field[i][j] == opponent)
                        {
                            i--;
                            j--;
                        }
                        if(i >= 0 && j >= 0 && field[i][j] == player)
                            placeAblePositions.add(new Move(rezI + 1, rezJ + 1));
                    }
                    i = rezI;
                    j = rezJ;
                }
            }
        }
    }

    void showPlaceAbleLocations(HashSet<Move> show)
    {
       for(Move p:show)
            field[p.x][p.y]='@';
        displayBoard(this);
        for(Move p:show)
            field[p.x][p.y]='-';
    }
    HashSet<Move> getWhereToPlacePeace(char player, char opponent)
    {
        HashSet<Move> whereToPlacePeace = new HashSet<>();
        findPlaceAbleLocations(field, player,opponent,whereToPlacePeace);
        return whereToPlacePeace;
    }
    int replaceX(char x)
    {
        int replace;

        replace= Character.toLowerCase(x) - 'a';
        return replace;
    }
    int findAWinner(Set<Move> whiteMove, Set<Move> blackMove)
    {
        updateScores();
        if(emptySpace == 0)
        {
            if(WCount>BCount) return 1;
            else if(WCount<BCount) return 2;
            else return 0;
        }
        if(WCount==0 || BCount==0)
        {
            if(WCount>0) return 1;
            else if (BCount>0) return 2;
        }
        if(whiteMove.isEmpty()&&blackMove.isEmpty())
        {
            if(WCount>BCount) return 1;
            else if(WCount<BCount) return 2;
            else return 0;
        }
        return 3;
    }
}
