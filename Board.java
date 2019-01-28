package ttt;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.Timer;

public class Board implements ActionListener, KeyListener
{
	
	public static Board board;
	
	public Renderer renderer;

	public int width = 500, height = 500;
	
	public int gameStatus = 0;
	
	public int turnCount = 0;
	
	public boolean up,down,left,right,space,enter;
	
	public Marker[][] base = new Marker[3][3];
	public Marker[][] ttt = new Marker[3][3];
	
	int xCor = 0, yCor = 0;
	
	Marker piece = new X();
	
	public boolean victory = false;
	
	public Board()
	{
		
		Timer timer = new Timer(20, this);
		JFrame jframe = new JFrame("Board");
		
		renderer = new Renderer();
		
		jframe.setSize(width + 15, height + 38);
		jframe.setVisible(true);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.add(renderer);
		
		jframe.addKeyListener(this);
		
		timer.start();
	}
	
	public void render(Graphics2D g)
	{
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width, height);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		if(gameStatus == 0)
		{
			g.setColor(Color.WHITE);
			g.setFont(new Font("Arial", 1, 50));
			g.drawString("Tic Tac Toe", width/2 - 145, 40);
			
			g.setFont(new Font("Arial", 1, 20));
			g.drawString("Press space to play", width / 2 - 100, 90);

		}
		
		if(gameStatus == 1)
		{
			g.setColor(Color.WHITE);
			
			g.setStroke(new BasicStroke(8f));
			g.drawLine(width / 3, 0, width / 3, height);
			g.drawLine((width / 3)*2, 0, (width / 3) * 2, height);
			
			g.drawLine(0, (height / 3), width, (height/ 3));
			g.drawLine(0, (height / 3)*2, width, (height/ 3)*2);
			
			g.setFont(new Font("Arial", 1, 200));
			for(int r = 0; r < 3; r++)
			{
				for( int c = 0; c < 3; c++)
				{
					if(ttt[r][c] != null)
					{
						if(ttt[r][c].toString().equals("X"))
						{
							g.setColor(Color.RED);
						}
						if(ttt[r][c].toString().equals("O"))
						{
							g.setColor(Color.BLUE);
						}
						g.drawString(ttt[r][c].toString(), (width/3) + ((170 * r) - 160), (height/3) + ((165 * c) - 10));
					}  
				}
			}
 	
			renderBase(piece);
			
			for(int a = 0; a < 3; a++)
			{
				for( int b = 0; b < 3; b++)
				{
					if(base[a][b] != null)
					{
						if(base[a][b].toString().equals("X"))
						{
							g.setColor(Color.RED);
						}
						if(base[a][b].toString().equals("O"))
						{
							g.setColor(Color.BLUE);
						}
					g.drawString(piece.toString(), (width/3) + ((170 * xCor) - 160), (height/3) + ((165 * yCor) - 10));
					} 
				}
			}
			if (isGameWon() != "*")
			{
				gameStatus = 2;
			}
		}
		
