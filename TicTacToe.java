import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe implements ActionListener
{
    int boardWidth = 500;
    int boardHeight = 550;

    JFrame frame = new JFrame("Tic-Tac-Toe");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel Panel = new JPanel();

    JButton[][] grid = new JButton[3][3];
    String player1 = "X";
    String player2 = "O";
    String Player = player1;

    boolean gameOver = false;
    int turns = 0;

    TicTacToe()
    {
        prepareFrame();
        addComponents();
        addActionListener();
    }

    public void prepareFrame()
    {
        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
    }

    public void addComponents()
    {
        textLabel.setBackground(Color.darkGray);
        textLabel.setForeground(Color.white);
        textLabel.setFont(new Font("Arial", Font.BOLD, 40));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Tic-Tac-Toe");
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH);

        Panel.setLayout(new GridLayout(3, 3));
        Panel.setBackground(Color.darkGray);
        frame.add(Panel);

        for (int row = 0; row < 3; row++)
        {
            for (int col = 0; col < 3; col++)
            {
                JButton tile = new JButton();
                grid[row][col] = tile;
                Panel.add(tile);

                tile.setBackground(Color.darkGray);
                tile.setForeground(Color.white);
                tile.setFont(new Font("Arial", Font.BOLD, 120));
                tile.setFocusable(false);
                tile.setBorder(BorderFactory.createLineBorder(Color.white, 3));
            }
        }
    }

    public void addActionListener()
    {
        for (int row = 0; row < 3; row++)
        {
            for (int col = 0; col < 3; col++)
            {
                grid[row][col].addActionListener(this);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (gameOver)
            return;
        JButton tile = (JButton) e.getSource();
        if (tile.getText() == "")
        {
            tile.setText(Player);
            turns++;
            checkWinner();
            if (!gameOver)
            {
                Player = Player == player1 ? player2 : player1;
                textLabel.setText(Player + "'s turn");
            }
        }
    }

    void checkWinner()
    {
        for (int row = 0; row < 3; row++)
        {
            if (grid[row][0].getText() == "") continue;

            if (grid[row][0].getText() == grid[row][1].getText() &&
                    grid[row][1].getText() == grid[row][2].getText())
            {
                for (int i = 0; i < 3; i++)
                {
                    setWinner(grid[row][i]);
                }
                gameOver = true;
                return;
            }
        }
        for (int col = 0; col < 3; col++) {
            if (grid[0][col].getText() == "")
                continue;

            if (grid[0][col].getText() == grid[1][col].getText() &&
                    grid[1][col].getText() == grid[2][col].getText())
            {
                for (int i = 0; i < 3; i++)
                {
                    setWinner(grid[i][col]);
                }
                gameOver = true;
                return;
            }
        }
        if (grid[0][0].getText() == grid[1][1].getText() &&
                grid[1][1].getText() == grid[2][2].getText() &&
                grid[0][0].getText() != "")
        {
            for (int i = 0; i < 3; i++)
            {
                setWinner(grid[i][i]);
            }
            gameOver = true;
            return;
        }
        if (grid[0][2].getText() == grid[1][1].getText() &&
                grid[1][1].getText() == grid[2][0].getText() &&
                grid[0][2].getText() != "")
        {
            setWinner(grid[0][2]);
            setWinner(grid[1][1]);
            setWinner(grid[2][0]);
            gameOver = true;
            return;
        }
        if (turns == 9) {
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    setTie(grid[row][col]);
                }
            }
            gameOver = true;
        }
    }

    void setWinner(JButton tile)
    {
        tile.setForeground(Color.decode("#2FCF00"));
        tile.setBackground(Color.decode("#99FFA2"));
        textLabel.setBackground(Color.decode("#99FFA2"));
        textLabel.setForeground(Color.decode("#2FCF00"));
        textLabel.setText("Victory! " + Player + " wins!");
    }

    void setTie(JButton tile)
    {
        tile.setForeground(Color.decode("#FF5D00"));
        tile.setBackground(Color.decode("#FFE795"));
        tile.setBorder(BorderFactory.createLineBorder(Color.decode("#FF5D00"), 3));
        textLabel.setBackground(Color.decode("#FFE795"));
        textLabel.setForeground(Color.decode("#FF5D00"));
        textLabel.setText("It's a tie!");
    }

    public static void main(String[] args) throws Exception
    {
        TicTacToe ticTacToe = new TicTacToe();
    }

}
