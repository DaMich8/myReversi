public class Move {
    int x,y;
    Move(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    @Override
    public String toString(){
        return "["+x+", "+y+"]";
    }

    @Override
    public boolean equals(Object o){
        return o.hashCode()==this.hashCode();
    }

    @Override
    public int hashCode() {
        return Integer.parseInt(x+""+y);
    }
}