		if(gameStatus == 2)
		{
			
			g.setColor(Color.WHITE);
			
			g.setStroke(new BasicStroke(8f));
			g.drawLine(width / 3, 0, width / 3, height);
			g.drawLine((width / 3)*2, 0, (width / 3) * 2, height);
			
			g.drawLine(0, (height / 3), width, (height/ 3));
			g.drawLine(0, (height / 3)*2, width, (height/ 3)*2);
			
			g.setFont(new Font("Arial", 1, 200));
			for(int r = 0; r < 3; r++)
			{
				for( int c = 0; c < 3; c++)
				{
					if(ttt[r][c] != null)
					{
						if(ttt[r][c].toString().equals("X"))
						{
							g.setColor(Color.RED);
						}
						if(ttt[r][c].toString().equals("O"))
						{
							g.setColor(Color.BLUE);
						}
						g.drawString(ttt[r][c].toString(), (width/3) + ((170 * r) - 160), (height/3) + ((165 * c) - 10));
					}  
				}
			}
 	
			renderBase(piece);
			
			for(int a = 0; a < 3; a++)
			{
				for( int b = 0; b < 3; b++)
				{
					if(base[a][b] != null)
					{
						if(base[a][b].toString().equals("X"))
						{
							g.setColor(Color.RED);
						}
						if(base[a][b].toString().equals("O"))
						{
							g.setColor(Color.BLUE);
						}
					g.drawString(piece.toString(), (width/3) + ((170 * xCor) - 160), (height/3) + ((165 * yCor) - 10));
					} 
				}
			}
			
			
			g.setColor(Color.MAGENTA);
			if(isGameWon() == "O")
			{
				g.setColor(Color.MAGENTA);
			}
			g.setFont(new Font("Arial", 1, 35));
			g.drawString("Game Over " + isGameWon() + " Won!", width / 2 - 135, 90);
			
			
		}
	}
	
	public void switchPlayers()
	{
		if(turnCount %2 == 1)
		{
			X x = new X();
			piece = (Marker)x;
		}
		else if(turnCount %2 == 0)
		{
			O o = new O();
			piece = (Marker)o;
		}
		turnCount++;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		renderer.repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) 
	{
		int id = e.getKeyCode();
		
		if(gameStatus != 2)
		{
		if(id == KeyEvent.VK_SPACE)
		{
			if(gameStatus == 0)
			{
				gameStatus = 1;
			}
		}
		if (id == KeyEvent.VK_UP)
		{
			yCor--;
			if(yCor < 0)
			{
				yCor = 0;
			}
		}
		if (id == KeyEvent.VK_DOWN)
		{
			yCor++;
			if(yCor > 2)
			{
				yCor = 2;
			}
		}
		if (id == KeyEvent.VK_RIGHT)
		{
			xCor++;
			if(xCor > 2)
			{
				xCor = 2;
			}
			
		}
		if (id == KeyEvent.VK_LEFT)
		{
			xCor--;
			if(xCor < 0)
			{
				xCor = 0;
			}
		}
		if(id == KeyEvent.VK_ENTER)
		{
			renderMarker(piece);
		}
		}
		
		
	}

	public void renderMarker(Marker marker)
	{
		if(ttt[xCor][yCor] == null)
		{
			marker.addMarker(xCor,yCor);
			switchPlayers();
		}
		else
		{
			Toolkit.getDefaultToolkit().beep();
		}
	}
	
	public void renderBase(Marker marker)
	{
		marker.renderBase(xCor, yCor);
	}
	
	public String isGameWon()
	{
		int count = 0;
		
		for (int a = 0; a < 3; a++)
		{
			count = 0;
			for (int b = 0; b < 3; b++)
			{
				if(ttt[a][b] != null)
				{
					count++;
				}
				if (count == 3 && ttt[a][0].toString() == ttt[a][1].toString() && ttt[a][0].toString() == ttt[a][2].toString())
				{
					return ttt[a][0].toString();
				}
			}
		}
		
		for (int x = 0; x < 3; x++)
		{
			count = 0;
			for (int y = 0; y < 3; y++)
			{
				if(ttt[y][x] != null)
				{
					count++;
				}
				if (count == 3 && ttt[0][x].toString() == ttt[1][x].toString() && ttt[0][x].toString() == ttt[2][x].toString())
				{
					return ttt[0][x].toString();
				}
			}
		}
		
		if(ttt[1][1] == null)
		{
			return "*";
		}
		
		if((ttt[0][0] != null && ttt[2][2] != null) && (ttt[0][0].toString() == ttt[1][1].toString() && ttt[0][0].toString() == ttt[2][2].toString()))
		{
			return ttt[0][0].toString();
		}
		
		if ((ttt[0][2] != null && ttt[2][0] != null) && (ttt[0][2].toString() == ttt[1][1].toString() && ttt[0][2].toString() == ttt[2][0].toString()))
		{
			return ttt[0][2].toString();
		}
		
		return "*";
	}

	
	@Override
	public void keyReleased(KeyEvent e)
	{
	}

	@Override
	public void keyTyped(KeyEvent e) 
	{
		
	}
	
	public static void main(String[] args)
	{
		board = new Board();
		
	}

}
