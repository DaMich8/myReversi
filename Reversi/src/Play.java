import java.util.HashSet;
import java.util.Scanner;

public class Play {
    static void play(Board b)
        {
            GameRules GR = new GameRules();
            System.out.println("White peace move first");
            b.updateScores();
            System.out.println("\nBlack peace placed: "+b.BCount+" White peace placed: "+b.WCount + " Space left to place peace:"+ b.emptySpace);
            Scanner scan = new Scanner(System.in);
            Move move = new Move(-1, -1);
            //b.displayBoard(b);
            String line;
            boolean turnSkip, lineCheck1, lineCheck2;
            int winner;
            while(true)
            {
                turnSkip=false;

                HashSet<Move> WhitePeacePlace = b.getWhereToPlacePeace('W','B');
                HashSet<Move> BlackPeacePlace = b.getWhereToPlacePeace('B','W');

                b.showPlaceAbleLocations(WhitePeacePlace);
                winner = b.findAWinner(WhitePeacePlace,BlackPeacePlace);
                if(winner==0)
                {
                    System.out.println("There is no winner it's a draw.");
                    break;
                }
                else if(winner==1)
                {
                    System.out.println("White player won with score:"+ b.WCount+ " : "+ b.BCount);
                    break;
                }
                else if(winner==2)
                {
                    System.out.println("Black player won with score:"+ b.BCount+ " : "+ b.WCount);
                    break;
                }
                if (WhitePeacePlace.isEmpty())
                {
                    System.out.println("No turn able to White player");
                    turnSkip=true;
                }
                if(!turnSkip)
                {
                    System.out.println("White player turn");
                    line = scan.nextLine();
                    char cha[] = line.toCharArray();
                    lineCheck1= Character.isLetter(line.charAt(0));
                    lineCheck2= Character.isDigit(line.charAt(1));
                    if(lineCheck1&&lineCheck2)
                    {
                        move.y = b.replaceX(line.charAt(0));
                        move.x = (Integer.parseInt(line.charAt(1) + "") - 1);
                    }

                    while(!lineCheck1||!lineCheck2||!WhitePeacePlace.contains(move))
                    {
                        System.out.println("Sorry you cannot place peace here try again\n\nWhite player turn");
                        line = scan.nextLine();
                        lineCheck1= Character.isLetter(line.charAt(0));
                        lineCheck2= Character.isDigit(line.charAt(1));
                        move.y=b.replaceX(line.charAt(0));
                        move.x=(Integer.parseInt(line.charAt(1)+"")-1);
                    }

                        b.placeAPeaces(move, 'W', 'B');
                        b.updateScores();
                        System.out.println("\nBlack peace placed: " + b.BCount + " White peace placed: " + b.WCount + " Space left to place peace:" + b.emptySpace);
                }

                WhitePeacePlace = b.getWhereToPlacePeace('W','B');
                BlackPeacePlace = b.getWhereToPlacePeace('B','W');

                b.showPlaceAbleLocations(BlackPeacePlace);
                winner = b.findAWinner(WhitePeacePlace,BlackPeacePlace);
                if(winner==0)
                {
                    System.out.println("There is no winner it's a draw.");
                    break;
                }
                else if(winner==1)
                {
                    System.out.println("White player won with score:"+ b.WCount+ " : "+ b.BCount);
                    break;
                }
                else if(winner==2)
                {
                    System.out.println("Black player won with score:"+ b.BCount+ " : "+ b.WCount);
                    break;
                }
                if(BlackPeacePlace.isEmpty())
                {
                    System.out.println("No turn able to Black player");
                    turnSkip=true;
                }
                if(!turnSkip)
                {
                    System.out.println("Black player turn");
                    line = scan.nextLine();
                    lineCheck1= Character.isLetter(line.charAt(0));
                    lineCheck2= Character.isDigit(line.charAt(1));
                    if(lineCheck1&&lineCheck2)
                    {
                        move.y = b.replaceX(line.charAt(0));
                        move.x = (Integer.parseInt(line.charAt(1) + "") - 1);
                    }
                    while(!lineCheck1||!lineCheck2||!BlackPeacePlace.contains(move))
                    {
                        System.out.println("Sorry you cannot place peace here try again\n\nBlack player turn");
                        line = scan.nextLine();
                        lineCheck1= Character.isLetter(line.charAt(0));
                        lineCheck2= Character.isDigit(line.charAt(1));
                        move.y=b.replaceX(line.charAt(0));
                        move.x=(Integer.parseInt(line.charAt(1)+"")-1);
                    }
                    b.placeAPeaces(move, 'B','W');
                    b.updateScores();
                    System.out.println("\nBlack peace placed: "+b.BCount+" White peace placed: "+b.WCount + " Space left to place peace:"+ b.emptySpace);
                }
            }
        }
}
