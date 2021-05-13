package tambolo;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;
import javax.swing.border.Border;

public class UI {
	Font UIFont = new Font(Font.MONOSPACED, Font.BOLD, 48);
	Font Smaller = new Font(Font.MONOSPACED, Font.ITALIC, 32);
	Font caution = new Font(Font.SANS_SERIF, Font.BOLD, 24);
	Color bg = new Color(196, 250, 248);
	Border Padding = BorderFactory.createEmptyBorder(35, 35, 35, 35);
	
	int Tickets_num;
	
	void welcome_frame_init() {
		JFrame welcome_frame = new JFrame();
		JPanel Welcome_Panel = new JPanel();
		Welcome_Panel.setLayout(new GridLayout(4, 1, 20, 10));
		Welcome_Panel.setBorder(Padding);
		Welcome_Panel.setBackground(bg);
		JLabel welcome_text_1 = new JLabel("Welcome\n");
		JLabel welcome_text_2 = new JLabel("To");
		JLabel welcome_text_3 = new JLabel("Automated Tambolo");
		JLabel welcome_text_4 = new JLabel("Press any key to continue...");
		
		welcome_text_1.setHorizontalAlignment(JLabel.CENTER);
		welcome_text_2.setHorizontalAlignment(JLabel.CENTER);
		welcome_text_3.setHorizontalAlignment(JLabel.CENTER);
		welcome_text_4.setHorizontalAlignment(JLabel.CENTER);
		welcome_text_1.setFont(UIFont);
		welcome_text_2.setFont(UIFont);
		welcome_text_3.setFont(UIFont);
		welcome_text_4.setFont(UIFont);
		
		Welcome_Panel.add(welcome_text_1);
		Welcome_Panel.add(welcome_text_2);
		Welcome_Panel.add(welcome_text_3);
		Welcome_Panel.add(welcome_text_4);
		
		welcome_frame.add(Welcome_Panel);
		welcome_frame.pack();
		welcome_frame.setVisible(true);
		welcome_frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		welcome_frame.addKeyListener(new KeyListener() {
			@Override
			public void keyReleased(KeyEvent e) {
				welcome_frame.dispose();
				player_details_frame_init();
			}
			@Override
			public void keyTyped(KeyEvent e) { }
			@Override
			public void keyPressed(KeyEvent e) { }
			
		});
	}
	
