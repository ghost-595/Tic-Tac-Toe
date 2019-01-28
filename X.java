package ttt;

public class X implements Marker
{
	
	X()
	{
		
	}
	
	public void addMarker(int x, int y)
	{	
		Board.board.ttt[x][y] = this;
	}
	
	public String toString()
	{ 
		return "X";
	}

	public void renderBase(int x, int y) 
	{
		Board.board.base[x][y] = this;
	}
	
}