	void player_details_frame_init() {
		JFrame player_details_frame = new JFrame();
		JPanel input_panel = new JPanel();
		input_panel.setBackground(bg);
		input_panel.setLayout(new GridBagLayout());
		input_panel.setBorder(Padding);
		JLabel input_label = new JLabel("Enter num of tickets: ");
		input_label.setFont(Smaller);
		JLabel caution_label = new JLabel("Please enter a number");
		caution_label.setFont(caution);
		caution_label.setForeground(Color.red);
		
		JTextField inputNoOfTickets = new JTextField(10);
		inputNoOfTickets.setFont(UIFont);
		inputNoOfTickets.setBackground(Color.white);
		inputNoOfTickets.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		//for input_label
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		input_panel.add(input_label, gbc);
		//for Text Field
		gbc.gridx = 1;
		gbc.gridy = 0;
		input_panel.add(inputNoOfTickets, gbc);
		
		JButton goToPrizes = new JButton("Select Prizes");
		//gridy = 1 is used for caution
		inputNoOfTickets.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) { }	
			@Override
			public void keyPressed(KeyEvent e) { }
			@Override
			public void keyReleased(KeyEvent e) {
				char current_letter = e.getKeyChar();
				if(current_letter == 10) {
					goToPrizes.doClick();
					return;
				}
				
				if(!Character.isDigit(current_letter) && current_letter != 65535 && current_letter >10 ) {
					try {
					String current = inputNoOfTickets.getText();
					current = current.substring(0, current.length()-1);
					inputNoOfTickets.setText(current);
					
					gbc.gridx = 1;
					gbc.gridy = 1;
					input_panel.add(caution_label, gbc);
					player_details_frame.revalidate();
					player_details_frame.pack();
					} catch(Exception excep) {}
				}
			}
			
		});
	
		
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.gridwidth = 2;
		input_panel.add(goToPrizes, gbc);		
		goToPrizes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Tickets_num = Integer.parseInt(inputNoOfTickets.getText());
				} catch(Exception excep) {
					gbc.gridx = 1;
					gbc.gridy = 1;
					gbc.gridwidth = 1;
					input_panel.add(caution_label, gbc);
					player_details_frame.revalidate();
					player_details_frame.pack();
					return;
				}
				player_details_frame.dispose();
				prize_frame_init();
			}
		});
		
		player_details_frame.add(input_panel);
		player_details_frame.pack();
		player_details_frame.setVisible(true);
		player_details_frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
	
	void prize_frame_init() {
		JFrame prize_frame = new JFrame();
		JPanel prize_panel = new JPanel();
		prize_panel.setBorder(Padding);
		prize_panel.setBackground(bg);
		prize_panel.setLayout(new GridBagLayout());
		JLabel caution_label = new JLabel("Please enter a number");
		caution_label.setFont(caution);
		caution_label.setForeground(Color.red);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		JLabel introToPrizes = new JLabel("<html><u>Select The Prizes To Play With</u></html>");
		introToPrizes.setFont(UIFont);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		prize_panel.add(introToPrizes, gbc);
		
		JCheckBox J5 = new JCheckBox("First 5"); //gridy = 1
		J5.setFont(Smaller);
		J5.setBackground(bg);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		prize_panel.add(J5, gbc);
		
		J5.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) { }
			@Override
			public void keyPressed(KeyEvent e) {}
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyChar() == 10) {
					J5.doClick();
				}
			}
			
		});
		
		JCheckBox LINES = new JCheckBox("Lines"); //gridy = 2
		LINES.setFont(Smaller);
		LINES.setBackground(bg);
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 2;
		prize_panel.add(LINES, gbc);
		
		LINES.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) { }
			@Override
			public void keyPressed(KeyEvent e) { }	
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyChar() == 10) {
					LINES.doClick();
				}
			}
		});
		
		JPanel numOfLines_panel = new JPanel();
		numOfLines_panel.setBackground(bg);
		JLabel numOfLines_Label = new JLabel("Enter No. of Lines:");
		numOfLines_Label.setFont(Smaller);
		JTextField numOfLines = new JTextField(10); //gridy = 2
		numOfLines.setFont(Smaller);
		numOfLines_panel.add(numOfLines_Label);
		numOfLines_panel.add(numOfLines);
		numOfLines_panel.setBorder(BorderFactory.createLineBorder(Color.black, 2));

		LINES.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(LINES.isSelected()) {
					gbc.gridx = 1;
					gbc.gridy = 2;
					gbc.weightx = 0.3;
					gbc.gridwidth = 1;
					prize_panel.add(numOfLines_panel, gbc);
					prize_panel.remove(LINES);
					gbc.gridx = 0;
					gbc.gridy = 2;
					gbc.gridwidth = 1;
					gbc.anchor = GridBagConstraints.EAST;
					prize_panel.add(LINES, gbc);
					gbc.anchor = GridBagConstraints.CENTER;
					prize_frame.revalidate();
					prize_frame.pack();
				}
				else {
					prize_panel.remove(numOfLines_panel);
					prize_panel.remove(LINES);
					prize_panel.add(LINES, gbc);
					prize_frame.revalidate();
					prize_frame.pack();
				}
			}
		});
		JButton goToPlay = new JButton("PLAY!");
		numOfLines.addKeyListener(new KeyListener() { //gridy = 3 = caution
			@Override
			public void keyTyped(KeyEvent e) { }	
			@Override
			public void keyPressed(KeyEvent e) { }
			@Override
			public void keyReleased(KeyEvent e) {
				char current_letter = e.getKeyChar();
				if(current_letter == 10) {
					goToPlay.doClick();
				}
				if(!Character.isDigit(current_letter) && current_letter != 65535 && current_letter >10 ) {
					try {
					String current = numOfLines.getText();
					current = current.substring(0, current.length()-1);
					numOfLines.setText(current);
					
					gbc.gridx = 1;
					gbc.gridy = 3;
					prize_panel.add(caution_label, gbc);
					prize_frame.revalidate();
					prize_frame.pack();
					} catch(Exception excep) {} //out of bounds
				}
			}
			
		});
		
		gbc.gridx = 0;
		gbc.gridwidth = 2;
		gbc.gridy = 4;
		prize_panel.add(goToPlay, gbc);
		
		goToPlay.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int n = -1;
				if(LINES.isSelected()) {
					try {
						n = Integer.parseInt(numOfLines.getText());
					}catch(Exception excep) {
						gbc.gridx = 1;
						gbc.gridy = 3;
						gbc.gridwidth = 1;
						prize_panel.add(caution_label, gbc);
						prize_frame.revalidate();
						prize_frame.pack();
						return;
					}
					Prizes.LINE_PRIZES = new Boolean[n];
					for(int i = 0; i<n; i++) {
						Prizes.LINE_PRIZES[i] = true;
					}
				}//do above if LINES is selected
				prize_frame.dispose();
				play_frame_init();
			}
		});
		
		
		prize_frame.add(prize_panel);
		prize_frame.pack();
		prize_frame.setVisible(true);
		prize_frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
	
	void play_frame_init() {
		JFrame play_frame = new JFrame();
		JFrame ticket_frame = new JFrame();
		JPanel play_panel = new JPanel();
		JPanel ticket_panel = new JPanel();
		ticket_panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridy = 0;
		
		ticketRender[] ticket_list = new ticketRender[Tickets_num];
		for(int i = 0; i<Tickets_num; i++) {
			gbc.gridy++;
			ticket_list[i] = new ticketRender("demo");
			ticket_panel.add(ticket_list[i], gbc);
		}
		JScrollPane jsp = new JScrollPane(ticket_panel);
		ticket_frame.add(jsp);
		ticket_frame.pack();
		ticket_frame.setVisible(true);
		ticket_frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
	
	
	
	public static void main(String[] args) {
		UI ob = new UI();
		ob.welcome_frame_init();
		
	}
}
